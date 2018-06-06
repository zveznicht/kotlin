class S {
    suspend fun returnsInt(): Int = 0
}

val a = S()
val b = S()

suspend fun Int.returnsString(): String = ""

fun unusedExpression(s: S) {
    <!UNUSED_EXPRESSION!>s::returnsInt<!>
    <!UNUSED_EXPRESSION!>s::class<!>
}

suspend fun noUnusedParameter(s: S): Int {
    val f = s::returnsInt
    return f()
}

fun unreachableCode(): S {
    (if (true) return a else return b)<!UNREACHABLE_CODE!>::<!UNRESOLVED_REFERENCE!>returnsInt<!><!>
    <!UNREACHABLE_CODE!>return a<!>
}

fun unreachableCodeInLoop(): Int {
    while (true) {
        (break)<!UNREACHABLE_CODE!>::returnsString<!>
        <!UNREACHABLE_CODE!>return 1<!>
    }
    return 2
}
