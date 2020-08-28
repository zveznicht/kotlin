/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.checkers.declaration

import org.jetbrains.kotlin.fir.FirFakeSourceElementKind
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.analysis.checkers.FirDeclarationInspector
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors
import org.jetbrains.kotlin.fir.analysis.getChild
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.isLocalClassOrAnonymousObject
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.name.SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT

object FirConflictsChecker : FirBasicDeclarationChecker() {
    override fun check(declaration: FirDeclaration, context: CheckerContext, reporter: DiagnosticReporter) {
        val inspector = FirDeclarationInspector()

        when (declaration) {
            is FirFile -> checkFile(declaration, inspector)
            is FirRegularClass -> checkRegularClass(declaration, inspector)
            else -> return
        }

        inspector.functionDeclarations.forEachNonSingle { it, hint ->
            // we'd better highlight the whole `name([param, ...])` thing
            reporter.reportConflictingOverloads(it.source?.getChild(KtTokens.IDENTIFIER), hint)
        }

        inspector.otherDeclarations.forEachNonSingle { it, hint ->
            if (it is FirRegularClass && it.isCompanion) {
                if (it.name == DEFAULT_NAME_FOR_COMPANION_OBJECT) {
                    reporter.reportConflictingDeclarations(it.source?.getChild(KtTokens.OBJECT_KEYWORD), hint)
                } else {
                    reporter.reportConflictingDeclarations(it.source?.getChild(KtTokens.IDENTIFIER), hint)
                }
            } else {
                reporter.reportConflictingDeclarations(it.source?.getChild(KtTokens.IDENTIFIER), hint)
            }
        }
    }

    private fun Map<String, List<FirDeclaration>>.forEachNonSingle(action: (FirDeclaration, String) -> Unit) {
        for (value in values) {
            if (value.size > 1) {
                val hint = value.joinToString { that -> that.toString() }

                value.forEach {
                    action(it, hint)
                }
            }
        }
    }

    private fun checkFile(declaration: FirFile, inspector: FirDeclarationInspector) {
        for (it in declaration.declarations) {
            inspector.collect(it)
        }
    }

    private fun checkRegularClass(declaration: FirRegularClass, inspector: FirDeclarationInspector) {
        for (it in declaration.declarations) {
            inspector.collect(it)
        }
    }

    private fun DiagnosticReporter.reportConflictingOverloads(source: FirSourceElement?, declarations: String) {
        source?.let { report(FirErrors.CONFLICTING_OVERLOADS.on(it, declarations)) }
    }

    private fun DiagnosticReporter.reportConflictingDeclarations(source: FirSourceElement?, declarations: String) {
        source?.let { report(FirErrors.REDECLARATION.on(it, declarations)) }
    }
}
