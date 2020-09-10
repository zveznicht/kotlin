// WITH_RUNTIME
// NO_CHECK_LAMBDA_INLINING
// IGNORE_BACKEND: JS

// FILE: lib.kt
package lib

tailrec suspend inline fun onOdd(x: Int, crossinline c: suspend (Int) -> Unit) {
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

fun box(): String {
    var res = ""
    val c: suspend (Int) -> Unit = { res += it }
    builder {
        onOdd(10, c)
    }
    return if (res == "97531") "OK" else "FAIL " + res
}
