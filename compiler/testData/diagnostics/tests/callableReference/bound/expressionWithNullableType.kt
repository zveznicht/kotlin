// !WITH_NEW_INFERENCE
// FILE: J.java

public interface J {
    String platformString();
}

// FILE: test.kt

fun f1(x: Int?): Any = <!NI;TYPE_MISMATCH!>x::<!OI;UNSAFE_CALL!>hashCode<!><!>
fun <T> f2(t: T): Any = <!NI;TYPE_MISMATCH!>t::<!OI;UNSAFE_CALL!>hashCode<!><!>
fun <S : String?> f3(s: S): Any = <!NI;TYPE_MISMATCH!>s::<!OI;UNSAFE_CALL!>hashCode<!><!>
fun <U : Any> f4(u: U?): Any = <!NI;TYPE_MISMATCH!>u::<!OI;UNSAFE_CALL!>hashCode<!><!>
fun f5(c: List<*>): Any = <!NI;TYPE_MISMATCH!>c[0]::<!OI;UNSAFE_CALL!>hashCode<!><!>

fun f6(j: J): Any = j.platformString()::hashCode
