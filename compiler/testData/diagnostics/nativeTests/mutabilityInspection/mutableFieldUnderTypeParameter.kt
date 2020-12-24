// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: test.kt

class Mutable(var state: String)
class Box<T>(val generic: T) {
    fun freezeVal() {
        background {
            generic // TODO: unreported runtime exception
        }
    }
}

fun test() {
    val mutable = Mutable("old")
    val b = Box(mutable)
    b.freezeVal()
    mutable.state = "new"
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
