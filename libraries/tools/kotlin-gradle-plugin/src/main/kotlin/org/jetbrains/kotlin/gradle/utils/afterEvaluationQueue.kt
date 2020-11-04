/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.utils

import org.gradle.api.Project
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

private val androidPluginIds = listOf(
    "android", "com.android.application", "android-library", "com.android.library",
    "com.android.test", "com.android.feature", "com.android.dynamic-feature", "com.android.instantapp"
)

internal val Project.afterEvaluationQueue: AfterEvaluationQueue
    get() = this.convention.findByType(AfterEvaluationQueue::class.java)
        ?: AfterEvaluationQueue(this).also { queue -> this.convention.add(AfterEvaluationQueue::class.java, "evaluationQueue", queue) }

class AfterEvaluationQueue(private val project: Project) {
    enum class Stage {
        AfterEvaluation, PostProcessing
    }

    private val lock = ReentrantLock()

    private var wasExecuted = false

    private val queues = Stage.values().toList().associateWith { mutableListOf<Project.() -> Unit>() }

    fun schedule(stage: Stage = Stage.AfterEvaluation, task: Project.() -> Unit) = lock.withLock {
        if (wasExecuted) project.task()
        else queues.getValue(stage).add(task)
    }

    private fun processQueues() {
        wasExecuted = true
        Stage.values().forEach { stage ->
            val queue = queues.getValue(stage)
            queue.forEach { action -> project.action() }
            queue.clear()
        }
    }

    init {
        require(!project.state.executed) { "Evaluation Queue cannot be created after evaluation" }
        project.scheduleAfterEvaluation {
            processQueues()
        }
    }
}

private fun Project.scheduleAfterEvaluation(action: () -> Unit) {
    assert(!project.state.executed)
    val isDispatched = AtomicBoolean(false)
    androidPluginIds.forEach { androidPluginId ->
        pluginManager.withPlugin(androidPluginId) {
            if (!isDispatched.getAndSet(true)) {
                afterEvaluate { action() }
            }
        }
    }
    afterEvaluate {
        if (!isDispatched.getAndSet(true)) {
            action()
        }
    }
}
