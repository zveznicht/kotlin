// !DIAGNOSTICS: -UNUSED_EXPRESSION,-UNUSED_VARIABLE
fun foo(x: Int, <!UNUSED_PARAMETER!>y<!>: Any) = x
fun foo(<!UNUSED_PARAMETER!>x<!>: Any, y: Int) = y

fun main() {
    ::<!CALLABLE_REFERENCE_RESOLUTION_AMBIGUITY!>foo<!>
    
    val fooRef: (Int, Any) -> Unit = ::<!CALLABLE_REFERENCE_RESOLUTION_AMBIGUITY!>foo<!>
}
