// WITH_RUNTIME
import kotlinx.sharing.*

@Shared
data class Bar(val a: String, var b: String, var c: Int = 42)

@Shared
data class Foo(val d: String, val bar: Bar)


fun box(): String {
    val bar = Bar("a", "b")
    val foo = Foo("d", bar)
    val sharedFoo = foo.share()
    bar.b = "bb"
    if (bar.b != "bb") {
        return "Original copy should be mutable"
    }
    if (foo.bar.b != "bb") {
        return "Must be linked with original"
    }
    if (sharedFoo.bar.b == "bb") {
        return "Shared copy should not reflect state changes in original"
    }
    try {
        sharedFoo.bar.b = "bb"
    } catch (e: java.lang.IllegalArgumentException) {
        if (e.message?.contains("Already readonly") == true)
            return "OK"
        else
            return "Incorrect exception text: ${e.message}"
    } catch (e: Throwable) {
        return "Incorrect exception class thrown: ${e::class}, ${e.message}"
    }
    return "Expected IAE to be thrown, but completed successfully"
}