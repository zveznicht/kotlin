// !DIAGNOSTICS: -UNUSED_PARAMETER, -EXTENSION_SHADOWED_BY_MEMBER

import kotlin.reflect.*

fun <T> ofType(x: T): T = x

class A {
    suspend fun foo() {}

    suspend fun bar() {}
}

suspend fun A.foo(): String = "A"

val x0 = A::foo

val x1 = ofType<suspend (A) -> Unit>(A::foo)
val x4: suspend (A) -> String = A::foo

val y0 = A::bar
val y1 = ofType<suspend (A) -> Unit>(A::bar)
