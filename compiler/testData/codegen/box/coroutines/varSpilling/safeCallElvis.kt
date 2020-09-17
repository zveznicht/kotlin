// WITH_RUNTIME

class Log {
    fun error(message: Any?) {}
}

private val log = Log()

class C {
    fun method() {}
}

fun <T : Any> df(r: suspend (T) -> Unit) {
}

fun foo(c: C?) {
    df<String> {
        c?.method() ?: log.error("OK")
    }
}

fun box(): String {
    foo(null)
    return "OK"
}