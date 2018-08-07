/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */
// TODO:
// - avoid descriptors where possible
// - determine accessor names
// - How do I specify type arguments in accessor target calls?

package org.jetbrains.kotlin.backend.jvm.lower

import org.jetbrains.kotlin.backend.common.FileLoweringPass
import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.jvm.JvmBackendContext
import org.jetbrains.kotlin.backend.jvm.JvmLoweredDeclarationOrigin
import org.jetbrains.kotlin.backend.jvm.intrinsics.receiverAndArgs
import org.jetbrains.kotlin.codegen.OwnerKind
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.descriptors.impl.ClassConstructorDescriptorImpl
import org.jetbrains.kotlin.descriptors.impl.SimpleFunctionDescriptorImpl
import org.jetbrains.kotlin.descriptors.impl.ValueParameterDescriptorImpl
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.declarations.impl.IrConstructorImpl
import org.jetbrains.kotlin.ir.declarations.impl.IrFunctionImpl
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.expressions.impl.*
import org.jetbrains.kotlin.ir.symbols.*
import org.jetbrains.kotlin.ir.symbols.impl.IrConstructorSymbolImpl
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImpl
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.ir.visitors.acceptVoid
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid
import org.jetbrains.kotlin.load.java.JvmAbi
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.DescriptorUtils
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

class AccessorLowering(val context: JvmBackendContext) : FileLoweringPass {

    override fun lower(irFile: IrFile) {
        // Temporary. Deal with unbound symbols in input.
        val declarationSymbolCollector = DeclarationSymbolCollector()
        irFile.acceptVoid(declarationSymbolCollector)

        val accessorCallsLowering = AccessorCallsLowering(context, declarationSymbolCollector.descriptorToSymbol)
        accessorCallsLowering.lower(irFile)

        // `handledSymbols` are needed because the same accessor may arise both from a field access and a getter/setter call.
        val handledSymbols = mutableSetOf<IrFunctionSymbol>()
        createAccessors(accessorCallsLowering.functionMap, ::createAccessorForFunction, handledSymbols)
        createAccessors(accessorCallsLowering.getterMap, ::createAccessorForGetter, handledSymbols)
        createAccessors(accessorCallsLowering.setterMap, ::createAccessorForSetter, handledSymbols)
    }


    inline fun <FromSyT : IrSymbol, ToSyT : IrFunctionSymbol> createAccessors(
        symbolMap: Map<FromSyT, ToSyT>,
        crossinline generator: (FromSyT, ToSyT) -> IrDeclaration,
        handledSymbols: MutableSet<IrFunctionSymbol>
    ) {
        symbolMap.forEach { targetSymbol, accessorSymbol ->
            if (!handledSymbols.contains(accessorSymbol)) {
                val accessor = generator(targetSymbol, accessorSymbol)
                (accessor.parent as IrDeclarationContainer).declarations.add(accessor)
                handledSymbols.add(accessorSymbol)
            }
        }
    }

    private fun createAccessorForFunction(targetSymbol: IrFunctionSymbol, accessorSymbol: IrFunctionSymbol): IrFunction =
        if (accessorSymbol is IrConstructorSymbol) {
            createAccessorForConstructor(targetSymbol as IrConstructorSymbol, accessorSymbol)
        } else {
            createAccessorForSimpleFunction(targetSymbol as IrSimpleFunctionSymbol, accessorSymbol as IrSimpleFunctionSymbol)
        }

    private fun createAccessorForConstructor(targetSymbol: IrConstructorSymbol, accessorSymbol: IrConstructorSymbol): IrConstructor {
        val accessor = IrConstructorImpl(
            UNDEFINED_OFFSET, UNDEFINED_OFFSET,
            JvmLoweredDeclarationOrigin.SYNTHETIC_ACCESSOR,
            accessorSymbol
        )
        accessor.parent = targetSymbol.owner.parent
        accessor.returnType = targetSymbol.owner.returnType
        accessor.createParameterDeclarations()
        accessor.body = IrExpressionBodyImpl(
            UNDEFINED_OFFSET, UNDEFINED_OFFSET,
            createConstructorCall(accessor, targetSymbol)
        )

        return accessor
    }

    private fun createConstructorCall(accessor: IrConstructor, targetSymbol: IrConstructorSymbol) =
        IrDelegatingConstructorCallImpl(
            UNDEFINED_OFFSET, UNDEFINED_OFFSET,
            context.irBuiltIns.unitType,
            targetSymbol, targetSymbol.descriptor, targetSymbol.owner.typeParameters.size
        ).also {
            copyAllParamsToArgs(it, accessor)
        }

