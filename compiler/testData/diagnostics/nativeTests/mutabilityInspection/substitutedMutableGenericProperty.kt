// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: test.kt

class Mutable(var state: Int)
class Generic<T>(val value: T)

fun test() {
    val mutable = Mutable(0)
    val genericMutable = Generic(mutable)

    background {
        <!FROZEN_MUTABLE_OBJECT!>genericMutable<!>
    }

    genericMutable.value.state++
}

// FILE: annotation.kt

package kotlin.native.concurrent

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.BINARY)
annotation class Frozen

// FILE: background.kt

import kotlin.native.concurrent.Frozen

fun <R> background(@Frozen param: Any? = null, @Frozen block: () -> R): R = null!!