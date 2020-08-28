interface IA {
    override fun <!ANY_METHOD_IMPLEMENTED_IN_INTERFACE!>toString<!>(): String = "IA"

    override fun <!ANY_METHOD_IMPLEMENTED_IN_INTERFACE!>equals<!>(other: Any?): Boolean = super.equals(other)

    override fun <!ANY_METHOD_IMPLEMENTED_IN_INTERFACE!>hashCode<!>(): Int {
        return 42;
    }
}