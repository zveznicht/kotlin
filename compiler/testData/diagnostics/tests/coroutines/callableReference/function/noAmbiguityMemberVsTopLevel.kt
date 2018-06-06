// !CHECK_TYPE
// !LANGUAGE: +CallableReferencesToClassMembersWithEmptyLHS, +Coroutines

import kotlin.reflect.KSuspendFunction0

fun expectSuspendFunction0Unit(f: suspend () -> Unit) = f
fun expectSuspendFunction0String(f: suspend () -> String) = f
fun expectSuspendFunction1Unit(f: suspend (A) -> Unit) = f
fun expectSuspendFunction1String(f: suspend (A) -> String) = f

suspend fun foo(): String = ""

class A {
    suspend fun foo() {}
    
    fun main() {
        val x = ::foo

        checkSubtype<KSuspendFunction0<Unit>>(x)

        expectSuspendFunction0Unit(x)
        expectSuspendFunction0String(<!TYPE_MISMATCH!>x<!>)
        expectSuspendFunction1Unit(<!TYPE_MISMATCH!>x<!>)
        expectSuspendFunction1String(<!TYPE_MISMATCH!>x<!>)

        expectSuspendFunction0Unit(::foo)
        expectSuspendFunction0String(::foo)
        expectSuspendFunction1Unit(<!TYPE_MISMATCH!>::foo<!>)
        expectSuspendFunction1String(<!TYPE_MISMATCH!>::foo<!>)
    }
}
