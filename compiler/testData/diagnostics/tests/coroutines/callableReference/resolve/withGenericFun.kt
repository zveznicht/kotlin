// !WITH_NEW_INFERENCE
// !DIAGNOSTICS: -UNUSED_PARAMETER, -UNUSED_VARIABLE
// NI_EXPECTED_FILE

suspend fun <T, R> apply(x: T, f: suspend (T) -> R): R = f(x)

suspend fun foo(i: Int) {}
suspend fun foo(s: String) {}

suspend fun test() {
    val x1 = apply(1, ::foo)
    val x2 = apply("hello", ::foo)
    val <!NI;IMPLICIT_NOTHING_PROPERTY_TYPE!>x3<!> = <!OI;TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>apply<!>(true, ::<!NI;DEBUG_INFO_MISSING_UNRESOLVED, OI;NONE_APPLICABLE!>foo<!>)
}
