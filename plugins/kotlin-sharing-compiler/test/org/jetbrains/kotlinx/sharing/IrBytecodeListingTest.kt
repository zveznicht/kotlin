/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing

import com.intellij.testFramework.TestDataPath
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners
import org.jetbrains.kotlin.test.KotlinTestUtils
import org.jetbrains.kotlin.test.KotlinTestUtils.DoTest
import org.jetbrains.kotlin.test.TestMetadata
import org.junit.runner.RunWith
import java.io.File
import java.util.regex.Pattern


// TODO: write generator

@TestMetadata("plugins/kotlin-sharing-compiler/testData/codegen")
@TestDataPath("\$PROJECT_ROOT")
@RunWith(
    JUnit3RunnerWithInners::class
)
class SharingIrBytecodeListingTestGenerated : AbstractSharingIrBytecodeListingTest() {
    @Throws(Exception::class)
    private fun runTest(testDataFilePath: String) {
        KotlinTestUtils.runTest(DoTest { filePath: String? -> this.doTest(filePath) }, this, testDataFilePath)
    }

    @Throws(Exception::class)
    fun testAllFilesPresentInCodegen() {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(
            this.javaClass,
            File("plugins/kotlin-sharing-compiler/testData/codegen"),
            Pattern.compile("^(.+)\\.kt$"),
            null,
            true
        )
    }

    @TestMetadata("Basic.kt")
    @Throws(Exception::class)
    fun testBasic() {
        runTest("plugins/kotlin-sharing-compiler/testData/codegen/Basic.kt")
    }
}