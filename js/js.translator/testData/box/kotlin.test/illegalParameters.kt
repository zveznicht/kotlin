// IGNORE_BACKEND: JS
// KJS_WITH_FULL_RUNTIME
// SKIP_DCE_DRIVEN

import common.*
import kotlin.test.Test

class BadClass(id: Int) {
    @Test
    fun foo() {}
}

private class BadUnreachableClass {
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

// non-reachable scenarios are tested in nested.kt
class Outer {
    private companion object {
        object InnerCompanion {
            @Test
            fun innerCompanionTest() {
            }
        }
    }
}

fun box() = checkLog {
        suite("BadClass") {
            test("foo") {
                caught("Test class BadClass must declare a constructor (publicly or internally reachable) with no explicit parameters")
            }
        }
        suite("BadUnreachableClass") {
            test("foo") {
                caught("Test class BadUnreachableClass must declare a constructor (publicly or internally reachable) with no explicit parameters")
            }
        }
        suite("BadConstructorClass") {
            test("foo") {
                caught("Test class BadConstructorClass must declare a constructor (publicly or internally reachable) with no explicit parameters")
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
                    caught("Test class BadNestedClass.NestedTestClass must declare a constructor (publicly or internally reachable) with no explicit parameters")
                }
            }
        }
        suite("BadMethodClass") {
            test("foo") {
                caught("Test method BadMethodClass::foo should be either (publicly or internally reachable) and can not have parameters")
            }
            test("ping") {
                caught("Test method BadMethodClass::ping should be either (publicly or internally reachable) and can not have parameters")
            }
        }
        suite("Outer") {
            suite("Companion") {
                suite("InnerCompanion") {
                    test("innerCompanionTest") {
                        caught("Test object Outer.Companion.InnerCompanion must be publicly or internally reachable")
                    }
                }
            }
        }
}