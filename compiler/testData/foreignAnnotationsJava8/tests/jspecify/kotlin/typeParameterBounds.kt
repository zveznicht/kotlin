// JAVA_SOURCES: testTypeParameterBounds

fun main(b1: TypeParameterBoundsB<Any?>, b2: TypeParameterBoundsB<TypeParameterBoundsB.Test>) {
    b1.foo(null)
    b1.bar<TypeParameterBoundsB.Test?>(null)
    b1.bar<TypeParameterBoundsB.Test>(TypeParameterBoundsB.Test())

    b2.foo(null)
    b2.boo(<!NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS!>null<!>)
    b2.bar<TypeParameterBoundsB.Test?>(null)
    b2.bar<TypeParameterBoundsB.Test>(TypeParameterBoundsB.Test())
}
