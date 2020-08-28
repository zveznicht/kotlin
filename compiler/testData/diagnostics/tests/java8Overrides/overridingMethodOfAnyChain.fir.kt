interface IA

interface IB : IA {
    override fun <!ANY_METHOD_IMPLEMENTED_IN_INTERFACE!>toString<!>(): String = "IB"
}

interface IC : IB {
    override fun <!ANY_METHOD_IMPLEMENTED_IN_INTERFACE!>toString<!>(): String = "IC"
}

interface ID : IC {
    override fun <!ANY_METHOD_IMPLEMENTED_IN_INTERFACE!>toString<!>(): String = "ID"
}