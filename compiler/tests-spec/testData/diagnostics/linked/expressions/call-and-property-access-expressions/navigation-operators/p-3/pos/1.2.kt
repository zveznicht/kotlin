// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNREACHABLE_CODE -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-222
 * PLACE: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 1
 * RELEVANT PLACES: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: check case when a.c has semantic when used as an expression: "A fully-qualified type, property or object name"
 */

// TESTCASE NUMBER: 1
// MODULE: mainModule
// FILE: case1Lib.kt
package mainModule
class Case1() { }

// FILE: case1Test.kt
package mainModule
fun case1(x: mainModule.Case1) {
    val y : mainModule.Case1 = mainModule.Case1()
}