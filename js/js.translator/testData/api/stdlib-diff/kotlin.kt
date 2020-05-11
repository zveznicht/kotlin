--- 14,14 ---
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
--- 35,31 ---
+ public inline fun doubleArrayOf(/*0*/ vararg elements: kotlin.Double /*kotlin.DoubleArray*/): kotlin.DoubleArray
- @kotlin.js.library public fun doubleArrayOf(/*0*/ vararg elements: kotlin.Double /*kotlin.DoubleArray*/): kotlin.DoubleArray
- public fun doubleArrayOf(/*0*/ vararg elements: kotlin.Double /*kotlin.DoubleArray*/): kotlin.DoubleArray
--- 38,33 ---
- public inline fun </*0*/ reified @kotlin.internal.PureReifiable T> emptyArray(): kotlin.Array<T>
--- 42,36 ---
+ public inline fun floatArrayOf(/*0*/ vararg elements: kotlin.Float /*kotlin.FloatArray*/): kotlin.FloatArray
+ public inline fun intArrayOf(/*0*/ vararg elements: kotlin.Int /*kotlin.IntArray*/): kotlin.IntArray
- @kotlin.js.library public fun floatArrayOf(/*0*/ vararg elements: kotlin.Float /*kotlin.FloatArray*/): kotlin.FloatArray
- public fun floatArrayOf(/*0*/ vararg elements: kotlin.Float /*kotlin.FloatArray*/): kotlin.FloatArray
- @kotlin.js.library public fun intArrayOf(/*0*/ vararg elements: kotlin.Int /*kotlin.IntArray*/): kotlin.IntArray
- public fun intArrayOf(/*0*/ vararg elements: kotlin.Int /*kotlin.IntArray*/): kotlin.IntArray
--- 50,42 ---
+ public inline fun longArrayOf(/*0*/ vararg elements: kotlin.Long /*kotlin.LongArray*/): kotlin.LongArray
- @kotlin.js.library public fun longArrayOf(/*0*/ vararg elements: kotlin.Long /*kotlin.LongArray*/): kotlin.LongArray
- public fun longArrayOf(/*0*/ vararg elements: kotlin.Long /*kotlin.LongArray*/): kotlin.LongArray
--- 71,62 ---
+ public inline fun shortArrayOf(/*0*/ vararg elements: kotlin.Short /*kotlin.ShortArray*/): kotlin.ShortArray
- @kotlin.js.library public fun shortArrayOf(/*0*/ vararg elements: kotlin.Short /*kotlin.ShortArray*/): kotlin.ShortArray
- public fun shortArrayOf(/*0*/ vararg elements: kotlin.Short /*kotlin.ShortArray*/): kotlin.ShortArray
--- 201,191 ---
+ @kotlin.SinceKotlin(version = "1.2") public fun kotlin.Double.toBits(): kotlin.Long
+ @kotlin.SinceKotlin(version = "1.2") public fun kotlin.Float.toBits(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.2") @kotlin.js.library(name = "doubleToBits") public fun kotlin.Double.toBits(): kotlin.Long
- @kotlin.SinceKotlin(version = "1.2") @kotlin.js.library(name = "floatToBits") public fun kotlin.Float.toBits(): kotlin.Int
--- 205,195 ---
+ @kotlin.SinceKotlin(version = "1.2") public fun kotlin.Double.toRawBits(): kotlin.Long
+ @kotlin.SinceKotlin(version = "1.2") public fun kotlin.Float.toRawBits(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.2") @kotlin.js.library(name = "doubleToRawBits") public fun kotlin.Double.toRawBits(): kotlin.Long
- @kotlin.SinceKotlin(version = "1.2") @kotlin.js.library(name = "floatToRawBits") public fun kotlin.Float.toRawBits(): kotlin.Int
--- 232,222 ---
- public interface Annotation {
- }
- 
--- 244,231 ---
+     public constructor ArithmeticException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor ArithmeticException(/*0*/ message: kotlin.String?)
--- 260,247 ---
+     @kotlin.SinceKotlin(version = "1.4") public constructor AssertionError(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ @kotlin.SinceKotlin(version = "1.4") public constructor AssertionError(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--- 266,253 ---
+     public open override /*1*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*1*/ fun hashCode(): kotlin.Int
--- 268,257 ---
+     public open override /*1*/ fun toString(): kotlin.String
--- 302,292 ---
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
--- 338,330 ---
+     public open override /*2*/ fun toString(): kotlin.String
--- 364,357 ---
+     public final val value: kotlin.Int
+         public final fun <get-value>(): kotlin.Int
--- 366,361 ---
+     public open override /*1*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*1*/ fun hashCode(): kotlin.Int
--- 378,375 ---
+     @kotlin.js.JsName(name = "toString") public open override /*1*/ fun toString(): kotlin.String
--- 420,418 ---
- public interface CharSequence {
-     public abstract val length: kotlin.Int
-         public abstract fun <get-length>(): kotlin.Int
-     public abstract operator fun get(/*0*/ index: kotlin.Int): kotlin.Char
-     public abstract fun subSequence(/*0*/ startIndex: kotlin.Int, /*1*/ endIndex: kotlin.Int): kotlin.CharSequence
- }
- 
--- 429,420 ---
+     public constructor ClassCastException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor ClassCastException(/*0*/ message: kotlin.String?)
--- 443,434 ---
+     public constructor ConcurrentModificationException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor ConcurrentModificationException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--- 483,474 ---
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
--- 515,508 ---
+     public open override /*2*/ fun toString(): kotlin.String
--- 556,550 ---
+     public open override /*1*/ fun compareTo(/*0*/ other: E): kotlin.Int
+     public open override /*1*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*1*/ fun hashCode(): kotlin.Int
-     protected final fun clone(): kotlin.Any
-     public final override /*1*/ fun compareTo(/*0*/ other: E): kotlin.Int
-     public final override /*1*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
-     public final override /*1*/ fun hashCode(): kotlin.Int
--- 569,562 ---
+     public constructor Error(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor Error(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--- 576,569 ---
+     public constructor Exception(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor Exception(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--- 626,619 ---
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
--- 658,653 ---
+     public open override /*2*/ fun toString(): kotlin.String
--- 692,688 ---
- public interface Function</*0*/ out R> {
- }
- 
--- 698,691 ---
+     public constructor IllegalArgumentException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor IllegalArgumentException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--- 705,698 ---
+     public constructor IllegalStateException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor IllegalStateException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--- 711,704 ---
+     public constructor IndexOutOfBoundsException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor IndexOutOfBoundsException(/*0*/ message: kotlin.String?)
--- 729,722 ---
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
--- 769,764 ---
+     public open override /*2*/ fun toString(): kotlin.String
--- 840,836 ---
+     public final inline operator fun compareTo(/*0*/ other: kotlin.Byte): kotlin.Int
+     public final inline operator fun compareTo(/*0*/ other: kotlin.Double): kotlin.Int
+     public final inline operator fun compareTo(/*0*/ other: kotlin.Float): kotlin.Int
+     public final inline operator fun compareTo(/*0*/ other: kotlin.Int): kotlin.Int
-     public final operator fun compareTo(/*0*/ other: kotlin.Byte): kotlin.Int
-     public final operator fun compareTo(/*0*/ other: kotlin.Double): kotlin.Int
-     public final operator fun compareTo(/*0*/ other: kotlin.Float): kotlin.Int
-     public final operator fun compareTo(/*0*/ other: kotlin.Int): kotlin.Int
--- 845,841 ---
+     public final inline operator fun compareTo(/*0*/ other: kotlin.Short): kotlin.Int
-     public final operator fun compareTo(/*0*/ other: kotlin.Short): kotlin.Int
--- 847,843 ---
+     public final inline operator fun div(/*0*/ other: kotlin.Byte): kotlin.Long
+     public final inline operator fun div(/*0*/ other: kotlin.Double): kotlin.Double
+     public final inline operator fun div(/*0*/ other: kotlin.Float): kotlin.Float
+     public final inline operator fun div(/*0*/ other: kotlin.Int): kotlin.Long
-     public final operator fun div(/*0*/ other: kotlin.Byte): kotlin.Long
-     public final operator fun div(/*0*/ other: kotlin.Double): kotlin.Double
-     public final operator fun div(/*0*/ other: kotlin.Float): kotlin.Float
-     public final operator fun div(/*0*/ other: kotlin.Int): kotlin.Long
--- 852,848 ---
+     public final inline operator fun div(/*0*/ other: kotlin.Short): kotlin.Long
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
-     public final operator fun div(/*0*/ other: kotlin.Short): kotlin.Long
--- 855,853 ---
+     public final inline operator fun minus(/*0*/ other: kotlin.Byte): kotlin.Long
+     public final inline operator fun minus(/*0*/ other: kotlin.Double): kotlin.Double
+     public final inline operator fun minus(/*0*/ other: kotlin.Float): kotlin.Float
+     public final inline operator fun minus(/*0*/ other: kotlin.Int): kotlin.Long
-     public final operator fun minus(/*0*/ other: kotlin.Byte): kotlin.Long
-     public final operator fun minus(/*0*/ other: kotlin.Double): kotlin.Double
-     public final operator fun minus(/*0*/ other: kotlin.Float): kotlin.Float
-     public final operator fun minus(/*0*/ other: kotlin.Int): kotlin.Long
--- 860,858 ---
+     public final inline operator fun minus(/*0*/ other: kotlin.Short): kotlin.Long
-     public final operator fun minus(/*0*/ other: kotlin.Short): kotlin.Long
--- 862,860 ---
+     public final inline operator fun plus(/*0*/ other: kotlin.Byte): kotlin.Long
+     public final inline operator fun plus(/*0*/ other: kotlin.Double): kotlin.Double
+     public final inline operator fun plus(/*0*/ other: kotlin.Float): kotlin.Float
+     public final inline operator fun plus(/*0*/ other: kotlin.Int): kotlin.Long
-     public final operator fun plus(/*0*/ other: kotlin.Byte): kotlin.Long
-     public final operator fun plus(/*0*/ other: kotlin.Double): kotlin.Double
-     public final operator fun plus(/*0*/ other: kotlin.Float): kotlin.Float
-     public final operator fun plus(/*0*/ other: kotlin.Int): kotlin.Long
--- 867,865 ---
+     public final inline operator fun plus(/*0*/ other: kotlin.Short): kotlin.Long
-     public final operator fun plus(/*0*/ other: kotlin.Short): kotlin.Long
--- 872,870 ---
+     @kotlin.SinceKotlin(version = "1.1") public final inline operator fun rem(/*0*/ other: kotlin.Byte): kotlin.Long
+     @kotlin.SinceKotlin(version = "1.1") public final inline operator fun rem(/*0*/ other: kotlin.Double): kotlin.Double
+     @kotlin.SinceKotlin(version = "1.1") public final inline operator fun rem(/*0*/ other: kotlin.Float): kotlin.Float
+     @kotlin.SinceKotlin(version = "1.1") public final inline operator fun rem(/*0*/ other: kotlin.Int): kotlin.Long
-     @kotlin.SinceKotlin(version = "1.1") public final operator fun rem(/*0*/ other: kotlin.Byte): kotlin.Long
-     @kotlin.SinceKotlin(version = "1.1") public final operator fun rem(/*0*/ other: kotlin.Double): kotlin.Double
-     @kotlin.SinceKotlin(version = "1.1") public final operator fun rem(/*0*/ other: kotlin.Float): kotlin.Float
-     @kotlin.SinceKotlin(version = "1.1") public final operator fun rem(/*0*/ other: kotlin.Int): kotlin.Long
--- 877,875 ---
+     @kotlin.SinceKotlin(version = "1.1") public final inline operator fun rem(/*0*/ other: kotlin.Short): kotlin.Long
-     @kotlin.SinceKotlin(version = "1.1") public final operator fun rem(/*0*/ other: kotlin.Short): kotlin.Long
--- 880,878 ---
+     public final inline operator fun times(/*0*/ other: kotlin.Byte): kotlin.Long
+     public final inline operator fun times(/*0*/ other: kotlin.Double): kotlin.Double
+     public final inline operator fun times(/*0*/ other: kotlin.Float): kotlin.Float
+     public final inline operator fun times(/*0*/ other: kotlin.Int): kotlin.Long
-     public final operator fun times(/*0*/ other: kotlin.Byte): kotlin.Long
-     public final operator fun times(/*0*/ other: kotlin.Double): kotlin.Double
-     public final operator fun times(/*0*/ other: kotlin.Float): kotlin.Float
-     public final operator fun times(/*0*/ other: kotlin.Int): kotlin.Long
--- 885,883 ---
+     public final inline operator fun times(/*0*/ other: kotlin.Short): kotlin.Long
-     public final operator fun times(/*0*/ other: kotlin.Short): kotlin.Long
--- 893,891 ---
+     public open override /*2*/ fun toString(): kotlin.String
--- 894,893 ---
+     public final inline operator fun unaryPlus(): kotlin.Long
-     public final operator fun unaryPlus(): kotlin.Long
--- 922,921 ---
+     public constructor NoSuchElementException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor NoSuchElementException(/*0*/ message: kotlin.String?)
--- 928,927 ---
+     public constructor NoWhenBranchMatchedException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor NoWhenBranchMatchedException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--- 941,940 ---
+     public constructor NullPointerException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor NullPointerException(/*0*/ message: kotlin.String?)
--- 957,956 ---
+     public constructor NumberFormatException(/*0*/ message: kotlin.String?)
-     /*primary*/ public constructor NumberFormatException(/*0*/ message: kotlin.String?)
--- 1040,1039 ---
+     public constructor RuntimeException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor RuntimeException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--- 1058,1057 ---
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
+     public open override /*2*/ fun hashCode(): kotlin.Int
--- 1094,1095 ---
+     public open override /*2*/ fun toString(): kotlin.String
--- 1130,1132 ---
+     public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
--- 1131,1134 ---
+     public open override /*2*/ fun hashCode(): kotlin.Int
--- 1133,1137 ---
+     public open override /*2*/ fun toString(): kotlin.String
--- 1144,1149 ---
+ @kotlin.js.JsName(name = "Error") public open external class Throwable {
- public open class Throwable {
--- 1147,1152 ---
+     public constructor Throwable(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor Throwable(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--- 1153,1158 ---
+     public open override /*1*/ fun toString(): kotlin.String
--- 1471,1477 ---
+     public constructor UninitializedPropertyAccessException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor UninitializedPropertyAccessException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
--- 1479,1485 ---
- public object Unit {
-     public open override /*1*/ fun toString(): kotlin.String
- }
- 
--- 1490,1492 ---
+     public constructor UnsupportedOperationException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
-     /*primary*/ public constructor UnsupportedOperationException(/*0*/ message: kotlin.String?, /*1*/ cause: kotlin.Throwable?)
