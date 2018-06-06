// !WITH_NEW_INFERENCE
// !DIAGNOSTICS: -UNUSED_PARAMETER, -UNUSED_VARIABLE

suspend fun foo(x: Int) {}
suspend fun foo(y: String) {}

fun <T> bar(x: T, f: suspend (T) -> Unit) {}

fun test2() {
    bar(1, ::foo)
    bar("", ::foo)
    bar(1.0, ::<!NI;DEBUG_INFO_MISSING_UNRESOLVED, OI;NONE_APPLICABLE!>foo<!>)
}
