// !CHECK_TYPE
// FILE: a.kt

package a.b.c

class D<E, F> {
    suspend fun foo(<!UNUSED_PARAMETER!>e<!>: E, <!UNUSED_PARAMETER!>f<!>: F) = this
}

// FILE: b.kt

import kotlin.reflect.KSuspendFunction3

fun main() {
    val x = a.b.c.D<String, Int>::foo

    checkSubtype<KSuspendFunction3<a.b.c.D<String, Int>, String, Int, a.b.c.D<String, Int>>>(x)
}
