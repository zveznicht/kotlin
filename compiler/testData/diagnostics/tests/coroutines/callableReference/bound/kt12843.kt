class Foo {
    suspend fun bar() {}
    suspend fun f() = <!UNRESOLVED_REFERENCE!>Unresolved<!>()::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar<!>
}

val f: suspend () -> Unit = <!UNRESOLVED_REFERENCE!>Unresolved<!>()::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo<!>
