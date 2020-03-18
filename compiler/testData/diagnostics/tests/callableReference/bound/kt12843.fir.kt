// !WITH_NEW_INFERENCE
// NI_EXPECTED_FILE

class Foo {
    fun bar() {}
    fun f() = <!UNRESOLVED_REFERENCE!><!UNRESOLVED_REFERENCE!>Unresolved<!>()::bar<!>
}

val f: () -> Unit = <!UNRESOLVED_REFERENCE!><!UNRESOLVED_REFERENCE!>Unresolved<!>()::foo<!>
