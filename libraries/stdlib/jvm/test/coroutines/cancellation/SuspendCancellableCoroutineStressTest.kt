/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package coroutines.cancellation

import coroutines.CoroutinesTestBase
import org.junit.After
import org.junit.Test
import java.util.concurrent.CancellationException
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.cancellation.suspendCancellableCoroutine
import kotlin.coroutines.jvm.internal.runSuspend
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class SuspendCancellableCoroutineStressTest : CoroutinesTestBase() {

    private val executor = Executors.newFixedThreadPool(1)
    private val iterations = 100_000
    private val invokedOnCancellation = AtomicInteger(0)
    private val cancelled = AtomicInteger(0)
    private val resumed = AtomicInteger(0)

    @After
    fun tearDown() {
        executor.shutdown()
        assertEquals(iterations, invokedOnCancellation.get())
        assertEquals(iterations, cancelled.get())
        assertEquals(iterations, resumed.get())
    }

    @Test
    fun testCancellation() {
        repeat(iterations) {
            assertFailsWith<CancellationException> {
                runSuspend {
                    suspendCancellableCoroutine {
                        it.invokeOnCancellation {
                            invokedOnCancellation.incrementAndGet()
                        }
                        executor.execute {
                            assertTrue(it.cancel())
                            cancelled.incrementAndGet()
                        }
                    }
                }
            }
            resumed.incrementAndGet()
        }
    }

    @Test
    fun testCancellationDifferentOrder() {
        repeat(iterations) {
            assertFailsWith<CancellationException> {
                runSuspend {
                    suspendCancellableCoroutine {
                        executor.execute {
                            assertTrue(it.cancel())
                            cancelled.incrementAndGet()
                        }
                        it.invokeOnCancellation {
                            invokedOnCancellation.incrementAndGet()
                        }
                    }
                }
            }
            resumed.incrementAndGet()
        }


    }
}