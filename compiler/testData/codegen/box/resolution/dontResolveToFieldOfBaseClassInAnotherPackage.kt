// FILE: a/b/c/Base.java

package a.b.c;

public class Base {
    protected String qqq  = "123";

    public String getQqq() {
        return "111";
    }

    public void setQqq(String qqq) {
        this.qqq = qqq;
    }
}

// FILE: main.kt

import a.b.c.Base

open class Derived : Base() {
    fun foo1(x: Base) {
        x as Derived
        val y = x.qqq
        if (y != "111") {
            throw Exception()
        }
    }
    fun foo2(x: Derived) {
        val y = x.qqq
        if (y != "123") {
            throw Exception()
        }
    }
    fun foo3(x: Base?) {
        if (x is Derived) {
            val y = x.qqq
            if (y != "111") {
                throw Exception()
            }
        }
    }
    fun foo4(x: Base) {
        val y = x.qqq
        if (y != "111") {
            throw Exception()
        }
    }
    fun foo5() {
        this as Base
        val y = this.qqq
        if (y != "123") {
            throw Exception()
        }
    }
    fun foo6() {
        this as Base
        this as Derived
        val y = this.qqq
        if (y != "123") {
            throw Exception()
        }
    }
    fun foo7() {
        if (qqq != "123") {
            throw Exception()
        }
    }
}

fun box(): String {
    val d = Derived()
    d.foo1(d)
    d.foo2(d)
    d.foo3(d)
    d.foo3(d)
    d.foo4(d)
    d.foo6()
    d.foo7()

    return "OK"
}