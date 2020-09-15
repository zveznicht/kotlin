// JAVA_SOURCES: testDefaults

fun main(a: DefaultsA) {
    a.everythingNotNullable(<!NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS!>null<!>)<!UNNECESSARY_SAFE_CALL!>?.<!>foo()
    a.everythingNotNullable(<!NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS!>null<!>).foo()
    a.everythingNotNullable(DefaultsA.Test()).foo()

    a.explicitlyNullnessUnspecified(DefaultsA.Test()).foo()
    a.explicitlyNullnessUnspecified(DefaultsA.Test())<!UNNECESSARY_SAFE_CALL!>?.<!>foo()
    a.explicitlyNullnessUnspecified(null).foo()

    a.defaultField<!UNNECESSARY_SAFE_CALL!>?.<!>foo()
    a.defaultField.foo()

    a.field?.foo()
    <!RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS!>a.field<!>.foo()
}