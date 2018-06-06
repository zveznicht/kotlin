// !WITH_NEW_INFERENCE
// !DIAGNOSTICS: -UNUSED_VARIABLE, -UNUSED_PARAMETER

suspend fun foo(i: Int) {}
suspend fun foo(s: String) {}
fun <T> id(x: T): T = x
fun <T> baz(x: T, y: T): T = TODO()

fun test() {
    val x1: suspend (Int) -> Unit = id(id(::foo))
    val x2: suspend (Int) -> Unit = baz(id(::foo), ::foo)
    val x3: suspend (Int) -> Unit = baz(id(::foo), id(id(::foo)))
    val x4: suspend (String) -> Unit = baz(id(::foo), id(id(::foo)))
    val x5: suspend (Double) -> Unit = baz(id(::<!NI;DEBUG_INFO_MISSING_UNRESOLVED, OI;NONE_APPLICABLE!>foo<!>), id(id(::<!NI;DEBUG_INFO_MISSING_UNRESOLVED, OI;NONE_APPLICABLE!>foo<!>)))


    id<suspend (Int) -> Unit>(id(id(::foo)))
    id(id<suspend (Int) -> Unit>(::foo))
    baz<suspend (Int) -> Unit>(id(::foo), id(id(::foo)))
    baz(id(::foo), id(id<suspend (Int) -> Unit>(::foo)))
    baz(id(::foo), id<suspend (Int) -> Unit>(id(::foo)))
}