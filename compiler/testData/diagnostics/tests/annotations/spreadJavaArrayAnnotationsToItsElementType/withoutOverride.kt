// !DIAGNOSTICS: -UNUSED_PARAMETER
// FILE: Base.java
public class Base {}

// FILE: Derived.java
import org.jetbrains.annotations.NotNull;

public class Derived extends Base {
    public String @NotNull [] foo() { return new String[] {}; }
}

// FILE: main.kt
fun take(x: String) {}

fun main(x: Derived) {
    var y: String? = null
    if (y == null) {
        y = <!DEBUG_INFO_EXPRESSION_TYPE("(kotlin.Array<(kotlin.String..kotlin.String?)>..kotlin.Array<out (kotlin.String..kotlin.String?)>?)")!>x.foo()<!>[0]
    }
    take(<!DEBUG_INFO_EXPRESSION_TYPE("kotlin.String?"), TYPE_MISMATCH!>y<!>)
}