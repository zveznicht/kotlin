data class A1(val <!REDECLARATION!>x<!>: Int, val y: String, val <!REDECLARATION!>x<!>: Int) {
    val z = ""
}

data class A2(val <!REDECLARATION!>x<!>: Int, val y: String) {
    val <!REDECLARATION!>x<!> = ""
}

data class A3(val<!SYNTAX!><!> :<!REDECLARATION!>Int<!>, val<!SYNTAX!><!> : <!REDECLARATION!>Int<!>)
