package test

class Foo {
    suspend fun <T> bar(x: Int) = x
}

suspend fun test() {
    Foo::<!TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>bar<!> <!SYNTAX!>< <!DEBUG_INFO_MISSING_UNRESOLVED!>Int<!> ><!> <!SYNTAX!>(2 <!DEBUG_INFO_MISSING_UNRESOLVED!>+<!> 2)<!>
}
