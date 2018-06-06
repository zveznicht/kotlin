// KT-11075 NONE_APPLICABLE reported for callable reference to an overloaded generic function with expected type provided

object TestCallableReferences {
    suspend fun <A> foo(x: A) = x
    suspend fun <B> foo(x: List<B>) = x

    fun test0(): suspend (String) -> String = TestCallableReferences::foo

    fun <T> test1(): suspend (List<T>) -> List<T> = TestCallableReferences::foo
}
