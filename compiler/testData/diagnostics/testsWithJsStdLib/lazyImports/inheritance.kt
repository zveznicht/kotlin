// MODULE: lib
// FILE: lib.kt

open class C

interface I

// MODULE: main(lib)
// FILE: main.kt
// ASYNC_IMPORT: lib

<!WRONG_ASYNC_MODULE_REFERENCE!>class CC : C()<!>

<!WRONG_ASYNC_MODULE_REFERENCE!>class II : I<!>

<!WRONG_ASYNC_MODULE_REFERENCE!>object OC<!> : C()

<!WRONG_ASYNC_MODULE_REFERENCE!>object OI<!> : I

fun test() {
    <!WRONG_ASYNC_MODULE_REFERENCE!>class LC : C()<!>

    <!WRONG_ASYNC_MODULE_REFERENCE!>class LI : I<!>

    <!WRONG_ASYNC_MODULE_REFERENCE!>object<!> : C() {}

    <!WRONG_ASYNC_MODULE_REFERENCE!>object<!> : I {}
}

suspend fun testSuspend() {
    <!WRONG_ASYNC_MODULE_REFERENCE!>class LC : C()<!>

    <!WRONG_ASYNC_MODULE_REFERENCE!>class LI : I<!>

    <!WRONG_ASYNC_MODULE_REFERENCE!>object<!> : C() {}

    <!WRONG_ASYNC_MODULE_REFERENCE!>object<!> : I {}
}

fun testIntrinsics() {
    runIfLoaded {
        <!WRONG_ASYNC_MODULE_REFERENCE!>class LC : C()<!>

        <!WRONG_ASYNC_MODULE_REFERENCE!>class LI : I<!>

        <!WRONG_ASYNC_MODULE_REFERENCE!>object<!> : C() {}

        <!WRONG_ASYNC_MODULE_REFERENCE!>object<!> : I {}
    }

    runIfLoaded({ run {
        <!WRONG_ASYNC_MODULE_REFERENCE!>class LC : C()<!>

        <!WRONG_ASYNC_MODULE_REFERENCE!>class LI : I<!>

        <!WRONG_ASYNC_MODULE_REFERENCE!>object<!> : C() {}

        <!WRONG_ASYNC_MODULE_REFERENCE!>object<!> : I {}
    } }, {})
}