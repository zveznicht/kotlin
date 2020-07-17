/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.checkers.expression

import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.checkers.anyTypeParameterHasTypeArguments
import org.jetbrains.kotlin.fir.analysis.checkers.retrieveTypeArgumentsSources
import org.jetbrains.kotlin.fir.analysis.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance
import org.jetbrains.kotlin.fir.types.coneType
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

object FirTypeArgumentsNotAllowedExpressionChecker : FirQualifiedAccessChecker() {
    override fun check(functionCall: FirQualifiedAccessExpression, context: CheckerContext, reporter: DiagnosticReporter) {
        // otherwise there'll be an attempt
        // to access firstChild of some null FirElement
        if (functionCall.typeRef is FirErrorTypeRef) {
            return
        }

        // analyze type parameters near
        // package names
        val explicitReceiver = functionCall.explicitReceiver

        if (explicitReceiver is FirResolvedQualifier && explicitReceiver.symbol == null) {
            val receiverSources = explicitReceiver.source?.retrieveTypeArgumentsSources()
                ?.firstOrNull()

            if (receiverSources?.hasAnyArguments() == true) {
                reporter.report(receiverSources.arguments)
                return
            }
        }

        // analyze things like `T<String>`
        val typeSources = functionCall.source?.retrieveTypeArgumentsSources()?.iterator()
            ?: return

        // skip the first which is the
        // function name
        typeSources.next()

        for (it in functionCall.typeArguments) {
            if (typeSources.hasNext()) {
                val sources = typeSources.next()

                it.safeAs<FirTypeProjectionWithVariance>()?.typeRef?.coneType?.let {
                    if (it.anyTypeParameterHasTypeArguments(sources, typeSources, reporter)) {
                        return
                    }
                }
            } /*else {
                println("ITERATORS HAS NO NEXT: $it")
            }*/
        }
    }

    private fun DiagnosticReporter.report(source: FirSourceElement?) {
        source?.let {
            report(FirErrors.TYPE_ARGUMENTS_NOT_ALLOWED.on(it))
        }
    }
}