// COMPILER_ARGUMENTS: -Xjspecify-annotations

import org.jspecify.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@DefaultNonNull
public class WildcardsWithDefault {
    public void noBoundsNotNull(WildcardsWithDefault.A<?, ?, ?> a) {}
    public void noBoundsNullable(WildcardsWithDefault.A<@Nullable ?, @Nullable ?, @Nullable ?> a) {}

    public class A <T extends Object, E extends @Nullable Object, F extends @NullnessUnspecified Object> {}
}

@DefaultNonNull
class Use {
    public static void main(
            WildcardsWithDefault.A<Object, Object, Object> aNotNullNotNullNotNull,
            WildcardsWithDefault.A<Object, Object, @Nullable Object> aNotNullNotNullNull,
            WildcardsWithDefault.A<Object, @Nullable Object, Object> aNotNullNullNotNull,
            WildcardsWithDefault.A<Object, @Nullable Object, @Nullable Object> aNotNullNullNull,
            WildcardsWithDefault b
    ) {
        // jspecify_nullness_mismatch
        b.noBoundsNotNull(aNotNullNotNullNotNull);
        b.noBoundsNotNull(aNotNullNotNullNull);
        // jspecify_nullness_mismatch
        b.noBoundsNotNull(aNotNullNullNotNull);
        // jspecify_nullness_mismatch
        b.noBoundsNotNull(aNotNullNullNull);

        b.noBoundsNullable(aNotNullNotNullNotNull);
        b.noBoundsNullable(aNotNullNotNullNull);
        b.noBoundsNullable(aNotNullNullNotNull);
        b.noBoundsNullable(aNotNullNullNull);
    }
}