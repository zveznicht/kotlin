/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.coroutines

import kotlin.coroutines.cancellation.CancellableContinuation
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.CoroutineSingletons
import kotlin.coroutines.intrinsics.CoroutineSingletons.RESUMED
import kotlin.coroutines.intrinsics.CoroutineSingletons.UNDECIDED

@PublishedApi
@SinceKotlin("1.3")
@OptIn(ExperimentalStdlibApi::class)
internal actual class SafeContinuation<in T>
internal actual constructor(
    private val delegate: Continuation<T>,
    initialResult: Any?
) : CancellableContinuation<T> {
    @PublishedApi
    internal actual constructor(delegate: Continuation<T>) : this(delegate, UNDECIDED)

    actual override val context: CoroutineContext
        get() = delegate.context

    private var _state: Any? = initialResult

    public actual override fun resumeWith(result: Result<T>) {
        val state = _state
        when {
            // Resume from undecided, update the value
            state === UNDECIDED -> {
                _state = result.value
                return
            }
            // Resume from suspended, should resume as well
            state === COROUTINE_SUSPENDED -> {
                _state = RESUMED
                delegate.resumeWith(result)
                return
            }
            // Cancelled -> ignore resume, do nothing
            state is Cancelled -> {
                if (state.makeResumed()) return
                alreadyResumed(result)
            }
            state is CancellationHandler -> {
                when {
                    // In undecided, update the value
                    state.decision === UNDECIDED -> {
                        _state = result.value
                        return
                    }
                    // Suspended, update and resume
                    state.decision === COROUTINE_SUSPENDED -> {
                        _state = result.value
                        delegate.resumeWith(result)
                        return
                    }
                    else -> throw IllegalStateException("Unreachable state $state")
                }
            }
            else -> throw IllegalStateException("Unreachable state $state")
        }
    }

    public actual override fun invokeOnCancellation(handler: (cause: Throwable) -> Unit) {
        val state = _state
        when {
            // UNDECIDED -> handler installed
            state === UNDECIDED -> {
                _state = CancellationHandler(handler, UNDECIDED)
                return
            }
            // SUSPENDED -> rare case, installed in async manner, install and do nothing
            state === COROUTINE_SUSPENDED -> {
                _state = CancellationHandler(handler, CoroutineSingletons.COROUTINE_SUSPENDED)
                return
            }
            // Cancelled -> invoke immediately
            state is Cancelled -> {
                if (state.makeHandled()) {
                    handler(getCancellationCause(state.cause))
                    return
                }
                multipleHandlersError(state, handler)
            }
            state is CancellationHandler -> multipleHandlersError(state, handler)
            // Resumed -> do nothing
            else -> return
        }
    }

    private fun multipleHandlersError(state: Any?, handler: (cause: Throwable) -> Unit) {
        throw IllegalStateException("Only one handler can be registered, had: $state, proposed: $handler")
    }

    private fun alreadyResumed(result: Result<T>) {
        throw IllegalStateException("Already resumed, but proposed with $result")
    }

    actual override fun cancel(cause: Throwable?): Boolean {
        val cancelled = Cancelled(cause)
        val state = _state
        when {
            // Undecided, set the value
            state === UNDECIDED -> {
                _state = cancelled
                return true
            }
            // Suspended, set the value, resume with the cause
            state === COROUTINE_SUSPENDED -> {
                _state = cancelled
                delegate.resumeWithException(getCancellationCause(cause))
                return true
            }
            state is CancellationHandler -> {
                when (state.decision) {
                    COROUTINE_SUSPENDED -> {
                        _state = cancelled
                        val exception = getCancellationCause(cause)
                        state.handler(exception)
                        delegate.resumeWithException(exception)
                        return true
                    }
                    // Coroutine is in UNDECIDED state with CancellationHandler installed. We have to notify
                    // getResultOrMarkSuspended that coroutine was cancelled AND invoke installed CH
                    UNDECIDED -> {
                        // Make handled unpublished Cancelled first, so no race with multiple handlers will occur
                        cancelled.makeHandled()
                        _state = cancelled
                        val e = getCancellationCause(cause)
                        state.handler(e)
                        return true
                    }
                    // Another cancel succeeded
                    else -> return false
                }
            }
            else -> return false // Resumed or cancelled
        }
    }

    actual override fun getResultOrMarkSuspended(): Any? = getOrThrow()

    @PublishedApi
    internal actual fun getOrThrow(): Any? {
        val state = _state
        when {
            // Just suspend
            state === UNDECIDED -> {
                _state = COROUTINE_SUSPENDED
                return COROUTINE_SUSPENDED
            }
            state is Cancelled -> {
                throw getCancellationCause(state.cause)
            }
            // Suspend, but preserve CH
            state is CancellationHandler -> {
                when {
                    state.decision === UNDECIDED -> {
                        _state = CancellationHandler(state.handler, CoroutineSingletons.COROUTINE_SUSPENDED)
                        return COROUTINE_SUSPENDED
                    }
                    else -> throw IllegalStateException("Unreachable state: $state")
                }
            }
            state is Result.Failure -> throw state.exception
            else -> return state // value
        }
    }

    private fun getCancellationCause(cause: Throwable?) =
        cause ?: CancellationException("Continuation $delegate was cancelled normally", cause)

    override fun toString(): String =
        "SafeContinuation for $delegate"

    private class Cancelled(val cause: Throwable?) {
        private var handled: Boolean = false
        private var resumed: Boolean = false

        fun makeHandled(): Boolean {
            if (handled) return false
            handled = true
            return true
        }

        fun makeResumed(): Boolean {
            if (resumed) return false
            resumed = true
            return true
        }

        override fun toString(): String = "Cancelled(cause=$cause)"
    }

    /**
     * Three possible states of decision:
     * UNDECIDED -- handler is installed, suspendCancellableCoroutine block still being executed
     * SUSPENDED -- handler is installed, coroutine was suspended
     */
    private class CancellationHandler(val handler: (cause: Throwable) -> Unit, val decision: CoroutineSingletons) {
        override fun toString(): String = "CancellationHandler(handler=$handler, decision=$decision)"
    }
}
