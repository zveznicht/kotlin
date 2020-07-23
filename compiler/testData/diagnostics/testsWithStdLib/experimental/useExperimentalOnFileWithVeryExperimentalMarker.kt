// FIR_IDENTICAL
// !USE_EXPERIMENTAL: kotlin.RequiresOptIn

class A(
    val l: MutableList<Int>,
    val f: () -> Unit,
    var w: Int
)

operator fun <T> MutableCollection<in T>?.plusAssign(element: T) {
    this?.add(element)
}

operator fun (() -> Unit)?.invoke() {}

operator fun Int?.inc(): Int? = 1
operator fun MutableList<Int>?.get(w: Int) = 1
operator fun MutableList<Int>?.set(w: Int, r: Int) = 1

operator fun <@kotlin.internal.OnlyInputTypes K, V> Map<out K, V>.get(key: K): V? = TODO()

fun main(a: A?, q: Map<String, String>?, t: CharSequence) {
    val l = a?.l
    l[0]

    q?.get(t)
    //a?.f()
//
//    a?.w++
//
//    a?.l[0]
//    a?.l[0] += 1

}
