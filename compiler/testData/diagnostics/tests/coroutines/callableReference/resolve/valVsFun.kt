// !DIAGNOSTICS: -UNUSED_PARAMETER

class A {
    suspend fun x() {}
}

fun f2(): suspend (A) -> Unit = A::x     // ok, function