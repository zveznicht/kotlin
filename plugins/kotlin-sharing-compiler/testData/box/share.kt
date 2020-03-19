// WITH_RUNTIME
import kotlinx.sharing.*

@Shared
data class Bar(val a: String, var b: String, var c: Int = 42)

fun box(): String {
    val bar = Bar("a", "b")
    val sharedBar = bar.share()
    bar.b = "bb"
    if (bar.b != "bb") {
        return "Original copy should be mutable"
    }
    if (sharedBar.b == "bb") {
        return "Shared copy should not reflect state changes in original"
    }
    try {
        sharedBar.b = "bb"
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