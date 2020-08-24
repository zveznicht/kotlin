/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.shortenRefs

import org.jetbrains.kotlin.AbstractImportsTest
import org.jetbrains.kotlin.idea.shortenRefs.FirShortenReferences
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.test.InTextDirectivesUtils
import java.io.File

abstract class AbstractFirShortenRefsTest : AbstractImportsTest() {
    private val enableFirTestDirective = "// ENABLE_FIR_TEST"
    private val enablePassedTestsAutomatically = false

    override fun doTest(file: KtFile): String? {
        val selectionModel = myFixture.editor.selectionModel
        if (!selectionModel.hasSelection()) error("No selection in input file")

        FirShortenReferences().process(
            file,
            selectionModel.selectionStart,
            selectionModel.selectionEnd
        )

        selectionModel.removeSelection()
        return null
    }

    protected fun doTestWithMuting(unused: String) {
        val testedFile = File(testPath())
        val afterFile = testedFile.resolveSibling(testedFile.name + ".after").takeIf { it.exists() }

        val testedFileText = testedFile.readText()
        val firTestEnabled = InTextDirectivesUtils.isDirectiveDefined(testedFileText, enableFirTestDirective)

        val testPassed = try {
            doTest(unused)

            true
        } catch (e: AssertionError) {
            if (firTestEnabled) throw e

            println("This test did not pass, but it is not supposed to (there are no '$enableFirTestDirective' directive)\n\n")
            e.printStackTrace()

            false
        }

        if (testPassed && !firTestEnabled) {
            if (enablePassedTestsAutomatically) {
                testedFile.prependLine(enableFirTestDirective)
                afterFile?.prependLine(enableFirTestDirective)
            }

            throw AssertionError("This test passed in fir, but is not enabled! Use '$enableFirTestDirective' directive to enable it!")
        }
    }

    override val nameCountToUseStarImportDefault: Int
        get() = Integer.MAX_VALUE
}

private fun File.prependLine(line: String) {
    val readText = readText()
    writeText("$line\n$readText")
}
