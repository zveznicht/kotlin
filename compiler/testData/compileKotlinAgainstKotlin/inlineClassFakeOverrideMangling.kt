// !LANGUAGE: +InlineClasses

// FILE: 1.kt

inline class IC(val s: String)

abstract class A {
    fun foo(s: String) = IC(s)
}

class C : A()

// FILE: 2.kt

fun box() = C().foo("OK").s
