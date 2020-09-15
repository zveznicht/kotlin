public class TypeParameterBoundsC {
    public static void main(TypeParameterBoundsB<@Nullable Object> b1, TypeParameterBoundsB<TypeParameterBoundsB.Test> b2) {
        b1.foo(null);
        b1.<@Nullable TypeParameterBoundsB.Test>bar(null);
        b1.<TypeParameterBoundsB.Test>bar(new TypeParameterBoundsB.Test());

        b2.foo(null);
        b2.boo(null);
        b2.<@Nullable TypeParameterBoundsB.Test>bar(null);
        b2.<TypeParameterBoundsB.Test>bar(new TypeParameterBoundsB.Test());
    }
}
