// !DIAGNOSTICS: -UNUSED_PARAMETER
// !LANGUAGE: -UseCorrectExecutionOrderForVarargArguments
// WITH_RUNTIME

fun foo(vararg x: Unit, y: Any) {}

fun main() {
    var acc1 = ""
    var acc2 = ""
    var acc3 = ""
    var acc4 = ""
    foo({ acc1 += "1" }(), y = { acc1 += "2" }())
    foo(<!CHANGING_ARGUMENTS_EXECUTION_ORDER_FOR_NAMED_VARARGS!>x = *<!REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION!>arrayOf({ acc2 += "1" }())<!><!>, y = { acc2 += "2" }())
    foo(<!CHANGING_ARGUMENTS_EXECUTION_ORDER_FOR_NAMED_VARARGS!>x = arrayOf({ acc3 += "1" }())<!>, y = { acc3 += "2" }())
    foo(*arrayOf({ acc4 += "1" }()), y = { acc4 += "2" }())
}
