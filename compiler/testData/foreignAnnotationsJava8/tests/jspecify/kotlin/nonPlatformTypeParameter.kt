// JAVA_SOURCES: NonPlatformTypeParameter.java

fun main(a1: NonPlatformTypeParameter<Any?>, a2: NonPlatformTypeParameter<String>) {
    a1.foo(null)
    a1.bar<String?>(null)
    a1.bar<String>(null)
    a1.bar<String>("")

    a2.foo(null)
    a2.bar<String?>(null)
    a2.bar<String>(null)
    a2.bar<String>("")
}
