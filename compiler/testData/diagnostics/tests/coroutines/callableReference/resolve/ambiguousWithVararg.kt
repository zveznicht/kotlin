// !DIAGNOSTICS: -UNUSED_PARAMETER

suspend fun fun1() {}
suspend fun fun1(x: Int) {}

val ref1 = ::<!OVERLOAD_RESOLUTION_AMBIGUITY!>fun1<!>

suspend fun fun2(vararg x: Int) {}
suspend fun fun2(x: Int) {}

val ref2 = ::<!OVERLOAD_RESOLUTION_AMBIGUITY!>fun2<!>

suspend fun fun3(x0: Int, vararg xs: Int) {}
suspend fun fun3(x0: String, vararg xs: String) {}

val ref3 = ::<!OVERLOAD_RESOLUTION_AMBIGUITY!>fun3<!>
