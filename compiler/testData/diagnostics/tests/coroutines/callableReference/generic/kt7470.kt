// !DIAGNOSTICS: -UNUSED_VARIABLE


suspend fun <T> shuffle(x: List<T>): List<T> = x

fun bar() {
    val s: suspend (List<String>) -> List<String> = ::shuffle
}