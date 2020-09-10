// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// CODE_ANALYSIS_STATE ignore
// FILE: A.java

import org.jspecify.annotations.*;

public class A {
    @Nullable public String field = null;

    @Nullable
    public String foo(String x, @NullnessUnspecified CharSequence y) {
        return "";
    }
}

// FILE: main.kt

fun main(a: A) {
    a.foo("", null)?.length
    a.foo("", null).length
    a.foo(null, "").length

    a.field?.length
    a.field.length
}
