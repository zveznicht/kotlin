/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen.context

import org.jetbrains.kotlin.codegen.OwnerKind
import org.jetbrains.kotlin.codegen.binding.MutableClosure
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapper
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor

class IndyLambdaContext(
    functionDescriptor: FunctionDescriptor,
    contextKind: OwnerKind,
    parentContext: CodegenContext<*>,
    closure: MutableClosure?,
    localLookup: LocalLookup?,
    typeMapper: KotlinTypeMapper
) : CodegenContext<FunctionDescriptor>(
    functionDescriptor, contextKind, parentContext, closure,
    ClosureContext.getClassForCallable(typeMapper, functionDescriptor, null), localLookup
), LocalLookup {

    val classDescriptor = ClosureContext.getClassForCallable(typeMapper, functionDescriptor, null)

    override fun isLocal(descriptor: DeclarationDescriptor): Boolean {
        return true
    }
}