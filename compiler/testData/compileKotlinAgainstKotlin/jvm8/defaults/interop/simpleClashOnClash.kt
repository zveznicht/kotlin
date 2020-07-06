// FULL_JDK
// FILE: 1.kt
interface OldInterface  {
    fun call(): String {
        return ""
    }
}

open class OldClass : OldInterface {

}

// FILE: main.kt
// !JVM_DEFAULT_MODE: all
// JVM_TARGET: 1.8
interface NewInterface : OldInterface  {
    override fun call() = "K" + super.call()
}

open class NewClass: OldClass(), NewInterface {

}

interface NewInterface2 : NewInterface  {
    override fun call() = "O" + super.call()
}

class NewClass2: NewClass(), NewInterface2 {

}

fun box(): String {
    return NewClass2().call()
}
