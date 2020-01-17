/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.statistics

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.intellij.util.concurrency.AppExecutorUtil
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import org.jetbrains.kotlin.statistics.BuildSessionLogger.Companion.STATISTICS_FILE_NAME_PATTERN
import org.jetbrains.kotlin.statistics.BuildSessionLogger.Companion.STATISTICS_FOLDER_NAME
import org.jetbrains.kotlin.statistics.fileloggers.MetricsContainer
import org.jetbrains.kotlin.statistics.metrics.BooleanMetrics
import org.jetbrains.kotlin.statistics.metrics.NumericalMetrics
import org.jetbrains.kotlin.statistics.metrics.StringMetrics

class KotlinGradleFUSLogger : StartupActivity, DumbAware, Runnable {

    override fun runActivity(project: Project) {
        AppExecutorUtil.getAppScheduledExecutorService()
            .scheduleWithFixedDelay(this, EXECUTION_DELAY_MIN, EXECUTION_DELAY_MIN, TimeUnit.MINUTES)
    }

    override fun run() {
        reportStatistics()
    }

    companion object {

        /**
         * Maximum amount of directories which were reported as gradle user dirs
         * These directories should be monitored for reported gradle statistics.
         */
        const val MAXIMUM_USER_DIRS = 10

        /**
         * Delay between sequential checks of gradle statistics
         */
        const val EXECUTION_DELAY_MIN = 60L

        /**
         * Property name used for persisting gradle user dirs
         */
        private const val GRADLE_USER_DIRS_PROPERTY_NAME = "kotlin-gradle-user-dirs"

        private val isRunning = AtomicBoolean(false)

        private fun MetricsContainer.log(group: FUSEventGroups, vararg metrics: Any) {
            val data = HashMap<String, String>()
            fun putIfNotNull(key: String, value: String?) {
                if (value != null) {
                    data[key] = value
                }
            }

            for (metric in metrics) {
                when (metric) {
                    is BooleanMetrics -> putIfNotNull(metric.name, this.getMetric(metric)?.toStringRepresentation())
                    is StringMetrics -> putIfNotNull(metric.name, this.getMetric(metric)?.toStringRepresentation())
                    is NumericalMetrics -> putIfNotNull(metric.name, this.getMetric(metric)?.toStringRepresentation())
                    is Pair<*, *> -> putIfNotNull(metric.first.toString(), metric.second.toString())
                }
            }
            KotlinFUSLogger.log(group, group.name, data)
        }

        private fun processMetricsContainer(container: MetricsContainer, previous: MetricsContainer?) {
            container.log(
                FUSEventGroups.GradleEnvironment,
                StringMetrics.OS_TYPE,
                NumericalMetrics.CPU_NUMBER_OF_CORES,
                StringMetrics.GRADLE_VERSION,
                NumericalMetrics.ARTIFACTS_DOWNLOAD_SPEED,
                StringMetrics.IDES_INSTALLED,
                BooleanMetrics.EXECUTED_FROM_IDEA
            )
            container.log(
                FUSEventGroups.GradleKapt,
                BooleanMetrics.ENABLED_KAPT,
                BooleanMetrics.ENABLED_DAGGER,
                BooleanMetrics.ENABLED_DATABINDING
            )

            container.log(
                FUSEventGroups.GradleCompilerPlugins,
                BooleanMetrics.ENABLED_COMPILER_PLUGIN_ALL_OPEN,
                BooleanMetrics.ENABLED_COMPILER_PLUGIN_NO_ARG,
                BooleanMetrics.ENABLED_COMPILER_PLUGIN_JPA_SUPPORT,
                BooleanMetrics.ENABLED_COMPILER_PLUGIN_SAM_WITH_RECEIVER
            )

            container.log(
                FUSEventGroups.GradleMPP,
                StringMetrics.MPP_PLATFORMS,
                BooleanMetrics.ENABLED_HMPP
            )

            container.log(
                FUSEventGroups.GradleLibraries,
                StringMetrics.LIBRARY_SPRING_VERSION,
                StringMetrics.LIBRARY_VAADIN_VERSION,
                StringMetrics.LIBRARY_GWT_VERSION,
                StringMetrics.LIBRARY_HYBERNATE_VERSION
            )

            container.log(
                FUSEventGroups.GradleGradleConfiguration,
                NumericalMetrics.GRADLE_DAEMON_HEAP_SIZE,
                NumericalMetrics.GRADLE_BUILD_NUMBER_IN_CURRENT_DAEMON,
                NumericalMetrics.CONFIGURATION_API_COUNT,
                NumericalMetrics.CONFIGURATION_IMPLEMENTATION_COUNT,
                NumericalMetrics.CONFIGURATION_COMPILE_COUNT,
                NumericalMetrics.CONFIGURATION_RUNTIME_COUNT,
                NumericalMetrics.GRADLE_NUMBER_OF_TASKS,
                NumericalMetrics.GRADLE_NUMBER_OF_UNCONFIGURED_TASKS,
                NumericalMetrics.GRADLE_NUMBER_OF_INCREMENTAL_TASKS
            )

            container.log(
                FUSEventGroups.GradleComponentVersions,
                StringMetrics.KOTLIN_COMPILER_VERSION,
                StringMetrics.KOTLIN_STDLIB_VERSION,
                StringMetrics.KOTLIN_REFLECT_VERSION,
                StringMetrics.KOTLIN_COROUTINES_VERSION,
                StringMetrics.KOTLIN_SERIALIZATION_VERSION,
                StringMetrics.ANDROID_GRADLE_PLUGIN_VERSION
            )

            container.log(
                FUSEventGroups.GradleKotlinFeatures,
                StringMetrics.KOTLIN_LANGUAGE_VERSION,
                StringMetrics.KOTLIN_API_VERSION,
                BooleanMetrics.BUILD_SRC_EXISTS,
                NumericalMetrics.BUILD_SRC_COUNT,
                BooleanMetrics.GRADLE_BUILD_CACHE_USED,
                BooleanMetrics.GRADLE_WORKER_API_USED,
                BooleanMetrics.KOTLIN_OFFICIAL_CODESTYLE,
                BooleanMetrics.KOTLIN_PROGRESSIVE_MODE,
                BooleanMetrics.KOTLIN_KTS_USED
            )

            container.log(
                FUSEventGroups.GradleGradlePerformance,
                NumericalMetrics.GRADLE_BUILD_DURATION,
                NumericalMetrics.GRADLE_EXECUTION_DURATION,
                NumericalMetrics.NUMBER_OF_SUBPROJECTS
            )

            val finishTime = container.getMetric(NumericalMetrics.BUILD_FINISH_TIME)?.getValue()
            val prevFinishTime = previous?.getMetric(NumericalMetrics.BUILD_FINISH_TIME)?.getValue()

            val betweenBuilds = if (finishTime != null && prevFinishTime != null) finishTime - prevFinishTime else null
            container.log(
                FUSEventGroups.GradleUserScenarios,
                Pair(NumericalMetrics.BUILD_FINISH_TIME.name, betweenBuilds),
                BooleanMetrics.DEBUGGER_ENABLED,
                BooleanMetrics.COMPILATION_STARTED,
                BooleanMetrics.TESTS_EXECUTED,
                BooleanMetrics.MAVEN_PUBLISH_EXECUTED,
                BooleanMetrics.BUILD_FAILED
            )
        }

        fun reportStatistics() {
            if (isRunning.weakCompareAndSet(false, true)) {
                try {
                    for (gradleUserHome in getGradleUserDirs()) {
                        File(gradleUserHome, STATISTICS_FOLDER_NAME).listFiles()?.filter {
                            it?.name?.matches(STATISTICS_FILE_NAME_PATTERN.toRegex()) ?: false
                        }?.forEach { statisticFile ->
                            try {
                                var previousEvent: MetricsContainer? = null
                                MetricsContainer.readFromFile(statisticFile) { metricContainer ->
                                    processMetricsContainer(metricContainer, previousEvent)
                                    previousEvent = metricContainer
                                }
                            } catch (e: Exception) {
                                Logger.getInstance(KotlinFUSLogger::class.java)
                                    .info("Failed to process file ${statisticFile.absolutePath}: ${e.message}", e)
                            } finally {
                                if (!statisticFile.delete()) {
                                    Logger.getInstance(KotlinFUSLogger::class.java)
                                        .warn("[FUS] Failed to delete file ${statisticFile.absolutePath}")
                                }
                            }
                        }
                    }
                } finally {
                    isRunning.set(false)
                }
            }
        }

        private fun getGradleUserDirs(): Array<String> {
            return PropertiesComponent.getInstance().getValues(GRADLE_USER_DIRS_PROPERTY_NAME) ?: emptyArray()
        }

        fun populateGradleUserDir(path: String) {
            val currentState = getGradleUserDirs()
            if (currentState.contains(path)) {
                return
            }

            val result = ArrayList<String>()
            result.add(path)
            result.addAll(currentState)

            PropertiesComponent.getInstance().setValues(
                GRADLE_USER_DIRS_PROPERTY_NAME,
                result.filter { path -> File(path).exists() }.filterIndexed { i, _ -> i < MAXIMUM_USER_DIRS }.toTypedArray()
            )
        }
    }
}