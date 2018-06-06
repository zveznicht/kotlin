// !CHECK_TYPE

import kotlin.reflect.KSuspendFunction1

class A {
    suspend fun foo() = 42
}

suspend fun A.<!EXTENSION_SHADOWED_BY_MEMBER!>foo<!>() {}

fun main() {
    val x = A::foo

    checkSubtype<KSuspendFunction1<A, Int>>(x)
}
