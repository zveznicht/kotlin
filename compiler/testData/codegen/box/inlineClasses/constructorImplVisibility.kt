// TARGET_BACKEND: JVM
// WITH_REFLECT
// FULL_RUNTIME

val ACC_PUBLIC = 1
val ACC_PRIVATE = 2
val ACC_PROTECTED = 4

inline class IC1 public constructor(val i: Int)
inline class IC2 private constructor(val i: Int)
inline class IC4 protected constructor(val i: Int)

fun box(): String {
    if (IC1::class.java.declaredMethods.single { it.name == "constructor-impl" }.modifiers and ACC_PUBLIC == 0) return "FAIL 1"
    if (IC2::class.java.declaredMethods.single { it.name == "constructor-impl" }.modifiers and ACC_PRIVATE == 0) return "FAIL 2"
    if (IC4::class.java.declaredMethods.single { it.name == "constructor-impl" }.modifiers and ACC_PROTECTED == 0) return "FAIL 4"
    return "OK"
}