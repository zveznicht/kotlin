// !DIAGNOSTICS: -UNUSED_PARAMETER
open class B(x: Int)
class <!SUPERTYPE_INITIALIZED_WITHOUT_PRIMARY_CONSTRUCTOR!>A<!> : B(1) {
    constructor(): super(1)
}
