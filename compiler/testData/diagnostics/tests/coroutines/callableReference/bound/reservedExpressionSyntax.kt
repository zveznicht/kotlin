// !DIAGNOSTICS: -UNUSED_VARIABLE
package test

object ClassMemberMarker

class a<T> {
    suspend fun foo() = ClassMemberMarker
}

class b<T1, T2> {
    suspend fun foo() = ClassMemberMarker
}

fun Int.foo() {}

class Test {
    val <T> List<T>.a: Int get() = size
    val <T> List<T>.b: Int? get() = size

    fun <T> List<T>.testCallable1(): () -> Unit = <!RESERVED_SYNTAX_IN_CALLABLE_REFERENCE_LHS!>a<T><!>::foo
    fun <T> List<T>.testCallable2(): () -> Unit = <!RESERVED_SYNTAX_IN_CALLABLE_REFERENCE_LHS!>b<!>?::<!UNSAFE_CALL!>foo<!>
    fun <T> List<T>.testCallable3(): () -> Unit = <!RESERVED_SYNTAX_IN_CALLABLE_REFERENCE_LHS!>b<T, Any><!>::<!UNSAFE_CALL!>foo<!>
    fun <T> List<T>.testCallable4(): () -> Unit = <!RESERVED_SYNTAX_IN_CALLABLE_REFERENCE_LHS!>b<T><!>?::<!UNSAFE_CALL!>foo<!>

    fun <T> List<T>.testUnresolved1() = <!UNRESOLVED_REFERENCE!>unresolved<!><T>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo<!>
    fun <T> List<T>.testUnresolved2() = <!RESERVED_SYNTAX_IN_CALLABLE_REFERENCE_LHS!>a<<!UNRESOLVED_REFERENCE!>unresolved<!>><!>::foo
    fun <T> List<T>.testUnresolved3() = a<<!SYNTAX!><!>>::foo
    fun <T> List<T>.testUnresolved4() = <!UNRESOLVED_REFERENCE!>unresolved<!>?::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo<!>
}