// !DIAGNOSTICS: -UNUSED_VARIABLE
// KT-10968 Callable reference: type inference by function return type

suspend fun <T> getT(): T = null!!

suspend fun getString() = ""

fun test() {
    val a : suspend () -> String = ::getString
    val b : suspend () -> String = ::getT
}
