/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.declarations.impl

import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor
import org.jetbrains.kotlin.descriptors.Visibility
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.symbols.IrTypeAliasSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.transformIfNeeded
import org.jetbrains.kotlin.ir.visitors.IrElementTransformer
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor
import org.jetbrains.kotlin.name.Name

class IrTypeAliasImpl(
    override val startOffset: Int,
    override val endOffset: Int,
    override val symbol: IrTypeAliasSymbol,
    override val name: Name,
    override val visibility: Visibility,
    override val expandedType: IrType,
    override val isActual: Boolean,
    override var origin: IrDeclarationOrigin
) : IrTypeAlias() {
    init {
        symbol.bind(this)
    }

    @ObsoleteDescriptorBasedAPI
    override val descriptor: TypeAliasDescriptor
        get() = symbol.descriptor

    override var typeParameters: List<IrTypeParameter> = emptyList()

    private var _parent: IrDeclarationParent? = null
    override var parent: IrDeclarationParent
        get() = _parent
            ?: throw UninitializedPropertyAccessException("Parent not initialized: $this")
        set(v) {
            _parent = v
        }

    override var annotations: List<IrConstructorCall> = emptyList()

    override val metadata: MetadataSource?
        get() = null

    override fun <R, D> accept(visitor: IrElementVisitor<R, D>, data: D): R =
        visitor.visitTypeAlias(this, data)

    override fun <D> acceptChildren(visitor: IrElementVisitor<Unit, D>, data: D) {
        typeParameters.forEach { it.accept(visitor, data) }
    }

    override fun <D> transformChildren(transformer: IrElementTransformer<D>, data: D) {
        typeParameters = typeParameters.transformIfNeeded(transformer, data)
    }

    companion object {
        fun fromSymbolDescriptor(
            startOffset: Int,
            endOffset: Int,
            symbol: IrTypeAliasSymbol,
            expandedType: IrType,
            origin: IrDeclarationOrigin,
            descriptor: TypeAliasDescriptor
        ) =
            IrTypeAliasImpl(
                startOffset, endOffset,
                symbol,
                descriptor.name,
                descriptor.visibility,
                expandedType,
                descriptor.isActual,
                origin
            )
    }
}
