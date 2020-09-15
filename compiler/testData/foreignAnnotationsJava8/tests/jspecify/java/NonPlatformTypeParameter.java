import org.jspecify.annotations.*;

public class NonPlatformTypeParameter<T extends @Nullable Object> {
    public static class Test {}

    public void foo(T t) {}
    public <E extends @Nullable Object> void bar(E e) {}

    public static void main(NonPlatformTypeParameter<Object> a1, NonPlatformTypeParameter<Test> a2) {
        a1.foo(null);
        a1.<Test>bar(null);
        a1.<Test>bar(new Test());

        a2.foo(null);
        a2.<Test>bar(null);
        a2.<Test>bar(new Test());
    }
}
