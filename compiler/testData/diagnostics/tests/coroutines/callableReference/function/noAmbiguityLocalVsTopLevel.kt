// !DIAGNOSTICS: -UNUSED_EXPRESSION
suspend fun bar() = 42

fun main() {
    suspend fun bar() = 239

    ::bar
}