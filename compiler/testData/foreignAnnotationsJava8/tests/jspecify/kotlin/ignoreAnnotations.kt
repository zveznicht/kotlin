// JAVA_SOURCES: IgnoreAnnotations.java

fun main(a: IgnoreAnnotations) {
    a.foo(IgnoreAnnotations.Derived(), null)?.foo()
    <!RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS!>a.foo(IgnoreAnnotations.Derived(), null)<!>.foo()
    <!RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS!>a.foo(null, IgnoreAnnotations.Derived())<!>.foo()

    a.field?.foo()
    <!RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS!>a.field<!>.foo()
}
