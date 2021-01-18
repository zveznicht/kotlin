// FILE: Derived.kt
class Derived : Base() {
    fun setValue(v: Any) {
        if (v is String) {
            value = v
        }
    }
}

// FILE: Base.java
public class Base {
    public String value;
}
