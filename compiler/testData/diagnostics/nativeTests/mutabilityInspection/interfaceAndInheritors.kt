// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// FILE: test.kt

interface IFace {
    fun doSomething()
}

class MutableInheritor(var state: Int = 0) : IFace {
    override fun doSomething() {
        state++
    }
}

class ImmutableInheritor : IFace {
    override fun doSomething() {
    }
}

fun test() {
    val immutable: IFace = ImmutableInheritor()
    val mutable: IFace = MutableInheritor()

    background {
        immutable
        mutable // will cause runtime error; can't do much about it
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