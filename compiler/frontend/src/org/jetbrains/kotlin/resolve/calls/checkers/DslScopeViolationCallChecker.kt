/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.resolve.calls.checkers

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.resolve.calls.DslMarkerUtils.extractDslMarkerFqNames
import org.jetbrains.kotlin.resolve.calls.extractDslMarkerFqNames
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.calls.resolvedCallUtil.getImplicitReceivers
import org.jetbrains.kotlin.resolve.descriptorUtil.getOwnerForEffectiveDispatchReceiverParameter
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValue
import org.jetbrains.kotlin.resolve.scopes.utils.parentsWithSelf

object DslScopeViolationCallChecker : CallChecker {
    override fun check(resolvedCall: ResolvedCall<*>, reportOn: PsiElement, context: CallCheckerContext) {
        if (!context.languageVersionSettings.supportsFeature(LanguageFeature.DslMarkersSupport)) return
        val callImplicitReceivers = resolvedCall.getImplicitReceivers()

        val originalReceivers = if (context.languageVersionSettings.supportsFeature(LanguageFeature.NewInference))
            callImplicitReceivers.map { it.original }
        else
            callImplicitReceivers

        for (callImplicitReceiver in originalReceivers) {
            checkCallImplicitReceiver(callImplicitReceiver, resolvedCall, reportOn, context)
        }
    }

    private fun checkCallImplicitReceiver(
        callImplicitReceiver: ReceiverValue,
        resolvedCall: ResolvedCall<*>,
        reportOn: PsiElement,
        context: CallCheckerContext
    ) {
        val receiversUntilOneFromTheCall =
            context.scope.parentsWithSelf
                .mapNotNull { (it as? LexicalScope)?.implicitReceiver?.value }
                .takeWhile { it != callImplicitReceiver }.toList()

        if (receiversUntilOneFromTheCall.isEmpty()) return

//        val callDslMarkers = resolvedCall.resultingDescriptor.original.containingDeclaration.annotations.extractDslMarkerFqNames()
//        val additionalCallDslMarkers = emptySet<FqName>()

        val (callDslMarkers, additionalCallDslMarkers) = extractDslMarkerFqNames(callImplicitReceiver)
        if (callDslMarkers.isEmpty() && additionalCallDslMarkers.isEmpty()) return

        val dslMarkersFromOuterReceivers = receiversUntilOneFromTheCall.map(::extractDslMarkerFqNames)

        val closestAnotherReceiverWithSameDslMarker =
            dslMarkersFromOuterReceivers.firstOrNull { (dslMarkersFromReceiver, _) ->
                dslMarkersFromReceiver.any(callDslMarkers::contains)
            }

        val withOverrides = resolvedCall.resultingDescriptor.original.withOverrides()

        fun ReceiverParameterDescriptor.annotations() = type.constructor.declarationDescriptor?.annotations

        val explicitDslMarkers = withOverrides.flatMap {
            val result = mutableListOf<FqName>()
            it.dispatchReceiverParameter?.annotations()?.let { result += it.extractDslMarkerFqNames() }
            it.extensionReceiverParameter?.annotations()?.let { result += it.extractDslMarkerFqNames() }

            result
        }

        if (closestAnotherReceiverWithSameDslMarker != null) {
            // TODO: report receivers configuration (what's one is used and what's one is the closest)
            if (explicitDslMarkers.intersect(callDslMarkers).isNotEmpty()) {
                context.trace.report(Errors.DSL_SCOPE_VIOLATION.on(reportOn, resolvedCall.resultingDescriptor))
            }
            return
        }

        val allDslMarkersFromCall = callDslMarkers + additionalCallDslMarkers

        val closestAnotherReceiverWithSameDslMarkerWithDeprecation =
            dslMarkersFromOuterReceivers.firstOrNull { (dslMarkersFromReceiver, additionalDslMarkersFromReceiver) ->
                val allMarkersFromReceiver = dslMarkersFromReceiver + additionalDslMarkersFromReceiver
                allDslMarkersFromCall.any(allMarkersFromReceiver::contains)
            }

        if (closestAnotherReceiverWithSameDslMarkerWithDeprecation != null) {
            val diagnostic =
                if (context.languageVersionSettings.supportsFeature(LanguageFeature.DslMarkerOnFunctionTypeReceiver))
                    Errors.DSL_SCOPE_VIOLATION
                else
                    Errors.DSL_SCOPE_VIOLATION_WARNING

            context.trace.report(diagnostic.on(reportOn, resolvedCall.resultingDescriptor))
        }
    }
}

fun CallableDescriptor.withOverrides(): Collection<CallableDescriptor> {

    val result = mutableSetOf<CallableDescriptor>(this)

    fun CallableDescriptor.dfs() {
        overriddenDescriptors.forEach {
            if (it !in result) {
                result += it
                it.dfs()
            }
        }
    }

    dfs()

    return result
}