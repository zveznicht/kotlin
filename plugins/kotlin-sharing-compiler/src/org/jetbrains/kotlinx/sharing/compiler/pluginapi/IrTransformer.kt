/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing.compiler.pluginapi

import org.jetbrains.kotlin.backend.common.ClassLoweringPass
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.ir.builders.*
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrBlockBody
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.*
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.declareSimpleFunctionWithOverrides
import org.jetbrains.kotlin.ir.util.statements
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi2ir.Psi2IrConfiguration
import org.jetbrains.kotlin.psi2ir.generators.DeclarationGenerator
import org.jetbrains.kotlin.psi2ir.generators.FunctionGenerator
import org.jetbrains.kotlin.psi2ir.generators.GeneratorContext
import org.jetbrains.kotlin.psi2ir.generators.GeneratorExtensions
import org.jetbrains.kotlin.types.KotlinType

abstract class IrTransformer(override val compilerContext: IrPluginContext) : IrBuilderExtension, ClassLoweringPass {
    fun createMissingParts(irClass: IrClass, pluginDescriptors: Collection<DeclarationDescriptor>) {
        pluginDescriptors.sortedBy { if (it is PropertyDescriptor) -1 else 1 }.forEach { desc ->
            val res: IrDeclaration = when (desc) {
                is PropertyDescriptor -> createPropertyForBackend(irClass, desc)
                is SimpleFunctionDescriptor -> createFunctionForBackend(irClass, desc)
                else -> TODO()
            }
            irClass.declarations.add(res)
        }
        // todo: remove sorting hack
//        val declarations: List<IrDeclaration> = pluginDescriptors.sortedBy { if (it is PropertyDescriptor) -1 else 1 }.map<DeclarationDescriptor, IrDeclaration> { desc ->
//            when (desc) {
//                is PropertyDescriptor -> createPropertyForBackend(irClass, desc)
//                is SimpleFunctionDescriptor -> createFunctionForBackend(irClass, desc)
//                else -> TODO()
//            }
//        }
//        irClass.declarations.addAll(declarations)
    }

    inline fun IrSimpleFunction.prepend(statements: ExtendedBodyBuilder.() -> Unit) {
        val oldBody = body?.statements.orEmpty()
        body = IrBlockBodyImpl(startOffset, endOffset, buildBody(statements).statements + oldBody)
    }

    inline fun IrSymbolOwner.buildBody(blockBody: ExtendedBodyBuilder.() -> Unit): IrBlockBody {
        return ExtendedBodyBuilder(compilerContext, this.symbol).apply { blockBody() }.doBuild()
    }

    inline fun IrSymbolOwner.buildExpression(builder: IrSingleStatementBuilder.() -> IrExpression): IrExpression {
        return IrSingleStatementBuilder(compilerContext, Scope(this.symbol), startOffset, endOffset).build(builder)
    }

    // todo: add owner
    open fun defineBackingField(propertyDescriptor: PropertyDescriptor, irProperty: IrProperty, createdField: IrField) = Unit

    open fun defineFunctionBody(functionDescriptor: SimpleFunctionDescriptor, irFunction: IrSimpleFunction): IrBody? = null

    private fun createFunctionForBackend(owner: IrClass, frontendFunction: SimpleFunctionDescriptor): IrSimpleFunction {
        val f: IrSimpleFunction = owner.declareSimpleFunctionWithExternalOverrides(frontendFunction)
        f.parent = owner
        f.buildWithScope { functionGenerator.generateFunctionParameterDeclarationsAndReturnType(f, null, null) }
        f.body = defineFunctionBody(frontendFunction, f)
        return f
    }

    private fun createPropertyForBackend(
        owner: IrClass,
        frontendProperty: PropertyDescriptor
    ): IrProperty {
        val irProperty = compilerContext.symbolTable.declareProperty(
            owner.startOffset, owner.endOffset,
            PLUGIN_ORIGIN, frontendProperty
        )
        irProperty.parent = owner
        irProperty.backingField = generatePropertyBackingField(frontendProperty, irProperty).apply {
            parent = owner
            correspondingPropertySymbol = irProperty.symbol
        }
        val fieldSymbol = irProperty.backingField!!.symbol
        irProperty.getter = frontendProperty.getter?.let { generatePropertyAccessor(it, fieldSymbol) }
            ?.apply { parent = owner }
        irProperty.setter = frontendProperty.setter?.let { generatePropertyAccessor(it, fieldSymbol) }
            ?.apply { parent = owner }
        return irProperty
    }

    private fun IrClass.declareSimpleFunctionWithExternalOverrides(descriptor: FunctionDescriptor): IrSimpleFunction {
        return compilerContext.symbolTable.declareSimpleFunction(
                startOffset, endOffset,
                PLUGIN_ORIGIN, descriptor
            )
            .also { f ->
                f.overriddenSymbols = descriptor.overriddenDescriptors.map {
                    compilerContext.symbolTable.referenceSimpleFunction(it.original)
                }
            }
    }

    private val functionGenerator: FunctionGenerator by lazy {
        with(compilerContext) {
            FunctionGenerator(
                DeclarationGenerator(
                    GeneratorContext(
                        Psi2IrConfiguration(),
                        moduleDescriptor,
                        bindingContext,
                        languageVersionSettings,
                        symbolTable,
                        GeneratorExtensions(),
                        typeTranslator,
                        typeTranslator.constantValueGenerator,
                        irBuiltIns
                    )
                )
            )
        }
    }

