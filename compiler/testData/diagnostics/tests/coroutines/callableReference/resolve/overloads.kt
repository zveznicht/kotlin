// !CHECK_TYPE
// !DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_EXPRESSION
import kotlin.reflect.KSuspendFunction1

open class A {
    open suspend fun bar() {}

    suspend fun bas() {}
}
class B: A() {
    override suspend fun bar() {}

    suspend fun bas(i: Int) {}
}

suspend fun A.foo() {}
suspend fun B.foo() {}

suspend fun fas() {}
suspend fun fas(i: Int = 1) {}

fun test() {
    B::foo // todo KT-9601 Chose maximally specific function in callable reference

    B::bar checkType { _<KSuspendFunction1<B, Unit>>() }

    B::<!OVERLOAD_RESOLUTION_AMBIGUITY!>bas<!>

    ::<!OVERLOAD_RESOLUTION_AMBIGUITY!>fas<!>
}