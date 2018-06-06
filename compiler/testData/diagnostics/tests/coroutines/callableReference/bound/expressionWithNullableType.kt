// FILE: J.java

public interface J {
    S platform();
}

// FILE: test.kt

interface S {
    suspend fun returnsInt() = 1
}

fun f1(x: S?): Any = x::<!UNSAFE_CALL!>returnsInt<!>
fun <T : S> f2(t: T): Any = t::returnsInt
fun <U : S> f4(u: U?): Any = u::<!UNSAFE_CALL!>returnsInt<!>
fun f5(c: List<S?>): Any = c[0]::<!UNSAFE_CALL!>returnsInt<!>

fun f6(j: J): Any = j.platform()::returnsInt
