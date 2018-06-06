// !CHECK_TYPE
// !DIAGNOSTICS: -UNUSED_EXPRESSION
// FILE: a.kt

package first

class A

suspend fun A.foo() {}
suspend fun A.bar() {}
suspend fun A.baz() {}

// FILE: b.kt

package other

import kotlin.reflect.KSuspendFunction1

import first.A
import first.foo

fun main() {
    val x = first.A::foo
    first.A::<!UNRESOLVED_REFERENCE!>bar<!>
    A::<!UNRESOLVED_REFERENCE!>baz<!>

    checkSubtype<KSuspendFunction1<A, Unit>>(x)
}
