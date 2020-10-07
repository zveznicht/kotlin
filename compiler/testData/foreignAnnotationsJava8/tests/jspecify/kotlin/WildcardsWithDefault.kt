// JAVA_SOURCES: WildcardsWithDefault.java

fun main(
            aNotNullNotNullNotNull: WildcardsWithDefault.A<Any, Any, Any>,
            aNotNullNotNullNull: WildcardsWithDefault.A<Any, Any, Any?>,
            aNotNullNullNotNull: WildcardsWithDefault.A<Any, Any?, Any>,
            aNotNullNullNull: WildcardsWithDefault.A<Any, Any?, Any?>,
            b: WildcardsWithDefault
    ): Unit {
        b.noBoundsNotNull(aNotNullNotNullNotNull);
        b.noBoundsNotNull(aNotNullNotNullNull);
        b.noBoundsNotNull(aNotNullNullNotNull);
        b.noBoundsNotNull(aNotNullNullNull);
        b.noBoundsNullable(aNotNullNotNullNotNull);
        b.noBoundsNullable(aNotNullNotNullNull);
        b.noBoundsNullable(aNotNullNullNotNull);
        b.noBoundsNullable(aNotNullNullNull);
}