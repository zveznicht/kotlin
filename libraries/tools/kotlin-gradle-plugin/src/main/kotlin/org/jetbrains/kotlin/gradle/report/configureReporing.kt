/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.report

import org.gradle.api.invocation.Gradle
import org.jetbrains.kotlin.gradle.plugin.PropertiesProvider
import org.jetbrains.kotlin.gradle.report.data.BuildExecutionDataProcessor
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

internal fun configureReporing(gradle: Gradle) {
    val buildDataProcessors = ArrayList<BuildExecutionDataProcessor>()

    val rootProject = gradle.rootProject
    val properties = PropertiesProvider(rootProject)
    if (properties.buildReportEnabled) {
        configurePlainTextReportWriter(gradle, properties)?.let {
            buildDataProcessors.add(it)
        }
    }

    val metricsOutputFile = properties.metricsFilePath?.let { File(it) }
    if (metricsOutputFile != null) {
        buildDataProcessors.add(MetricsWriter(metricsOutputFile, rootProject.logger))
    }

    if (buildDataProcessors.isNotEmpty()) {
        val listener = BuildDataRecorder(gradle, buildDataProcessors)
        gradle.addBuildListener(listener)
    }
}

private fun configurePlainTextReportWriter(
    gradle: Gradle,
    properties: PropertiesProvider
): BuildExecutionDataProcessor? {
    val buildReportMode = if (properties.buildReportVerbose) BuildReportMode.VERBOSE else BuildReportMode.SIMPLE
    gradle.taskGraph.whenReady { graph ->
        graph.allTasks.asSequence()
            .filterIsInstance<AbstractKotlinCompile<*>>()
            .forEach { it.buildReportMode = buildReportMode }
    }

    val log = gradle.rootProject.logger
    val reportDir = properties.buildReportDir
        ?: gradle.rootProject.buildDir.resolve("reports/kotlin-build").apply { mkdirs() }
    if (reportDir.isFile) {
        log.error("Kotlin build report cannot be created: '$reportDir' is a file")
        return null
    }
    reportDir.mkdirs()
    val ts = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(Calendar.getInstance().time)
    val reportFile = reportDir.resolve("${gradle.rootProject.name}-build-$ts.txt")

    return PlainTextBuildReportWriter(
        outputFile = reportFile,
        printMetrics = properties.buildReportMetrics || buildReportMode == BuildReportMode.VERBOSE,
        log = log
    )
}
