// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: test.kt

class This(var mutable: String) {
    fun test() {
        background {
            <!FROZEN_MUTABLE_OBJECT!>this<!>
        }
    }
}

fun test() {
    val mutable = This("old")
    mutable.test()

    mutable.mutable = "new"
}

// FILE: annotation.kt

package kotlin.native.concurrent

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.BINARY)
annotation class Frozen

// FILE: background.kt

import kotlin.native.concurrent.Frozen

fun <R> background(@Frozen param: Any? = null, @Frozen block: () -> R): R = null!!