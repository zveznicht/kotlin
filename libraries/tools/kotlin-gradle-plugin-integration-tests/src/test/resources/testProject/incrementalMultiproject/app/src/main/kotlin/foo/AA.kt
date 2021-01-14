package foo

import bar.*

open class AA : A() {
    fun aa() {}

    fun test() = ab()

    open protected fun method() {
        //this method will be made public
    }
}