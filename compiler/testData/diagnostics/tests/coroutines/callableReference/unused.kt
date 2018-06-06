suspend fun foo() {

}

class A {
    suspend fun foo() {

    }
}

suspend fun A.bar() {

}

fun test() {
    <!UNUSED_EXPRESSION!>::foo<!>
    <!UNUSED_EXPRESSION!>A::foo<!>
    <!UNUSED_EXPRESSION!>A::bar<!>
}