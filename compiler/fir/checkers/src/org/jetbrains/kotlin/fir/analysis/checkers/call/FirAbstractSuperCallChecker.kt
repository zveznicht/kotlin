/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.checkers.call

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.checkers.getClassLikeDeclaration
import org.jetbrains.kotlin.fir.analysis.checkers.markedAs
import org.jetbrains.kotlin.fir.analysis.checkers.getSimpleFunctionDeclaration
import org.jetbrains.kotlin.fir.analysis.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors
import org.jetbrains.kotlin.fir.declarations.FirRegularClass
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
import org.jetbrains.kotlin.fir.references.FirSuperReference
import org.jetbrains.kotlin.lexer.KtTokens.ABSTRACT_KEYWORD
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

object FirAbstractSuperCallChecker : FirFunctionCallChecker() {
    override fun check(functionCall: FirFunctionCall, context: CheckerContext, reporter: DiagnosticReporter) {
        val closestClass = context.findClosest<FirRegularClass>() ?: return

        // require the receiver to be the super reference
        functionCall.explicitReceiver.safeAs<FirQualifiedAccessExpression>()
            ?.calleeReference.safeAs<FirSuperReference>()
            ?: return

        if (closestClass.classKind == ClassKind.CLASS) {
            val function = functionCall.getSimpleFunctionDeclaration(context)
                ?: return

            val declaration = functionCall.getClassLikeDeclaration(context)
                .safeAs<FirRegularClass>()
                ?: return

            if (
                declaration.markedAs(ABSTRACT_KEYWORD) &&
                function.markedAs(ABSTRACT_KEYWORD)
            ) {
                reporter.report(functionCall.source)
            }
        }
    }

    private fun DiagnosticReporter.report(source: FirSourceElement?) {
        source?.let {
            report(FirErrors.ABSTRACT_SUPER_CALL.on(it))
        }
    }
}