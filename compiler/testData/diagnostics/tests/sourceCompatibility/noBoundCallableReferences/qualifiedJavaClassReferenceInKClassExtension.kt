// !LANGUAGE: -BoundCallableReferences
// !WITH_NEW_INFERENCE

import kotlin.reflect.KClass

val <T : Any> KClass<T>.java: Class<T> get() = null!!

val <T : Any> KClass<T>.foo: Any?
    get() {
        return <!UNSUPPORTED_FEATURE!>java.<!UNRESOLVED_REFERENCE!>lang<!>.<!DEBUG_INFO_MISSING_UNRESOLVED!>Integer<!><!>::<!NI;CALLABLE_REFERENCE_RESOLUTION_AMBIGUITY, OI;OVERLOAD_RESOLUTION_AMBIGUITY!>hashCode<!>
    }
