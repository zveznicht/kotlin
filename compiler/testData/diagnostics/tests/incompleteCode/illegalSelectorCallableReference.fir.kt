// !DIAGNOSTICS: -UNUSED_EXPRESSION
// !WITH_NEW_INFERENCE

fun test() {
    <!UNRESOLVED_REFERENCE!>"a"."b"::foo<!>
    "a"."b"::class
    <!UNRESOLVED_REFERENCE!>"a"."b"."c"::foo<!>
    "a"."b"."c"::class
}
