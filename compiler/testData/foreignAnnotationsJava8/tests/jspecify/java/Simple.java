import org.jspecify.annotations.*;

public class Simple {
    public static class Base {}
    public static class Derived extends Base {
        void foo() {}
    }

    @Nullable public Simple.Derived field = null;

    @Nullable
    public Simple.Derived foo(Derived x, @NullnessUnspecified Base y) {
        return null;
    }

    static public void main(Simple a) {
        a.foo(new Derived(), null).foo();
        a.foo(null, new Derived()).foo();

        a.field.foo();
    }
}
