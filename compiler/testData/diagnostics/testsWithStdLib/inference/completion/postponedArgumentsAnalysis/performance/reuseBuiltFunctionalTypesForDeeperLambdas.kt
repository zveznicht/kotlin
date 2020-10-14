// FIR_IDENTICAL
// SKIP_TXT
// !DIAGNOSTICS: -UNUSED_ANONYMOUS_PARAMETER

class Bar<A, B>(x: Foo<A>, y: B)
class Foo<A, B>(x: Bar<A>, y: B)
class Inv<A, B>(x: Foo<A, B>, y: Foo<Int, B>)

class A {
    val x: Map<String, (String, String, String, String) -> Unit> =
        mapOf(
            "" to Inv { a, b, c, d -> },
            "" to { a, b, c, d -> },
        )
}