val <!REDECLARATION!>a<!> : Int = 1
val <!REDECLARATION!>a<!> : Int = 1
val <!REDECLARATION!>a<!> : Int = 1

val <!REDECLARATION!>b<!> : Int = 1
val <!REDECLARATION!>b<!> : Int = 1
val <!REDECLARATION!>b<!> : Int = 1
val <!REDECLARATION!>b<!> : Int = 1

fun <!CONFLICTING_OVERLOADS!>foo<!>() {} // and here too
fun <!CONFLICTING_OVERLOADS!>foo<!>() {} // and here
fun <!CONFLICTING_OVERLOADS!>foo<!>() {} // and here
fun <!CONFLICTING_OVERLOADS!>foo<!>() {} // and here

fun <!CONFLICTING_OVERLOADS!>bar<!>() {} // and here
fun <!CONFLICTING_OVERLOADS!>bar<!>() {} // and here
fun <!CONFLICTING_OVERLOADS!>bar<!>() {} // and here

class A {
    val <!REDECLARATION!>a<!> : Int = 1
    val <!REDECLARATION!>a<!> : Int = 1
    val <!REDECLARATION!>a<!> : Int = 1

    val <!REDECLARATION!>b<!> : Int = 1
    val <!REDECLARATION!>b<!> : Int = 1
    val <!REDECLARATION!>b<!> : Int = 1
    val <!REDECLARATION!>b<!> : Int = 1

    fun <!CONFLICTING_OVERLOADS!>foo<!>() {} // and here too
    fun <!CONFLICTING_OVERLOADS!>foo<!>() {} // and here
    fun <!CONFLICTING_OVERLOADS!>foo<!>() {} // and here
    fun <!CONFLICTING_OVERLOADS!>foo<!>() {} // and here

    fun <!CONFLICTING_OVERLOADS!>bar<!>() {} // and here
    fun <!CONFLICTING_OVERLOADS!>bar<!>() {} // and here
    fun <!CONFLICTING_OVERLOADS!>bar<!>() {} // and here
}
