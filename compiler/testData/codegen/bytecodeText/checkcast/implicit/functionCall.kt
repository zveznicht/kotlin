open class Base {
    fun test(base: Base) {

    }
}

open class Derived : Base()

fun Base.test2(base: Base) {

}


fun test() {
    Derived().test(Derived())
    Derived().test2(Derived())
}

// JVM_TEMPLATES:
// 0 CHECKCAST

// JVM_IR_TEMPLATES:
// 0 CHECKCAST
