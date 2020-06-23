/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.declarations

import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.symbols.IrTypeAliasSymbol
import org.jetbrains.kotlin.ir.types.IrType

abstract class IrTypeAlias :
    IrPureDeclaration(),
    IrSymbolDeclaration<IrTypeAliasSymbol>,
    IrDeclarationWithName,
    IrDeclarationWithVisibility,
    IrTypeParametersContainer {

    @ObsoleteDescriptorBasedAPI
    abstract override val descriptor: TypeAliasDescriptor

    abstract val isActual: Boolean
    abstract val expandedType: IrType
}
