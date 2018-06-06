// !CHECK_TYPE

class Foo

suspend fun Foo?.bar() {}

fun test() {
    val r1 = Foo ?:: bar
    checkSubtype<suspend (Foo?) -> Unit>(r1)

    val r2 = Foo ? :: bar
    checkSubtype<suspend (Foo?) -> Unit>(r2)

    val r3 = Foo ? ? :: bar
    checkSubtype<suspend (Foo?) -> Unit>(r3)
}
