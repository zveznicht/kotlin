// WITH_RUNTIME
import kotlinx.sharing.*

@Shared
class Bar(val a: String, var b: String) {
    var c: Int = 42
}

fun box(): String {
    val bar = Bar("a", "b")
    bar.b = "bb"
    if (bar.b != "bb") {
        return "Should be mutable after creation"
    }
    bar.setReadOnly()
    try {
        bar.b = "bbb"
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