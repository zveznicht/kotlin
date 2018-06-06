// !DIAGNOSTICS: -UNUSED_PARAMETER

suspend fun foo() {}
suspend fun foo(s: String) {}

val x1 = ::<!OVERLOAD_RESOLUTION_AMBIGUITY!>foo<!>
val x2: suspend () -> Unit = ::foo
val x3: suspend (String) -> Unit = ::foo
val x4: suspend (Int) -> Unit = ::<!NONE_APPLICABLE!>foo<!>