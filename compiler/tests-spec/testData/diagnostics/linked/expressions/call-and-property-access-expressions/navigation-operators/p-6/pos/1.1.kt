// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-222
 * PLACE: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 6 -> sentence 1
 * RELEVANT PLACES: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 7 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION:
 */


// TESTCASE NUMBER: 1
class Case1() {
    fun foo(): Int = 100
    fun zoo(x: String): Int = 100
    val a: () -> Int = { 101 }
}

fun case1() {
    val x = Case1()
    val foores = x.foo()
    val zoores = x.zoo("")
    val ares: Int = x.a()
}