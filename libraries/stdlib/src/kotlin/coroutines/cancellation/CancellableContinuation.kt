package kotlin.coroutines.cancellation

import kotlin.coroutines.Continuation
import kotlin.coroutines.SafeContinuation
import kotlin.coroutines.intrinsics.intercepted
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.internal.InlineOnly

/**
 * A regular continuation that can be externally cancelled.
 * When the [cancel] function is explicitly invoked, this continuation immediately resumes with a [CancellationException]
 * or its cause if it was specified.
 * Conceptually, it is a [Continuation] that can be cancelled **externally** by [CancellationSource], triggering
 * immediate abort of current computation and throwing [CancellationException].
 *
 * [CancellableContinuation] interface is experimental standard library API is not stable for inheritance.
 */
@ExperimentalStdlibApi
@SinceKotlin("1.4")
public interface CancellableContinuation<in T> : Continuation<T> {

    /**
     * Registers a [handler] that will be **synchronously** invoked when this continuation is [cancelled][cancel].
     * At most one [handler] can be installed on a continuation, otherwise [IllegalStateException] is thrown.
     * Cause parameter is an instance of [CancellationException] when [CancellableContinuation] was cancelled
     * without a cause and cancellation cause otherwise.
     * [handler] should be exception safe and does not throw any exceptions.
     */
    public fun invokeOnCancellation(handler: (cause: Throwable) -> Unit)

    /**
     * Cancels this continuation with an optional cancellation [cause]. The result is `true` if this continuation was
     * cancelled as a result of this invocation, and `false` otherwise.
     * Cancellation should resume the continuation with cancellation cause if it is suspended.
     */
    public fun cancel(cause: Throwable? = null): Boolean

    /**
     * Determines the result of the current cancellable continuation after its creation and manipulation
     * with it. This function can return [COROUTINE_SUSPENDED], resulting value of type [T]
     * if coroutine was already resumed with a value or throw an exception if coroutine was
     * already cancelled or resumed with an exception.
     *
     * This function is called by [suspendCancellableCoroutine] when its block completes.
     * Calling this function manually leads to unspecified behaviour.
     */
    public fun getResultOrMarkSuspended(): Any?
}

/**
 * Obtains the current continuation instance inside suspend function, wraps it into [CancellableContinuation]
 * and suspends currently running coroutine.
 * This suspending function is cancellable, it immediately checks for cancellation of the resulting context using [CancellationSource]
 * and throws [CancellationException] if it was cancelled.
 * Asynchronous cancellation triggered by [CancellationSource] results in [CancellableContinuation.cancel] call
 * that resumes this continuation with [CancellationException] if it is suspended.
 * Concurrent [Continuation.resumeWith] and [CancellableContinuation.cancel] are allowed and it is guaranteed that
 * only one of them will succeed.
 *
 * In this function both [Continuation.resumeWith] and [CancellableContinuation.cancel] can be used either synchronously in
 * the same stack-frame where the suspension function is run or asynchronously later in the same thread or
 * from a different thread of execution. Subsequent invocation of any resume function will produce an [IllegalStateException].
 */
@InlineOnly
@SinceKotlin("1.4")
@ExperimentalStdlibApi
public suspend inline fun <T> suspendCancellableCoroutine(crossinline block: (CancellableContinuation<T>) -> Unit): T =
    suspendCoroutineUninterceptedOrReturn { uCont ->
        val cont = createCancellableContinuation(uCont)
        block(cont)
        cont.getResultOrMarkSuspended()
    }

@PublishedApi
@ExperimentalStdlibApi
internal fun <T> createCancellableContinuation(continuation: Continuation<T>): CancellableContinuation<T> {
    val source = continuation.context[CancellationSource]
    val intercepted = continuation.intercepted()
    return source?.createCancellableContinuation(intercepted) ?: SafeContinuation(intercepted)
}
