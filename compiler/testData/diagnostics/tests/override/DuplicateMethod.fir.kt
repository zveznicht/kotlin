interface Some {
    fun test()
}

class SomeImpl : Some  {
    override fun <!CONFLICTING_OVERLOADS!>test<!>() {}
    override fun <!CONFLICTING_OVERLOADS!>test<!>() {}
}