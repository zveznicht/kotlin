/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.build.report.metrics

import org.jetbrains.kotlin.build.ExecutionStrategy
import java.util.*

class BuildMetricsReporterImpl : BuildMetricsReporter {
    private val myBuildTimeStartNs: EnumMap<BuildTime, Long> =
        EnumMap(
            BuildTime::class.java
        )
    private val myBuildTimes = BuildTimes()
    private val myBuildAttributes = BuildAttributes()

    override fun startMeasure(metric: BuildTime, startNs: Long) {
        if (metric in myBuildTimeStartNs) {
            error("$metric was restarted before it finished")
        }
        myBuildTimeStartNs[metric] = startNs
    }

    override fun endMeasure(metric: BuildTime, endNs: Long) {
        val startNs = myBuildTimeStartNs.remove(metric) ?: error("$metric finished before it started")
        val durationNs = endNs - startNs
        myBuildTimes.add(metric, durationNs)
    }

    override fun reportRebuildReason(rebuildReason: RebuildReason) {
        myBuildAttributes.rebuild(rebuildReason)
    }

    override fun reportExecutionStrategy(executionStrategy: ExecutionStrategy) {
        myBuildAttributes.executionStrategy(executionStrategy)
    }

    override fun getMetrics(): BuildMetrics =
        BuildMetrics(
            buildTimes = myBuildTimes,
            buildAttributes = myBuildAttributes
        )

    override fun addMetrics(metrics: BuildMetrics?) {
        if (metrics == null) return

        myBuildAttributes.addAll(metrics.buildAttributes)
        myBuildTimes.addAll(metrics.buildTimes)
    }
}