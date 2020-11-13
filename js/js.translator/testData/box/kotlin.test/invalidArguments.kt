// KJS_WITH_FULL_RUNTIME
// SKIP_DCE_DRIVEN

import common.*
import kotlin.test.Test

class BadClass(id: Int) {
    @Test
    fun foo() {}
}

class BadClass2() {
    constructor(id: Int): this()
    @Test
    fun foo() {}
}

class BadMethodClass() {
    @Test
    fun foo(id: Int) {}
}

fun box() = checkLog {
        suite("BadClass") {
            test("foo") {
                caught("Test class BadClass must declare a single constructor with no explicit params")
            }
        }
        suite("BadClass2") {
            test("foo") {
                caught("Test class BadClass2 must declare a single constructor with no explicit params")
            }
        }
        suite("BadMethodClass") {
            test("foo") {
                caught("Test method BadMethodClass::foo can not have params")
            }
        }
}