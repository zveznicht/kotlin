// IGNORE_BACKEND: JS
// KJS_WITH_FULL_RUNTIME
// SKIP_DCE_DRIVEN

import common.*
import kotlin.test.Test

class BadClass(id: Int) {
    @Test
    fun foo() {}
}

private class BadPrivateClass {
    @Test
    fun foo() {}
}

class BadProtectedMethodClass {
    @Test
    protected fun foo() {}
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
class OuterWithPrivateCompanion {
    private companion object {
        object InnerCompanion {
            @Test
            fun innerCompanionTest() {
            }
        }
    }
}

class OuterWithPrivateMethod {
    companion object {
        object InnerCompanion {
            @Test
            private fun innerCompanionTest() {
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
        suite("BadPrivateClass") {
            test("foo") {
                caught("Test method BadPrivateClass::foo should be either (publicly or internally reachable) and can not have parameters")
            }
        }
        suite("BadProtectedMethodClass") {
            test("foo") {
                caught("Test method BadProtectedMethodClass::foo should be either (publicly or internally reachable) and can not have parameters")
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
        suite("OuterWithPrivateCompanion") {
            suite("Companion") {
                suite("InnerCompanion") {
                    test("innerCompanionTest") {
                        caught("Test method OuterWithPrivateCompanion.Companion.InnerCompanion::innerCompanionTest should be either (publicly or internally reachable) and can not have parameters")
                    }
                }
            }
        }
        suite("OuterWithPrivateMethod") {
            suite("Companion") {
                suite("InnerCompanion") {
                    test("innerCompanionTest") {
                        caught("Test method OuterWithPrivateMethod.Companion.InnerCompanion::innerCompanionTest should be either (publicly or internally reachable) and can not have parameters")
                    }
                }
            }
        }
}