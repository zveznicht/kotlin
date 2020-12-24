/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.inspections

import com.intellij.util.containers.MultiMap
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.incremental.components.NoLookupLocation
import org.jetbrains.kotlin.inspections.WellKnownNames.ATOMICS
import org.jetbrains.kotlin.inspections.WellKnownNames.FROZEN_ANNOTATION_FQ_NAME
import org.jetbrains.kotlin.inspections.WellKnownNames.MUTABLE_BLACK_LIST
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.calls.model.ResolvedValueArgument
import org.jetbrains.kotlin.resolve.calls.model.VariableAsFunctionResolvedCall
import org.jetbrains.kotlin.resolve.calls.resolvedCallUtil.getImplicitReceivers
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.resolve.hasBackingField
import org.jetbrains.kotlin.resolve.scopes.receivers.ExpressionReceiver
import org.jetbrains.kotlin.resolve.scopes.receivers.ImplicitReceiver
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.supertypes
import org.jetbrains.kotlin.utils.SmartList
import org.jetbrains.kotlin.utils.addIfNotNull

object WellKnownNames {
    val FROZEN_ANNOTATION_FQ_NAME = FqName("kotlin.native.concurrent.Frozen")
    val ATOMIC_INT = FqName("kotlin.native.concurrent.AtomicInt")
    val ATOMIC_LONG = FqName("kotlin.native.concurrent.AtomicLong")
    val ATOMIC_NATIVE_PTR = FqName("kotlin.native.concurrent.AtomicNativePtr")
    val ATOMIC_REFERENCE = FqName("kotlin.native.concurrent.AtomicReference")
    val MUTABLE_ITERABLE = FqName("kotlin.collections.MutableIterable")

    val ATOMICS = setOf(ATOMIC_INT, ATOMIC_LONG, ATOMIC_NATIVE_PTR, ATOMIC_REFERENCE)
    val MUTABLE_BLACK_LIST = setOf(MUTABLE_ITERABLE)
}

val ResolvedCall<*>.isCold: Boolean
    get() {
        return frozenRoots.isNotEmpty()
    }

@OptIn(ExperimentalStdlibApi::class)
val ResolvedCall<*>.frozenRoots: List<FreezableArgumentRoot>
    get() = buildList {
        for ((valueParameterDescriptor, valueArgument) in valueArguments) {
            if (valueParameterDescriptor.annotations.hasAnnotation(FROZEN_ANNOTATION_FQ_NAME)) {
                add(FreezableValueArgumentRoot(valueArgument))
            }
        }

        val receiver: ExpressionReceiver? = extensionReceiver as? ExpressionReceiver
        if (receiver != null
            && candidateDescriptor.extensionReceiverParameter?.annotations?.hasAnnotation(FROZEN_ANNOTATION_FQ_NAME) == true
        ) {
            add(FreezableExtensionReceiverRoot(receiver))
        }
    }

abstract class FreezableArgumentRoot {
    abstract fun getExpressions(): Collection<KtExpression>
}

class FreezableValueArgumentRoot(val root: ResolvedValueArgument) : FreezableArgumentRoot() {
    override fun getExpressions(): Collection<KtExpression> =
        root.arguments.mapNotNull { it.getArgumentExpression() }
}

class FreezableExtensionReceiverRoot(val explicitExtensionReceiver: ExpressionReceiver) : FreezableArgumentRoot() {
    override fun getExpressions(): Collection<KtExpression> {
        return SmartList(explicitExtensionReceiver.expression)
    }
}

private typealias CapturedValues = Map<KtExpression, ValueDescriptor>
private typealias CapturedImplicitReceivers = Map<KtExpression, ImplicitReceiver>
private typealias CaptureResults = Pair<CapturedValues, CapturedImplicitReceivers>

