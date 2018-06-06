// !DIAGNOSTICS: -UNUSED_PARAMETER, -UNUSED_VARIABLE

suspend fun baz(i: Int) = i
fun <T> bar(x: T): T = TODO()

fun nullableFun(): (suspend (Int) -> Int)? = null

fun test() {
    val x1: suspend (Int) -> Int = bar(if (true) ::baz else ::baz)
    val x2: suspend (Int) -> Int = bar(nullableFun() ?: ::baz)
    val x3: suspend (Int) -> Int = bar(::baz <!USELESS_ELVIS!>?: ::baz<!>)

    val i = 0
    val x4: suspend (Int) -> Int = bar(when (i) {
                                   10 -> ::baz
                                   20 -> ::baz
                                   else -> ::baz
                               })

    val x5: suspend (Int) -> Int = bar(::baz<!NOT_NULL_ASSERTION_ON_CALLABLE_REFERENCE!>!!<!>)
}

suspend fun test2() {
    (if (true) ::baz else ::baz)(1)
}