// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: B.java

import org.jspecify.annotations.*;

@DefaultNotNull
public class B<T> {
    public void foo(T t) {}
    public <E> void bar(E e) {}
}

// FILE: main.kt

fun main(b1: B<<!UPPER_BOUND_VIOLATED!>Any?<!>>, b2: B<String>) {
    b1.foo(<!NULL_FOR_NONNULL_TYPE!>null<!>)
    b1.bar<<!UPPER_BOUND_VIOLATED!>String?<!>>(<!NULL_FOR_NONNULL_TYPE!>null<!>)
    b1.bar<String>("")

    b2.foo(<!NULL_FOR_NONNULL_TYPE!>null<!>)
    b2.bar<<!UPPER_BOUND_VIOLATED!>String?<!>>(<!NULL_FOR_NONNULL_TYPE!>null<!>)
    b2.bar<String>("")
}
