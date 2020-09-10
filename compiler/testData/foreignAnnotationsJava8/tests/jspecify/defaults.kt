// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: A.java

import org.jspecify.annotations.*;

@DefaultNotNull
public class A {
    public String defaultField = "";
    @Nullable public String field = null;

    public String everythingNotNullable(String x) { return ""; }

    public String explicitlyNullnessUnknown(@NullnessUnknown String x) { return ""; }
}

// FILE: main.kt

fun main(a: A) {
    a.everythingNotNullable(<!NULL_FOR_NONNULL_TYPE!>null<!>)<!UNNECESSARY_SAFE_CALL!>?.<!>length
    a.everythingNotNullable(<!NULL_FOR_NONNULL_TYPE!>null<!>).length
    a.everythingNotNullable("").length

    a.explicitlyNullnessUnknown("").length
    a.explicitlyNullnessUnknown("")<!UNNECESSARY_SAFE_CALL!>?.<!>length
    a.explicitlyNullnessUnknown(null).length

    a.defaultField<!UNNECESSARY_SAFE_CALL!>?.<!>length
    a.defaultField.length

    a.field?.length
    a.field<!UNSAFE_CALL!>.<!>length
}
