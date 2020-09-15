import org.jspecify.annotations.*;

public class IgnoreAnnotations {
    public static class Base {
        void foo() {}
    }
    public static class Derived extends Base { }

    @Nullable public Derived field = null;

    @Nullable
    public Derived foo(Derived x, @NullnessUnspecified Base y) {
        return null;
    }

    static void main(IgnoreAnnotations a) {
        a.foo(new Derived(), null).foo();
        a.foo(null, new Derived()).foo();

        a.field.foo();
    }
}
