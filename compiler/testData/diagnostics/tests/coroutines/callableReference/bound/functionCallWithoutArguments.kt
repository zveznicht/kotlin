class S {
    suspend fun id() = this
}

fun test(<!UNUSED_PARAMETER!>s<!>: S) = s.<!ILLEGAL_SUSPEND_FUNCTION_CALL, FUNCTION_CALL_EXPECTED!>id<!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>id<!>
