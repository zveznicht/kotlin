// IGNORE_BACKEND: JS

// FILE: lib.kt
package lib

tailrec inline fun onOdd(x: Int, crossinline c: (Int) -> Unit) {
    val lambda = {
        if (x.rem(2) != 0) c(x)
    }
    lambda()
    if (x == 0) return
    onOdd(x - 1, c)
}

// FILE: box.kt
import lib.*

var res = ""

fun append(i: Int) {
    res += i
}

fun box(): String {
    onOdd(10, ::append)
    return if (res == "97531") "OK" else "FAIL " + res
}
