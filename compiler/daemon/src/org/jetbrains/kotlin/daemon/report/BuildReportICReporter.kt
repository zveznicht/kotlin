/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.daemon.report

import org.jetbrains.kotlin.build.metrics.BuildAttribute
import org.jetbrains.kotlin.build.metrics.BuildMetricsReporter
import org.jetbrains.kotlin.build.metrics.BuildMetricsReporterImpl
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.daemon.common.CompilationResultCategory
import org.jetbrains.kotlin.daemon.common.CompilationResults
import org.jetbrains.kotlin.incremental.ICReporterBase
import org.jetbrains.kotlin.build.metrics.BuildTime
import java.io.File
import java.util.*
import kotlin.collections.LinkedHashMap



// todo: sync BuildReportICReporterAsync
internal class BuildReportICReporter(
    private val compilationResults: CompilationResults,
    rootDir: File,
    private val isVerbose: Boolean = false,
    private val reportMetrics: Boolean = true
) : ICReporterBase(rootDir), RemoteICReporter {
    private val icLogLines = arrayListOf<String>()
    private val recompilationReason = HashMap<File, String>()
    override val metricsReporter: BuildMetricsReporter =
        if (reportMetrics) BuildMetricsReporterImpl() else BuildMetricsReporter.DoNothing

    override fun report(message: () -> String) {
        icLogLines.add(message())
    }

    override fun reportVerbose(message: () -> String) {
        if (isVerbose) {
            report(message)
        }
    }

    override fun reportCompileIteration(incremental: Boolean, sourceFiles: Collection<File>, exitCode: ExitCode) {
        if (!incremental) return

        icLogLines.add("Compile iteration:")
        for (file in sourceFiles) {
            val reason = recompilationReason[file]?.let { " <- $it" } ?: ""
            icLogLines.add("  ${file.relativeOrCanonical()}$reason")
        }
        recompilationReason.clear()
    }

    override fun reportMarkDirty(affectedFiles: Iterable<File>, reason: String) {
        affectedFiles.forEach { recompilationReason[it] = reason }
    }

    override fun flush() {
        if (reportMetrics) {
            compilationResults.add(CompilationResultCategory.BUILD_METRICS.code, metricsReporter.getMetrics())
        }

        compilationResults.add(CompilationResultCategory.BUILD_REPORT_LINES.code, icLogLines)
    }
}