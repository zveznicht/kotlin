// FIR_IDENTICAL
// !LANGUAGE: +MultiPlatformProjects
// MODULE: m1-common
// FILE: common.kt

expect fun <!CONFLICTING_OVERLOADS!>foo<!>()
expect fun <!CONFLICTING_OVERLOADS!>foo<!>()

expect fun foo(x: Int)
