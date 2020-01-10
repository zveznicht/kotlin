package my

class A {
    companion object X {
        fun foo() {}
    }
}

fun test() {
    val x = A
    A.foo()
    A.X.foo()
}