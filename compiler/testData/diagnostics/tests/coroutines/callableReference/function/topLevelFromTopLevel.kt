// !CHECK_TYPE

import kotlin.reflect.*

suspend fun foo() {}
suspend fun bar(<!UNUSED_PARAMETER!>x<!>: Int) {}
suspend fun baz() = "OK"

fun main() {
    val x = ::foo
    val y = ::bar
    val z = ::baz

    checkSubtype<KSuspendFunction0<Unit>>(x)
    checkSubtype<KSuspendFunction1<Int, Unit>>(y)
    checkSubtype<KSuspendFunction0<String>>(z)
}
