// WITH_RUNTIME
// NO_CHECK_LAMBDA_INLINING
// IGNORE_BACKEND: JS

// FILE: lib.kt
package lib

tailrec suspend inline fun onOdd(x: Int, noinline c: suspend (Int) -> Unit) {
    val lambda = suspend {
        if (x.rem(2) != 0) c(x)
    }
    lambda()
    if (x == 0) return
    onOdd(x - 1, c)
}

// FILE: box.kt
// WITH_COROUTINES
import lib.*
import helpers.*
import kotlin.coroutines.*

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(EmptyContinuation)
}

var res = ""

fun append(i: Int) {
    res += i
}

fun box(): String {
    builder {
        onOdd(10, ::append)
    }
    return if (res == "97531") "OK" else "FAIL " + res
}
