// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: test.kt

open class Base {
    open val i: Int = 42
}

open class Derived : Base() {
    override var i: Int = 42
}

class Mutable : Derived()

fun test() {
    val mutable = Mutable()

    background {
        <!FROZEN_MUTABLE_OBJECT!>mutable<!>
    }

    mutable.i++
}

// FILE: annotation.kt

package kotlin.native.concurrent

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.BINARY)
annotation class Frozen

// FILE: background.kt

import kotlin.native.concurrent.Frozen

fun <R> background(@Frozen param: Any? = null, @Frozen block: () -> R): R = null!!