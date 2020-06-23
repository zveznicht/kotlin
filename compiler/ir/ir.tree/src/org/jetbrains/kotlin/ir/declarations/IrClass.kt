/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.ir.declarations

import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.types.IrType

abstract class IrClass :
    IrPureDeclaration(), IrSymbolDeclaration<IrClassSymbol>, IrDeclarationWithName, IrDeclarationWithVisibility,
    IrDeclarationContainer, IrTypeParametersContainer, IrAttributeContainer {

    @ObsoleteDescriptorBasedAPI
    abstract override val descriptor: ClassDescriptor

    abstract override var visibility: Visibility

    abstract val kind: ClassKind
    abstract var modality: Modality
    abstract val isCompanion: Boolean
    abstract val isInner: Boolean
    abstract val isData: Boolean
    abstract val isExternal: Boolean
    abstract val isInline: Boolean
    abstract val isExpect: Boolean
    abstract val isFun: Boolean

    abstract val source: SourceElement

    abstract var superTypes: List<IrType>

    abstract var thisReceiver: IrValueParameter?
}

fun IrClass.addMember(member: IrDeclaration) {
    declarations.add(member)
}

fun IrClass.addAll(members: List<IrDeclaration>) {
    declarations.addAll(members)
}

fun IrClass.getInstanceInitializerMembers() =
    declarations.filter {
        when (it) {
            is IrAnonymousInitializer ->
                true
            is IrProperty ->
                it.backingField?.initializer != null
            is IrField ->
                it.initializer != null
            else -> false
        }
    }
