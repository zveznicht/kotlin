// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 0.1-222
 * PLACE: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 6 -> sentence 1
 * RELEVANT PLACES: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 7 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION:
 */

class Case() {
    fun foo(): Int = 100
    fun zoo(x: String): Int = 100
    val a: () -> Int = { 101 }
}

// TESTCASE NUMBER: 1
fun case1() {
    val x = Case()
    val foores = x.foo(<!TOO_MANY_ARGUMENTS!>""<!>)
    val zoores = x.zoo(<!NO_VALUE_FOR_PARAMETER!>)<!>
    val ares: Int = x.a(<!TOO_MANY_ARGUMENTS!>1<!>)
}

// TESTCASE NUMBER: 2
fun case2() {
    val x = Case()
    val foores = x.<!UNRESOLVED_REFERENCE!>foo1<!>()
    val zoores = x.<!UNRESOLVED_REFERENCE!>zoo1<!>("")
    val ares: Int = x.<!UNRESOLVED_REFERENCE!>a1<!>()
}