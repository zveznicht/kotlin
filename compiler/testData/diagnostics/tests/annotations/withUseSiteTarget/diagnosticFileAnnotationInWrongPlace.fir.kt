package bar

@file:foo
val prop

@file:[bar baz]
fun func() {}

@file:[baz]
class C

<!SYNTAX!>@file:<!>
interface <!REDECLARATION!>T<!>

@file:[<!SYNTAX!><!>]
interface <!REDECLARATION!>T<!>

annotation class foo
annotation class bar
annotation class baz