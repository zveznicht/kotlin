/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package coroutines.cancellation

import coroutines.CoroutinesTestBase
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.concurrent.thread
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.cancellation.suspendCancellableCoroutine
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.fail

class SuspendCancellableCoroutineJvmTest : CoroutinesTestBase() {

    @Test
    fun testRegularResume() = runTest {
        val result = suspendCancellableCoroutine<Int> {
            expect(1)
            thread {
                Thread.sleep(100) // It would be nice to actually write infra for runBlocking here, but alas
                expect(2)
                it.resumeWith(Result.success(239))
            }
        }
        assertEquals(239, result)
        finish(3)
    }

    @Test
    fun testRegularResumeWithExceptionWithHandler() = runTest {
        assertFailsWith<IndexOutOfBoundsException> {
            suspendCancellableCoroutine<Int> {
                expect(1)
                it.invokeOnCancellation { fail() }
                thread {
                    Thread.sleep(100)
                    expect(2)
                    it.resumeWith(Result.failure(IndexOutOfBoundsException()))
                }
            }
        }
        finish(3)
    }

    @Test
    fun testCancelWithHandler() = runTest {
        assertFailsWith<CancellationException> {
            suspendCancellableCoroutine<Int> {
                expect(1)
                it.invokeOnCancellation {
                    assertTrue(it is CancellationException)
                    expect(3)
                }
                thread {
                    Thread.sleep(100)
                    expect(2)
                    it.cancel()
                }
            }
        }
        finish(4)
    }

    @Test
    fun testCancelWithCauseWithHandler() = runTest {
        assertFailsWith<ArithmeticException> {
            suspendCancellableCoroutine<Int> {
                expect(1)
                it.invokeOnCancellation {
                    assertTrue(it is ArithmeticException)
                    expect(3)
                }
                thread {
                    Thread.sleep(100)
                    expect(2)
                    it.cancel(ArithmeticException())
                }
            }
        }
        finish(4)
    }

    @Test
    fun testRegularResumeWithException() = runTest {
        assertFailsWith<IndexOutOfBoundsException> {
            suspendCancellableCoroutine<Int> {
                expect(1)
                thread {
                    Thread.sleep(100)
                    expect(2)
                    it.resumeWith(Result.failure(IndexOutOfBoundsException()))
                }
            }
        }
        finish(3)
    }

    @Test
    fun testCancel() = runTest {
        assertFailsWith<CancellationException> {
            suspendCancellableCoroutine<Int> {
                expect(1)
                thread {
                    Thread.sleep(100)
                    expect(2)
                    it.cancel()
                }
            }
        }
        finish(3)
    }

    @Test
    fun testCancelWithCause() = runTest {
        assertFailsWith<ArithmeticException> {
            suspendCancellableCoroutine<Int> {
                expect(1)
                thread {
                    Thread.sleep(100)
                    expect(2)
                    it.cancel(ArithmeticException())
                }
            }
        }
        finish(3)
    }
}