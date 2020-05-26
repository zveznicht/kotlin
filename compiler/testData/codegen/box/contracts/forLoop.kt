// !USE_EXPERIMENTAL: kotlin.contracts.ExperimentalContracts
// IGNORE_BACKEND: NATIVE
// WITH_RUNTIME

import kotlin.contracts.*

fun runOnce(action: () -> Unit) {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    action()
}

fun o(): String {
    var res = ""
    for (o in listOf("O")) {
        runOnce {
            res += o
        }
    }
    return res
}

fun k(): String {
    var res = ""
    for ((k, _) in listOf("K" to "FAIL")) {
        runOnce {
            res += k
        }
    }
    return res
}

fun box(): String {
    return o() + k()
}
