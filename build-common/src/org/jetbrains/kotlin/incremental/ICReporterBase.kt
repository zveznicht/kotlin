/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental

import org.jetbrains.kotlin.build.metrics.BuildAttribute
import org.jetbrains.kotlin.build.metrics.BuildMetricsReporter
import org.jetbrains.kotlin.build.metrics.BuildTime
import java.io.File

abstract class ICReporterBase(private val pathsBase: File? = null) : ICReporter {
    override fun reportMarkDirtyClass(affectedFiles: Iterable<File>, classFqName: String) {
        reportMarkDirty(affectedFiles, "dirty class $classFqName")
    }

    override fun reportMarkDirtyMember(affectedFiles: Iterable<File>, scope: String, name: String) {
        reportMarkDirty(affectedFiles, "dirty member $scope#$name")
    }

    override fun reportMarkDirty(affectedFiles: Iterable<File>, reason: String) {
        affectedFiles.forEach { file ->
            reportVerbose { "${pathsAsString(file)} is marked dirty: $reason" }
        }
    }

    protected fun relativizeIfPossible(files: Iterable<File>): List<File> =
        files.map { it.relativeOrCanonical() }

    protected fun pathsAsString(files: Iterable<File>): String =
        relativizeIfPossible(files).map { it.path }.sorted().joinToString()

    protected fun pathsAsString(vararg files: File): String =
        pathsAsString(files.toList())

    protected fun File.relativeOrCanonical(): File =
        pathsBase?.let { relativeToOrNull(it) } ?: canonicalFile

    protected open val metricsReporter: BuildMetricsReporter =
        BuildMetricsReporter.DoNothing

    override fun startMeasure(metric: BuildTime, startNs: Long) {
        metricsReporter.startMeasure(metric, startNs)
    }

    override fun endMeasure(metric: BuildTime, endNs: Long) {
        metricsReporter.endMeasure(metric, endNs)
    }

    override fun reportAttribute(metric: BuildAttribute, value: String) {
        metricsReporter.reportAttribute(metric, value)
    }
}