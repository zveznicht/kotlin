// !DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_EXPRESSION

open class A
class B: A()

suspend fun A.foo() {}
suspend fun B.foo() {} // more specific

suspend fun bar(a: Any) {}
suspend fun bar(a: Int) {}  // more specific

fun test() {
    B::foo
    ::bar
}