// IGNORE_BACKEND: JS_IR, JS

// FILE: lib.kt
package lib

tailrec inline fun onFive(x: Int, c: () -> Unit) {
    if (x == 5) c()
    if (x == 0) return
    onFive(x - 1, c)
}

// FILE: box.kt
import lib.*

fun box(): String {
    onFive(10) {
        return "OK"
    }
    return "FAIL"
}
