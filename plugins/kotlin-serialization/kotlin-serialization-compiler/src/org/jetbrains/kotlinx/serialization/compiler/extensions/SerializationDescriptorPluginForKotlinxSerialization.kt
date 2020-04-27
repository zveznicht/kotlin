/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.serialization.compiler.extensions

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.metadata.serialization.MutableVersionRequirementTable
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.serialization.DescriptorSerializer
import org.jetbrains.kotlin.serialization.DescriptorSerializerPlugin
import org.jetbrains.kotlin.serialization.SerializerExtension
import org.jetbrains.kotlinx.serialization.compiler.resolve.isInternalSerializable
import org.jetbrains.kotlinx.serialization.compiler.resolve.serializablePropertiesFor

class SerializationDescriptorPluginForKotlinxSerialization : DescriptorSerializerPlugin {
    override fun afterClass(
        descriptor: ClassDescriptor,
        proto: ProtoBuf.Class.Builder,
        versionRequirementTable: MutableVersionRequirementTable,
        childSerializer: DescriptorSerializer,
        bindingContext: BindingContext?,
        extension: SerializerExtension
    ) {
        fun Name.toIndex() = extension.stringTable.getStringIndex(asString())

        val isApplicable =
            descriptor.isInternalSerializable && (descriptor.modality == Modality.OPEN || descriptor.modality == Modality.ABSTRACT)
        if (!isApplicable) return

        val propertiesCorrectOrder = bindingContext?.serializablePropertiesFor(descriptor)?.serializableProperties.orEmpty()
        proto.setExtension(
            SerializationPluginMetadataExtensions.propertiesNamesInProgramOrder,
            propertiesCorrectOrder.map { it.descriptor.name.toIndex() }
        )
    }
}