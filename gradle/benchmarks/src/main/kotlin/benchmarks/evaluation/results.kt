package benchmarks.evaluation

import benchmarks.dsl.*

class ScenarioResult(val stepResults: List<StepResult>)

class StepResult(
    val step: Step,
    val upToDateChecksTimeMs: Long = -1,
    val configTimeMs: Long = -1,
    val taskExecutionMs: Long = -1,
    val allBuildTimeMs: Long = -1,
    val buildFailure: Exception? = null,
    val metrics: Map<String, Long> = emptyMap()
)

open class ResultsListener {
    open fun scenarioStarted(scenario: Scenario) {}
    open fun scenarioFinished(scenario: Scenario, result: ScenarioResult) {}
    open fun stepStarted(step: Step) {}
    open fun stepFinished(step: Step, result: StepResult) {}
}