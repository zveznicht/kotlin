// ERROR: Variable 'v' is never used

fun f() {
    val v<caret> = -predicate
    println(v())
}