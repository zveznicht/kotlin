package benchmarks.scenarios

import benchmarks.dsl.TargetFile
import benchmarks.dsl.Tasks
import benchmarks.dsl.TypeOfChange
import benchmarks.dsl.suite

val defaultBenchmarks =
    suite {
        // todo: use dist or ideaPlugin
        defaultTasks(Tasks.CORE_UTIL_CLASSES)

        scenario("clean build") {
            step {
                doNotMeasure()
                runTasks(Tasks.CLEAN)
            }
            step {}
        }

        scenario("add private function") {
            step {
                changeFile(TargetFile.CORE_UTIL_STRINGS, TypeOfChange.ADD_PRIVATE_FUNCTION)
            }
        }

        scenario("add public function") {
            step {
                changeFile(TargetFile.CORE_UTIL_STRINGS, TypeOfChange.ADD_PUBLIC_FUNCTION)
            }
        }

        scenario("add private class") {
            step {
                changeFile(TargetFile.CORE_UTIL_STRINGS, TypeOfChange.ADD_PRIVATE_CLASS)
            }
        }

        scenario("add public class") {
            step {
                changeFile(TargetFile.CORE_UTIL_STRINGS, TypeOfChange.ADD_PUBLIC_CLASS)
            }
        }

        scenario("build after error") {
            step {
                doNotMeasure()
                changeFile(TargetFile.CORE_UTIL_STRINGS, TypeOfChange.INTRODUCE_COMPILE_ERROR)
            }
            revertLastStep()
        }
    }