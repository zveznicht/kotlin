// SPLIT_PER_MODULE
// DONT_TARGET_EXACT_BACKEND: JS
// MODULE: lib
// FILE: lib.kt
// MODULE_KIND: COMMON_JS
package lib

fun fn() = "OK"

// MODULE: main(lib)
// FILE: main.kt
// ASYNC_IMPORT: <lib>
// MODULE_KIND: COMMON_JS
// CALL_MAIN
package main

import lib.*

val beforeLoad = runIfLoaded { fn() }

var fnCopy = "FAIL"

suspend fun main() {
    fnCopy = fn()
}

fun box(): String {

    if (beforeLoad != null) return "fail 1: $beforeLoad"

    if (fnCopy != "OK") return "fail 2: $fnCopy"

    val afterLoad = runIfLoaded { fn() }

    if (afterLoad != "OK") return "fail 3: $afterLoad"

    return "OK"
}