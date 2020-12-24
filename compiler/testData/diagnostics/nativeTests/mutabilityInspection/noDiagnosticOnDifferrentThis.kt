// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// FILE: test.kt

class Mutable(var state: String) {
    fun doSomething() {
        background {
            with(Unit) {
                this
            }
        }
    }
}

fun test() {
    val mutable = Mutable("old")
    mutable.doSomething()
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
public inline fun <T, R> with(receiver: T, block: T.() -> R): R = null!!