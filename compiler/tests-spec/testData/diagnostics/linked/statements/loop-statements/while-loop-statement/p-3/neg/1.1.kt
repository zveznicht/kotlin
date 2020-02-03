// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 0.1-253
 * PLACE: statements, loop-statements, while-loop-statement -> paragraph 3 -> sentence 1
 * RELEVANT PLACES: statements, loop-statements, while-loop-statement -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: condition expression is not a subtype of kotlin.Boolean.
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val condition: Any = true
    while (condition && "true") {
    }
}

// TESTCASE NUMBER: 2
fun case2() {
    val condition: Boolean? = true
    while (condition) {
    }
}

// FILE: JavaClass.java
public class JavaClass {
    public static Boolean CONDITION = false;
    public static Boolean CONDITION_NULL;
}

// FILE: KotlinClass.kt
// TESTCASE NUMBER: 3
fun case3() {
    //todo:  fix  Test isn't negative because it doesn't contain error elements. TEST CASES: 1, 2, 3
    val c1 = JavaClass.CONDITION
    val c2 = JavaClass.CONDITION_NULL

    while (c1){}
    while (c2){}

    c1 checkType { check<Boolean>() }
    c2 checkType { check<Boolean ?>() }

    <!DEBUG_INFO_EXPRESSION_TYPE("(kotlin.Boolean..kotlin.Boolean?)")!>c1<!>
    <!DEBUG_INFO_EXPRESSION_TYPE("(kotlin.Boolean..kotlin.Boolean?)")!>c2<!>
}