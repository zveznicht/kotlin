/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing.compiler.extensions

import com.intellij.mock.MockProject
import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.backend.common.ClassLoweringPass
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.runOnFilePostfix
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.ir.visitors.acceptVoid
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension
import org.jetbrains.kotlinx.sharing.compiler.backend.ir.SharingTransformer

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

private class SharingClassLowering(
    val context: IrPluginContext
) :
    IrElementTransformerVoid(), ClassLoweringPass {
    override fun lower(irClass: IrClass) {
        SharingTransformer(context).lower(irClass)
    }
}

class SharingLoweringExtension : IrGenerationExtension {
    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: IrPluginContext
    ) {
        val serializerClassLowering = SharingClassLowering(pluginContext)
        for (file in moduleFragment.files)
            serializerClassLowering.runOnFileInOrder(file)
    }
}


class SharingPluginComponentRegistrar : ComponentRegistrar {
    override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
        registerExtensions(project)
    }

    companion object {
        fun registerExtensions(project: Project) {
//            SyntheticResolveExtension.registerExtension(project, TODO())
            IrGenerationExtension.registerExtension(project, SharingLoweringExtension())
        }
    }
}