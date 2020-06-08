--------
+ public inline fun </*0*/ T> arrayOf(/*0*/ vararg elements: T /*kotlin.Array<out T>*/): kotlin.Array<T>
+ public inline fun </*0*/ reified T> arrayOfNulls(/*0*/ size: kotlin.Int): kotlin.Array<T?>
+ public inline fun booleanArrayOf(/*0*/ vararg elements: kotlin.Boolean /*kotlin.BooleanArray*/): kotlin.BooleanArray
+ public inline fun byteArrayOf(/*0*/ vararg elements: kotlin.Byte /*kotlin.ByteArray*/): kotlin.ByteArray
+ public inline fun charArrayOf(/*0*/ vararg elements: kotlin.Char /*kotlin.CharArray*/): kotlin.CharArray
- @kotlin.js.library public fun </*0*/ T> arrayOf(/*0*/ vararg elements: T /*kotlin.Array<out T>*/): kotlin.Array<T>
- public inline fun </*0*/ reified @kotlin.internal.PureReifiable T> arrayOf(/*0*/ vararg elements: T /*kotlin.Array<out T>*/): kotlin.Array<T>
- public fun </*0*/ reified @kotlin.internal.PureReifiable T> arrayOfNulls(/*0*/ size: kotlin.Int): kotlin.Array<T?>
- @kotlin.js.library public fun booleanArrayOf(/*0*/ vararg elements: kotlin.Boolean /*kotlin.BooleanArray*/): kotlin.BooleanArray
- public fun booleanArrayOf(/*0*/ vararg elements: kotlin.Boolean /*kotlin.BooleanArray*/): kotlin.BooleanArray
- @kotlin.js.library public fun byteArrayOf(/*0*/ vararg elements: kotlin.Byte /*kotlin.ByteArray*/): kotlin.ByteArray
- public fun byteArrayOf(/*0*/ vararg elements: kotlin.Byte /*kotlin.ByteArray*/): kotlin.ByteArray
- @kotlin.js.library public fun charArrayOf(/*0*/ vararg elements: kotlin.Char /*kotlin.CharArray*/): kotlin.CharArray
- public fun charArrayOf(/*0*/ vararg elements: kotlin.Char /*kotlin.CharArray*/): kotlin.CharArray
--------
+ public inline fun doubleArrayOf(/*0*/ vararg elements: kotlin.Double /*kotlin.DoubleArray*/): kotlin.DoubleArray
- @kotlin.js.library public fun doubleArrayOf(/*0*/ vararg elements: kotlin.Double /*kotlin.DoubleArray*/): kotlin.DoubleArray
- public fun doubleArrayOf(/*0*/ vararg elements: kotlin.Double /*kotlin.DoubleArray*/): kotlin.DoubleArray
--------
- public inline fun </*0*/ reified @kotlin.internal.PureReifiable T> emptyArray(): kotlin.Array<T>
--------
+ public inline fun floatArrayOf(/*0*/ vararg elements: kotlin.Float /*kotlin.FloatArray*/): kotlin.FloatArray
+ public inline fun intArrayOf(/*0*/ vararg elements: kotlin.Int /*kotlin.IntArray*/): kotlin.IntArray
- @kotlin.js.library public fun floatArrayOf(/*0*/ vararg elements: kotlin.Float /*kotlin.FloatArray*/): kotlin.FloatArray
- public fun floatArrayOf(/*0*/ vararg elements: kotlin.Float /*kotlin.FloatArray*/): kotlin.FloatArray
- @kotlin.js.library public fun intArrayOf(/*0*/ vararg elements: kotlin.Int /*kotlin.IntArray*/): kotlin.IntArray
- public fun intArrayOf(/*0*/ vararg elements: kotlin.Int /*kotlin.IntArray*/): kotlin.IntArray
--------
+ public inline fun longArrayOf(/*0*/ vararg elements: kotlin.Long /*kotlin.LongArray*/): kotlin.LongArray
- @kotlin.js.library public fun longArrayOf(/*0*/ vararg elements: kotlin.Long /*kotlin.LongArray*/): kotlin.LongArray
- public fun longArrayOf(/*0*/ vararg elements: kotlin.Long /*kotlin.LongArray*/): kotlin.LongArray
--------
+ public inline fun shortArrayOf(/*0*/ vararg elements: kotlin.Short /*kotlin.ShortArray*/): kotlin.ShortArray
- @kotlin.js.library public fun shortArrayOf(/*0*/ vararg elements: kotlin.Short /*kotlin.ShortArray*/): kotlin.ShortArray
- public fun shortArrayOf(/*0*/ vararg elements: kotlin.Short /*kotlin.ShortArray*/): kotlin.ShortArray
--------
+ @kotlin.SinceKotlin(version = "1.2") public fun kotlin.Double.toBits(): kotlin.Long
+ @kotlin.SinceKotlin(version = "1.2") public fun kotlin.Float.toBits(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.2") @kotlin.js.library(name = "doubleToBits") public fun kotlin.Double.toBits(): kotlin.Long
- @kotlin.SinceKotlin(version = "1.2") @kotlin.js.library(name = "floatToBits") public fun kotlin.Float.toBits(): kotlin.Int
--------
+ @kotlin.SinceKotlin(version = "1.2") public fun kotlin.Double.toRawBits(): kotlin.Long
+ @kotlin.SinceKotlin(version = "1.2") public fun kotlin.Float.toRawBits(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.2") @kotlin.js.library(name = "doubleToRawBits") public fun kotlin.Double.toRawBits(): kotlin.Long
- @kotlin.SinceKotlin(version = "1.2") @kotlin.js.library(name = "floatToRawBits") public fun kotlin.Float.toRawBits(): kotlin.Int
--------
- public interface Annotation {
- }
- 
--------
+     public constructor ArithmeticException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor ArithmeticException(/*0*/ message: kotlin.String?)
--------
+     @kotlin.SinceKotlin(version = "1.4") public constructor AssertionError(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ @kotlin.SinceKotlin(version = "1.4") public constructor AssertionError(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--------
+     public open override /*1*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*1*/ fun hashCode(): kotlin.Int
--------
+     public open override /*1*/ fun toString(): kotlin.String
--------
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
--------
+     public open override /*2*/ fun toString(): kotlin.String
--------
+     public open override /*1*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*1*/ fun hashCode(): kotlin.Int
--------
+     @kotlin.js.JsName(name = "toString") public open override /*1*/ fun toString(): kotlin.String
--------
- public interface CharSequence {
-     public abstract val length: kotlin.Int
-         public abstract fun <get-length>(): kotlin.Int
-     public abstract operator fun get(/*0*/ index: kotlin.Int): kotlin.Char
-     public abstract fun subSequence(/*0*/ startIndex: kotlin.Int, /*1*/ endIndex: kotlin.Int): kotlin.CharSequence
- }
- 
--------
+     public constructor ClassCastException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor ClassCastException(/*0*/ message: kotlin.String?)
--------
+     public constructor ConcurrentModificationException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor ConcurrentModificationException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--------
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
--------
+     public open override /*2*/ fun toString(): kotlin.String
--------
+     public open override /*1*/ fun compareTo(/*0*/ other: E): kotlin.Int
+     public open override /*1*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*1*/ fun hashCode(): kotlin.Int
-     protected final fun clone(): kotlin.Any
-     public final override /*1*/ fun compareTo(/*0*/ other: E): kotlin.Int
-     public final override /*1*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
-     public final override /*1*/ fun hashCode(): kotlin.Int
--------
+     public constructor Error(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor Error(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--------
+     public constructor Exception(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor Exception(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--------
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
--------
+     public open override /*2*/ fun toString(): kotlin.String
--------
- public interface Function</*0*/ out R> {
- }
- 
--------
+     public constructor IllegalArgumentException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor IllegalArgumentException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--------
+     public constructor IllegalStateException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor IllegalStateException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--------
+     public constructor IndexOutOfBoundsException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor IndexOutOfBoundsException(/*0*/ message: kotlin.String?)
--------
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
--------
+     public open override /*2*/ fun toString(): kotlin.String
--------
+     public final inline operator fun compareTo(/*0*/ other: kotlin.Byte): kotlin.Int
+     public final inline operator fun compareTo(/*0*/ other: kotlin.Double): kotlin.Int
+     public final inline operator fun compareTo(/*0*/ other: kotlin.Float): kotlin.Int
+     public final inline operator fun compareTo(/*0*/ other: kotlin.Int): kotlin.Int
-     public final operator fun compareTo(/*0*/ other: kotlin.Byte): kotlin.Int
-     public final operator fun compareTo(/*0*/ other: kotlin.Double): kotlin.Int
-     public final operator fun compareTo(/*0*/ other: kotlin.Float): kotlin.Int
-     public final operator fun compareTo(/*0*/ other: kotlin.Int): kotlin.Int
--------
+     public final inline operator fun compareTo(/*0*/ other: kotlin.Short): kotlin.Int
-     public final operator fun compareTo(/*0*/ other: kotlin.Short): kotlin.Int
--------
+     public final inline operator fun div(/*0*/ other: kotlin.Byte): kotlin.Long
+     public final inline operator fun div(/*0*/ other: kotlin.Double): kotlin.Double
+     public final inline operator fun div(/*0*/ other: kotlin.Float): kotlin.Float
+     public final inline operator fun div(/*0*/ other: kotlin.Int): kotlin.Long
-     public final operator fun div(/*0*/ other: kotlin.Byte): kotlin.Long
-     public final operator fun div(/*0*/ other: kotlin.Double): kotlin.Double
-     public final operator fun div(/*0*/ other: kotlin.Float): kotlin.Float
-     public final operator fun div(/*0*/ other: kotlin.Int): kotlin.Long
--------
+     public final inline operator fun div(/*0*/ other: kotlin.Short): kotlin.Long
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
-     public final operator fun div(/*0*/ other: kotlin.Short): kotlin.Long
--------
+     public final inline operator fun minus(/*0*/ other: kotlin.Byte): kotlin.Long
+     public final inline operator fun minus(/*0*/ other: kotlin.Double): kotlin.Double
+     public final inline operator fun minus(/*0*/ other: kotlin.Float): kotlin.Float
+     public final inline operator fun minus(/*0*/ other: kotlin.Int): kotlin.Long
-     public final operator fun minus(/*0*/ other: kotlin.Byte): kotlin.Long
-     public final operator fun minus(/*0*/ other: kotlin.Double): kotlin.Double
-     public final operator fun minus(/*0*/ other: kotlin.Float): kotlin.Float
-     public final operator fun minus(/*0*/ other: kotlin.Int): kotlin.Long
--------
+     public final inline operator fun minus(/*0*/ other: kotlin.Short): kotlin.Long
-     public final operator fun minus(/*0*/ other: kotlin.Short): kotlin.Long
--------
+     public final inline operator fun plus(/*0*/ other: kotlin.Byte): kotlin.Long
+     public final inline operator fun plus(/*0*/ other: kotlin.Double): kotlin.Double
+     public final inline operator fun plus(/*0*/ other: kotlin.Float): kotlin.Float
+     public final inline operator fun plus(/*0*/ other: kotlin.Int): kotlin.Long
-     public final operator fun plus(/*0*/ other: kotlin.Byte): kotlin.Long
-     public final operator fun plus(/*0*/ other: kotlin.Double): kotlin.Double
-     public final operator fun plus(/*0*/ other: kotlin.Float): kotlin.Float
-     public final operator fun plus(/*0*/ other: kotlin.Int): kotlin.Long
--------
+     public final inline operator fun plus(/*0*/ other: kotlin.Short): kotlin.Long
-     public final operator fun plus(/*0*/ other: kotlin.Short): kotlin.Long
--------
+     @kotlin.SinceKotlin(version = "1.1") public final inline operator fun rem(/*0*/ other: kotlin.Byte): kotlin.Long
+     @kotlin.SinceKotlin(version = "1.1") public final inline operator fun rem(/*0*/ other: kotlin.Double): kotlin.Double
+     @kotlin.SinceKotlin(version = "1.1") public final inline operator fun rem(/*0*/ other: kotlin.Float): kotlin.Float
+     @kotlin.SinceKotlin(version = "1.1") public final inline operator fun rem(/*0*/ other: kotlin.Int): kotlin.Long
-     @kotlin.SinceKotlin(version = "1.1") public final operator fun rem(/*0*/ other: kotlin.Byte): kotlin.Long
-     @kotlin.SinceKotlin(version = "1.1") public final operator fun rem(/*0*/ other: kotlin.Double): kotlin.Double
-     @kotlin.SinceKotlin(version = "1.1") public final operator fun rem(/*0*/ other: kotlin.Float): kotlin.Float
-     @kotlin.SinceKotlin(version = "1.1") public final operator fun rem(/*0*/ other: kotlin.Int): kotlin.Long
--------
+     @kotlin.SinceKotlin(version = "1.1") public final inline operator fun rem(/*0*/ other: kotlin.Short): kotlin.Long
-     @kotlin.SinceKotlin(version = "1.1") public final operator fun rem(/*0*/ other: kotlin.Short): kotlin.Long
--------
+     public final inline operator fun times(/*0*/ other: kotlin.Byte): kotlin.Long
+     public final inline operator fun times(/*0*/ other: kotlin.Double): kotlin.Double
+     public final inline operator fun times(/*0*/ other: kotlin.Float): kotlin.Float
+     public final inline operator fun times(/*0*/ other: kotlin.Int): kotlin.Long
-     public final operator fun times(/*0*/ other: kotlin.Byte): kotlin.Long
-     public final operator fun times(/*0*/ other: kotlin.Double): kotlin.Double
-     public final operator fun times(/*0*/ other: kotlin.Float): kotlin.Float
-     public final operator fun times(/*0*/ other: kotlin.Int): kotlin.Long
--------
+     public final inline operator fun times(/*0*/ other: kotlin.Short): kotlin.Long
-     public final operator fun times(/*0*/ other: kotlin.Short): kotlin.Long
--------
+     public open override /*2*/ fun toString(): kotlin.String
--------
+     public final inline operator fun unaryPlus(): kotlin.Long
-     public final operator fun unaryPlus(): kotlin.Long
--------
+     public constructor NoSuchElementException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor NoSuchElementException(/*0*/ message: kotlin.String?)
--------
+     public constructor NoWhenBranchMatchedException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor NoWhenBranchMatchedException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--------
+     public constructor NullPointerException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor NullPointerException(/*0*/ message: kotlin.String?)
--------
+     public constructor NumberFormatException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor NumberFormatException(/*0*/ message: kotlin.String?)
--------
+     public constructor RuntimeException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor RuntimeException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--------
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
--------
+     public open override /*2*/ fun toString(): kotlin.String
--------
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
--------
+     public open override /*2*/ fun hashCode(): kotlin.Int
--------
+     public open override /*2*/ fun toString(): kotlin.String
--------
+ @kotlin.js.JsName(name = "Error") public open external class Throwable {
- public open class Throwable {
--------
+     public constructor Throwable(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor Throwable(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--------
+     public open override /*1*/ fun toString(): kotlin.String
--------
+     public constructor UninitializedPropertyAccessException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor UninitializedPropertyAccessException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--------
- public object Unit {
-     public open override /*1*/ fun toString(): kotlin.String
- }
- 
--------
+     public constructor UnsupportedOperationException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor UnsupportedOperationException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)