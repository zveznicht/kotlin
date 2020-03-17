// !WITH_NEW_INFERENCE
// NI_EXPECTED_FILE

class A<T, U : Any> {
    fun foo() = <!OTHER_ERROR!>T<!>::toString

    fun bar() = <!OTHER_ERROR!>U<!>::toString
}

fun <T> foo() = <!OTHER_ERROR!>T<!>::toString

fun <U : Any> bar() = <!OTHER_ERROR!>U<!>::toString
