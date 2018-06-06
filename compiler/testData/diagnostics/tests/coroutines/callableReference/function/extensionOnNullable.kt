// !DIAGNOSTICS:-UNUSED_VARIABLE

import kotlin.reflect.*

class A {
    suspend fun foo() {}
}

suspend fun A?.foo() {}

val f: KSuspendFunction1<A, Unit> = A::foo
val g: KSuspendFunction1<A, Unit> = A?::foo
