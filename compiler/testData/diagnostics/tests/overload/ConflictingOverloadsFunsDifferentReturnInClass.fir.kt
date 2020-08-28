class A {
    fun <!CONFLICTING_OVERLOADS!>a<!>(a: Int): Int = 0

    fun <!CONFLICTING_OVERLOADS!>a<!>(a: Int) {
    }
}
