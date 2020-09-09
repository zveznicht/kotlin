// !DIAGNOSTICS: -UNUSED_PARAMETER

// FILE: Base.java
public class Base {
    public String [] [] foo() { return new String[][] {}; }
    public String [] [] [] bar() { return new String[][][] {}; }
}

// FILE: Anno.java
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
public @interface Anno {}

// FILE: Derived.java
import org.jetbrains.annotations.NotNull;

public class Derived extends Base {
    @Override
    public String [] @NotNull @Anno [] foo() { return new String[][] {}; }
    @Override
    public String [] [] @NotNull [] bar() { return new String[][][] {}; }
}

// FILE: main.kt
fun take(x: String) {}

fun main(x: Derived) {
    var y1: String? = null
    var y2: String? = null
    if (y1 == null) {
        y1 = <!DEBUG_INFO_EXPRESSION_TYPE("(kotlin.Array<(kotlin.Array<@org.jetbrains.annotations.NotNull @Anno kotlin.String>..kotlin.Array<out @org.jetbrains.annotations.NotNull @Anno kotlin.String>?)>..kotlin.Array<out (kotlin.Array<@org.jetbrains.annotations.NotNull @Anno kotlin.String>..kotlin.Array<out @org.jetbrains.annotations.NotNull @Anno kotlin.String>?)>?)")!>x.foo()<!>[0][0]
    }
    if (y2 == null) {
        y2 = <!DEBUG_INFO_EXPRESSION_TYPE("(kotlin.Array<(kotlin.Array<(kotlin.Array<@org.jetbrains.annotations.NotNull kotlin.String>..kotlin.Array<out @org.jetbrains.annotations.NotNull kotlin.String>?)>..kotlin.Array<out (kotlin.Array<@org.jetbrains.annotations.NotNull kotlin.String>..kotlin.Array<out @org.jetbrains.annotations.NotNull kotlin.String>?)>?)>..kotlin.Array<out (kotlin.Array<(kotlin.Array<@org.jetbrains.annotations.NotNull kotlin.String>..kotlin.Array<out @org.jetbrains.annotations.NotNull kotlin.String>?)>..kotlin.Array<out (kotlin.Array<@org.jetbrains.annotations.NotNull kotlin.String>..kotlin.Array<out @org.jetbrains.annotations.NotNull kotlin.String>?)>?)>?)")!>x.bar()<!>[0][0][0]
    }
    take(<!DEBUG_INFO_EXPRESSION_TYPE("kotlin.String & kotlin.String?"), DEBUG_INFO_SMARTCAST!>y1<!>)
    take(<!DEBUG_INFO_EXPRESSION_TYPE("kotlin.String & kotlin.String?"), DEBUG_INFO_SMARTCAST!>y2<!>)
}