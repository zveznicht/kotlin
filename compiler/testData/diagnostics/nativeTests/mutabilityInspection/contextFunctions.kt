// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: test.kt

class Frozen(val immutable: Int, var mutable: Int = 42) {
    fun doSomething() {
        background {
            <!FROZEN_MUTABLE_OBJECT!>immutable<!>
            <!FROZEN_MUTABLE_OBJECT!>let<!> {
                <!FROZEN_MUTABLE_OBJECT!>immutable<!>
            }

            <!FROZEN_MUTABLE_OBJECT!>run<!> {
                immutable
            }
            with(Frozen(10)) {
                immutable
                mutable++
            }
        }
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

public inline fun <T, R> T.let(block: (T) -> R): R = null!!
public inline fun <R> run(block: () -> R): R = null!!
public inline fun <T, R> T.run(block: T.() -> R): R = null!!
public inline fun <T, R> with(receiver: T, block: T.() -> R): R = null!!
