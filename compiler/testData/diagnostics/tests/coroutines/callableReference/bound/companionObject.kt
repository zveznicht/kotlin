// !CHECK_TYPE
// !DIAGNOSTICS: -UNUSED_EXPRESSION

package test

class C {
    companion object {
        suspend fun foo(): String = "companion"
        suspend fun bar() {}
    }

    suspend fun foo(): Int = 0
}

fun test() {
    val r1 = C::foo
    checkSubtype<suspend (C) -> Int>(r1)

    val r2 = test.C::foo
    checkSubtype<suspend (C) -> Int>(r2)

    val r3 = C.Companion::foo
    checkSubtype<suspend () -> String>(r3)

    val r4 = test.C.Companion::foo
    checkSubtype<suspend () -> String>(r4)

    val r5 = (C)::foo
    checkSubtype<suspend () -> String>(r5)

    val r6 = (test.C)::foo
    checkSubtype<suspend () -> String>(r6)

    val c = C.Companion
    val r7 = c::foo
    checkSubtype<suspend () -> String>(r7)

    C::<!UNRESOLVED_REFERENCE!>bar<!>
}
