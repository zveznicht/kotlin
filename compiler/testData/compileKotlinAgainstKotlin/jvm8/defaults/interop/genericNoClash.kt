// FULL_JDK
// FILE: 1.kt
interface OldInterface<T>  {
    fun call(p: T): T {
        return null!!
    }
}

open class OldClass<Y> : OldInterface<Y> {

}

// FILE: main.kt
// !JVM_DEFAULT_MODE: all
// JVM_TARGET: 1.8
interface NewInterface : OldInterface<String>  {
    override fun call(p: String) = "O" + super.call(p)
}

class NewClass: OldClass<String>(), NewInterface {

}

fun box(): String {
    try {
        return NewClass().call("K")
    } catch (e: Exception) {
        val stack = e.getStackTrace().map { it.className + "." + it.methodName }
        if (stack[0] != "OldInterface\$DefaultImpls.call") return "fail 0: ${stack[0]}"
        if (stack[1] != "NewInterface.call") return "fail 1: ${stack[1]}"
        if (stack[2] != "NewClass.call") return "fail 2: ${stack[2]}"
        return "OK"
    }
    return "fail"
}
