/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.serialization.compiler.extensions

import com.intellij.mock.MockProject
import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.codegen.extensions.ExpressionCodegenExtension
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.container.StorageComponentContainer
import org.jetbrains.kotlin.container.useInstance
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor
import org.jetbrains.kotlin.js.translate.extensions.JsSyntheticTranslateExtension
import org.jetbrains.kotlin.library.metadata.KlibMetadataSerializerProtocol
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil
import org.jetbrains.kotlin.platform.TargetPlatform
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension
import org.jetbrains.kotlin.serialization.DescriptorSerializerPlugin
import org.jetbrains.kotlin.serialization.js.JsSerializerProtocol
import org.jetbrains.kotlinx.serialization.compiler.diagnostic.SerializationPluginDeclarationChecker

class SerializationComponentRegistrar : ComponentRegistrar {
    override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
        registerExtensions(project)
    }

    companion object {
        internal var serializationDescriptorSerializer: SerializationDescriptorSerializerPlugin? = null
            private set

        fun registerExtensions(project: Project) {
            SyntheticResolveExtension.registerExtension(project, SerializationResolveExtension())

            ExpressionCodegenExtension.registerExtension(project, SerializationCodegenExtension())
            JsSyntheticTranslateExtension.registerExtension(project, SerializationJsExtension())
            IrGenerationExtension.registerExtension(project, SerializationLoweringExtension())

            StorageComponentContainerContributor.registerExtension(project, SerializationPluginComponentContainerContributor())

            // This method is never called in the IDE, therefore this extension is not available there.
            // Since IDE does not perform any serialization of descriptors, metadata written to the 'serializationDescriptorSerializer'
            // is never deleted, effectively causing memory leaks.
            if (serializationDescriptorSerializer == null) serializationDescriptorSerializer = SerializationDescriptorSerializerPlugin()
            DescriptorSerializerPlugin.registerExtension(project, serializationDescriptorSerializer!!)
            registerProtoExtensions()
        }


        private fun registerProtoExtensions() {
            SerializationPluginMetadataExtensions.registerAllExtensions(JvmProtoBufUtil.EXTENSION_REGISTRY)
            SerializationPluginMetadataExtensions.registerAllExtensions(JsSerializerProtocol.extensionRegistry)
            SerializationPluginMetadataExtensions.registerAllExtensions(KlibMetadataSerializerProtocol.extensionRegistry)
        }

    }
}

class SerializationPluginComponentContainerContributor : StorageComponentContainerContributor {
    override fun registerModuleComponents(
        container: StorageComponentContainer,
        platform: TargetPlatform,
        moduleDescriptor: ModuleDescriptor
    ) {
        container.useInstance(SerializationPluginDeclarationChecker())
    }
}
