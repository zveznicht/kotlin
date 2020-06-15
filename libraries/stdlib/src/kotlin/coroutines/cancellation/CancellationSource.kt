/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.coroutines.cancellation

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.getPolymorphicElement
import kotlin.coroutines.minusPolymorphicKey
import kotlin.coroutines.AbstractCoroutineContextKey

/**
 * The source of compilation that represents a lifecycle of all [CancellableContinuation] associated with it.
 * The source is responsible for keeping track of created cancellable continuations and its cancellation.
 *
 * ### Integration with `suspendCancellableCoroutine`
 *
 * Integration is achieved using key polymorphism provided by [AbstractCoroutineContextKey] and [getPolymorphicElement].
 * In order to provide a compliant seamless integration, it is recommended to provide a companion key
 * that implements [AbstractCoroutineContextKey].
 */
@ExperimentalStdlibApi
@SinceKotlin("1.4")
public interface CancellationSource : CoroutineContext.Element {
    /**
     * The key that defines *the* canсellation token.
     */
    public companion object Key : CoroutineContext.Key<CancellationSource>

    /**
     * A key of this cancellation source, represented by [Key].
     */
    override val key: CoroutineContext.Key<*> get() = Key

    /**
     * Creates a wrapping instance of [CancellableContinuation] for the given [continuation], associated with
     * the current [CancellationSource].
     */
    public fun <T> createCancellableContinuation(continuation: Continuation<T>): CancellableContinuation<T>

    override fun <E : CoroutineContext.Element> get(key: CoroutineContext.Key<E>): E? = getPolymorphicElement(key)
    override fun minusKey(key: CoroutineContext.Key<*>): CoroutineContext = minusPolymorphicKey(key)
}
