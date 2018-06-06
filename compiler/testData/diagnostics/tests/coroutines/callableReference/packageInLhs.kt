// !DIAGNOSTICS: -UNUSED_EXPRESSION
// FILE: simpleName.kt

package foo

suspend fun test() {
    <!EXPRESSION_EXPECTED_PACKAGE_FOUND!>foo<!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>test<!>
}

// FILE: qualifiedName.kt

package foo.bar

suspend fun test() {
    foo.<!EXPRESSION_EXPECTED_PACKAGE_FOUND!>bar<!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>test<!>
}
