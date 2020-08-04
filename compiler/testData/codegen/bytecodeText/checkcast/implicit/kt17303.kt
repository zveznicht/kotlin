enum class X {
    A, B;
    val isA get() = this === A
}

// valueOf()
// 1 CHECKCAST X

// JVM_TEMPLATES
// 2 CHECKCAST
// values()
// 1 CHECKCAST \[LX;

// JVM_IR_TEMPLATES
// 1 CHECKCAST