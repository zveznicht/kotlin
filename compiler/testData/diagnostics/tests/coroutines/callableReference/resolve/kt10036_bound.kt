// !DIAGNOSTICS: -UNUSED_EXPRESSION -UNUSED_PARAMETER, -REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE

class OverloadTest {
    suspend fun foo(bar: Boolean) {}
    suspend fun foo(bar: Any?) {}
}

suspend inline fun <T : Any> OverloadTest.overload(value: T?, function: suspend (T) -> Unit) {
}

suspend fun OverloadTest.overloadBoolean(value: Boolean?) = overload(value, OverloadTest()::foo)
