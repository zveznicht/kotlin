// !CHECK_TYPE
// FILE: a.kt

package a.b.c

class D {
    suspend fun foo() = 42
}

// FILE: b.kt

import kotlin.reflect.KSuspendFunction1

fun main() {
    val x = a.b.c.D::foo

    checkSubtype<KSuspendFunction1<a.b.c.D, Int>>(x)
}
