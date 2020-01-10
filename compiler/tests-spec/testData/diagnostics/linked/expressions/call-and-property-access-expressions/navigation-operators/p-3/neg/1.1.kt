// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNREACHABLE_CODE -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 0.1-222
 * PLACE: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 1
 * RELEVANT PLACES: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: check case when a.c has semantic when used as an expression: "A fully-qualified type, property or object name"
 */

// TESTCASE NUMBER: 1
fun case1( x : kotlin.<!UNRESOLVED_REFERENCE!>X<!>) {
    val b = kotlin.<!UNRESOLVED_REFERENCE!>X<!>;
    val a : kotlin.Any = <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>x<!>
}

// TESTCASE NUMBER: 2
class Case2() {
    companion object {
        const val x = "This is x"
        fun getY(): String {
            return "This is Y"
        }
    }
}


fun case2() {
    Case2.<!UNRESOLVED_REFERENCE!>x1<!>
    Case2.<!UNRESOLVED_REFERENCE!>getY1<!>()

    <!UNRESOLVED_REFERENCE!>CaseUnknown<!>.<!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>x<!>
    <!UNRESOLVED_REFERENCE!>CaseUnknown<!>.<!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>getY<!>()
}