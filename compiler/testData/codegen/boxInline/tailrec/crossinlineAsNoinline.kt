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

fun box(): String {
    var res = ""
    val c: (Int) -> Unit = { res += it }
    onOdd(10, c)
    return if (res == "97531") "OK" else "FAIL " + res
}
