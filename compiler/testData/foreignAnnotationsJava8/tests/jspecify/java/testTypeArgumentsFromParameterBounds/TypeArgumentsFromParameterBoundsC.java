import org.jspecify.annotations.*;

@DefaultNotNull
public class TypeArgumentsFromParameterBoundsC {
    static public class Test {}

    public void bar(TypeArgumentsFromParameterBoundsA<Test, Test, Test> a) {}
}
