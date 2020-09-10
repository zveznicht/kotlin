// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: A.java

import org.jspecify.annotations.*;

public class A<T extends @Nullable Object> {
    public void foo(T t) {}
    public <E extends @Nullable Object> void bar(E e) {}
}

// FILE: main.kt

fun main(a1: A<Any?>, a2: A<String>) {
    a1.foo(null)
    a1.bar<String?>(null)
    a1.bar<String>(<!NULL_FOR_NONNULL_TYPE!>null<!>)
    a1.bar<String>("")

    a2.foo(<!NULL_FOR_NONNULL_TYPE!>null<!>)
    a2.bar<String?>(null)
    a2.bar<String>(<!NULL_FOR_NONNULL_TYPE!>null<!>)
    a2.bar<String>("")
}
