--------
+ public inline fun <T> arrayOf(vararg elements: T): kotlin.Array<T>
- @kotlin.js.library
- public fun <T> arrayOf(vararg elements: T): kotlin.Array<T>
--------
+ public inline fun <reified T> arrayOfNulls(size: kotlin.Int): kotlin.Array<T?>
- public inline fun <reified @kotlin.internal.PureReifiable
- T> arrayOf(vararg elements: T): kotlin.Array<T>
--------
+ public inline fun booleanArrayOf(vararg elements: kotlin.Boolean): kotlin.BooleanArray
- public fun <reified @kotlin.internal.PureReifiable
- T> arrayOfNulls(size: kotlin.Int): kotlin.Array<T?>
--------
+ public inline fun byteArrayOf(vararg elements: kotlin.Byte): kotlin.ByteArray
- @kotlin.js.library
- public fun booleanArrayOf(vararg elements: kotlin.Boolean): kotlin.BooleanArray
--------
+ public inline fun charArrayOf(vararg elements: kotlin.Char): kotlin.CharArray
- public fun booleanArrayOf(vararg elements: kotlin.Boolean): kotlin.BooleanArray
--------
- @kotlin.js.library
- public fun byteArrayOf(vararg elements: kotlin.Byte): kotlin.ByteArray
- 
- public fun byteArrayOf(vararg elements: kotlin.Byte): kotlin.ByteArray
- 
- @kotlin.js.library
- public fun charArrayOf(vararg elements: kotlin.Char): kotlin.CharArray
- 
- public fun charArrayOf(vararg elements: kotlin.Char): kotlin.CharArray
- 
--------
+ public inline fun doubleArrayOf(vararg elements: kotlin.Double): kotlin.DoubleArray
- @kotlin.js.library
- public fun doubleArrayOf(vararg elements: kotlin.Double): kotlin.DoubleArray
--------
- public fun doubleArrayOf(vararg elements: kotlin.Double): kotlin.DoubleArray
- 
--------
- public inline fun <reified @kotlin.internal.PureReifiable
- T> emptyArray(): kotlin.Array<T>
- 
--------
+ public inline fun floatArrayOf(vararg elements: kotlin.Float): kotlin.FloatArray
- @kotlin.js.library
- public fun floatArrayOf(vararg elements: kotlin.Float): kotlin.FloatArray
--------
+ public inline fun intArrayOf(vararg elements: kotlin.Int): kotlin.IntArray
- public fun floatArrayOf(vararg elements: kotlin.Float): kotlin.FloatArray
--------
- @kotlin.js.library
- public fun intArrayOf(vararg elements: kotlin.Int): kotlin.IntArray
- 
- public fun intArrayOf(vararg elements: kotlin.Int): kotlin.IntArray
- 
--------
+ public inline fun longArrayOf(vararg elements: kotlin.Long): kotlin.LongArray
- @kotlin.js.library
- public fun longArrayOf(vararg elements: kotlin.Long): kotlin.LongArray
--------
- public fun longArrayOf(vararg elements: kotlin.Long): kotlin.LongArray
- 
--------
+ public inline fun shortArrayOf(vararg elements: kotlin.Short): kotlin.ShortArray
- @kotlin.js.library
- public fun shortArrayOf(vararg elements: kotlin.Short): kotlin.ShortArray
--------
- public fun shortArrayOf(vararg elements: kotlin.Short): kotlin.ShortArray
- 
--------
- @kotlin.js.library(name = "doubleToBits")
--------
- @kotlin.js.library(name = "floatToBits")
--------
- @kotlin.js.library(name = "doubleToRawBits")
--------
- @kotlin.js.library(name = "floatToRawBits")
--------
- public interface Annotation {
- }
- 
--------
+     public open override operator fun equals(other: kotlin.Any?): kotlin.Boolean
+ 
+     public open override fun hashCode(): kotlin.Int
+ 
--------
+     public open override fun toString(): kotlin.String
+ 
--------
+     public open override operator fun equals(other: kotlin.Any?): kotlin.Boolean
+ 
+     public open override fun hashCode(): kotlin.Int
+ 
--------
+     public open override fun toString(): kotlin.String
+ 
--------
+     public open override operator fun equals(other: kotlin.Any?): kotlin.Boolean
+ 
+     public open override fun hashCode(): kotlin.Int
+ 
--------
+     @kotlin.js.JsName(name = "toString")
+     public open override fun toString(): kotlin.String
+ 
--------
- public interface CharSequence {
-     public abstract val length: kotlin.Int { get; }
- 
-     public abstract operator fun get(index: kotlin.Int): kotlin.Char
- 
-     public abstract fun subSequence(startIndex: kotlin.Int, endIndex: kotlin.Int): kotlin.CharSequence
- }
- 
--------
+     public open override operator fun equals(other: kotlin.Any?): kotlin.Boolean
+ 
+     public open override fun hashCode(): kotlin.Int
+ 
--------
+     public open override fun toString(): kotlin.String
+ 
--------
+     public open override operator fun compareTo(other: E): kotlin.Int
-     protected final fun clone(): kotlin.Any
--------
+     public open override operator fun equals(other: kotlin.Any?): kotlin.Boolean
-     public final override operator fun compareTo(other: E): kotlin.Int
--------
+     public open override fun hashCode(): kotlin.Int
-     public final override operator fun equals(other: kotlin.Any?): kotlin.Boolean
--------
-     public final override fun hashCode(): kotlin.Int
- 
--------
+     public open override operator fun equals(other: kotlin.Any?): kotlin.Boolean
+ 
+     public open override fun hashCode(): kotlin.Int
+ 
--------
+     public open override fun toString(): kotlin.String
+ 
--------
- public interface Function<out R> {
- }
- 
--------
+     public open override operator fun equals(other: kotlin.Any?): kotlin.Boolean
+ 
+     public open override fun hashCode(): kotlin.Int
+ 
--------
+     public open override fun toString(): kotlin.String
+ 
--------
+     public final inline operator fun compareTo(other: kotlin.Byte): kotlin.Int
-     public final operator fun compareTo(other: kotlin.Byte): kotlin.Int
--------
+     public final inline operator fun compareTo(other: kotlin.Double): kotlin.Int
-     public final operator fun compareTo(other: kotlin.Double): kotlin.Int
--------
+     public final inline operator fun compareTo(other: kotlin.Float): kotlin.Int
-     public final operator fun compareTo(other: kotlin.Float): kotlin.Int
--------
+     public final inline operator fun compareTo(other: kotlin.Int): kotlin.Int
-     public final operator fun compareTo(other: kotlin.Int): kotlin.Int
--------
+     public final inline operator fun compareTo(other: kotlin.Short): kotlin.Int
-     public final operator fun compareTo(other: kotlin.Short): kotlin.Int
--------
+     public final inline operator fun div(other: kotlin.Byte): kotlin.Long
-     public final operator fun div(other: kotlin.Byte): kotlin.Long
--------
+     public final inline operator fun div(other: kotlin.Double): kotlin.Double
-     public final operator fun div(other: kotlin.Double): kotlin.Double
--------
+     public final inline operator fun div(other: kotlin.Float): kotlin.Float
-     public final operator fun div(other: kotlin.Float): kotlin.Float
--------
+     public final inline operator fun div(other: kotlin.Int): kotlin.Long
-     public final operator fun div(other: kotlin.Int): kotlin.Long
--------
+     public final inline operator fun div(other: kotlin.Short): kotlin.Long
-     public final operator fun div(other: kotlin.Short): kotlin.Long
--------
+     public open override operator fun equals(other: kotlin.Any?): kotlin.Boolean
+ 
+     public open override fun hashCode(): kotlin.Int
+ 
--------
+     public final inline operator fun minus(other: kotlin.Byte): kotlin.Long
-     public final operator fun minus(other: kotlin.Byte): kotlin.Long
--------
+     public final inline operator fun minus(other: kotlin.Double): kotlin.Double
-     public final operator fun minus(other: kotlin.Double): kotlin.Double
--------
+     public final inline operator fun minus(other: kotlin.Float): kotlin.Float
-     public final operator fun minus(other: kotlin.Float): kotlin.Float
--------
+     public final inline operator fun minus(other: kotlin.Int): kotlin.Long
-     public final operator fun minus(other: kotlin.Int): kotlin.Long
--------
+     public final inline operator fun minus(other: kotlin.Short): kotlin.Long
-     public final operator fun minus(other: kotlin.Short): kotlin.Long
--------
+     public final inline operator fun plus(other: kotlin.Byte): kotlin.Long
-     public final operator fun plus(other: kotlin.Byte): kotlin.Long
--------
+     public final inline operator fun plus(other: kotlin.Double): kotlin.Double
-     public final operator fun plus(other: kotlin.Double): kotlin.Double
--------
+     public final inline operator fun plus(other: kotlin.Float): kotlin.Float
-     public final operator fun plus(other: kotlin.Float): kotlin.Float
--------
+     public final inline operator fun plus(other: kotlin.Int): kotlin.Long
-     public final operator fun plus(other: kotlin.Int): kotlin.Long
--------
+     public final inline operator fun plus(other: kotlin.Short): kotlin.Long
-     public final operator fun plus(other: kotlin.Short): kotlin.Long
--------
+     public final inline operator fun rem(other: kotlin.Byte): kotlin.Long
-     public final operator fun rem(other: kotlin.Byte): kotlin.Long
--------
+     public final inline operator fun rem(other: kotlin.Double): kotlin.Double
-     public final operator fun rem(other: kotlin.Double): kotlin.Double
--------
+     public final inline operator fun rem(other: kotlin.Float): kotlin.Float
-     public final operator fun rem(other: kotlin.Float): kotlin.Float
--------
+     public final inline operator fun rem(other: kotlin.Int): kotlin.Long
-     public final operator fun rem(other: kotlin.Int): kotlin.Long
--------
+     public final inline operator fun rem(other: kotlin.Short): kotlin.Long
-     public final operator fun rem(other: kotlin.Short): kotlin.Long
--------
+     public final inline operator fun times(other: kotlin.Byte): kotlin.Long
-     public final operator fun times(other: kotlin.Byte): kotlin.Long
--------
+     public final inline operator fun times(other: kotlin.Double): kotlin.Double
-     public final operator fun times(other: kotlin.Double): kotlin.Double
--------
+     public final inline operator fun times(other: kotlin.Float): kotlin.Float
-     public final operator fun times(other: kotlin.Float): kotlin.Float
--------
+     public final inline operator fun times(other: kotlin.Int): kotlin.Long
-     public final operator fun times(other: kotlin.Int): kotlin.Long
--------
+     public final inline operator fun times(other: kotlin.Short): kotlin.Long
-     public final operator fun times(other: kotlin.Short): kotlin.Long
--------
+     public open override fun toString(): kotlin.String
+ 
--------
+     public final inline operator fun unaryPlus(): kotlin.Long
-     public final operator fun unaryPlus(): kotlin.Long
--------
+     public open override operator fun equals(other: kotlin.Any?): kotlin.Boolean
+ 
+     public open override fun hashCode(): kotlin.Int
+ 
--------
+     public open override fun toString(): kotlin.String
+ 
--------
+     public open override operator fun equals(other: kotlin.Any?): kotlin.Boolean
+ 
--------
+     public open override fun hashCode(): kotlin.Int
+ 
--------
+     public open override fun toString(): kotlin.String
+ 
--------
+ @kotlin.js.JsName(name = "Error")
+ public open external class Throwable {
- public open class Throwable {
--------
+ 
+     public open override fun toString(): kotlin.String
--------
- public object Unit {
-     public open override fun toString(): kotlin.String
- }
- 