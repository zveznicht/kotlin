// MODULE: lib
// FILE: lib.kt

class C {
    fun fn() {}

    val p = 0
}

interface I {
    fun fn()

    val p: Int
}

// MODULE: main(lib)
// FILE: main.kt
// ASYNC_IMPORT: lib

fun test(c: C, i: I) {
    c.fn()
    c.p
    i.fn()
    i.p
}

suspend fun testSuspend(c: C, i: I) {
    c.fn()
    c.p
    i.fn()
    i.p
}

fun testIntrinsics(c: C, i: I) {
    runIfLoaded {
        c.fn()
        c.p
        i.fn()
        i.p
    }

    runIfLoaded({ run {
                    c.fn()
                    c.p
                    i.fn()
                    i.p
                } }, {})
}