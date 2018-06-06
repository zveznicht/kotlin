// FILE: Foo.kt

package test

class Foo {
    suspend fun bar() {}
}

// FILE: test.kt

import test.Foo

suspend fun Foo(): String = ""

val f = Foo::bar
val g = Foo::<!UNRESOLVED_REFERENCE!>length<!>
