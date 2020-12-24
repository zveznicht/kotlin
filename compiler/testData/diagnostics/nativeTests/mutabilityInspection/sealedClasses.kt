// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// FILE: test.kt

sealed class Sealed {
    abstract fun doSomething()
}

class SealedMutable(var state: Int) : Sealed() {
    override fun doSomething() {
        state++
    }
}
class SealedImmutable(val prop: Int) : Sealed() {
    override fun doSomething() {
    }
}

fun test() {
    val mutable: Sealed = SealedMutable(0)
    val immutable: Sealed = SealedImmutable(0)

    background {
        mutable // TODO: hierarchies of sealed classes are file-local, should check probably
        immutable
    }

    immutable.doSomething()
    mutable.doSomething()
}

// FILE: annotation.kt

package kotlin.native.concurrent

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.BINARY)
annotation class Frozen

// FILE: background.kt

import kotlin.native.concurrent.Frozen

fun <R> background(@Frozen param: Any? = null, @Frozen block: () -> R): R = null!!