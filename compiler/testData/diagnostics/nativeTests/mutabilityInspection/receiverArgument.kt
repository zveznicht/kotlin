// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// FILE: test.kt

class Mutable(var state: Int = 0)

fun test() {
    val mutable = Mutable()
    val frozenLambda = { <!FROZEN_MUTABLE_OBJECT!>mutable<!>.state }.myFreeze()

    frozenLambda()

    mutable.state++
}

// FILE: annotation.kt

package kotlin.native.concurrent

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.BINARY)
annotation class Frozen

// FILE: background.kt

import kotlin.native.concurrent.Frozen

fun <T> @receiver: kotlin.native.concurrent.Frozen T.myFreeze(): T = null!!