    private fun generatePropertyBackingField(
        propertyDescriptor: PropertyDescriptor,
        originProperty: IrProperty
    ): IrField {
        return compilerContext.symbolTable.declareField(
            originProperty.startOffset,
            originProperty.endOffset,
            PLUGIN_ORIGIN,
            propertyDescriptor,
            propertyDescriptor.type.toIrType()
        ).apply { defineBackingField(propertyDescriptor, originProperty, this) }
    }

    private fun generatePropertyAccessor(
        descriptor: PropertyAccessorDescriptor,
        fieldSymbol: IrFieldSymbol
    ): IrSimpleFunction {
        val declaration = compilerContext.symbolTable.declareSimpleFunctionWithOverrides(
            fieldSymbol.owner.startOffset,
            fieldSymbol.owner.endOffset,
            PLUGIN_ORIGIN, descriptor
        )
        return declaration.buildWithScope { irAccessor ->
            irAccessor.createParameterDeclarations(receiver = null)
            irAccessor.returnType = irAccessor.descriptor.returnType!!.toIrType()
            irAccessor.body = when (descriptor) {
                is PropertyGetterDescriptor -> definePropertyGetterBody(descriptor.correspondingProperty, descriptor, irAccessor)
                is PropertySetterDescriptor -> definePropertySetterBody(descriptor.correspondingProperty, descriptor, irAccessor)
                else -> throw AssertionError("Should be getter or setter: $descriptor")
            }
        }
    }

    open fun definePropertyGetterBody(
        property: PropertyDescriptor,
        getter: PropertyGetterDescriptor,
        irGetter: IrSimpleFunction
    ): IrBlockBody {
        val startOffset = irGetter.startOffset
        val endOffset = irGetter.endOffset
        val irBody = IrBlockBodyImpl(startOffset, endOffset)

        val receiver = generateReceiverExpressionForFieldAccess(irGetter.dispatchReceiverParameter!!.symbol, property)

        irBody.statements.add(
            IrReturnImpl(
                startOffset, endOffset, compilerContext.irBuiltIns.nothingType,
                irGetter.symbol,
                IrGetFieldImpl(
                    startOffset, endOffset,
                    compilerContext.symbolTable.referenceField(property),
                    property.type.toIrType(),
                    receiver
                )
            )
        )
        return irBody
    }

    open fun definePropertySetterBody(
        property: PropertyDescriptor,
        setter: PropertySetterDescriptor,
        irAccessor: IrSimpleFunction
    ): IrBlockBody {
        val startOffset = irAccessor.startOffset
        val endOffset = irAccessor.endOffset
        val irBody = IrBlockBodyImpl(startOffset, endOffset)

        val receiver = generateReceiverExpressionForFieldAccess(irAccessor.dispatchReceiverParameter!!.symbol, property)

        val irValueParameter = irAccessor.valueParameters.single()
        irBody.statements.add(
            IrSetFieldImpl(
                startOffset, endOffset,
                compilerContext.symbolTable.referenceField(property),
                receiver,
                IrGetValueImpl(startOffset, endOffset, irValueParameter.type, irValueParameter.symbol),
                compilerContext.irBuiltIns.unitType
            )
        )
        return irBody
    }
}

// todo: Split with generator helpers
// merge with other block body builders
class ExtendedBodyBuilder(val compilerContext: IrPluginContext, val scopeSymbol: IrSymbol) : IrBlockBodyBuilder(
    compilerContext, Scope(scopeSymbol),
    scopeSymbol.owner.startOffset,
    scopeSymbol.owner.endOffset
) {
    val irThis: IrExpression
        get() = when (val ctx = scopeSymbol.owner) {
            is IrSimpleFunction -> irGet(ctx.dispatchReceiverParameter!!)
            is IrClass -> irGet(ctx.thisReceiver!!)
            else -> error("No this in ${ctx::class}")
        }

    fun IrExpression.getProperty(property: IrProperty): IrExpression {
        return if (property.getter != null) irGet(property.getter!!.returnType, this, property.getter!!.symbol)
        else irGetField(this, property.backingField!!)
    }

    fun KotlinType.toIrType() = compilerContext.typeTranslator.translateType(this)

    fun IrExpression.invoke(
        callee: IrFunctionSymbol,
        vararg args: IrExpression,
        typeHint: IrType = callee.descriptor.returnType!!.toIrType()
    ): IrExpression {
        val call = irCall(callee, type = typeHint)
        call.dispatchReceiver = this
        args.forEachIndexed(call::putValueArgument)
        return call
    }

    fun invoke(
        callee: IrFunctionSymbol,
        vararg args: IrExpression,
        typeHint: IrType = callee.descriptor.returnType!!.toIrType()
    ): IrExpression {
        val call = irCall(callee, type = typeHint)
        call.dispatchReceiver = null
        args.forEachIndexed(call::putValueArgument)
        return call
    }
}

fun IrPluginContext.searchClass(packageName: String, className: String): ClassDescriptor {
    return requireNotNull(
        moduleDescriptor.findClassAcrossModuleDependencies(
            ClassId(
                FqName(packageName),
                Name.identifier(className)
            )
        )
    ) { "Can't locate class $className" }
}
