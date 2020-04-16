// IGNORE_BACKEND_FIR: JVM_IR
class C {
    fun ffff(i: Int) = this
}

fun box(): String {
    42.apply(C()::ffff)
    return "OK"
}