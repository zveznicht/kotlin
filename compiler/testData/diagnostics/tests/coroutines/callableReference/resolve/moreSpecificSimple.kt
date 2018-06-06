// !CHECK_TYPE

interface IA
interface IB : IA

suspend fun IA.extFun() {}
suspend fun IB.extFun() {}

fun test() {
    val extFun = IB::extFun
    checkSubtype<suspend IB.() -> Unit>(extFun)
}
