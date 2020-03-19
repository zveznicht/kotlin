// WITH_RUNTIME
// CURIOUS_ABOUT setA, setB, setC, setReadOnly, share

import kotlinx.sharing.*

@Shared
data class Bar(val a: String, var b: String, var c: Int = 42)
