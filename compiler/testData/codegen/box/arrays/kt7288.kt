
fun foo() = intArrayOf()
fun box(): String {
    val x = ((foo() as Any) is Array<*>)

    return "OK"
}