@OptIn(ExperimentalStdlibApi::class)
private fun valuesFromFreezableClosure(root: FreezableArgumentRoot, context: BindingContext): CaptureResults {
    val lambdas = mutableSetOf<FunctionDescriptor>()
    val capturedValues = mutableMapOf<KtExpression, ValueDescriptor>()
    val capturedImplicitReceivers = mutableMapOf<KtExpression, ImplicitReceiver>()

    for (argumentExpression in root.getExpressions()) {
        argumentExpression.accept(object : KtTreeVisitorVoid() {
            // only care about properties that come from outside
            override fun visitSimpleNameExpression(expression: KtSimpleNameExpression) {
                val referenceTarget = context[BindingContext.REFERENCE_TARGET, expression]
                if (referenceTarget is ValueDescriptor) {
                    val containingDeclarations = generateSequence(referenceTarget, DeclarationDescriptor::getContainingDeclaration)
                    if (containingDeclarations.none { it in lambdas }) {
                        capturedValues[expression] = referenceTarget
                    }
                }
                super.visitSimpleNameExpression(expression)
            }

            override fun visitThisExpression(expression: KtThisExpression) {
                val call = context[BindingContext.CALL, expression]
                val resolvedCall = context[BindingContext.RESOLVED_CALL, call] ?: return
                val containingDeclarations =
                    generateSequence(resolvedCall.candidateDescriptor, DeclarationDescriptor::getContainingDeclaration)
                if (containingDeclarations.none { it in lambdas }) {
                    val candidate = resolvedCall.candidateDescriptor
                    if (candidate is ReceiverParameterDescriptor)
                        capturedValues[expression] = candidate
                }
            }

            // call's implicit receiver from outside of lambda is captured
            override fun visitExpression(expression: KtExpression) {
                val call = context[BindingContext.CALL, expression]

                context[BindingContext.RESOLVED_CALL, call]?.let { resolvedCall ->
                    registerCapturedImplicitReceivers(resolvedCall, expression)
                    if (resolvedCall is VariableAsFunctionResolvedCall) {
                        registerCapturedImplicitReceivers(resolvedCall.variableCall, expression)
                    }
                }

                super.visitExpression(expression)
            }

            private fun registerCapturedImplicitReceivers(resolvedCall: ResolvedCall<*>, expression: KtExpression) {
                for (implicitReceiver in resolvedCall.getImplicitReceivers()) {
                    if (implicitReceiver is ImplicitReceiver && implicitReceiver.declarationDescriptor !in lambdas) {
                        capturedImplicitReceivers[expression] = implicitReceiver
                    }
                }
            }

            override fun visitLambdaExpression(lambdaExpression: KtLambdaExpression) {
                lambdas.addIfNotNull(context[BindingContext.FUNCTION, lambdaExpression.functionLiteral])
                super.visitLambdaExpression(lambdaExpression)
            }
        })
    }

    return capturedValues to capturedImplicitReceivers
}

sealed class CapturedCandidate(val element: KtExpression, val type: KotlinType)
class CapturedImplicitReceiver(element: KtExpression, type: KotlinType) : CapturedCandidate(element, type)
class CapturedValue(element: KtExpression, type: KotlinType) : CapturedCandidate(element, type)

fun collectTypesFromFreezableCapture(
    root: FreezableArgumentRoot,
    context: BindingContext,
    collector: MultiMap<KotlinType, CapturedCandidate>
) {
    val (capturedValues, capturedImplicitReceivers) = valuesFromFreezableClosure(root, context)
    for ((expression, freezableValue) in capturedValues) {
        collector.putValue(freezableValue.type, CapturedValue(expression, freezableValue.type))
    }
    for ((expression, freezableImplicitReceiver) in capturedImplicitReceivers) {
        collector.putValue(freezableImplicitReceiver.type, CapturedImplicitReceiver(expression, freezableImplicitReceiver.type))
    }
}

sealed class MutabilityResult

object Stateless : MutabilityResult()
object InProgress : MutabilityResult()
class Stateful(val type: KotlinType, val property: PropertyDescriptor?) : MutabilityResult()
class TransitivelyStateful(val type: KotlinType, val property: PropertyDescriptor, val cause: MutabilityResult) : MutabilityResult()

fun findFirstMutableStateOwnerIfAny(
    type: KotlinType,
    cache: MutableMap<KotlinType, MutabilityResult>,
    context: BindingContext,
): MutabilityResult {
    cache[type]?.let { return it }

    cache[type] = InProgress

    if (type.supertypes().any { it.constructor.declarationDescriptor?.fqNameSafe in MUTABLE_BLACK_LIST }) {
        return Stateful(type, null).also {
            cache[type] = it
        }
    }

    val lazyVariableSequence = sequence {
        for (varName in type.memberScope.getVariableNames()) {
            yieldAll(type.memberScope.getContributedVariables(varName, NoLookupLocation.FOR_ALREADY_TRACKED))
        }
    }

    val nextIteration = mutableSetOf<Pair<KotlinType, PropertyDescriptor>>()
    for (variable in lazyVariableSequence) {
        when {
            variable.isVar -> {
                return Stateful(type, variable).also {
                    cache[type] = it
                }
            }
            variable.withBackingField(context) -> {
                if (variable.type.constructor.declarationDescriptor?.fqNameSafe !in ATOMICS)
                    nextIteration.add(variable.type to variable)
            }
        }
    }

    // Potentially dangerous, no certainty that this recursion converges in a reasonable time
    for ((propertyType, property) in nextIteration) {
        val result = findFirstMutableStateOwnerIfAny(propertyType, cache, context)
        if (result is Stateful || result is TransitivelyStateful) {
            return TransitivelyStateful(type, property, result).also {
                cache[type] = it
            }
        }
    }

    cache[type] = Stateless
    return Stateless
}

// monkey patched version from resolve util, source !is KotlinSourceElement for substituted type parameters
// TODO: investigate if it's possible to improve that method instead (consider original descriptor also mb?)
private fun PropertyDescriptor.withBackingField(bindingContext: BindingContext): Boolean {
    return when (true) {
        kind == CallableMemberDescriptor.Kind.FAKE_OVERRIDE -> overriddenDescriptors.any { it.hasBackingField(bindingContext) }
        bindingContext[BindingContext.BACKING_FIELD_REQUIRED, this] -> true
        compileTimeInitializer != null -> true
        getter != null -> false
        else -> true
    }
}
