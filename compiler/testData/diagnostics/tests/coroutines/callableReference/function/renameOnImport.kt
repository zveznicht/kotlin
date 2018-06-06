// !CHECK_TYPE
// FILE: a.kt

package other

suspend fun foo() {}

class A {
    suspend fun bar() = 42
}

suspend fun A.baz(<!UNUSED_PARAMETER!>x<!>: String) {}

// FILE: b.kt

import kotlin.reflect.*

import other.foo as foofoo
import other.A as AA
import other.baz as bazbaz

fun main() {
    val x = ::foofoo
    val y = AA::bar
    val z = AA::bazbaz

    checkSubtype<KSuspendFunction0<Unit>>(x)
    checkSubtype<KSuspendFunction1<AA, Int>>(y)
    checkSubtype<KSuspendFunction2<AA, String, Unit>>(z)
}
