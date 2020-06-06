--- 271,271 ---
+ @kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ K, /*1*/ V> kotlin.Array<out K>.associateWith(/*0*/ valueSelector: (K) -> V): kotlin.collections.Map<K, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.BooleanArray.associateWith(/*0*/ valueSelector: (kotlin.Boolean) -> V): kotlin.collections.Map<kotlin.Boolean, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.ByteArray.associateWith(/*0*/ valueSelector: (kotlin.Byte) -> V): kotlin.collections.Map<kotlin.Byte, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.CharArray.associateWith(/*0*/ valueSelector: (kotlin.Char) -> V): kotlin.collections.Map<kotlin.Char, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.DoubleArray.associateWith(/*0*/ valueSelector: (kotlin.Double) -> V): kotlin.collections.Map<kotlin.Double, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.FloatArray.associateWith(/*0*/ valueSelector: (kotlin.Float) -> V): kotlin.collections.Map<kotlin.Float, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.IntArray.associateWith(/*0*/ valueSelector: (kotlin.Int) -> V): kotlin.collections.Map<kotlin.Int, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.LongArray.associateWith(/*0*/ valueSelector: (kotlin.Long) -> V): kotlin.collections.Map<kotlin.Long, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.ShortArray.associateWith(/*0*/ valueSelector: (kotlin.Short) -> V): kotlin.collections.Map<kotlin.Short, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UByteArray.associateWith(/*0*/ valueSelector: (kotlin.UByte) -> V): kotlin.collections.Map<kotlin.UByte, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UIntArray.associateWith(/*0*/ valueSelector: (kotlin.UInt) -> V): kotlin.collections.Map<kotlin.UInt, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.ULongArray.associateWith(/*0*/ valueSelector: (kotlin.ULong) -> V): kotlin.collections.Map<kotlin.ULong, V>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UShortArray.associateWith(/*0*/ valueSelector: (kotlin.UShort) -> V): kotlin.collections.Map<kotlin.UShort, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ K, /*1*/ V> kotlin.Array<out K>.associateWith(/*0*/ valueSelector: (K) -> V): kotlin.collections.Map<K, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.BooleanArray.associateWith(/*0*/ valueSelector: (kotlin.Boolean) -> V): kotlin.collections.Map<kotlin.Boolean, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.ByteArray.associateWith(/*0*/ valueSelector: (kotlin.Byte) -> V): kotlin.collections.Map<kotlin.Byte, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.CharArray.associateWith(/*0*/ valueSelector: (kotlin.Char) -> V): kotlin.collections.Map<kotlin.Char, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.DoubleArray.associateWith(/*0*/ valueSelector: (kotlin.Double) -> V): kotlin.collections.Map<kotlin.Double, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.FloatArray.associateWith(/*0*/ valueSelector: (kotlin.Float) -> V): kotlin.collections.Map<kotlin.Float, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.IntArray.associateWith(/*0*/ valueSelector: (kotlin.Int) -> V): kotlin.collections.Map<kotlin.Int, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.LongArray.associateWith(/*0*/ valueSelector: (kotlin.Long) -> V): kotlin.collections.Map<kotlin.Long, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.ShortArray.associateWith(/*0*/ valueSelector: (kotlin.Short) -> V): kotlin.collections.Map<kotlin.Short, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UByteArray.associateWith(/*0*/ valueSelector: (kotlin.UByte) -> V): kotlin.collections.Map<kotlin.UByte, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UIntArray.associateWith(/*0*/ valueSelector: (kotlin.UInt) -> V): kotlin.collections.Map<kotlin.UInt, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.ULongArray.associateWith(/*0*/ valueSelector: (kotlin.ULong) -> V): kotlin.collections.Map<kotlin.ULong, V>
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UShortArray.associateWith(/*0*/ valueSelector: (kotlin.UShort) -> V): kotlin.collections.Map<kotlin.UShort, V>
--- 285,285 ---
+ @kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.Array<out K>.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (K) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Boolean, in V>> kotlin.BooleanArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Boolean) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Byte, in V>> kotlin.ByteArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Byte) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Char, in V>> kotlin.CharArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Char) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Double, in V>> kotlin.DoubleArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Double) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Float, in V>> kotlin.FloatArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Float) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Int, in V>> kotlin.IntArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Int) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Long, in V>> kotlin.LongArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Long) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Short, in V>> kotlin.ShortArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Short) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.UByte, in V>> kotlin.UByteArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.UByte) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.UInt, in V>> kotlin.UIntArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.UInt) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.ULong, in V>> kotlin.ULongArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.ULong) -> V): M
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.UShort, in V>> kotlin.UShortArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.UShort) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.Array<out K>.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (K) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Boolean, in V>> kotlin.BooleanArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Boolean) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Byte, in V>> kotlin.ByteArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Byte) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Char, in V>> kotlin.CharArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Char) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Double, in V>> kotlin.DoubleArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Double) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Float, in V>> kotlin.FloatArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Float) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Int, in V>> kotlin.IntArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Int) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Long, in V>> kotlin.LongArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Long) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Short, in V>> kotlin.ShortArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Short) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.UByte, in V>> kotlin.UByteArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.UByte) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.UInt, in V>> kotlin.UIntArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.UInt) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.ULong, in V>> kotlin.ULongArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.ULong) -> V): M
- @kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.UShort, in V>> kotlin.UShortArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.UShort) -> V): M
--- 410,410 ---
+ @kotlin.SinceKotlin(version = "1.4") public infix fun </*0*/ T> kotlin.Array<out T>?.contentDeepEquals(/*0*/ other: kotlin.Array<out T>?): kotlin.Boolean
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayDeepEquals") public infix fun </*0*/ T> kotlin.Array<out T>?.contentDeepEquals(/*0*/ other: kotlin.Array<out T>?): kotlin.Boolean
--- 412,412 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T> kotlin.Array<out T>?.contentDeepHashCode(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayDeepHashCode") public fun </*0*/ T> kotlin.Array<out T>?.contentDeepHashCode(): kotlin.Int
--- 414,414 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T> kotlin.Array<out T>?.contentDeepToString(): kotlin.String
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayDeepToString") public fun </*0*/ T> kotlin.Array<out T>?.contentDeepToString(): kotlin.String
--- 416,416 ---
+ @kotlin.SinceKotlin(version = "1.4") public infix fun </*0*/ T> kotlin.Array<out T>?.contentEquals(/*0*/ other: kotlin.Array<out T>?): kotlin.Boolean
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun </*0*/ T> kotlin.Array<out T>?.contentEquals(/*0*/ other: kotlin.Array<out T>?): kotlin.Boolean
--- 418,418 ---
+ @kotlin.SinceKotlin(version = "1.4") public infix fun kotlin.BooleanArray?.contentEquals(/*0*/ other: kotlin.BooleanArray?): kotlin.Boolean
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.BooleanArray?.contentEquals(/*0*/ other: kotlin.BooleanArray?): kotlin.Boolean
--- 420,420 ---
+ @kotlin.SinceKotlin(version = "1.4") public infix fun kotlin.ByteArray?.contentEquals(/*0*/ other: kotlin.ByteArray?): kotlin.Boolean
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.ByteArray?.contentEquals(/*0*/ other: kotlin.ByteArray?): kotlin.Boolean
--- 422,422 ---
+ @kotlin.SinceKotlin(version = "1.4") public infix fun kotlin.CharArray?.contentEquals(/*0*/ other: kotlin.CharArray?): kotlin.Boolean
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.CharArray?.contentEquals(/*0*/ other: kotlin.CharArray?): kotlin.Boolean
--- 424,424 ---
+ @kotlin.SinceKotlin(version = "1.4") public infix fun kotlin.DoubleArray?.contentEquals(/*0*/ other: kotlin.DoubleArray?): kotlin.Boolean
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.DoubleArray?.contentEquals(/*0*/ other: kotlin.DoubleArray?): kotlin.Boolean
--- 426,426 ---
+ @kotlin.SinceKotlin(version = "1.4") public infix fun kotlin.FloatArray?.contentEquals(/*0*/ other: kotlin.FloatArray?): kotlin.Boolean
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.FloatArray?.contentEquals(/*0*/ other: kotlin.FloatArray?): kotlin.Boolean
--- 428,428 ---
+ @kotlin.SinceKotlin(version = "1.4") public infix fun kotlin.IntArray?.contentEquals(/*0*/ other: kotlin.IntArray?): kotlin.Boolean
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.IntArray?.contentEquals(/*0*/ other: kotlin.IntArray?): kotlin.Boolean
--- 430,430 ---
+ @kotlin.SinceKotlin(version = "1.4") public infix fun kotlin.LongArray?.contentEquals(/*0*/ other: kotlin.LongArray?): kotlin.Boolean
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.LongArray?.contentEquals(/*0*/ other: kotlin.LongArray?): kotlin.Boolean
--- 432,432 ---
+ @kotlin.SinceKotlin(version = "1.4") public infix fun kotlin.ShortArray?.contentEquals(/*0*/ other: kotlin.ShortArray?): kotlin.Boolean
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.ShortArray?.contentEquals(/*0*/ other: kotlin.ShortArray?): kotlin.Boolean
--- 442,442 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T> kotlin.Array<out T>?.contentHashCode(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun </*0*/ T> kotlin.Array<out T>?.contentHashCode(): kotlin.Int
--- 444,444 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.BooleanArray?.contentHashCode(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.BooleanArray?.contentHashCode(): kotlin.Int
--- 446,446 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.ByteArray?.contentHashCode(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.ByteArray?.contentHashCode(): kotlin.Int
--- 448,448 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.CharArray?.contentHashCode(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.CharArray?.contentHashCode(): kotlin.Int
--- 450,450 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.DoubleArray?.contentHashCode(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.DoubleArray?.contentHashCode(): kotlin.Int
--- 452,452 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.FloatArray?.contentHashCode(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.FloatArray?.contentHashCode(): kotlin.Int
--- 454,454 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.IntArray?.contentHashCode(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.IntArray?.contentHashCode(): kotlin.Int
--- 456,456 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.LongArray?.contentHashCode(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.LongArray?.contentHashCode(): kotlin.Int
--- 458,458 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.ShortArray?.contentHashCode(): kotlin.Int
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.ShortArray?.contentHashCode(): kotlin.Int
--- 468,468 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T> kotlin.Array<out T>?.contentToString(): kotlin.String
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun </*0*/ T> kotlin.Array<out T>?.contentToString(): kotlin.String
--- 470,470 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.BooleanArray?.contentToString(): kotlin.String
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.BooleanArray?.contentToString(): kotlin.String
--- 472,472 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.ByteArray?.contentToString(): kotlin.String
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.ByteArray?.contentToString(): kotlin.String
--- 474,474 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.CharArray?.contentToString(): kotlin.String
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.CharArray?.contentToString(): kotlin.String
--- 476,476 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.DoubleArray?.contentToString(): kotlin.String
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.DoubleArray?.contentToString(): kotlin.String
--- 478,478 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.FloatArray?.contentToString(): kotlin.String
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.FloatArray?.contentToString(): kotlin.String
--- 480,480 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.IntArray?.contentToString(): kotlin.String
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.IntArray?.contentToString(): kotlin.String
--- 482,482 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.LongArray?.contentToString(): kotlin.String
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.LongArray?.contentToString(): kotlin.String
--- 484,484 ---
+ @kotlin.SinceKotlin(version = "1.4") public fun kotlin.ShortArray?.contentToString(): kotlin.String
- @kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.ShortArray?.contentToString(): kotlin.String
--- 1903,1903 ---
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.randomOrNull(): T?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun </*0*/ T> kotlin.Array<out T>.randomOrNull(/*0*/ random: kotlin.random.Random): T?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.randomOrNull(): kotlin.Boolean?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.BooleanArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Boolean?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.randomOrNull(): kotlin.Byte?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.ByteArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Byte?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.randomOrNull(): kotlin.Char?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.CharArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Char?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.randomOrNull(): kotlin.Double?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.DoubleArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Double?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.randomOrNull(): kotlin.Float?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.FloatArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Float?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.randomOrNull(): kotlin.Int?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.IntArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Int?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.randomOrNull(): kotlin.Long?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.LongArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Long?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.randomOrNull(): kotlin.Short?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.ShortArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Short?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.randomOrNull(): kotlin.UByte?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.UByte?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.randomOrNull(): kotlin.UInt?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.UInt?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.randomOrNull(): kotlin.ULong?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.ULong?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.randomOrNull(): kotlin.UShort?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.UShort?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Collection<T>.randomOrNull(): T?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun </*0*/ T> kotlin.collections.Collection<T>.randomOrNull(/*0*/ random: kotlin.random.Random): T?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.randomOrNull(): T?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun </*0*/ T> kotlin.Array<out T>.randomOrNull(/*0*/ random: kotlin.random.Random): T?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.randomOrNull(): kotlin.Boolean?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun kotlin.BooleanArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Boolean?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.randomOrNull(): kotlin.Byte?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun kotlin.ByteArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Byte?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.randomOrNull(): kotlin.Char?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun kotlin.CharArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Char?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.randomOrNull(): kotlin.Double?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun kotlin.DoubleArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Double?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.randomOrNull(): kotlin.Float?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun kotlin.FloatArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Float?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.randomOrNull(): kotlin.Int?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun kotlin.IntArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Int?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.randomOrNull(): kotlin.Long?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun kotlin.LongArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Long?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.randomOrNull(): kotlin.Short?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun kotlin.ShortArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Short?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.randomOrNull(): kotlin.UByte?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.UByte?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.randomOrNull(): kotlin.UInt?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.UInt?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.randomOrNull(): kotlin.ULong?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.ULong?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.randomOrNull(): kotlin.UShort?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.UShort?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Collection<T>.randomOrNull(): T?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun </*0*/ T> kotlin.collections.Collection<T>.randomOrNull(/*0*/ random: kotlin.random.Random): T?
--- 1974,1974 ---
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduceOrNull(/*0*/ operation: (acc: S, T) -> S): S?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.BooleanArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.ByteArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.Byte?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.CharArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.Char?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.DoubleArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.Double?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.FloatArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.Float?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.IntArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.Int?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.LongArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.Long?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.ShortArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.Short?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduceOrNull(/*0*/ operation: (acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.UByte?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduceOrNull(/*0*/ operation: (acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.UInt?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduceOrNull(/*0*/ operation: (acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.ULong?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduceOrNull(/*0*/ operation: (acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.UShort?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.reduceOrNull(/*0*/ operation: (acc: S, T) -> S): S?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduceOrNull(/*0*/ operation: (acc: S, T) -> S): S?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.BooleanArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.ByteArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.Byte?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.CharArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.Char?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.DoubleArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.Double?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.FloatArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.Float?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.IntArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.Int?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.LongArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.Long?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.ShortArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.Short?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduceOrNull(/*0*/ operation: (acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.UByte?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduceOrNull(/*0*/ operation: (acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.UInt?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduceOrNull(/*0*/ operation: (acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.ULong?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduceOrNull(/*0*/ operation: (acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.UShort?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.reduceOrNull(/*0*/ operation: (acc: S, T) -> S): S?
--- 2030,2030 ---
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduceRightOrNull(/*0*/ operation: (T, acc: S) -> S): S?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.BooleanArray.reduceRightOrNull(/*0*/ operation: (kotlin.Boolean, acc: kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.ByteArray.reduceRightOrNull(/*0*/ operation: (kotlin.Byte, acc: kotlin.Byte) -> kotlin.Byte): kotlin.Byte?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.CharArray.reduceRightOrNull(/*0*/ operation: (kotlin.Char, acc: kotlin.Char) -> kotlin.Char): kotlin.Char?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.DoubleArray.reduceRightOrNull(/*0*/ operation: (kotlin.Double, acc: kotlin.Double) -> kotlin.Double): kotlin.Double?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.FloatArray.reduceRightOrNull(/*0*/ operation: (kotlin.Float, acc: kotlin.Float) -> kotlin.Float): kotlin.Float?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.IntArray.reduceRightOrNull(/*0*/ operation: (kotlin.Int, acc: kotlin.Int) -> kotlin.Int): kotlin.Int?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.LongArray.reduceRightOrNull(/*0*/ operation: (kotlin.Long, acc: kotlin.Long) -> kotlin.Long): kotlin.Long?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.ShortArray.reduceRightOrNull(/*0*/ operation: (kotlin.Short, acc: kotlin.Short) -> kotlin.Short): kotlin.Short?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduceRightOrNull(/*0*/ operation: (kotlin.UByte, acc: kotlin.UByte) -> kotlin.UByte): kotlin.UByte?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduceRightOrNull(/*0*/ operation: (kotlin.UInt, acc: kotlin.UInt) -> kotlin.UInt): kotlin.UInt?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduceRightOrNull(/*0*/ operation: (kotlin.ULong, acc: kotlin.ULong) -> kotlin.ULong): kotlin.ULong?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduceRightOrNull(/*0*/ operation: (kotlin.UShort, acc: kotlin.UShort) -> kotlin.UShort): kotlin.UShort?
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.List<T>.reduceRightOrNull(/*0*/ operation: (T, acc: S) -> S): S?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduceRightOrNull(/*0*/ operation: (T, acc: S) -> S): S?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.BooleanArray.reduceRightOrNull(/*0*/ operation: (kotlin.Boolean, acc: kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.ByteArray.reduceRightOrNull(/*0*/ operation: (kotlin.Byte, acc: kotlin.Byte) -> kotlin.Byte): kotlin.Byte?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.CharArray.reduceRightOrNull(/*0*/ operation: (kotlin.Char, acc: kotlin.Char) -> kotlin.Char): kotlin.Char?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.DoubleArray.reduceRightOrNull(/*0*/ operation: (kotlin.Double, acc: kotlin.Double) -> kotlin.Double): kotlin.Double?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.FloatArray.reduceRightOrNull(/*0*/ operation: (kotlin.Float, acc: kotlin.Float) -> kotlin.Float): kotlin.Float?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.IntArray.reduceRightOrNull(/*0*/ operation: (kotlin.Int, acc: kotlin.Int) -> kotlin.Int): kotlin.Int?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.LongArray.reduceRightOrNull(/*0*/ operation: (kotlin.Long, acc: kotlin.Long) -> kotlin.Long): kotlin.Long?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun kotlin.ShortArray.reduceRightOrNull(/*0*/ operation: (kotlin.Short, acc: kotlin.Short) -> kotlin.Short): kotlin.Short?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduceRightOrNull(/*0*/ operation: (kotlin.UByte, acc: kotlin.UByte) -> kotlin.UByte): kotlin.UByte?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduceRightOrNull(/*0*/ operation: (kotlin.UInt, acc: kotlin.UInt) -> kotlin.UInt): kotlin.UInt?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduceRightOrNull(/*0*/ operation: (kotlin.ULong, acc: kotlin.ULong) -> kotlin.ULong): kotlin.ULong?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduceRightOrNull(/*0*/ operation: (kotlin.UShort, acc: kotlin.UShort) -> kotlin.UShort): kotlin.UShort?
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.List<T>.reduceRightOrNull(/*0*/ operation: (T, acc: S) -> S): S?
--- 2149,2149 ---
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.runningReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.runningReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
--- 2162,2162 ---
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.runningReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.runningReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
--- 2177,2177 ---
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, T) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Boolean) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Byte) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Char) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Double) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Float) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Int) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Long) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Short) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UByte) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UInt) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.ULong) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UShort) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, T) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, T) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Boolean) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Byte) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Char) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Double) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Float) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Int) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Long) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Short) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UByte) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UInt) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.ULong) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UShort) -> R): kotlin.collections.List<R>
+ @kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, T) -> R): kotlin.collections.List<R>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.scanReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.scanReduce(/*0*/ operation: (acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.scanReduce(/*0*/ operation: (acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.collections.List<kotlin.Byte>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.scanReduce(/*0*/ operation: (acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.collections.List<kotlin.Char>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.scanReduce(/*0*/ operation: (acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.collections.List<kotlin.Double>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.scanReduce(/*0*/ operation: (acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.collections.List<kotlin.Float>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.scanReduce(/*0*/ operation: (acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.collections.List<kotlin.Int>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.scanReduce(/*0*/ operation: (acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.collections.List<kotlin.Long>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.scanReduce(/*0*/ operation: (acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.collections.List<kotlin.Short>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.scanReduce(/*0*/ operation: (acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.collections.List<kotlin.UByte>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.scanReduce(/*0*/ operation: (acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.collections.List<kotlin.UInt>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.scanReduce(/*0*/ operation: (acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.collections.List<kotlin.ULong>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.scanReduce(/*0*/ operation: (acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.collections.List<kotlin.UShort>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.scanReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): kotlin.collections.List<S>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.collections.List<kotlin.Byte>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.collections.List<kotlin.Char>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.collections.List<kotlin.Double>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.collections.List<kotlin.Float>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.collections.List<kotlin.Int>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.collections.List<kotlin.Long>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.collections.List<kotlin.Short>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.collections.List<kotlin.UByte>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.collections.List<kotlin.UInt>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.collections.List<kotlin.ULong>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.collections.List<kotlin.UShort>
+ @kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): kotlin.collections.List<S>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, T) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Boolean) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Byte) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Char) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Double) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Float) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Int) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Long) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Short) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UByte) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UInt) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.ULong) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UShort) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, T) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, T) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Boolean) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Byte) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Char) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Double) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Float) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Int) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Long) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Short) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UByte) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UInt) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.ULong) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UShort) -> R): kotlin.collections.List<R>
- @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, T) -> R): kotlin.collections.List<R>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.scanReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.scanReduce(/*0*/ operation: (acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.scanReduce(/*0*/ operation: (acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.collections.List<kotlin.Byte>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.scanReduce(/*0*/ operation: (acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.collections.List<kotlin.Char>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.scanReduce(/*0*/ operation: (acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.collections.List<kotlin.Double>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.scanReduce(/*0*/ operation: (acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.collections.List<kotlin.Float>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.scanReduce(/*0*/ operation: (acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.collections.List<kotlin.Int>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.scanReduce(/*0*/ operation: (acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.collections.List<kotlin.Long>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.scanReduce(/*0*/ operation: (acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.collections.List<kotlin.Short>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.scanReduce(/*0*/ operation: (acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.collections.List<kotlin.UByte>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.scanReduce(/*0*/ operation: (acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.collections.List<kotlin.UInt>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.scanReduce(/*0*/ operation: (acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.collections.List<kotlin.ULong>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.scanReduce(/*0*/ operation: (acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.collections.List<kotlin.UShort>
- @kotlin.Deprecated(message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.scanReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): kotlin.collections.List<S>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.collections.List<kotlin.Byte>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.collections.List<kotlin.Char>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.collections.List<kotlin.Double>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.collections.List<kotlin.Float>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.collections.List<kotlin.Int>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.collections.List<kotlin.Long>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.collections.List<kotlin.Short>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.collections.List<kotlin.UByte>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.collections.List<kotlin.UInt>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.collections.List<kotlin.ULong>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.collections.List<kotlin.UShort>
- @kotlin.Deprecated(message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): kotlin.collections.List<S>
--- 2380,2380 ---
+ public fun kotlin.ByteArray.sort(): kotlin.Unit
- @kotlin.js.library(name = "primitiveArraySort") public fun kotlin.ByteArray.sort(): kotlin.Unit
--- 2383,2383 ---
+ public fun kotlin.CharArray.sort(): kotlin.Unit
- @kotlin.js.library(name = "primitiveArraySort") public fun kotlin.CharArray.sort(): kotlin.Unit
--- 2386,2386 ---
+ public fun kotlin.DoubleArray.sort(): kotlin.Unit
- @kotlin.js.library(name = "primitiveArraySort") public fun kotlin.DoubleArray.sort(): kotlin.Unit
--- 2389,2389 ---
+ public fun kotlin.FloatArray.sort(): kotlin.Unit
- @kotlin.js.library(name = "primitiveArraySort") public fun kotlin.FloatArray.sort(): kotlin.Unit
--- 2392,2392 ---
+ public fun kotlin.IntArray.sort(): kotlin.Unit
- @kotlin.js.library(name = "primitiveArraySort") public fun kotlin.IntArray.sort(): kotlin.Unit
--- 2398,2398 ---
+ public fun kotlin.ShortArray.sort(): kotlin.Unit
- @kotlin.js.library(name = "primitiveArraySort") public fun kotlin.ShortArray.sort(): kotlin.Unit
--- 3126,3126 ---
- public abstract class BooleanIterator : kotlin.collections.Iterator<kotlin.Boolean> {
-     /*primary*/ public constructor BooleanIterator()
-     public final override /*1*/ fun next(): kotlin.Boolean
-     public abstract fun nextBoolean(): kotlin.Boolean
- }
- 
--- 3138,3132 ---
- public abstract class ByteIterator : kotlin.collections.Iterator<kotlin.Byte> {
-     /*primary*/ public constructor ByteIterator()
-     public final override /*1*/ fun next(): kotlin.Byte
-     public abstract fun nextByte(): kotlin.Byte
- }
- 
--- 3150,3138 ---
- public abstract class CharIterator : kotlin.collections.Iterator<kotlin.Char> {
-     /*primary*/ public constructor CharIterator()
-     public final override /*1*/ fun next(): kotlin.Char
-     public abstract fun nextChar(): kotlin.Char
- }
- 
--- 3165,3147 ---
- public interface Collection</*0*/ out E> : kotlin.collections.Iterable<E> {
-     public abstract val size: kotlin.Int
-         public abstract fun <get-size>(): kotlin.Int
-     public abstract operator fun contains(/*0*/ element: E): kotlin.Boolean
-     public abstract fun containsAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
-     public abstract fun isEmpty(): kotlin.Boolean
-     public abstract override /*1*/ fun iterator(): kotlin.collections.Iterator<E>
- }
- 
--- 3180,3153 ---
- public abstract class DoubleIterator : kotlin.collections.Iterator<kotlin.Double> {
-     /*primary*/ public constructor DoubleIterator()
-     public final override /*1*/ fun next(): kotlin.Double
-     public abstract fun nextDouble(): kotlin.Double
- }
- 
--- 3192,3159 ---
- public abstract class FloatIterator : kotlin.collections.Iterator<kotlin.Float> {
-     /*primary*/ public constructor FloatIterator()
-     public final override /*1*/ fun next(): kotlin.Float
-     public abstract fun nextFloat(): kotlin.Float
- }
- 
--- 3256,3217 ---
- public abstract class IntIterator : kotlin.collections.Iterator<kotlin.Int> {
-     /*primary*/ public constructor IntIterator()
-     public final override /*1*/ fun next(): kotlin.Int
-     public abstract fun nextInt(): kotlin.Int
- }
- 
--- 3266,3221 ---
- public interface Iterable</*0*/ out T> {
-     public abstract operator fun iterator(): kotlin.collections.Iterator<T>
- }
- 
--- 3275,3226 ---
- public interface Iterator</*0*/ out T> {
-     public abstract operator fun hasNext(): kotlin.Boolean
-     public abstract operator fun next(): T
- }
- 
--- 3318,3264 ---
- public interface List</*0*/ out E> : kotlin.collections.Collection<E> {
-     public abstract override /*1*/ val size: kotlin.Int
-         public abstract override /*1*/ fun <get-size>(): kotlin.Int
-     public abstract override /*1*/ fun contains(/*0*/ element: E): kotlin.Boolean
-     public abstract override /*1*/ fun containsAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
-     public abstract operator fun get(/*0*/ index: kotlin.Int): E
-     public abstract fun indexOf(/*0*/ element: E): kotlin.Int
-     public abstract override /*1*/ fun isEmpty(): kotlin.Boolean
-     public abstract override /*1*/ fun iterator(): kotlin.collections.Iterator<E>
-     public abstract fun lastIndexOf(/*0*/ element: E): kotlin.Int
-     public abstract fun listIterator(): kotlin.collections.ListIterator<E>
-     public abstract fun listIterator(/*0*/ index: kotlin.Int): kotlin.collections.ListIterator<E>
-     public abstract fun subList(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.collections.List<E>
- }
- 
--- 3342,3273 ---
- public interface ListIterator</*0*/ out T> : kotlin.collections.Iterator<T> {
-     public abstract override /*1*/ fun hasNext(): kotlin.Boolean
-     public abstract fun hasPrevious(): kotlin.Boolean
-     public abstract override /*1*/ fun next(): T
-     public abstract fun nextIndex(): kotlin.Int
-     public abstract fun previous(): T
-     public abstract fun previousIndex(): kotlin.Int
- }
- 
--- 3357,3279 ---
- public abstract class LongIterator : kotlin.collections.Iterator<kotlin.Long> {
-     /*primary*/ public constructor LongIterator()
-     public final override /*1*/ fun next(): kotlin.Long
-     public abstract fun nextLong(): kotlin.Long
- }
- 
--- 3385,3301 ---
- public interface Map</*0*/ K, /*1*/ out V> {
-     public abstract val entries: kotlin.collections.Set<kotlin.collections.Map.Entry<K, V>>
-         public abstract fun <get-entries>(): kotlin.collections.Set<kotlin.collections.Map.Entry<K, V>>
-     public abstract val keys: kotlin.collections.Set<K>
-         public abstract fun <get-keys>(): kotlin.collections.Set<K>
-     public abstract val size: kotlin.Int
-         public abstract fun <get-size>(): kotlin.Int
-     public abstract val values: kotlin.collections.Collection<V>
-         public abstract fun <get-values>(): kotlin.collections.Collection<V>
-     public abstract fun containsKey(/*0*/ key: K): kotlin.Boolean
-     public abstract fun containsValue(/*0*/ value: V): kotlin.Boolean
-     public abstract operator fun get(/*0*/ key: K): V?
-     public abstract fun isEmpty(): kotlin.Boolean
- 
-     public interface Entry</*0*/ out K, /*1*/ out V> {
-         public abstract val key: K
-             public abstract fun <get-key>(): K
-         public abstract val value: V
-             public abstract fun <get-value>(): V
-     }
- }
- 
--- 3417,3311 ---
- public interface MutableCollection</*0*/ E> : kotlin.collections.Collection<E>, kotlin.collections.MutableIterable<E> {
-     public abstract fun add(/*0*/ element: E): kotlin.Boolean
-     public abstract fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
-     public abstract fun clear(): kotlin.Unit
-     public abstract override /*2*/ fun iterator(): kotlin.collections.MutableIterator<E>
-     public abstract fun remove(/*0*/ element: E): kotlin.Boolean
-     public abstract fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
-     public abstract fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
- }
- 
--- 3431,3315 ---
- public interface MutableIterable</*0*/ out T> : kotlin.collections.Iterable<T> {
-     public abstract override /*1*/ fun iterator(): kotlin.collections.MutableIterator<T>
- }
- 
--- 3439,3319 ---
- public interface MutableIterator</*0*/ out T> : kotlin.collections.Iterator<T> {
-     public abstract fun remove(): kotlin.Unit
- }
- 
--- 3459,3335 ---
- public interface MutableList</*0*/ E> : kotlin.collections.List<E>, kotlin.collections.MutableCollection<E> {
-     public abstract override /*1*/ fun add(/*0*/ element: E): kotlin.Boolean
-     public abstract fun add(/*0*/ index: kotlin.Int, /*1*/ element: E): kotlin.Unit
-     public abstract fun addAll(/*0*/ index: kotlin.Int, /*1*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
-     public abstract override /*1*/ fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
-     public abstract override /*1*/ fun clear(): kotlin.Unit
-     public abstract override /*1*/ fun listIterator(): kotlin.collections.MutableListIterator<E>
-     public abstract override /*1*/ fun listIterator(/*0*/ index: kotlin.Int): kotlin.collections.MutableListIterator<E>
-     public abstract override /*1*/ fun remove(/*0*/ element: E): kotlin.Boolean
-     public abstract override /*1*/ fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
-     public abstract fun removeAt(/*0*/ index: kotlin.Int): E
-     public abstract override /*1*/ fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
-     public abstract operator fun set(/*0*/ index: kotlin.Int, /*1*/ element: E): E
-     public abstract override /*1*/ fun subList(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.collections.MutableList<E>
- }
- 
--- 3483,3343 ---
- public interface MutableListIterator</*0*/ T> : kotlin.collections.ListIterator<T>, kotlin.collections.MutableIterator<T> {
-     public abstract fun add(/*0*/ element: T): kotlin.Unit
-     public abstract override /*2*/ fun hasNext(): kotlin.Boolean
-     public abstract override /*2*/ fun next(): T
-     public abstract override /*1*/ fun remove(): kotlin.Unit
-     public abstract fun set(/*0*/ element: T): kotlin.Unit
- }
- 
--- 3508,3360 ---
- public interface MutableMap</*0*/ K, /*1*/ V> : kotlin.collections.Map<K, V> {
-     public abstract override /*1*/ val entries: kotlin.collections.MutableSet<kotlin.collections.MutableMap.MutableEntry<K, V>>
-         public abstract override /*1*/ fun <get-entries>(): kotlin.collections.MutableSet<kotlin.collections.MutableMap.MutableEntry<K, V>>
-     public abstract override /*1*/ val keys: kotlin.collections.MutableSet<K>
-         public abstract override /*1*/ fun <get-keys>(): kotlin.collections.MutableSet<K>
-     public abstract override /*1*/ val values: kotlin.collections.MutableCollection<V>
-         public abstract override /*1*/ fun <get-values>(): kotlin.collections.MutableCollection<V>
-     public abstract fun clear(): kotlin.Unit
-     public abstract fun put(/*0*/ key: K, /*1*/ value: V): V?
-     public abstract fun putAll(/*0*/ from: kotlin.collections.Map<out K, V>): kotlin.Unit
-     public abstract fun remove(/*0*/ key: K): V?
- 
-     public interface MutableEntry</*0*/ K, /*1*/ V> : kotlin.collections.Map.Entry<K, V> {
-         public abstract fun setValue(/*0*/ newValue: V): V
-     }
- }
- 
--- 3535,3370 ---
- public interface MutableSet</*0*/ E> : kotlin.collections.Set<E>, kotlin.collections.MutableCollection<E> {
-     public abstract override /*1*/ fun add(/*0*/ element: E): kotlin.Boolean
-     public abstract override /*1*/ fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
-     public abstract override /*1*/ fun clear(): kotlin.Unit
-     public abstract override /*2*/ fun iterator(): kotlin.collections.MutableIterator<E>
-     public abstract override /*1*/ fun remove(/*0*/ element: E): kotlin.Boolean
-     public abstract override /*1*/ fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
-     public abstract override /*1*/ fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
- }
- 
--- 3557,3382 ---
- public interface Set</*0*/ out E> : kotlin.collections.Collection<E> {
-     public abstract override /*1*/ val size: kotlin.Int
-         public abstract override /*1*/ fun <get-size>(): kotlin.Int
-     public abstract override /*1*/ fun contains(/*0*/ element: E): kotlin.Boolean
-     public abstract override /*1*/ fun containsAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
-     public abstract override /*1*/ fun isEmpty(): kotlin.Boolean
-     public abstract override /*1*/ fun iterator(): kotlin.collections.Iterator<E>
- }
- 
--- 3572,3388 ---
- public abstract class ShortIterator : kotlin.collections.Iterator<kotlin.Short> {
-     /*primary*/ public constructor ShortIterator()
-     public final override /*1*/ fun next(): kotlin.Short
-     public abstract fun nextShort(): kotlin.Short
- }
- 
