// !DIAGNOSTICS: -UNUSED_EXPRESSION

class Unrelated()

class Test {
    init {
        Unrelated::<!UNRESOLVED_REFERENCE!>foo<!>
    }

    suspend fun foo() {}
}
