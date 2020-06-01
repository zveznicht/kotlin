/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package test.collections.guava

import junit.framework.*

open class GuavaBaseTest : TestListener {
    override fun addFailure(test: Test, e: AssertionFailedError) {
        println(e.stackTraceToString())
        throw e
    }

    override fun addError(test: Test, e: Throwable)  {
        println(e.stackTraceToString())
        throw e
    }

    override fun startTest(test: Test) { }

    override fun endTest(test: Test) { }

    fun runTestSuite(suite: TestSuite) {
        for (t in suite.tests()) {
            val r = TestResult()
            r.addListener(this)
            t.run(r)
        }
    }
}