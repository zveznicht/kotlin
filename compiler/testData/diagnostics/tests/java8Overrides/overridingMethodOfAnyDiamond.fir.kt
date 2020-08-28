interface ILeft {
    override fun toString(): String
}

interface IRight {
    override fun toString(): String
}

interface IDiamond : ILeft, IRight {
    override fun <!ANY_METHOD_IMPLEMENTED_IN_INTERFACE!>toString<!>(): String = "IDiamond"
}