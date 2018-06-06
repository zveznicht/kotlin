// !DIAGNOSTICS: -UNUSED_VARIABLE
// !CHECK_TYPE

import kotlin.reflect.KSuspendFunction0

suspend fun test() {
    val a = if (true) {
        val x = 1
        "".length
        ::foo
    } else {
        ::foo
    }
    a checkType {  _<KSuspendFunction0<Int>>() }
}

suspend fun foo(): Int = 0