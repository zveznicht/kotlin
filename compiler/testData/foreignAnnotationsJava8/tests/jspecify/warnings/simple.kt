// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// CODE_ANALYSIS_STATE warn
// FILE: A.java

import org.jspecify.annotations.*;

public class A {
    @Nullable public String field = null;

    @Nullable
    public String foo(String x, @NullnessUnknown CharSequence y) {
        return "";
    }
}

// FILE: main.kt

fun main(a: A) {
    a.foo("", null)?.length
    <!RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS!>a.foo("", null)<!>.length

    a.field?.length
    <!RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS!>a.field<!>.length
}
