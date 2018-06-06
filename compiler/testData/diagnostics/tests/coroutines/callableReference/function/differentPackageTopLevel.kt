// !CHECK_TYPE
// FILE: a.kt

package first

suspend fun foo() {}
suspend fun bar(<!UNUSED_PARAMETER!>x<!>: Int) {}
suspend fun baz() = "OK"

// FILE: b.kt

package other

import kotlin.reflect.*

import first.foo
import first.bar
import first.baz

fun main() {
    val x = ::foo
    val y = ::bar
    val z = ::baz

    checkSubtype<KSuspendFunction0<Unit>>(x)
    checkSubtype<KSuspendFunction1<Int, Unit>>(y)
    checkSubtype<KSuspendFunction0<String>>(z)
}
