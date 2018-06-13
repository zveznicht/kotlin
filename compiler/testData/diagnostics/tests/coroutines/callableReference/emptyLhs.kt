// !DIAGNOSTICS: -UNUSED_EXPRESSION, -EXTENSION_SHADOWED_BY_MEMBER, -EXPERIMENTAL_FEATURE_WARNING
// !LANGUAGE: +CallableReferencesToClassMembersWithEmptyLHS

suspend fun topLevelFun() = 2

suspend fun A.extensionFun(): Int = 4

class A {
    suspend fun memberFun() = 6

    val ok2 = ::topLevelFun

    fun test1() {
        ::extensionFun
    }

    fun test2() {
        ::memberFun
    }
}



val ok2 = ::topLevelFun

fun A.test1() {
    ::extensionFun
}

fun A.test2() {
    ::memberFun
}
