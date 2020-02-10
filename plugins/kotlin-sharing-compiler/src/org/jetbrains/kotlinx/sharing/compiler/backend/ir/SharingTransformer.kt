/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing.compiler.backend.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.descriptorUtil.builtIns
import org.jetbrains.kotlinx.sharing.compiler.extensions.IrTransformer
import org.jetbrains.kotlinx.sharing.compiler.extensions.PluginDeclarationsCreator

class SharingDeclarations : PluginDeclarationsCreator {
    override fun createPropertyForFrontend(owner: ClassDescriptor, context: BindingContext): List<PropertyDescriptor> {
        return listOf(SimpleSyntheticPropertyDescriptor(owner, "isReadOnly", owner.builtIns.booleanType, isVar = true))
    }
}

class SharingTransformer(override val compilerContext: IrPluginContext) : IrTransformer() {
    override fun lower(irClass: IrClass) {
//        createPropertyForBackend()
//        irClass.descriptor
    }
}