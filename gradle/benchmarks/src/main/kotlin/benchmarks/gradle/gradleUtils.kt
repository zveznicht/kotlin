/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package gradle

import benchmarks.dsl.*
import benchmarks.evaluation.*
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProjectConnection
import org.gradle.tooling.events.FinishEvent
import org.gradle.tooling.events.ProgressEvent
import org.gradle.tooling.events.ProgressListener
import org.gradle.tooling.events.configuration.ProjectConfigurationFinishEvent
import org.gradle.tooling.events.task.TaskFinishEvent
import java.io.File
import java.util.ArrayDeque

// todo: run each scenario multiple times

fun runBenchmarks(benchmarks: Suite, resultsListener: ResultsListener) {
    val root = File(".").absoluteFile
    val c = GradleConnector.newConnector().forProjectDirectory(root).connect()
    val eval = BenchmarkEvaluator(c, benchmarks.defaultTasks)

    try {
        for (scenario in benchmarks.scenarios) {
            resultsListener.scenarioStarted(scenario)

            val stepResults = scenario.steps.map { step ->
                resultsListener.stepStarted(step)
                // todo: try/catch
                eval.runStep(step).also { stepResult ->
                    resultsListener.stepFinished(step, stepResult)
                }
            }
            resultsListener.scenarioFinished(scenario, ScenarioResult(stepResults))
            eval.revertAppliedChanges()
        }
    } finally {
        eval.revertAppliedChanges()
        c.close()
    }
}

class BenchmarkEvaluator(
    private val c: ProjectConnection,
    private val defaultTasks: Array<Tasks>
) {
    private val appliedChanges = ArrayDeque<FileChange>()

    fun revertAppliedChanges() {
        while (appliedChanges.isNotEmpty()) {
            try {
                appliedChanges.pollFirst().revert()
            } catch (e: Exception) {
                System.err.println("Failed to revert changes: $e")
            }
        }
    }

    fun runStep(step: Step): StepResult {
        for (change in step.changes) {
            try {
                change.apply()
                appliedChanges.addFirst(change)
            } catch (e: Exception) {
                System.err.println("Failed to apply changes: $e")
                return StepResult(step, buildFailure = e)
            }
        }
        val tasksPaths = (step.tasks ?: defaultTasks).map { it.path }.toTypedArray()
        val gradleBuildListener = BuildRecordingProgressListener()

        var buildFailure: Exception? = null
        try {
            c.newBuild().forTasks(*tasksPaths).addProgressListener(gradleBuildListener).run()
        } catch (e: Exception) {
            buildFailure = e
        }

        return StepResult(
            step,
            // todo: split inputs and outputs checks time
            upToDateChecksTimeMs = gradleBuildListener.snapshotTimeMs,
            configTimeMs = gradleBuildListener.configTimeMs,
            taskExecutionMs = gradleBuildListener.taskExecutionMs,
            allBuildTimeMs = gradleBuildListener.allBuildTimeMs,
            buildFailure = buildFailure
        )
    }

    private class BuildRecordingProgressListener : ProgressListener {
        private lateinit var firstEvent: ProgressEvent
        private lateinit var lastEvent: ProgressEvent
        private lateinit var lastProjectConfigurationFinishEvent: ProjectConfigurationFinishEvent

        var snapshotTimeMs = 0L
            private set

        val configTimeMs: Long
            get() = lastProjectConfigurationFinishEvent.eventTime - firstEvent.eventTime

        val taskExecutionMs: Long
            get() = lastEvent.eventTime - lastProjectConfigurationFinishEvent.eventTime

        val allBuildTimeMs: Long
            get() = lastEvent.eventTime - firstEvent.eventTime

        private var isFirst = true

        override fun statusChanged(event: ProgressEvent) {
            if (isFirst) {
                isFirst = false
                firstEvent = event
            }
            lastEvent = event

            if (event is ProjectConfigurationFinishEvent) {
                lastProjectConfigurationFinishEvent = event
            }
            if (event is FinishEvent) {
                if (event is TaskFinishEvent) {
                    event.result
                }

                lastEvent = event
                val result = event.result
                when {
                    event.displayName.startsWith("Snapshot ") -> {
                        snapshotTimeMs += (result.endTime - result.startTime)
                    }
                }
            }
        }
    }
}
