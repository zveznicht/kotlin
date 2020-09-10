// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// CODE_ANALYSIS_STATE warn
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
    // TODO: NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS should be reported
    a1.bar<String>(null)
    a1.bar<String>("")

    // TODO: NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS should be reported
    a2.foo(null)
    a2.bar<String?>(null)
    // TODO: NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS should be reported
    a2.bar<String>(null)
    a2.bar<String>("")
}
