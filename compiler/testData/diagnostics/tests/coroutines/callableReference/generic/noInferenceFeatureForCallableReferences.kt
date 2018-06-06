// !LANGUAGE: -TypeInferenceOnGenericsForCallableReferences
// !DIAGNOSTICS: -UNUSED_VARIABLE, -UNUSED_PARAMETER, -EXPERIMENTAL_FEATURE_WARNING

suspend fun <T> bar(s: T) {}
fun <T> complex(t: T, f: suspend (T) -> Unit) {}
fun <T> simple(f: suspend (T) -> Unit) {}

fun test1() {
    complex(1, <!UNSUPPORTED_FEATURE!>::bar<!>)
    simple<String>(<!UNSUPPORTED_FEATURE!>::bar<!>)
}

// ---

fun <T> takeFun(f: suspend (T) -> Unit) {}
fun <T, R> callFun(f: suspend (T) -> R): R = TODO()

suspend fun <T> foo(s: T) {}

open class Wrapper<T>(val value: T)
suspend fun <T, R : Wrapper<in T>> createWrapper(s: T): R = TODO()

fun <T> Wrapper<T>.baz(transform: suspend (T) -> Unit): T = TODO()

fun test2() {
    takeFun<String>(<!UNSUPPORTED_FEATURE!>::foo<!>)

    callFun<String, Wrapper<String>>(<!UNSUPPORTED_FEATURE!>::createWrapper<!>)
    callFun<Int, Wrapper<Number>>(<!UNSUPPORTED_FEATURE!>::createWrapper<!>)
    callFun<String, Wrapper<*>>(<!UNSUPPORTED_FEATURE!>::createWrapper<!>)

    callFun<Int, Wrapper<Int>>(<!UNSUPPORTED_FEATURE!>::createWrapper<!>).baz(<!UNSUPPORTED_FEATURE!>::foo<!>)
}

// ---

class A1 {
    suspend fun <T> a1(t: T): Unit {}
    fun test1(): suspend (String) -> Unit = A1()::a1
}

class A2 {
    suspend fun <K, V> a2(key: K): V = TODO()

    fun test1(): suspend (String) -> Unit = A2()::a2
    fun <T3> test2(): suspend (T3) -> T3 = A2()::a2
}

// ---

suspend fun foo1(x: Int?) {}
suspend fun foo1(y: String?) {}
suspend fun foo1(z: Boolean) {}

suspend fun <T> baz1(element: suspend (T) -> Unit): T? = null

suspend fun test4() {
    val a1: Int? = baz1(::foo1)
    val a2: String? = baz1(::foo1)
}