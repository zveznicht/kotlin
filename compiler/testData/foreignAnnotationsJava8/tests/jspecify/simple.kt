// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
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
    a.foo("", null)<!UNSAFE_CALL!>.<!>length

    a.field?.length
    a.field<!UNSAFE_CALL!>.<!>length
}
