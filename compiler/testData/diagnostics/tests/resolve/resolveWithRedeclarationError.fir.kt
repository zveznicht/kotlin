//If this test hangs, it means something is broken.
package c

fun z(view: () -> Unit) {}

fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }
fun <!CONFLICTING_OVERLOADS!>x<!>() = z { z { z { z { z { z { z { z { } } } } } } } }

class x() {}