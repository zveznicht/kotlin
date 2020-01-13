/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.common.lower

import org.jetbrains.kotlin.backend.common.CommonBackendContext
import org.jetbrains.kotlin.backend.common.FileLoweringPass
import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.common.ir.copyTo
import org.jetbrains.kotlin.backend.common.ir.simpleFunctions
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.builders.irBlockBody
import org.jetbrains.kotlin.ir.builders.irCall
import org.jetbrains.kotlin.ir.builders.irGet
import org.jetbrains.kotlin.ir.builders.irReturn
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.declarations.impl.IrFunctionImpl
import org.jetbrains.kotlin.ir.descriptors.WrappedSimpleFunctionDescriptor
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrFunctionReference
import org.jetbrains.kotlin.ir.expressions.impl.IrFunctionReferenceImpl
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl
import org.jetbrains.kotlin.ir.types.IrSimpleType
import org.jetbrains.kotlin.ir.types.classOrNull
import org.jetbrains.kotlin.ir.types.typeOrNull
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid
import org.jetbrains.kotlin.name.Name

class FunctionReferencesWithDefaultsLowering(val context: CommonBackendContext) : FileLoweringPass {
    private object DECLARATION_ORIGIN_FUNCTION_REFERENCE_WITH_DEFAULTS : IrDeclarationOriginImpl("FUNCTION_REFERENCE_WITH_DEFAULTS")

    private fun IrClass.getInvokeFunction() = simpleFunctions().single { it.name.asString() == "invoke" }

    private var index = 0

    override fun lower(irFile: IrFile) {
        var generatedFunctions = mutableListOf<IrSimpleFunction>()
        irFile.transform(object : IrElementTransformerVoidWithContext() {

            override fun visitDeclaration(declaration: IrDeclaration): IrStatement {
                lateinit var tempGeneratedFunctions: MutableList<IrSimpleFunction>
                if (declaration is IrClass) {
                    tempGeneratedFunctions = generatedFunctions
                    generatedFunctions = mutableListOf()
                }
                val result = super.visitDeclaration(declaration)
                if (declaration is IrClass) {
                    declaration.declarations += generatedFunctions
                    generatedFunctions = tempGeneratedFunctions
                }
                return result
            }

            override fun visitFunctionReference(expression: IrFunctionReference): IrExpression {
                expression.transformChildrenVoid(this)

                val bridgeBuilder = BridgeBuilder(expression)
                if (!bridgeBuilder.needBridge) return expression

                val (bridge, functionReference) = bridgeBuilder.build(currentClass?.irElement as? IrClass ?: irFile)
                generatedFunctions.add(bridge)
                return functionReference
            }
        }, data = null)

        irFile.declarations += generatedFunctions
    }

    inner class BridgeBuilder(val functionReference: IrFunctionReference) {
        val startOffset = functionReference.startOffset
        val endOffset = functionReference.endOffset
        val referencedFunction = functionReference.symbol.owner
        val functionParameters = referencedFunction.explicitParameters
        val boundFunctionParameters = functionReference.getArgumentsWithIr().map { it.first }

        val functionReferenceType = functionReference.type as IrSimpleType
        val returnType = functionReferenceType.arguments.last().typeOrNull!!
        val functionParameterTypes = functionReferenceType.arguments.map { it.typeOrNull!! }.dropLast(1)

        val typeArgumentsMap = referencedFunction.typeParameters.associate { typeParam ->
            typeParam.symbol to functionReference.getTypeArgument(typeParam.index)!!
        }

        val superClass = functionReferenceType.classOrNull!!.owner
        val superFunction = superClass.getInvokeFunction()
        val superTypeArgumentsMap = superClass.typeParameters.associate { typeParam ->
            typeParam.symbol to functionReferenceType.arguments[typeParam.index].typeOrNull!!
        }

        val bridgeValueParameters = mutableListOf<IrValueParameter>()

        init {
            var unboundIndex = 0
            val boundArgsSet = boundFunctionParameters.toSet()
            for (parameter in functionParameters) when {
                parameter in boundArgsSet -> bridgeValueParameters += parameter

                unboundIndex == functionParameterTypes.size && parameter.defaultValue != null -> {
                    // The default value is to be used.
                }

                !parameter.isVararg -> {
                    assert(unboundIndex < functionParameterTypes.size) {
                        "Exhausted all <invoke> arguments"
                    }
                    bridgeValueParameters += parameter
                    ++unboundIndex
                }

                else -> {
                    var count = 0
                    while (unboundIndex < functionParameterTypes.size) {
                        val param = superFunction.valueParameters[unboundIndex]
                        val substitutedParamType = param.type.substitute(typeArgumentsMap).substitute(superTypeArgumentsMap)
                        if (substitutedParamType == parameter.varargElementType!!) {
                            ++unboundIndex
                            ++count
                        } else {
                            if (substitutedParamType == param.type && count == 0)
                                ++unboundIndex
                            break
                        }
                    }
                    bridgeValueParameters += parameter
                }
            }
        }

        val needBridge get() = bridgeValueParameters.size != functionParameters.size

        fun build(parent: IrDeclarationContainer) = buildBridge(parent).let { bridge ->
            bridge to IrFunctionReferenceImpl(
                startOffset, endOffset,
                functionReferenceType,
                bridge.symbol,
                0,
                bridge.valueParameters.size
            ).apply {
                functionReference.getArgumentsWithIr().forEach { (parameter, argument) ->
                    val index = bridgeValueParameters.indexOf(parameter)
                    assert(index >= 0) {
                        "Unexpected bound parameter: ${parameter.dump()}"
                    }
                    putValueArgument(index, argument)
                }
            }
        }

        // TODO: Probably we should create only one function for each different set of default arguments specified.
        private fun buildBridge(parent: IrDeclarationContainer): IrSimpleFunction {
            val descriptor = WrappedSimpleFunctionDescriptor()
            return IrFunctionImpl(
                startOffset, endOffset,
                DECLARATION_ORIGIN_FUNCTION_REFERENCE_WITH_DEFAULTS,
                IrSimpleFunctionSymbolImpl(descriptor),
                Name.identifier("${referencedFunction.name}\$${index++}"),
                Visibilities.PRIVATE,
                Modality.FINAL,
                returnType,
                isInline = false,
                isExternal = false,
                isTailrec = false,
                isSuspend = referencedFunction.isSuspend,
                isExpect = false,
                isFakeOverride = false,
                isOperator = false
            ).apply {
                descriptor.bind(this)
                val function = this
                this.parent = parent

                bridgeValueParameters.mapIndexedTo(valueParameters) { index, parameter ->
                    parameter.copyTo(
                        function, DECLARATION_ORIGIN_FUNCTION_REFERENCE_WITH_DEFAULTS, index,
                        type = parameter.type.substitute(typeArgumentsMap).substitute(superTypeArgumentsMap)
                    )
                }

                body = context.createIrBuilder(function.symbol, startOffset, endOffset).irBlockBody(startOffset, endOffset) {
                    +irReturn(
                        irCall(functionReference.symbol).apply {
                            for (parameter in functionParameters) {
                                val index = bridgeValueParameters.indexOf(parameter)
                                if (index < 0)
                                    continue
                                val argument = irGet(valueParameters[index])
                                when (parameter) {
                                    referencedFunction.dispatchReceiverParameter -> dispatchReceiver = argument
                                    referencedFunction.extensionReceiverParameter -> extensionReceiver = argument
                                    else -> putValueArgument(parameter.index, argument)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}