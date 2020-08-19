// FILE: 1.kt
package test

fun <V> openFuture(): OpenFuture<V> = object : OpenFuture<V>{}

interface OpenFuture<V>
interface Observer<A>
class Observable<B> {
    fun subscribe(observer: Observer<B>) {}
}

inline fun <T> T.also2(block: (T) -> Unit): T {
    block(this)
    return this
}
// FILE: 2.kt

import test.*

fun <C> Observable<C>.toFuture() {
    openFuture<C>().also2 {
        this.subscribe(object : Observer<C> {})
    }
}

fun box(): String {
    Observable<String>().toFuture()
    return "OK"
}