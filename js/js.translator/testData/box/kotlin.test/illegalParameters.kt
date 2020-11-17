// IGNORE_BACKEND: JS
// KJS_WITH_FULL_RUNTIME
// SKIP_DCE_DRIVEN

import common.*
import kotlin.test.Test

class BadClass(id: Int) {
    @Test
    fun foo() {}
}

class BadConstructorClass private constructor() {
    @Test
    fun foo() {}
}

class GoodClass() {
    constructor(id: Int): this()
    @Test
    fun foo() {}
}

class GoodNestedClass {
    class NestedTestClass {
        @Test
        fun foo() {}

        fun helperMethod(param: String) {}
    }
}

class BadNestedClass {
    class NestedTestClass(id: Int) {
        @Test
        fun foo() {}
    }
}

class BadMethodClass() {
    @Test
    fun foo(id: Int) {}

    @Test
    private fun ping() {}
}

fun box() = checkLog {
        suite("BadClass") {
            test("foo") {
                caught("Test class BadClass must declare a constructor (public or internal) with no explicit parameters")
            }
        }
        suite("BadConstructorClass") {
            test("foo") {
                caught("Test class BadConstructorClass must declare a constructor (public or internal) with no explicit parameters")
            }
        }
        suite("GoodClass") {
            test("foo")
        }
        suite("GoodNestedClass") {
            suite("NestedTestClass") {
                test("foo")
            }
        }
        suite("BadNestedClass") {
            suite("NestedTestClass") {
                test("foo") {
                    caught("Test class BadNestedClass.NestedTestClass must declare a constructor (public or internal) with no explicit parameters")
                }
            }
        }
        suite("BadMethodClass") {
            test("foo") {
                caught("Test method BadMethodClass::foo should be either public or internal and can not have parameters")
            }
            test("ping") {
                caught("Test method BadMethodClass::ping should be either public or internal and can not have parameters")
            }
        }
}