import org.jspecify.annotations.*;

public class AnnotatedWildcardsB {
    public static class Base {}
    public static class Derived extends Base {}

    public void superAsIs(AnnotatedWildcardsA<? super Base, ? super Base, ? super Base> a) {}
    public void superNullable(AnnotatedWildcardsA<? super @Nullable Base, ? super @Nullable Base, ? super @Nullable Base> a) {}

    public void extendsAsIs(AnnotatedWildcardsA<? extends Base, ? extends Base, ? extends Base> a) {}
    public void extendsNullable(AnnotatedWildcardsA<? extends @Nullable Base, ? extends @Nullable Base, ? extends @Nullable Base> a) {}

    public void noBounds(AnnotatedWildcardsA<?, ?, ?> a) {}

    public void main(
            AnnotatedWildcardsA<Derived, Derived, Derived> aNullUnspecNullUnspecNullUnspec,
            AnnotatedWildcardsA<Derived, Derived, @Nullable Derived> aNullUnspecNullUnspecNull,
            AnnotatedWildcardsA<Derived, @Nullable Derived, Derived> aNullUnspecNullNullUnspec,
            AnnotatedWildcardsA<Derived, @Nullable Derived, @Nullable Derived> aNullUnspecNullNull,

            AnnotatedWildcardsA<Object, Object, Object> aObjectNullUnspecNullUnspecNullUnspec,
            AnnotatedWildcardsA<Object, Object, @Nullable Object> aObjectNullUnspecNullUnspecNull,
            AnnotatedWildcardsA<Object, @Nullable  Object, Object> aObjectNullUnspecNullNullUnspec,
            AnnotatedWildcardsA<Object, @Nullable Object, @Nullable Object> aObjectNullUnspecNullNull,

            AnnotatedWildcardsB b
    ) {
        b.superAsIs(aObjectNullUnspecNullUnspecNullUnspec);
        b.superAsIs(aObjectNullUnspecNullUnspecNull);
        b.superAsIs(aObjectNullUnspecNullNullUnspec);
        b.superAsIs(aObjectNullUnspecNullNull);

        b.superNullable(aObjectNullUnspecNullUnspecNullUnspec);
        b.superNullable(aObjectNullUnspecNullUnspecNull);
        b.superNullable(aObjectNullUnspecNullNullUnspec);
        b.superNullable(aObjectNullUnspecNullNull);

        b.extendsAsIs(aNullUnspecNullUnspecNullUnspec);
        b.extendsAsIs(aNullUnspecNullUnspecNull);
        b.extendsAsIs(aNullUnspecNullNullUnspec);
        b.extendsAsIs(aNullUnspecNullNull);

        b.extendsNullable(aNullUnspecNullUnspecNullUnspec);
        b.extendsNullable(aNullUnspecNullUnspecNull);
        b.extendsNullable(aNullUnspecNullNullUnspec);
        b.extendsNullable(aNullUnspecNullNull);

        b.noBounds(aNullUnspecNullUnspecNullUnspec);
        b.noBounds(aNullUnspecNullUnspecNull);
        b.noBounds(aNullUnspecNullNullUnspec);
        b.noBounds(aNullUnspecNullNull);
    }
}
