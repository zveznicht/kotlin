// !DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_EXPRESSION
// KT-9601 Chose maximally specific function in callable reference

open class A {
    suspend fun foo(a: Any) {}
    suspend fun fas(a: Int) {}
}
class B: A() {
    suspend fun foo(a: Int) {}
    suspend fun fas(a: Any) {}
}

fun test() {
    B::foo
    B::fas
}