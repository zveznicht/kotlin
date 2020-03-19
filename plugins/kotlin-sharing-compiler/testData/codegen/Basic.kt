// WITH_RUNTIME
// CURIOUS_ABOUT setA, setB, setC, <init>, setReadOnly

import kotlinx.sharing.*

@Shared
class Bar(val a: String, var b: String) {
    var c: Int = 42
}
