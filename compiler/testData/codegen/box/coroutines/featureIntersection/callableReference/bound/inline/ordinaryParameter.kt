// !LANGUAGE: +NewInference
// IGNORE_BACKEND: JS

// WITH_RUNTIME
// WITH_COROUTINES

import helpers.*
import kotlin.coroutines.experimental.*

inline fun go(f: () -> String) = f()

suspend fun String.id(): String = this

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(EmptyContinuation)
}

fun box(): String {
    val x = "OK"
    var res = "FAIL"
    builder {
        res = go(x::id)
    }
    return res
}
