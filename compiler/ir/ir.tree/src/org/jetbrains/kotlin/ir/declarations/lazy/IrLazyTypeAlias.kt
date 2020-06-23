/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.declarations.lazy

import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor
import org.jetbrains.kotlin.descriptors.Visibility
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.symbols.IrTypeAliasSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.DeclarationStubGenerator
import org.jetbrains.kotlin.ir.util.TypeTranslator
import org.jetbrains.kotlin.ir.util.transformIfNeeded
import org.jetbrains.kotlin.ir.visitors.IrElementTransformer
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor
import org.jetbrains.kotlin.name.Name

@OptIn(ObsoleteDescriptorBasedAPI::class)
class IrLazyTypeAlias(
    override val startOffset: Int,
    override val endOffset: Int,
    override var origin: IrDeclarationOrigin,
    override val symbol: IrTypeAliasSymbol,
    override val descriptor: TypeAliasDescriptor,
    override val name: Name,
    override val visibility: Visibility,
    override val isActual: Boolean,
    override val stubGenerator: DeclarationStubGenerator,
    override val typeTranslator: TypeTranslator
) : IrLazyDeclarationBase, IrTypeAlias() {
    init {
        symbol.bind(this)
    }

    override var typeParameters: List<IrTypeParameter> by lazyVar {
        descriptor.declaredTypeParameters.mapTo(arrayListOf()) {
            stubGenerator.generateOrGetTypeParameterStub(it)
        }
    }

    override val expandedType: IrType by lazy {
        withInitialIr {
            typeTranslator.buildWithScope(this) {
                descriptor.expandedType.toIrType()
            }
        }
    }

    override var parent: IrDeclarationParent by createLazyParent()

    override var annotations: List<IrConstructorCall> by createLazyAnnotations()

    override fun <R, D> accept(visitor: IrElementVisitor<R, D>, data: D): R =
        visitor.visitTypeAlias(this, data)

    override fun <D> acceptChildren(visitor: IrElementVisitor<Unit, D>, data: D) {
        typeParameters.forEach { it.accept(visitor, data) }
    }

    override fun <D> transformChildren(transformer: IrElementTransformer<D>, data: D) {
        typeParameters = typeParameters.transformIfNeeded(transformer, data)
    }
}
