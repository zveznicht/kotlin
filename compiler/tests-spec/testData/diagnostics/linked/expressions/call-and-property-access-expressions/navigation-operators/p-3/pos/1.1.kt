// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNREACHABLE_CODE -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-222
 * PLACE: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 1
 * RELEVANT PLACES: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 3 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: check case when a.c has semantic when used as an expression: "A fully-qualified type, property or object name"
 */

// TESTCASE NUMBER: 1
fun case1( x : kotlin.Nothing) {
    val b = kotlin.Unit;
    val a : kotlin.Any = x
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
    Case2.x
    Case2.getY()
}
