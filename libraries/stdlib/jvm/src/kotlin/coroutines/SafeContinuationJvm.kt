/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package kotlin.coroutines

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlin.coroutines.cancellation.CancellableContinuation
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.CoroutineSingletons
import kotlin.coroutines.intrinsics.CoroutineSingletons.RESUMED
import kotlin.coroutines.intrinsics.CoroutineSingletons.UNDECIDED
import kotlin.coroutines.jvm.internal.CoroutineStackFrame

@PublishedApi
@SinceKotlin("1.3")
@OptIn(ExperimentalStdlibApi::class)
internal actual class SafeContinuation<in T>
internal actual constructor(
    private val delegate: Continuation<T>,
    initialResult: Any?
) : CancellableContinuation<T>, CoroutineStackFrame {
    @PublishedApi
    internal actual constructor(delegate: Continuation<T>) : this(delegate, UNDECIDED)

    actual override val context: CoroutineContext
        get() = delegate.context

    /*
     * Possible states:
     *
     * UNDECIDED: block with CC is being executed
     * RESUMED: completed with value or exception
     * Cancelled: cancelled, also completed
     * COROUTINE_SUSPENDED: coroutine is suspended
     * CancellationHandler(state): CH is installed
     *
     * Possible transitions (no ASCII art here, too much space):
     * 1) UNDECIDED (4 transitions, resumes are counted as 1)
     *    -> value on resume
     *    -> Result.failure on resumeWithException
     *    -> Cancelled on cancel
     *    -> CancellationHandler(UNDECIDED) if CH was installed right within [block]
     *    -> COROUTINE_SUSPENDED when block completes
     * 2) COROUTINE_SUSPENDED (3 transitions)
     *    -> value | Cancelled on resume/cancel
     *    -> CancellationHandler(COROUTINE_SUSPENDED) if **another thread** installed CH
     * 3) CancellationHandler(COROUTINE_SUSPENDED) (2 transitions)
     *    -> value | Cancelled on resume/cancel
     * 4) CancellationHandler(UNDECIDED) (3 transitions)
     *     -> value on resume
     *     -> CancellationHandler(COROUTINE_SUSPENDED) when block completes
     *     -> Cancelled on cancellation
     * The latter transition is required to asynchronously notify block that coroutine was cancelled
     * 12 transitions in total
     */
    @Volatile
    private var _state: Any? = initialResult

    /*
     * This particular visibility + annotation is required to ensure **direct** access
     * to static final AFU to enable JIT optimization.
     */
    private companion object {
        @Suppress("UNCHECKED_CAST")
        @JvmField
        public val RESULT = AtomicReferenceFieldUpdater.newUpdater<SafeContinuation<*>, Any?>(
            SafeContinuation::class.java, Any::class.java as Class<Any?>, "_state"
        )
    }

    public actual override fun resumeWith(result: Result<T>) {
        // 4 transitions
        loopOnState { state ->
            when {
                // Resume from undecided, update the value
                state === UNDECIDED -> {
                    if (compareAndSet(UNDECIDED, result.value)) return
                }
                // Resume from suspended, should resume as well
                state === COROUTINE_SUSPENDED -> {
                    if (compareAndSet(COROUTINE_SUSPENDED, RESUMED)) {
                        delegate.resumeWith(result)
                        return
                    }
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
                            if (compareAndSet(state, result.value)) return
                        }
                        // Suspended, update and resume
                        state.decision === COROUTINE_SUSPENDED -> {
                            if (compareAndSet(state, result.value)) {
                                delegate.resumeWith(result)
                                return
                            }
                        }
                        else -> throw IllegalStateException("Unreachable state $state")
                    }
                }
                else -> throw IllegalStateException("Unreachable state $state")
            }
        }
    }

    public actual override fun invokeOnCancellation(handler: (cause: Throwable) -> Unit): Unit = loopOnState { state ->
        // 2 transitions, 6 in total
        when {
            // UNDECIDED -> handler installed
            state === UNDECIDED -> if (compareAndSet(UNDECIDED, CancellationHandler(handler, UNDECIDED))) {
                return
            }
            // SUSPENDED -> rare case, installed in async manner, install and do nothing
            state === COROUTINE_SUSPENDED ->
                if (compareAndSet(COROUTINE_SUSPENDED, CancellationHandler(handler, CoroutineSingletons.COROUTINE_SUSPENDED))) {
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
        // 4 transitions, 10 in total
        loopOnState { state ->
            when {
                // Undecided, set the value
                state === UNDECIDED -> {
                    if (compareAndSet(UNDECIDED, cancelled)) return true
                }
                // Suspended, set the value, resume with the cause
                state === COROUTINE_SUSPENDED -> {
                    if (compareAndSet(COROUTINE_SUSPENDED, cancelled)) {
                        delegate.resumeWithException(getCancellationCause(cause))
                        return true
                    }
                }
                state is CancellationHandler -> {
                    when (state.decision) {
                        COROUTINE_SUSPENDED -> {
                            if (compareAndSet(state, cancelled)) {
                                val exception = getCancellationCause(cause)
                                state.handler(exception)
                                delegate.resumeWithException(exception)
                                return true
                            }
                        }
                        // Coroutine is in UNDECIDED state with CancellationHandler installed. We have to notify
                        // getResultOrMarkSuspended that coroutine was cancelled AND invoke installed CH
                        UNDECIDED -> {
                            // Make handled unpublished Cancelled first, so no race with multiple handlers will occur
                            cancelled.makeHandled()
                            if (compareAndSet(state, cancelled)) {
                                val e = getCancellationCause(cause)
                                state.handler(e)
                                return true
                            } else {
                                // Wasn't published, reset and retry CAS loop
                                cancelled.makeUnhandled()
                            }
                        }
                        // Another cancel succeeded
                        else -> return false
                    }
                }
                else -> return false // Resumed or cancelled
            }
        }
    }

    actual override fun getResultOrMarkSuspended(): Any? = getOrThrow()

    @PublishedApi
    internal actual fun getOrThrow(): Any? {
        // 2 transitions, 12 in total
        loopOnState { state ->
            when {
                // Just suspend
                state === UNDECIDED -> {
                    if (compareAndSet(UNDECIDED, COROUTINE_SUSPENDED))
                        return COROUTINE_SUSPENDED
                }
                state is Cancelled -> {
                    throw getCancellationCause(state.cause)
                }
                // Suspend, but preserve CH
                state is CancellationHandler -> {
                    when {
                        state.decision === UNDECIDED -> {
                            if (compareAndSet(state, CancellationHandler(state.handler, CoroutineSingletons.COROUTINE_SUSPENDED)))
                                return COROUTINE_SUSPENDED
                        }
                    }
                }
                state is Result.Failure -> throw state.exception
                else -> return state // value
            }
        }
    }

    // --- aliases

    @Suppress("NOTHING_TO_INLINE")
    private inline fun compareAndSet(expected: Any?, update: Any?): Boolean {
        return RESULT.compareAndSet(this, expected, update)
    }

    private inline fun loopOnState(block: (Any?) -> Unit): Nothing {
        while (true) block(_state)
    }

    private fun getCancellationCause(cause: Throwable?) =
        cause ?: CancellationException("Continuation $delegate was cancelled normally", cause)

    // --- CoroutineStackFrame implementation

    public override val callerFrame: CoroutineStackFrame?
        get() = delegate as? CoroutineStackFrame

    override fun getStackTraceElement(): StackTraceElement? =
        null

    override fun toString(): String =
        "SafeContinuation for $delegate"

    private class Cancelled(@JvmField val cause: Throwable?) {
        private val handled: AtomicBoolean = AtomicBoolean(false)
        private val resumed: AtomicBoolean = AtomicBoolean(false)

        fun makeHandled() = handled.compareAndSet(false, true)
        fun makeUnhandled() = handled.compareAndSet(true, false)
        fun makeResumed() = resumed.compareAndSet(false, true)
        override fun toString(): String = "Cancelled(cause=$cause)"
    }

    /**
     * Three possible states of decision:
     * UNDECIDED -- handler is installed, suspendCancellableCoroutine block still being executed
     * SUSPENDED -- handler is installed, coroutine was suspended
     */
    private class CancellationHandler(@JvmField val handler: (cause: Throwable) -> Unit, @JvmField val decision: CoroutineSingletons) {
        override fun toString(): String = "CancellationHandler(handler=$handler, decision=$decision)"
    }
}
