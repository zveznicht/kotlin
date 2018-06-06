// !DIAGNOSTICS: -UNUSED_PARAMETER
fun <T> foo(t: T, x: suspend (() -> Unit) -> Unit) {}

suspend fun <T> bar(s: T) {}
fun <T> complex(t: T, f: suspend (T) -> Unit) {}

fun test1() {
    foo(1, ::bar)

    complex(1, ::bar)
}

fun <R> test2(x: R) {
    foo(x, ::bar)

    complex(x, ::bar)
}