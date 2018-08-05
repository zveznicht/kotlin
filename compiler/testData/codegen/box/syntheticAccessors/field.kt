// FILE: A.kt

package a

import b.B

class A {
    fun foo() = OK

    companion object : B() {}
}

fun box(): String {
    return A().foo()
}

// FILE: B.kt

package b

open class B {
    protected @JvmField val OK = "OK"
}