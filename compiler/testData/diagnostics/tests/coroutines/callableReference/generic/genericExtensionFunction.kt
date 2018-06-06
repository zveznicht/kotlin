// !WITH_NEW_INFERENCE
// !CHECK_TYPE
// !DIAGNOSTICS: -UNUSED_PARAMETER, -UNUSED_VARIABLE

class Wrapper

suspend fun <R, S> Wrapper.foo(x: R): S = TODO()
suspend fun Wrapper.fooIntString(x: Int): String = ""
suspend fun <T> Wrapper.fooReturnString(x: T): String = ""
suspend fun <T> Wrapper.fooTakeInt(x: Int): T = TODO()

fun <T, R, S> bar(f: suspend T.(R) -> S): Tripple<T, R, S> = TODO()
fun <T, R, S> baz(x: T, y: R, z: S, f: suspend T.(R) -> S): Tripple<T, R, S> = TODO()

class Tripple<A, B, C>(val a: A, val b: B, val c: C)

fun test1() {
    val x: suspend Wrapper.(String) -> Boolean = Wrapper::foo
    bar<Wrapper, Double, Float>(Wrapper::foo).checkType { _<Tripple<Wrapper, Double, Float>>() }
    bar(Wrapper::fooIntString).checkType { _<Tripple<Wrapper, Int, String>>() }
}

fun <T> test2() {
    bar<Wrapper, Int, String>(Wrapper::fooReturnString).checkType { _<Tripple<Wrapper, Int, String>>() }
    bar<Wrapper, T, String>(Wrapper::fooReturnString).checkType { _<Tripple<Wrapper, T, String>>() }
    bar<Wrapper, T, T>(<!NI;TYPE_MISMATCH!>Wrapper::<!OI;TYPE_INFERENCE_EXPECTED_TYPE_MISMATCH!>fooReturnString<!><!>)
    bar<Wrapper, Int, Int>(<!NI;TYPE_MISMATCH!>Wrapper::<!OI;TYPE_INFERENCE_EXPECTED_TYPE_MISMATCH!>fooReturnString<!><!>)

    bar<Wrapper, Int, T>(Wrapper::fooTakeInt).checkType { _<Tripple<Wrapper, Int, T>>() }
    bar<Wrapper, Int, String>(Wrapper::fooTakeInt).checkType { _<Tripple<Wrapper, Int, String>>() }
}