package test;

import org.jetbrains.annotations.NotNull;

public class TypeParameterAnnotations {
    class A<T> {
        public T test() { return null; }
    }
    public A<@NotNull String> implementMe() { return new A(); }
}
