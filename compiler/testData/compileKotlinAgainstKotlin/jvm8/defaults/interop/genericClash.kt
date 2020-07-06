// FULL_JDK
// FILE: 1.kt
interface OldInterface<T>  {
    fun call(p: T): T {
        return p
    }
}

open class OldClass : OldInterface<String> {

}

// FILE: main.kt
// !JVM_DEFAULT_MODE: all
// JVM_TARGET: 1.8
interface NewInterface : OldInterface<String>  {
    override fun call(p: String) = "O" + super.call(p)
}

class NewClass: OldClass(), NewInterface {

}

fun box(): String {
    return NewClass().call("K")
}
