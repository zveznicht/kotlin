/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.resolve.calls.tasks

import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithVisibility
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.diagnostics.reportDiagnosticOnce
import org.jetbrains.kotlin.psi.Call
import org.jetbrains.kotlin.psi.KtCallableReferenceExpression
import org.jetbrains.kotlin.psi.KtSimpleNameExpression
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

class TracingStrategyForCallableReferenceWithNewInference(
    callableReferenceExpression: KtCallableReferenceExpression,
    call: Call,
) : AbstractTracingStrategy(callableReferenceExpression, call) {
    private val referenceRHS: KtSimpleNameExpression =
        call.valueArguments.single().getArgumentExpression().safeAs<KtCallableReferenceExpression>()?.callableReference
            ?: error("Incorrect argument of synthetic call for top-level callable reference. Call: $call")

    override fun <D : CallableDescriptor?> bindResolvedCall(trace: BindingTrace, resolvedCall: ResolvedCall<D>) {
        // Do nothing for fake wrapper
    }

    override fun <D : CallableDescriptor?> unresolvedReferenceWrongReceiver(
        trace: BindingTrace,
        candidates: MutableCollection<out ResolvedCall<D>>
    ) {
        // Do nothing for fake wrapper
    }

    override fun unresolvedReference(trace: BindingTrace) {
        // Do nothing for fake wrapper
    }

    override fun bindCall(trace: BindingTrace, call: Call) {
        // Do nothing for fake wrapper
    }

    override fun <D : CallableDescriptor?> bindReference(trace: BindingTrace, resolvedCall: ResolvedCall<D>) {
        // Do nothing for fake wrapper
    }

    override fun invisibleMember(trace: BindingTrace, descriptor: DeclarationDescriptorWithVisibility) {
        trace.reportDiagnosticOnce(Errors.INVISIBLE_MEMBER.on(referenceRHS, descriptor, descriptor.visibility, descriptor))
    }

    override fun instantiationOfAbstractClass(trace: BindingTrace) {
        trace.reportDiagnosticOnce(Errors.CREATING_AN_INSTANCE_OF_ABSTRACT_CLASS.on(referenceRHS))
    }

    override fun wrongNumberOfTypeArguments(trace: BindingTrace, expectedTypeArgumentCount: Int, descriptor: CallableDescriptor) {
        // Do nothing for fake wrapper
    }
}