// ERROR: Variable 'v' is never used

fun f() {
    val <caret>v = foo[1]
    println(v())
}