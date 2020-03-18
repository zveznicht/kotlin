// !DIAGNOSTICS: -UNUSED_EXPRESSION
// !WITH_NEW_INFERENCE

// FILE: simpleName.kt

package foo

fun test() {
    foo::test
}

// FILE: qualifiedName.kt

package foo.bar

fun test() {
    foo.bar::test
}
