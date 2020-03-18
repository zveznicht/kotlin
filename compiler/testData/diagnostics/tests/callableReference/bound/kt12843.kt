// !WITH_NEW_INFERENCE
// NI_EXPECTED_FILE

class Foo {
    fun bar() {}
    fun f() = <!UNRESOLVED_REFERENCE!>Unresolved<!>()::<!OI;DEBUG_INFO_MISSING_UNRESOLVED!>bar<!>
}

val f: () -> Unit = <!UNRESOLVED_REFERENCE!>Unresolved<!>()::<!NI;UNRESOLVED_REFERENCE, OI;DEBUG_INFO_MISSING_UNRESOLVED!>foo<!>
