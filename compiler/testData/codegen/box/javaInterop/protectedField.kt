// FILE: ProtectedField.java

public abstract class ProtectedField {
    protected String field = "OK";
}

//FILE: test.kt
package test

import ProtectedField

class Derived: ProtectedField() {
    fun getField() = myRun { super.field }
}

fun myRun(f: () -> String) = f()

fun box() = Derived().getField()
