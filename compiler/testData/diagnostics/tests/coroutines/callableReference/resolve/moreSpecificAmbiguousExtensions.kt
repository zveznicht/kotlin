// !CHECK_TYPE
// !DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_VARIABLE

interface IA
interface IB : IA

suspend fun IA.extFun(x: IB) {}
suspend fun IB.extFun(x: IA) {}

fun test() {
    val extFun1 = IA::extFun
    val extFun2 = IB::<!OVERLOAD_RESOLUTION_AMBIGUITY!>extFun<!>
}

fun testWithExpectedType() {
    val extFun_AB_A: suspend IA.(IB) -> Unit = IA::extFun
    val extFun_AA_B: suspend IA.(IA) -> Unit = IB::<!NONE_APPLICABLE!>extFun<!>
    val extFun_BB_A: suspend IB.(IB) -> Unit = IA::extFun
    val extFun_BA_B: suspend IB.(IA) -> Unit = IB::extFun
    val extFun_BB_B: suspend IB.(IB) -> Unit = IB::<!OVERLOAD_RESOLUTION_AMBIGUITY!>extFun<!>
}