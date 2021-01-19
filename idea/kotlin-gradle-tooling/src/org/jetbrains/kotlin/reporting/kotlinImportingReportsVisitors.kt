/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.org.jetbrains.kotlin.reporting

import org.gradle.api.logging.LogLevel
import org.gradle.api.logging.Logger
import org.jetbrains.kotlin.gradle.ErroneousImportingReport
import org.jetbrains.kotlin.gradle.KotlinImportingReport
import org.jetbrains.kotlin.gradle.OrphanSourceSetsImportingReport

interface KotlinImportingReportsVisitor<T> {
    fun visit(visited: T)
}

interface SingleKotlinImportingReportVisitor<T: KotlinImportingReport> :  KotlinImportingReportsVisitor<T>

abstract class GradleLogImportingReportVisitor<T : KotlinImportingReport>(protected val logger: Logger) :
    SingleKotlinImportingReportVisitor<T> {
    protected open val logLevel = LogLevel.INFO
    abstract fun logReport(report: T)
    override fun visit(visited: T) = logReport(visited)
}

class GradleLogOrphanSourceSetReportVisitor(logger: Logger) : GradleLogImportingReportVisitor<OrphanSourceSetsImportingReport>(logger) {
    override val logLevel: LogLevel = LogLevel.WARN

    override fun logReport(report: OrphanSourceSetsImportingReport) {
        logger.log(logLevel, report.message)
    }
}

class GradleLogErroneousImportingReportVisitor(logger: Logger) : GradleLogImportingReportVisitor<ErroneousImportingReport>(logger) {
    override fun logReport(report: ErroneousImportingReport) {
        logger.log(logLevel, report.cause, report.throwable)
    }
}