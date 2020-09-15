// JAVA_SOURCES: testTypeArgumentsFromParameterBounds

fun main(
    aNotNullNotNullNotNull: TypeArgumentsFromParameterBoundsA<TypeArgumentsFromParameterBoundsC.Test, TypeArgumentsFromParameterBoundsC.Test, TypeArgumentsFromParameterBoundsC.Test>,
    aNotNullNotNullNull: TypeArgumentsFromParameterBoundsA<TypeArgumentsFromParameterBoundsC.Test, TypeArgumentsFromParameterBoundsC.Test, TypeArgumentsFromParameterBoundsC.Test?>,
    aNotNullNullNotNull: TypeArgumentsFromParameterBoundsA<TypeArgumentsFromParameterBoundsC.Test, TypeArgumentsFromParameterBoundsC.Test?, TypeArgumentsFromParameterBoundsC.Test>,
    aNotNullNullNull: TypeArgumentsFromParameterBoundsA<TypeArgumentsFromParameterBoundsC.Test, TypeArgumentsFromParameterBoundsC.Test?, TypeArgumentsFromParameterBoundsC.Test?>,
    c: TypeArgumentsFromParameterBoundsC
) {
    c.bar(aNotNullNotNullNotNull)
    c.bar(aNotNullNotNullNull)
    c.bar(aNotNullNullNotNull)
    c.bar(aNotNullNullNull)
}
