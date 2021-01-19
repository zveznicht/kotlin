// TARGET_BACKEND: JVM
// FILE: A.kt
// WITH_RUNTIME

interface SimpleTypeMarker

// FILE: B.kt

class SimpleType : SimpleTypeMarker {
    fun foo() = "OK"
}

interface User {
    fun SimpleTypeMarker.bar(): String {
        require(this is SimpleType)
        return this.foo()
    }
}

class UserImpl : User

fun box(): String {
    return with(UserImpl()) {
        SimpleType().bar()
    }
}