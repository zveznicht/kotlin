/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.resolve.konan.diagnostics

import com.intellij.psi.PsiElement
import com.intellij.util.containers.MultiMap
import org.jetbrains.kotlin.inspections.*
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.resolve.calls.checkers.CallChecker
import org.jetbrains.kotlin.resolve.calls.checkers.CallCheckerContext
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.types.KotlinType

object FrozenChecker : CallChecker {
    @OptIn(ExperimentalStdlibApi::class)
    override fun check(resolvedCall: ResolvedCall<*>, reportOn: PsiElement, context: CallCheckerContext) {
        val bindingContext = context.trace.bindingContext

        val candidatesByTypes = MultiMap.createSet<KotlinType, CapturedCandidate>()
        for (argument in resolvedCall.frozenRoots) {
            collectTypesFromFreezableCapture(argument, bindingContext, candidatesByTypes)
        }

        if (candidatesByTypes.isEmpty)
            return

        // probably worth a service service
        val cache = mutableMapOf<KotlinType, MutabilityResult>()
        for (type in candidatesByTypes.keySet()) {
            when (val mutabilityState = findFirstMutableStateOwnerIfAny(type, cache, bindingContext)) {
                InProgress -> {
                    error("Mutability analysis of type '$type' has not been completed properly")
                }
                is Stateful, is TransitivelyStateful -> {
                    for (mutableBastard in candidatesByTypes[type]) {
                        context.trace.report(ErrorsNative.FREEZE_WARNING.on(mutableBastard.element, mutabilityState, mutableBastard))
                    }
                    continue
                }
            }
        }
    }
}
