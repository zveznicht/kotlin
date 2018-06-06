// !DIAGNOSTICS: -UNUSED_PARAMETER

suspend fun foo() {}
suspend fun foo(s: String) {}

fun fn(f: suspend () -> Unit) {}

fun test() {
    fn(::foo)
}