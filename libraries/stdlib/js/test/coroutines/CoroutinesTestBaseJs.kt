/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine
import kotlin.js.Promise

actual abstract class CoroutinesTestBase {
    private var actionIndex = 0
    private var finished = false

    actual fun runTest(block: suspend () -> Unit): dynamic {
        var _resolve: ((Unit) -> Unit)? = null
        var _reject: ((Throwable) -> Unit)? = null
        val promise = Promise<Unit>() { resolve, reject ->
            _resolve = resolve
            _reject = reject
        }

        block.startCoroutine(Continuation(EmptyCoroutineContext) {
            if (it.isSuccess) {
                _resolve!!(Unit)
            } else {
                _reject!!(it.exceptionOrNull()!!)
            }
        })
        return promise.then(onFulfilled = {
            if (actionIndex != 0 && !finished) {
                throw IllegalStateException("Expecting that 'finish(${actionIndex + 1})' was invoked, but it was not")
            }
        }, onRejected = { e -> throw e })
    }

    actual fun expect(index: Int) {
        val wasIndex = ++actionIndex
        check(index == wasIndex) { "Expecting action index $index but it is actually $wasIndex" }
    }

    actual fun finish(index: Int) {
        expect(index)
        check(!finished) { "Should call 'finish(...)' at most once" }
        finished = true
    }
}