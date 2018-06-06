// !CHECK_TYPE

object Obj {
    suspend fun foo() {}
}

fun test() {
    checkSubtype<suspend () -> Unit>(Obj::foo)
}
