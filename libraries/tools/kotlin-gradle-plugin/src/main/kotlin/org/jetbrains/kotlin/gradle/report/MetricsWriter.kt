/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.report

import org.gradle.api.logging.Logger
import org.jetbrains.kotlin.build.report.metrics.BuildTime
import org.jetbrains.kotlin.build.report.metrics.BuildTimes
import org.jetbrains.kotlin.gradle.logging.kotlinDebug
import org.jetbrains.kotlin.gradle.report.data.BuildExecutionData
import org.jetbrains.kotlin.gradle.report.data.BuildExecutionDataProcessor
import java.io.File
import java.io.ObjectOutputStream

internal class MetricsWriter(
    private val outputFile: File,
    private val log: Logger
) : BuildExecutionDataProcessor {
    override fun process(build: BuildExecutionData) {
        try {
            // todo: use proto buf?
            outputFile.parentFile.mkdirs()
            outputFile.outputStream().buffered().use {
                val out = ObjectOutputStream(it)
                out.writeInt(BuildTime.values().size)
                BuildTime.values().forEach { time ->
                    out.writeUTF(time.name)
                    out.writeInt(time.ordinal)
                    val parentOrdinal = time.parent?.ordinal ?: -1
                    out.writeInt(parentOrdinal)
                }

                val buildTimes = build.aggregatedMetrics.buildTimes.asMap()
                out.writeInt(buildTimes.size)
                for ((bt, timeNs) in buildTimes) {
                    out.writeInt(bt.ordinal)
                    out.writeLong(timeNs)
                }

                val buildAttributesTypes = build.aggregatedMetrics.buildAttributes.asMap()
                out.writeInt(buildAttributesTypes.size)

                for ((baType, baValues) in buildAttributesTypes) {
                    out.writeUTF(baType.simpleName)
                    out.writeInt(baValues.size)
                    for ((value, count) in baValues) {
                        out.writeUTF(value.name)
                        out.writeInt(count)
                    }
                }
            }
        } catch (e: Exception) {
            log.kotlinDebug { "Could not write metrics to $outputFile: $e" }
        }
    }
}