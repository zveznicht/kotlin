/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.build.report.metrics

import org.jetbrains.kotlin.build.ExecutionStrategy

object DoNothingBuildMetricsReporter : BuildMetricsReporter {
    override fun startMeasure(metric: BuildTime, startNs: Long) {
    }

    override fun endMeasure(metric: BuildTime, endNs: Long) {
    }

    override fun reportRebuildReason(rebuildReason: RebuildReason) {
    }

    override fun reportExecutionStrategy(executionStrategy: ExecutionStrategy) {
    }

    override fun getMetrics(): BuildMetrics =
        BuildMetrics(
            BuildTimes(),
            BuildAttributes()
        )

    override fun addMetrics(metrics: BuildMetrics?) {}
}