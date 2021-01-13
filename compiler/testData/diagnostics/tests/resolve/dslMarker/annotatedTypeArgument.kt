// !DIAGNOSTICS: -UNUSED_PARAMETER
interface F
@DslMarker
annotation class CheckAnnotation
@CheckAnnotation
interface A {
    val fieldOfA: F
}
@CheckAnnotation
interface B
fun withA(h: A.() -> Unit): Unit = TODO("")
fun A.useAWithAReceiver(h: A.() -> Unit): Unit = TODO("")
fun A.useBWithAReceiver(h: B.() -> Unit): Unit = TODO("")
fun test1() {
    withA {
        useBWithAReceiver {
            // should not compile
            <!DSL_SCOPE_VIOLATION!>fieldOfA<!>
            // should not compile
            <!DSL_SCOPE_VIOLATION!>useBWithAReceiver<!> {
            }
        }
    }
}
class Test2(): A {
    override val fieldOfA: F = TODO("")
    private val privateField: Any
    init {
        useAWithAReceiver {
            // should compile
            privateField
        }
        useBWithAReceiver {
            // should compile
            privateField
            // should not compile
            <!DSL_SCOPE_VIOLATION!>fieldOfA<!>
            // should not compile
            <!DSL_SCOPE_VIOLATION!>useBWithAReceiver<!> {
            }
        }
    }
}
