/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package coroutines.cancellation

import coroutines.CoroutinesTestBase
import kotlin.coroutines.Continuation
import kotlin.coroutines.SafeContinuation
import kotlin.coroutines.cancellation.CancellableContinuation
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.cancellation.CancellationSource
import kotlin.coroutines.cancellation.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.test.*

class SuspendCancellableCoroutineTest : CoroutinesTestBase() {

    @Test
    fun testSameFrameResume() = runTest {
        val result = suspendCancellableCoroutine<Int> {
            expect(1)
            it.resumeWith(Result.success(239))
        }
        assertEquals(239, result)
        finish(2)
    }

    @Test
    fun testRegularResumeWithExceptionSameFrameWithHandler() = runTest {
        // Bug in assertFailsWith on JS BE (here and below)
        try {
            suspendCancellableCoroutine<Int> {
                it.invokeOnCancellation { fail() }
                expect(1)
                it.resumeWith(Result.failure(IndexOutOfBoundsException()))
            }
        } catch (e: IndexOutOfBoundsException) {
            finish(2)
        }
    }

    @Test
    fun testCancelSameFrameWithHandler() = runTest {
        try {
            suspendCancellableCoroutine<Int> {
                it.invokeOnCancellation {
                    assertTrue(it is CancellationException)
                    expect(2)
                }
                expect(1)
                it.cancel()
            }
        } catch (e: CancellationException) {
            finish(3)
        }
    }

    @Test
    fun testCancelWithCauseSameFrameWithHandler() = runTest {
        try {
            suspendCancellableCoroutine<Int> {
                it.invokeOnCancellation {
                    assertTrue(it is ArithmeticException)
                    expect(2)
                }
                expect(1)
                it.cancel(ArithmeticException())
            }
        } catch (e: ArithmeticException) {
            finish(3)
        }
    }

    @Test
    fun testRegularResumeWithExceptionSameFrame() = runTest {
        try {
            suspendCancellableCoroutine<Int> {
                expect(1)
                it.resumeWith(Result.failure(IndexOutOfBoundsException()))
            }
        } catch (e: IndexOutOfBoundsException) {
            finish(2)
        }
    }

    @Test
    fun testCancelSameFrame() = runTest {
        try {
            suspendCancellableCoroutine<Int> {
                expect(1)
                it.cancel()
            }
        } catch (e: CancellationException) {
            finish(2)
        }
    }

    @Test
    fun testCancelWithCauseSameFrame() = runTest {
        try {
            suspendCancellableCoroutine<Int> {
                expect(1)
                it.cancel(ArithmeticException())
            }
        } catch (e: ArithmeticException) {
            finish(2)
        }
    }

    @Test
    fun testExceptionAfterCancellationIsNotReported() = runTest {
        try {
            suspendCancellableCoroutine<Unit> {
                it.cancel()
                it.resumeWith(Result.failure(AssertionError()))
                expect(1)
            }
        } catch (e: CancellationException) {
            finish(2)
        }
    }

    @Test
    fun testSecondResumeAfterCancellationFails() = runTest {
        try {
            suspendCancellableCoroutine<Unit> {
                it.cancel()
                it.resume(Unit)
                assertFailsWith<IllegalStateException> { it.resume(Unit) }
                expect(1)
            }
        } catch (e: CancellationException) {
            finish(2)
        }
    }

    // Invoke on cancellation test

    @Test
    fun testDoubleSubscription() = runTest {
        try {
            suspendCancellableCoroutine<Unit> { c ->
                c.invokeOnCancellation { fail() }
                expect(1)
                c.invokeOnCancellation { fail() }
                fail()
            }
        } catch (e: IllegalStateException) {
            finish(2)
        }
    }

    @Test
    fun testDoubleSubscriptionAfterCompletion() = runTest {
        suspendCancellableCoroutine<Unit> { c ->
            c.resumeWith(Result(Unit))
            // Nothing happened
            c.invokeOnCancellation { fail() }
            // Cannot validate after completion
            c.invokeOnCancellation { fail() }
        }
    }

    @Test
    fun testDoubleSubscriptionAfterCancellation() = runTest {
        try {
            suspendCancellableCoroutine<Unit> { c ->
                c.cancel()
                c.invokeOnCancellation {
                    assertTrue(it is CancellationException)
                    expect(1)
                }
                assertFailsWith<IllegalStateException> { c.invokeOnCancellation { fail() } }
            }
        } catch (e: CancellationException) {
            finish(2)
        }
    }

    @Test
    fun testDoubleSubscriptionAfterCancellationWithCause() = runTest {
        try {
            suspendCancellableCoroutine<Unit> { c ->
                c.invokeOnCancellation {
                    require(it is IndexOutOfBoundsException)
                    expect(1)
                }
                c.cancel(IndexOutOfBoundsException())
                assertFailsWith<IllegalStateException> { c.invokeOnCancellation { fail() } }
            }
        } catch (e: IndexOutOfBoundsException) {
            finish(2)
        }
    }

    @Test
    fun testCancellationSource() {
        val block: suspend () -> Unit = {
            suspendCancellableCoroutine {
                assertTrue(it is CustomCancellableContinuation<*>)
                expect(1)
                it.resumeWith(Result.success(Unit))
            }
        }

        block.startCoroutine(Continuation(CustomCancellationSource) {})
        finish(2)
    }

    object CustomCancellationSource : CancellationSource {
        override fun <T> createCancellableContinuation(continuation: Continuation<T>): CancellableContinuation<T> {
            return CustomCancellableContinuation(SafeContinuation(continuation))
        }
    }

    class CustomCancellableContinuation<T>(original: CancellableContinuation<T>) : CancellableContinuation<T> by original
}