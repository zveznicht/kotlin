// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: test.kt

class NoFreeze(val immutable: String, var mutable: Int) {
    fun processCopy() {
        val local = immutable
        background {
            local
        }
    }
}

fun test() {
    val noFreeze = NoFreeze("old", 42)
    noFreeze.processCopy()
    noFreeze.mutable++
}

// FILE: annotation.kt

package kotlin.native.concurrent

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.BINARY)
annotation class Frozen

// FILE: background.kt

import kotlin.native.concurrent.Frozen

fun <R> background(@Frozen param: Any? = null, @Frozen block: () -> R): R = null!!