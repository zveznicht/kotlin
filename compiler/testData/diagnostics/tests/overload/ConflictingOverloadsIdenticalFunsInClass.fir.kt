class A() {
    fun <!CONFLICTING_OVERLOADS!>b<!>() {
    }

    fun <!CONFLICTING_OVERLOADS!>b<!>() {
    }
}