    private fun createAccessorForSimpleFunction(
        targetSymbol: IrSimpleFunctionSymbol,
        accessorSymbol: IrSimpleFunctionSymbol
    ): IrSimpleFunction {
        val accessor = IrFunctionImpl(
            UNDEFINED_OFFSET, UNDEFINED_OFFSET,
            JvmLoweredDeclarationOrigin.SYNTHETIC_ACCESSOR,
            accessorSymbol
        )
        accessor.parent = targetSymbol.owner.parent
        accessor.returnType = targetSymbol.owner.returnType
        accessor.createParameterDeclarations()
        accessor.body = IrExpressionBodyImpl(
            UNDEFINED_OFFSET, UNDEFINED_OFFSET,
            createSimpleFunctionCall(accessor, targetSymbol)
        )
        return accessor
    }

    private fun createSimpleFunctionCall(accessor: IrFunction, targetSymbol: IrFunctionSymbol) =
        IrCallImpl(
            UNDEFINED_OFFSET, UNDEFINED_OFFSET,
            accessor.returnType,
            targetSymbol, targetSymbol.descriptor,
            targetSymbol.owner.typeParameters.size
        ).also {
            copyAllParamsToArgs(it, accessor)
        }

    private fun copyAllParamsToArgs(
        call: IrMemberAccessExpression,
        syntheticFunction: IrFunction
    ) {
        var offset = 0
        val delegateTo = call.descriptor
        syntheticFunction.typeParameters.forEachIndexed { i, typeParam ->
            call.putTypeArgument(i, IrSimpleTypeImpl(typeParam.symbol, false, emptyList(), emptyList()))
        }
        delegateTo.dispatchReceiverParameter?.let {
            call.dispatchReceiver =
                    IrGetValueImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, syntheticFunction.valueParameters[offset++].symbol)
        }

        delegateTo.extensionReceiverParameter?.let {
            call.extensionReceiver =
                    IrGetValueImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, syntheticFunction.valueParameters[offset++].symbol)
        }

        call.descriptor.valueParameters.forEachIndexed { i, param ->
            call.putValueArgument(
                i,
                IrGetValueImpl(
                    UNDEFINED_OFFSET,
                    UNDEFINED_OFFSET,
                    syntheticFunction.valueParameters[i + offset].symbol
                )
            )
        }
    }

    private fun createAccessorForGetter(targetFieldSymbol: IrFieldSymbol, accessorSymbol: IrSimpleFunctionSymbol): IrSimpleFunction {
        val type = targetFieldSymbol.owner.type
        val accessor = IrFunctionImpl(
            UNDEFINED_OFFSET, UNDEFINED_OFFSET,
            JvmLoweredDeclarationOrigin.SYNTHETIC_ACCESSOR,
            accessorSymbol
        )
        accessor.parent = targetFieldSymbol.owner.parent
        accessor.returnType = type
        accessor.createParameterDeclarations()
        val maybeDispatchReceiver = if (targetFieldSymbol.descriptor.dispatchReceiverParameter != null)
            IrGetValueImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, accessor.valueParameters[0].symbol) else null
        accessor.body = IrExpressionBodyImpl(
            UNDEFINED_OFFSET, UNDEFINED_OFFSET,
            IrGetFieldImpl(
                UNDEFINED_OFFSET, UNDEFINED_OFFSET,
                targetFieldSymbol,
                type,
                maybeDispatchReceiver
            )
        )
        return accessor
    }

    private fun createAccessorForSetter(targetFieldSymbol: IrFieldSymbol, accessorSymbol: IrSimpleFunctionSymbol): IrSimpleFunction {
        val type = targetFieldSymbol.owner.type
        val accessor = IrFunctionImpl(
            UNDEFINED_OFFSET, UNDEFINED_OFFSET,
            JvmLoweredDeclarationOrigin.SYNTHETIC_ACCESSOR,
            accessorSymbol
        )
        accessor.parent = targetFieldSymbol.owner.parent
        accessor.returnType = context.irBuiltIns.unitType
        accessor.createParameterDeclarations()
        val maybeDispatchReceiver = if (targetFieldSymbol.descriptor.dispatchReceiverParameter != null)
            IrGetValueImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, accessor.valueParameters[0].symbol) else null
        val value = IrGetValueImpl(
            UNDEFINED_OFFSET, UNDEFINED_OFFSET,
            accessor.valueParameters[if (targetFieldSymbol.descriptor.dispatchReceiverParameter != null) 1 else 0].symbol
        )
        accessor.body = IrExpressionBodyImpl(
            UNDEFINED_OFFSET, UNDEFINED_OFFSET,
            IrSetFieldImpl(
                UNDEFINED_OFFSET, UNDEFINED_OFFSET,
                targetFieldSymbol,
                maybeDispatchReceiver,
                value,
                type
            )
        )
        return accessor
    }
}

