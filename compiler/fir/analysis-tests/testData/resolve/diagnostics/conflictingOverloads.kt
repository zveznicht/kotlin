fun <!CONFLICTING_OVERLOADS!>test<!>(x: Int) {}

fun <!CONFLICTING_OVERLOADS!>test<!>(y: Int) {}

fun test() {}

fun test(z: Int, c: Char) {}

open class <!REDECLARATION!>A<!> {
    open fun rest(s: String) {}

    open val u = 20
}

class <!REDECLARATION!>A<!> {

}

class <!REDECLARATION!>B<!> : A {
    override fun <!CONFLICTING_OVERLOADS!>rest<!>(s: String) {}

    fun <!CONFLICTING_OVERLOADS!>rest<!>(s: String) {}

    fun rest(l: Long) {}

    override val u = 310
}

interface <!REDECLARATION!>B<!>

enum class <!REDECLARATION!>B<!>

val <!REDECLARATION!>u<!> = 10
val <!REDECLARATION!>u<!> = 20

typealias <!REDECLARATION!>TA<!> = A
typealias <!REDECLARATION!>TA<!> = B

typealias BA = A

fun <T> kek(t: T) where T : (String) -> Any?, T : Char {}
fun <T> kek(t: T) where T : () -> Boolean, T : String {}
fun <T : Int> kek(t: T) {}

fun lol(a: Array<Int>) {}
fun lol(a: Array<Boolean>) {}

fun <T> <!CONFLICTING_OVERLOADS!>mem<!>(t: T) where T : () -> Boolean, T : String {}
fun <T> <!CONFLICTING_OVERLOADS!>mem<!>(t: T) where T : String, T : () -> Boolean {}

class M {
    companion <!REDECLARATION!>object<!> {}
    val <!REDECLARATION!>Companion<!> = object : Any {}
}

fun B.foo() {}

class L {
    fun B.foo() {}
}

fun mest()

class mest

fun() {}

private fun() {}