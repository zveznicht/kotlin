// FILE: JI.java
public interface JI {
    @Override
    String toString();
}

// FILE: main.kt
interface IA {
    override fun toString(): String
}

abstract class A {

}
class CA : IA, A()
