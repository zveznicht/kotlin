// FILE: Derived.kt
class Derived: Base() {
    init {
        value = 0
    }

    fun getValue() = value

    fun setValue(value: Int) {
        this.value = value
    }
}

// FILE: Base.java
public class Base {
    public int value;
}

