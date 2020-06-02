// WITH_RUNTIME
// KJS_WITH_FULL_RUNTIME
// IGNORE_BACKEND: JS, JS_IR
// IGNORE_BACKEND_FIR: JVM_IR

fun testIntCoercedToUByte() {
    var sum = 0
    for (i in 0 until 4.toUByte()) {
        sum += i.toInt()
    }
    if (sum != 6) throw AssertionError("sum=$sum")
}

fun testIntCoercedToUShort() {
    var sum = 0
    for (i in 0 until 4.toUShort()) {
        sum += i.toInt()
    }
    if (sum != 6) throw AssertionError("sum=$sum")
}

fun testIntCoercedToUInt() {
    var sum = 0
    for (i in 0 until 4u) {
        sum += i.toInt()
    }
    if (sum != 6) throw AssertionError("sum=$sum")
}

fun testIntCoercedToULong() {
    var sum = 0
    for (i in 0 until 4.toULong()) {
        sum += i.toInt()
    }
    if (sum != 6) throw AssertionError("sum=$sum")
}

fun box(): String {
    testIntCoercedToUByte()
    testIntCoercedToUShort()
    testIntCoercedToUInt()
    testIntCoercedToULong()
    return "OK"
}