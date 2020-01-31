/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.stm.compiler.extensions

import org.jetbrains.kotlin.builtins.DefaultBuiltIns
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.descriptors.impl.SimpleFunctionDescriptorImpl
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension

open class StmResolveExtension : SyntheticResolveExtension {
    override fun generateSyntheticMethods(
        thisDescriptor: ClassDescriptor,
        name: Name,
        bindingContext: BindingContext,
        fromSupertypes: List<SimpleFunctionDescriptor>,
        result: MutableCollection<SimpleFunctionDescriptor>
    ) {

        val isPublish = name.asString() == "publish" && result.none {
            it.name.asString() == "publish" && it.valueParameters.size == 0 && it.returnType == DefaultBuiltIns.Instance.intType
        }

        if (!isPublish) return

        val functionDescriptor = SimpleFunctionDescriptorImpl.create(
            thisDescriptor,
            Annotations.EMPTY,
            name,
            CallableMemberDescriptor.Kind.SYNTHESIZED,
            thisDescriptor.source
        )
        functionDescriptor.initialize(
            null,
            thisDescriptor.thisAsReceiverParameter,
            emptyList(),
            emptyList(),
            DefaultBuiltIns.Instance.intType,
            Modality.OPEN,
            Visibilities.PUBLIC
        )

        result.add(functionDescriptor)

        result.contains(functionDescriptor)
    }
}