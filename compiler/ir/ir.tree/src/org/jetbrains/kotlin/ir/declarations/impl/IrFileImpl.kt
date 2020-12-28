/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.declarations.impl

import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.SourceManager
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.MetadataSource
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.symbols.IrFileSymbol
import org.jetbrains.kotlin.ir.symbols.impl.IrFileSymbolImpl
import org.jetbrains.kotlin.ir.visitors.IrElementTransformer
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor
import org.jetbrains.kotlin.name.FqName

class IrFileImpl(
    override val fileEntry: SourceManager.FileEntry,
    override val symbol: IrFileSymbol,
    override val fqName: FqName
) : IrFile() {
    // TODO remove
    constructor(
        fileEntry: SourceManager.FileEntry,
        packageFragmentDescriptor: PackageFragmentDescriptor
    ) : this(fileEntry, IrFileSymbolImpl(), packageFragmentDescriptor.fqName)

    init {
        symbol.bind(this)
    }

    override val startOffset: Int
        get() = 0

    override val endOffset: Int
        get() = fileEntry.maxOffset

    @ObsoleteDescriptorBasedAPI
    override val packageFragmentDescriptor: PackageFragmentDescriptor
        get() = symbol.descriptor

    override val declarations: MutableList<IrDeclaration> = ArrayList()

    override var annotations: List<IrConstructorCall> = emptyList()

    override var metadata: MetadataSource? = null

    override fun <R, D> accept(visitor: IrElementVisitor<R, D>, data: D): R =
        visitor.visitFile(this, data)

    override fun <D> acceptChildren(visitor: IrElementVisitor<Unit, D>, data: D) {
        declarations.forEach { it.accept(visitor, data) }
    }

    override fun <D> transformChildren(transformer: IrElementTransformer<D>, data: D) {
        declarations.forEachIndexed { i, irDeclaration ->
            declarations[i] = irDeclaration.transform(transformer, data) as IrDeclaration
        }
    }
}
