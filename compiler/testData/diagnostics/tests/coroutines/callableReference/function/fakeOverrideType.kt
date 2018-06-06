// !CHECK_TYPE

import kotlin.reflect.KSuspendFunction2

open class A {
    suspend fun foo(s: String): String = s
    suspend fun bar(): Int = 0
}

class B : A() {
}


suspend fun test() {
    B::foo checkType { _<KSuspendFunction2<B, String, String>>() }

    (B::bar)(<!TYPE_MISMATCH!>"No."<!>)
}
