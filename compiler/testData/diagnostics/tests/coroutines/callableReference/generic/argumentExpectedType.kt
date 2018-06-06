// !DIAGNOSTICS: -UNUSED_PARAMETER

class Case<T>
suspend fun <T> test(case: Case<T>) {}
fun runTest(method: suspend (Case<Any>) -> Unit) {}

fun <T> runTestGeneric(f: suspend (Case<T>) -> Unit) {}

fun test() {
    runTest(::test)
    runTestGeneric<Int>(::test)
}