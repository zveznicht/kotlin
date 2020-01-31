/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.stm.compiler.extensions

import org.jetbrains.kotlin.backend.common.BackendContext
import org.jetbrains.kotlin.backend.common.ClassLoweringPass
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.runOnFilePostfix
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.ir.visitors.acceptVoid
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlinx.stm.compiler.backend.ir.StmIrGenerator

/**
 * Copy of [runOnFilePostfix], but this implementation first lowers declaration, then its children.
 */
fun ClassLoweringPass.runOnFileInOrder(irFile: IrFile) {
    irFile.acceptVoid(object : IrElementVisitorVoid {
        override fun visitElement(element: IrElement) {
            element.acceptChildrenVoid(this)
        }

        override fun visitClass(declaration: IrClass) {
            lower(declaration)
            declaration.acceptChildrenVoid(this)
        }
    })
}

private class StmClassLowering(
    val context: BackendContext,
    val bindingContext: BindingContext
) :
    IrElementTransformerVoid(), ClassLoweringPass {
    override fun lower(irClass: IrClass) {
        StmIrGenerator.generate(irClass, context, bindingContext)
    }
}

open class StmLoweringExtension : IrGenerationExtension {
    override fun generate(
        file: IrFile,
        backendContext: BackendContext,
        bindingContext: BindingContext
    ) {
        StmClassLowering(backendContext, bindingContext).runOnFileInOrder(file)
    }
}