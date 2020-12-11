// !LANGUAGE: +ImprovementsAroundTypeEnhancement -ReadTypeUseAnnotationsFromClassFiles

package test;

import org.jetbrains.annotations.*;

public class Basic_DisabledReading<T extends @NotNull Object> {
    interface G<T> extends G2<@NotNull T, @NotNull String> { }

    interface G2<A, B> { }

    static class A {
        class B<A, B> {}
    }

    public interface MyClass<TT> {
        void f1(G<@NotNull String> p);
        G2<@Nullable String, @NotNull Integer> f2();
        <T extends @NotNull Object> void f3(@NotNull T x);
        void f4(G<@NotNull String @Nullable []> p);
        void f5(G<@NotNull ?> p);
        void f6(G<@NotNull ? extends @Nullable Object> p);
        void f7(G<@NotNull A.B<?, ?>> p);
        G<Basic_DisabledReading.@Nullable A.B<?, ?>> f81();
        G<Basic_DisabledReading.A.@Nullable B<?, ?>> f9();
        <T extends @NotNull Object, K extends G<@NotNull String []>> void f10(T p);
    }

    Basic_DisabledReading(G<@NotNull String> p) { }
}
