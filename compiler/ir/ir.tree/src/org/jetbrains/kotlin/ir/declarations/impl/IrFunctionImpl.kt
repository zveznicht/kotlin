/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.declarations.impl

import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Visibility
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.descriptors.WrappedSimpleFunctionDescriptor
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.transformIfNeeded
import org.jetbrains.kotlin.ir.visitors.IrElementTransformer
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor
import org.jetbrains.kotlin.name.Name

class IrFunctionImpl(
    override val startOffset: Int,
    override val endOffset: Int,
    override var origin: IrDeclarationOrigin,
    override val symbol: IrSimpleFunctionSymbol,
    override val name: Name,
    override var visibility: Visibility,
    override val modality: Modality,
    returnType: IrType,
    override val isInline: Boolean,
    override val isExternal: Boolean,
    override val isTailrec: Boolean,
    override val isSuspend: Boolean,
    override val isOperator: Boolean,
    override val isExpect: Boolean,
    override val isFakeOverride: Boolean = origin == IrDeclarationOrigin.FAKE_OVERRIDE
) : IrSimpleFunction() {
    constructor(
        startOffset: Int,
        endOffset: Int,
        origin: IrDeclarationOrigin,
        symbol: IrSimpleFunctionSymbol,
        returnType: IrType,
        descriptor: FunctionDescriptor,
        name: Name = descriptor.name
    ) : this(
        startOffset, endOffset, origin, symbol,
        name = name,
        visibility = descriptor.visibility,
        modality = descriptor.modality,
        returnType = returnType,
        isInline = descriptor.isInline,
        isExternal = descriptor.isExternal,
        isTailrec = descriptor.isTailrec,
        isSuspend = descriptor.isSuspend,
        isOperator = descriptor.isOperator,
        isExpect = descriptor.isExpect
    )

    // Used by kotlin-native in InteropLowering.kt and IrUtils2.kt
    constructor(
        startOffset: Int,
        endOffset: Int,
        origin: IrDeclarationOrigin,
        descriptor: FunctionDescriptor,
        returnType: IrType
    ) : this(
        startOffset, endOffset, origin,
        IrSimpleFunctionSymbolImpl(descriptor), returnType, descriptor
    )

    @Suppress("DEPRECATION")
    override var returnType: IrType = returnType
        get() = if (field === org.jetbrains.kotlin.ir.types.impl.IrUninitializedType) {
            error("Return type is not initialized")
        } else {
            field
        }

    override var typeParameters: List<IrTypeParameter> = emptyList()

    override var dispatchReceiverParameter: IrValueParameter? = null
    override var extensionReceiverParameter: IrValueParameter? = null
    override var valueParameters: List<IrValueParameter> = emptyList()

    override var body: IrBody? = null

    override var metadata: MetadataSource? = null

    override var overriddenSymbols: List<IrSimpleFunctionSymbol> = emptyList()
    override var attributeOwnerId: IrAttributeContainer = this

    override var correspondingPropertySymbol: IrPropertySymbol? = null

    private var _parent: IrDeclarationParent? = null
    override var parent: IrDeclarationParent
        get() = _parent
            ?: throw UninitializedPropertyAccessException("Parent not initialized: $this")
        set(v) {
            _parent = v
        }

    override var annotations: List<IrConstructorCall> = emptyList()

    @ObsoleteDescriptorBasedAPI
    override val descriptor: FunctionDescriptor get() = symbol.descriptor

    init {
        symbol.bind(this)
    }

    override fun <R, D> accept(visitor: IrElementVisitor<R, D>, data: D): R =
        visitor.visitSimpleFunction(this, data)

    override fun <D> acceptChildren(visitor: IrElementVisitor<Unit, D>, data: D) {
        typeParameters.forEach { it.accept(visitor, data) }

        dispatchReceiverParameter?.accept(visitor, data)
        extensionReceiverParameter?.accept(visitor, data)
        valueParameters.forEach { it.accept(visitor, data) }

        body?.accept(visitor, data)
    }

    override fun <D> transformChildren(transformer: IrElementTransformer<D>, data: D) {
        typeParameters = typeParameters.transformIfNeeded(transformer, data)

        dispatchReceiverParameter = dispatchReceiverParameter?.transform(transformer, data)
        extensionReceiverParameter = extensionReceiverParameter?.transform(transformer, data)
        valueParameters = valueParameters.transformIfNeeded(transformer, data)

        body = body?.transform(transformer, data)
    }
}

