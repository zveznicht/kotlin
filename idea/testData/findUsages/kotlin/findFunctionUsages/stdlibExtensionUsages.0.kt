// PSI_ELEMENT: org.jetbrains.kotlin.psi.KtNamedFunction
// OPTIONS: usages
package sample

fun foo(list: List<String>) {
    val s = list.<caret>joinToString()
}

fun bar(list: List<Int>) {
    val s = list.joinToString()
}


