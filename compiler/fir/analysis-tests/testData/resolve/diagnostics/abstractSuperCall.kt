open class C {
    fun h() {}
}

abstract class A : C() {
    abstract fun f()

    fun t() {
        super.h()
    }
}

class B : A() {
    override fun f() {

    }

    fun g() {
        super.<!ABSTRACT_SUPER_CALL!>f()<!>
        super.t()
    }
}

abstract class J : A() {
    fun r() {
        super.<!ABSTRACT_SUPER_CALL!>f()<!>
        super.t()
    }
}
