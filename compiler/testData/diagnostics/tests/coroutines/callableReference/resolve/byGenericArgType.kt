// !WITH_NEW_INFERENCE
// !DIAGNOSTICS: -UNUSED_PARAMETER

fun <T> ofType(x: T): T = x

suspend fun foo() {}
suspend fun foo(s: String) {}

val x1 = ofType<suspend () -> Unit>(::foo)
val x2 = ofType<suspend (String) -> Unit>(::foo)
val x3 = ofType<suspend (Int) -> Unit>(::<!NI;DEBUG_INFO_MISSING_UNRESOLVED, OI;NONE_APPLICABLE!>foo<!>)