// !CHECK_TYPE

import kotlin.reflect.KSuspendFunction1

class A<T>(val t: T) {
    suspend fun foo(): T = t
}

fun bar() {
    val x = A<String>::foo

    checkSubtype<KSuspendFunction1<A<String>, String>>(x)
}
