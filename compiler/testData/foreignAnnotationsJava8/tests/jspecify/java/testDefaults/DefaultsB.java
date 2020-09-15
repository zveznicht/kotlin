public class DefaultsB {
    static void main(DefaultsA a) {
        a.everythingNotNullable(null).foo();
        a.everythingNotNullable(new DefaultsA.Test()).foo();

        a.explicitlyNullnessUnspecified(new DefaultsA.Test()).foo();
        a.explicitlyNullnessUnspecified(null).foo();

        a.defaultField.foo();

        a.field.foo();
    }
}
