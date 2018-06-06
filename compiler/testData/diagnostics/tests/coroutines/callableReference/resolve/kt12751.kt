// !DIAGNOSTICS: -UNUSED_PARAMETER
// KT-12751 Type inference failed with forEach and bound reference

class L<out T>

fun <T> L<T>.foo(action: suspend (T) -> Unit): Unit {}

class B {
    suspend fun remove(charSequence: CharSequence) {}
}

fun foo(list: L<CharSequence>, b: B) {
    list.foo(b::remove)
    list.foo<CharSequence>(b::remove)
}
