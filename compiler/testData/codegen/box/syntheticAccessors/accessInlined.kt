class Qq {
    fun test(): String {
        return foo()
    }

    companion object {
        private val OK = "OK"

        private inline fun foo() = OK
    }
}

fun box(): String {
    return Qq().test()
}
