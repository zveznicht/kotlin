// !CHECK_TYPE

import kotlin.reflect.*

class A

suspend fun A.foo() {}
suspend fun A.bar(<!UNUSED_PARAMETER!>x<!>: Int) {}
suspend fun A.baz() = "OK"

fun main() {
    val x = A::foo
    val y = A::bar
    val z = A::baz

    checkSubtype<KSuspendFunction1<A, Unit>>(x)
    checkSubtype<KSuspendFunction2<A, Int, Unit>>(y)
    checkSubtype<KSuspendFunction1<A, String>>(z)
}
