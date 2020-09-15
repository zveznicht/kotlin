// JAVA_SOURCES: testWildcardsWithDefault

fun main(
    aNotNullNotNullNotNull: WildcardsWithDefaultA<Any, Any, Any>,
    aNotNullNotNullNull: WildcardsWithDefaultA<Any, Any, Any?>,
    aNotNullNullNotNull: WildcardsWithDefaultA<Any, Any?, Any>,
    aNotNullNullNull: WildcardsWithDefaultA<Any, Any?, Any?>,
    b: WildcardsWithDefaultB
) {
    b.noBoundsNotNull(aNotNullNotNullNotNull)
    b.noBoundsNotNull(aNotNullNotNullNull)
    b.noBoundsNotNull(aNotNullNullNotNull)
    b.noBoundsNotNull(aNotNullNullNull)
}
