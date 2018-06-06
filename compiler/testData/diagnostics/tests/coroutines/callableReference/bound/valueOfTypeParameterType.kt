interface S {
    suspend fun foo() = ""
}

fun <T: S> get(t: T): suspend () -> String {
    return t::foo
}
