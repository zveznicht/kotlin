/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.spring.tests.gutter

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.psi.PsiElement
import com.intellij.spring.model.utils.SpringModelSearchers
import com.intellij.testFramework.PlatformTestUtil
import com.intellij.testFramework.UsefulTestCase
import org.jetbrains.kotlin.idea.codeInsight.generate.AbstractCodeInsightActionTest
import org.jetbrains.kotlin.idea.spring.tests.SpringTestFixtureExtension
import org.jetbrains.kotlin.idea.test.TestFixtureExtension

class SpringKtClassAnnotatorPerformanceTest : AbstractCodeInsightActionTest() {

    override fun setUp() {
        super.setUp()
        TestFixtureExtension.loadFixture<SpringTestFixtureExtension>(myModule)
    }

    fun testKtComponentScan() {
        val beansCount = 50

        myFixture.configureByText("Config.kt", """
        package pkg;

        @org.springframework.context.annotation.Configuration
        @org.springframework.context.annotation.ComponentScan
        open class Config {

       ${(0..beansCount).joinToString("\n") {
            """
                @org.springframework.context.annotation.Bean
                open fun localBean$it() = LocalBean$it()
                """.trimIndent()
        }}
        }

        ${(0..beansCount).joinToString("\n") {
            "class LocalBean$it"
        }}

    """)

        TestFixtureExtension.getFixture<SpringTestFixtureExtension>()!!.configureFileSet(myFixture, listOf("Config.kt"))

        PlatformTestUtil.startPerformanceTest("do Highlighting", 2800
        ) {
            myFixture.testHighlighting("Config.kt")

            val allGutters = myFixture.findAllGutters("Config.kt")
            println("count = " + allGutters.size)
            val guttersMapping = allGutters.map {
                (it as LineMarkerInfo.LineMarkerGutterIconRenderer<*>).lineMarkerInfo.let {
                    it.element?.text.toString() to it.lineMarkerTooltip
                }
            }
            println("guttersMapping = " + guttersMapping)
        }.attempts(2).assertTiming()

    }

    fun testKtComponentScanJava() {
        val beansCount = 50

        val addClass = myFixture.addClass("""
        package pkg;

        @org.springframework.context.annotation.Configuration
        @org.springframework.context.annotation.ComponentScan
        public class Config {

       ${(0..beansCount).joinToString("\n") {
            """
                @org.springframework.context.annotation.Bean
                LocalBean$it localBean$it(){
                 return new LocalBean$it();
                }
                """.trimIndent()
        }}
        }

        ${(0..beansCount).joinToString("\n") {
            "class LocalBean$it {}"
        }}

    """)

        println("addClass = " + addClass.containingFile.virtualFile.url)

        TestFixtureExtension.getFixture<SpringTestFixtureExtension>()!!.configureFileSet(myFixture, listOf("pkg/Config.java"))

        PlatformTestUtil.startPerformanceTest("do Highlighting", 1000
        ) {
            myFixture.testHighlighting("pkg/Config.java")

            val allGutters = myFixture.findAllGutters("pkg/Config.java")
            println("count = " + allGutters.size)
            val guttersMapping = allGutters.map {
                (it as LineMarkerInfo.LineMarkerGutterIconRenderer<*>).lineMarkerInfo.let {
                    it.element?.text.toString() to it.lineMarkerTooltip
                }
            }
            println("guttersMapping = " + guttersMapping)
        }.attempts(2).assertTiming()

    }


    override fun tearDown() {
        TestFixtureExtension.unloadFixture<SpringTestFixtureExtension>()
        super.tearDown()
    }
}