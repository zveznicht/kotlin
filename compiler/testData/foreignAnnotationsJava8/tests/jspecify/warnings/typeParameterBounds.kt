// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// CODE_ANALYSIS_STATE warn
// FILE: B.java

import org.jspecify.annotations.*;

@DefaultNotNull
public class B<T> {
    public void foo(T t) {}
    public <E> void bar(E e) {}
}

// FILE: main.kt

// TODO: UPPER_BOUND_VIOLATED_WARNING should be reported
fun main(b1: B<Any?>, b2: B<String>) {
    // TODO: NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS should be reported
    b1.foo(null)
    // TODO: UPPER_BOUND_VIOLATED_WARNING should be reported
    b1.bar<String?>(null)
    b1.bar<String>("")

    // TODO: NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS should be reported
    b2.foo(null)
    // TODO: UPPER_BOUND_VIOLATED_WARNING should be reported
    b2.bar<String?>(null)
    b2.bar<String>("")
}
