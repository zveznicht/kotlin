// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: A.java

import org.jspecify.annotations.*;

public class A<T extends Object, E extends @Nullable Object, F extends @NullnessUnspecified Object> {
}

// FILE: B.java

import org.jspecify.annotations.*;

public class B {
    @DefaultNotNull
    public void noBoundsNotNull(A<?, ?, ?> a) {}
}

// FILE: main.kt

fun main(
    aNotNullNotNullNotNull: A<String, String, String>,
    aNotNullNotNullNull: A<String, String, String?>,
    aNotNullNullNotNull: A<String, String?, String>,
    aNotNullNullNull: A<String, String?, String?>,
    b: B
) {
    b.noBoundsNotNull(aNotNullNotNullNotNull)
    b.noBoundsNotNull(<!TYPE_MISMATCH!>aNotNullNotNullNull<!>)
    b.noBoundsNotNull(<!TYPE_MISMATCH!>aNotNullNullNotNull<!>)
    b.noBoundsNotNull(<!TYPE_MISMATCH!>aNotNullNullNull<!>)
}
