// !DIAGNOSTICS: -UNUSED_EXPRESSION,-UNUSED_VARIABLE
suspend fun foo(x: Int, <!UNUSED_PARAMETER!>y<!>: Any) = x
suspend fun foo(<!UNUSED_PARAMETER!>x<!>: Any, y: Int) = y

<!CONFLICTING_OVERLOADS!>suspend fun bar()<!> {}
<!CONFLICTING_OVERLOADS!>fun bar()<!> {}

fun main() {
    ::<!OVERLOAD_RESOLUTION_AMBIGUITY!>foo<!>
    ::<!OVERLOAD_RESOLUTION_AMBIGUITY!>bar<!>

    val fooRef: suspend (Int, Any) -> Unit = ::<!NONE_APPLICABLE!>foo<!>
}
