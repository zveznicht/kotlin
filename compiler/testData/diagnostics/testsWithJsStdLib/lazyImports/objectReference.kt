// MODULE: lib
// FILE: lib.kt

object O {
    fun fn() {}
}

class A {
    companion object {
        fun cfn() {}
    }
}

// MODULE: main(lib)
// FILE: main.kt
// ASYNC_IMPORT: lib

fun test() {
    <!WRONG_ASYNC_MODULE_REFERENCE!>O<!>
    <!WRONG_ASYNC_MODULE_REFERENCE!>O<!>.fn()
    <!WRONG_ASYNC_MODULE_REFERENCE!>A<!>
    <!WRONG_ASYNC_MODULE_REFERENCE, WRONG_ASYNC_MODULE_REFERENCE!>A<!>.cfn()
}

suspend fun testSuspend() {
    O
    O.fn()
    A
    A.cfn()
}

val prop = <!WRONG_ASYNC_MODULE_REFERENCE!>O<!>
val prop2 = <!WRONG_ASYNC_MODULE_REFERENCE!>O<!>.fn()
val prop3 = <!WRONG_ASYNC_MODULE_REFERENCE!>A<!>
val prop4 = <!WRONG_ASYNC_MODULE_REFERENCE, WRONG_ASYNC_MODULE_REFERENCE!>A<!>.cfn()

fun testIntrinsics() {
    runIfLoaded {
        O
        O.fn()
        A
        A.cfn()
    }

    runIfLoaded({
        run {
            O
            O.fn()
            A
            A.cfn()
        } }, {})
}