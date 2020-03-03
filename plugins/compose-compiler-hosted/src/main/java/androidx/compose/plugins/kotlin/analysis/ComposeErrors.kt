/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package androidx.compose.plugins.kotlin.analysis

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory0
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory2
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.diagnostics.Severity
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.types.KotlinType

/**
 * Error messages
 */
interface ComposeErrors {
    companion object {
        val OPEN_MODEL =
            DiagnosticFactory0
                .create<PsiElement>(Severity.ERROR)
        val SUSPEND_FUNCTION_USED_AS_SFC =
            DiagnosticFactory0
                .create<KtElement>(Severity.ERROR)
        val COMPOSABLE_INVOCATION_IN_NON_COMPOSABLE =
            DiagnosticFactory0
                .create<PsiElement>(Severity.ERROR)
        val INVALID_TYPE_SIGNATURE_SFC =
            DiagnosticFactory0
                .create<KtElement>(Severity.ERROR)
        val NO_COMPOSER_FOUND =
            DiagnosticFactory0
                .create<KtElement>(Severity.ERROR)
        val INVALID_COMPOSER_IMPLEMENTATION =
            DiagnosticFactory2
                .create<KtElement, KotlinType, String>(
                    Severity.ERROR
                )
        val ILLEGAL_ASSIGN_TO_UNIONTYPE =
            DiagnosticFactory2
                .create<KtExpression, Collection<KotlinType>, Collection<KotlinType>>(
                    Severity.ERROR
                )
        val ILLEGAL_TRY_CATCH_AROUND_COMPOSABLE =
            DiagnosticFactory0
                .create<PsiElement>(Severity.ERROR)
        val INITIALIZER: Any = object : Any() {
            init {
                Errors.Initializer.initializeFactoryNames(ComposeErrors::class.java)
            }
        }
    }
}