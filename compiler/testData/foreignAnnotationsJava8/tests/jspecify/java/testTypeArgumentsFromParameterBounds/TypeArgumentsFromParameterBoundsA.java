import org.jspecify.annotations.*;

public class TypeArgumentsFromParameterBoundsA<T extends Object, E extends @Nullable Object, F extends @NullnessUnspecified Object> {
    static public void main(
            TypeArgumentsFromParameterBoundsA<TypeArgumentsFromParameterBoundsC.Test, TypeArgumentsFromParameterBoundsC.Test, TypeArgumentsFromParameterBoundsC.Test> aNullUnspecNullUnspecNullUnspec,
            TypeArgumentsFromParameterBoundsA<TypeArgumentsFromParameterBoundsC.Test, TypeArgumentsFromParameterBoundsC.Test, @Nullable TypeArgumentsFromParameterBoundsC.Test> aNullUnspecNullUnspecNull,
            TypeArgumentsFromParameterBoundsA<TypeArgumentsFromParameterBoundsC.Test, @Nullable TypeArgumentsFromParameterBoundsC.Test, TypeArgumentsFromParameterBoundsC.Test> aNullUnspecNullNullUnspec,
            TypeArgumentsFromParameterBoundsA<TypeArgumentsFromParameterBoundsC.Test, @Nullable TypeArgumentsFromParameterBoundsC.Test, @Nullable TypeArgumentsFromParameterBoundsC.Test> aNullUnspecNullNull,
            TypeArgumentsFromParameterBoundsC c
    ) {
        c.bar(aNullUnspecNullUnspecNullUnspec);
        c.bar(aNullUnspecNullUnspecNull);
        c.bar(aNullUnspecNullNullUnspec);
        c.bar(aNullUnspecNullNull);
    }
}
