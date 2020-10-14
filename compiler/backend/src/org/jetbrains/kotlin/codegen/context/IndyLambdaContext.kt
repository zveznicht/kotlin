/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen.context

import org.jetbrains.kotlin.codegen.FrameMap
import org.jetbrains.kotlin.codegen.OwnerKind
import org.jetbrains.kotlin.codegen.StackValue
import org.jetbrains.kotlin.codegen.binding.MutableClosure
import org.jetbrains.kotlin.codegen.state.GenerationState
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapper
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor

class IndyLambdaContext(
    functionDescriptor: FunctionDescriptor,
    contextKind: OwnerKind,
    parentContext: CodegenContext<*>,
    closure: MutableClosure,
    localLookup: LocalLookup?,
    typeMapper: KotlinTypeMapper,
    val classDescriptor: ClassDescriptor
) : CodegenContext<FunctionDescriptor>(
    functionDescriptor, contextKind, parentContext, closure,
    ClosureContext.getClassForCallable(typeMapper, functionDescriptor, null), localLookup
), LocalLookup {

    val captured = linkedMapOf<DeclarationDescriptor, StackValue>()
    val frame = FrameMap()
    override fun lookupInContext(
        d: DeclarationDescriptor,
        result: StackValue?,
        state: GenerationState,
        ignoreNoOuter: Boolean
    ): StackValue? {
        val fromOldContext = super.lookupInContext(d, result, state, ignoreNoOuter)
        if (fromOldContext != null) {
            //TODO check staticness and skip
            captured[d] = fromOldContext
            if (frame.getIndex(d) == -1) {
                frame.enter(d, fromOldContext.type)
            }
        }

        return if (fromOldContext != null) StackValue.local(65000 + frame.getIndex(d), fromOldContext.type) else fromOldContext
    }

    override fun isLocal(descriptor: DeclarationDescriptor): Boolean {
        return captured.containsKey(descriptor)
    }
}