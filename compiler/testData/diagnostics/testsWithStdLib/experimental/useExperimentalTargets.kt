class A(
    val l: MutableList<Int>,
    var w: Int,
    val q: () -> Unit
)

operator fun MutableList<Int>?.plusAssign(t: Int) {}
operator fun MutableList<Int>?.set(t: Int, w: Int) {}
operator fun MutableList<Int>?.get(t: Int) = 1
operator fun Int?.inc(): Int = 1
operator fun (() -> Unit)?.invoke() {}

fun foo(a: A?) {
    //a?.l += 1
    //a?.l[0]
    //a?.l[0]++

    val l = a?.l
    l[0]

    //a?.q()

    //a?.w++
}
