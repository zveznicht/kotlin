// !LANGUAGE: +NewInference
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_RUNTIME
// KJS_WITH_FULL_RUNTIME
// IGNORE_BACKEND: JS

fun foo(vararg l: Int, s: String): String =
    if (l.size == 2) s else "Fail"

inline fun bar(f: (IntArray, String) -> String): String = f(intArrayOf(1, 2), "OK")

fun box(): String = bar(::foo)
