// WITH_RUNTIME
class IrConstructor : IrFunction(), IrMember
class IrSimpleFunction : IrFunction(), IrMember
interface IrMember : IrDeclaration
abstract class IrFunction : IrDeclaration
interface IrDeclaration

fun cond(s: String) = true

fun test(): String {
    val z = when {
        cond("1") -> IrConstructor()
        cond("2") -> IrSimpleFunction()
        else -> error(123)
    }

    test(z)
    return "OK"
}

fun test(s:IrFunction) {

}

fun box(): String {
    test()
    return "OK"
}