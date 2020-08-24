// ENABLE_FIR_TEST
object X{ }

<selection>class Y {
    fun f(op: X.()->Unit) {
        X.op()
    }
}</selection>