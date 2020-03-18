// FIR_IDENTICAL
// !DIAGNOSTICS: -UNUSED_PARAMETER
// !LANGUAGE: +NewInference
// !RENDER_DIAGNOSTICS_MESSAGES

interface A
interface B

fun foo(a: A) {}
fun foo(b: B) {}

fun bar(a: A) {}

fun <T> boo() {}

fun <I> id(arg: I) = arg

val l: (A) -> Unit =
    if (1 < 2)
    {
//        materialize()
//        ::foo
        id(::foo)
    }
    else
    {
        ::bar
    }

fun <M> materialize(): M = TODO()