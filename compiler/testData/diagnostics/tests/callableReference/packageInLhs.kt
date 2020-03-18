// !DIAGNOSTICS: -UNUSED_EXPRESSION
// !WITH_NEW_INFERENCE

// FILE: simpleName.kt

package foo

fun test() {
    <!EXPRESSION_EXPECTED_PACKAGE_FOUND!>foo<!>::<!OI;DEBUG_INFO_MISSING_UNRESOLVED!>test<!>
}

// FILE: qualifiedName.kt

package foo.bar

fun test() {
    foo.<!EXPRESSION_EXPECTED_PACKAGE_FOUND!>bar<!>::<!OI;DEBUG_INFO_MISSING_UNRESOLVED!>test<!>
}
