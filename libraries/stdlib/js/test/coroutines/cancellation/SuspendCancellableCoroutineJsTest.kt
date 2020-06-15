/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package coroutines.cancellation

import coroutines.CoroutinesTestBase
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.cancellation.suspendCancellableCoroutine
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class SuspendCancellableCoroutineJsTest : CoroutinesTestBase() {

    @Test
    fun testRegularResume() = runTest {
        val result = suspendCancellableCoroutine<Int> {
            expect(1)
            schedule {
                expect(2)
                it.resumeWith(Result.success(239))
            }
        }
        assertEquals(239, result)
        finish(3)
    }

    @Test
    fun testRegularResumeWithExceptionWithHandler() = runTest {
        try {
            suspendCancellableCoroutine<Int> {
                expect(1)
                it.invokeOnCancellation { fail() }
                schedule {
                    expect(2)
                    it.resumeWith(Result.failure(IndexOutOfBoundsException()))
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            finish(3)
        }
    }

    @Test
    fun testCancelWithHandler() = runTest {
        try {
            suspendCancellableCoroutine<Int> {
                expect(1)
                it.invokeOnCancellation {
                    assertTrue(it is CancellationException)
                    expect(3)
                }
                schedule {
                    expect(2)
                    it.cancel()
                }
            }
        } catch (e: CancellationException) {
            finish(4)
        }
    }

    @Test
    fun testCancelWithCauseWithHandler() = runTest {
        try {
            suspendCancellableCoroutine<Int> {
                expect(1)
                it.invokeOnCancellation {
                    assertTrue(it is ArithmeticException)
                    expect(3)
                }
                schedule {
                    expect(2)
                    it.cancel(ArithmeticException())
                }
            }
        } catch (e: ArithmeticException) {
            finish(4)
        }
    }

    @Test
    fun testRegularResumeWithException() = runTest {
        try {
            suspendCancellableCoroutine<Int> {
                expect(1)
                schedule {
                    expect(2)
                    it.resumeWith(Result.failure(IndexOutOfBoundsException()))
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            finish(3)
        }
    }

    @Test
    fun testCancel() = runTest {
        try {
            suspendCancellableCoroutine<Int> {
                expect(1)
                schedule {
                    expect(2)
                    it.cancel()
                }
            }
        } catch (e: CancellationException) {
            finish(3)
        }
    }

    @Test
    fun testCancelWithCause() = runTest {
        try {
            suspendCancellableCoroutine<Int> {
                expect(1)
                schedule {
                    expect(2)
                    it.cancel(ArithmeticException())
                }
            }
        } catch (e: ArithmeticException) {
            finish(3)
        }
    }

    fun schedule(block: dynamic) = setTimeout(block, 1)
}

private external fun setTimeout(handler: dynamic, timeout: Int = definedExternally): Int
