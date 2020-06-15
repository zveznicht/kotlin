/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package coroutines

import org.junit.After
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.jvm.internal.runSuspend

actual abstract class CoroutinesTestBase {
    private var actionIndex = AtomicInteger()
    private var finished = AtomicBoolean()

    @After
    fun after() {
        if (actionIndex.get() != 0 && !finished.get()) {
            throw IllegalStateException("Expecting that 'finish(${actionIndex.get() + 1})' was invoked, but it was not")
        }
    }

    actual fun runTest(block: suspend () -> Unit) = runSuspend(block)

    actual fun expect(index: Int) {
        val wasIndex = actionIndex.incrementAndGet()
        check(index == wasIndex) { "Expecting action index $index but it is actually $wasIndex" }
    }

    actual fun finish(index: Int) {
        expect(index)
        check(!finished.getAndSet(true)) { "Should call 'finish(...)' at most once" }
    }
}