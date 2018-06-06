// FILE: A.java

class A {
    public S getFoo() { return null; }
}

// FILE: test.kt

class S {
    suspend fun doIt() {}
}

fun test() {
    with (A()) {
        foo::doIt
    }
}
