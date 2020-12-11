/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.perf

import com.intellij.testFramework.RunAll
import org.jetbrains.kotlin.idea.perf.util.TeamCity
import org.jetbrains.kotlin.idea.testFramework.ProjectOpenAction
import java.util.concurrent.atomic.AtomicLong

/**
 * This is basically a copy of [PerformanceProjectsTest] with some test cases omitted.
 */
class HighLevelPerformanceProjectsTest : AbstractPerformanceProjectsTest() {
    companion object {

        @JvmStatic
        val hwStats: Stats = Stats("fir helloWorld project")

        @JvmStatic
        val warmUp = WarmUpProject(hwStats)

        @JvmStatic
        val timer: AtomicLong = AtomicLong()

        fun resetTimestamp() {
            timer.set(0)
        }

        fun markTimestamp() {
            timer.set(System.nanoTime())
        }
    }

    override fun setUp() {
        super.setUp()
        warmUp.warmUp(this)
    }

    override fun tearDown() {
        RunAll.runAll({ super.tearDown() })
    }

    fun testHelloWorldProject() {
        TeamCity.suite("fir Hello world project") {
            myProject = perfOpenProject(stats = hwStats) {
                name("fir-helloKotlin")

                kotlinFile("HelloMain") {
                    topFunction("main") {
                        param("args", "Array<String>")
                        body("""println("Hello World!")""")
                    }
                }

                kotlinFile("HelloMain2") {
                    topFunction("main") {
                        param("args", "Array<String>")
                        body("""println("Hello World!")""")
                    }
                }
            }

            // highlight
            perfHighlightFile("src/HelloMain.kt", hwStats)
            perfHighlightFile("src/HelloMain2.kt", hwStats)
        }
    }

    fun testKotlinProject() {
        TeamCity.suite("fir Kotlin project") {
            Stats("fir kotlin project").use {
                perfOpenKotlinProject(it)

                val filesToHighlight = arrayOf(
//                    "idea/idea-analysis/src/org/jetbrains/kotlin/idea/util/PsiPrecedences.kt",
                    "compiler/psi/src/org/jetbrains/kotlin/psi/KtElement.kt",
//                    "compiler/psi/src/org/jetbrains/kotlin/psi/KtFile.kt",
//                    "core/builtins/native/kotlin/Primitives.kt",
//
//                    "compiler/frontend/src/org/jetbrains/kotlin/cfg/ControlFlowProcessor.kt",
//                    "compiler/frontend/src/org/jetbrains/kotlin/cfg/ControlFlowInformationProvider.kt",
//
//                    "compiler/backend/src/org/jetbrains/kotlin/codegen/state/KotlinTypeMapper.kt",
//                    "compiler/backend/src/org/jetbrains/kotlin/codegen/inline/MethodInliner.kt"
                )

                filesToHighlight.forEach { file -> perfHighlightFile(file, stats = it) }
                filesToHighlight.forEach { file -> perfHighlightFileEmptyProfile(file, stats = it) }

                perfTypeAndHighlight(
                    it,
                    "compiler/psi/src/org/jetbrains/kotlin/psi/KtFile.kt",
                    "override fun getDeclarations(): List<KtDeclaration> {",
                    "val q = import",
                    note = "in-method getDeclarations-import"
                )

                perfTypeAndHighlight(
                    it,
                    "compiler/psi/src/org/jetbrains/kotlin/psi/KtFile.kt",
                    "override fun getDeclarations(): List<KtDeclaration> {",
                    "val q = import",
                    typeAfterMarker = false,
                    note = "out-of-method import"
                )
            }
        }
    }

    private fun perfOpenKotlinProjectFast(stats: Stats) = perfOpenKotlinProject(stats, fast = true)

    private fun perfOpenKotlinProject(stats: Stats, fast: Boolean = false) {
        myProject = perfOpenProject(
            name = "kotlin",
            stats = stats,
            note = "",
            path = "../perfTestProject",
            openAction = ProjectOpenAction.GRADLE_PROJECT,
            fast = fast
        )
    }
}