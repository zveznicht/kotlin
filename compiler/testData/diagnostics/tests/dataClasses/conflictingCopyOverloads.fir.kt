// !DIAGNOSTICS: -UNUSED_PARAMETER

data class A(val x: Int, val y: String) {
    fun <!CONFLICTING_OVERLOADS!>copy<!>(x: Int, y: String) = x
    fun <!CONFLICTING_OVERLOADS!>copy<!>(x: Int, y: String) = A(x, y)
}