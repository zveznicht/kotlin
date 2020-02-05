/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing.compiler.backend.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrClass

class SharingTransformer(override val compilerContext: IrPluginContext): IrBuilderExtension {
    fun lower(irClass: IrClass) {
        println(irClass.name)
    }
}