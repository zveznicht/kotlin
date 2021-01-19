// FILE: result.kt

package kotlin

inline class Result(val value: Any?)

// FILE: box.kt

fun box(): String {
    return Result("OK").value as String
}
