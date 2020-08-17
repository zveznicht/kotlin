open class Base
class Derived: Base()

fun box(s: Derived?) {
    if (s != null) {
        test(s)
    }
}

fun test(s: Base) {}

// 0 CHECKCAST