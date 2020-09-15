import org.jspecify.annotations.*;

@DefaultNotNull
public class WildcardsWithDefaultB {
    public void noBoundsNotNull(WildcardsWithDefaultA<?, ?, ?> a) {}
}
