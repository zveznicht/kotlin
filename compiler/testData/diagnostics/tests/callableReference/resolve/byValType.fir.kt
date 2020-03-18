// !DIAGNOSTICS: -UNUSED_PARAMETER
// !WITH_NEW_INFERENCE
// NI_EXPECTED_FILE

fun foo() {}
fun foo(s: String) {}

val x1 = <!UNRESOLVED_REFERENCE!>::foo<!>
val x2: () -> Unit = ::foo
val x3: (String) -> Unit = ::foo
val x4: (Int) -> Unit = <!UNRESOLVED_REFERENCE!>::foo<!>