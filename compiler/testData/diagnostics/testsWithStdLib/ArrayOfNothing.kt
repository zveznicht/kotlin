fun <K> id(x: K) = x

class Inv<T>(x: T)

class A {
    val x: Map<String, Inv<(String, String, String, String) -> Unit>> =
        mapOf(
            "" to Inv { a, b, c, d -> },
            "" to Inv { a, b, c, d -> },
            "" to Inv { a, b, c, d -> },
            "" to Inv { a, b, c, d -> },
            "" to Inv { a, b, c, d -> },
            "" to Inv { a, b, c, d -> },
            "" to Inv { a, b, c, d -> },
            "" to Inv { a, b, c, d -> },
            "" to Inv { a, b, c, d -> }
        )
}