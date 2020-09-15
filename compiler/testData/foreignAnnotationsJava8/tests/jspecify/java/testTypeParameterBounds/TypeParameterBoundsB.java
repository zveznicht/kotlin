import org.jspecify.annotations.*;

@DefaultNotNull
public class TypeParameterBoundsB<T> {
    static public class Test {}

    public void foo(T t) {}
    public <E> void bar(E e) {}
    public void boo(Test e) {}
}
