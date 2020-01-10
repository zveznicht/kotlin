// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNREACHABLE_CODE -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 0.1-222
 * PLACE: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 1
 * RELEVANT PLACES: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: check case when a.c has semantic when used as an expression: "A fully-qualified type, property or object name"
 */

// MODULE: mainModule
// FILE: case1Lib.kt
package mainModule
class Case1() { }

// FILE: case1Test.kt
package mainModule

// TESTCASE NUMBER: 1
fun case1(x: mainModule.<!UNRESOLVED_REFERENCE!>CaseUnknown<!>, x1: <!UNRESOLVED_REFERENCE!>unknownPack<!>.<!DEBUG_INFO_MISSING_UNRESOLVED!>Case1<!>) {
    val y : mainModule.<!UNRESOLVED_REFERENCE!>CaseUnknown<!> = mainModule.Case1()
    val z : mainModule.Case1 = mainModule.<!UNRESOLVED_REFERENCE!>CaseUnknown<!>()

    val y1 : <!UNRESOLVED_REFERENCE!>unknownPack<!>.<!DEBUG_INFO_MISSING_UNRESOLVED!>Case2<!> = mainModule.Case1()
    val z1 : mainModule.Case1 = <!UNRESOLVED_REFERENCE!>unknownPack<!>.<!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>Case1<!>()
}
