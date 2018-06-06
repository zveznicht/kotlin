// !DIAGNOSTICS: -UNUSED_VARIABLE, -UNUSED_PARAMETER

import kotlin.reflect.KClass

class S {
    suspend fun returnsInt(): Int = 0
    suspend fun returnsString(): String = ""
    suspend fun returnsBoolean(a: Any?): Boolean = false
}

fun test(s: S) {
    val f: suspend () -> Int = s::returnsInt
    val g: suspend () -> String = s::returnsString
    val h: suspend (Any?) -> Boolean = s::returnsBoolean
}
