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

val beforeLoad = runIfLoaded { ::fn }

var fnRef = { "FAIL" }

suspend fun main() {
    fnRef = ::fn
}

fun box(): String {

    if (beforeLoad?.invoke() != null) return "fail 1"

    if (fnRef() != "OK") return "fail 2"

    val afterLoad = runIfLoaded { ::fn }

    if (afterLoad?.invoke() != "OK") return "fail 3: $afterLoad"

    return "OK"
}