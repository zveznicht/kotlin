// WITH_RUNTIME
// NO_CHECK_LAMBDA_INLINING

// FILE: lib.kt
package lib

tailrec suspend inline fun onFive(x: Int, c: suspend () -> Unit) {
    if (x == 5) c()
    if (x == 0) return
    onFive(x - 1, c)
}

// FILE: box.kt
// WITH_COROUTINES
import lib.*
import helpers.*
import kotlin.coroutines.*

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(EmptyContinuation)
}

suspend fun test(): String {
    onFive(10) {
        return "OK"
    }
    return "FAIL"
}

fun box(): String {
    var res = "FAIL 1"
    builder {
        res = test()
    }
    return res
}
