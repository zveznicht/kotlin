/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea

import org.jetbrains.kotlin.idea.fir.low.level.api.element.builder.DuplicatedFirSourceElementsException
import org.jetbrains.kotlin.test.InTextDirectivesUtils
import java.io.File

fun Throwable.shouldBeRethrown(): Boolean = when (this) {
    is DuplicatedFirSourceElementsException -> true
    else -> false
}

const val FIR_IGNORE = "// FIR_IGNORE"
const val FIR_COMPARISON = "// FIR_COMPARISON"

/**
 * Set this flag to `true` to insert directive automatically to all files
 * that pass tests but do not already have the directive.
 */
private const val insertDirectiveAutomatically = false

fun runTestWithCustomEnableDirective(directive: String, testFile: File, test: () -> Unit) {
    val testFileAfter = testFile.resolveSibling(testFile.name + ".after").takeIf { it.exists() }

    val testEnabled = InTextDirectivesUtils.isDirectiveDefined(testFile.readText(), directive)

    try {
        test()
    } catch (e: Throwable) {
        if (testEnabled) throw e
        return
    }

    if (!testEnabled) {
        if (insertDirectiveAutomatically) {
            testFile.insertDirective(directive)
            testFileAfter?.insertDirective(directive)
        }

        throw AssertionError("Looks like test is passing, please add ${directive.removePrefix("// ")} at the beginning of the file")
    }
}

private fun File.insertDirective(directive: String) {
    val originalText = readText()
    writeText("$directive\n$originalText")
}