private class AccessorCallsLowering(
    val context: JvmBackendContext,
    val descriptorToBoundSymbol: Map<DeclarationDescriptor, IrSymbol>
) : FileLoweringPass, IrElementTransformerVoidWithContext() {
    private inline fun <reified SyT : IrSymbol> SyT.fixUp() =
        if (isBound) this else descriptorToBoundSymbol[descriptor]!!.safeAs<SyT>()!!

    val functionMap = mutableMapOf<IrFunctionSymbol, IrFunctionSymbol>()
    val getterMap = mutableMapOf<IrFieldSymbol, IrSimpleFunctionSymbol>()
    val setterMap = mutableMapOf<IrFieldSymbol, IrSimpleFunctionSymbol>()

    override fun lower(irFile: IrFile) {
        irFile.transformChildrenVoid(this)
    }

    override fun visitFunctionAccess(expression: IrFunctionAccessExpression): IrExpression {
        if (expression.usesDefaultArguments()) {
            return super.visitFunctionAccess(expression)
        }
        return super.visitExpression(
            handleAccess(expression, expression.symbol, functionMap, ::makeFunctionAccessorSymbol, ::modifyFunctionAccessExpression)
        )
    }

    override fun visitGetField(expression: IrGetField) = super.visitExpression(
        handleAccess(expression, expression.symbol, getterMap, ::makeGetterAccessorSymbol, ::modifyGetterExpression)
    )

    override fun visitSetField(expression: IrSetField) = super.visitExpression(
        handleAccess(expression, expression.symbol, setterMap, ::makeSetterAccessorSymbol, ::modifySetterExpression)
    )

    private inline fun <ExprT : IrDeclarationReference, reified FromSyT : IrSymbol, ToSyT : IrSymbol> handleAccess(
        expression: ExprT,
        symbol: FromSyT,
        accumMap: MutableMap<FromSyT, ToSyT>,
        symbolConverter: (FromSyT) -> ToSyT,
        exprConverter: (ExprT, ToSyT) -> IrDeclarationReference
    ): IrExpression {
        if (!symbol.isAccessible()) {
            val accessorSymbol = accumMap.getOrPut(symbol.fixUp(), { symbolConverter(symbol) })
            return exprConverter(expression, accessorSymbol)
        } else {
            return expression
        }
    }

    private fun makeFunctionAccessorSymbol(functionSymbol: IrFunctionSymbol): IrFunctionSymbol = when (functionSymbol) {
        is IrConstructorSymbol -> {
            IrConstructorSymbolImpl(functionSymbol.descriptor.makeConstructorAccessorDescriptor())
        }
        else -> {
            IrSimpleFunctionSymbolImpl(
                functionSymbol.descriptor.toStatic(
                    functionSymbol.descriptor.containingDeclaration as ClassOrPackageFragmentDescriptor,
                    functionSymbol.descriptor.accessorName()
                )
            )
        }
    }

    private fun makeGetterAccessorSymbol(fieldSymbol: IrFieldSymbol) = IrSimpleFunctionSymbolImpl(
        SimpleFunctionDescriptorImpl.create(
            fieldSymbol.descriptor.containingDeclaration,
            Annotations.EMPTY,
            fieldSymbol.descriptor.accessorNameForGetter(),
            CallableMemberDescriptor.Kind.SYNTHESIZED,
            fieldSymbol.descriptor.source
        ).also { getterDescriptor ->
            getterDescriptor.initialize(
                null,
                null,
                emptyList(), // fieldSymbol.descriptor.typeParameters
                listOfNotNull<ValueParameterDescriptor>(
                    fieldSymbol.descriptor.dispatchReceiverParameter?.toValueReceiverParameter(getterDescriptor, 0, Name.identifier("this"))
                ).toMutableList(),
                fieldSymbol.descriptor.type,
                Modality.FINAL,
                Visibilities.INTERNAL
            )
        }
    )

    private fun makeSetterAccessorSymbol(fieldSymbol: IrFieldSymbol) = IrSimpleFunctionSymbolImpl(
        SimpleFunctionDescriptorImpl.create(
            fieldSymbol.descriptor.containingDeclaration,
            Annotations.EMPTY,
            fieldSymbol.descriptor.accessorNameForSetter(),
            CallableMemberDescriptor.Kind.SYNTHESIZED,
            fieldSymbol.descriptor.source
        ).also { setterDescriptor ->
            val valueParametersList = mutableListOf<ValueParameterDescriptor>()
            fieldSymbol.descriptor.dispatchReceiverParameter?.let {
                valueParametersList.add(it.toValueReceiverParameter(setterDescriptor, 0, Name.identifier("this")))
            }
            valueParametersList.add(
                ValueParameterDescriptorImpl(
                    setterDescriptor,
                    null,
                    valueParametersList.size,
                    Annotations.EMPTY,
                    Name.identifier("value"),
                    fieldSymbol.descriptor.type,
                    false,
                    false,
                    false,
                    null,
                    SourceElement.NO_SOURCE
                )
            )
            setterDescriptor.initialize(
                null,
                null,
                emptyList(), // fieldSymbol.descriptor.typeParameters
                valueParametersList,
                context.builtIns.unitType,
                Modality.FINAL,
                Visibilities.INTERNAL
            )
        }
    )

    private fun modifyFunctionAccessExpression(
        oldExpression: IrFunctionAccessExpression,
        accessorSymbol: IrFunctionSymbol
    ): IrFunctionAccessExpression {
        val newExpression = when (oldExpression) {
            is IrCall -> IrCallImpl(
                oldExpression.startOffset, oldExpression.endOffset,
                oldExpression.type,
                accessorSymbol, accessorSymbol.descriptor,
                oldExpression.typeArgumentsCount,
                oldExpression.origin,
                oldExpression.superQualifierSymbol
            )
            is IrDelegatingConstructorCall -> IrDelegatingConstructorCallImpl(
                oldExpression.startOffset, oldExpression.endOffset,
                context.irBuiltIns.unitType,
                accessorSymbol as IrConstructorSymbol, accessorSymbol.descriptor,
                oldExpression.typeArgumentsCount
            )
            else -> error("Need IrCall or IrDelegatingConstructor call, got $oldExpression")
        }
        newExpression.copyTypeArgumentsFrom(oldExpression)
        val receiverAndArgs = oldExpression.receiverAndArgs()
        receiverAndArgs.forEachIndexed { i, irExpression ->
            newExpression.putValueArgument(i, irExpression)
        }
        if (accessorSymbol is IrConstructorSymbol) {
            newExpression.putValueArgument(
                receiverAndArgs.size,
                IrConstImpl.constNull(
                    UNDEFINED_OFFSET,
                    UNDEFINED_OFFSET,
                    context.ir.symbols.defaultConstructorMarker.owner.defaultType
                )
            )
        }
        return newExpression
    }

    private fun modifyGetterExpression(
        oldExpression: IrGetField,
        accessorSymbol: IrFunctionSymbol
    ): IrCall {
        val call = IrCallImpl(
            oldExpression.startOffset, oldExpression.endOffset,
            oldExpression.type,
            accessorSymbol, accessorSymbol.descriptor,
            0,
            oldExpression.origin
        )
        oldExpression.receiver?.let {
            call.putValueArgument(0, oldExpression.receiver)
        }
        return call
    }

    private fun modifySetterExpression(
        oldExpression: IrSetField,
        accessorSymbol: IrFunctionSymbol
    ): IrCall {
        val call = IrCallImpl(
            oldExpression.startOffset, oldExpression.endOffset,
            oldExpression.type,
            accessorSymbol, accessorSymbol.descriptor,
            0,
            oldExpression.origin
        )
        oldExpression.receiver?.let {
            call.putValueArgument(0, oldExpression.receiver)
        }
        call.putValueArgument(call.valueArgumentsCount, oldExpression.value)
        return call
    }

    private fun ConstructorDescriptor.makeConstructorAccessorDescriptor() =
        ClassConstructorDescriptorImpl.createSynthesized(
            containingDeclaration as ClassDescriptor,
            annotations,
            /* isPrimary = */ false,
            source
        ).also { newDescriptor ->
            var offset = 0
            val receivers = mutableListOf<ValueParameterDescriptor>()
            extensionReceiverParameter?.apply {
                receivers.add(toValueReceiverParameter(newDescriptor, offset++, Name.identifier("receiver")))
            }
            dispatchReceiverParameter?.apply {
                receivers.add(toValueReceiverParameter(newDescriptor, offset++, Name.identifier("this")))
            }
            newDescriptor.initialize(
                null,
                null,
                emptyList(), // typeParameters
                receivers + valueParameters.map {
                    it.copy(
                        newDescriptor,
                        it.name,
                        it.index + offset
                    )
                } + ValueParameterDescriptorImpl.createWithDestructuringDeclarations(
                    newDescriptor,
                    null,
                    valueParameters.size,
                    Annotations.EMPTY,
                    Name.identifier("marker"),
                    context.ir.symbols.defaultConstructorMarker.descriptor.defaultType,
                    false,
                    false,
                    false,
                    null,
                    SourceElement.NO_SOURCE,
                    null
                ),
                returnType,
                Modality.FINAL,
                Visibilities.LOCAL
            )
        }

    // !!!!!! Should I use syntheticAccesssorUtils here ???
    private fun FunctionDescriptor.accessorName(): Name {
        val jvmName = DescriptorUtils.getJvmName(this) ?: context.state.typeMapper.mapFunctionName(this, OwnerKind.getMemberOwnerKind(containingDeclaration))
        return Name.identifier("access\$$jvmName")
    }

    private fun PropertyDescriptor.accessorNameForGetter(): Name {
        val getterName = JvmAbi.getterName(name.asString())
        return Name.identifier("access\$$getterName")
    }

    private fun PropertyDescriptor.accessorNameForSetter(): Name {
        val setterName = JvmAbi.setterName(name.asString())
        return Name.identifier("access\$$setterName")
    }

    private fun IrSymbol.isAccessible(): Boolean {
        /// We assume that IR code that reaches us has been checked for correctness at the frontend.
        /// This function needs to single out those cases where Java accessibility rules differ from Kotlin's.
        /// TODO: a very primitive preliminary code.

        val declarationRaw = fixUp().owner as IrDeclarationWithVisibility
        val declaration = (declarationRaw as? IrSimpleFunction)?.resolveFakeOverride() ?: declarationRaw
        if (declaration.visibility == Visibilities.PUBLIC) return true

        val symbolDeclarationContainer = declaration.parent as? IrDeclarationContainer
        // If local variables are accessible Kotlin rules, they also are by Java rules.
        if (symbolDeclarationContainer == null) return true

        val contextDeclarationContainer = allScopes.lastOrNull { it.irElement is IrDeclarationContainer }?.irElement

        val samePackage = declaration.getPackageFragment()?.fqName == contextDeclarationContainer?.getPackageFragment()?.fqName
        return when {
            declaration.visibility == Visibilities.PRIVATE && symbolDeclarationContainer != contextDeclarationContainer -> false
            (declaration.visibility == Visibilities.PROTECTED && !samePackage &&
                    !(symbolDeclarationContainer is IrClass && contextDeclarationContainer is IrClass &&
                            contextDeclarationContainer.isSubclassOf(symbolDeclarationContainer))) -> false
            else -> true
        }
    }
}

private fun ReceiverParameterDescriptor.toValueReceiverParameter(functionDescriptor: FunctionDescriptor, index: Int, name: Name) =
    ValueParameterDescriptorImpl(
        functionDescriptor,
        null,
        index,
        Annotations.EMPTY,
        name,
        type,
        /* declaresDefaultvalue = */false,
        /* isCrossinline = */ false,
        /* isNoinline = */ false,
        /* varargElementType */ null,
        source
    )

/*
    Temporary measure for dealing with unbound symbols in input irFile.
    Copy/pasted from org.jetbrains.kotlin.ir.backend.js.lower.inline.IrUnboundSymbolReplacer.
 */
private class DeclarationSymbolCollector : IrElementVisitorVoid {

    val descriptorToSymbol = mutableMapOf<DeclarationDescriptor, IrSymbol>()

    fun register(symbol: IrSymbol) {
        descriptorToSymbol[symbol.descriptor] = symbol
    }

    override fun visitElement(element: IrElement) {
        element.acceptChildrenVoid(this)

        if (element is IrSymbolOwner && element !is IrAnonymousInitializer) {
            register(element.symbol)
        }
    }
}