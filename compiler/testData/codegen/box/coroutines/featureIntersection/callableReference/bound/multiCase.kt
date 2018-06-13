// IGNORE_BACKEND: JS

// COMMON_COROUTINES_TEST
// WITH_RUNTIME
// WITH_COROUTINES

import helpers.*
import COROUTINES_PACKAGE.*

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(EmptyContinuation)
}

class A(var v: Int) {
    suspend fun f(x: Int) = x * v
}

suspend fun A.g(x: Int) = x * f(x);

object F {
    var u = 0
}

fun box(): String {
    builder {
        val a = A(5)

        val af = a::f
        if (af(10) != 50) throw RuntimeException("fail4: ${af(10)}")

        val ag = a::g
        if (ag(10) != 500) throw RuntimeException("fail5: ${ag(10)}")

        val fu = F::u
        if (fu() != 0) throw RuntimeException("fail9: ${fu()}")
        if (fu.get() != 0) throw RuntimeException("fail10: ${fu.get()}")
        fu.set(8)
        if (F.u != 8) throw RuntimeException("fail11: ${F.u}")

        val x = 100

        suspend fun A.lf() = v * x;
        val alf = a::lf
        if (alf() != 500) throw RuntimeException("fail9: ${alf()}")
    }

    return "OK"
}
