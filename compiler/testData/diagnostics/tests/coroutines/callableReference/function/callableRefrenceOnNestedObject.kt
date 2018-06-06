open class A {
    suspend fun foo() = 42

    object B: A()
}

suspend fun test() {
    (A::foo)(A.B)
}
