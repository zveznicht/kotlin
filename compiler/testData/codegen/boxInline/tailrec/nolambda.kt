// FILE: lib.kt
package lib

tailrec inline fun factorial(x: Int, y: Int = 1): Int {
    if (x == 0) return y
    return factorial(x - 1, x * y)
}

// FILE: box.kt
import lib.*

fun box(): String {
    val fac = factorial(5)
    return if (fac == 120) "OK" else "FAIL"
}
