/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.checkers

import com.intellij.testFramework.TestDataPath
import org.jetbrains.kotlin.idea.test.withCustomCompilerOptions
import org.jetbrains.kotlin.nj2k.AbstractNewJavaToKotlinConverterSingleFileTest
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners
import org.jetbrains.kotlin.test.KotlinTestUtils
import org.jetbrains.kotlin.test.TestMetadata
import org.jetbrains.kotlin.test.util.addDependency
import org.junit.runner.RunWith
import java.io.File

@TestMetadata("compiler/testData/foreignAnnotationsJava8/tests/jspecify/java")
@TestDataPath("\$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners::class)
class JspecifyJavaToKotlinUseSiteConsistencyTest : AbstractNewJavaToKotlinConverterSingleFileTest() {
    override fun provideExpectedFile(javaPath: String): File {
        val kotlinPath = javaPath
            .replace(".java", ".kt")
            .replace("/jspecify/java/", "/jspecify/kotlin/")
        return File(kotlinPath)
    }

    override fun doTest(javaPath: String) {
//        this.myFixture.addFileToProject("org/jspecify/annotations/DefaultNonNull.java", File("third-party/jdk8-annotations/org/jspecify/annotations/DefaultNonNull.java").readText())
        super.doTest(javaPath)
    }

    fun runTest(testDataFilePath: String) {
        KotlinTestUtils.runTest(::doTest, this, testDataFilePath)
    }

    @TestMetadata("WildcardsWithDefault.java")
    fun test() {
        runTest("compiler/testData/foreignAnnotationsJava8/tests/jspecify/java/WildcardsWithDefault.java")
    }
}
