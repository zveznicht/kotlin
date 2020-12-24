// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// FILE: test.kt

class Mutable(var state: Int = 0)

class SimpleTransitivelyMutable(val mutable: Mutable)

class TransitivelyMutableWithBackReferences(mutable: Mutable) {
    val selfType: TransitivelyMutableWithBackReferences? = null
    val transitive: SimpleTransitivelyMutable = SimpleTransitivelyMutable(mutable)
}

fun test() {
    val mutable = Mutable()
    val withBackReferences = TransitivelyMutableWithBackReferences(mutable)

    background {
        <!FROZEN_MUTABLE_OBJECT!>withBackReferences<!>
    }

    mutable.state++
}

// FILE: annotation.kt

package kotlin.native.concurrent

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.BINARY)
annotation class Frozen

// FILE: background.kt

import kotlin.native.concurrent.Frozen

fun <R> background(@Frozen param: Any? = null, @Frozen block: () -> R): R = null!!