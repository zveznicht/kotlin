/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.util

import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.incremental.components.NoLookupLocation
import org.jetbrains.kotlin.ir.descriptors.*
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.scopes.MemberScope
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DescriptorWithContainerSource
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor

inline fun ClassDescriptor.findFirstFunction(name: String, predicate: (CallableMemberDescriptor) -> Boolean) =
    unsubstitutedMemberScope.findFirstFunction(name, predicate)

inline fun MemberScope.findFirstFunction(name: String, predicate: (CallableMemberDescriptor) -> Boolean) =
    getContributedFunctions(Name.identifier(name), NoLookupLocation.FROM_BACKEND).first(predicate)

fun FunctionDescriptor.toWrapped(): WrappedSimpleFunctionDescriptor = when {
    this is DescriptorWithContainerSource ->
        WrappedFunctionDescriptorWithContainerSource(containerSource)
    this is PropertyGetterDescriptor ->
        WrappedPropertyGetterDescriptor(annotations, source)
    this is PropertySetterDescriptor ->
        WrappedPropertySetterDescriptor(annotations, source)
    this is SimpleFunctionDescriptor ->
        WrappedSimpleFunctionDescriptor(annotations, source)
    else -> error("unexpected descriptor kind: $this")
}