// !DIAGNOSTICS: -UNUSED_EXPRESSION, -EXTENSION_SHADOWED_BY_MEMBER, -EXPERIMENTAL_FEATURE_WARNING
// !LANGUAGE: +CallableReferencesToClassMembersWithEmptyLHS

suspend fun topLevelFun() = 2

suspend fun A.extensionFun(): Int = 4

class A {
    suspend fun memberFun() = 6

    val ok2 = ::topLevelFun

    fun fail1() {
        ::extensionFun
    }

    fun fail2() {
        ::memberFun
    }
}



val ok2 = ::topLevelFun

fun A.fail1() {
    ::extensionFun
}

fun A.fail2() {
    ::memberFun
}
