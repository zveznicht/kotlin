/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen

import org.jetbrains.kotlin.config.JvmDefaultMode
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.resolve.descriptorUtil.getSuperClassNotAny
import org.jetbrains.kotlin.resolve.jvm.annotations.isCompiledToJvmDefault
import org.jetbrains.kotlin.util.findInterfaceImplementation

// In new default scheme if implementations comes from JVM default method we shouldn't generate any implicit delegation to it.
// But in case of mixed hierarchies base class could have such implicit delegate to method in DefaultImpl.
// And this implicit override have more priority then default method from interface in runtime dispatching.
// That requires generation of another one delegate that explicitly calls default method.
fun FunctionDescriptor.findPossibleMethodClashesWithOldScheme(jvmDefaultMode: JvmDefaultMode): List<FunctionDescriptor> {
    val container = containingDeclaration as? ClassDescriptor ?: return emptyList()

    //if no super class then no any possible clashes
    container.getSuperClassNotAny() ?: return emptyList()

    val overridesFromClass = overriddenDescriptors.filter { descriptor: FunctionDescriptor ->
        !JvmCodegenUtil.isJvmInterface(descriptor.containingDeclaration)
    }

    val filtered = overridesFromClass.filter { findInterfaceImplementation(it, true)?.isCompiledToJvmDefault(jvmDefaultMode) == false }
    return filtered + overridesFromClass.flatMap { it.findPossibleMethodClashesWithOldScheme(jvmDefaultMode) }
}

