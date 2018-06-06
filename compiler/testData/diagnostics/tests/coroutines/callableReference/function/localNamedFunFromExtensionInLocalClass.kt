// !CHECK_TYPE

import kotlin.reflect.*

class A

fun main() {
    suspend fun foo() {}
    suspend fun bar(<!UNUSED_PARAMETER!>x<!>: Int) {}
    suspend fun baz() = "OK"
    
    class B {
        fun A.ext() {
            val x = ::foo
            val y = ::bar
            val z = ::baz

            checkSubtype<KSuspendFunction0<Unit>>(x)
            checkSubtype<KSuspendFunction1<Int, Unit>>(y)
            checkSubtype<KSuspendFunction0<String>>(z)
        }
    }
}
