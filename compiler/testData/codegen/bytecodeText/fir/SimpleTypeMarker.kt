// TARGET_BACKEND: JVM
// WITH_RUNTIME

interface SimpleTypeMarker

class SimpleType : SimpleTypeMarker {
    fun foo() = "OK"
}

class User {
    fun SimpleTypeMarker.bar(): String {
        require(this is SimpleType)
        return this.foo()
    }
}

fun box(): String {
    return with(User()) {
        SimpleType().bar()
    }
}

// 0 INVOKEVIRTUAL SimpleTypeMarker.foo
// 1 INVOKEVIRTUAL SimpleType.foo