// MODULE: lib
// FILE: lib.kt

fun fn() {}

// MODULE: main(lib)
// FILE: main.kt
// ASYNC_IMPORT: lib

fun test() {
    (::<!WRONG_ASYNC_MODULE_REFERENCE!>fn<!>)()
}

suspend fun testSuspend() {
    (::fn)()
}

fun testIntrinsics() {
    runIfLoaded {
        (::fn)()
    }

    runIfLoaded({ run {
        (::fn)()
    } }, {})
}