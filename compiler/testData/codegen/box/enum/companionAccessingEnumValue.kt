private var logs = ""

enum class Foo(val text: String) {
    FOO("foo"),
    BAR("bar"),
    PING("foo");

    init {
        logs += "B"
    }

    companion object {
        val first = values()[0]
        init {
            logs += first.text
        }
    }

    init {
        logs += "C"
    }
}

fun box(): String {
    Foo.FOO

    if (Foo.first !== Foo.FOO) return "FAIL 0: ${Foo.first}"

    if (logs != "BCBCBCfoo") return "FAIL 1: ${logs}"

    return "OK"
}