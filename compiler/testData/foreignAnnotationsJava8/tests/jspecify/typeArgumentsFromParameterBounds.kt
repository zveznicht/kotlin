// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: A.java

import org.jspecify.annotations.*;

public class A<T extends Object, E extends @Nullable Object, F extends @NullnessUnspecified Object> {
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
    c.bar(<!TYPE_MISMATCH!>aNotNullNotNullNull<!>)
    c.bar(<!TYPE_MISMATCH!>aNotNullNullNotNull<!>)
    c.bar(<!TYPE_MISMATCH!>aNotNullNullNull<!>)
}
