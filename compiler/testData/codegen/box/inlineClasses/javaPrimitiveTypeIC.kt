// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME
import kotlin.test.*

inline class I(val x: Int)
inline class JLI(val x: java.lang.Integer)
inline class U(val x: Unit?)
inline class N(val x: Nothing?)

val pUnit = Unit
val pNUnit: Unit? = Unit

val icUnit = U(Unit)
val icNull = N(null)

val anyIcUnit: Any = icUnit
val anyIcNull: Any = icNull

val z = I(42)
val jli = JLI(java.lang.Integer(42))

fun box(): String {
    assertEquals(null, pUnit::class.javaPrimitiveType)
    assertEquals(null, pNUnit!!::class.javaPrimitiveType)
    assertEquals(null, icUnit::class.javaPrimitiveType)
    assertEquals(null, icNull::class.javaPrimitiveType)
    assertEquals(null, anyIcUnit::class.javaPrimitiveType)
    assertEquals(null, anyIcNull::class.javaPrimitiveType)
    assertEquals(null, z::class.javaPrimitiveType)
    assertEquals(null, jli::class.javaPrimitiveType)

    assertEquals(null, Unit::class.javaPrimitiveType)
    assertEquals(java.lang.Void.TYPE, Nothing::class.javaPrimitiveType)
    assertEquals(null, U::class.javaPrimitiveType)
    assertEquals(null, N::class.javaPrimitiveType)
    assertEquals(null, I::class.javaPrimitiveType)
    assertEquals(null, JLI::class.javaPrimitiveType)

    return "OK"
}
