/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.declarations

import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor
import org.jetbrains.kotlin.ir.IrElementBase
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.SourceManager
import org.jetbrains.kotlin.ir.symbols.IrExternalPackageFragmentSymbol
import org.jetbrains.kotlin.ir.symbols.IrFileSymbol
import org.jetbrains.kotlin.ir.symbols.IrPackageFragmentSymbol
import org.jetbrains.kotlin.ir.visitors.IrElementTransformer
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
import java.io.File

abstract class IrPackageFragment : IrElementBase(), IrDeclarationContainer, IrSymbolOwner {
    @ObsoleteDescriptorBasedAPI
    abstract val packageFragmentDescriptor: PackageFragmentDescriptor
    abstract override val symbol: IrPackageFragmentSymbol

    abstract val fqName: FqName
}

abstract class IrExternalPackageFragment : IrPackageFragment() {
    abstract override val symbol: IrExternalPackageFragmentSymbol
    abstract val containerSource: DeserializedContainerSource?
}

abstract class IrFile : IrPackageFragment(), IrMutableAnnotationContainer, IrMetadataSourceOwner {
    abstract override val symbol: IrFileSymbol

    abstract val fileEntry: SourceManager.FileEntry

    override fun <D> transform(transformer: IrElementTransformer<D>, data: D): IrFile =
        accept(transformer, data) as IrFile
}

val IrFile.path: String get() = fileEntry.name
val IrFile.name: String get() = File(path).name
