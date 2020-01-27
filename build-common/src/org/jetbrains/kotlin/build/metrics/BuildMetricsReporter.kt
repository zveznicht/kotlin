/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.build.metrics

import java.util.EnumMap

interface BuildMetricsReporter {
    fun startMeasure(metric: BuildTime, startNs: Long)
    fun endMeasure(metric: BuildTime, endNs: Long)

    fun reportAttribute(metric: BuildAttribute, value: String)

    fun getMetrics(): BuildMetrics
    fun addMetrics(metrics: BuildMetrics?)

    object DoNothing : BuildMetricsReporter {
        override fun startMeasure(metric: BuildTime, startNs: Long) {
        }

        override fun endMeasure(metric: BuildTime, endNs: Long) {
        }

        override fun reportAttribute(metric: BuildAttribute, value: String) {
        }

        override fun getMetrics(): BuildMetrics = BuildMetrics(emptyMap(), emptyMap())
        override fun addMetrics(metrics: BuildMetrics?) {}
    }
}

class BuildMetricsReporterImpl : BuildMetricsReporter {
    private val myBuildTimeStartNs: EnumMap<BuildTime, Long> = EnumMap(BuildTime::class.java)
    private val myBuildTimes: EnumMap<BuildTime, Long> = EnumMap(BuildTime::class.java)
    private val myBuildAttributes: EnumMap<BuildAttribute, String> = EnumMap(BuildAttribute::class.java)

    override fun startMeasure(metric: BuildTime, startNs: Long) {
        if (metric in myBuildTimeStartNs) {
            error("$metric was restarted before it finished")
        }
        myBuildTimeStartNs[metric] = startNs
    }

    override fun endMeasure(metric: BuildTime, endNs: Long) {
        val startNs = myBuildTimeStartNs.remove(metric) ?: error("$metric finished before it started")
        val durationNs = endNs - startNs
        myBuildTimes[metric] = myBuildTimes.getOrDefault(metric, 0) + durationNs
    }

    override fun reportAttribute(metric: BuildAttribute, value: String) {
        myBuildAttributes[metric] = value
    }

    override fun getMetrics(): BuildMetrics =
        BuildMetrics(buildTimes = myBuildTimes, buildAttributes = myBuildAttributes)

    override fun addMetrics(metrics: BuildMetrics?) {
        if (metrics == null) return

        myBuildAttributes.putAll(metrics.buildAttributes)
        myBuildTimes.putAll(metrics.buildTimes)
    }
}

inline fun <T> BuildMetricsReporter.measure(metric: BuildTime, fn: () -> T): T {
    val start = System.nanoTime()
    startMeasure(metric, start)

    try {
        return fn()
    } finally {
        val end = System.nanoTime()
        endMeasure(metric, end)
    }
}