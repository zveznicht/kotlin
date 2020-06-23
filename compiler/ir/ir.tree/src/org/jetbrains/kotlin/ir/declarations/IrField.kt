/*
 * Copyright 2000-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.declarations

import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol
import org.jetbrains.kotlin.ir.types.IrType

abstract class IrField :
    IrPureDeclaration(), IrSymbolDeclaration<IrFieldSymbol>,
    IrDeclarationWithName, IrDeclarationWithVisibility, IrDeclarationParent {

    @ObsoleteDescriptorBasedAPI
    abstract override val descriptor: PropertyDescriptor

    abstract val type: IrType
    abstract val isFinal: Boolean
    abstract val isExternal: Boolean
    abstract val isStatic: Boolean

    abstract var initializer: IrExpressionBody?

    abstract var correspondingPropertySymbol: IrPropertySymbol?

    abstract override val metadata: MetadataSource?
}
