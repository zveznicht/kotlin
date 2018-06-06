// !DIAGNOSTICS: -UNUSED_VARIABLE

class Foo<out T>(val name: T) {

    fun testFunc() {
        val ok1 = this::func
        val ok2 = this@Foo::func
        val ok3 = object { val y: Any = this@Foo::func }

        val fail1 = Foo(name)::<!INVISIBLE_MEMBER!>func<!>
    }

    private suspend fun func(t: T): T = t
}
