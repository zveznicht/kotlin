// ENABLE_FIR_TEST
    open class Bar {
        companion object {
            class FromBarCompanion
        }
    }

class Foo : Bar() {
    val a = <selection>Companion.FromBarCompanion()</selection>
}