class IrFakeOverrideFunctionImpl(
    override val startOffset: Int,
    override val endOffset: Int,
    override var origin: IrDeclarationOrigin,
    override val name: Name,
    override var visibility: Visibility,
    override var modality: Modality,
    returnType: IrType,
    override val isInline: Boolean,
    override val isExternal: Boolean,
    override val isTailrec: Boolean,
    override val isSuspend: Boolean,
    override val isOperator: Boolean,
    override val isExpect: Boolean
) : IrSimpleFunction() {
    private var _symbol: IrSimpleFunctionSymbol? = null

    override val symbol: IrSimpleFunctionSymbol
        get() = _symbol ?: error("$this has not acquired a symbol yet")

    @Suppress("DEPRECATION")
    override var returnType: IrType = returnType
        get() = if (field === org.jetbrains.kotlin.ir.types.impl.IrUninitializedType) {
            error("Return type is not initialized")
        } else {
            field
        }

    override var typeParameters: List<IrTypeParameter> = emptyList()

    override var dispatchReceiverParameter: IrValueParameter? = null
    override var extensionReceiverParameter: IrValueParameter? = null
    override var valueParameters: List<IrValueParameter> = emptyList()

    override var body: IrBody? = null

    override var metadata: MetadataSource? = null

    override var overriddenSymbols: List<IrSimpleFunctionSymbol> = emptyList()
    override var attributeOwnerId: IrAttributeContainer = this

    override var correspondingPropertySymbol: IrPropertySymbol? = null

    private var _parent: IrDeclarationParent? = null
    override var parent: IrDeclarationParent
        get() = _parent
            ?: throw UninitializedPropertyAccessException("Parent not initialized: $this")
        set(v) {
            _parent = v
        }

    override var annotations: List<IrConstructorCall> = emptyList()

    override val isFakeOverride: Boolean get() = true

    @ObsoleteDescriptorBasedAPI
    override val descriptor
        get() = _symbol?.descriptor ?: WrappedSimpleFunctionDescriptor()

    @OptIn(ObsoleteDescriptorBasedAPI::class)
    fun acquireSymbol(symbol: IrSimpleFunctionSymbol) {
        assert(_symbol == null) { "$this already has symbol _symbol" }
        _symbol = symbol
        symbol.bind(this)
        (symbol.descriptor as? WrappedSimpleFunctionDescriptor)?.bind(this)
    }

    override fun <R, D> accept(visitor: IrElementVisitor<R, D>, data: D): R =
        visitor.visitSimpleFunction(this, data)

    override fun <D> acceptChildren(visitor: IrElementVisitor<Unit, D>, data: D) {
        typeParameters.forEach { it.accept(visitor, data) }

        dispatchReceiverParameter?.accept(visitor, data)
        extensionReceiverParameter?.accept(visitor, data)
        valueParameters.forEach { it.accept(visitor, data) }

        body?.accept(visitor, data)
    }

    override fun <D> transformChildren(transformer: IrElementTransformer<D>, data: D) {
        typeParameters = typeParameters.transformIfNeeded(transformer, data)

        dispatchReceiverParameter = dispatchReceiverParameter?.transform(transformer, data)
        extensionReceiverParameter = extensionReceiverParameter?.transform(transformer, data)
        valueParameters = valueParameters.transformIfNeeded(transformer, data)

        body = body?.transform(transformer, data)
    }
}
