// JAVA_SOURCES: Simple.java

fun main(a: Simple) {
    a.foo(Simple.Derived(), null)?.foo()
    <!RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS!>a.foo(Simple.Derived(), null)<!>.foo()

    a.field?.foo()
    <!RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS!>a.field<!>.foo()
}
