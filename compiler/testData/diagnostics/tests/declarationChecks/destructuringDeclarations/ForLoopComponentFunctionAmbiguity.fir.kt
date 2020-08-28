class A {
    operator fun <!CONFLICTING_OVERLOADS!>component1<!>() = 1
    operator fun <!CONFLICTING_OVERLOADS!>component1<!>() = 1
    operator fun component2() = 1
}

class C {
    operator fun iterator(): Iterator<A> = null!!
}

fun test() {
    for ((<!AMBIGUITY!>x<!>, y) in C()) {

    }
}
