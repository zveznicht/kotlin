// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: test.kt

open class Base {
    open val i: Int = 42
}
class Immutable : Base()

fun test() {
    val immutable = Immutable()

    background {
        immutable
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