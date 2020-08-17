fun useML(x: MutableList<String>) {}

fun test(x: List<String>) {
    x as MutableList<String>
    useML(x)
}

fun test2(x: java.util.ArrayList<String>) {
    x as MutableList<String>
    useML(x)
}

// 0 CHECKCAST