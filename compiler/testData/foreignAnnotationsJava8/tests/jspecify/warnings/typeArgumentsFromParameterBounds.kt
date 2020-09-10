// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// CODE_ANALYSIS_STATE warn
// FILE: A.java

import org.jspecify.annotations.*;

public class A<T extends Object, E extends @Nullable Object, F extends @NullnessUnknown Object> {
}

// FILE: C.java

import org.jspecify.annotations.*;

@DefaultNotNull
public class C {
    public void bar(A<String, String, String> a) {}
}

// FILE: main.kt

fun main(
    aNotNullNotNullNotNull: A<String, String, String>,
    aNotNullNotNullNull: A<String, String, String?>,
    aNotNullNullNotNull: A<String, String?, String>,
    aNotNullNullNull: A<String, String?, String?>,
    c: C
) {
    c.bar(aNotNullNotNullNotNull)
    // TODO: NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS should be reported
    c.bar(aNotNullNotNullNull)
    // TODO: NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS should be reported
    c.bar(aNotNullNullNotNull)
    // TODO: NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS should be reported
    c.bar(aNotNullNullNull)
}
