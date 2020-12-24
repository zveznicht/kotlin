// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: test.kt

class Mutable(var state: String)
class TransitivelyMutable1(val cause: Mutable)
class TransitivelyMutable2(val cause: TransitivelyMutable1)
class TransitivelyMutable3(val cause: TransitivelyMutable2)

fun test() {
    val mutable = Mutable("old")
    val t1 = TransitivelyMutable1(mutable)
    val t2 = TransitivelyMutable2(t1)
    val transitivelyMutable = TransitivelyMutable3(t2)

    background {
        <!FROZEN_MUTABLE_OBJECT!>transitivelyMutable<!>
    }

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