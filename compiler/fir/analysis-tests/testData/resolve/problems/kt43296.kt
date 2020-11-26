interface Slice<V>

interface A
interface B : A
interface C : A

val SL0: Slice<A>
val SL1: Slice<B>
val SL2: Slice<C>

fun <X> foo(s: Slice<X>): X? {
    if (s === SL1 || s === SL2) {
        return <!INAPPLICABLE_CANDIDATE!>bar<!>(s) // inferred as bar<A>(s), but A is not a subtype of X
    }
    return null
}

fun <Y> bar(w: Slice<Y>): Y? = null