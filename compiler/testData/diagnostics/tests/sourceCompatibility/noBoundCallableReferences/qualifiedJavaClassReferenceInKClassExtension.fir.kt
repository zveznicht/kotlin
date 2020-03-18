// !LANGUAGE: -BoundCallableReferences
// !WITH_NEW_INFERENCE

import kotlin.reflect.KClass

val <T : Any> KClass<T>.java: Class<T> get() = null!!

val <T : Any> KClass<T>.foo: Any?
    get() {
        return <!UNRESOLVED_REFERENCE!>java.<!UNRESOLVED_REFERENCE!>lang<!>.<!UNRESOLVED_REFERENCE!>Integer<!>::hashCode<!>
    }
