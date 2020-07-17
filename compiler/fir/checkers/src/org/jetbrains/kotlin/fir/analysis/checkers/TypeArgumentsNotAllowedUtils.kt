/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.checkers

import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.analysis.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

fun ConeKotlinType.anyTypeParameterHasTypeArguments(
    sources: TypeArgumentSources,
    iterator: Iterator<TypeArgumentSources>,
    reporter: DiagnosticReporter
): Boolean {
    if (this is ConeTypeParameterType) {
        if (sources.hasAnyArguments()) {
            reporter.report(sources.arguments)
            return true
        }
    }

    for (it in typeArguments) {
        val coneType = it.safeAs<ConeKotlinType>()

        if (iterator.hasNext()) {
            if (coneType?.anyTypeParameterHasTypeArguments(iterator.next(), iterator, reporter) == true) {
                return true
            }
        } /*else {
            println("ITERATORS HAS NO NEXT: $it")
        }*/
    }

    return false
}

private fun DiagnosticReporter.report(source: FirSourceElement?) {
    source?.let {
        report(FirErrors.TYPE_ARGUMENTS_NOT_ALLOWED.on(it))
    }
}