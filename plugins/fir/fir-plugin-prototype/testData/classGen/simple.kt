import org.jetbrains.kotlin.fir.plugin.WithClass
import org.jetbrains.kotlin.fir.plugin.AllOpen

// TODO

@WithClass
class A : AGen {
    override fun foo(): String {
        return ""
    }
}

class B : BGen {

}

fun test(a: AGen) {
    takeString(a.foo())
}

fun takeString(s: String) {}