/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.jvm.compiler

import org.jetbrains.kotlin.checkers.AbstractDiagnosticsTest
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl
import org.jetbrains.kotlin.test.MockLibraryUtil
import java.io.File
import java.util.regex.Pattern

abstract class AbstractJspecifyAnnotationsTest : AbstractDiagnosticsTest() {
    override fun doMultiFileTest(
        wholeFile: File,
        files: List<TestFile>
    ) {
        super.doMultiFileTest(
            wholeFile,
            files,
            MockLibraryUtil.compileJavaFilesLibraryToJar(FOREIGN_ANNOTATIONS_SOURCES_PATH, "foreign-annotations")
        )
    }

    override fun doTest(filePath: String) {
        val ktSourceCode = File(filePath).readText()
        val javaSourcesFilename = javaSourcesPathRegex.matcher(ktSourceCode).also { it.find() }.group(1)
            ?: throw Exception("Java sources' path not found")
        val javaSourcesFile = File("$JSPECIFY_JAVA_SOURCES_PATH/$javaSourcesFilename")
        val mergedSourceCode = buildString {
            appendLine("// ORIGINAL_KT_FILE: $filePath")

            if (javaSourcesFilename.endsWith(".java")) {
                appendLine("// FILE: ${javaSourcesFile.name}")
                appendLine(javaSourcesFile.readText())
            } else {
                assert(javaSourcesFile.isDirectory) { "Specified Java sources should be a file with `java` extension or directory" }
                for (javaFile in javaSourcesFile.walkTopDown().filter { it.isFile && it.extension == "java" }) {
                    appendLine("// FILE: ${javaFile.name}")
                    appendLine(javaFile.readText())
                }
            }
            appendLine("// FILE: main.kt\n$ktSourceCode")
        }

        super.doTest(createTempFile().apply { writeText(mergedSourceCode) }.absolutePath)
    }

    override fun checkDiagnostics(actualText: String, testDataFile: File) {
        val originalKtFilePath = originalKtFileRegex.matcher(actualText).also { it.find() }.group(1)
            ?: throw Exception("Path for original kt file in the merged file isn't found")
        val actualKtSourceCode = actualText.substringAfter(MAIN_KT_FILE_DIRECTIVE)

        super.checkDiagnostics(actualKtSourceCode, File(originalKtFilePath))
    }

    override fun validateAndCompareDescriptorWithFile(
        testDataFile: File,
        testFiles: List<TestFile>,
        modules: Map<TestModule?, ModuleDescriptorImpl>,
        coroutinesPackage: String
    ) {
        val originalKtFilePath = originalKtFileRegex.matcher(testDataFile.readText()).also { it.find() }.group(1)
            ?: throw Exception("Path for original kt file in the merged file isn't found")

        super.validateAndCompareDescriptorWithFile(File(originalKtFilePath), testFiles, modules, coroutinesPackage)
    }

    companion object {
        const val FOREIGN_ANNOTATIONS_SOURCES_PATH = "third-party/jdk8-annotations"
        const val JSPECIFY_JAVA_SOURCES_PATH = "compiler/testData/foreignAnnotationsJava8/tests/jspecify/java"
        const val MAIN_KT_FILE_DIRECTIVE = "// FILE: main.kt\n"

        private val originalKtFileRegex = Pattern.compile("""// ORIGINAL_KT_FILE: (.*?\.kts?)\n""")
        private val javaSourcesPathRegex = Pattern.compile("""// JAVA_SOURCES: (.*?(?:\.java)?)\n""")
    }
}
