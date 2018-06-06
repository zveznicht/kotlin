// !DIAGNOSTICS: -UNUSED_EXPRESSION -UNUSED_PARAMETER, -REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE
// KT-10036 Ambiguous overload cannot be resolved when using a member function reference in Beta 2, that worked in Beta 1

class OverloadTest {
    suspend fun foo(bar: Boolean) {}
    suspend fun foo(bar: Any?) {}
}

object Literal

suspend inline fun <T : Any> OverloadTest.overload(value: T?, function: suspend OverloadTest.(T) -> Unit) {
    if (value == null) foo(Literal) else function(<!DEBUG_INFO_SMARTCAST!>value<!>)
}

// Overload resolution ambiguity
suspend fun OverloadTest.overloadBoolean(value: Boolean?) = overload(value, OverloadTest::foo)

// Works fine
suspend fun OverloadTest.overloadBoolean2(value: Boolean?) = overload(value) { foo(it) }