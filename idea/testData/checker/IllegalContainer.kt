// FILE: Int.java

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@interface Int {
    Class value();
}

// FILE: test.kt

@Int(String.class)
public class Test {
}