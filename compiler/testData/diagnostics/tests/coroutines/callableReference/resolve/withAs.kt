// !DIAGNOSTICS: -UNUSED_PARAMETER

suspend fun foo() {}
suspend fun foo(s: String) {}

fun bar(f: suspend () -> Unit) = 1
fun bar(f: suspend (String) -> Unit) = 2

val x1 = ::<!OVERLOAD_RESOLUTION_AMBIGUITY!>foo<!> as suspend () -> Unit
val x2 = bar(::<!OVERLOAD_RESOLUTION_AMBIGUITY!>foo<!> as suspend (String) -> Unit)