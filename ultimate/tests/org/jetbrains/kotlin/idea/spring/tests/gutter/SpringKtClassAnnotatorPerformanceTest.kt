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
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.testFramework.PlatformTestUtil
import org.jetbrains.kotlin.idea.spring.tests.SpringKtLightHighlightingTestCase

class SpringKtClassAnnotatorPerformanceTest : SpringKtLightHighlightingTestCase() {


    fun testKtComponentScan() {
        val beansCount = 50

        myFixture.configureByText("Config.kt", """
        package pkg;

        @org.springframework.context.annotation.Configuration
        @org.springframework.context.annotation.ComponentScan
        open class Config {
        <caret>

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

        configureFileSet("Config.kt")
        PsiDocumentManager.getInstance(project).commitAllDocuments()

        PlatformTestUtil.startPerformanceTest("do Highlighting", 2800
        ) {
            myFixture.testHighlighting("Config.kt")

            val allGutters = myFixture.findAllGutters("Config.kt")
            println("count = " + allGutters.size)
//            TestCase.assertEquals(103, allGutters.size)
            val guttersMapping = allGutters.map {
                (it as LineMarkerInfo.LineMarkerGutterIconRenderer<*>).lineMarkerInfo.let {
                    it.element?.text.toString() to it.lineMarkerTooltip
                }
            }
            println("guttersMapping = " + guttersMapping)
        }.setup {

            val modCount = myFixture.psiManager.modificationTracker.outOfCodeBlockModificationCount
            println("modCount = " + modCount)
            WriteCommandAction.runWriteCommandAction(project) {
                val document = myFixture.editor.document
                document.insertString(myFixture.caretOffset, "fun foo$modCount(){}\n")
            }
            PsiDocumentManager.getInstance(project).commitAllDocuments()
            FileDocumentManager.getInstance().saveAllDocuments()

        }.attempts(5).assertTiming()

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

        configureFileSet("pkg/Config.java")

        PlatformTestUtil.startPerformanceTest("do Highlighting", 2800
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
        }.attempts(3).assertTiming()

    }


}

class SpringKtClassAnnotatorPTest : SpringKtLightHighlightingTestCase() {
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

        configureFileSet("Config.kt")


        myFixture.testHighlighting("Config.kt")

        val allGutters = myFixture.findAllGutters("Config.kt")
        println("count = " + allGutters.size)
        val guttersMapping = allGutters.map {
            (it as LineMarkerInfo.LineMarkerGutterIconRenderer<*>).lineMarkerInfo.let {
                it.element?.text.toString() to it.lineMarkerTooltip
            }
        }
        println("guttersMapping = " + guttersMapping)


    }

}