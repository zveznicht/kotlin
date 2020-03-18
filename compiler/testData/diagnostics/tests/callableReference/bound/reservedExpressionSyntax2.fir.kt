// !DIAGNOSTICS: -UNUSED_VARIABLE
// !WITH_NEW_INFERENCE

package test

fun nullableFun(): Int? = null
fun Int.foo() {}

val test1 = nullableFun()?::foo