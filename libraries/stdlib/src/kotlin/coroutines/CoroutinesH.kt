/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.coroutines

import kotlin.coroutines.cancellation.CancellableContinuation

@PublishedApi
@SinceKotlin("1.3")
@OptIn(ExperimentalStdlibApi::class)
internal expect class SafeContinuation<in T> : CancellableContinuation<T> {
    internal constructor(delegate: Continuation<T>, initialResult: Any?)

    @PublishedApi
    internal constructor(delegate: Continuation<T>)

    @PublishedApi
    internal fun getOrThrow(): Any?

    override fun invokeOnCancellation(handler: (cause: Throwable) -> Unit)
    override fun cancel(cause: Throwable?): Boolean
    override fun getResultOrMarkSuspended(): Any?

    override val context: CoroutineContext
    override fun resumeWith(result: Result<T>): Unit
}
