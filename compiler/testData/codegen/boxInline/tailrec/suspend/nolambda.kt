// WITH_RUNTIME
// NO_CHECK_LAMBDA_INLINING

// FILE: lib.kt
package lib

tailrec suspend inline fun factorial(x: Int, y: Int = 1): Int {
    if (x == 0) return y
    return factorial(x - 1, x * y)
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
    var fac = -1
    builder {
        fac = factorial(5)
    }
    return if (fac == 120) "OK" else "FAIL"
}
