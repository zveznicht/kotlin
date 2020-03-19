/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing.compiler.extensions

import com.intellij.mock.MockProject
import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension
import org.jetbrains.kotlinx.sharing.compiler.backend.ir.SharingDeclarations
import org.jetbrains.kotlinx.sharing.compiler.backend.ir.SharingTransformer
import org.jetbrains.kotlinx.sharing.compiler.pluginapi.CompilerPlugin
import org.jetbrains.kotlinx.sharing.compiler.pluginapi.IrTransformer
import org.jetbrains.kotlinx.sharing.compiler.pluginapi.PluginDeclarationsCreator

class SharingCompilerPlugin : CompilerPlugin() {
    override fun isApplied(toClass: ClassDescriptor): Boolean =
        toClass.annotations.hasAnnotation(FqName("kotlinx.sharing.Shared"))

    override fun createIrTransformer(context: IrPluginContext): IrTransformer = SharingTransformer(context)

    override val creator: PluginDeclarationsCreator = SharingDeclarations()
}

// todo: commonize this
class SharingPluginComponentRegistrar : ComponentRegistrar {
    override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
        registerExtensions(project)
    }

    companion object {
        fun registerExtensions(project: Project) {
            val plugin = SharingCompilerPlugin()
            SyntheticResolveExtension.registerExtension(project, plugin)
            IrGenerationExtension.registerExtension(project, plugin)
        }
    }
}