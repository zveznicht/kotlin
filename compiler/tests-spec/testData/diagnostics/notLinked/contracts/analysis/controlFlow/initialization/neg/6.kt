// !USE_EXPERIMENTAL: kotlin.contracts.ExperimentalContracts
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS NOT LINKED SPEC TEST (NEGATIVE)
 *
 * SECTIONS: contracts, analysis, controlFlow, initialization
 * NUMBER: 6
 * DESCRIPTION: Check the presence of CallsInPlace effect on the lambda in the parentheses.
 * HELPERS: contractFunctions
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val value_1: Int
    funWithExactlyOnceCallsInPlace({ value_1 = 11 })
    value_1.inc()
}

// TESTCASE NUMBER: 2
fun case_2() {
    var value_1: Int
    funWithAtLeastOnceCallsInPlace({ value_1 = 11 })
    value_1.inc()
}
