// MODULE: lib
// FILE: lib.kt

class A {}

// MODULE: main(lib)
// FILE: main.kt
// ASYNC_IMPORT: lib

fun test() {
    <!WRONG_ASYNC_MODULE_REFERENCE!>A()<!>
}

suspend fun testSuspend() {
    A()
}

fun testIntrinsics() {
    runIfLoaded {
        A()
    }

    runIfLoaded({ run {
        A()
    } }, {})
}