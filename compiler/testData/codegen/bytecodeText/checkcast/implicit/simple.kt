open class Base
open class Derived : Base()

@JvmField
var property: Base = Base()

fun test(array: Array<Base>) {
    property = Derived()
    array[0] = Derived()

    var captured = Base();
    {
        captured = Derived()
    }()
}

// 1 CHECKCAST
// 1 CHECKCAST kotlin/jvm/functions/Function0