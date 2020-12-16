class Bar {
    private lateinit var foo: String

    fun baz(foo: String) { //rename newFoo to foo
        if(this::foo.isInitialized) {
            throw Exception("AAA")
        }

        foo = newFoo
    }
}