// MODULE: lib
// FILE: lib.kt

fun fn() {}

val p = 0

// MODULE: main(lib)
// FILE: main.kt
// ASYNC_IMPORT: lib

fun test() {
    <!WRONG_ASYNC_MODULE_REFERENCE!>fn()<!>
    <!WRONG_ASYNC_MODULE_REFERENCE!>p<!>
}

suspend fun testSuspend() {
    fn()
    p
}

fun testIntrinsics() {
    runIfLoaded {
        fn()
        p
    }

    runIfLoaded({ run {
        fn()
        p
    } }, {})
}