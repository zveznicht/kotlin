// EXPECTED_REACHABLE_NODES: 1251

var result = ""

open class A {
    fun foo() { result += "A" }
    open fun boo() { result += "FAIL" }
}
class B : A() {
    override fun boo() { result += "B" }
    fun bar() { result += "C" }
}
fun box(): String {
    val b = B()
    b.boo()
    b.foo()
    b.bar()
    if (result != "BAC") return "FAIL: $result"

    return "OK"
}