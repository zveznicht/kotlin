/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

import benchmarks.dsl.*
import benchmarks.evaluation.*
import benchmarks.scenarios.*
import gradle.runBenchmarks

fun main() {
    // todo: better representation
    val listener = object : ResultsListener() {
        override fun scenarioStarted(scenario: Scenario) {
            println("Scenario '${scenario.name}' started")
        }

        override fun scenarioFinished(scenario: Scenario, result: ScenarioResult) {
            println("Scenario '${scenario.name}' finished")
            println()
        }

        override fun stepStarted(step: Step) {}

        override fun stepFinished(step: Step, result: StepResult) {
            println("Step results:")
            if (result.buildFailure != null) {
                println("  Build failed: ${result.buildFailure.stackTrace.joinToString("\n")}")
            } else {
                println("  All build time: ${result.allBuildTimeMs} ms")
                println("  Configuration time: ${result.configTimeMs} ms")
                println("  Task execution time: ${result.taskExecutionMs} ms")
                println("  Up-to-date checks time: ${result.upToDateChecksTimeMs} ms")
            }
        }
    }

    runBenchmarks(defaultBenchmarks, listener)
}
