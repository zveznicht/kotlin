// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: test.kt

class Mutable(var state: String)

fun test() {
    background {
        val local = Mutable("")
        local
    }
}

// FILE: annotation.kt

package kotlin.native.concurrent

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.BINARY)
annotation class Frozen

// FILE: background.kt

import kotlin.native.concurrent.Frozen

fun <R> background(@Frozen param: Any? = null, @Frozen block: () -> R): R = null!!