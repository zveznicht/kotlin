/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.checkers.declaration

import org.jetbrains.kotlin.fir.analysis.checkers.SourceClarification
import org.jetbrains.kotlin.fir.analysis.checkers.anyTypeParameterHasTypeArguments
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.checkers.retrieveTypeArgumentsSources
import org.jetbrains.kotlin.fir.analysis.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.fir.declarations.FirDeclaration
import org.jetbrains.kotlin.fir.declarations.FirVariable
import org.jetbrains.kotlin.fir.types.ConeClassErrorType
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

object FirTypeArgumentsNotAllowedDeclarationChecker : FirBasicDeclarationChecker() {
    override fun check(declaration: FirDeclaration, context: CheckerContext, reporter: DiagnosticReporter) {
        if (declaration is FirVariable<*>) {
            val returnType = declaration.returnTypeRef.safeAs<FirResolvedTypeRef>()?.type
                ?: return

            // otherwise there'll be an attempt
            // to access firstChild of some null FirElement
            if (returnType is ConeClassErrorType) {
                return
            }

            declaration.returnTypeRef.source?.let { source ->
                val typeSources = source.retrieveTypeArgumentsSources(
                    SourceClarification().forPsi { it.firstChild }
                ).iterator()

                if (returnType.anyTypeParameterHasTypeArguments(typeSources.next(), typeSources, reporter)) {
                    return
                }
            }
        }
    }
}