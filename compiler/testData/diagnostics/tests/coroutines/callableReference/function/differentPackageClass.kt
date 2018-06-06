// !CHECK_TYPE
// FILE: a.kt

package first

class A {
    suspend fun foo() {}
    suspend fun bar(<!UNUSED_PARAMETER!>x<!>: Int) {}
    suspend fun baz() = "OK"
}

// FILE: b.kt

package other

import kotlin.reflect.*

import first.A

fun main() {
    val x = first.A::foo
    val y = first.A::bar
    val z = A::baz

    checkSubtype<KSuspendFunction1<A, Unit>>(x)
    checkSubtype<KSuspendFunction2<A, Int, Unit>>(y)
    checkSubtype<KSuspendFunction1<A, String>>(z)
}
