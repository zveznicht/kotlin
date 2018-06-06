// !DIAGNOSTICS: -UNUSED_PARAMETER
// KT-12799 Bound callable references not resolved for overload

class C {
    suspend fun xf1(){}
    suspend fun xf1(s: String){}
}

fun foo(p: suspend (String) -> Unit){}

fun bar(c: C) {
    foo(c::xf1)
}
