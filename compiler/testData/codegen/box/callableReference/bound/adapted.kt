// IGNORE_BACKEND_FIR: JVM_IR
class C {
    fun ffff(i: Int, s: String = "OK") = s
}

fun box(): String = 42.run(C()::ffff)