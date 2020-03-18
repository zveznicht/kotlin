// !WITH_NEW_INFERENCE

fun <T, U> T.map(f: (T) -> U) = f(this)

fun consume(<!UNUSED_PARAMETER!>s<!>: String) {}

fun test() {
    consume(1.<!NI;NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>map<!>(::<!UNRESOLVED_REFERENCE!>foo<!>))
}