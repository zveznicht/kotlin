// WITH_RUNTIME
// CURIOUS_ABOUT share

import kotlinx.sharing.*

@Shared
data class Bar(val a: String, var b: String, var c: Int = 42)

@Shared
data class Foo(val d: String, val bar: Bar)
