// JAVA_SOURCES: testAnnotatedWildcards

fun main(
    aNotNullNotNullNotNull: AnnotatedWildcardsA<AnnotatedWildcardsB.Derived, AnnotatedWildcardsB.Derived, AnnotatedWildcardsB.Derived>,
    aNotNullNotNullNull: AnnotatedWildcardsA<AnnotatedWildcardsB.Derived, AnnotatedWildcardsB.Derived, AnnotatedWildcardsB.Derived?>,
    aNotNullNullNotNull: AnnotatedWildcardsA<AnnotatedWildcardsB.Derived, AnnotatedWildcardsB.Derived?, AnnotatedWildcardsB.Derived>,
    aNotNullNullNull: AnnotatedWildcardsA<AnnotatedWildcardsB.Derived, AnnotatedWildcardsB.Derived?, AnnotatedWildcardsB.Derived?>,

    aAnyNotNullNotNullNotNull: AnnotatedWildcardsA<Any, Any, Any>,
    aAnyNotNullNotNullNull: AnnotatedWildcardsA<Any, Any, Any?>,
    aAnyNotNullNullNotNull: AnnotatedWildcardsA<Any, Any?, Any>,
    aAnyNotNullNullNull: AnnotatedWildcardsA<Any, Any?, Any?>,

    b: AnnotatedWildcardsB
) {
    b.superAsIs(aAnyNotNullNotNullNotNull)
    b.superAsIs(aAnyNotNullNotNullNull)
    b.superAsIs(aAnyNotNullNullNotNull)
    b.superAsIs(aAnyNotNullNullNull)

    // TODO: Bound for the first argument in "superNullable" contradicts to declared nullability of the parameter
    // Do we need to ignore such arguments' nullability?
    b.superNullable(aAnyNotNullNotNullNotNull)
    b.superNullable(aAnyNotNullNotNullNull)
    b.superNullable(aAnyNotNullNullNotNull)
    b.superNullable(aAnyNotNullNullNull)

    b.extendsAsIs(aNotNullNotNullNotNull)
    b.extendsAsIs(aNotNullNotNullNull)
    b.extendsAsIs(aNotNullNullNotNull)
    b.extendsAsIs(aNotNullNullNull)

    b.extendsNullable(aNotNullNotNullNotNull)
    b.extendsNullable(aNotNullNotNullNull)
    b.extendsNullable(aNotNullNullNotNull)
    b.extendsNullable(aNotNullNullNull)

    b.noBounds(aNotNullNotNullNotNull)
    b.noBounds(aNotNullNotNullNull)
    b.noBounds(aNotNullNullNotNull)
    b.noBounds(aNotNullNullNull)
}