/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package benchmarks.dsl

fun suite(fn: SuiteBuilder.() -> Unit): Suite =
    SuiteBuilderImpl().apply(fn).build()

interface SuiteBuilder {
    fun scenario(name: String, fn: ScenarioBuilder.() -> Unit)
    fun defaultTasks(vararg tasks: Tasks)
}

interface ScenarioBuilder {
    fun step(fn: StepBuilder.() -> Unit)
    fun revertLastStep()
}

interface StepBuilder {
    fun doNotMeasure()
    fun changeFile(targetFile: TargetFile, typeOfChange: TypeOfChange)
    fun runTasks(vararg tasksToRun: Tasks)
}

class SuiteBuilderImpl : SuiteBuilder {
    private var defaultTasks = arrayOf<Tasks>()
    private val scenarios = arrayListOf<Scenario>()

    override fun scenario(name: String, fn: ScenarioBuilder.() -> Unit) {
        scenarios.add(ScenarioBuilderImpl(name = name).apply(fn).build())
    }

    override fun defaultTasks(vararg tasks: Tasks) {
        defaultTasks = arrayOf(*tasks)
    }

    fun build() =
        Suite(scenarios = scenarios.toTypedArray(), defaultTasks = defaultTasks)
}

class ScenarioBuilderImpl(private val name: String) : ScenarioBuilder {
    private val steps = arrayListOf<Step>()

    override fun step(fn: StepBuilder.() -> Unit) {
        steps.add(StepBuilderImpl().apply(fn).build())
    }

    override fun revertLastStep() {
        // todo: revert
        steps.add(steps.last())
    }

    fun build() =
        Scenario(name = name, steps = steps.toTypedArray())
}

class StepBuilderImpl : StepBuilder {
    private var measure = false
    private val changes = arrayListOf<FileChange>()
    private var tasks: Array<Tasks>? = null

    override fun changeFile(targetFile: TargetFile, typeOfChange: TypeOfChange) {
        changes.add(FileChange(targetFile, typeOfChange))
    }

    override fun runTasks(vararg tasksToRun: Tasks) {
        this.tasks = arrayOf(*tasksToRun)
    }

    override fun doNotMeasure() {
        measure = false
    }

    fun build() =
        Step(measure = measure, changes = changes.toTypedArray(), tasks = tasks)
}