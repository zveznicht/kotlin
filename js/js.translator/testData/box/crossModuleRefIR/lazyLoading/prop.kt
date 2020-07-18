// SPLIT_PER_MODULE
// DONT_TARGET_EXACT_BACKEND: JS
// MODULE: lib
// FILE: lib.kt
// MODULE_KIND: COMMON_JS
package lib

var prop = "OK"

// MODULE: main(lib)
// FILE: main.kt
// ASYNC_IMPORT: <lib>
// MODULE_KIND: COMMON_JS
// CALL_MAIN
package main

import lib.*

val beforeLoad = runIfLoaded { prop }

var propCopy = "FAIL"

suspend fun main() {
    propCopy = prop
}

fun box(): String {

    if (beforeLoad != null) return "fail 1"

    if (propCopy != "OK") return "fail 2: $propCopy"

    val afterLoad = runIfLoaded { prop }

    if (afterLoad != "OK") return "fail 3: $afterLoad"

    return "OK"
}