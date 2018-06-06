// !DIAGNOSTICS: -UNUSED_EXPRESSION, -UNUSED_PARAMETER
// !WITH_NEW_INFERENCE

class A

fun test1() {
    val <!UNUSED_VARIABLE!>foo<!> = ::<!UNRESOLVED_REFERENCE!>foo<!>

    ::<!UNRESOLVED_REFERENCE!>bar<!>

    A::<!UNRESOLVED_REFERENCE!>bar<!>

    <!UNRESOLVED_REFERENCE!>B<!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar<!>
}

suspend fun test2() {
    suspend fun foo(x: Any) {}
    suspend fun foo() {}

    <!UNRESOLVED_REFERENCE!>Unresolved<!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo<!>
    foo(<!UNRESOLVED_REFERENCE!>Unresolved<!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo<!>)
    foo(<!UNRESOLVED_REFERENCE!>Unresolved<!>::<!NI;UNRESOLVED_REFERENCE, OI;DEBUG_INFO_MISSING_UNRESOLVED!>unresolved<!>)
    ::<!UNRESOLVED_REFERENCE!>unresolved<!>
}