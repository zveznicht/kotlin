import org.jspecify.annotations.*;

@DefaultNotNull
public class DefaultsA {
    static class Test {
        public Test foo() { return null; }
    }

    public Test defaultField = null;
    @Nullable public Test field = null;

    public Test everythingNotNullable(Test x) { return null; }

    public Test explicitlyNullnessUnspecified(@NullnessUnspecified Test x) { return null; }
}
