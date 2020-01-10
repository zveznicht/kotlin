// !LANGUAGE: -ProhibitVisibilityOfNestedClassifiersFromSupertypesOfCompanion

open class A {
    inner class B {
        fun foo() {}
    }

    inner class D

    companion object {
        class B {
            fun bar() {}
        }

        class C
    }

    init {
        B().foo()
        B().<!UNRESOLVED_REFERENCE!>bar<!>()

        D()
        C()
    }
}

class E: A() {
    init {
        B().foo()
        B().<!UNRESOLVED_REFERENCE!>bar<!>()

        D()
        C()
    }

    object Z {
        init {
            B().foo()
            B().<!UNRESOLVED_REFERENCE!>bar<!>()

            D()
            C()
        }
    }
}

class F: A() {
    class B {
        fun fas() {}
    }
    inner class D {
        fun f() {}
    }

    init {
        B().<!UNRESOLVED_REFERENCE!>fas<!>()
        D().<!UNRESOLVED_REFERENCE!>f<!>()
    }

    companion object {
        init {
            B().<!UNRESOLVED_REFERENCE!>fas<!>()
            D().<!UNRESOLVED_REFERENCE!>f<!>()
        }
    }
}
