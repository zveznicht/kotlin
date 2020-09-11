// !DIAGNOSTICS: -UNUSED_PARAMETER

// FILE: Anno.java
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
public @interface Anno {}

// FILE: Foo.java
import org.jetbrains.annotations.NotNull;

public class Foo {
    @Anno
    public String @NotNull [] foo() { return new String[] {}; }
}

// FILE: main.kt
fun take(x: String) {}

fun main(x: Foo) {
    var y: String? = null
    if (y == null) {
        y = <!DEBUG_INFO_EXPRESSION_TYPE("(kotlin.Array<@org.jetbrains.annotations.NotNull kotlin.String>..kotlin.Array<out @org.jetbrains.annotations.NotNull kotlin.String>?)")!>x.foo()<!>[0]
    }
    take(<!DEBUG_INFO_EXPRESSION_TYPE("kotlin.String & kotlin.String?"), DEBUG_INFO_SMARTCAST!>y<!>)
}