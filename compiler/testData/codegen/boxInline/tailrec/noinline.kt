// FILE: lib.kt
package lib

tailrec inline fun onOdd(x: Int, noinline c: (Int) -> Unit) {
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
    onOdd(10) { res += it }
    return if (res == "97531") "OK" else "FAIL " + res
}
