import kotlin.reflect.KClass

fun Any?.foo() {
    val result = (this as Array<KClass<*>>).<!INAPPLICABLE_CANDIDATE!>map<!>(<!UNRESOLVED_REFERENCE!>KClass<*>::java<!>).toTypedArray()
    val withLambda = (this as Array<KClass<*>>).map { it.java }.toTypedArray()
}
