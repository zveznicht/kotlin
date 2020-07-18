/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.js.resolve.diagnostics

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType
import org.jetbrains.kotlin.psi.psiUtil.getParentOfTypes
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall
import org.jetbrains.kotlin.resolve.calls.checkers.CallChecker
import org.jetbrains.kotlin.resolve.calls.checkers.CallCheckerContext
import org.jetbrains.kotlin.resolve.calls.checkers.findEnclosingSuspendFunction
import org.jetbrains.kotlin.resolve.calls.model.ExpressionValueArgument
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.checkers.*
import org.jetbrains.kotlin.resolve.descriptorUtil.module
import org.jetbrains.kotlin.types.typeUtil.immediateSupertypes
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

object JsLazyModuleReferenceChecker : CallChecker {
    override fun check(resolvedCall: ResolvedCall<*>, reportOn: PsiElement, context: CallCheckerContext) {

        val currentModule = context.moduleDescriptor as? ModuleDescriptorImpl ?: return
        val targetModule = resolvedCall.resultingDescriptor.original.module

        if (currentModule.shouldLazyLoad(targetModule)) {

            val callElement = resolvedCall.call.callElement as? KtExpression ?: return

            if (isLazyModuleAccessRestricted(context, callElement)) {
                val descriptor = resolvedCall.resultingDescriptor.original
                if (descriptor.dispatchReceiverParameter == null) {
                    context.trace.report(ErrorsJs.WRONG_ASYNC_MODULE_REFERENCE.on(callElement, "cannot reference async module"))
                }
            }
        }
    }
}

object JsLazyModuleClassifierUsageChecker: ClassifierUsageChecker {
    override fun check(targetDescriptor: ClassifierDescriptor, element: PsiElement, context: ClassifierUsageCheckerContext) {
        val currentModule = context.moduleDescriptor as? ModuleDescriptorImpl ?: return
        val targetModule = targetDescriptor.module

        if (currentModule.shouldLazyLoad(targetModule)) {

            if (!isInsideSpecialLambda(context.trace.bindingContext, element) && !isInsidgeSuspendFunction(context.trace.bindingContext, element)) {
                if (element.getParentOfType<KtExpression>(true) is KtDotQualifiedExpression && targetDescriptor is ClassDescriptor) {
                    context.trace.report(ErrorsJs.WRONG_ASYNC_MODULE_REFERENCE.on(element, "cannot reference async module"))
                }
            }
        }
    }
}

object JsLazyModuleDeclarationChecker : DeclarationChecker {
    override fun check(declaration: KtDeclaration, descriptor: DeclarationDescriptor, context: DeclarationCheckerContext) {

        val currentModule = context.moduleDescriptor as? ModuleDescriptorImpl ?: return

        if (descriptor is ClassDescriptor &&
            descriptor.defaultType.immediateSupertypes()
                .any { it.constructor.declarationDescriptor?.module?.let { currentModule.shouldLazyLoad(it) } ?: false }
        ) {
            context.trace.report(ErrorsJs.WRONG_ASYNC_MODULE_REFERENCE.on(declaration, "cannot inherit from an async module declaration"))
        }
    }
}

private fun isInsidgeSuspendFunction(bindingContext: BindingContext, e: PsiElement): Boolean {
    generateSequence(e) { it.getParentOfType<KtFunction>(true) }.forEach { p ->
        val d = bindingContext.get(BindingContext.DECLARATION_TO_DESCRIPTOR, p.getParentOfType<KtFunction>(true)) as? FunctionDescriptor
        if (d?.isSuspend == true) return true
    }

    return false
}

private fun isLazyModuleAccessRestricted(context: CallCheckerContext, e: KtExpression): Boolean {
    return findEnclosingSuspendFunction(context, e) == null && !isInsideSpecialLambda(context.trace.bindingContext, e)
}

private val AllowAsyncRefsFqn = FqName("kotlin.js.AllowAsyncRefs")

private fun isInsideSpecialLambda(bindingContext: BindingContext, e: PsiElement): Boolean {

    var lastArgument: KtValueArgument? = null

    generateSequence(e) { it.getParentOfTypes(true, KtValueArgument::class.java, KtCallExpression::class.java) }.forEach { p ->
        when (p) {
            is KtValueArgument -> lastArgument = p
            is KtCallExpression -> {
                val resolvedCall = p.getResolvedCall(bindingContext)
                if (resolvedCall?.resultingDescriptor is FunctionDescriptor) {
                    for ((d, v) in resolvedCall.valueArguments.entries) {
                        val argument = v.safeAs<ExpressionValueArgument>()?.valueArgument ?: continue

                        if (lastArgument != argument) continue

                        if (d.annotations.any { it.fqName == AllowAsyncRefsFqn }) return true
                    }
                }
            }
        }
    }

    return false
}

