// !DIAGNOSTICS: -UNUSED_PARAMETER
// KT-9733 No error shown for 2 "main" functions in the same file

fun <!CONFLICTING_OVERLOADS!>main<!>(args: Array<String>) {}
fun <!CONFLICTING_OVERLOADS!>main<!>(args: Array<String>) {}