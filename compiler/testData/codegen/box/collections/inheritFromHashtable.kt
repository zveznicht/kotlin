// TARGET_BACKEND: JVM
// WITH_RUNTIME
// FULL_JDK
import java.util.Hashtable

class A : java.util.Hashtable<String, String>()

fun box(): String {
    val a = A()
    return "OK"
}