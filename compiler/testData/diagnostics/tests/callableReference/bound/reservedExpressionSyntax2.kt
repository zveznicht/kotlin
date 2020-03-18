// !DIAGNOSTICS: -UNUSED_VARIABLE
// !WITH_NEW_INFERENCE

package test

fun nullableFun(): Int? = null
fun Int.foo() {}

val test1 = <!NI;TYPE_MISMATCH!><!RESERVED_SYNTAX_IN_CALLABLE_REFERENCE_LHS!>nullableFun()<!>?::<!OI;UNSAFE_CALL!>foo<!><!>