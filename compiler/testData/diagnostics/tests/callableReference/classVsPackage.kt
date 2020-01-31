/*
 * RELEVANT SPEC SENTENCES (spec version: 0.1-220, test type: pos):
 *  - expressions, call-and-property-access-expressions, callable-references -> paragraph 3 -> sentence 1
 *  - expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 1
 *  - expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 2
 *  - expressions, call-and-property-access-expressions, navigation-operators -> paragraph 9 -> sentence 4
 */
// MODULE: m1
// FILE: 1.kt

package a

class b {
    class c
}

// MODULE: m2
// FILE: 2.kt

package a.b

class c {
    fun foo() {}
}

// MODULE: m3(m1, m2)
// FILE: test.kt

package test

fun test() = a.b.c::foo
