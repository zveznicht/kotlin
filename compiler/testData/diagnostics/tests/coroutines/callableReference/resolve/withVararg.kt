// !DIAGNOSTICS: -UNUSED_PARAMETER

suspend fun foo(vararg ii: Int) {}
suspend fun foo(vararg ss: String) {}
suspend fun foo(i: Int) {}

val fn1: suspend (Int) -> Unit = ::foo
val fn2: suspend (IntArray) -> Unit = ::foo
val fn3: suspend (Int, Int) -> Unit = ::<!NONE_APPLICABLE!>foo<!>
val fn4: suspend (Array<String>) -> Unit = ::foo