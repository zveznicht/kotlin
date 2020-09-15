import org.jspecify.annotations.*;

public class WildcardsWithDefaultA<T extends Object, E extends @Nullable Object, F extends @NullnessUnspecified Object> {
    public static void main(
            WildcardsWithDefaultA<Object, Object, Object> aNullUnspecNullUnspecNullUnspec,
            WildcardsWithDefaultA<Object, Object, @Nullable Object> aNullUnspecNullUnspecNull,
            WildcardsWithDefaultA<Object, @Nullable Object, Object> aNullUnspecNullNullUnspec,
            WildcardsWithDefaultA<Object, @Nullable Object, @Nullable Object> aNullUnspecNullNull,
            WildcardsWithDefaultB b
    ) {
        b.noBoundsNotNull(aNullUnspecNullUnspecNullUnspec);
        b.noBoundsNotNull(aNullUnspecNullUnspecNull);
        b.noBoundsNotNull(aNullUnspecNullNullUnspec);
        b.noBoundsNotNull(aNullUnspecNullNull);
    }
}