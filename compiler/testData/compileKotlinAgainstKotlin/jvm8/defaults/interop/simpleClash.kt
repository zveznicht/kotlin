// FULL_JDK
// FILE: 1.kt
interface OldInterface  {
    fun call(): String {
        return "K"
    }
}

open class OldClass : OldInterface {

}

// FILE: main.kt
// !JVM_DEFAULT_MODE: all
// JVM_TARGET: 1.8
interface NewInterface : OldInterface  {
    override fun call() = "O" + super.call()
}

class NewClass: OldClass(), NewInterface {

}

fun box(): String {
    return NewClass().call()
}
