fun <!CONFLICTING_OVERLOADS!>takeString<!>(s: String) {}

class Wrapper(val s: String?) {
    fun withThis() {
        if (s != null) {
            takeString(this.s) // Should be OK
        }
        if (this.s != null) {
            takeString(s) // Should be OK
        }
    }
}

fun <!CONFLICTING_OVERLOADS!>takeString<!>(s: String) {}