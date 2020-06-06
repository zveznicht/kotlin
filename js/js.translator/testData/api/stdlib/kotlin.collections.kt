package kotlin.collections

public val </*0*/ T> kotlin.Array<out T>.indices: kotlin.ranges.IntRange
    public fun kotlin.Array<out T>.<get-indices>(): kotlin.ranges.IntRange
public val kotlin.BooleanArray.indices: kotlin.ranges.IntRange
    public fun kotlin.BooleanArray.<get-indices>(): kotlin.ranges.IntRange
public val kotlin.ByteArray.indices: kotlin.ranges.IntRange
    public fun kotlin.ByteArray.<get-indices>(): kotlin.ranges.IntRange
public val kotlin.CharArray.indices: kotlin.ranges.IntRange
    public fun kotlin.CharArray.<get-indices>(): kotlin.ranges.IntRange
public val kotlin.DoubleArray.indices: kotlin.ranges.IntRange
    public fun kotlin.DoubleArray.<get-indices>(): kotlin.ranges.IntRange
public val kotlin.FloatArray.indices: kotlin.ranges.IntRange
    public fun kotlin.FloatArray.<get-indices>(): kotlin.ranges.IntRange
public val kotlin.IntArray.indices: kotlin.ranges.IntRange
    public fun kotlin.IntArray.<get-indices>(): kotlin.ranges.IntRange
public val kotlin.LongArray.indices: kotlin.ranges.IntRange
    public fun kotlin.LongArray.<get-indices>(): kotlin.ranges.IntRange
public val kotlin.ShortArray.indices: kotlin.ranges.IntRange
    public fun kotlin.ShortArray.<get-indices>(): kotlin.ranges.IntRange
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public val kotlin.UByteArray.indices: kotlin.ranges.IntRange
    public inline fun kotlin.UByteArray.<get-indices>(): kotlin.ranges.IntRange
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public val kotlin.UIntArray.indices: kotlin.ranges.IntRange
    public inline fun kotlin.UIntArray.<get-indices>(): kotlin.ranges.IntRange
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public val kotlin.ULongArray.indices: kotlin.ranges.IntRange
    public inline fun kotlin.ULongArray.<get-indices>(): kotlin.ranges.IntRange
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public val kotlin.UShortArray.indices: kotlin.ranges.IntRange
    public inline fun kotlin.UShortArray.<get-indices>(): kotlin.ranges.IntRange
public val kotlin.collections.Collection<*>.indices: kotlin.ranges.IntRange
    public fun kotlin.collections.Collection<*>.<get-indices>(): kotlin.ranges.IntRange
public val </*0*/ T> kotlin.Array<out T>.lastIndex: kotlin.Int
    public fun kotlin.Array<out T>.<get-lastIndex>(): kotlin.Int
public val kotlin.BooleanArray.lastIndex: kotlin.Int
    public fun kotlin.BooleanArray.<get-lastIndex>(): kotlin.Int
public val kotlin.ByteArray.lastIndex: kotlin.Int
    public fun kotlin.ByteArray.<get-lastIndex>(): kotlin.Int
public val kotlin.CharArray.lastIndex: kotlin.Int
    public fun kotlin.CharArray.<get-lastIndex>(): kotlin.Int
public val kotlin.DoubleArray.lastIndex: kotlin.Int
    public fun kotlin.DoubleArray.<get-lastIndex>(): kotlin.Int
public val kotlin.FloatArray.lastIndex: kotlin.Int
    public fun kotlin.FloatArray.<get-lastIndex>(): kotlin.Int
public val kotlin.IntArray.lastIndex: kotlin.Int
    public fun kotlin.IntArray.<get-lastIndex>(): kotlin.Int
public val kotlin.LongArray.lastIndex: kotlin.Int
    public fun kotlin.LongArray.<get-lastIndex>(): kotlin.Int
public val kotlin.ShortArray.lastIndex: kotlin.Int
    public fun kotlin.ShortArray.<get-lastIndex>(): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public val kotlin.UByteArray.lastIndex: kotlin.Int
    public inline fun kotlin.UByteArray.<get-lastIndex>(): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public val kotlin.UIntArray.lastIndex: kotlin.Int
    public inline fun kotlin.UIntArray.<get-lastIndex>(): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public val kotlin.ULongArray.lastIndex: kotlin.Int
    public inline fun kotlin.ULongArray.<get-lastIndex>(): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public val kotlin.UShortArray.lastIndex: kotlin.Int
    public inline fun kotlin.UShortArray.<get-lastIndex>(): kotlin.Int
public val </*0*/ T> kotlin.collections.List<T>.lastIndex: kotlin.Int
    public fun kotlin.collections.List<T>.<get-lastIndex>(): kotlin.Int
@kotlin.internal.InlineOnly public inline fun </*0*/ T> Iterable(/*0*/ crossinline iterator: () -> kotlin.collections.Iterator<T>): kotlin.collections.Iterable<T>
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline fun </*0*/ T> List(/*0*/ size: kotlin.Int, /*1*/ init: (index: kotlin.Int) -> T): kotlin.collections.List<T>
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline fun </*0*/ T> MutableList(/*0*/ size: kotlin.Int, /*1*/ init: (index: kotlin.Int) -> T): kotlin.collections.MutableList<T>
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline fun </*0*/ T> arrayListOf(): kotlin.collections.ArrayList<T>
public fun </*0*/ T> arrayListOf(/*0*/ vararg elements: T /*kotlin.Array<out T>*/): kotlin.collections.ArrayList<T>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ E> buildList(/*0*/ capacity: kotlin.Int, /*1*/ @kotlin.BuilderInference builderAction: kotlin.collections.MutableList<E>.() -> kotlin.Unit): kotlin.collections.List<E>
    CallsInPlace(builderAction, EXACTLY_ONCE)

@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ E> buildList(/*0*/ @kotlin.BuilderInference builderAction: kotlin.collections.MutableList<E>.() -> kotlin.Unit): kotlin.collections.List<E>
    CallsInPlace(builderAction, EXACTLY_ONCE)

@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> buildMap(/*0*/ capacity: kotlin.Int, /*1*/ @kotlin.BuilderInference builderAction: kotlin.collections.MutableMap<K, V>.() -> kotlin.Unit): kotlin.collections.Map<K, V>
    CallsInPlace(builderAction, EXACTLY_ONCE)

@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> buildMap(/*0*/ @kotlin.BuilderInference builderAction: kotlin.collections.MutableMap<K, V>.() -> kotlin.Unit): kotlin.collections.Map<K, V>
    CallsInPlace(builderAction, EXACTLY_ONCE)

@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ E> buildSet(/*0*/ capacity: kotlin.Int, /*1*/ @kotlin.BuilderInference builderAction: kotlin.collections.MutableSet<E>.() -> kotlin.Unit): kotlin.collections.Set<E>
    CallsInPlace(builderAction, EXACTLY_ONCE)

@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun </*0*/ E> buildSet(/*0*/ @kotlin.BuilderInference builderAction: kotlin.collections.MutableSet<E>.() -> kotlin.Unit): kotlin.collections.Set<E>
    CallsInPlace(builderAction, EXACTLY_ONCE)

public fun </*0*/ T> emptyList(): kotlin.collections.List<T>
public fun </*0*/ K, /*1*/ V> emptyMap(): kotlin.collections.Map<K, V>
public fun </*0*/ T> emptySet(): kotlin.collections.Set<T>
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> hashMapOf(): kotlin.collections.HashMap<K, V>
public fun </*0*/ K, /*1*/ V> hashMapOf(/*0*/ vararg pairs: kotlin.Pair<K, V> /*kotlin.Array<out kotlin.Pair<K, V>>*/): kotlin.collections.HashMap<K, V>
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline fun </*0*/ T> hashSetOf(): kotlin.collections.HashSet<T>
public fun </*0*/ T> hashSetOf(/*0*/ vararg elements: T /*kotlin.Array<out T>*/): kotlin.collections.HashSet<T>
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> linkedMapOf(): kotlin.collections.LinkedHashMap<K, V>
public fun </*0*/ K, /*1*/ V> linkedMapOf(/*0*/ vararg pairs: kotlin.Pair<K, V> /*kotlin.Array<out kotlin.Pair<K, V>>*/): kotlin.collections.LinkedHashMap<K, V>
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline fun </*0*/ T> linkedSetOf(): kotlin.collections.LinkedHashSet<T>
public fun </*0*/ T> linkedSetOf(/*0*/ vararg elements: T /*kotlin.Array<out T>*/): kotlin.collections.LinkedHashSet<T>
public fun </*0*/ V> linkedStringMapOf(/*0*/ vararg pairs: kotlin.Pair<kotlin.String, V> /*kotlin.Array<out kotlin.Pair<kotlin.String, V>>*/): kotlin.collections.LinkedHashMap<kotlin.String, V>
public fun linkedStringSetOf(/*0*/ vararg elements: kotlin.String /*kotlin.Array<out kotlin.String>*/): kotlin.collections.LinkedHashSet<kotlin.String>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> listOf(): kotlin.collections.List<T>
public fun </*0*/ T> listOf(/*0*/ element: T): kotlin.collections.List<T>
public fun </*0*/ T> listOf(/*0*/ vararg elements: T /*kotlin.Array<out T>*/): kotlin.collections.List<T>
public fun </*0*/ T : kotlin.Any> listOfNotNull(/*0*/ element: T?): kotlin.collections.List<T>
public fun </*0*/ T : kotlin.Any> listOfNotNull(/*0*/ vararg elements: T? /*kotlin.Array<out T?>*/): kotlin.collections.List<T>
@kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> mapOf(): kotlin.collections.Map<K, V>
public fun </*0*/ K, /*1*/ V> mapOf(/*0*/ vararg pairs: kotlin.Pair<K, V> /*kotlin.Array<out kotlin.Pair<K, V>>*/): kotlin.collections.Map<K, V>
public fun </*0*/ K, /*1*/ V> mapOf(/*0*/ pair: kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline fun </*0*/ T> mutableListOf(): kotlin.collections.MutableList<T>
public fun </*0*/ T> mutableListOf(/*0*/ vararg elements: T /*kotlin.Array<out T>*/): kotlin.collections.MutableList<T>
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> mutableMapOf(): kotlin.collections.MutableMap<K, V>
public fun </*0*/ K, /*1*/ V> mutableMapOf(/*0*/ vararg pairs: kotlin.Pair<K, V> /*kotlin.Array<out kotlin.Pair<K, V>>*/): kotlin.collections.MutableMap<K, V>
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline fun </*0*/ T> mutableSetOf(): kotlin.collections.MutableSet<T>
public fun </*0*/ T> mutableSetOf(/*0*/ vararg elements: T /*kotlin.Array<out T>*/): kotlin.collections.MutableSet<T>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> setOf(): kotlin.collections.Set<T>
public fun </*0*/ T> setOf(/*0*/ element: T): kotlin.collections.Set<T>
public fun </*0*/ T> setOf(/*0*/ vararg elements: T /*kotlin.Array<out T>*/): kotlin.collections.Set<T>
@kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T : kotlin.Any> setOfNotNull(/*0*/ element: T?): kotlin.collections.Set<T>
@kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T : kotlin.Any> setOfNotNull(/*0*/ vararg elements: T? /*kotlin.Array<out T?>*/): kotlin.collections.Set<T>
public fun </*0*/ V> stringMapOf(/*0*/ vararg pairs: kotlin.Pair<kotlin.String, V> /*kotlin.Array<out kotlin.Pair<kotlin.String, V>>*/): kotlin.collections.HashMap<kotlin.String, V>
public fun stringSetOf(/*0*/ vararg elements: kotlin.String /*kotlin.Array<out kotlin.String>*/): kotlin.collections.HashSet<kotlin.String>
public fun </*0*/ T> kotlin.collections.MutableCollection<in T>.addAll(/*0*/ elements: kotlin.Array<out T>): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.MutableCollection<in T>.addAll(/*0*/ elements: kotlin.collections.Iterable<T>): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.MutableCollection<in T>.addAll(/*0*/ elements: kotlin.sequences.Sequence<T>): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ T, /*1*/ K, /*2*/ R> kotlin.collections.Grouping<T, K>.aggregate(/*0*/ operation: (key: K, accumulator: R?, element: T, first: kotlin.Boolean) -> R): kotlin.collections.Map<K, R>
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ T, /*1*/ K, /*2*/ R, /*3*/ M : kotlin.collections.MutableMap<in K, R>> kotlin.collections.Grouping<T, K>.aggregateTo(/*0*/ destination: M, /*1*/ operation: (key: K, accumulator: R?, element: T, first: kotlin.Boolean) -> R): M
public inline fun </*0*/ T> kotlin.Array<out T>.all(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.BooleanArray.all(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.ByteArray.all(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.CharArray.all(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.DoubleArray.all(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.FloatArray.all(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.IntArray.all(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.LongArray.all(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.ShortArray.all(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.all(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.all(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.all(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.all(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.Boolean
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.all(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Boolean
public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.all(/*0*/ predicate: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Boolean): kotlin.Boolean
public fun </*0*/ T> kotlin.Array<out T>.any(): kotlin.Boolean
public inline fun </*0*/ T> kotlin.Array<out T>.any(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.BooleanArray.any(): kotlin.Boolean
public inline fun kotlin.BooleanArray.any(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.ByteArray.any(): kotlin.Boolean
public inline fun kotlin.ByteArray.any(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.CharArray.any(): kotlin.Boolean
public inline fun kotlin.CharArray.any(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.DoubleArray.any(): kotlin.Boolean
public inline fun kotlin.DoubleArray.any(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.FloatArray.any(): kotlin.Boolean
public inline fun kotlin.FloatArray.any(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.IntArray.any(): kotlin.Boolean
public inline fun kotlin.IntArray.any(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.LongArray.any(): kotlin.Boolean
public inline fun kotlin.LongArray.any(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.ShortArray.any(): kotlin.Boolean
public inline fun kotlin.ShortArray.any(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.any(): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.any(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.any(): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.any(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.any(): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.any(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.any(): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.any(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.Iterable<T>.any(): kotlin.Boolean
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.any(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Boolean
public fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.any(): kotlin.Boolean
public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.any(/*0*/ predicate: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.asByteArray(): kotlin.ByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.asIntArray(): kotlin.IntArray
public fun </*0*/ T> kotlin.Array<out T>.asIterable(): kotlin.collections.Iterable<T>
public fun kotlin.BooleanArray.asIterable(): kotlin.collections.Iterable<kotlin.Boolean>
public fun kotlin.ByteArray.asIterable(): kotlin.collections.Iterable<kotlin.Byte>
public fun kotlin.CharArray.asIterable(): kotlin.collections.Iterable<kotlin.Char>
public fun kotlin.DoubleArray.asIterable(): kotlin.collections.Iterable<kotlin.Double>
public fun kotlin.FloatArray.asIterable(): kotlin.collections.Iterable<kotlin.Float>
public fun kotlin.IntArray.asIterable(): kotlin.collections.Iterable<kotlin.Int>
public fun kotlin.LongArray.asIterable(): kotlin.collections.Iterable<kotlin.Long>
public fun kotlin.ShortArray.asIterable(): kotlin.collections.Iterable<kotlin.Short>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.asIterable(): kotlin.collections.Iterable<T>
@kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.asIterable(): kotlin.collections.Iterable<kotlin.collections.Map.Entry<K, V>>
public fun </*0*/ T> kotlin.Array<out T>.asList(): kotlin.collections.List<T>
@kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.asList(): kotlin.collections.List<kotlin.Boolean>
@kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.asList(): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.asList(): kotlin.collections.List<kotlin.Char>
@kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.asList(): kotlin.collections.List<kotlin.Double>
@kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.asList(): kotlin.collections.List<kotlin.Float>
@kotlin.internal.InlineOnly public inline fun kotlin.IntArray.asList(): kotlin.collections.List<kotlin.Int>
@kotlin.internal.InlineOnly public inline fun kotlin.LongArray.asList(): kotlin.collections.List<kotlin.Long>
@kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.asList(): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.asList(): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.asList(): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.asList(): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.asList(): kotlin.collections.List<kotlin.UShort>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.asLongArray(): kotlin.LongArray
public fun </*0*/ T> kotlin.collections.List<T>.asReversed(): kotlin.collections.List<T>
@kotlin.jvm.JvmName(name = "asReversedMutable") public fun </*0*/ T> kotlin.collections.MutableList<T>.asReversed(): kotlin.collections.MutableList<T>
public fun </*0*/ T> kotlin.Array<out T>.asSequence(): kotlin.sequences.Sequence<T>
public fun kotlin.BooleanArray.asSequence(): kotlin.sequences.Sequence<kotlin.Boolean>
public fun kotlin.ByteArray.asSequence(): kotlin.sequences.Sequence<kotlin.Byte>
public fun kotlin.CharArray.asSequence(): kotlin.sequences.Sequence<kotlin.Char>
public fun kotlin.DoubleArray.asSequence(): kotlin.sequences.Sequence<kotlin.Double>
public fun kotlin.FloatArray.asSequence(): kotlin.sequences.Sequence<kotlin.Float>
public fun kotlin.IntArray.asSequence(): kotlin.sequences.Sequence<kotlin.Int>
public fun kotlin.LongArray.asSequence(): kotlin.sequences.Sequence<kotlin.Long>
public fun kotlin.ShortArray.asSequence(): kotlin.sequences.Sequence<kotlin.Short>
public fun </*0*/ T> kotlin.collections.Iterable<T>.asSequence(): kotlin.sequences.Sequence<T>
public fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.asSequence(): kotlin.sequences.Sequence<kotlin.collections.Map.Entry<K, V>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.asShortArray(): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.asUByteArray(): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.asUIntArray(): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.asULongArray(): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.asUShortArray(): kotlin.UShortArray
public inline fun </*0*/ T, /*1*/ K, /*2*/ V> kotlin.Array<out T>.associate(/*0*/ transform: (T) -> kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
public inline fun </*0*/ K, /*1*/ V> kotlin.BooleanArray.associate(/*0*/ transform: (kotlin.Boolean) -> kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
public inline fun </*0*/ K, /*1*/ V> kotlin.ByteArray.associate(/*0*/ transform: (kotlin.Byte) -> kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
public inline fun </*0*/ K, /*1*/ V> kotlin.CharArray.associate(/*0*/ transform: (kotlin.Char) -> kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
public inline fun </*0*/ K, /*1*/ V> kotlin.DoubleArray.associate(/*0*/ transform: (kotlin.Double) -> kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
public inline fun </*0*/ K, /*1*/ V> kotlin.FloatArray.associate(/*0*/ transform: (kotlin.Float) -> kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
public inline fun </*0*/ K, /*1*/ V> kotlin.IntArray.associate(/*0*/ transform: (kotlin.Int) -> kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
public inline fun </*0*/ K, /*1*/ V> kotlin.LongArray.associate(/*0*/ transform: (kotlin.Long) -> kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
public inline fun </*0*/ K, /*1*/ V> kotlin.ShortArray.associate(/*0*/ transform: (kotlin.Short) -> kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
public inline fun </*0*/ T, /*1*/ K, /*2*/ V> kotlin.collections.Iterable<T>.associate(/*0*/ transform: (T) -> kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
public inline fun </*0*/ T, /*1*/ K> kotlin.Array<out T>.associateBy(/*0*/ keySelector: (T) -> K): kotlin.collections.Map<K, T>
public inline fun </*0*/ T, /*1*/ K, /*2*/ V> kotlin.Array<out T>.associateBy(/*0*/ keySelector: (T) -> K, /*1*/ valueTransform: (T) -> V): kotlin.collections.Map<K, V>
public inline fun </*0*/ K> kotlin.BooleanArray.associateBy(/*0*/ keySelector: (kotlin.Boolean) -> K): kotlin.collections.Map<K, kotlin.Boolean>
public inline fun </*0*/ K, /*1*/ V> kotlin.BooleanArray.associateBy(/*0*/ keySelector: (kotlin.Boolean) -> K, /*1*/ valueTransform: (kotlin.Boolean) -> V): kotlin.collections.Map<K, V>
public inline fun </*0*/ K> kotlin.ByteArray.associateBy(/*0*/ keySelector: (kotlin.Byte) -> K): kotlin.collections.Map<K, kotlin.Byte>
public inline fun </*0*/ K, /*1*/ V> kotlin.ByteArray.associateBy(/*0*/ keySelector: (kotlin.Byte) -> K, /*1*/ valueTransform: (kotlin.Byte) -> V): kotlin.collections.Map<K, V>
public inline fun </*0*/ K> kotlin.CharArray.associateBy(/*0*/ keySelector: (kotlin.Char) -> K): kotlin.collections.Map<K, kotlin.Char>
public inline fun </*0*/ K, /*1*/ V> kotlin.CharArray.associateBy(/*0*/ keySelector: (kotlin.Char) -> K, /*1*/ valueTransform: (kotlin.Char) -> V): kotlin.collections.Map<K, V>
public inline fun </*0*/ K> kotlin.DoubleArray.associateBy(/*0*/ keySelector: (kotlin.Double) -> K): kotlin.collections.Map<K, kotlin.Double>
public inline fun </*0*/ K, /*1*/ V> kotlin.DoubleArray.associateBy(/*0*/ keySelector: (kotlin.Double) -> K, /*1*/ valueTransform: (kotlin.Double) -> V): kotlin.collections.Map<K, V>
public inline fun </*0*/ K> kotlin.FloatArray.associateBy(/*0*/ keySelector: (kotlin.Float) -> K): kotlin.collections.Map<K, kotlin.Float>
public inline fun </*0*/ K, /*1*/ V> kotlin.FloatArray.associateBy(/*0*/ keySelector: (kotlin.Float) -> K, /*1*/ valueTransform: (kotlin.Float) -> V): kotlin.collections.Map<K, V>
public inline fun </*0*/ K> kotlin.IntArray.associateBy(/*0*/ keySelector: (kotlin.Int) -> K): kotlin.collections.Map<K, kotlin.Int>
public inline fun </*0*/ K, /*1*/ V> kotlin.IntArray.associateBy(/*0*/ keySelector: (kotlin.Int) -> K, /*1*/ valueTransform: (kotlin.Int) -> V): kotlin.collections.Map<K, V>
public inline fun </*0*/ K> kotlin.LongArray.associateBy(/*0*/ keySelector: (kotlin.Long) -> K): kotlin.collections.Map<K, kotlin.Long>
public inline fun </*0*/ K, /*1*/ V> kotlin.LongArray.associateBy(/*0*/ keySelector: (kotlin.Long) -> K, /*1*/ valueTransform: (kotlin.Long) -> V): kotlin.collections.Map<K, V>
public inline fun </*0*/ K> kotlin.ShortArray.associateBy(/*0*/ keySelector: (kotlin.Short) -> K): kotlin.collections.Map<K, kotlin.Short>
public inline fun </*0*/ K, /*1*/ V> kotlin.ShortArray.associateBy(/*0*/ keySelector: (kotlin.Short) -> K, /*1*/ valueTransform: (kotlin.Short) -> V): kotlin.collections.Map<K, V>
public inline fun </*0*/ T, /*1*/ K> kotlin.collections.Iterable<T>.associateBy(/*0*/ keySelector: (T) -> K): kotlin.collections.Map<K, T>
public inline fun </*0*/ T, /*1*/ K, /*2*/ V> kotlin.collections.Iterable<T>.associateBy(/*0*/ keySelector: (T) -> K, /*1*/ valueTransform: (T) -> V): kotlin.collections.Map<K, V>
public inline fun </*0*/ T, /*1*/ K, /*2*/ M : kotlin.collections.MutableMap<in K, in T>> kotlin.Array<out T>.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (T) -> K): M
public inline fun </*0*/ T, /*1*/ K, /*2*/ V, /*3*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.Array<out T>.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (T) -> K, /*2*/ valueTransform: (T) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, in kotlin.Boolean>> kotlin.BooleanArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Boolean) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.BooleanArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Boolean) -> K, /*2*/ valueTransform: (kotlin.Boolean) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, in kotlin.Byte>> kotlin.ByteArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Byte) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.ByteArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Byte) -> K, /*2*/ valueTransform: (kotlin.Byte) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, in kotlin.Char>> kotlin.CharArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Char) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.CharArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Char) -> K, /*2*/ valueTransform: (kotlin.Char) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, in kotlin.Double>> kotlin.DoubleArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Double) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.DoubleArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Double) -> K, /*2*/ valueTransform: (kotlin.Double) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, in kotlin.Float>> kotlin.FloatArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Float) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.FloatArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Float) -> K, /*2*/ valueTransform: (kotlin.Float) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, in kotlin.Int>> kotlin.IntArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Int) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.IntArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Int) -> K, /*2*/ valueTransform: (kotlin.Int) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, in kotlin.Long>> kotlin.LongArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Long) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.LongArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Long) -> K, /*2*/ valueTransform: (kotlin.Long) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, in kotlin.Short>> kotlin.ShortArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Short) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.ShortArray.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Short) -> K, /*2*/ valueTransform: (kotlin.Short) -> V): M
public inline fun </*0*/ T, /*1*/ K, /*2*/ M : kotlin.collections.MutableMap<in K, in T>> kotlin.collections.Iterable<T>.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (T) -> K): M
public inline fun </*0*/ T, /*1*/ K, /*2*/ V, /*3*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.collections.Iterable<T>.associateByTo(/*0*/ destination: M, /*1*/ keySelector: (T) -> K, /*2*/ valueTransform: (T) -> V): M
public inline fun </*0*/ T, /*1*/ K, /*2*/ V, /*3*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.Array<out T>.associateTo(/*0*/ destination: M, /*1*/ transform: (T) -> kotlin.Pair<K, V>): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.BooleanArray.associateTo(/*0*/ destination: M, /*1*/ transform: (kotlin.Boolean) -> kotlin.Pair<K, V>): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.ByteArray.associateTo(/*0*/ destination: M, /*1*/ transform: (kotlin.Byte) -> kotlin.Pair<K, V>): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.CharArray.associateTo(/*0*/ destination: M, /*1*/ transform: (kotlin.Char) -> kotlin.Pair<K, V>): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.DoubleArray.associateTo(/*0*/ destination: M, /*1*/ transform: (kotlin.Double) -> kotlin.Pair<K, V>): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.FloatArray.associateTo(/*0*/ destination: M, /*1*/ transform: (kotlin.Float) -> kotlin.Pair<K, V>): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.IntArray.associateTo(/*0*/ destination: M, /*1*/ transform: (kotlin.Int) -> kotlin.Pair<K, V>): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.LongArray.associateTo(/*0*/ destination: M, /*1*/ transform: (kotlin.Long) -> kotlin.Pair<K, V>): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.ShortArray.associateTo(/*0*/ destination: M, /*1*/ transform: (kotlin.Short) -> kotlin.Pair<K, V>): M
public inline fun </*0*/ T, /*1*/ K, /*2*/ V, /*3*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.collections.Iterable<T>.associateTo(/*0*/ destination: M, /*1*/ transform: (T) -> kotlin.Pair<K, V>): M
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ K, /*1*/ V> kotlin.Array<out K>.associateWith(/*0*/ valueSelector: (K) -> V): kotlin.collections.Map<K, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.BooleanArray.associateWith(/*0*/ valueSelector: (kotlin.Boolean) -> V): kotlin.collections.Map<kotlin.Boolean, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.ByteArray.associateWith(/*0*/ valueSelector: (kotlin.Byte) -> V): kotlin.collections.Map<kotlin.Byte, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.CharArray.associateWith(/*0*/ valueSelector: (kotlin.Char) -> V): kotlin.collections.Map<kotlin.Char, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.DoubleArray.associateWith(/*0*/ valueSelector: (kotlin.Double) -> V): kotlin.collections.Map<kotlin.Double, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.FloatArray.associateWith(/*0*/ valueSelector: (kotlin.Float) -> V): kotlin.collections.Map<kotlin.Float, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.IntArray.associateWith(/*0*/ valueSelector: (kotlin.Int) -> V): kotlin.collections.Map<kotlin.Int, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.LongArray.associateWith(/*0*/ valueSelector: (kotlin.Long) -> V): kotlin.collections.Map<kotlin.Long, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.ShortArray.associateWith(/*0*/ valueSelector: (kotlin.Short) -> V): kotlin.collections.Map<kotlin.Short, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UByteArray.associateWith(/*0*/ valueSelector: (kotlin.UByte) -> V): kotlin.collections.Map<kotlin.UByte, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UIntArray.associateWith(/*0*/ valueSelector: (kotlin.UInt) -> V): kotlin.collections.Map<kotlin.UInt, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.ULongArray.associateWith(/*0*/ valueSelector: (kotlin.ULong) -> V): kotlin.collections.Map<kotlin.ULong, V>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UShortArray.associateWith(/*0*/ valueSelector: (kotlin.UShort) -> V): kotlin.collections.Map<kotlin.UShort, V>
@kotlin.SinceKotlin(version = "1.3") public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Iterable<K>.associateWith(/*0*/ valueSelector: (K) -> V): kotlin.collections.Map<K, V>
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.Array<out K>.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (K) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Boolean, in V>> kotlin.BooleanArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Boolean) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Byte, in V>> kotlin.ByteArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Byte) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Char, in V>> kotlin.CharArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Char) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Double, in V>> kotlin.DoubleArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Double) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Float, in V>> kotlin.FloatArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Float) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Int, in V>> kotlin.IntArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Int) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Long, in V>> kotlin.LongArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Long) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.Short, in V>> kotlin.ShortArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.Short) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.UByte, in V>> kotlin.UByteArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.UByte) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.UInt, in V>> kotlin.UIntArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.UInt) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.ULong, in V>> kotlin.ULongArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.ULong) -> V): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V, /*1*/ M : kotlin.collections.MutableMap<in kotlin.UShort, in V>> kotlin.UShortArray.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (kotlin.UShort) -> V): M
@kotlin.SinceKotlin(version = "1.3") public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.collections.Iterable<K>.associateWithTo(/*0*/ destination: M, /*1*/ valueSelector: (K) -> V): M
@kotlin.jvm.JvmName(name = "averageOfByte") public fun kotlin.Array<out kotlin.Byte>.average(): kotlin.Double
@kotlin.jvm.JvmName(name = "averageOfDouble") public fun kotlin.Array<out kotlin.Double>.average(): kotlin.Double
@kotlin.jvm.JvmName(name = "averageOfFloat") public fun kotlin.Array<out kotlin.Float>.average(): kotlin.Double
@kotlin.jvm.JvmName(name = "averageOfInt") public fun kotlin.Array<out kotlin.Int>.average(): kotlin.Double
@kotlin.jvm.JvmName(name = "averageOfLong") public fun kotlin.Array<out kotlin.Long>.average(): kotlin.Double
@kotlin.jvm.JvmName(name = "averageOfShort") public fun kotlin.Array<out kotlin.Short>.average(): kotlin.Double
public fun kotlin.ByteArray.average(): kotlin.Double
public fun kotlin.DoubleArray.average(): kotlin.Double
public fun kotlin.FloatArray.average(): kotlin.Double
public fun kotlin.IntArray.average(): kotlin.Double
public fun kotlin.LongArray.average(): kotlin.Double
public fun kotlin.ShortArray.average(): kotlin.Double
@kotlin.jvm.JvmName(name = "averageOfByte") public fun kotlin.collections.Iterable<kotlin.Byte>.average(): kotlin.Double
@kotlin.jvm.JvmName(name = "averageOfDouble") public fun kotlin.collections.Iterable<kotlin.Double>.average(): kotlin.Double
@kotlin.jvm.JvmName(name = "averageOfFloat") public fun kotlin.collections.Iterable<kotlin.Float>.average(): kotlin.Double
@kotlin.jvm.JvmName(name = "averageOfInt") public fun kotlin.collections.Iterable<kotlin.Int>.average(): kotlin.Double
@kotlin.jvm.JvmName(name = "averageOfLong") public fun kotlin.collections.Iterable<kotlin.Long>.average(): kotlin.Double
@kotlin.jvm.JvmName(name = "averageOfShort") public fun kotlin.collections.Iterable<kotlin.Short>.average(): kotlin.Double
public fun </*0*/ T> kotlin.collections.List<T>.binarySearch(/*0*/ element: T, /*1*/ comparator: kotlin.Comparator<in T>, /*2*/ fromIndex: kotlin.Int = ..., /*3*/ toIndex: kotlin.Int = ...): kotlin.Int
public fun </*0*/ T> kotlin.collections.List<T>.binarySearch(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ..., /*2*/ comparison: (T) -> kotlin.Int): kotlin.Int
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.collections.List<T?>.binarySearch(/*0*/ element: T?, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Int
public inline fun </*0*/ T, /*1*/ K : kotlin.Comparable<K>> kotlin.collections.List<T>.binarySearchBy(/*0*/ key: K?, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ..., /*3*/ crossinline selector: (T) -> K?): kotlin.Int
@kotlin.SinceKotlin(version = "1.2") public fun </*0*/ T> kotlin.collections.Iterable<T>.chunked(/*0*/ size: kotlin.Int): kotlin.collections.List<kotlin.collections.List<T>>
@kotlin.SinceKotlin(version = "1.2") public fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.chunked(/*0*/ size: kotlin.Int, /*1*/ transform: (kotlin.collections.List<T>) -> R): kotlin.collections.List<R>
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.Array<out T>.component1(): T
@kotlin.internal.InlineOnly public inline operator fun kotlin.BooleanArray.component1(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline operator fun kotlin.ByteArray.component1(): kotlin.Byte
@kotlin.internal.InlineOnly public inline operator fun kotlin.CharArray.component1(): kotlin.Char
@kotlin.internal.InlineOnly public inline operator fun kotlin.DoubleArray.component1(): kotlin.Double
@kotlin.internal.InlineOnly public inline operator fun kotlin.FloatArray.component1(): kotlin.Float
@kotlin.internal.InlineOnly public inline operator fun kotlin.IntArray.component1(): kotlin.Int
@kotlin.internal.InlineOnly public inline operator fun kotlin.LongArray.component1(): kotlin.Long
@kotlin.internal.InlineOnly public inline operator fun kotlin.ShortArray.component1(): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UByteArray.component1(): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UIntArray.component1(): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.ULongArray.component1(): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UShortArray.component1(): kotlin.UShort
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.List<T>.component1(): T
@kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map.Entry<K, V>.component1(): K
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.Array<out T>.component2(): T
@kotlin.internal.InlineOnly public inline operator fun kotlin.BooleanArray.component2(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline operator fun kotlin.ByteArray.component2(): kotlin.Byte
@kotlin.internal.InlineOnly public inline operator fun kotlin.CharArray.component2(): kotlin.Char
@kotlin.internal.InlineOnly public inline operator fun kotlin.DoubleArray.component2(): kotlin.Double
@kotlin.internal.InlineOnly public inline operator fun kotlin.FloatArray.component2(): kotlin.Float
@kotlin.internal.InlineOnly public inline operator fun kotlin.IntArray.component2(): kotlin.Int
@kotlin.internal.InlineOnly public inline operator fun kotlin.LongArray.component2(): kotlin.Long
@kotlin.internal.InlineOnly public inline operator fun kotlin.ShortArray.component2(): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UByteArray.component2(): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UIntArray.component2(): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.ULongArray.component2(): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UShortArray.component2(): kotlin.UShort
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.List<T>.component2(): T
@kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map.Entry<K, V>.component2(): V
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.Array<out T>.component3(): T
@kotlin.internal.InlineOnly public inline operator fun kotlin.BooleanArray.component3(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline operator fun kotlin.ByteArray.component3(): kotlin.Byte
@kotlin.internal.InlineOnly public inline operator fun kotlin.CharArray.component3(): kotlin.Char
@kotlin.internal.InlineOnly public inline operator fun kotlin.DoubleArray.component3(): kotlin.Double
@kotlin.internal.InlineOnly public inline operator fun kotlin.FloatArray.component3(): kotlin.Float
@kotlin.internal.InlineOnly public inline operator fun kotlin.IntArray.component3(): kotlin.Int
@kotlin.internal.InlineOnly public inline operator fun kotlin.LongArray.component3(): kotlin.Long
@kotlin.internal.InlineOnly public inline operator fun kotlin.ShortArray.component3(): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UByteArray.component3(): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UIntArray.component3(): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.ULongArray.component3(): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UShortArray.component3(): kotlin.UShort
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.List<T>.component3(): T
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.Array<out T>.component4(): T
@kotlin.internal.InlineOnly public inline operator fun kotlin.BooleanArray.component4(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline operator fun kotlin.ByteArray.component4(): kotlin.Byte
@kotlin.internal.InlineOnly public inline operator fun kotlin.CharArray.component4(): kotlin.Char
@kotlin.internal.InlineOnly public inline operator fun kotlin.DoubleArray.component4(): kotlin.Double
@kotlin.internal.InlineOnly public inline operator fun kotlin.FloatArray.component4(): kotlin.Float
@kotlin.internal.InlineOnly public inline operator fun kotlin.IntArray.component4(): kotlin.Int
@kotlin.internal.InlineOnly public inline operator fun kotlin.LongArray.component4(): kotlin.Long
@kotlin.internal.InlineOnly public inline operator fun kotlin.ShortArray.component4(): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UByteArray.component4(): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UIntArray.component4(): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.ULongArray.component4(): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UShortArray.component4(): kotlin.UShort
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.List<T>.component4(): T
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.Array<out T>.component5(): T
@kotlin.internal.InlineOnly public inline operator fun kotlin.BooleanArray.component5(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline operator fun kotlin.ByteArray.component5(): kotlin.Byte
@kotlin.internal.InlineOnly public inline operator fun kotlin.CharArray.component5(): kotlin.Char
@kotlin.internal.InlineOnly public inline operator fun kotlin.DoubleArray.component5(): kotlin.Double
@kotlin.internal.InlineOnly public inline operator fun kotlin.FloatArray.component5(): kotlin.Float
@kotlin.internal.InlineOnly public inline operator fun kotlin.IntArray.component5(): kotlin.Int
@kotlin.internal.InlineOnly public inline operator fun kotlin.LongArray.component5(): kotlin.Long
@kotlin.internal.InlineOnly public inline operator fun kotlin.ShortArray.component5(): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UByteArray.component5(): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UIntArray.component5(): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.ULongArray.component5(): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UShortArray.component5(): kotlin.UShort
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.List<T>.component5(): T
public operator fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.Array<out T>.contains(/*0*/ element: T): kotlin.Boolean
public operator fun kotlin.BooleanArray.contains(/*0*/ element: kotlin.Boolean): kotlin.Boolean
public operator fun kotlin.ByteArray.contains(/*0*/ element: kotlin.Byte): kotlin.Boolean
public operator fun kotlin.CharArray.contains(/*0*/ element: kotlin.Char): kotlin.Boolean
public operator fun kotlin.DoubleArray.contains(/*0*/ element: kotlin.Double): kotlin.Boolean
public operator fun kotlin.FloatArray.contains(/*0*/ element: kotlin.Float): kotlin.Boolean
public operator fun kotlin.IntArray.contains(/*0*/ element: kotlin.Int): kotlin.Boolean
public operator fun kotlin.LongArray.contains(/*0*/ element: kotlin.Long): kotlin.Boolean
public operator fun kotlin.ShortArray.contains(/*0*/ element: kotlin.Short): kotlin.Boolean
public operator fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.collections.Iterable<T>.contains(/*0*/ element: T): kotlin.Boolean
@kotlin.internal.InlineOnly public inline operator fun </*0*/ @kotlin.internal.OnlyInputTypes K, /*1*/ V> kotlin.collections.Map<out K, V>.contains(/*0*/ key: K): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.collections.Collection<T>.containsAll(/*0*/ elements: kotlin.collections.Collection<T>): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun </*0*/ @kotlin.internal.OnlyInputTypes K> kotlin.collections.Map<out K, *>.containsKey(/*0*/ key: K): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ @kotlin.internal.OnlyInputTypes V> kotlin.collections.Map<K, V>.containsValue(/*0*/ value: V): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public infix fun </*0*/ T> kotlin.Array<out T>.contentDeepEquals(/*0*/ other: kotlin.Array<out T>): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayDeepEquals") public infix fun </*0*/ T> kotlin.Array<out T>?.contentDeepEquals(/*0*/ other: kotlin.Array<out T>?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun </*0*/ T> kotlin.Array<out T>.contentDeepHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayDeepHashCode") public fun </*0*/ T> kotlin.Array<out T>?.contentDeepHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun </*0*/ T> kotlin.Array<out T>.contentDeepToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayDeepToString") public fun </*0*/ T> kotlin.Array<out T>?.contentDeepToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public infix fun </*0*/ T> kotlin.Array<out T>.contentEquals(/*0*/ other: kotlin.Array<out T>): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun </*0*/ T> kotlin.Array<out T>?.contentEquals(/*0*/ other: kotlin.Array<out T>?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public infix fun kotlin.BooleanArray.contentEquals(/*0*/ other: kotlin.BooleanArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.BooleanArray?.contentEquals(/*0*/ other: kotlin.BooleanArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public infix fun kotlin.ByteArray.contentEquals(/*0*/ other: kotlin.ByteArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.ByteArray?.contentEquals(/*0*/ other: kotlin.ByteArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public infix fun kotlin.CharArray.contentEquals(/*0*/ other: kotlin.CharArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.CharArray?.contentEquals(/*0*/ other: kotlin.CharArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public infix fun kotlin.DoubleArray.contentEquals(/*0*/ other: kotlin.DoubleArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.DoubleArray?.contentEquals(/*0*/ other: kotlin.DoubleArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public infix fun kotlin.FloatArray.contentEquals(/*0*/ other: kotlin.FloatArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.FloatArray?.contentEquals(/*0*/ other: kotlin.FloatArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public infix fun kotlin.IntArray.contentEquals(/*0*/ other: kotlin.IntArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.IntArray?.contentEquals(/*0*/ other: kotlin.IntArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public infix fun kotlin.LongArray.contentEquals(/*0*/ other: kotlin.LongArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.LongArray?.contentEquals(/*0*/ other: kotlin.LongArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public infix fun kotlin.ShortArray.contentEquals(/*0*/ other: kotlin.ShortArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayEquals") public infix fun kotlin.ShortArray?.contentEquals(/*0*/ other: kotlin.ShortArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.UByteArray.contentEquals(/*0*/ other: kotlin.UByteArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.UByteArray?.contentEquals(/*0*/ other: kotlin.UByteArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.UIntArray.contentEquals(/*0*/ other: kotlin.UIntArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.UIntArray?.contentEquals(/*0*/ other: kotlin.UIntArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.ULongArray.contentEquals(/*0*/ other: kotlin.ULongArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.ULongArray?.contentEquals(/*0*/ other: kotlin.ULongArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.UShortArray.contentEquals(/*0*/ other: kotlin.UShortArray): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.UShortArray?.contentEquals(/*0*/ other: kotlin.UShortArray?): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun </*0*/ T> kotlin.Array<out T>.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun </*0*/ T> kotlin.Array<out T>?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.BooleanArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.BooleanArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.ByteArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.ByteArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.CharArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.CharArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.DoubleArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.DoubleArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.FloatArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.FloatArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.IntArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.IntArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.LongArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.LongArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.ShortArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayHashCode") public fun kotlin.ShortArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray?.contentHashCode(): kotlin.Int
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun </*0*/ T> kotlin.Array<out T>.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun </*0*/ T> kotlin.Array<out T>?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.BooleanArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.BooleanArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.ByteArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.ByteArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.CharArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.CharArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.DoubleArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.DoubleArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.FloatArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.FloatArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.IntArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.IntArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.LongArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.LongArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.LowPriorityInOverloadResolution public fun kotlin.ShortArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.js.library(name = "arrayToString") public fun kotlin.ShortArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray?.contentToString(): kotlin.String
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.copyInto(/*0*/ destination: kotlin.Array<T>, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.Array<T>
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.copyInto(/*0*/ destination: kotlin.BooleanArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.BooleanArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.copyInto(/*0*/ destination: kotlin.ByteArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.ByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.copyInto(/*0*/ destination: kotlin.CharArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.CharArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.copyInto(/*0*/ destination: kotlin.DoubleArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.DoubleArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.copyInto(/*0*/ destination: kotlin.FloatArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.FloatArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.copyInto(/*0*/ destination: kotlin.IntArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.IntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.copyInto(/*0*/ destination: kotlin.LongArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.LongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.copyInto(/*0*/ destination: kotlin.ShortArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.copyInto(/*0*/ destination: kotlin.UByteArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.copyInto(/*0*/ destination: kotlin.UIntArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.copyInto(/*0*/ destination: kotlin.ULongArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.copyInto(/*0*/ destination: kotlin.UShortArray, /*1*/ destinationOffset: kotlin.Int = ..., /*2*/ startIndex: kotlin.Int = ..., /*3*/ endIndex: kotlin.Int = ...): kotlin.UShortArray
public inline fun </*0*/ T> kotlin.Array<out T>.copyOf(): kotlin.Array<T>
public fun </*0*/ T> kotlin.Array<out T>.copyOf(/*0*/ newSize: kotlin.Int): kotlin.Array<T?>
public fun kotlin.BooleanArray.copyOf(): kotlin.BooleanArray
public fun kotlin.BooleanArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.BooleanArray
public inline fun kotlin.ByteArray.copyOf(): kotlin.ByteArray
public fun kotlin.ByteArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.ByteArray
public fun kotlin.CharArray.copyOf(): kotlin.CharArray
public fun kotlin.CharArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.CharArray
public inline fun kotlin.DoubleArray.copyOf(): kotlin.DoubleArray
public fun kotlin.DoubleArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.DoubleArray
public inline fun kotlin.FloatArray.copyOf(): kotlin.FloatArray
public fun kotlin.FloatArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.FloatArray
public inline fun kotlin.IntArray.copyOf(): kotlin.IntArray
public fun kotlin.IntArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.IntArray
public fun kotlin.LongArray.copyOf(): kotlin.LongArray
public fun kotlin.LongArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.LongArray
public inline fun kotlin.ShortArray.copyOf(): kotlin.ShortArray
public fun kotlin.ShortArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.copyOf(): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.copyOf(): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.copyOf(): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.copyOf(): kotlin.UShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.copyOf(/*0*/ newSize: kotlin.Int): kotlin.UShortArray
public fun </*0*/ T> kotlin.Array<out T>.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Array<T>
public fun kotlin.BooleanArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.BooleanArray
public fun kotlin.ByteArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.ByteArray
public fun kotlin.CharArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.CharArray
public fun kotlin.DoubleArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.DoubleArray
public fun kotlin.FloatArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.FloatArray
public fun kotlin.IntArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.IntArray
public fun kotlin.LongArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.LongArray
public fun kotlin.ShortArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.copyOfRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.UShortArray
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.count(): kotlin.Int
public inline fun </*0*/ T> kotlin.Array<out T>.count(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Int
@kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.count(): kotlin.Int
public inline fun kotlin.BooleanArray.count(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Int
@kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.count(): kotlin.Int
public inline fun kotlin.ByteArray.count(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Int
@kotlin.internal.InlineOnly public inline fun kotlin.CharArray.count(): kotlin.Int
public inline fun kotlin.CharArray.count(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Int
@kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.count(): kotlin.Int
public inline fun kotlin.DoubleArray.count(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Int
@kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.count(): kotlin.Int
public inline fun kotlin.FloatArray.count(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Int
@kotlin.internal.InlineOnly public inline fun kotlin.IntArray.count(): kotlin.Int
public inline fun kotlin.IntArray.count(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Int
@kotlin.internal.InlineOnly public inline fun kotlin.LongArray.count(): kotlin.Int
public inline fun kotlin.LongArray.count(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Int
@kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.count(): kotlin.Int
public inline fun kotlin.ShortArray.count(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.count(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.count(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.count(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.count(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.Int
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Collection<T>.count(): kotlin.Int
public fun </*0*/ T> kotlin.collections.Iterable<T>.count(): kotlin.Int
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.count(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Int
@kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.count(): kotlin.Int
public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.count(/*0*/ predicate: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Boolean): kotlin.Int
public fun </*0*/ T> kotlin.Array<out T>.distinct(): kotlin.collections.List<T>
public fun kotlin.BooleanArray.distinct(): kotlin.collections.List<kotlin.Boolean>
public fun kotlin.ByteArray.distinct(): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.distinct(): kotlin.collections.List<kotlin.Char>
public fun kotlin.DoubleArray.distinct(): kotlin.collections.List<kotlin.Double>
public fun kotlin.FloatArray.distinct(): kotlin.collections.List<kotlin.Float>
public fun kotlin.IntArray.distinct(): kotlin.collections.List<kotlin.Int>
public fun kotlin.LongArray.distinct(): kotlin.collections.List<kotlin.Long>
public fun kotlin.ShortArray.distinct(): kotlin.collections.List<kotlin.Short>
public fun </*0*/ T> kotlin.collections.Iterable<T>.distinct(): kotlin.collections.List<T>
public inline fun </*0*/ T, /*1*/ K> kotlin.Array<out T>.distinctBy(/*0*/ selector: (T) -> K): kotlin.collections.List<T>
public inline fun </*0*/ K> kotlin.BooleanArray.distinctBy(/*0*/ selector: (kotlin.Boolean) -> K): kotlin.collections.List<kotlin.Boolean>
public inline fun </*0*/ K> kotlin.ByteArray.distinctBy(/*0*/ selector: (kotlin.Byte) -> K): kotlin.collections.List<kotlin.Byte>
public inline fun </*0*/ K> kotlin.CharArray.distinctBy(/*0*/ selector: (kotlin.Char) -> K): kotlin.collections.List<kotlin.Char>
public inline fun </*0*/ K> kotlin.DoubleArray.distinctBy(/*0*/ selector: (kotlin.Double) -> K): kotlin.collections.List<kotlin.Double>
public inline fun </*0*/ K> kotlin.FloatArray.distinctBy(/*0*/ selector: (kotlin.Float) -> K): kotlin.collections.List<kotlin.Float>
public inline fun </*0*/ K> kotlin.IntArray.distinctBy(/*0*/ selector: (kotlin.Int) -> K): kotlin.collections.List<kotlin.Int>
public inline fun </*0*/ K> kotlin.LongArray.distinctBy(/*0*/ selector: (kotlin.Long) -> K): kotlin.collections.List<kotlin.Long>
public inline fun </*0*/ K> kotlin.ShortArray.distinctBy(/*0*/ selector: (kotlin.Short) -> K): kotlin.collections.List<kotlin.Short>
public inline fun </*0*/ T, /*1*/ K> kotlin.collections.Iterable<T>.distinctBy(/*0*/ selector: (T) -> K): kotlin.collections.List<T>
public fun </*0*/ T> kotlin.Array<out T>.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<T>
public fun kotlin.BooleanArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Boolean>
public fun kotlin.ByteArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Char>
public fun kotlin.DoubleArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Double>
public fun kotlin.FloatArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Float>
public fun kotlin.IntArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Int>
public fun kotlin.LongArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Long>
public fun kotlin.ShortArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UShort>
public fun </*0*/ T> kotlin.collections.Iterable<T>.drop(/*0*/ n: kotlin.Int): kotlin.collections.List<T>
public fun </*0*/ T> kotlin.Array<out T>.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<T>
public fun kotlin.BooleanArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Boolean>
public fun kotlin.ByteArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Char>
public fun kotlin.DoubleArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Double>
public fun kotlin.FloatArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Float>
public fun kotlin.IntArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Int>
public fun kotlin.LongArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Long>
public fun kotlin.ShortArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UShort>
public fun </*0*/ T> kotlin.collections.List<T>.dropLast(/*0*/ n: kotlin.Int): kotlin.collections.List<T>
public inline fun </*0*/ T> kotlin.Array<out T>.dropLastWhile(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun kotlin.BooleanArray.dropLastWhile(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
public inline fun kotlin.ByteArray.dropLastWhile(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.collections.List<kotlin.Byte>
public inline fun kotlin.CharArray.dropLastWhile(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.collections.List<kotlin.Char>
public inline fun kotlin.DoubleArray.dropLastWhile(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.collections.List<kotlin.Double>
public inline fun kotlin.FloatArray.dropLastWhile(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.collections.List<kotlin.Float>
public inline fun kotlin.IntArray.dropLastWhile(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.collections.List<kotlin.Int>
public inline fun kotlin.LongArray.dropLastWhile(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.collections.List<kotlin.Long>
public inline fun kotlin.ShortArray.dropLastWhile(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.dropLastWhile(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.dropLastWhile(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.dropLastWhile(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.dropLastWhile(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.collections.List<kotlin.UShort>
public inline fun </*0*/ T> kotlin.collections.List<T>.dropLastWhile(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun </*0*/ T> kotlin.Array<out T>.dropWhile(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun kotlin.BooleanArray.dropWhile(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
public inline fun kotlin.ByteArray.dropWhile(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.collections.List<kotlin.Byte>
public inline fun kotlin.CharArray.dropWhile(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.collections.List<kotlin.Char>
public inline fun kotlin.DoubleArray.dropWhile(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.collections.List<kotlin.Double>
public inline fun kotlin.FloatArray.dropWhile(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.collections.List<kotlin.Float>
public inline fun kotlin.IntArray.dropWhile(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.collections.List<kotlin.Int>
public inline fun kotlin.LongArray.dropWhile(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.collections.List<kotlin.Long>
public inline fun kotlin.ShortArray.dropWhile(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.dropWhile(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.dropWhile(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.dropWhile(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.dropWhile(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.collections.List<kotlin.UShort>
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.dropWhile(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
@kotlin.SinceKotlin(version = "1.1") public fun </*0*/ T, /*1*/ K> kotlin.collections.Grouping<T, K>.eachCount(): kotlin.collections.Map<K, kotlin.Int>
@kotlin.SinceKotlin(version = "1.1") public fun </*0*/ T, /*1*/ K, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.Int>> kotlin.collections.Grouping<T, K>.eachCountTo(/*0*/ destination: M): M
public fun </*0*/ T> kotlin.Array<out T>.elementAt(/*0*/ index: kotlin.Int): T
public fun kotlin.BooleanArray.elementAt(/*0*/ index: kotlin.Int): kotlin.Boolean
public fun kotlin.ByteArray.elementAt(/*0*/ index: kotlin.Int): kotlin.Byte
public fun kotlin.CharArray.elementAt(/*0*/ index: kotlin.Int): kotlin.Char
public fun kotlin.DoubleArray.elementAt(/*0*/ index: kotlin.Int): kotlin.Double
public fun kotlin.FloatArray.elementAt(/*0*/ index: kotlin.Int): kotlin.Float
public fun kotlin.IntArray.elementAt(/*0*/ index: kotlin.Int): kotlin.Int
public fun kotlin.LongArray.elementAt(/*0*/ index: kotlin.Int): kotlin.Long
public fun kotlin.ShortArray.elementAt(/*0*/ index: kotlin.Int): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.elementAt(/*0*/ index: kotlin.Int): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.elementAt(/*0*/ index: kotlin.Int): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.elementAt(/*0*/ index: kotlin.Int): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.elementAt(/*0*/ index: kotlin.Int): kotlin.UShort
public fun </*0*/ T> kotlin.collections.Iterable<T>.elementAt(/*0*/ index: kotlin.Int): T
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.List<T>.elementAt(/*0*/ index: kotlin.Int): T
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> T): T
@kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Boolean): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Byte): kotlin.Byte
@kotlin.internal.InlineOnly public inline fun kotlin.CharArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Char): kotlin.Char
@kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Double): kotlin.Double
@kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Float): kotlin.Float
@kotlin.internal.InlineOnly public inline fun kotlin.IntArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Int): kotlin.Int
@kotlin.internal.InlineOnly public inline fun kotlin.LongArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Long): kotlin.Long
@kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Short): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.UByte): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.UShort): kotlin.UShort
public fun </*0*/ T> kotlin.collections.Iterable<T>.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> T): T
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.List<T>.elementAtOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> T): T
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.elementAtOrNull(/*0*/ index: kotlin.Int): T?
@kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.Boolean?
@kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.Byte?
@kotlin.internal.InlineOnly public inline fun kotlin.CharArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.Char?
@kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.Double?
@kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.Float?
@kotlin.internal.InlineOnly public inline fun kotlin.IntArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.Int?
@kotlin.internal.InlineOnly public inline fun kotlin.LongArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.Long?
@kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.elementAtOrNull(/*0*/ index: kotlin.Int): kotlin.UShort?
public fun </*0*/ T> kotlin.collections.Iterable<T>.elementAtOrNull(/*0*/ index: kotlin.Int): T?
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.List<T>.elementAtOrNull(/*0*/ index: kotlin.Int): T?
@kotlin.SinceKotlin(version = "1.3") public fun </*0*/ T> kotlin.Array<T>.fill(/*0*/ element: T, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.BooleanArray.fill(/*0*/ element: kotlin.Boolean, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.ByteArray.fill(/*0*/ element: kotlin.Byte, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.CharArray.fill(/*0*/ element: kotlin.Char, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.DoubleArray.fill(/*0*/ element: kotlin.Double, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.FloatArray.fill(/*0*/ element: kotlin.Float, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.IntArray.fill(/*0*/ element: kotlin.Int, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.LongArray.fill(/*0*/ element: kotlin.Long, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.ShortArray.fill(/*0*/ element: kotlin.Short, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.fill(/*0*/ element: kotlin.UByte, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.fill(/*0*/ element: kotlin.UInt, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.fill(/*0*/ element: kotlin.ULong, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.fill(/*0*/ element: kotlin.UShort, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.2") public fun </*0*/ T> kotlin.collections.MutableList<T>.fill(/*0*/ value: T): kotlin.Unit
public inline fun </*0*/ T> kotlin.Array<out T>.filter(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun kotlin.BooleanArray.filter(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
public inline fun kotlin.ByteArray.filter(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.collections.List<kotlin.Byte>
public inline fun kotlin.CharArray.filter(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.collections.List<kotlin.Char>
public inline fun kotlin.DoubleArray.filter(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.collections.List<kotlin.Double>
public inline fun kotlin.FloatArray.filter(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.collections.List<kotlin.Float>
public inline fun kotlin.IntArray.filter(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.collections.List<kotlin.Int>
public inline fun kotlin.LongArray.filter(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.collections.List<kotlin.Long>
public inline fun kotlin.ShortArray.filter(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.filter(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.filter(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.filter(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.filter(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.collections.List<kotlin.UShort>
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.filter(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.filter(/*0*/ predicate: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Boolean): kotlin.collections.Map<K, V>
public inline fun </*0*/ T> kotlin.Array<out T>.filterIndexed(/*0*/ predicate: (index: kotlin.Int, T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun kotlin.BooleanArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
public inline fun kotlin.ByteArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.Byte) -> kotlin.Boolean): kotlin.collections.List<kotlin.Byte>
public inline fun kotlin.CharArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.Char) -> kotlin.Boolean): kotlin.collections.List<kotlin.Char>
public inline fun kotlin.DoubleArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.Double) -> kotlin.Boolean): kotlin.collections.List<kotlin.Double>
public inline fun kotlin.FloatArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.Float) -> kotlin.Boolean): kotlin.collections.List<kotlin.Float>
public inline fun kotlin.IntArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.Int) -> kotlin.Boolean): kotlin.collections.List<kotlin.Int>
public inline fun kotlin.LongArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.Long) -> kotlin.Boolean): kotlin.collections.List<kotlin.Long>
public inline fun kotlin.ShortArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.Short) -> kotlin.Boolean): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.UByte) -> kotlin.Boolean): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.UInt) -> kotlin.Boolean): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.ULong) -> kotlin.Boolean): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.filterIndexed(/*0*/ predicate: (index: kotlin.Int, kotlin.UShort) -> kotlin.Boolean): kotlin.collections.List<kotlin.UShort>
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.filterIndexed(/*0*/ predicate: (index: kotlin.Int, T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun </*0*/ T, /*1*/ C : kotlin.collections.MutableCollection<in T>> kotlin.Array<out T>.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, T) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Boolean>> kotlin.BooleanArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.Boolean) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Byte>> kotlin.ByteArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.Byte) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Char>> kotlin.CharArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.Char) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Double>> kotlin.DoubleArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.Double) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Float>> kotlin.FloatArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.Float) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Int>> kotlin.IntArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.Int) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Long>> kotlin.LongArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.Long) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Short>> kotlin.ShortArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.Short) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.UByte>> kotlin.UByteArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.UByte) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.UInt>> kotlin.UIntArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.UInt) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.ULong>> kotlin.ULongArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.ULong) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.UShort>> kotlin.UShortArray.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, kotlin.UShort) -> kotlin.Boolean): C
public inline fun </*0*/ T, /*1*/ C : kotlin.collections.MutableCollection<in T>> kotlin.collections.Iterable<T>.filterIndexedTo(/*0*/ destination: C, /*1*/ predicate: (index: kotlin.Int, T) -> kotlin.Boolean): C
public inline fun </*0*/ reified R> kotlin.Array<*>.filterIsInstance(): kotlin.collections.List<R>
public inline fun </*0*/ reified R> kotlin.collections.Iterable<*>.filterIsInstance(): kotlin.collections.List<R>
public inline fun </*0*/ reified R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.Array<*>.filterIsInstanceTo(/*0*/ destination: C): C
public inline fun </*0*/ reified R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.collections.Iterable<*>.filterIsInstanceTo(/*0*/ destination: C): C
public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.filterKeys(/*0*/ predicate: (K) -> kotlin.Boolean): kotlin.collections.Map<K, V>
public inline fun </*0*/ T> kotlin.Array<out T>.filterNot(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun kotlin.BooleanArray.filterNot(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
public inline fun kotlin.ByteArray.filterNot(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.collections.List<kotlin.Byte>
public inline fun kotlin.CharArray.filterNot(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.collections.List<kotlin.Char>
public inline fun kotlin.DoubleArray.filterNot(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.collections.List<kotlin.Double>
public inline fun kotlin.FloatArray.filterNot(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.collections.List<kotlin.Float>
public inline fun kotlin.IntArray.filterNot(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.collections.List<kotlin.Int>
public inline fun kotlin.LongArray.filterNot(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.collections.List<kotlin.Long>
public inline fun kotlin.ShortArray.filterNot(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.filterNot(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.filterNot(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.filterNot(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.filterNot(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.collections.List<kotlin.UShort>
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.filterNot(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.filterNot(/*0*/ predicate: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Boolean): kotlin.collections.Map<K, V>
public fun </*0*/ T : kotlin.Any> kotlin.Array<out T?>.filterNotNull(): kotlin.collections.List<T>
public fun </*0*/ T : kotlin.Any> kotlin.collections.Iterable<T?>.filterNotNull(): kotlin.collections.List<T>
public fun </*0*/ C : kotlin.collections.MutableCollection<in T>, /*1*/ T : kotlin.Any> kotlin.Array<out T?>.filterNotNullTo(/*0*/ destination: C): C
public fun </*0*/ C : kotlin.collections.MutableCollection<in T>, /*1*/ T : kotlin.Any> kotlin.collections.Iterable<T?>.filterNotNullTo(/*0*/ destination: C): C
public inline fun </*0*/ T, /*1*/ C : kotlin.collections.MutableCollection<in T>> kotlin.Array<out T>.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (T) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Boolean>> kotlin.BooleanArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Byte>> kotlin.ByteArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Byte) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Char>> kotlin.CharArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Char) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Double>> kotlin.DoubleArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Double) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Float>> kotlin.FloatArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Float) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Int>> kotlin.IntArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Int) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Long>> kotlin.LongArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Long) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Short>> kotlin.ShortArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Short) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.UByte>> kotlin.UByteArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.UByte) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.UInt>> kotlin.UIntArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.UInt) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.ULong>> kotlin.ULongArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.ULong) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.UShort>> kotlin.UShortArray.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.UShort) -> kotlin.Boolean): C
public inline fun </*0*/ T, /*1*/ C : kotlin.collections.MutableCollection<in T>> kotlin.collections.Iterable<T>.filterNotTo(/*0*/ destination: C, /*1*/ predicate: (T) -> kotlin.Boolean): C
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.collections.Map<out K, V>.filterNotTo(/*0*/ destination: M, /*1*/ predicate: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Boolean): M
public inline fun </*0*/ T, /*1*/ C : kotlin.collections.MutableCollection<in T>> kotlin.Array<out T>.filterTo(/*0*/ destination: C, /*1*/ predicate: (T) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Boolean>> kotlin.BooleanArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Byte>> kotlin.ByteArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Byte) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Char>> kotlin.CharArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Char) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Double>> kotlin.DoubleArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Double) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Float>> kotlin.FloatArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Float) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Int>> kotlin.IntArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Int) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Long>> kotlin.LongArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Long) -> kotlin.Boolean): C
public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Short>> kotlin.ShortArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.Short) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.UByte>> kotlin.UByteArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.UByte) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.UInt>> kotlin.UIntArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.UInt) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.ULong>> kotlin.ULongArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.ULong) -> kotlin.Boolean): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.UShort>> kotlin.UShortArray.filterTo(/*0*/ destination: C, /*1*/ predicate: (kotlin.UShort) -> kotlin.Boolean): C
public inline fun </*0*/ T, /*1*/ C : kotlin.collections.MutableCollection<in T>> kotlin.collections.Iterable<T>.filterTo(/*0*/ destination: C, /*1*/ predicate: (T) -> kotlin.Boolean): C
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.collections.Map<out K, V>.filterTo(/*0*/ destination: M, /*1*/ predicate: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Boolean): M
public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.filterValues(/*0*/ predicate: (V) -> kotlin.Boolean): kotlin.collections.Map<K, V>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.find(/*0*/ predicate: (T) -> kotlin.Boolean): T?
@kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.find(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
@kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.find(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Byte?
@kotlin.internal.InlineOnly public inline fun kotlin.CharArray.find(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Char?
@kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.find(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Double?
@kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.find(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Float?
@kotlin.internal.InlineOnly public inline fun kotlin.IntArray.find(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Int?
@kotlin.internal.InlineOnly public inline fun kotlin.LongArray.find(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Long?
@kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.find(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.find(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.find(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.find(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.find(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.UShort?
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.find(/*0*/ predicate: (T) -> kotlin.Boolean): T?
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.findLast(/*0*/ predicate: (T) -> kotlin.Boolean): T?
@kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.findLast(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
@kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.findLast(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Byte?
@kotlin.internal.InlineOnly public inline fun kotlin.CharArray.findLast(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Char?
@kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.findLast(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Double?
@kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.findLast(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Float?
@kotlin.internal.InlineOnly public inline fun kotlin.IntArray.findLast(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Int?
@kotlin.internal.InlineOnly public inline fun kotlin.LongArray.findLast(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Long?
@kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.findLast(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.findLast(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.findLast(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.findLast(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.findLast(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.UShort?
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.findLast(/*0*/ predicate: (T) -> kotlin.Boolean): T?
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.List<T>.findLast(/*0*/ predicate: (T) -> kotlin.Boolean): T?
public fun </*0*/ T> kotlin.Array<out T>.first(): T
public inline fun </*0*/ T> kotlin.Array<out T>.first(/*0*/ predicate: (T) -> kotlin.Boolean): T
public fun kotlin.BooleanArray.first(): kotlin.Boolean
public inline fun kotlin.BooleanArray.first(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.ByteArray.first(): kotlin.Byte
public inline fun kotlin.ByteArray.first(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Byte
public fun kotlin.CharArray.first(): kotlin.Char
public inline fun kotlin.CharArray.first(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Char
public fun kotlin.DoubleArray.first(): kotlin.Double
public inline fun kotlin.DoubleArray.first(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Double
public fun kotlin.FloatArray.first(): kotlin.Float
public inline fun kotlin.FloatArray.first(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Float
public fun kotlin.IntArray.first(): kotlin.Int
public inline fun kotlin.IntArray.first(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Int
public fun kotlin.LongArray.first(): kotlin.Long
public inline fun kotlin.LongArray.first(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Long
public fun kotlin.ShortArray.first(): kotlin.Short
public inline fun kotlin.ShortArray.first(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.first(): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.first(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.first(): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.first(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.first(): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.first(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.first(): kotlin.UShort
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.first(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.UShort
public fun </*0*/ T> kotlin.collections.Iterable<T>.first(): T
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.first(/*0*/ predicate: (T) -> kotlin.Boolean): T
public fun </*0*/ T> kotlin.collections.List<T>.first(): T
public fun </*0*/ T> kotlin.Array<out T>.firstOrNull(): T?
public inline fun </*0*/ T> kotlin.Array<out T>.firstOrNull(/*0*/ predicate: (T) -> kotlin.Boolean): T?
public fun kotlin.BooleanArray.firstOrNull(): kotlin.Boolean?
public inline fun kotlin.BooleanArray.firstOrNull(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
public fun kotlin.ByteArray.firstOrNull(): kotlin.Byte?
public inline fun kotlin.ByteArray.firstOrNull(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Byte?
public fun kotlin.CharArray.firstOrNull(): kotlin.Char?
public inline fun kotlin.CharArray.firstOrNull(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Char?
public fun kotlin.DoubleArray.firstOrNull(): kotlin.Double?
public inline fun kotlin.DoubleArray.firstOrNull(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Double?
public fun kotlin.FloatArray.firstOrNull(): kotlin.Float?
public inline fun kotlin.FloatArray.firstOrNull(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Float?
public fun kotlin.IntArray.firstOrNull(): kotlin.Int?
public inline fun kotlin.IntArray.firstOrNull(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Int?
public fun kotlin.LongArray.firstOrNull(): kotlin.Long?
public inline fun kotlin.LongArray.firstOrNull(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Long?
public fun kotlin.ShortArray.firstOrNull(): kotlin.Short?
public inline fun kotlin.ShortArray.firstOrNull(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.firstOrNull(): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.firstOrNull(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.firstOrNull(): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.firstOrNull(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.firstOrNull(): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.firstOrNull(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.firstOrNull(): kotlin.UShort?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.firstOrNull(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.UShort?
public fun </*0*/ T> kotlin.collections.Iterable<T>.firstOrNull(): T?
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.firstOrNull(/*0*/ predicate: (T) -> kotlin.Boolean): T?
public fun </*0*/ T> kotlin.collections.List<T>.firstOrNull(): T?
public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.flatMap(/*0*/ transform: (T) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "flatMapSequence") public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.flatMap(/*0*/ transform: (T) -> kotlin.sequences.Sequence<R>): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.BooleanArray.flatMap(/*0*/ transform: (kotlin.Boolean) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.ByteArray.flatMap(/*0*/ transform: (kotlin.Byte) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.CharArray.flatMap(/*0*/ transform: (kotlin.Char) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.DoubleArray.flatMap(/*0*/ transform: (kotlin.Double) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.FloatArray.flatMap(/*0*/ transform: (kotlin.Float) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.IntArray.flatMap(/*0*/ transform: (kotlin.Int) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.LongArray.flatMap(/*0*/ transform: (kotlin.Long) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.ShortArray.flatMap(/*0*/ transform: (kotlin.Short) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.flatMap(/*0*/ transform: (kotlin.UByte) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.flatMap(/*0*/ transform: (kotlin.UInt) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.flatMap(/*0*/ transform: (kotlin.ULong) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.flatMap(/*0*/ transform: (kotlin.UShort) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.flatMap(/*0*/ transform: (T) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "flatMapSequence") public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.flatMap(/*0*/ transform: (T) -> kotlin.sequences.Sequence<R>): kotlin.collections.List<R>
public inline fun </*0*/ K, /*1*/ V, /*2*/ R> kotlin.collections.Map<out K, V>.flatMap(/*0*/ transform: (kotlin.collections.Map.Entry<K, V>) -> kotlin.collections.Iterable<R>): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "flatMapSequence") public inline fun </*0*/ K, /*1*/ V, /*2*/ R> kotlin.collections.Map<out K, V>.flatMap(/*0*/ transform: (kotlin.collections.Map.Entry<K, V>) -> kotlin.sequences.Sequence<R>): kotlin.collections.List<R>
public inline fun </*0*/ T, /*1*/ R, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.Array<out T>.flatMapTo(/*0*/ destination: C, /*1*/ transform: (T) -> kotlin.collections.Iterable<R>): C
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "flatMapSequenceTo") public inline fun </*0*/ T, /*1*/ R, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.Array<out T>.flatMapTo(/*0*/ destination: C, /*1*/ transform: (T) -> kotlin.sequences.Sequence<R>): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.BooleanArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Boolean) -> kotlin.collections.Iterable<R>): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.ByteArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Byte) -> kotlin.collections.Iterable<R>): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.CharArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Char) -> kotlin.collections.Iterable<R>): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.DoubleArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Double) -> kotlin.collections.Iterable<R>): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.FloatArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Float) -> kotlin.collections.Iterable<R>): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.IntArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Int) -> kotlin.collections.Iterable<R>): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.LongArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Long) -> kotlin.collections.Iterable<R>): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.ShortArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Short) -> kotlin.collections.Iterable<R>): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.UByteArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.UByte) -> kotlin.collections.Iterable<R>): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.UIntArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.UInt) -> kotlin.collections.Iterable<R>): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.ULongArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.ULong) -> kotlin.collections.Iterable<R>): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.UShortArray.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.UShort) -> kotlin.collections.Iterable<R>): C
public inline fun </*0*/ T, /*1*/ R, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.collections.Iterable<T>.flatMapTo(/*0*/ destination: C, /*1*/ transform: (T) -> kotlin.collections.Iterable<R>): C
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "flatMapSequenceTo") public inline fun </*0*/ T, /*1*/ R, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.collections.Iterable<T>.flatMapTo(/*0*/ destination: C, /*1*/ transform: (T) -> kotlin.sequences.Sequence<R>): C
public inline fun </*0*/ K, /*1*/ V, /*2*/ R, /*3*/ C : kotlin.collections.MutableCollection<in R>> kotlin.collections.Map<out K, V>.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.collections.Map.Entry<K, V>) -> kotlin.collections.Iterable<R>): C
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "flatMapSequenceTo") public inline fun </*0*/ K, /*1*/ V, /*2*/ R, /*3*/ C : kotlin.collections.MutableCollection<in R>> kotlin.collections.Map<out K, V>.flatMapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.collections.Map.Entry<K, V>) -> kotlin.sequences.Sequence<R>): C
public fun </*0*/ T> kotlin.Array<out kotlin.Array<out T>>.flatten(): kotlin.collections.List<T>
public fun </*0*/ T> kotlin.collections.Iterable<kotlin.collections.Iterable<T>>.flatten(): kotlin.collections.List<T>
public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, T) -> R): R
public inline fun </*0*/ R> kotlin.BooleanArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Boolean) -> R): R
public inline fun </*0*/ R> kotlin.ByteArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Byte) -> R): R
public inline fun </*0*/ R> kotlin.CharArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Char) -> R): R
public inline fun </*0*/ R> kotlin.DoubleArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Double) -> R): R
public inline fun </*0*/ R> kotlin.FloatArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Float) -> R): R
public inline fun </*0*/ R> kotlin.IntArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Int) -> R): R
public inline fun </*0*/ R> kotlin.LongArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Long) -> R): R
public inline fun </*0*/ R> kotlin.ShortArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Short) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UByte) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UInt) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.ULong) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UShort) -> R): R
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ T, /*1*/ K, /*2*/ R> kotlin.collections.Grouping<T, K>.fold(/*0*/ initialValueSelector: (key: K, element: T) -> R, /*1*/ operation: (key: K, accumulator: R, element: T) -> R): kotlin.collections.Map<K, R>
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ T, /*1*/ K, /*2*/ R> kotlin.collections.Grouping<T, K>.fold(/*0*/ initialValue: R, /*1*/ operation: (accumulator: R, element: T) -> R): kotlin.collections.Map<K, R>
public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.fold(/*0*/ initial: R, /*1*/ operation: (acc: R, T) -> R): R
public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, T) -> R): R
public inline fun </*0*/ R> kotlin.BooleanArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Boolean) -> R): R
public inline fun </*0*/ R> kotlin.ByteArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Byte) -> R): R
public inline fun </*0*/ R> kotlin.CharArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Char) -> R): R
public inline fun </*0*/ R> kotlin.DoubleArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Double) -> R): R
public inline fun </*0*/ R> kotlin.FloatArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Float) -> R): R
public inline fun </*0*/ R> kotlin.IntArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Int) -> R): R
public inline fun </*0*/ R> kotlin.LongArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Long) -> R): R
public inline fun </*0*/ R> kotlin.ShortArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Short) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UByte) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UInt) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.ULong) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UShort) -> R): R
public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.foldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, T) -> R): R
public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.foldRight(/*0*/ initial: R, /*1*/ operation: (T, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.BooleanArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.Boolean, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.ByteArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.Byte, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.CharArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.Char, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.DoubleArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.Double, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.FloatArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.Float, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.IntArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.Int, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.LongArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.Long, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.ShortArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.Short, acc: R) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.UByte, acc: R) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.UInt, acc: R) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.ULong, acc: R) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.foldRight(/*0*/ initial: R, /*1*/ operation: (kotlin.UShort, acc: R) -> R): R
public inline fun </*0*/ T, /*1*/ R> kotlin.collections.List<T>.foldRight(/*0*/ initial: R, /*1*/ operation: (T, acc: R) -> R): R
public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, T, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.BooleanArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.Boolean, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.ByteArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.Byte, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.CharArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.Char, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.DoubleArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.Double, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.FloatArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.Float, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.IntArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.Int, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.LongArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.Long, acc: R) -> R): R
public inline fun </*0*/ R> kotlin.ShortArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.Short, acc: R) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.UByte, acc: R) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.UInt, acc: R) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.ULong, acc: R) -> R): R
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, kotlin.UShort, acc: R) -> R): R
public inline fun </*0*/ T, /*1*/ R> kotlin.collections.List<T>.foldRightIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, T, acc: R) -> R): R
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ T, /*1*/ K, /*2*/ R, /*3*/ M : kotlin.collections.MutableMap<in K, R>> kotlin.collections.Grouping<T, K>.foldTo(/*0*/ destination: M, /*1*/ initialValueSelector: (key: K, element: T) -> R, /*2*/ operation: (key: K, accumulator: R, element: T) -> R): M
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ T, /*1*/ K, /*2*/ R, /*3*/ M : kotlin.collections.MutableMap<in K, R>> kotlin.collections.Grouping<T, K>.foldTo(/*0*/ destination: M, /*1*/ initialValue: R, /*2*/ operation: (accumulator: R, element: T) -> R): M
public inline fun </*0*/ T> kotlin.Array<out T>.forEach(/*0*/ action: (T) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.BooleanArray.forEach(/*0*/ action: (kotlin.Boolean) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.ByteArray.forEach(/*0*/ action: (kotlin.Byte) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.CharArray.forEach(/*0*/ action: (kotlin.Char) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.DoubleArray.forEach(/*0*/ action: (kotlin.Double) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.FloatArray.forEach(/*0*/ action: (kotlin.Float) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.IntArray.forEach(/*0*/ action: (kotlin.Int) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.LongArray.forEach(/*0*/ action: (kotlin.Long) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.ShortArray.forEach(/*0*/ action: (kotlin.Short) -> kotlin.Unit): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.forEach(/*0*/ action: (kotlin.UByte) -> kotlin.Unit): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.forEach(/*0*/ action: (kotlin.UInt) -> kotlin.Unit): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.forEach(/*0*/ action: (kotlin.ULong) -> kotlin.Unit): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.forEach(/*0*/ action: (kotlin.UShort) -> kotlin.Unit): kotlin.Unit
@kotlin.internal.HidesMembers public inline fun </*0*/ T> kotlin.collections.Iterable<T>.forEach(/*0*/ action: (T) -> kotlin.Unit): kotlin.Unit
public inline fun </*0*/ T> kotlin.collections.Iterator<T>.forEach(/*0*/ operation: (T) -> kotlin.Unit): kotlin.Unit
@kotlin.internal.HidesMembers public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.forEach(/*0*/ action: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Unit): kotlin.Unit
public inline fun </*0*/ T> kotlin.Array<out T>.forEachIndexed(/*0*/ action: (index: kotlin.Int, T) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.BooleanArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Boolean) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.ByteArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Byte) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.CharArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Char) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.DoubleArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Double) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.FloatArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Float) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.IntArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Int) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.LongArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Long) -> kotlin.Unit): kotlin.Unit
public inline fun kotlin.ShortArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Short) -> kotlin.Unit): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.UByte) -> kotlin.Unit): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.UInt) -> kotlin.Unit): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.ULong) -> kotlin.Unit): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.forEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.UShort) -> kotlin.Unit): kotlin.Unit
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.forEachIndexed(/*0*/ action: (index: kotlin.Int, T) -> kotlin.Unit): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ @kotlin.internal.OnlyInputTypes K, /*1*/ V> kotlin.collections.Map<out K, V>.get(/*0*/ key: K): V?
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> T): T
@kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Boolean): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Byte): kotlin.Byte
@kotlin.internal.InlineOnly public inline fun kotlin.CharArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Char): kotlin.Char
@kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Double): kotlin.Double
@kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Float): kotlin.Float
@kotlin.internal.InlineOnly public inline fun kotlin.IntArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Int): kotlin.Int
@kotlin.internal.InlineOnly public inline fun kotlin.LongArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Long): kotlin.Long
@kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.Short): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.UByte): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> kotlin.UShort): kotlin.UShort
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.List<T>.getOrElse(/*0*/ index: kotlin.Int, /*1*/ defaultValue: (kotlin.Int) -> T): T
@kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<K, V>.getOrElse(/*0*/ key: K, /*1*/ defaultValue: () -> V): V
public fun </*0*/ T> kotlin.Array<out T>.getOrNull(/*0*/ index: kotlin.Int): T?
public fun kotlin.BooleanArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.Boolean?
public fun kotlin.ByteArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.Byte?
public fun kotlin.CharArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.Char?
public fun kotlin.DoubleArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.Double?
public fun kotlin.FloatArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.Float?
public fun kotlin.IntArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.Int?
public fun kotlin.LongArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.Long?
public fun kotlin.ShortArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.getOrNull(/*0*/ index: kotlin.Int): kotlin.UShort?
public fun </*0*/ T> kotlin.collections.List<T>.getOrNull(/*0*/ index: kotlin.Int): T?
public inline fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<K, V>.getOrPut(/*0*/ key: K, /*1*/ defaultValue: () -> V): V
@kotlin.SinceKotlin(version = "1.1") public fun </*0*/ K, /*1*/ V> kotlin.collections.Map<K, V>.getValue(/*0*/ key: K): V
@kotlin.internal.InlineOnly public inline operator fun </*0*/ V, /*1*/ V1 : V> kotlin.collections.Map<in kotlin.String, V>.getValue(/*0*/ thisRef: kotlin.Any?, /*1*/ property: kotlin.reflect.KProperty<*>): V1
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use getValue() with two type parameters instead") @kotlin.jvm.JvmName(name = "getVarContravariant") @kotlin.internal.LowPriorityInOverloadResolution @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.collections.MutableMap<in kotlin.String, in V>.getValue(/*0*/ thisRef: kotlin.Any?, /*1*/ property: kotlin.reflect.KProperty<*>): V
@kotlin.jvm.JvmName(name = "getVar") @kotlin.internal.InlineOnly public inline operator fun </*0*/ V, /*1*/ V1 : V> kotlin.collections.MutableMap<in kotlin.String, out V>.getValue(/*0*/ thisRef: kotlin.Any?, /*1*/ property: kotlin.reflect.KProperty<*>): V1
public inline fun </*0*/ T, /*1*/ K> kotlin.Array<out T>.groupBy(/*0*/ keySelector: (T) -> K): kotlin.collections.Map<K, kotlin.collections.List<T>>
public inline fun </*0*/ T, /*1*/ K, /*2*/ V> kotlin.Array<out T>.groupBy(/*0*/ keySelector: (T) -> K, /*1*/ valueTransform: (T) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
public inline fun </*0*/ K> kotlin.BooleanArray.groupBy(/*0*/ keySelector: (kotlin.Boolean) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.Boolean>>
public inline fun </*0*/ K, /*1*/ V> kotlin.BooleanArray.groupBy(/*0*/ keySelector: (kotlin.Boolean) -> K, /*1*/ valueTransform: (kotlin.Boolean) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
public inline fun </*0*/ K> kotlin.ByteArray.groupBy(/*0*/ keySelector: (kotlin.Byte) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.Byte>>
public inline fun </*0*/ K, /*1*/ V> kotlin.ByteArray.groupBy(/*0*/ keySelector: (kotlin.Byte) -> K, /*1*/ valueTransform: (kotlin.Byte) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
public inline fun </*0*/ K> kotlin.CharArray.groupBy(/*0*/ keySelector: (kotlin.Char) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.Char>>
public inline fun </*0*/ K, /*1*/ V> kotlin.CharArray.groupBy(/*0*/ keySelector: (kotlin.Char) -> K, /*1*/ valueTransform: (kotlin.Char) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
public inline fun </*0*/ K> kotlin.DoubleArray.groupBy(/*0*/ keySelector: (kotlin.Double) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.Double>>
public inline fun </*0*/ K, /*1*/ V> kotlin.DoubleArray.groupBy(/*0*/ keySelector: (kotlin.Double) -> K, /*1*/ valueTransform: (kotlin.Double) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
public inline fun </*0*/ K> kotlin.FloatArray.groupBy(/*0*/ keySelector: (kotlin.Float) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.Float>>
public inline fun </*0*/ K, /*1*/ V> kotlin.FloatArray.groupBy(/*0*/ keySelector: (kotlin.Float) -> K, /*1*/ valueTransform: (kotlin.Float) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
public inline fun </*0*/ K> kotlin.IntArray.groupBy(/*0*/ keySelector: (kotlin.Int) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.Int>>
public inline fun </*0*/ K, /*1*/ V> kotlin.IntArray.groupBy(/*0*/ keySelector: (kotlin.Int) -> K, /*1*/ valueTransform: (kotlin.Int) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
public inline fun </*0*/ K> kotlin.LongArray.groupBy(/*0*/ keySelector: (kotlin.Long) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.Long>>
public inline fun </*0*/ K, /*1*/ V> kotlin.LongArray.groupBy(/*0*/ keySelector: (kotlin.Long) -> K, /*1*/ valueTransform: (kotlin.Long) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
public inline fun </*0*/ K> kotlin.ShortArray.groupBy(/*0*/ keySelector: (kotlin.Short) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.Short>>
public inline fun </*0*/ K, /*1*/ V> kotlin.ShortArray.groupBy(/*0*/ keySelector: (kotlin.Short) -> K, /*1*/ valueTransform: (kotlin.Short) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K> kotlin.UByteArray.groupBy(/*0*/ keySelector: (kotlin.UByte) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.UByte>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.UByteArray.groupBy(/*0*/ keySelector: (kotlin.UByte) -> K, /*1*/ valueTransform: (kotlin.UByte) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K> kotlin.UIntArray.groupBy(/*0*/ keySelector: (kotlin.UInt) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.UInt>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.UIntArray.groupBy(/*0*/ keySelector: (kotlin.UInt) -> K, /*1*/ valueTransform: (kotlin.UInt) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K> kotlin.ULongArray.groupBy(/*0*/ keySelector: (kotlin.ULong) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.ULong>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.ULongArray.groupBy(/*0*/ keySelector: (kotlin.ULong) -> K, /*1*/ valueTransform: (kotlin.ULong) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K> kotlin.UShortArray.groupBy(/*0*/ keySelector: (kotlin.UShort) -> K): kotlin.collections.Map<K, kotlin.collections.List<kotlin.UShort>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.UShortArray.groupBy(/*0*/ keySelector: (kotlin.UShort) -> K, /*1*/ valueTransform: (kotlin.UShort) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
public inline fun </*0*/ T, /*1*/ K> kotlin.collections.Iterable<T>.groupBy(/*0*/ keySelector: (T) -> K): kotlin.collections.Map<K, kotlin.collections.List<T>>
public inline fun </*0*/ T, /*1*/ K, /*2*/ V> kotlin.collections.Iterable<T>.groupBy(/*0*/ keySelector: (T) -> K, /*1*/ valueTransform: (T) -> V): kotlin.collections.Map<K, kotlin.collections.List<V>>
public inline fun </*0*/ T, /*1*/ K, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<T>>> kotlin.Array<out T>.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (T) -> K): M
public inline fun </*0*/ T, /*1*/ K, /*2*/ V, /*3*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.Array<out T>.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (T) -> K, /*2*/ valueTransform: (T) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.Boolean>>> kotlin.BooleanArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Boolean) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.BooleanArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Boolean) -> K, /*2*/ valueTransform: (kotlin.Boolean) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.Byte>>> kotlin.ByteArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Byte) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.ByteArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Byte) -> K, /*2*/ valueTransform: (kotlin.Byte) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.Char>>> kotlin.CharArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Char) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.CharArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Char) -> K, /*2*/ valueTransform: (kotlin.Char) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.Double>>> kotlin.DoubleArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Double) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.DoubleArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Double) -> K, /*2*/ valueTransform: (kotlin.Double) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.Float>>> kotlin.FloatArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Float) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.FloatArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Float) -> K, /*2*/ valueTransform: (kotlin.Float) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.Int>>> kotlin.IntArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Int) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.IntArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Int) -> K, /*2*/ valueTransform: (kotlin.Int) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.Long>>> kotlin.LongArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Long) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.LongArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Long) -> K, /*2*/ valueTransform: (kotlin.Long) -> V): M
public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.Short>>> kotlin.ShortArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Short) -> K): M
public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.ShortArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.Short) -> K, /*2*/ valueTransform: (kotlin.Short) -> V): M
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.UByte>>> kotlin.UByteArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.UByte) -> K): M
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.UByteArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.UByte) -> K, /*2*/ valueTransform: (kotlin.UByte) -> V): M
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.UInt>>> kotlin.UIntArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.UInt) -> K): M
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.UIntArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.UInt) -> K, /*2*/ valueTransform: (kotlin.UInt) -> V): M
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.ULong>>> kotlin.ULongArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.ULong) -> K): M
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.ULongArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.ULong) -> K, /*2*/ valueTransform: (kotlin.ULong) -> V): M
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<kotlin.UShort>>> kotlin.UShortArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.UShort) -> K): M
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.UShortArray.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (kotlin.UShort) -> K, /*2*/ valueTransform: (kotlin.UShort) -> V): M
public inline fun </*0*/ T, /*1*/ K, /*2*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<T>>> kotlin.collections.Iterable<T>.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (T) -> K): M
public inline fun </*0*/ T, /*1*/ K, /*2*/ V, /*3*/ M : kotlin.collections.MutableMap<in K, kotlin.collections.MutableList<V>>> kotlin.collections.Iterable<T>.groupByTo(/*0*/ destination: M, /*1*/ keySelector: (T) -> K, /*2*/ valueTransform: (T) -> V): M
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ T, /*1*/ K> kotlin.Array<out T>.groupingBy(/*0*/ crossinline keySelector: (T) -> K): kotlin.collections.Grouping<T, K>
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ T, /*1*/ K> kotlin.collections.Iterable<T>.groupingBy(/*0*/ crossinline keySelector: (T) -> K): kotlin.collections.Grouping<T, K>
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.Array<*>, /*1*/ R> C.ifEmpty(/*0*/ defaultValue: () -> R): R where C : R
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun </*0*/ C : kotlin.collections.Collection<*>, /*1*/ R> C.ifEmpty(/*0*/ defaultValue: () -> R): R where C : R
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun </*0*/ M : kotlin.collections.Map<*, *>, /*1*/ R> M.ifEmpty(/*0*/ defaultValue: () -> R): R where M : R
public fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.Array<out T>.indexOf(/*0*/ element: T): kotlin.Int
public fun kotlin.BooleanArray.indexOf(/*0*/ element: kotlin.Boolean): kotlin.Int
public fun kotlin.ByteArray.indexOf(/*0*/ element: kotlin.Byte): kotlin.Int
public fun kotlin.CharArray.indexOf(/*0*/ element: kotlin.Char): kotlin.Int
public fun kotlin.DoubleArray.indexOf(/*0*/ element: kotlin.Double): kotlin.Int
public fun kotlin.FloatArray.indexOf(/*0*/ element: kotlin.Float): kotlin.Int
public fun kotlin.IntArray.indexOf(/*0*/ element: kotlin.Int): kotlin.Int
public fun kotlin.LongArray.indexOf(/*0*/ element: kotlin.Long): kotlin.Int
public fun kotlin.ShortArray.indexOf(/*0*/ element: kotlin.Short): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.indexOf(/*0*/ element: kotlin.UByte): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.indexOf(/*0*/ element: kotlin.UInt): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.indexOf(/*0*/ element: kotlin.ULong): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.indexOf(/*0*/ element: kotlin.UShort): kotlin.Int
public fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.collections.Iterable<T>.indexOf(/*0*/ element: T): kotlin.Int
public fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.collections.List<T>.indexOf(/*0*/ element: T): kotlin.Int
public inline fun </*0*/ T> kotlin.Array<out T>.indexOfFirst(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.BooleanArray.indexOfFirst(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.ByteArray.indexOfFirst(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.CharArray.indexOfFirst(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.DoubleArray.indexOfFirst(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.FloatArray.indexOfFirst(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.IntArray.indexOfFirst(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.LongArray.indexOfFirst(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.ShortArray.indexOfFirst(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.indexOfFirst(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.indexOfFirst(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.indexOfFirst(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.indexOfFirst(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.Int
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.indexOfFirst(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Int
public inline fun </*0*/ T> kotlin.collections.List<T>.indexOfFirst(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Int
public inline fun </*0*/ T> kotlin.Array<out T>.indexOfLast(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.BooleanArray.indexOfLast(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.ByteArray.indexOfLast(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.CharArray.indexOfLast(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.DoubleArray.indexOfLast(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.FloatArray.indexOfLast(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.IntArray.indexOfLast(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.LongArray.indexOfLast(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Int
public inline fun kotlin.ShortArray.indexOfLast(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.indexOfLast(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.indexOfLast(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.indexOfLast(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.indexOfLast(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.Int
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.indexOfLast(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Int
public inline fun </*0*/ T> kotlin.collections.List<T>.indexOfLast(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Int
public infix fun </*0*/ T> kotlin.Array<out T>.intersect(/*0*/ other: kotlin.collections.Iterable<T>): kotlin.collections.Set<T>
public infix fun kotlin.BooleanArray.intersect(/*0*/ other: kotlin.collections.Iterable<kotlin.Boolean>): kotlin.collections.Set<kotlin.Boolean>
public infix fun kotlin.ByteArray.intersect(/*0*/ other: kotlin.collections.Iterable<kotlin.Byte>): kotlin.collections.Set<kotlin.Byte>
public infix fun kotlin.CharArray.intersect(/*0*/ other: kotlin.collections.Iterable<kotlin.Char>): kotlin.collections.Set<kotlin.Char>
public infix fun kotlin.DoubleArray.intersect(/*0*/ other: kotlin.collections.Iterable<kotlin.Double>): kotlin.collections.Set<kotlin.Double>
public infix fun kotlin.FloatArray.intersect(/*0*/ other: kotlin.collections.Iterable<kotlin.Float>): kotlin.collections.Set<kotlin.Float>
public infix fun kotlin.IntArray.intersect(/*0*/ other: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.Set<kotlin.Int>
public infix fun kotlin.LongArray.intersect(/*0*/ other: kotlin.collections.Iterable<kotlin.Long>): kotlin.collections.Set<kotlin.Long>
public infix fun kotlin.ShortArray.intersect(/*0*/ other: kotlin.collections.Iterable<kotlin.Short>): kotlin.collections.Set<kotlin.Short>
public infix fun </*0*/ T> kotlin.collections.Iterable<T>.intersect(/*0*/ other: kotlin.collections.Iterable<T>): kotlin.collections.Set<T>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.isEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.isEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.isEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.CharArray.isEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.isEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.isEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.IntArray.isEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.LongArray.isEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.isEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.isNotEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.isNotEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.isNotEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.CharArray.isNotEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.isNotEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.isNotEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.IntArray.isNotEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.LongArray.isNotEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.isNotEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Collection<T>.isNotEmpty(): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.isNotEmpty(): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.Array<*>?.isNullOrEmpty(): kotlin.Boolean
    Returns(FALSE) -> <this> != null

@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Collection<T>?.isNullOrEmpty(): kotlin.Boolean
    Returns(FALSE) -> <this> != null

@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>?.isNullOrEmpty(): kotlin.Boolean
    Returns(FALSE) -> <this> != null

@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.Iterator<T>.iterator(): kotlin.collections.Iterator<T>
@kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.iterator(): kotlin.collections.Iterator<kotlin.collections.Map.Entry<K, V>>
@kotlin.jvm.JvmName(name = "mutableIterator") @kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<K, V>.iterator(): kotlin.collections.MutableIterator<kotlin.collections.MutableMap.MutableEntry<K, V>>
public fun </*0*/ T, /*1*/ A : kotlin.text.Appendable> kotlin.Array<out T>.joinTo(/*0*/ buffer: A, /*1*/ separator: kotlin.CharSequence = ..., /*2*/ prefix: kotlin.CharSequence = ..., /*3*/ postfix: kotlin.CharSequence = ..., /*4*/ limit: kotlin.Int = ..., /*5*/ truncated: kotlin.CharSequence = ..., /*6*/ transform: ((T) -> kotlin.CharSequence)? = ...): A
public fun </*0*/ A : kotlin.text.Appendable> kotlin.BooleanArray.joinTo(/*0*/ buffer: A, /*1*/ separator: kotlin.CharSequence = ..., /*2*/ prefix: kotlin.CharSequence = ..., /*3*/ postfix: kotlin.CharSequence = ..., /*4*/ limit: kotlin.Int = ..., /*5*/ truncated: kotlin.CharSequence = ..., /*6*/ transform: ((kotlin.Boolean) -> kotlin.CharSequence)? = ...): A
public fun </*0*/ A : kotlin.text.Appendable> kotlin.ByteArray.joinTo(/*0*/ buffer: A, /*1*/ separator: kotlin.CharSequence = ..., /*2*/ prefix: kotlin.CharSequence = ..., /*3*/ postfix: kotlin.CharSequence = ..., /*4*/ limit: kotlin.Int = ..., /*5*/ truncated: kotlin.CharSequence = ..., /*6*/ transform: ((kotlin.Byte) -> kotlin.CharSequence)? = ...): A
public fun </*0*/ A : kotlin.text.Appendable> kotlin.CharArray.joinTo(/*0*/ buffer: A, /*1*/ separator: kotlin.CharSequence = ..., /*2*/ prefix: kotlin.CharSequence = ..., /*3*/ postfix: kotlin.CharSequence = ..., /*4*/ limit: kotlin.Int = ..., /*5*/ truncated: kotlin.CharSequence = ..., /*6*/ transform: ((kotlin.Char) -> kotlin.CharSequence)? = ...): A
public fun </*0*/ A : kotlin.text.Appendable> kotlin.DoubleArray.joinTo(/*0*/ buffer: A, /*1*/ separator: kotlin.CharSequence = ..., /*2*/ prefix: kotlin.CharSequence = ..., /*3*/ postfix: kotlin.CharSequence = ..., /*4*/ limit: kotlin.Int = ..., /*5*/ truncated: kotlin.CharSequence = ..., /*6*/ transform: ((kotlin.Double) -> kotlin.CharSequence)? = ...): A
public fun </*0*/ A : kotlin.text.Appendable> kotlin.FloatArray.joinTo(/*0*/ buffer: A, /*1*/ separator: kotlin.CharSequence = ..., /*2*/ prefix: kotlin.CharSequence = ..., /*3*/ postfix: kotlin.CharSequence = ..., /*4*/ limit: kotlin.Int = ..., /*5*/ truncated: kotlin.CharSequence = ..., /*6*/ transform: ((kotlin.Float) -> kotlin.CharSequence)? = ...): A
public fun </*0*/ A : kotlin.text.Appendable> kotlin.IntArray.joinTo(/*0*/ buffer: A, /*1*/ separator: kotlin.CharSequence = ..., /*2*/ prefix: kotlin.CharSequence = ..., /*3*/ postfix: kotlin.CharSequence = ..., /*4*/ limit: kotlin.Int = ..., /*5*/ truncated: kotlin.CharSequence = ..., /*6*/ transform: ((kotlin.Int) -> kotlin.CharSequence)? = ...): A
public fun </*0*/ A : kotlin.text.Appendable> kotlin.LongArray.joinTo(/*0*/ buffer: A, /*1*/ separator: kotlin.CharSequence = ..., /*2*/ prefix: kotlin.CharSequence = ..., /*3*/ postfix: kotlin.CharSequence = ..., /*4*/ limit: kotlin.Int = ..., /*5*/ truncated: kotlin.CharSequence = ..., /*6*/ transform: ((kotlin.Long) -> kotlin.CharSequence)? = ...): A
public fun </*0*/ A : kotlin.text.Appendable> kotlin.ShortArray.joinTo(/*0*/ buffer: A, /*1*/ separator: kotlin.CharSequence = ..., /*2*/ prefix: kotlin.CharSequence = ..., /*3*/ postfix: kotlin.CharSequence = ..., /*4*/ limit: kotlin.Int = ..., /*5*/ truncated: kotlin.CharSequence = ..., /*6*/ transform: ((kotlin.Short) -> kotlin.CharSequence)? = ...): A
public fun </*0*/ T, /*1*/ A : kotlin.text.Appendable> kotlin.collections.Iterable<T>.joinTo(/*0*/ buffer: A, /*1*/ separator: kotlin.CharSequence = ..., /*2*/ prefix: kotlin.CharSequence = ..., /*3*/ postfix: kotlin.CharSequence = ..., /*4*/ limit: kotlin.Int = ..., /*5*/ truncated: kotlin.CharSequence = ..., /*6*/ transform: ((T) -> kotlin.CharSequence)? = ...): A
public fun </*0*/ T> kotlin.Array<out T>.joinToString(/*0*/ separator: kotlin.CharSequence = ..., /*1*/ prefix: kotlin.CharSequence = ..., /*2*/ postfix: kotlin.CharSequence = ..., /*3*/ limit: kotlin.Int = ..., /*4*/ truncated: kotlin.CharSequence = ..., /*5*/ transform: ((T) -> kotlin.CharSequence)? = ...): kotlin.String
public fun kotlin.BooleanArray.joinToString(/*0*/ separator: kotlin.CharSequence = ..., /*1*/ prefix: kotlin.CharSequence = ..., /*2*/ postfix: kotlin.CharSequence = ..., /*3*/ limit: kotlin.Int = ..., /*4*/ truncated: kotlin.CharSequence = ..., /*5*/ transform: ((kotlin.Boolean) -> kotlin.CharSequence)? = ...): kotlin.String
public fun kotlin.ByteArray.joinToString(/*0*/ separator: kotlin.CharSequence = ..., /*1*/ prefix: kotlin.CharSequence = ..., /*2*/ postfix: kotlin.CharSequence = ..., /*3*/ limit: kotlin.Int = ..., /*4*/ truncated: kotlin.CharSequence = ..., /*5*/ transform: ((kotlin.Byte) -> kotlin.CharSequence)? = ...): kotlin.String
public fun kotlin.CharArray.joinToString(/*0*/ separator: kotlin.CharSequence = ..., /*1*/ prefix: kotlin.CharSequence = ..., /*2*/ postfix: kotlin.CharSequence = ..., /*3*/ limit: kotlin.Int = ..., /*4*/ truncated: kotlin.CharSequence = ..., /*5*/ transform: ((kotlin.Char) -> kotlin.CharSequence)? = ...): kotlin.String
public fun kotlin.DoubleArray.joinToString(/*0*/ separator: kotlin.CharSequence = ..., /*1*/ prefix: kotlin.CharSequence = ..., /*2*/ postfix: kotlin.CharSequence = ..., /*3*/ limit: kotlin.Int = ..., /*4*/ truncated: kotlin.CharSequence = ..., /*5*/ transform: ((kotlin.Double) -> kotlin.CharSequence)? = ...): kotlin.String
public fun kotlin.FloatArray.joinToString(/*0*/ separator: kotlin.CharSequence = ..., /*1*/ prefix: kotlin.CharSequence = ..., /*2*/ postfix: kotlin.CharSequence = ..., /*3*/ limit: kotlin.Int = ..., /*4*/ truncated: kotlin.CharSequence = ..., /*5*/ transform: ((kotlin.Float) -> kotlin.CharSequence)? = ...): kotlin.String
public fun kotlin.IntArray.joinToString(/*0*/ separator: kotlin.CharSequence = ..., /*1*/ prefix: kotlin.CharSequence = ..., /*2*/ postfix: kotlin.CharSequence = ..., /*3*/ limit: kotlin.Int = ..., /*4*/ truncated: kotlin.CharSequence = ..., /*5*/ transform: ((kotlin.Int) -> kotlin.CharSequence)? = ...): kotlin.String
public fun kotlin.LongArray.joinToString(/*0*/ separator: kotlin.CharSequence = ..., /*1*/ prefix: kotlin.CharSequence = ..., /*2*/ postfix: kotlin.CharSequence = ..., /*3*/ limit: kotlin.Int = ..., /*4*/ truncated: kotlin.CharSequence = ..., /*5*/ transform: ((kotlin.Long) -> kotlin.CharSequence)? = ...): kotlin.String
public fun kotlin.ShortArray.joinToString(/*0*/ separator: kotlin.CharSequence = ..., /*1*/ prefix: kotlin.CharSequence = ..., /*2*/ postfix: kotlin.CharSequence = ..., /*3*/ limit: kotlin.Int = ..., /*4*/ truncated: kotlin.CharSequence = ..., /*5*/ transform: ((kotlin.Short) -> kotlin.CharSequence)? = ...): kotlin.String
public fun </*0*/ T> kotlin.collections.Iterable<T>.joinToString(/*0*/ separator: kotlin.CharSequence = ..., /*1*/ prefix: kotlin.CharSequence = ..., /*2*/ postfix: kotlin.CharSequence = ..., /*3*/ limit: kotlin.Int = ..., /*4*/ truncated: kotlin.CharSequence = ..., /*5*/ transform: ((T) -> kotlin.CharSequence)? = ...): kotlin.String
public fun </*0*/ T> kotlin.Array<out T>.last(): T
public inline fun </*0*/ T> kotlin.Array<out T>.last(/*0*/ predicate: (T) -> kotlin.Boolean): T
public fun kotlin.BooleanArray.last(): kotlin.Boolean
public inline fun kotlin.BooleanArray.last(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.ByteArray.last(): kotlin.Byte
public inline fun kotlin.ByteArray.last(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Byte
public fun kotlin.CharArray.last(): kotlin.Char
public inline fun kotlin.CharArray.last(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Char
public fun kotlin.DoubleArray.last(): kotlin.Double
public inline fun kotlin.DoubleArray.last(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Double
public fun kotlin.FloatArray.last(): kotlin.Float
public inline fun kotlin.FloatArray.last(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Float
public fun kotlin.IntArray.last(): kotlin.Int
public inline fun kotlin.IntArray.last(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Int
public fun kotlin.LongArray.last(): kotlin.Long
public inline fun kotlin.LongArray.last(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Long
public fun kotlin.ShortArray.last(): kotlin.Short
public inline fun kotlin.ShortArray.last(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.last(): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.last(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.last(): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.last(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.last(): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.last(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.last(): kotlin.UShort
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.last(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.UShort
public fun </*0*/ T> kotlin.collections.Iterable<T>.last(): T
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.last(/*0*/ predicate: (T) -> kotlin.Boolean): T
public fun </*0*/ T> kotlin.collections.List<T>.last(): T
public inline fun </*0*/ T> kotlin.collections.List<T>.last(/*0*/ predicate: (T) -> kotlin.Boolean): T
public fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.Array<out T>.lastIndexOf(/*0*/ element: T): kotlin.Int
public fun kotlin.BooleanArray.lastIndexOf(/*0*/ element: kotlin.Boolean): kotlin.Int
public fun kotlin.ByteArray.lastIndexOf(/*0*/ element: kotlin.Byte): kotlin.Int
public fun kotlin.CharArray.lastIndexOf(/*0*/ element: kotlin.Char): kotlin.Int
public fun kotlin.DoubleArray.lastIndexOf(/*0*/ element: kotlin.Double): kotlin.Int
public fun kotlin.FloatArray.lastIndexOf(/*0*/ element: kotlin.Float): kotlin.Int
public fun kotlin.IntArray.lastIndexOf(/*0*/ element: kotlin.Int): kotlin.Int
public fun kotlin.LongArray.lastIndexOf(/*0*/ element: kotlin.Long): kotlin.Int
public fun kotlin.ShortArray.lastIndexOf(/*0*/ element: kotlin.Short): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.lastIndexOf(/*0*/ element: kotlin.UByte): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.lastIndexOf(/*0*/ element: kotlin.UInt): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.lastIndexOf(/*0*/ element: kotlin.ULong): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.lastIndexOf(/*0*/ element: kotlin.UShort): kotlin.Int
public fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.collections.Iterable<T>.lastIndexOf(/*0*/ element: T): kotlin.Int
public fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.collections.List<T>.lastIndexOf(/*0*/ element: T): kotlin.Int
public fun </*0*/ T> kotlin.Array<out T>.lastOrNull(): T?
public inline fun </*0*/ T> kotlin.Array<out T>.lastOrNull(/*0*/ predicate: (T) -> kotlin.Boolean): T?
public fun kotlin.BooleanArray.lastOrNull(): kotlin.Boolean?
public inline fun kotlin.BooleanArray.lastOrNull(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
public fun kotlin.ByteArray.lastOrNull(): kotlin.Byte?
public inline fun kotlin.ByteArray.lastOrNull(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Byte?
public fun kotlin.CharArray.lastOrNull(): kotlin.Char?
public inline fun kotlin.CharArray.lastOrNull(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Char?
public fun kotlin.DoubleArray.lastOrNull(): kotlin.Double?
public inline fun kotlin.DoubleArray.lastOrNull(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Double?
public fun kotlin.FloatArray.lastOrNull(): kotlin.Float?
public inline fun kotlin.FloatArray.lastOrNull(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Float?
public fun kotlin.IntArray.lastOrNull(): kotlin.Int?
public inline fun kotlin.IntArray.lastOrNull(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Int?
public fun kotlin.LongArray.lastOrNull(): kotlin.Long?
public inline fun kotlin.LongArray.lastOrNull(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Long?
public fun kotlin.ShortArray.lastOrNull(): kotlin.Short?
public inline fun kotlin.ShortArray.lastOrNull(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.lastOrNull(): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.lastOrNull(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.lastOrNull(): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.lastOrNull(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.lastOrNull(): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.lastOrNull(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.lastOrNull(): kotlin.UShort?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.lastOrNull(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.UShort?
public fun </*0*/ T> kotlin.collections.Iterable<T>.lastOrNull(): T?
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.lastOrNull(/*0*/ predicate: (T) -> kotlin.Boolean): T?
public fun </*0*/ T> kotlin.collections.List<T>.lastOrNull(): T?
public inline fun </*0*/ T> kotlin.collections.List<T>.lastOrNull(/*0*/ predicate: (T) -> kotlin.Boolean): T?
public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.map(/*0*/ transform: (T) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.BooleanArray.map(/*0*/ transform: (kotlin.Boolean) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.ByteArray.map(/*0*/ transform: (kotlin.Byte) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.CharArray.map(/*0*/ transform: (kotlin.Char) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.DoubleArray.map(/*0*/ transform: (kotlin.Double) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.FloatArray.map(/*0*/ transform: (kotlin.Float) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.IntArray.map(/*0*/ transform: (kotlin.Int) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.LongArray.map(/*0*/ transform: (kotlin.Long) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.ShortArray.map(/*0*/ transform: (kotlin.Short) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.map(/*0*/ transform: (kotlin.UByte) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.map(/*0*/ transform: (kotlin.UInt) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.map(/*0*/ transform: (kotlin.ULong) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.map(/*0*/ transform: (kotlin.UShort) -> R): kotlin.collections.List<R>
public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.map(/*0*/ transform: (T) -> R): kotlin.collections.List<R>
public inline fun </*0*/ K, /*1*/ V, /*2*/ R> kotlin.collections.Map<out K, V>.map(/*0*/ transform: (kotlin.collections.Map.Entry<K, V>) -> R): kotlin.collections.List<R>
public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.mapIndexed(/*0*/ transform: (index: kotlin.Int, T) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.BooleanArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.Boolean) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.ByteArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.Byte) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.CharArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.Char) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.DoubleArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.Double) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.FloatArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.Float) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.IntArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.Int) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.LongArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.Long) -> R): kotlin.collections.List<R>
public inline fun </*0*/ R> kotlin.ShortArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.Short) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.UByte) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.UInt) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.ULong) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.mapIndexed(/*0*/ transform: (index: kotlin.Int, kotlin.UShort) -> R): kotlin.collections.List<R>
public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.mapIndexed(/*0*/ transform: (index: kotlin.Int, T) -> R): kotlin.collections.List<R>
public inline fun </*0*/ T, /*1*/ R : kotlin.Any> kotlin.Array<out T>.mapIndexedNotNull(/*0*/ transform: (index: kotlin.Int, T) -> R?): kotlin.collections.List<R>
public inline fun </*0*/ T, /*1*/ R : kotlin.Any> kotlin.collections.Iterable<T>.mapIndexedNotNull(/*0*/ transform: (index: kotlin.Int, T) -> R?): kotlin.collections.List<R>
public inline fun </*0*/ T, /*1*/ R : kotlin.Any, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.Array<out T>.mapIndexedNotNullTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, T) -> R?): C
public inline fun </*0*/ T, /*1*/ R : kotlin.Any, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.collections.Iterable<T>.mapIndexedNotNullTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, T) -> R?): C
public inline fun </*0*/ T, /*1*/ R, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.Array<out T>.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, T) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.BooleanArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.Boolean) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.ByteArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.Byte) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.CharArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.Char) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.DoubleArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.Double) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.FloatArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.Float) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.IntArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.Int) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.LongArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.Long) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.ShortArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.Short) -> R): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.UByteArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.UByte) -> R): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.UIntArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.UInt) -> R): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.ULongArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.ULong) -> R): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.UShortArray.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, kotlin.UShort) -> R): C
public inline fun </*0*/ T, /*1*/ R, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.collections.Iterable<T>.mapIndexedTo(/*0*/ destination: C, /*1*/ transform: (index: kotlin.Int, T) -> R): C
public inline fun </*0*/ K, /*1*/ V, /*2*/ R> kotlin.collections.Map<out K, V>.mapKeys(/*0*/ transform: (kotlin.collections.Map.Entry<K, V>) -> R): kotlin.collections.Map<R, V>
public inline fun </*0*/ K, /*1*/ V, /*2*/ R, /*3*/ M : kotlin.collections.MutableMap<in R, in V>> kotlin.collections.Map<out K, V>.mapKeysTo(/*0*/ destination: M, /*1*/ transform: (kotlin.collections.Map.Entry<K, V>) -> R): M
public inline fun </*0*/ T, /*1*/ R : kotlin.Any> kotlin.Array<out T>.mapNotNull(/*0*/ transform: (T) -> R?): kotlin.collections.List<R>
public inline fun </*0*/ T, /*1*/ R : kotlin.Any> kotlin.collections.Iterable<T>.mapNotNull(/*0*/ transform: (T) -> R?): kotlin.collections.List<R>
public inline fun </*0*/ K, /*1*/ V, /*2*/ R : kotlin.Any> kotlin.collections.Map<out K, V>.mapNotNull(/*0*/ transform: (kotlin.collections.Map.Entry<K, V>) -> R?): kotlin.collections.List<R>
public inline fun </*0*/ T, /*1*/ R : kotlin.Any, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.Array<out T>.mapNotNullTo(/*0*/ destination: C, /*1*/ transform: (T) -> R?): C
public inline fun </*0*/ T, /*1*/ R : kotlin.Any, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.collections.Iterable<T>.mapNotNullTo(/*0*/ destination: C, /*1*/ transform: (T) -> R?): C
public inline fun </*0*/ K, /*1*/ V, /*2*/ R : kotlin.Any, /*3*/ C : kotlin.collections.MutableCollection<in R>> kotlin.collections.Map<out K, V>.mapNotNullTo(/*0*/ destination: C, /*1*/ transform: (kotlin.collections.Map.Entry<K, V>) -> R?): C
public inline fun </*0*/ T, /*1*/ R, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.Array<out T>.mapTo(/*0*/ destination: C, /*1*/ transform: (T) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.BooleanArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Boolean) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.ByteArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Byte) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.CharArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Char) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.DoubleArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Double) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.FloatArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Float) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.IntArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Int) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.LongArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Long) -> R): C
public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.ShortArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.Short) -> R): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.UByteArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.UByte) -> R): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.UIntArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.UInt) -> R): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.ULongArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.ULong) -> R): C
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ C : kotlin.collections.MutableCollection<in R>> kotlin.UShortArray.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.UShort) -> R): C
public inline fun </*0*/ T, /*1*/ R, /*2*/ C : kotlin.collections.MutableCollection<in R>> kotlin.collections.Iterable<T>.mapTo(/*0*/ destination: C, /*1*/ transform: (T) -> R): C
public inline fun </*0*/ K, /*1*/ V, /*2*/ R, /*3*/ C : kotlin.collections.MutableCollection<in R>> kotlin.collections.Map<out K, V>.mapTo(/*0*/ destination: C, /*1*/ transform: (kotlin.collections.Map.Entry<K, V>) -> R): C
public inline fun </*0*/ K, /*1*/ V, /*2*/ R> kotlin.collections.Map<out K, V>.mapValues(/*0*/ transform: (kotlin.collections.Map.Entry<K, V>) -> R): kotlin.collections.Map<K, R>
public inline fun </*0*/ K, /*1*/ V, /*2*/ R, /*3*/ M : kotlin.collections.MutableMap<in K, in R>> kotlin.collections.Map<out K, V>.mapValuesTo(/*0*/ destination: M, /*1*/ transform: (kotlin.collections.Map.Entry<K, V>) -> R): M
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.Array<out T>.max(): T?
@kotlin.SinceKotlin(version = "1.1") public fun kotlin.Array<out kotlin.Double>.max(): kotlin.Double?
@kotlin.SinceKotlin(version = "1.1") public fun kotlin.Array<out kotlin.Float>.max(): kotlin.Float?
public fun kotlin.ByteArray.max(): kotlin.Byte?
public fun kotlin.CharArray.max(): kotlin.Char?
public fun kotlin.DoubleArray.max(): kotlin.Double?
public fun kotlin.FloatArray.max(): kotlin.Float?
public fun kotlin.IntArray.max(): kotlin.Int?
public fun kotlin.LongArray.max(): kotlin.Long?
public fun kotlin.ShortArray.max(): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.max(): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.max(): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.max(): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.max(): kotlin.UShort?
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.collections.Iterable<T>.max(): T?
@kotlin.SinceKotlin(version = "1.1") public fun kotlin.collections.Iterable<kotlin.Double>.max(): kotlin.Double?
@kotlin.SinceKotlin(version = "1.1") public fun kotlin.collections.Iterable<kotlin.Float>.max(): kotlin.Float?
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.Array<out T>.maxBy(/*0*/ selector: (T) -> R): T?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.BooleanArray.maxBy(/*0*/ selector: (kotlin.Boolean) -> R): kotlin.Boolean?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ByteArray.maxBy(/*0*/ selector: (kotlin.Byte) -> R): kotlin.Byte?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.CharArray.maxBy(/*0*/ selector: (kotlin.Char) -> R): kotlin.Char?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.DoubleArray.maxBy(/*0*/ selector: (kotlin.Double) -> R): kotlin.Double?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.FloatArray.maxBy(/*0*/ selector: (kotlin.Float) -> R): kotlin.Float?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.IntArray.maxBy(/*0*/ selector: (kotlin.Int) -> R): kotlin.Int?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.LongArray.maxBy(/*0*/ selector: (kotlin.Long) -> R): kotlin.Long?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ShortArray.maxBy(/*0*/ selector: (kotlin.Short) -> R): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UByteArray.maxBy(/*0*/ selector: (kotlin.UByte) -> R): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UIntArray.maxBy(/*0*/ selector: (kotlin.UInt) -> R): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ULongArray.maxBy(/*0*/ selector: (kotlin.ULong) -> R): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UShortArray.maxBy(/*0*/ selector: (kotlin.UShort) -> R): kotlin.UShort?
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.collections.Iterable<T>.maxBy(/*0*/ selector: (T) -> R): T?
@kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ R : kotlin.Comparable<R>> kotlin.collections.Map<out K, V>.maxBy(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> R): kotlin.collections.Map.Entry<K, V>?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.Array<out T>.maxOf(/*0*/ selector: (T) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.maxOf(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.maxOf(/*0*/ selector: (T) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.BooleanArray.maxOf(/*0*/ selector: (kotlin.Boolean) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.maxOf(/*0*/ selector: (kotlin.Boolean) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.maxOf(/*0*/ selector: (kotlin.Boolean) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ByteArray.maxOf(/*0*/ selector: (kotlin.Byte) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.maxOf(/*0*/ selector: (kotlin.Byte) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.maxOf(/*0*/ selector: (kotlin.Byte) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.CharArray.maxOf(/*0*/ selector: (kotlin.Char) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.maxOf(/*0*/ selector: (kotlin.Char) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.maxOf(/*0*/ selector: (kotlin.Char) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.DoubleArray.maxOf(/*0*/ selector: (kotlin.Double) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.maxOf(/*0*/ selector: (kotlin.Double) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.maxOf(/*0*/ selector: (kotlin.Double) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.FloatArray.maxOf(/*0*/ selector: (kotlin.Float) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.maxOf(/*0*/ selector: (kotlin.Float) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.maxOf(/*0*/ selector: (kotlin.Float) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.IntArray.maxOf(/*0*/ selector: (kotlin.Int) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.maxOf(/*0*/ selector: (kotlin.Int) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.maxOf(/*0*/ selector: (kotlin.Int) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.LongArray.maxOf(/*0*/ selector: (kotlin.Long) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.maxOf(/*0*/ selector: (kotlin.Long) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.maxOf(/*0*/ selector: (kotlin.Long) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ShortArray.maxOf(/*0*/ selector: (kotlin.Short) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.maxOf(/*0*/ selector: (kotlin.Short) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.maxOf(/*0*/ selector: (kotlin.Short) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UByteArray.maxOf(/*0*/ selector: (kotlin.UByte) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.maxOf(/*0*/ selector: (kotlin.UByte) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.maxOf(/*0*/ selector: (kotlin.UByte) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UIntArray.maxOf(/*0*/ selector: (kotlin.UInt) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.maxOf(/*0*/ selector: (kotlin.UInt) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.maxOf(/*0*/ selector: (kotlin.UInt) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ULongArray.maxOf(/*0*/ selector: (kotlin.ULong) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.maxOf(/*0*/ selector: (kotlin.ULong) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.maxOf(/*0*/ selector: (kotlin.ULong) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UShortArray.maxOf(/*0*/ selector: (kotlin.UShort) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.maxOf(/*0*/ selector: (kotlin.UShort) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.maxOf(/*0*/ selector: (kotlin.UShort) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.collections.Iterable<T>.maxOf(/*0*/ selector: (T) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.maxOf(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.maxOf(/*0*/ selector: (T) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ R : kotlin.Comparable<R>> kotlin.collections.Map<out K, V>.maxOf(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.maxOf(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.maxOf(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.Array<out T>.maxOfOrNull(/*0*/ selector: (T) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.maxOfOrNull(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.maxOfOrNull(/*0*/ selector: (T) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.BooleanArray.maxOfOrNull(/*0*/ selector: (kotlin.Boolean) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.maxOfOrNull(/*0*/ selector: (kotlin.Boolean) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.maxOfOrNull(/*0*/ selector: (kotlin.Boolean) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ByteArray.maxOfOrNull(/*0*/ selector: (kotlin.Byte) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.maxOfOrNull(/*0*/ selector: (kotlin.Byte) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.maxOfOrNull(/*0*/ selector: (kotlin.Byte) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.CharArray.maxOfOrNull(/*0*/ selector: (kotlin.Char) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.maxOfOrNull(/*0*/ selector: (kotlin.Char) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.maxOfOrNull(/*0*/ selector: (kotlin.Char) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.DoubleArray.maxOfOrNull(/*0*/ selector: (kotlin.Double) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.maxOfOrNull(/*0*/ selector: (kotlin.Double) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.maxOfOrNull(/*0*/ selector: (kotlin.Double) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.FloatArray.maxOfOrNull(/*0*/ selector: (kotlin.Float) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.maxOfOrNull(/*0*/ selector: (kotlin.Float) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.maxOfOrNull(/*0*/ selector: (kotlin.Float) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.IntArray.maxOfOrNull(/*0*/ selector: (kotlin.Int) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.maxOfOrNull(/*0*/ selector: (kotlin.Int) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.maxOfOrNull(/*0*/ selector: (kotlin.Int) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.LongArray.maxOfOrNull(/*0*/ selector: (kotlin.Long) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.maxOfOrNull(/*0*/ selector: (kotlin.Long) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.maxOfOrNull(/*0*/ selector: (kotlin.Long) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ShortArray.maxOfOrNull(/*0*/ selector: (kotlin.Short) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.maxOfOrNull(/*0*/ selector: (kotlin.Short) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.maxOfOrNull(/*0*/ selector: (kotlin.Short) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UByteArray.maxOfOrNull(/*0*/ selector: (kotlin.UByte) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.maxOfOrNull(/*0*/ selector: (kotlin.UByte) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.maxOfOrNull(/*0*/ selector: (kotlin.UByte) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UIntArray.maxOfOrNull(/*0*/ selector: (kotlin.UInt) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.maxOfOrNull(/*0*/ selector: (kotlin.UInt) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.maxOfOrNull(/*0*/ selector: (kotlin.UInt) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ULongArray.maxOfOrNull(/*0*/ selector: (kotlin.ULong) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.maxOfOrNull(/*0*/ selector: (kotlin.ULong) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.maxOfOrNull(/*0*/ selector: (kotlin.ULong) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UShortArray.maxOfOrNull(/*0*/ selector: (kotlin.UShort) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.maxOfOrNull(/*0*/ selector: (kotlin.UShort) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.maxOfOrNull(/*0*/ selector: (kotlin.UShort) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.collections.Iterable<T>.maxOfOrNull(/*0*/ selector: (T) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.maxOfOrNull(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.maxOfOrNull(/*0*/ selector: (T) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ R : kotlin.Comparable<R>> kotlin.collections.Map<out K, V>.maxOfOrNull(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.maxOfOrNull(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.maxOfOrNull(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (T) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Boolean) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Byte) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Char) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Double) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Float) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Int) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Long) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Short) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UByte) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UInt) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.ULong) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UShort) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (T) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ R> kotlin.collections.Map<out K, V>.maxOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.collections.Map.Entry<K, V>) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (T) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Boolean) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Byte) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Char) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Double) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Float) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Int) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Long) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Short) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UByte) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UInt) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.ULong) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UShort) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (T) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ R> kotlin.collections.Map<out K, V>.maxOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.collections.Map.Entry<K, V>) -> R): R?
public fun </*0*/ T> kotlin.Array<out T>.maxWith(/*0*/ comparator: kotlin.Comparator<in T>): T?
public fun kotlin.BooleanArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Boolean>): kotlin.Boolean?
public fun kotlin.ByteArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Byte>): kotlin.Byte?
public fun kotlin.CharArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Char>): kotlin.Char?
public fun kotlin.DoubleArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Double>): kotlin.Double?
public fun kotlin.FloatArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Float>): kotlin.Float?
public fun kotlin.IntArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Int>): kotlin.Int?
public fun kotlin.LongArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Long>): kotlin.Long?
public fun kotlin.ShortArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Short>): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.UByte>): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.UInt>): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.ULong>): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.UShort>): kotlin.UShort?
public fun </*0*/ T> kotlin.collections.Iterable<T>.maxWith(/*0*/ comparator: kotlin.Comparator<in T>): T?
@kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.maxWith(/*0*/ comparator: kotlin.Comparator<in kotlin.collections.Map.Entry<K, V>>): kotlin.collections.Map.Entry<K, V>?
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.Array<out T>.min(): T?
@kotlin.SinceKotlin(version = "1.1") public fun kotlin.Array<out kotlin.Double>.min(): kotlin.Double?
@kotlin.SinceKotlin(version = "1.1") public fun kotlin.Array<out kotlin.Float>.min(): kotlin.Float?
public fun kotlin.ByteArray.min(): kotlin.Byte?
public fun kotlin.CharArray.min(): kotlin.Char?
public fun kotlin.DoubleArray.min(): kotlin.Double?
public fun kotlin.FloatArray.min(): kotlin.Float?
public fun kotlin.IntArray.min(): kotlin.Int?
public fun kotlin.LongArray.min(): kotlin.Long?
public fun kotlin.ShortArray.min(): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.min(): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.min(): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.min(): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.min(): kotlin.UShort?
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.collections.Iterable<T>.min(): T?
@kotlin.SinceKotlin(version = "1.1") public fun kotlin.collections.Iterable<kotlin.Double>.min(): kotlin.Double?
@kotlin.SinceKotlin(version = "1.1") public fun kotlin.collections.Iterable<kotlin.Float>.min(): kotlin.Float?
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.Array<out T>.minBy(/*0*/ selector: (T) -> R): T?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.BooleanArray.minBy(/*0*/ selector: (kotlin.Boolean) -> R): kotlin.Boolean?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ByteArray.minBy(/*0*/ selector: (kotlin.Byte) -> R): kotlin.Byte?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.CharArray.minBy(/*0*/ selector: (kotlin.Char) -> R): kotlin.Char?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.DoubleArray.minBy(/*0*/ selector: (kotlin.Double) -> R): kotlin.Double?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.FloatArray.minBy(/*0*/ selector: (kotlin.Float) -> R): kotlin.Float?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.IntArray.minBy(/*0*/ selector: (kotlin.Int) -> R): kotlin.Int?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.LongArray.minBy(/*0*/ selector: (kotlin.Long) -> R): kotlin.Long?
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ShortArray.minBy(/*0*/ selector: (kotlin.Short) -> R): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UByteArray.minBy(/*0*/ selector: (kotlin.UByte) -> R): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UIntArray.minBy(/*0*/ selector: (kotlin.UInt) -> R): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ULongArray.minBy(/*0*/ selector: (kotlin.ULong) -> R): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UShortArray.minBy(/*0*/ selector: (kotlin.UShort) -> R): kotlin.UShort?
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.collections.Iterable<T>.minBy(/*0*/ selector: (T) -> R): T?
public inline fun </*0*/ K, /*1*/ V, /*2*/ R : kotlin.Comparable<R>> kotlin.collections.Map<out K, V>.minBy(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> R): kotlin.collections.Map.Entry<K, V>?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.Array<out T>.minOf(/*0*/ selector: (T) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.minOf(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.minOf(/*0*/ selector: (T) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.BooleanArray.minOf(/*0*/ selector: (kotlin.Boolean) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.minOf(/*0*/ selector: (kotlin.Boolean) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.minOf(/*0*/ selector: (kotlin.Boolean) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ByteArray.minOf(/*0*/ selector: (kotlin.Byte) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.minOf(/*0*/ selector: (kotlin.Byte) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.minOf(/*0*/ selector: (kotlin.Byte) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.CharArray.minOf(/*0*/ selector: (kotlin.Char) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.minOf(/*0*/ selector: (kotlin.Char) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.minOf(/*0*/ selector: (kotlin.Char) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.DoubleArray.minOf(/*0*/ selector: (kotlin.Double) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.minOf(/*0*/ selector: (kotlin.Double) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.minOf(/*0*/ selector: (kotlin.Double) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.FloatArray.minOf(/*0*/ selector: (kotlin.Float) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.minOf(/*0*/ selector: (kotlin.Float) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.minOf(/*0*/ selector: (kotlin.Float) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.IntArray.minOf(/*0*/ selector: (kotlin.Int) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.minOf(/*0*/ selector: (kotlin.Int) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.minOf(/*0*/ selector: (kotlin.Int) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.LongArray.minOf(/*0*/ selector: (kotlin.Long) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.minOf(/*0*/ selector: (kotlin.Long) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.minOf(/*0*/ selector: (kotlin.Long) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ShortArray.minOf(/*0*/ selector: (kotlin.Short) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.minOf(/*0*/ selector: (kotlin.Short) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.minOf(/*0*/ selector: (kotlin.Short) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UByteArray.minOf(/*0*/ selector: (kotlin.UByte) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.minOf(/*0*/ selector: (kotlin.UByte) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.minOf(/*0*/ selector: (kotlin.UByte) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UIntArray.minOf(/*0*/ selector: (kotlin.UInt) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.minOf(/*0*/ selector: (kotlin.UInt) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.minOf(/*0*/ selector: (kotlin.UInt) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ULongArray.minOf(/*0*/ selector: (kotlin.ULong) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.minOf(/*0*/ selector: (kotlin.ULong) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.minOf(/*0*/ selector: (kotlin.ULong) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UShortArray.minOf(/*0*/ selector: (kotlin.UShort) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.minOf(/*0*/ selector: (kotlin.UShort) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.minOf(/*0*/ selector: (kotlin.UShort) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.collections.Iterable<T>.minOf(/*0*/ selector: (T) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.minOf(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.minOf(/*0*/ selector: (T) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ R : kotlin.Comparable<R>> kotlin.collections.Map<out K, V>.minOf(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.minOf(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.minOf(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Float): kotlin.Float
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.Array<out T>.minOfOrNull(/*0*/ selector: (T) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.minOfOrNull(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.minOfOrNull(/*0*/ selector: (T) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.BooleanArray.minOfOrNull(/*0*/ selector: (kotlin.Boolean) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.minOfOrNull(/*0*/ selector: (kotlin.Boolean) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.minOfOrNull(/*0*/ selector: (kotlin.Boolean) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ByteArray.minOfOrNull(/*0*/ selector: (kotlin.Byte) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.minOfOrNull(/*0*/ selector: (kotlin.Byte) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.minOfOrNull(/*0*/ selector: (kotlin.Byte) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.CharArray.minOfOrNull(/*0*/ selector: (kotlin.Char) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.minOfOrNull(/*0*/ selector: (kotlin.Char) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.minOfOrNull(/*0*/ selector: (kotlin.Char) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.DoubleArray.minOfOrNull(/*0*/ selector: (kotlin.Double) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.minOfOrNull(/*0*/ selector: (kotlin.Double) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.minOfOrNull(/*0*/ selector: (kotlin.Double) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.FloatArray.minOfOrNull(/*0*/ selector: (kotlin.Float) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.minOfOrNull(/*0*/ selector: (kotlin.Float) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.minOfOrNull(/*0*/ selector: (kotlin.Float) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.IntArray.minOfOrNull(/*0*/ selector: (kotlin.Int) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.minOfOrNull(/*0*/ selector: (kotlin.Int) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.minOfOrNull(/*0*/ selector: (kotlin.Int) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.LongArray.minOfOrNull(/*0*/ selector: (kotlin.Long) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.minOfOrNull(/*0*/ selector: (kotlin.Long) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.minOfOrNull(/*0*/ selector: (kotlin.Long) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ShortArray.minOfOrNull(/*0*/ selector: (kotlin.Short) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.minOfOrNull(/*0*/ selector: (kotlin.Short) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.minOfOrNull(/*0*/ selector: (kotlin.Short) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UByteArray.minOfOrNull(/*0*/ selector: (kotlin.UByte) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.minOfOrNull(/*0*/ selector: (kotlin.UByte) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.minOfOrNull(/*0*/ selector: (kotlin.UByte) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UIntArray.minOfOrNull(/*0*/ selector: (kotlin.UInt) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.minOfOrNull(/*0*/ selector: (kotlin.UInt) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.minOfOrNull(/*0*/ selector: (kotlin.UInt) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ULongArray.minOfOrNull(/*0*/ selector: (kotlin.ULong) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.minOfOrNull(/*0*/ selector: (kotlin.ULong) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.minOfOrNull(/*0*/ selector: (kotlin.ULong) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.UShortArray.minOfOrNull(/*0*/ selector: (kotlin.UShort) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.minOfOrNull(/*0*/ selector: (kotlin.UShort) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.minOfOrNull(/*0*/ selector: (kotlin.UShort) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.collections.Iterable<T>.minOfOrNull(/*0*/ selector: (T) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.minOfOrNull(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.minOfOrNull(/*0*/ selector: (T) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ R : kotlin.Comparable<R>> kotlin.collections.Map<out K, V>.minOfOrNull(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.minOfOrNull(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.minOfOrNull(/*0*/ selector: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (T) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Boolean) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Byte) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Char) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Double) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Float) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Int) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Long) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Short) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UByte) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UInt) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.ULong) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UShort) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (T) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ R> kotlin.collections.Map<out K, V>.minOfWith(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.collections.Map.Entry<K, V>) -> R): R
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (T) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Boolean) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Byte) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Char) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Double) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Float) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Int) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Long) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.Short) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UByte) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UInt) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.ULong) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.UShort) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (T) -> R): R?
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V, /*2*/ R> kotlin.collections.Map<out K, V>.minOfWithOrNull(/*0*/ comparator: kotlin.Comparator<in R>, /*1*/ selector: (kotlin.collections.Map.Entry<K, V>) -> R): R?
public fun </*0*/ T> kotlin.Array<out T>.minWith(/*0*/ comparator: kotlin.Comparator<in T>): T?
public fun kotlin.BooleanArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Boolean>): kotlin.Boolean?
public fun kotlin.ByteArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Byte>): kotlin.Byte?
public fun kotlin.CharArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Char>): kotlin.Char?
public fun kotlin.DoubleArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Double>): kotlin.Double?
public fun kotlin.FloatArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Float>): kotlin.Float?
public fun kotlin.IntArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Int>): kotlin.Int?
public fun kotlin.LongArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Long>): kotlin.Long?
public fun kotlin.ShortArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Short>): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.UByte>): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.UInt>): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.ULong>): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.UShort>): kotlin.UShort?
public fun </*0*/ T> kotlin.collections.Iterable<T>.minWith(/*0*/ comparator: kotlin.Comparator<in T>): T?
public fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.minWith(/*0*/ comparator: kotlin.Comparator<in kotlin.collections.Map.Entry<K, V>>): kotlin.collections.Map.Entry<K, V>?
public operator fun </*0*/ T> kotlin.collections.Iterable<T>.minus(/*0*/ element: T): kotlin.collections.List<T>
public operator fun </*0*/ T> kotlin.collections.Iterable<T>.minus(/*0*/ elements: kotlin.Array<out T>): kotlin.collections.List<T>
public operator fun </*0*/ T> kotlin.collections.Iterable<T>.minus(/*0*/ elements: kotlin.collections.Iterable<T>): kotlin.collections.List<T>
public operator fun </*0*/ T> kotlin.collections.Iterable<T>.minus(/*0*/ elements: kotlin.sequences.Sequence<T>): kotlin.collections.List<T>
@kotlin.SinceKotlin(version = "1.1") public operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.minus(/*0*/ key: K): kotlin.collections.Map<K, V>
@kotlin.SinceKotlin(version = "1.1") public operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.minus(/*0*/ keys: kotlin.Array<out K>): kotlin.collections.Map<K, V>
@kotlin.SinceKotlin(version = "1.1") public operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.minus(/*0*/ keys: kotlin.collections.Iterable<K>): kotlin.collections.Map<K, V>
@kotlin.SinceKotlin(version = "1.1") public operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.minus(/*0*/ keys: kotlin.sequences.Sequence<K>): kotlin.collections.Map<K, V>
public operator fun </*0*/ T> kotlin.collections.Set<T>.minus(/*0*/ element: T): kotlin.collections.Set<T>
public operator fun </*0*/ T> kotlin.collections.Set<T>.minus(/*0*/ elements: kotlin.Array<out T>): kotlin.collections.Set<T>
public operator fun </*0*/ T> kotlin.collections.Set<T>.minus(/*0*/ elements: kotlin.collections.Iterable<T>): kotlin.collections.Set<T>
public operator fun </*0*/ T> kotlin.collections.Set<T>.minus(/*0*/ elements: kotlin.sequences.Sequence<T>): kotlin.collections.Set<T>
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.MutableCollection<in T>.minusAssign(/*0*/ element: T): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.MutableCollection<in T>.minusAssign(/*0*/ elements: kotlin.Array<T>): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.MutableCollection<in T>.minusAssign(/*0*/ elements: kotlin.collections.Iterable<T>): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.MutableCollection<in T>.minusAssign(/*0*/ elements: kotlin.sequences.Sequence<T>): kotlin.Unit
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<K, V>.minusAssign(/*0*/ key: K): kotlin.Unit
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<K, V>.minusAssign(/*0*/ keys: kotlin.Array<out K>): kotlin.Unit
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<K, V>.minusAssign(/*0*/ keys: kotlin.collections.Iterable<K>): kotlin.Unit
@kotlin.SinceKotlin(version = "1.1") @kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<K, V>.minusAssign(/*0*/ keys: kotlin.sequences.Sequence<K>): kotlin.Unit
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.minusElement(/*0*/ element: T): kotlin.collections.List<T>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Set<T>.minusElement(/*0*/ element: T): kotlin.collections.Set<T>
public fun </*0*/ T> kotlin.Array<out T>.none(): kotlin.Boolean
public inline fun </*0*/ T> kotlin.Array<out T>.none(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.BooleanArray.none(): kotlin.Boolean
public inline fun kotlin.BooleanArray.none(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.ByteArray.none(): kotlin.Boolean
public inline fun kotlin.ByteArray.none(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.CharArray.none(): kotlin.Boolean
public inline fun kotlin.CharArray.none(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.DoubleArray.none(): kotlin.Boolean
public inline fun kotlin.DoubleArray.none(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.FloatArray.none(): kotlin.Boolean
public inline fun kotlin.FloatArray.none(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.IntArray.none(): kotlin.Boolean
public inline fun kotlin.IntArray.none(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.LongArray.none(): kotlin.Boolean
public inline fun kotlin.LongArray.none(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.ShortArray.none(): kotlin.Boolean
public inline fun kotlin.ShortArray.none(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.none(): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.none(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.none(): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.none(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.none(): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.none(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.none(): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.none(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.Iterable<T>.none(): kotlin.Boolean
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.none(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Boolean
public fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.none(): kotlin.Boolean
public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.none(/*0*/ predicate: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ T, /*1*/ C : kotlin.collections.Iterable<T>> C.onEach(/*0*/ action: (T) -> kotlin.Unit): C
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.Map<out K, V>> M.onEach(/*0*/ action: (kotlin.collections.Map.Entry<K, V>) -> kotlin.Unit): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.onEach(/*0*/ action: (T) -> kotlin.Unit): kotlin.Array<out T>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.onEach(/*0*/ action: (kotlin.Boolean) -> kotlin.Unit): kotlin.BooleanArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.onEach(/*0*/ action: (kotlin.Byte) -> kotlin.Unit): kotlin.ByteArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.onEach(/*0*/ action: (kotlin.Char) -> kotlin.Unit): kotlin.CharArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.onEach(/*0*/ action: (kotlin.Double) -> kotlin.Unit): kotlin.DoubleArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.onEach(/*0*/ action: (kotlin.Float) -> kotlin.Unit): kotlin.FloatArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.onEach(/*0*/ action: (kotlin.Int) -> kotlin.Unit): kotlin.IntArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.onEach(/*0*/ action: (kotlin.Long) -> kotlin.Unit): kotlin.LongArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.onEach(/*0*/ action: (kotlin.Short) -> kotlin.Unit): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.onEach(/*0*/ action: (kotlin.UByte) -> kotlin.Unit): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.onEach(/*0*/ action: (kotlin.UInt) -> kotlin.Unit): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.onEach(/*0*/ action: (kotlin.ULong) -> kotlin.Unit): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.onEach(/*0*/ action: (kotlin.UShort) -> kotlin.Unit): kotlin.UShortArray
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ T, /*1*/ C : kotlin.collections.Iterable<T>> C.onEachIndexed(/*0*/ action: (index: kotlin.Int, T) -> kotlin.Unit): C
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.Map<out K, V>> M.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.collections.Map.Entry<K, V>) -> kotlin.Unit): M
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.onEachIndexed(/*0*/ action: (index: kotlin.Int, T) -> kotlin.Unit): kotlin.Array<out T>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Boolean) -> kotlin.Unit): kotlin.BooleanArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Byte) -> kotlin.Unit): kotlin.ByteArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Char) -> kotlin.Unit): kotlin.CharArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Double) -> kotlin.Unit): kotlin.DoubleArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Float) -> kotlin.Unit): kotlin.FloatArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Int) -> kotlin.Unit): kotlin.IntArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Long) -> kotlin.Unit): kotlin.LongArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.Short) -> kotlin.Unit): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.UByte) -> kotlin.Unit): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.UInt) -> kotlin.Unit): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.ULong) -> kotlin.Unit): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.onEachIndexed(/*0*/ action: (index: kotlin.Int, kotlin.UShort) -> kotlin.Unit): kotlin.UShortArray
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>?.orEmpty(): kotlin.Array<out T>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Collection<T>?.orEmpty(): kotlin.collections.Collection<T>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.List<T>?.orEmpty(): kotlin.collections.List<T>
@kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map<K, V>?.orEmpty(): kotlin.collections.Map<K, V>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Set<T>?.orEmpty(): kotlin.collections.Set<T>
public inline fun </*0*/ T> kotlin.Array<out T>.partition(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Pair<kotlin.collections.List<T>, kotlin.collections.List<T>>
public inline fun kotlin.BooleanArray.partition(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Pair<kotlin.collections.List<kotlin.Boolean>, kotlin.collections.List<kotlin.Boolean>>
public inline fun kotlin.ByteArray.partition(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Pair<kotlin.collections.List<kotlin.Byte>, kotlin.collections.List<kotlin.Byte>>
public inline fun kotlin.CharArray.partition(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Pair<kotlin.collections.List<kotlin.Char>, kotlin.collections.List<kotlin.Char>>
public inline fun kotlin.DoubleArray.partition(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Pair<kotlin.collections.List<kotlin.Double>, kotlin.collections.List<kotlin.Double>>
public inline fun kotlin.FloatArray.partition(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Pair<kotlin.collections.List<kotlin.Float>, kotlin.collections.List<kotlin.Float>>
public inline fun kotlin.IntArray.partition(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Pair<kotlin.collections.List<kotlin.Int>, kotlin.collections.List<kotlin.Int>>
public inline fun kotlin.LongArray.partition(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Pair<kotlin.collections.List<kotlin.Long>, kotlin.collections.List<kotlin.Long>>
public inline fun kotlin.ShortArray.partition(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Pair<kotlin.collections.List<kotlin.Short>, kotlin.collections.List<kotlin.Short>>
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.partition(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Pair<kotlin.collections.List<T>, kotlin.collections.List<T>>
public inline operator fun </*0*/ T> kotlin.Array<out T>.plus(/*0*/ element: T): kotlin.Array<T>
public inline operator fun </*0*/ T> kotlin.Array<out T>.plus(/*0*/ elements: kotlin.Array<out T>): kotlin.Array<T>
public operator fun </*0*/ T> kotlin.Array<out T>.plus(/*0*/ elements: kotlin.collections.Collection<T>): kotlin.Array<T>
public inline operator fun kotlin.BooleanArray.plus(/*0*/ element: kotlin.Boolean): kotlin.BooleanArray
public inline operator fun kotlin.BooleanArray.plus(/*0*/ elements: kotlin.BooleanArray): kotlin.BooleanArray
public operator fun kotlin.BooleanArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.Boolean>): kotlin.BooleanArray
public inline operator fun kotlin.ByteArray.plus(/*0*/ element: kotlin.Byte): kotlin.ByteArray
public inline operator fun kotlin.ByteArray.plus(/*0*/ elements: kotlin.ByteArray): kotlin.ByteArray
public operator fun kotlin.ByteArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.Byte>): kotlin.ByteArray
public inline operator fun kotlin.CharArray.plus(/*0*/ element: kotlin.Char): kotlin.CharArray
public inline operator fun kotlin.CharArray.plus(/*0*/ elements: kotlin.CharArray): kotlin.CharArray
public operator fun kotlin.CharArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.Char>): kotlin.CharArray
public inline operator fun kotlin.DoubleArray.plus(/*0*/ element: kotlin.Double): kotlin.DoubleArray
public inline operator fun kotlin.DoubleArray.plus(/*0*/ elements: kotlin.DoubleArray): kotlin.DoubleArray
public operator fun kotlin.DoubleArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.Double>): kotlin.DoubleArray
public inline operator fun kotlin.FloatArray.plus(/*0*/ element: kotlin.Float): kotlin.FloatArray
public inline operator fun kotlin.FloatArray.plus(/*0*/ elements: kotlin.FloatArray): kotlin.FloatArray
public operator fun kotlin.FloatArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.Float>): kotlin.FloatArray
public inline operator fun kotlin.IntArray.plus(/*0*/ element: kotlin.Int): kotlin.IntArray
public inline operator fun kotlin.IntArray.plus(/*0*/ elements: kotlin.IntArray): kotlin.IntArray
public operator fun kotlin.IntArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.Int>): kotlin.IntArray
public inline operator fun kotlin.LongArray.plus(/*0*/ element: kotlin.Long): kotlin.LongArray
public inline operator fun kotlin.LongArray.plus(/*0*/ elements: kotlin.LongArray): kotlin.LongArray
public operator fun kotlin.LongArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.Long>): kotlin.LongArray
public inline operator fun kotlin.ShortArray.plus(/*0*/ element: kotlin.Short): kotlin.ShortArray
public inline operator fun kotlin.ShortArray.plus(/*0*/ elements: kotlin.ShortArray): kotlin.ShortArray
public operator fun kotlin.ShortArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.Short>): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UByteArray.plus(/*0*/ element: kotlin.UByte): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UByteArray.plus(/*0*/ elements: kotlin.UByteArray): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public operator fun kotlin.UByteArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.UByte>): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UIntArray.plus(/*0*/ element: kotlin.UInt): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UIntArray.plus(/*0*/ elements: kotlin.UIntArray): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public operator fun kotlin.UIntArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.UInt>): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.ULongArray.plus(/*0*/ element: kotlin.ULong): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.ULongArray.plus(/*0*/ elements: kotlin.ULongArray): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public operator fun kotlin.ULongArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.ULong>): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UShortArray.plus(/*0*/ element: kotlin.UShort): kotlin.UShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline operator fun kotlin.UShortArray.plus(/*0*/ elements: kotlin.UShortArray): kotlin.UShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public operator fun kotlin.UShortArray.plus(/*0*/ elements: kotlin.collections.Collection<kotlin.UShort>): kotlin.UShortArray
public operator fun </*0*/ T> kotlin.collections.Collection<T>.plus(/*0*/ element: T): kotlin.collections.List<T>
public operator fun </*0*/ T> kotlin.collections.Collection<T>.plus(/*0*/ elements: kotlin.Array<out T>): kotlin.collections.List<T>
public operator fun </*0*/ T> kotlin.collections.Collection<T>.plus(/*0*/ elements: kotlin.collections.Iterable<T>): kotlin.collections.List<T>
public operator fun </*0*/ T> kotlin.collections.Collection<T>.plus(/*0*/ elements: kotlin.sequences.Sequence<T>): kotlin.collections.List<T>
public operator fun </*0*/ T> kotlin.collections.Iterable<T>.plus(/*0*/ element: T): kotlin.collections.List<T>
public operator fun </*0*/ T> kotlin.collections.Iterable<T>.plus(/*0*/ elements: kotlin.Array<out T>): kotlin.collections.List<T>
public operator fun </*0*/ T> kotlin.collections.Iterable<T>.plus(/*0*/ elements: kotlin.collections.Iterable<T>): kotlin.collections.List<T>
public operator fun </*0*/ T> kotlin.collections.Iterable<T>.plus(/*0*/ elements: kotlin.sequences.Sequence<T>): kotlin.collections.List<T>
public operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.plus(/*0*/ pairs: kotlin.Array<out kotlin.Pair<K, V>>): kotlin.collections.Map<K, V>
public operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.plus(/*0*/ pair: kotlin.Pair<K, V>): kotlin.collections.Map<K, V>
public operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.plus(/*0*/ pairs: kotlin.collections.Iterable<kotlin.Pair<K, V>>): kotlin.collections.Map<K, V>
public operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.plus(/*0*/ map: kotlin.collections.Map<out K, V>): kotlin.collections.Map<K, V>
public operator fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.plus(/*0*/ pairs: kotlin.sequences.Sequence<kotlin.Pair<K, V>>): kotlin.collections.Map<K, V>
public operator fun </*0*/ T> kotlin.collections.Set<T>.plus(/*0*/ element: T): kotlin.collections.Set<T>
public operator fun </*0*/ T> kotlin.collections.Set<T>.plus(/*0*/ elements: kotlin.Array<out T>): kotlin.collections.Set<T>
public operator fun </*0*/ T> kotlin.collections.Set<T>.plus(/*0*/ elements: kotlin.collections.Iterable<T>): kotlin.collections.Set<T>
public operator fun </*0*/ T> kotlin.collections.Set<T>.plus(/*0*/ elements: kotlin.sequences.Sequence<T>): kotlin.collections.Set<T>
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.MutableCollection<in T>.plusAssign(/*0*/ element: T): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.MutableCollection<in T>.plusAssign(/*0*/ elements: kotlin.Array<T>): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.MutableCollection<in T>.plusAssign(/*0*/ elements: kotlin.collections.Iterable<T>): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ T> kotlin.collections.MutableCollection<in T>.plusAssign(/*0*/ elements: kotlin.sequences.Sequence<T>): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<in K, in V>.plusAssign(/*0*/ pairs: kotlin.Array<out kotlin.Pair<K, V>>): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<in K, in V>.plusAssign(/*0*/ pair: kotlin.Pair<K, V>): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<in K, in V>.plusAssign(/*0*/ pairs: kotlin.collections.Iterable<kotlin.Pair<K, V>>): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<in K, in V>.plusAssign(/*0*/ map: kotlin.collections.Map<K, V>): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<in K, in V>.plusAssign(/*0*/ pairs: kotlin.sequences.Sequence<kotlin.Pair<K, V>>): kotlin.Unit
public inline fun </*0*/ T> kotlin.Array<out T>.plusElement(/*0*/ element: T): kotlin.Array<T>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Collection<T>.plusElement(/*0*/ element: T): kotlin.collections.List<T>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.plusElement(/*0*/ element: T): kotlin.collections.List<T>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Set<T>.plusElement(/*0*/ element: T): kotlin.collections.Set<T>
public fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<in K, in V>.putAll(/*0*/ pairs: kotlin.Array<out kotlin.Pair<K, V>>): kotlin.Unit
public fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<in K, in V>.putAll(/*0*/ pairs: kotlin.collections.Iterable<kotlin.Pair<K, V>>): kotlin.Unit
public fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<in K, in V>.putAll(/*0*/ pairs: kotlin.sequences.Sequence<kotlin.Pair<K, V>>): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.random(): T
@kotlin.SinceKotlin(version = "1.3") public fun </*0*/ T> kotlin.Array<out T>.random(/*0*/ random: kotlin.random.Random): T
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.random(): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.BooleanArray.random(/*0*/ random: kotlin.random.Random): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.random(): kotlin.Byte
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.ByteArray.random(/*0*/ random: kotlin.random.Random): kotlin.Byte
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.random(): kotlin.Char
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.CharArray.random(/*0*/ random: kotlin.random.Random): kotlin.Char
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.random(): kotlin.Double
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.DoubleArray.random(/*0*/ random: kotlin.random.Random): kotlin.Double
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.random(): kotlin.Float
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.FloatArray.random(/*0*/ random: kotlin.random.Random): kotlin.Float
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.random(): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.IntArray.random(/*0*/ random: kotlin.random.Random): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.random(): kotlin.Long
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.LongArray.random(/*0*/ random: kotlin.random.Random): kotlin.Long
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.random(): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") public fun kotlin.ShortArray.random(/*0*/ random: kotlin.random.Random): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.random(): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.random(/*0*/ random: kotlin.random.Random): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.random(): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.random(/*0*/ random: kotlin.random.Random): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.random(): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.random(/*0*/ random: kotlin.random.Random): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.random(): kotlin.UShort
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.random(/*0*/ random: kotlin.random.Random): kotlin.UShort
@kotlin.SinceKotlin(version = "1.3") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Collection<T>.random(): T
@kotlin.SinceKotlin(version = "1.3") public fun </*0*/ T> kotlin.collections.Collection<T>.random(/*0*/ random: kotlin.random.Random): T
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.randomOrNull(): T?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun </*0*/ T> kotlin.Array<out T>.randomOrNull(/*0*/ random: kotlin.random.Random): T?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.randomOrNull(): kotlin.Boolean?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.BooleanArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Boolean?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.randomOrNull(): kotlin.Byte?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.ByteArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Byte?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.randomOrNull(): kotlin.Char?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.CharArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Char?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.randomOrNull(): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.DoubleArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.randomOrNull(): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.FloatArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.randomOrNull(): kotlin.Int?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.IntArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Int?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.randomOrNull(): kotlin.Long?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.LongArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Long?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.randomOrNull(): kotlin.Short?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun kotlin.ShortArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.Short?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.randomOrNull(): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.randomOrNull(): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.randomOrNull(): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.randomOrNull(): kotlin.UShort?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.randomOrNull(/*0*/ random: kotlin.random.Random): kotlin.UShort?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Collection<T>.randomOrNull(): T?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public fun </*0*/ T> kotlin.collections.Collection<T>.randomOrNull(/*0*/ random: kotlin.random.Random): T?
public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduce(/*0*/ operation: (acc: S, T) -> S): S
public inline fun kotlin.BooleanArray.reduce(/*0*/ operation: (acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.ByteArray.reduce(/*0*/ operation: (acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.Byte
public inline fun kotlin.CharArray.reduce(/*0*/ operation: (acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.Char
public inline fun kotlin.DoubleArray.reduce(/*0*/ operation: (acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.Double
public inline fun kotlin.FloatArray.reduce(/*0*/ operation: (acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.Float
public inline fun kotlin.IntArray.reduce(/*0*/ operation: (acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.Int
public inline fun kotlin.LongArray.reduce(/*0*/ operation: (acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.Long
public inline fun kotlin.ShortArray.reduce(/*0*/ operation: (acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduce(/*0*/ operation: (acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduce(/*0*/ operation: (acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduce(/*0*/ operation: (acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduce(/*0*/ operation: (acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.UShort
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ S, /*1*/ T : S, /*2*/ K> kotlin.collections.Grouping<T, K>.reduce(/*0*/ operation: (key: K, accumulator: S, element: T) -> S): kotlin.collections.Map<K, S>
public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.reduce(/*0*/ operation: (acc: S, T) -> S): S
public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): S
public inline fun kotlin.BooleanArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.ByteArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.Byte
public inline fun kotlin.CharArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.Char
public inline fun kotlin.DoubleArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.Double
public inline fun kotlin.FloatArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.Float
public inline fun kotlin.IntArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.Int
public inline fun kotlin.LongArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.Long
public inline fun kotlin.ShortArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.UShort
public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.reduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): S
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): S?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.BooleanArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.ByteArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.Byte?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.CharArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.Char?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.DoubleArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.FloatArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.IntArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.Int?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.LongArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.Long?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.ShortArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.Short?
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.UShort?
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.reduceIndexedOrNull(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): S?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduceOrNull(/*0*/ operation: (acc: S, T) -> S): S?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.BooleanArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.ByteArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.Byte?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.CharArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.Char?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.DoubleArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.FloatArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.IntArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.Int?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.LongArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.Long?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.ShortArray.reduceOrNull(/*0*/ operation: (acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.Short?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduceOrNull(/*0*/ operation: (acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduceOrNull(/*0*/ operation: (acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduceOrNull(/*0*/ operation: (acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduceOrNull(/*0*/ operation: (acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.UShort?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.reduceOrNull(/*0*/ operation: (acc: S, T) -> S): S?
public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduceRight(/*0*/ operation: (T, acc: S) -> S): S
public inline fun kotlin.BooleanArray.reduceRight(/*0*/ operation: (kotlin.Boolean, acc: kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.ByteArray.reduceRight(/*0*/ operation: (kotlin.Byte, acc: kotlin.Byte) -> kotlin.Byte): kotlin.Byte
public inline fun kotlin.CharArray.reduceRight(/*0*/ operation: (kotlin.Char, acc: kotlin.Char) -> kotlin.Char): kotlin.Char
public inline fun kotlin.DoubleArray.reduceRight(/*0*/ operation: (kotlin.Double, acc: kotlin.Double) -> kotlin.Double): kotlin.Double
public inline fun kotlin.FloatArray.reduceRight(/*0*/ operation: (kotlin.Float, acc: kotlin.Float) -> kotlin.Float): kotlin.Float
public inline fun kotlin.IntArray.reduceRight(/*0*/ operation: (kotlin.Int, acc: kotlin.Int) -> kotlin.Int): kotlin.Int
public inline fun kotlin.LongArray.reduceRight(/*0*/ operation: (kotlin.Long, acc: kotlin.Long) -> kotlin.Long): kotlin.Long
public inline fun kotlin.ShortArray.reduceRight(/*0*/ operation: (kotlin.Short, acc: kotlin.Short) -> kotlin.Short): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduceRight(/*0*/ operation: (kotlin.UByte, acc: kotlin.UByte) -> kotlin.UByte): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduceRight(/*0*/ operation: (kotlin.UInt, acc: kotlin.UInt) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduceRight(/*0*/ operation: (kotlin.ULong, acc: kotlin.ULong) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduceRight(/*0*/ operation: (kotlin.UShort, acc: kotlin.UShort) -> kotlin.UShort): kotlin.UShort
public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.List<T>.reduceRight(/*0*/ operation: (T, acc: S) -> S): S
public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, T, acc: S) -> S): S
public inline fun kotlin.BooleanArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.Boolean, acc: kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean
public inline fun kotlin.ByteArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.Byte, acc: kotlin.Byte) -> kotlin.Byte): kotlin.Byte
public inline fun kotlin.CharArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.Char, acc: kotlin.Char) -> kotlin.Char): kotlin.Char
public inline fun kotlin.DoubleArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.Double, acc: kotlin.Double) -> kotlin.Double): kotlin.Double
public inline fun kotlin.FloatArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.Float, acc: kotlin.Float) -> kotlin.Float): kotlin.Float
public inline fun kotlin.IntArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.Int, acc: kotlin.Int) -> kotlin.Int): kotlin.Int
public inline fun kotlin.LongArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.Long, acc: kotlin.Long) -> kotlin.Long): kotlin.Long
public inline fun kotlin.ShortArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.Short, acc: kotlin.Short) -> kotlin.Short): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.UByte, acc: kotlin.UByte) -> kotlin.UByte): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.UInt, acc: kotlin.UInt) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.ULong, acc: kotlin.ULong) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, kotlin.UShort, acc: kotlin.UShort) -> kotlin.UShort): kotlin.UShort
public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.List<T>.reduceRightIndexed(/*0*/ operation: (index: kotlin.Int, T, acc: S) -> S): S
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, T, acc: S) -> S): S?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.BooleanArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.Boolean, acc: kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.ByteArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.Byte, acc: kotlin.Byte) -> kotlin.Byte): kotlin.Byte?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.CharArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.Char, acc: kotlin.Char) -> kotlin.Char): kotlin.Char?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.DoubleArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.Double, acc: kotlin.Double) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.FloatArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.Float, acc: kotlin.Float) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.IntArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.Int, acc: kotlin.Int) -> kotlin.Int): kotlin.Int?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.LongArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.Long, acc: kotlin.Long) -> kotlin.Long): kotlin.Long?
@kotlin.SinceKotlin(version = "1.4") public inline fun kotlin.ShortArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.Short, acc: kotlin.Short) -> kotlin.Short): kotlin.Short?
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.UByte, acc: kotlin.UByte) -> kotlin.UByte): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.UInt, acc: kotlin.UInt) -> kotlin.UInt): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.ULong, acc: kotlin.ULong) -> kotlin.ULong): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, kotlin.UShort, acc: kotlin.UShort) -> kotlin.UShort): kotlin.UShort?
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.List<T>.reduceRightIndexedOrNull(/*0*/ operation: (index: kotlin.Int, T, acc: S) -> S): S?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.reduceRightOrNull(/*0*/ operation: (T, acc: S) -> S): S?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.BooleanArray.reduceRightOrNull(/*0*/ operation: (kotlin.Boolean, acc: kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.ByteArray.reduceRightOrNull(/*0*/ operation: (kotlin.Byte, acc: kotlin.Byte) -> kotlin.Byte): kotlin.Byte?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.CharArray.reduceRightOrNull(/*0*/ operation: (kotlin.Char, acc: kotlin.Char) -> kotlin.Char): kotlin.Char?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.DoubleArray.reduceRightOrNull(/*0*/ operation: (kotlin.Double, acc: kotlin.Double) -> kotlin.Double): kotlin.Double?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.FloatArray.reduceRightOrNull(/*0*/ operation: (kotlin.Float, acc: kotlin.Float) -> kotlin.Float): kotlin.Float?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.IntArray.reduceRightOrNull(/*0*/ operation: (kotlin.Int, acc: kotlin.Int) -> kotlin.Int): kotlin.Int?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.LongArray.reduceRightOrNull(/*0*/ operation: (kotlin.Long, acc: kotlin.Long) -> kotlin.Long): kotlin.Long?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun kotlin.ShortArray.reduceRightOrNull(/*0*/ operation: (kotlin.Short, acc: kotlin.Short) -> kotlin.Short): kotlin.Short?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reduceRightOrNull(/*0*/ operation: (kotlin.UByte, acc: kotlin.UByte) -> kotlin.UByte): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reduceRightOrNull(/*0*/ operation: (kotlin.UInt, acc: kotlin.UInt) -> kotlin.UInt): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reduceRightOrNull(/*0*/ operation: (kotlin.ULong, acc: kotlin.ULong) -> kotlin.ULong): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reduceRightOrNull(/*0*/ operation: (kotlin.UShort, acc: kotlin.UShort) -> kotlin.UShort): kotlin.UShort?
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.List<T>.reduceRightOrNull(/*0*/ operation: (T, acc: S) -> S): S?
@kotlin.SinceKotlin(version = "1.1") public inline fun </*0*/ S, /*1*/ T : S, /*2*/ K, /*3*/ M : kotlin.collections.MutableMap<in K, S>> kotlin.collections.Grouping<T, K>.reduceTo(/*0*/ destination: M, /*1*/ operation: (key: K, accumulator: S, element: T) -> S): M
@kotlin.internal.InlineOnly public inline fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.collections.MutableCollection<out T>.remove(/*0*/ element: T): kotlin.Boolean
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use removeAt(index) instead.", replaceWith = kotlin.ReplaceWith(expression = "removeAt(index)", imports = {})) @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.MutableList<T>.remove(/*0*/ index: kotlin.Int): T
@kotlin.internal.InlineOnly public inline fun </*0*/ @kotlin.internal.OnlyInputTypes K, /*1*/ V> kotlin.collections.MutableMap<out K, V>.remove(/*0*/ key: K): V?
public fun </*0*/ T> kotlin.collections.MutableCollection<in T>.removeAll(/*0*/ elements: kotlin.Array<out T>): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.MutableCollection<in T>.removeAll(/*0*/ elements: kotlin.collections.Iterable<T>): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.MutableCollection<in T>.removeAll(/*0*/ elements: kotlin.sequences.Sequence<T>): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.collections.MutableCollection<out T>.removeAll(/*0*/ elements: kotlin.collections.Collection<T>): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.MutableIterable<T>.removeAll(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.MutableList<T>.removeAll(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Boolean
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun </*0*/ T> kotlin.collections.MutableList<T>.removeFirst(): T
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun </*0*/ T> kotlin.collections.MutableList<T>.removeFirstOrNull(): T?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun </*0*/ T> kotlin.collections.MutableList<T>.removeLast(): T
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public fun </*0*/ T> kotlin.collections.MutableList<T>.removeLastOrNull(): T?
public fun </*0*/ T : kotlin.Any> kotlin.Array<T?>.requireNoNulls(): kotlin.Array<T>
public fun </*0*/ T : kotlin.Any> kotlin.collections.Iterable<T?>.requireNoNulls(): kotlin.collections.Iterable<T>
public fun </*0*/ T : kotlin.Any> kotlin.collections.List<T?>.requireNoNulls(): kotlin.collections.List<T>
public fun </*0*/ T> kotlin.collections.MutableCollection<in T>.retainAll(/*0*/ elements: kotlin.Array<out T>): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.MutableCollection<in T>.retainAll(/*0*/ elements: kotlin.collections.Iterable<T>): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.MutableCollection<in T>.retainAll(/*0*/ elements: kotlin.sequences.Sequence<T>): kotlin.Boolean
@kotlin.internal.InlineOnly public inline fun </*0*/ @kotlin.internal.OnlyInputTypes T> kotlin.collections.MutableCollection<out T>.retainAll(/*0*/ elements: kotlin.collections.Collection<T>): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.MutableIterable<T>.retainAll(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Boolean
public fun </*0*/ T> kotlin.collections.MutableList<T>.retainAll(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.Boolean
public fun </*0*/ T> kotlin.Array<T>.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T> kotlin.Array<T>.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.BooleanArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.BooleanArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.ByteArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.ByteArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.CharArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.CharArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.DoubleArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.DoubleArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.FloatArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.FloatArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.IntArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.IntArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.LongArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.LongArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.ShortArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.ShortArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reverse(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reverse(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun </*0*/ T> kotlin.collections.MutableList<T>.reverse(): kotlin.Unit
public fun </*0*/ T> kotlin.Array<out T>.reversed(): kotlin.collections.List<T>
public fun kotlin.BooleanArray.reversed(): kotlin.collections.List<kotlin.Boolean>
public fun kotlin.ByteArray.reversed(): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.reversed(): kotlin.collections.List<kotlin.Char>
public fun kotlin.DoubleArray.reversed(): kotlin.collections.List<kotlin.Double>
public fun kotlin.FloatArray.reversed(): kotlin.collections.List<kotlin.Float>
public fun kotlin.IntArray.reversed(): kotlin.collections.List<kotlin.Int>
public fun kotlin.LongArray.reversed(): kotlin.collections.List<kotlin.Long>
public fun kotlin.ShortArray.reversed(): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.reversed(): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.reversed(): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.reversed(): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.reversed(): kotlin.collections.List<kotlin.UShort>
public fun </*0*/ T> kotlin.collections.Iterable<T>.reversed(): kotlin.collections.List<T>
public fun </*0*/ T> kotlin.Array<T>.reversedArray(): kotlin.Array<T>
public fun kotlin.BooleanArray.reversedArray(): kotlin.BooleanArray
public fun kotlin.ByteArray.reversedArray(): kotlin.ByteArray
public fun kotlin.CharArray.reversedArray(): kotlin.CharArray
public fun kotlin.DoubleArray.reversedArray(): kotlin.DoubleArray
public fun kotlin.FloatArray.reversedArray(): kotlin.FloatArray
public fun kotlin.IntArray.reversedArray(): kotlin.IntArray
public fun kotlin.LongArray.reversedArray(): kotlin.LongArray
public fun kotlin.ShortArray.reversedArray(): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.reversedArray(): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.reversedArray(): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.reversedArray(): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.reversedArray(): kotlin.UShortArray
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, T) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Boolean) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Byte) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Char) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Double) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Float) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Int) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Long) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Short) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UByte) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UInt) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.ULong) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UShort) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.runningFold(/*0*/ initial: R, /*1*/ operation: (acc: R, T) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, T) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Boolean) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Byte) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Char) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Double) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Float) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Int) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Long) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Short) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UByte) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UInt) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.ULong) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UShort) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.runningFoldIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, T) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.runningReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.runningReduce(/*0*/ operation: (acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.runningReduce(/*0*/ operation: (acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.collections.List<kotlin.Byte>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.runningReduce(/*0*/ operation: (acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.collections.List<kotlin.Char>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.runningReduce(/*0*/ operation: (acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.collections.List<kotlin.Double>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.runningReduce(/*0*/ operation: (acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.collections.List<kotlin.Float>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.runningReduce(/*0*/ operation: (acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.collections.List<kotlin.Int>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.runningReduce(/*0*/ operation: (acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.collections.List<kotlin.Long>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.runningReduce(/*0*/ operation: (acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.runningReduce(/*0*/ operation: (acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.runningReduce(/*0*/ operation: (acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.runningReduce(/*0*/ operation: (acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.runningReduce(/*0*/ operation: (acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.collections.List<kotlin.UShort>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.runningReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): kotlin.collections.List<S>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.collections.List<kotlin.Byte>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.collections.List<kotlin.Char>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.collections.List<kotlin.Double>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.collections.List<kotlin.Float>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.collections.List<kotlin.Int>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.collections.List<kotlin.Long>
@kotlin.SinceKotlin(version = "1.4") @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.collections.List<kotlin.UShort>
@kotlin.SinceKotlin(version = "1.4") public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.runningReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): kotlin.collections.List<S>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, T) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Boolean) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Byte) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Char) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Double) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Float) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Int) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Long) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.Short) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UByte) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UInt) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.ULong) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, kotlin.UShort) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.scan(/*0*/ initial: R, /*1*/ operation: (acc: R, T) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, T) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.BooleanArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Boolean) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ByteArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Byte) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.CharArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Char) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.DoubleArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Double) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.FloatArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Float) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.IntArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Int) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.LongArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Long) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ShortArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.Short) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UByteArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UByte) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UIntArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UInt) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.ULongArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.ULong) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R> kotlin.UShortArray.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, kotlin.UShort) -> R): kotlin.collections.List<R>
@kotlin.SinceKotlin(version = "1.4") @kotlin.WasExperimental(markerClass = {kotlin.ExperimentalStdlibApi::class}) public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.scanIndexed(/*0*/ initial: R, /*1*/ operation: (index: kotlin.Int, acc: R, T) -> R): kotlin.collections.List<R>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.scanReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.scanReduce(/*0*/ operation: (acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.scanReduce(/*0*/ operation: (acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.collections.List<kotlin.Byte>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.scanReduce(/*0*/ operation: (acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.collections.List<kotlin.Char>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.scanReduce(/*0*/ operation: (acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.collections.List<kotlin.Double>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.scanReduce(/*0*/ operation: (acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.collections.List<kotlin.Float>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.scanReduce(/*0*/ operation: (acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.collections.List<kotlin.Int>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.scanReduce(/*0*/ operation: (acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.collections.List<kotlin.Long>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.scanReduce(/*0*/ operation: (acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.collections.List<kotlin.Short>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.scanReduce(/*0*/ operation: (acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.collections.List<kotlin.UByte>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.scanReduce(/*0*/ operation: (acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.collections.List<kotlin.UInt>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.scanReduce(/*0*/ operation: (acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.collections.List<kotlin.ULong>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.scanReduce(/*0*/ operation: (acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.collections.List<kotlin.UShort>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduce instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduce(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.scanReduce(/*0*/ operation: (acc: S, T) -> S): kotlin.collections.List<S>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.Array<out T>.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): kotlin.collections.List<S>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Boolean, kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Byte, kotlin.Byte) -> kotlin.Byte): kotlin.collections.List<kotlin.Byte>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Char, kotlin.Char) -> kotlin.Char): kotlin.collections.List<kotlin.Char>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Double, kotlin.Double) -> kotlin.Double): kotlin.collections.List<kotlin.Double>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Float, kotlin.Float) -> kotlin.Float): kotlin.collections.List<kotlin.Float>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Int, kotlin.Int) -> kotlin.Int): kotlin.collections.List<kotlin.Int>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Long, kotlin.Long) -> kotlin.Long): kotlin.collections.List<kotlin.Long>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.Short, kotlin.Short) -> kotlin.Short): kotlin.collections.List<kotlin.Short>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UByte, kotlin.UByte) -> kotlin.UByte): kotlin.collections.List<kotlin.UByte>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UInt, kotlin.UInt) -> kotlin.UInt): kotlin.collections.List<kotlin.UInt>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.ULong, kotlin.ULong) -> kotlin.ULong): kotlin.collections.List<kotlin.ULong>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: kotlin.UShort, kotlin.UShort) -> kotlin.UShort): kotlin.collections.List<kotlin.UShort>
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "Use runningReduceIndexed instead.", replaceWith = kotlin.ReplaceWith(expression = "runningReduceIndexed(operation)", imports = {})) @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public inline fun </*0*/ S, /*1*/ T : S> kotlin.collections.Iterable<T>.scanReduceIndexed(/*0*/ operation: (index: kotlin.Int, acc: S, T) -> S): kotlin.collections.List<S>
@kotlin.internal.InlineOnly public inline operator fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<K, V>.set(/*0*/ key: K, /*1*/ value: V): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun </*0*/ V> kotlin.collections.MutableMap<in kotlin.String, in V>.setValue(/*0*/ thisRef: kotlin.Any?, /*1*/ property: kotlin.reflect.KProperty<*>, /*2*/ value: V): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T> kotlin.Array<T>.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T> kotlin.Array<T>.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.BooleanArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.BooleanArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.ByteArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.ByteArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.CharArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.CharArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.DoubleArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.DoubleArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.FloatArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.FloatArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.IntArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.IntArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.LongArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.LongArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.ShortArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.ShortArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.2") public fun </*0*/ T> kotlin.collections.MutableList<T>.shuffle(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") public fun </*0*/ T> kotlin.collections.MutableList<T>.shuffle(/*0*/ random: kotlin.random.Random): kotlin.Unit
@kotlin.SinceKotlin(version = "1.2") public fun </*0*/ T> kotlin.collections.Iterable<T>.shuffled(): kotlin.collections.List<T>
@kotlin.SinceKotlin(version = "1.3") public fun </*0*/ T> kotlin.collections.Iterable<T>.shuffled(/*0*/ random: kotlin.random.Random): kotlin.collections.List<T>
public fun </*0*/ T> kotlin.Array<out T>.single(): T
public inline fun </*0*/ T> kotlin.Array<out T>.single(/*0*/ predicate: (T) -> kotlin.Boolean): T
public fun kotlin.BooleanArray.single(): kotlin.Boolean
public inline fun kotlin.BooleanArray.single(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean
public fun kotlin.ByteArray.single(): kotlin.Byte
public inline fun kotlin.ByteArray.single(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Byte
public fun kotlin.CharArray.single(): kotlin.Char
public inline fun kotlin.CharArray.single(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Char
public fun kotlin.DoubleArray.single(): kotlin.Double
public inline fun kotlin.DoubleArray.single(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Double
public fun kotlin.FloatArray.single(): kotlin.Float
public inline fun kotlin.FloatArray.single(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Float
public fun kotlin.IntArray.single(): kotlin.Int
public inline fun kotlin.IntArray.single(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Int
public fun kotlin.LongArray.single(): kotlin.Long
public inline fun kotlin.LongArray.single(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Long
public fun kotlin.ShortArray.single(): kotlin.Short
public inline fun kotlin.ShortArray.single(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Short
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.single(): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.single(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.UByte
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.single(): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.single(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.single(): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.single(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.single(): kotlin.UShort
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.single(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.UShort
public fun </*0*/ T> kotlin.collections.Iterable<T>.single(): T
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.single(/*0*/ predicate: (T) -> kotlin.Boolean): T
public fun </*0*/ T> kotlin.collections.List<T>.single(): T
public fun </*0*/ T> kotlin.Array<out T>.singleOrNull(): T?
public inline fun </*0*/ T> kotlin.Array<out T>.singleOrNull(/*0*/ predicate: (T) -> kotlin.Boolean): T?
public fun kotlin.BooleanArray.singleOrNull(): kotlin.Boolean?
public inline fun kotlin.BooleanArray.singleOrNull(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.Boolean?
public fun kotlin.ByteArray.singleOrNull(): kotlin.Byte?
public inline fun kotlin.ByteArray.singleOrNull(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.Byte?
public fun kotlin.CharArray.singleOrNull(): kotlin.Char?
public inline fun kotlin.CharArray.singleOrNull(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.Char?
public fun kotlin.DoubleArray.singleOrNull(): kotlin.Double?
public inline fun kotlin.DoubleArray.singleOrNull(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.Double?
public fun kotlin.FloatArray.singleOrNull(): kotlin.Float?
public inline fun kotlin.FloatArray.singleOrNull(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.Float?
public fun kotlin.IntArray.singleOrNull(): kotlin.Int?
public inline fun kotlin.IntArray.singleOrNull(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.Int?
public fun kotlin.LongArray.singleOrNull(): kotlin.Long?
public inline fun kotlin.LongArray.singleOrNull(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.Long?
public fun kotlin.ShortArray.singleOrNull(): kotlin.Short?
public inline fun kotlin.ShortArray.singleOrNull(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.Short?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.singleOrNull(): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.singleOrNull(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.UByte?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.singleOrNull(): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.singleOrNull(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.UInt?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.singleOrNull(): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.singleOrNull(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.ULong?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.singleOrNull(): kotlin.UShort?
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.singleOrNull(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.UShort?
public fun </*0*/ T> kotlin.collections.Iterable<T>.singleOrNull(): T?
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.singleOrNull(/*0*/ predicate: (T) -> kotlin.Boolean): T?
public fun </*0*/ T> kotlin.collections.List<T>.singleOrNull(): T?
public fun </*0*/ T> kotlin.Array<out T>.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<T>
public fun </*0*/ T> kotlin.Array<out T>.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<T>
public fun kotlin.BooleanArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.Boolean>
public fun kotlin.BooleanArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.Boolean>
public fun kotlin.ByteArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.Byte>
public fun kotlin.ByteArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.Char>
public fun kotlin.CharArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.Char>
public fun kotlin.DoubleArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.Double>
public fun kotlin.DoubleArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.Double>
public fun kotlin.FloatArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.Float>
public fun kotlin.FloatArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.Float>
public fun kotlin.IntArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.Int>
public fun kotlin.IntArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.Int>
public fun kotlin.LongArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.Long>
public fun kotlin.LongArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.Long>
public fun kotlin.ShortArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.Short>
public fun kotlin.ShortArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<kotlin.UShort>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<kotlin.UShort>
public fun </*0*/ T> kotlin.collections.List<T>.slice(/*0*/ indices: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.List<T>
public fun </*0*/ T> kotlin.collections.List<T>.slice(/*0*/ indices: kotlin.ranges.IntRange): kotlin.collections.List<T>
public fun </*0*/ T> kotlin.Array<T>.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.Array<T>
public fun </*0*/ T> kotlin.Array<T>.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.Array<T>
public fun kotlin.BooleanArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.BooleanArray
public fun kotlin.BooleanArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.BooleanArray
public fun kotlin.ByteArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.ByteArray
public fun kotlin.ByteArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.ByteArray
public fun kotlin.CharArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.CharArray
public fun kotlin.CharArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.CharArray
public fun kotlin.DoubleArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.DoubleArray
public fun kotlin.DoubleArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.DoubleArray
public fun kotlin.FloatArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.FloatArray
public fun kotlin.FloatArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.FloatArray
public fun kotlin.IntArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.IntArray
public fun kotlin.IntArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.IntArray
public fun kotlin.LongArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.LongArray
public fun kotlin.LongArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.LongArray
public fun kotlin.ShortArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.ShortArray
public fun kotlin.ShortArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.sliceArray(/*0*/ indices: kotlin.collections.Collection<kotlin.Int>): kotlin.UShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.sliceArray(/*0*/ indices: kotlin.ranges.IntRange): kotlin.UShortArray
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.Array<out T>.sort(): kotlin.Unit
public fun </*0*/ T> kotlin.Array<out T>.sort(/*0*/ comparison: (a: T, b: T) -> kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T : kotlin.Comparable<T>> kotlin.Array<out T>.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.js.library(name = "primitiveArraySort") public fun kotlin.ByteArray.sort(): kotlin.Unit
@kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.sort(/*0*/ noinline comparison: (a: kotlin.Byte, b: kotlin.Byte) -> kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.ByteArray.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.js.library(name = "primitiveArraySort") public fun kotlin.CharArray.sort(): kotlin.Unit
@kotlin.internal.InlineOnly public inline fun kotlin.CharArray.sort(/*0*/ noinline comparison: (a: kotlin.Char, b: kotlin.Char) -> kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.CharArray.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.js.library(name = "primitiveArraySort") public fun kotlin.DoubleArray.sort(): kotlin.Unit
@kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.sort(/*0*/ noinline comparison: (a: kotlin.Double, b: kotlin.Double) -> kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.DoubleArray.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.js.library(name = "primitiveArraySort") public fun kotlin.FloatArray.sort(): kotlin.Unit
@kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.sort(/*0*/ noinline comparison: (a: kotlin.Float, b: kotlin.Float) -> kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.FloatArray.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.js.library(name = "primitiveArraySort") public fun kotlin.IntArray.sort(): kotlin.Unit
@kotlin.internal.InlineOnly public inline fun kotlin.IntArray.sort(/*0*/ noinline comparison: (a: kotlin.Int, b: kotlin.Int) -> kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.IntArray.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
public fun kotlin.LongArray.sort(): kotlin.Unit
@kotlin.internal.InlineOnly public inline fun kotlin.LongArray.sort(/*0*/ noinline comparison: (a: kotlin.Long, b: kotlin.Long) -> kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.LongArray.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.js.library(name = "primitiveArraySort") public fun kotlin.ShortArray.sort(): kotlin.Unit
@kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.sort(/*0*/ noinline comparison: (a: kotlin.Short, b: kotlin.Short) -> kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.ShortArray.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.sort(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.sort(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.sort(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.sort(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.sort(/*0*/ fromIndex: kotlin.Int = ..., /*1*/ toIndex: kotlin.Int = ...): kotlin.Unit
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.collections.MutableList<T>.sort(): kotlin.Unit
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.Array<out T>.sortBy(/*0*/ crossinline selector: (T) -> R?): kotlin.Unit
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.collections.MutableList<T>.sortBy(/*0*/ crossinline selector: (T) -> R?): kotlin.Unit
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.Array<out T>.sortByDescending(/*0*/ crossinline selector: (T) -> R?): kotlin.Unit
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.collections.MutableList<T>.sortByDescending(/*0*/ crossinline selector: (T) -> R?): kotlin.Unit
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.Array<out T>.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T : kotlin.Comparable<T>> kotlin.Array<out T>.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.ByteArray.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.ByteArray.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.CharArray.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.CharArray.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.DoubleArray.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.DoubleArray.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.FloatArray.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.FloatArray.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.IntArray.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.IntArray.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.LongArray.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.LongArray.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun kotlin.ShortArray.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun kotlin.ShortArray.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.sortDescending(): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.sortDescending(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.collections.MutableList<T>.sortDescending(): kotlin.Unit
public fun </*0*/ T> kotlin.Array<out T>.sortWith(/*0*/ comparator: kotlin.Comparator<in T>): kotlin.Unit
@kotlin.SinceKotlin(version = "1.4") public fun </*0*/ T> kotlin.Array<out T>.sortWith(/*0*/ comparator: kotlin.Comparator<in T>, /*1*/ fromIndex: kotlin.Int = ..., /*2*/ toIndex: kotlin.Int = ...): kotlin.Unit
public fun </*0*/ T> kotlin.collections.MutableList<T>.sortWith(/*0*/ comparator: kotlin.Comparator<in T>): kotlin.Unit
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.Array<out T>.sorted(): kotlin.collections.List<T>
public fun kotlin.ByteArray.sorted(): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.sorted(): kotlin.collections.List<kotlin.Char>
public fun kotlin.DoubleArray.sorted(): kotlin.collections.List<kotlin.Double>
public fun kotlin.FloatArray.sorted(): kotlin.collections.List<kotlin.Float>
public fun kotlin.IntArray.sorted(): kotlin.collections.List<kotlin.Int>
public fun kotlin.LongArray.sorted(): kotlin.collections.List<kotlin.Long>
public fun kotlin.ShortArray.sorted(): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.sorted(): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.sorted(): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.sorted(): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.sorted(): kotlin.collections.List<kotlin.UShort>
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.collections.Iterable<T>.sorted(): kotlin.collections.List<T>
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.Array<T>.sortedArray(): kotlin.Array<T>
public fun kotlin.ByteArray.sortedArray(): kotlin.ByteArray
public fun kotlin.CharArray.sortedArray(): kotlin.CharArray
public fun kotlin.DoubleArray.sortedArray(): kotlin.DoubleArray
public fun kotlin.FloatArray.sortedArray(): kotlin.FloatArray
public fun kotlin.IntArray.sortedArray(): kotlin.IntArray
public fun kotlin.LongArray.sortedArray(): kotlin.LongArray
public fun kotlin.ShortArray.sortedArray(): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.sortedArray(): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.sortedArray(): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.sortedArray(): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.sortedArray(): kotlin.UShortArray
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.Array<T>.sortedArrayDescending(): kotlin.Array<T>
public fun kotlin.ByteArray.sortedArrayDescending(): kotlin.ByteArray
public fun kotlin.CharArray.sortedArrayDescending(): kotlin.CharArray
public fun kotlin.DoubleArray.sortedArrayDescending(): kotlin.DoubleArray
public fun kotlin.FloatArray.sortedArrayDescending(): kotlin.FloatArray
public fun kotlin.IntArray.sortedArrayDescending(): kotlin.IntArray
public fun kotlin.LongArray.sortedArrayDescending(): kotlin.LongArray
public fun kotlin.ShortArray.sortedArrayDescending(): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.sortedArrayDescending(): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.sortedArrayDescending(): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.sortedArrayDescending(): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.sortedArrayDescending(): kotlin.UShortArray
public fun </*0*/ T> kotlin.Array<out T>.sortedArrayWith(/*0*/ comparator: kotlin.Comparator<in T>): kotlin.Array<out T>
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.Array<out T>.sortedBy(/*0*/ crossinline selector: (T) -> R?): kotlin.collections.List<T>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.BooleanArray.sortedBy(/*0*/ crossinline selector: (kotlin.Boolean) -> R?): kotlin.collections.List<kotlin.Boolean>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ByteArray.sortedBy(/*0*/ crossinline selector: (kotlin.Byte) -> R?): kotlin.collections.List<kotlin.Byte>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.CharArray.sortedBy(/*0*/ crossinline selector: (kotlin.Char) -> R?): kotlin.collections.List<kotlin.Char>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.DoubleArray.sortedBy(/*0*/ crossinline selector: (kotlin.Double) -> R?): kotlin.collections.List<kotlin.Double>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.FloatArray.sortedBy(/*0*/ crossinline selector: (kotlin.Float) -> R?): kotlin.collections.List<kotlin.Float>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.IntArray.sortedBy(/*0*/ crossinline selector: (kotlin.Int) -> R?): kotlin.collections.List<kotlin.Int>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.LongArray.sortedBy(/*0*/ crossinline selector: (kotlin.Long) -> R?): kotlin.collections.List<kotlin.Long>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ShortArray.sortedBy(/*0*/ crossinline selector: (kotlin.Short) -> R?): kotlin.collections.List<kotlin.Short>
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.collections.Iterable<T>.sortedBy(/*0*/ crossinline selector: (T) -> R?): kotlin.collections.List<T>
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.Array<out T>.sortedByDescending(/*0*/ crossinline selector: (T) -> R?): kotlin.collections.List<T>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.BooleanArray.sortedByDescending(/*0*/ crossinline selector: (kotlin.Boolean) -> R?): kotlin.collections.List<kotlin.Boolean>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ByteArray.sortedByDescending(/*0*/ crossinline selector: (kotlin.Byte) -> R?): kotlin.collections.List<kotlin.Byte>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.CharArray.sortedByDescending(/*0*/ crossinline selector: (kotlin.Char) -> R?): kotlin.collections.List<kotlin.Char>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.DoubleArray.sortedByDescending(/*0*/ crossinline selector: (kotlin.Double) -> R?): kotlin.collections.List<kotlin.Double>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.FloatArray.sortedByDescending(/*0*/ crossinline selector: (kotlin.Float) -> R?): kotlin.collections.List<kotlin.Float>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.IntArray.sortedByDescending(/*0*/ crossinline selector: (kotlin.Int) -> R?): kotlin.collections.List<kotlin.Int>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.LongArray.sortedByDescending(/*0*/ crossinline selector: (kotlin.Long) -> R?): kotlin.collections.List<kotlin.Long>
public inline fun </*0*/ R : kotlin.Comparable<R>> kotlin.ShortArray.sortedByDescending(/*0*/ crossinline selector: (kotlin.Short) -> R?): kotlin.collections.List<kotlin.Short>
public inline fun </*0*/ T, /*1*/ R : kotlin.Comparable<R>> kotlin.collections.Iterable<T>.sortedByDescending(/*0*/ crossinline selector: (T) -> R?): kotlin.collections.List<T>
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.Array<out T>.sortedDescending(): kotlin.collections.List<T>
public fun kotlin.ByteArray.sortedDescending(): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.sortedDescending(): kotlin.collections.List<kotlin.Char>
public fun kotlin.DoubleArray.sortedDescending(): kotlin.collections.List<kotlin.Double>
public fun kotlin.FloatArray.sortedDescending(): kotlin.collections.List<kotlin.Float>
public fun kotlin.IntArray.sortedDescending(): kotlin.collections.List<kotlin.Int>
public fun kotlin.LongArray.sortedDescending(): kotlin.collections.List<kotlin.Long>
public fun kotlin.ShortArray.sortedDescending(): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.sortedDescending(): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.sortedDescending(): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.sortedDescending(): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.sortedDescending(): kotlin.collections.List<kotlin.UShort>
public fun </*0*/ T : kotlin.Comparable<T>> kotlin.collections.Iterable<T>.sortedDescending(): kotlin.collections.List<T>
public fun </*0*/ T> kotlin.Array<out T>.sortedWith(/*0*/ comparator: kotlin.Comparator<in T>): kotlin.collections.List<T>
public fun kotlin.BooleanArray.sortedWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Boolean>): kotlin.collections.List<kotlin.Boolean>
public fun kotlin.ByteArray.sortedWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Byte>): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.sortedWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Char>): kotlin.collections.List<kotlin.Char>
public fun kotlin.DoubleArray.sortedWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Double>): kotlin.collections.List<kotlin.Double>
public fun kotlin.FloatArray.sortedWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Float>): kotlin.collections.List<kotlin.Float>
public fun kotlin.IntArray.sortedWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Int>): kotlin.collections.List<kotlin.Int>
public fun kotlin.LongArray.sortedWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Long>): kotlin.collections.List<kotlin.Long>
public fun kotlin.ShortArray.sortedWith(/*0*/ comparator: kotlin.Comparator<in kotlin.Short>): kotlin.collections.List<kotlin.Short>
public fun </*0*/ T> kotlin.collections.Iterable<T>.sortedWith(/*0*/ comparator: kotlin.Comparator<in T>): kotlin.collections.List<T>
public infix fun </*0*/ T> kotlin.Array<out T>.subtract(/*0*/ other: kotlin.collections.Iterable<T>): kotlin.collections.Set<T>
public infix fun kotlin.BooleanArray.subtract(/*0*/ other: kotlin.collections.Iterable<kotlin.Boolean>): kotlin.collections.Set<kotlin.Boolean>
public infix fun kotlin.ByteArray.subtract(/*0*/ other: kotlin.collections.Iterable<kotlin.Byte>): kotlin.collections.Set<kotlin.Byte>
public infix fun kotlin.CharArray.subtract(/*0*/ other: kotlin.collections.Iterable<kotlin.Char>): kotlin.collections.Set<kotlin.Char>
public infix fun kotlin.DoubleArray.subtract(/*0*/ other: kotlin.collections.Iterable<kotlin.Double>): kotlin.collections.Set<kotlin.Double>
public infix fun kotlin.FloatArray.subtract(/*0*/ other: kotlin.collections.Iterable<kotlin.Float>): kotlin.collections.Set<kotlin.Float>
public infix fun kotlin.IntArray.subtract(/*0*/ other: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.Set<kotlin.Int>
public infix fun kotlin.LongArray.subtract(/*0*/ other: kotlin.collections.Iterable<kotlin.Long>): kotlin.collections.Set<kotlin.Long>
public infix fun kotlin.ShortArray.subtract(/*0*/ other: kotlin.collections.Iterable<kotlin.Short>): kotlin.collections.Set<kotlin.Short>
public infix fun </*0*/ T> kotlin.collections.Iterable<T>.subtract(/*0*/ other: kotlin.collections.Iterable<T>): kotlin.collections.Set<T>
@kotlin.jvm.JvmName(name = "sumOfByte") public fun kotlin.Array<out kotlin.Byte>.sum(): kotlin.Int
@kotlin.jvm.JvmName(name = "sumOfDouble") public fun kotlin.Array<out kotlin.Double>.sum(): kotlin.Double
@kotlin.jvm.JvmName(name = "sumOfFloat") public fun kotlin.Array<out kotlin.Float>.sum(): kotlin.Float
@kotlin.jvm.JvmName(name = "sumOfInt") public fun kotlin.Array<out kotlin.Int>.sum(): kotlin.Int
@kotlin.jvm.JvmName(name = "sumOfLong") public fun kotlin.Array<out kotlin.Long>.sum(): kotlin.Long
@kotlin.jvm.JvmName(name = "sumOfShort") public fun kotlin.Array<out kotlin.Short>.sum(): kotlin.Int
@kotlin.jvm.JvmName(name = "sumOfUByte") @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.Array<out kotlin.UByte>.sum(): kotlin.UInt
@kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.Array<out kotlin.UInt>.sum(): kotlin.UInt
@kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.Array<out kotlin.ULong>.sum(): kotlin.ULong
@kotlin.jvm.JvmName(name = "sumOfUShort") @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.Array<out kotlin.UShort>.sum(): kotlin.UInt
public fun kotlin.ByteArray.sum(): kotlin.Int
public fun kotlin.DoubleArray.sum(): kotlin.Double
public fun kotlin.FloatArray.sum(): kotlin.Float
public fun kotlin.IntArray.sum(): kotlin.Int
public fun kotlin.LongArray.sum(): kotlin.Long
public fun kotlin.ShortArray.sum(): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.sum(): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.sum(): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.sum(): kotlin.ULong
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.sum(): kotlin.UInt
@kotlin.jvm.JvmName(name = "sumOfByte") public fun kotlin.collections.Iterable<kotlin.Byte>.sum(): kotlin.Int
@kotlin.jvm.JvmName(name = "sumOfDouble") public fun kotlin.collections.Iterable<kotlin.Double>.sum(): kotlin.Double
@kotlin.jvm.JvmName(name = "sumOfFloat") public fun kotlin.collections.Iterable<kotlin.Float>.sum(): kotlin.Float
@kotlin.jvm.JvmName(name = "sumOfInt") public fun kotlin.collections.Iterable<kotlin.Int>.sum(): kotlin.Int
@kotlin.jvm.JvmName(name = "sumOfLong") public fun kotlin.collections.Iterable<kotlin.Long>.sum(): kotlin.Long
@kotlin.jvm.JvmName(name = "sumOfShort") public fun kotlin.collections.Iterable<kotlin.Short>.sum(): kotlin.Int
@kotlin.jvm.JvmName(name = "sumOfUByte") @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.collections.Iterable<kotlin.UByte>.sum(): kotlin.UInt
@kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.collections.Iterable<kotlin.UInt>.sum(): kotlin.UInt
@kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.collections.Iterable<kotlin.ULong>.sum(): kotlin.ULong
@kotlin.jvm.JvmName(name = "sumOfUShort") @kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.collections.Iterable<kotlin.UShort>.sum(): kotlin.UInt
public inline fun </*0*/ T> kotlin.Array<out T>.sumBy(/*0*/ selector: (T) -> kotlin.Int): kotlin.Int
public inline fun kotlin.BooleanArray.sumBy(/*0*/ selector: (kotlin.Boolean) -> kotlin.Int): kotlin.Int
public inline fun kotlin.ByteArray.sumBy(/*0*/ selector: (kotlin.Byte) -> kotlin.Int): kotlin.Int
public inline fun kotlin.CharArray.sumBy(/*0*/ selector: (kotlin.Char) -> kotlin.Int): kotlin.Int
public inline fun kotlin.DoubleArray.sumBy(/*0*/ selector: (kotlin.Double) -> kotlin.Int): kotlin.Int
public inline fun kotlin.FloatArray.sumBy(/*0*/ selector: (kotlin.Float) -> kotlin.Int): kotlin.Int
public inline fun kotlin.IntArray.sumBy(/*0*/ selector: (kotlin.Int) -> kotlin.Int): kotlin.Int
public inline fun kotlin.LongArray.sumBy(/*0*/ selector: (kotlin.Long) -> kotlin.Int): kotlin.Int
public inline fun kotlin.ShortArray.sumBy(/*0*/ selector: (kotlin.Short) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.sumBy(/*0*/ selector: (kotlin.UByte) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.sumBy(/*0*/ selector: (kotlin.UInt) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.sumBy(/*0*/ selector: (kotlin.ULong) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.sumBy(/*0*/ selector: (kotlin.UShort) -> kotlin.UInt): kotlin.UInt
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.sumBy(/*0*/ selector: (T) -> kotlin.Int): kotlin.Int
public inline fun </*0*/ T> kotlin.Array<out T>.sumByDouble(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double
public inline fun kotlin.BooleanArray.sumByDouble(/*0*/ selector: (kotlin.Boolean) -> kotlin.Double): kotlin.Double
public inline fun kotlin.ByteArray.sumByDouble(/*0*/ selector: (kotlin.Byte) -> kotlin.Double): kotlin.Double
public inline fun kotlin.CharArray.sumByDouble(/*0*/ selector: (kotlin.Char) -> kotlin.Double): kotlin.Double
public inline fun kotlin.DoubleArray.sumByDouble(/*0*/ selector: (kotlin.Double) -> kotlin.Double): kotlin.Double
public inline fun kotlin.FloatArray.sumByDouble(/*0*/ selector: (kotlin.Float) -> kotlin.Double): kotlin.Double
public inline fun kotlin.IntArray.sumByDouble(/*0*/ selector: (kotlin.Int) -> kotlin.Double): kotlin.Double
public inline fun kotlin.LongArray.sumByDouble(/*0*/ selector: (kotlin.Long) -> kotlin.Double): kotlin.Double
public inline fun kotlin.ShortArray.sumByDouble(/*0*/ selector: (kotlin.Short) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.sumByDouble(/*0*/ selector: (kotlin.UByte) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.sumByDouble(/*0*/ selector: (kotlin.UInt) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.sumByDouble(/*0*/ selector: (kotlin.ULong) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.sumByDouble(/*0*/ selector: (kotlin.UShort) -> kotlin.Double): kotlin.Double
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.sumByDouble(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.sumOf(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.sumOf(/*0*/ selector: (T) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.sumOf(/*0*/ selector: (T) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.sumOf(/*0*/ selector: (T) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.Array<out T>.sumOf(/*0*/ selector: (T) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.sumOf(/*0*/ selector: (kotlin.Boolean) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.sumOf(/*0*/ selector: (kotlin.Boolean) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.sumOf(/*0*/ selector: (kotlin.Boolean) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.sumOf(/*0*/ selector: (kotlin.Boolean) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.BooleanArray.sumOf(/*0*/ selector: (kotlin.Boolean) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.sumOf(/*0*/ selector: (kotlin.Byte) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.sumOf(/*0*/ selector: (kotlin.Byte) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.sumOf(/*0*/ selector: (kotlin.Byte) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.sumOf(/*0*/ selector: (kotlin.Byte) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.sumOf(/*0*/ selector: (kotlin.Byte) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.sumOf(/*0*/ selector: (kotlin.Char) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.sumOf(/*0*/ selector: (kotlin.Char) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.sumOf(/*0*/ selector: (kotlin.Char) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.sumOf(/*0*/ selector: (kotlin.Char) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.CharArray.sumOf(/*0*/ selector: (kotlin.Char) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.sumOf(/*0*/ selector: (kotlin.Double) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.sumOf(/*0*/ selector: (kotlin.Double) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.sumOf(/*0*/ selector: (kotlin.Double) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.sumOf(/*0*/ selector: (kotlin.Double) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.DoubleArray.sumOf(/*0*/ selector: (kotlin.Double) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.sumOf(/*0*/ selector: (kotlin.Float) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.sumOf(/*0*/ selector: (kotlin.Float) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.sumOf(/*0*/ selector: (kotlin.Float) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.sumOf(/*0*/ selector: (kotlin.Float) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.FloatArray.sumOf(/*0*/ selector: (kotlin.Float) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.sumOf(/*0*/ selector: (kotlin.Int) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.sumOf(/*0*/ selector: (kotlin.Int) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.sumOf(/*0*/ selector: (kotlin.Int) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.sumOf(/*0*/ selector: (kotlin.Int) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.sumOf(/*0*/ selector: (kotlin.Int) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.sumOf(/*0*/ selector: (kotlin.Long) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.sumOf(/*0*/ selector: (kotlin.Long) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.sumOf(/*0*/ selector: (kotlin.Long) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.sumOf(/*0*/ selector: (kotlin.Long) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.sumOf(/*0*/ selector: (kotlin.Long) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.sumOf(/*0*/ selector: (kotlin.Short) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.sumOf(/*0*/ selector: (kotlin.Short) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.sumOf(/*0*/ selector: (kotlin.Short) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.sumOf(/*0*/ selector: (kotlin.Short) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.sumOf(/*0*/ selector: (kotlin.Short) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.sumOf(/*0*/ selector: (kotlin.UByte) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.sumOf(/*0*/ selector: (kotlin.UByte) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.sumOf(/*0*/ selector: (kotlin.UByte) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.sumOf(/*0*/ selector: (kotlin.UByte) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.sumOf(/*0*/ selector: (kotlin.UByte) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.sumOf(/*0*/ selector: (kotlin.UInt) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.sumOf(/*0*/ selector: (kotlin.UInt) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.sumOf(/*0*/ selector: (kotlin.UInt) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.sumOf(/*0*/ selector: (kotlin.UInt) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.sumOf(/*0*/ selector: (kotlin.UInt) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.sumOf(/*0*/ selector: (kotlin.ULong) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.sumOf(/*0*/ selector: (kotlin.ULong) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.sumOf(/*0*/ selector: (kotlin.ULong) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.sumOf(/*0*/ selector: (kotlin.ULong) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.sumOf(/*0*/ selector: (kotlin.ULong) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.sumOf(/*0*/ selector: (kotlin.UShort) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.sumOf(/*0*/ selector: (kotlin.UShort) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.sumOf(/*0*/ selector: (kotlin.UShort) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.sumOf(/*0*/ selector: (kotlin.UShort) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.sumOf(/*0*/ selector: (kotlin.UShort) -> kotlin.ULong): kotlin.ULong
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfDouble") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.sumOf(/*0*/ selector: (T) -> kotlin.Double): kotlin.Double
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfInt") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.sumOf(/*0*/ selector: (T) -> kotlin.Int): kotlin.Int
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfLong") @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.sumOf(/*0*/ selector: (T) -> kotlin.Long): kotlin.Long
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfUInt") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.sumOf(/*0*/ selector: (T) -> kotlin.UInt): kotlin.UInt
@kotlin.SinceKotlin(version = "1.4") @kotlin.OverloadResolutionByLambdaReturnType @kotlin.jvm.JvmName(name = "sumOfULong") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Iterable<T>.sumOf(/*0*/ selector: (T) -> kotlin.ULong): kotlin.ULong
public fun </*0*/ T> kotlin.Array<out T>.take(/*0*/ n: kotlin.Int): kotlin.collections.List<T>
public fun kotlin.BooleanArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Boolean>
public fun kotlin.ByteArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Char>
public fun kotlin.DoubleArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Double>
public fun kotlin.FloatArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Float>
public fun kotlin.IntArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Int>
public fun kotlin.LongArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Long>
public fun kotlin.ShortArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.take(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UShort>
public fun </*0*/ T> kotlin.collections.Iterable<T>.take(/*0*/ n: kotlin.Int): kotlin.collections.List<T>
public fun </*0*/ T> kotlin.Array<out T>.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<T>
public fun kotlin.BooleanArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Boolean>
public fun kotlin.ByteArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Char>
public fun kotlin.DoubleArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Double>
public fun kotlin.FloatArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Float>
public fun kotlin.IntArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Int>
public fun kotlin.LongArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Long>
public fun kotlin.ShortArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<kotlin.UShort>
public fun </*0*/ T> kotlin.collections.List<T>.takeLast(/*0*/ n: kotlin.Int): kotlin.collections.List<T>
public inline fun </*0*/ T> kotlin.Array<out T>.takeLastWhile(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun kotlin.BooleanArray.takeLastWhile(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
public inline fun kotlin.ByteArray.takeLastWhile(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.collections.List<kotlin.Byte>
public inline fun kotlin.CharArray.takeLastWhile(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.collections.List<kotlin.Char>
public inline fun kotlin.DoubleArray.takeLastWhile(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.collections.List<kotlin.Double>
public inline fun kotlin.FloatArray.takeLastWhile(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.collections.List<kotlin.Float>
public inline fun kotlin.IntArray.takeLastWhile(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.collections.List<kotlin.Int>
public inline fun kotlin.LongArray.takeLastWhile(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.collections.List<kotlin.Long>
public inline fun kotlin.ShortArray.takeLastWhile(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.takeLastWhile(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.takeLastWhile(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.takeLastWhile(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.takeLastWhile(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.collections.List<kotlin.UShort>
public inline fun </*0*/ T> kotlin.collections.List<T>.takeLastWhile(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun </*0*/ T> kotlin.Array<out T>.takeWhile(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
public inline fun kotlin.BooleanArray.takeWhile(/*0*/ predicate: (kotlin.Boolean) -> kotlin.Boolean): kotlin.collections.List<kotlin.Boolean>
public inline fun kotlin.ByteArray.takeWhile(/*0*/ predicate: (kotlin.Byte) -> kotlin.Boolean): kotlin.collections.List<kotlin.Byte>
public inline fun kotlin.CharArray.takeWhile(/*0*/ predicate: (kotlin.Char) -> kotlin.Boolean): kotlin.collections.List<kotlin.Char>
public inline fun kotlin.DoubleArray.takeWhile(/*0*/ predicate: (kotlin.Double) -> kotlin.Boolean): kotlin.collections.List<kotlin.Double>
public inline fun kotlin.FloatArray.takeWhile(/*0*/ predicate: (kotlin.Float) -> kotlin.Boolean): kotlin.collections.List<kotlin.Float>
public inline fun kotlin.IntArray.takeWhile(/*0*/ predicate: (kotlin.Int) -> kotlin.Boolean): kotlin.collections.List<kotlin.Int>
public inline fun kotlin.LongArray.takeWhile(/*0*/ predicate: (kotlin.Long) -> kotlin.Boolean): kotlin.collections.List<kotlin.Long>
public inline fun kotlin.ShortArray.takeWhile(/*0*/ predicate: (kotlin.Short) -> kotlin.Boolean): kotlin.collections.List<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.takeWhile(/*0*/ predicate: (kotlin.UByte) -> kotlin.Boolean): kotlin.collections.List<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.takeWhile(/*0*/ predicate: (kotlin.UInt) -> kotlin.Boolean): kotlin.collections.List<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.takeWhile(/*0*/ predicate: (kotlin.ULong) -> kotlin.Boolean): kotlin.collections.List<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.takeWhile(/*0*/ predicate: (kotlin.UShort) -> kotlin.Boolean): kotlin.collections.List<kotlin.UShort>
public inline fun </*0*/ T> kotlin.collections.Iterable<T>.takeWhile(/*0*/ predicate: (T) -> kotlin.Boolean): kotlin.collections.List<T>
public fun kotlin.Array<out kotlin.Boolean>.toBooleanArray(): kotlin.BooleanArray
public fun kotlin.collections.Collection<kotlin.Boolean>.toBooleanArray(): kotlin.BooleanArray
public fun kotlin.Array<out kotlin.Byte>.toByteArray(): kotlin.ByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UByteArray.toByteArray(): kotlin.ByteArray
public fun kotlin.collections.Collection<kotlin.Byte>.toByteArray(): kotlin.ByteArray
public fun kotlin.Array<out kotlin.Char>.toCharArray(): kotlin.CharArray
public fun kotlin.collections.Collection<kotlin.Char>.toCharArray(): kotlin.CharArray
public fun </*0*/ T, /*1*/ C : kotlin.collections.MutableCollection<in T>> kotlin.Array<out T>.toCollection(/*0*/ destination: C): C
public fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Boolean>> kotlin.BooleanArray.toCollection(/*0*/ destination: C): C
public fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Byte>> kotlin.ByteArray.toCollection(/*0*/ destination: C): C
public fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Char>> kotlin.CharArray.toCollection(/*0*/ destination: C): C
public fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Double>> kotlin.DoubleArray.toCollection(/*0*/ destination: C): C
public fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Float>> kotlin.FloatArray.toCollection(/*0*/ destination: C): C
public fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Int>> kotlin.IntArray.toCollection(/*0*/ destination: C): C
public fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Long>> kotlin.LongArray.toCollection(/*0*/ destination: C): C
public fun </*0*/ C : kotlin.collections.MutableCollection<in kotlin.Short>> kotlin.ShortArray.toCollection(/*0*/ destination: C): C
public fun </*0*/ T, /*1*/ C : kotlin.collections.MutableCollection<in T>> kotlin.collections.Iterable<T>.toCollection(/*0*/ destination: C): C
public fun kotlin.Array<out kotlin.Double>.toDoubleArray(): kotlin.DoubleArray
public fun kotlin.collections.Collection<kotlin.Double>.toDoubleArray(): kotlin.DoubleArray
public fun kotlin.Array<out kotlin.Float>.toFloatArray(): kotlin.FloatArray
public fun kotlin.collections.Collection<kotlin.Float>.toFloatArray(): kotlin.FloatArray
public fun </*0*/ T> kotlin.Array<out T>.toHashSet(): kotlin.collections.HashSet<T>
public fun kotlin.BooleanArray.toHashSet(): kotlin.collections.HashSet<kotlin.Boolean>
public fun kotlin.ByteArray.toHashSet(): kotlin.collections.HashSet<kotlin.Byte>
public fun kotlin.CharArray.toHashSet(): kotlin.collections.HashSet<kotlin.Char>
public fun kotlin.DoubleArray.toHashSet(): kotlin.collections.HashSet<kotlin.Double>
public fun kotlin.FloatArray.toHashSet(): kotlin.collections.HashSet<kotlin.Float>
public fun kotlin.IntArray.toHashSet(): kotlin.collections.HashSet<kotlin.Int>
public fun kotlin.LongArray.toHashSet(): kotlin.collections.HashSet<kotlin.Long>
public fun kotlin.ShortArray.toHashSet(): kotlin.collections.HashSet<kotlin.Short>
public fun </*0*/ T> kotlin.collections.Iterable<T>.toHashSet(): kotlin.collections.HashSet<T>
public fun kotlin.Array<out kotlin.Int>.toIntArray(): kotlin.IntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UIntArray.toIntArray(): kotlin.IntArray
public fun kotlin.collections.Collection<kotlin.Int>.toIntArray(): kotlin.IntArray
public fun </*0*/ T> kotlin.Array<out T>.toList(): kotlin.collections.List<T>
public fun kotlin.BooleanArray.toList(): kotlin.collections.List<kotlin.Boolean>
public fun kotlin.ByteArray.toList(): kotlin.collections.List<kotlin.Byte>
public fun kotlin.CharArray.toList(): kotlin.collections.List<kotlin.Char>
public fun kotlin.DoubleArray.toList(): kotlin.collections.List<kotlin.Double>
public fun kotlin.FloatArray.toList(): kotlin.collections.List<kotlin.Float>
public fun kotlin.IntArray.toList(): kotlin.collections.List<kotlin.Int>
public fun kotlin.LongArray.toList(): kotlin.collections.List<kotlin.Long>
public fun kotlin.ShortArray.toList(): kotlin.collections.List<kotlin.Short>
public fun </*0*/ T> kotlin.collections.Iterable<T>.toList(): kotlin.collections.List<T>
public fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.toList(): kotlin.collections.List<kotlin.Pair<K, V>>
public fun kotlin.Array<out kotlin.Long>.toLongArray(): kotlin.LongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ULongArray.toLongArray(): kotlin.LongArray
public fun kotlin.collections.Collection<kotlin.Long>.toLongArray(): kotlin.LongArray
public fun </*0*/ K, /*1*/ V> kotlin.Array<out kotlin.Pair<K, V>>.toMap(): kotlin.collections.Map<K, V>
public fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.Array<out kotlin.Pair<K, V>>.toMap(/*0*/ destination: M): M
public fun </*0*/ K, /*1*/ V> kotlin.collections.Iterable<kotlin.Pair<K, V>>.toMap(): kotlin.collections.Map<K, V>
public fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.collections.Iterable<kotlin.Pair<K, V>>.toMap(/*0*/ destination: M): M
@kotlin.SinceKotlin(version = "1.1") public fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.toMap(): kotlin.collections.Map<K, V>
@kotlin.SinceKotlin(version = "1.1") public fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.collections.Map<out K, V>.toMap(/*0*/ destination: M): M
public fun </*0*/ K, /*1*/ V> kotlin.sequences.Sequence<kotlin.Pair<K, V>>.toMap(): kotlin.collections.Map<K, V>
public fun </*0*/ K, /*1*/ V, /*2*/ M : kotlin.collections.MutableMap<in K, in V>> kotlin.sequences.Sequence<kotlin.Pair<K, V>>.toMap(/*0*/ destination: M): M
public fun </*0*/ T> kotlin.Array<out T>.toMutableList(): kotlin.collections.MutableList<T>
public fun kotlin.BooleanArray.toMutableList(): kotlin.collections.MutableList<kotlin.Boolean>
public fun kotlin.ByteArray.toMutableList(): kotlin.collections.MutableList<kotlin.Byte>
public fun kotlin.CharArray.toMutableList(): kotlin.collections.MutableList<kotlin.Char>
public fun kotlin.DoubleArray.toMutableList(): kotlin.collections.MutableList<kotlin.Double>
public fun kotlin.FloatArray.toMutableList(): kotlin.collections.MutableList<kotlin.Float>
public fun kotlin.IntArray.toMutableList(): kotlin.collections.MutableList<kotlin.Int>
public fun kotlin.LongArray.toMutableList(): kotlin.collections.MutableList<kotlin.Long>
public fun kotlin.ShortArray.toMutableList(): kotlin.collections.MutableList<kotlin.Short>
public fun </*0*/ T> kotlin.collections.Collection<T>.toMutableList(): kotlin.collections.MutableList<T>
public fun </*0*/ T> kotlin.collections.Iterable<T>.toMutableList(): kotlin.collections.MutableList<T>
@kotlin.SinceKotlin(version = "1.1") public fun </*0*/ K, /*1*/ V> kotlin.collections.Map<out K, V>.toMutableMap(): kotlin.collections.MutableMap<K, V>
public fun </*0*/ T> kotlin.Array<out T>.toMutableSet(): kotlin.collections.MutableSet<T>
public fun kotlin.BooleanArray.toMutableSet(): kotlin.collections.MutableSet<kotlin.Boolean>
public fun kotlin.ByteArray.toMutableSet(): kotlin.collections.MutableSet<kotlin.Byte>
public fun kotlin.CharArray.toMutableSet(): kotlin.collections.MutableSet<kotlin.Char>
public fun kotlin.DoubleArray.toMutableSet(): kotlin.collections.MutableSet<kotlin.Double>
public fun kotlin.FloatArray.toMutableSet(): kotlin.collections.MutableSet<kotlin.Float>
public fun kotlin.IntArray.toMutableSet(): kotlin.collections.MutableSet<kotlin.Int>
public fun kotlin.LongArray.toMutableSet(): kotlin.collections.MutableSet<kotlin.Long>
public fun kotlin.ShortArray.toMutableSet(): kotlin.collections.MutableSet<kotlin.Short>
public fun </*0*/ T> kotlin.collections.Iterable<T>.toMutableSet(): kotlin.collections.MutableSet<T>
@kotlin.internal.InlineOnly public inline fun </*0*/ K, /*1*/ V> kotlin.collections.Map.Entry<K, V>.toPair(): kotlin.Pair<K, V>
public fun </*0*/ T> kotlin.Array<out T>.toSet(): kotlin.collections.Set<T>
public fun kotlin.BooleanArray.toSet(): kotlin.collections.Set<kotlin.Boolean>
public fun kotlin.ByteArray.toSet(): kotlin.collections.Set<kotlin.Byte>
public fun kotlin.CharArray.toSet(): kotlin.collections.Set<kotlin.Char>
public fun kotlin.DoubleArray.toSet(): kotlin.collections.Set<kotlin.Double>
public fun kotlin.FloatArray.toSet(): kotlin.collections.Set<kotlin.Float>
public fun kotlin.IntArray.toSet(): kotlin.collections.Set<kotlin.Int>
public fun kotlin.LongArray.toSet(): kotlin.collections.Set<kotlin.Long>
public fun kotlin.ShortArray.toSet(): kotlin.collections.Set<kotlin.Short>
public fun </*0*/ T> kotlin.collections.Iterable<T>.toSet(): kotlin.collections.Set<T>
public fun kotlin.Array<out kotlin.Short>.toShortArray(): kotlin.ShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.UShortArray.toShortArray(): kotlin.ShortArray
public fun kotlin.collections.Collection<kotlin.Short>.toShortArray(): kotlin.ShortArray
public fun kotlin.BooleanArray.toTypedArray(): kotlin.Array<kotlin.Boolean>
public fun kotlin.ByteArray.toTypedArray(): kotlin.Array<kotlin.Byte>
public fun kotlin.CharArray.toTypedArray(): kotlin.Array<kotlin.Char>
public fun kotlin.DoubleArray.toTypedArray(): kotlin.Array<kotlin.Double>
public fun kotlin.FloatArray.toTypedArray(): kotlin.Array<kotlin.Float>
public fun kotlin.IntArray.toTypedArray(): kotlin.Array<kotlin.Int>
public fun kotlin.LongArray.toTypedArray(): kotlin.Array<kotlin.Long>
public fun kotlin.ShortArray.toTypedArray(): kotlin.Array<kotlin.Short>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.toTypedArray(): kotlin.Array<kotlin.UByte>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.toTypedArray(): kotlin.Array<kotlin.UInt>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.toTypedArray(): kotlin.Array<kotlin.ULong>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.toTypedArray(): kotlin.Array<kotlin.UShort>
@kotlin.internal.InlineOnly public inline fun </*0*/ T> kotlin.collections.Collection<T>.toTypedArray(): kotlin.Array<T>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.Array<out kotlin.UByte>.toUByteArray(): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ByteArray.toUByteArray(): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.collections.Collection<kotlin.UByte>.toUByteArray(): kotlin.UByteArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.Array<out kotlin.UInt>.toUIntArray(): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.IntArray.toUIntArray(): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.collections.Collection<kotlin.UInt>.toUIntArray(): kotlin.UIntArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.Array<out kotlin.ULong>.toULongArray(): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.LongArray.toULongArray(): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.collections.Collection<kotlin.ULong>.toULongArray(): kotlin.ULongArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.Array<out kotlin.UShort>.toUShortArray(): kotlin.UShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun kotlin.ShortArray.toUShortArray(): kotlin.UShortArray
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.collections.Collection<kotlin.UShort>.toUShortArray(): kotlin.UShortArray
public infix fun </*0*/ T> kotlin.Array<out T>.union(/*0*/ other: kotlin.collections.Iterable<T>): kotlin.collections.Set<T>
public infix fun kotlin.BooleanArray.union(/*0*/ other: kotlin.collections.Iterable<kotlin.Boolean>): kotlin.collections.Set<kotlin.Boolean>
public infix fun kotlin.ByteArray.union(/*0*/ other: kotlin.collections.Iterable<kotlin.Byte>): kotlin.collections.Set<kotlin.Byte>
public infix fun kotlin.CharArray.union(/*0*/ other: kotlin.collections.Iterable<kotlin.Char>): kotlin.collections.Set<kotlin.Char>
public infix fun kotlin.DoubleArray.union(/*0*/ other: kotlin.collections.Iterable<kotlin.Double>): kotlin.collections.Set<kotlin.Double>
public infix fun kotlin.FloatArray.union(/*0*/ other: kotlin.collections.Iterable<kotlin.Float>): kotlin.collections.Set<kotlin.Float>
public infix fun kotlin.IntArray.union(/*0*/ other: kotlin.collections.Iterable<kotlin.Int>): kotlin.collections.Set<kotlin.Int>
public infix fun kotlin.LongArray.union(/*0*/ other: kotlin.collections.Iterable<kotlin.Long>): kotlin.collections.Set<kotlin.Long>
public infix fun kotlin.ShortArray.union(/*0*/ other: kotlin.collections.Iterable<kotlin.Short>): kotlin.collections.Set<kotlin.Short>
public infix fun </*0*/ T> kotlin.collections.Iterable<T>.union(/*0*/ other: kotlin.collections.Iterable<T>): kotlin.collections.Set<T>
public fun </*0*/ T, /*1*/ R> kotlin.Array<out kotlin.Pair<T, R>>.unzip(): kotlin.Pair<kotlin.collections.List<T>, kotlin.collections.List<R>>
public fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<kotlin.Pair<T, R>>.unzip(): kotlin.Pair<kotlin.collections.List<T>, kotlin.collections.List<R>>
@kotlin.SinceKotlin(version = "1.2") public fun </*0*/ T> kotlin.collections.Iterable<T>.windowed(/*0*/ size: kotlin.Int, /*1*/ step: kotlin.Int = ..., /*2*/ partialWindows: kotlin.Boolean = ...): kotlin.collections.List<kotlin.collections.List<T>>
@kotlin.SinceKotlin(version = "1.2") public fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.windowed(/*0*/ size: kotlin.Int, /*1*/ step: kotlin.Int = ..., /*2*/ partialWindows: kotlin.Boolean = ..., /*3*/ transform: (kotlin.collections.List<T>) -> R): kotlin.collections.List<R>
public fun </*0*/ K, /*1*/ V> kotlin.collections.Map<K, V>.withDefault(/*0*/ defaultValue: (key: K) -> V): kotlin.collections.Map<K, V>
@kotlin.jvm.JvmName(name = "withDefaultMutable") public fun </*0*/ K, /*1*/ V> kotlin.collections.MutableMap<K, V>.withDefault(/*0*/ defaultValue: (key: K) -> V): kotlin.collections.MutableMap<K, V>
public fun </*0*/ T> kotlin.Array<out T>.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<T>>
public fun kotlin.BooleanArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.Boolean>>
public fun kotlin.ByteArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.Byte>>
public fun kotlin.CharArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.Char>>
public fun kotlin.DoubleArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.Double>>
public fun kotlin.FloatArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.Float>>
public fun kotlin.IntArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.Int>>
public fun kotlin.LongArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.Long>>
public fun kotlin.ShortArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.Short>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UByteArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.UByte>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UIntArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.UInt>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.ULongArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.ULong>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public fun kotlin.UShortArray.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<kotlin.UShort>>
public fun </*0*/ T> kotlin.collections.Iterable<T>.withIndex(): kotlin.collections.Iterable<kotlin.collections.IndexedValue<T>>
public fun </*0*/ T> kotlin.collections.Iterator<T>.withIndex(): kotlin.collections.Iterator<kotlin.collections.IndexedValue<T>>
public infix fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<T, R>>
public inline fun </*0*/ T, /*1*/ R, /*2*/ V> kotlin.Array<out T>.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: T, b: R) -> V): kotlin.collections.List<V>
public infix fun </*0*/ T, /*1*/ R> kotlin.Array<out T>.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<T, R>>
public inline fun </*0*/ T, /*1*/ R, /*2*/ V> kotlin.Array<out T>.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: T, b: R) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.BooleanArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.Boolean, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.BooleanArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.Boolean, b: R) -> V): kotlin.collections.List<V>
public infix fun kotlin.BooleanArray.zip(/*0*/ other: kotlin.BooleanArray): kotlin.collections.List<kotlin.Pair<kotlin.Boolean, kotlin.Boolean>>
public inline fun </*0*/ V> kotlin.BooleanArray.zip(/*0*/ other: kotlin.BooleanArray, /*1*/ transform: (a: kotlin.Boolean, b: kotlin.Boolean) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.BooleanArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.Boolean, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.BooleanArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.Boolean, b: R) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.ByteArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.Byte, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.ByteArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.Byte, b: R) -> V): kotlin.collections.List<V>
public infix fun kotlin.ByteArray.zip(/*0*/ other: kotlin.ByteArray): kotlin.collections.List<kotlin.Pair<kotlin.Byte, kotlin.Byte>>
public inline fun </*0*/ V> kotlin.ByteArray.zip(/*0*/ other: kotlin.ByteArray, /*1*/ transform: (a: kotlin.Byte, b: kotlin.Byte) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.ByteArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.Byte, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.ByteArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.Byte, b: R) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.CharArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.Char, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.CharArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.Char, b: R) -> V): kotlin.collections.List<V>
public infix fun kotlin.CharArray.zip(/*0*/ other: kotlin.CharArray): kotlin.collections.List<kotlin.Pair<kotlin.Char, kotlin.Char>>
public inline fun </*0*/ V> kotlin.CharArray.zip(/*0*/ other: kotlin.CharArray, /*1*/ transform: (a: kotlin.Char, b: kotlin.Char) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.CharArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.Char, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.CharArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.Char, b: R) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.DoubleArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.Double, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.DoubleArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.Double, b: R) -> V): kotlin.collections.List<V>
public infix fun kotlin.DoubleArray.zip(/*0*/ other: kotlin.DoubleArray): kotlin.collections.List<kotlin.Pair<kotlin.Double, kotlin.Double>>
public inline fun </*0*/ V> kotlin.DoubleArray.zip(/*0*/ other: kotlin.DoubleArray, /*1*/ transform: (a: kotlin.Double, b: kotlin.Double) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.DoubleArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.Double, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.DoubleArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.Double, b: R) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.FloatArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.Float, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.FloatArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.Float, b: R) -> V): kotlin.collections.List<V>
public infix fun kotlin.FloatArray.zip(/*0*/ other: kotlin.FloatArray): kotlin.collections.List<kotlin.Pair<kotlin.Float, kotlin.Float>>
public inline fun </*0*/ V> kotlin.FloatArray.zip(/*0*/ other: kotlin.FloatArray, /*1*/ transform: (a: kotlin.Float, b: kotlin.Float) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.FloatArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.Float, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.FloatArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.Float, b: R) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.IntArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.Int, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.IntArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.Int, b: R) -> V): kotlin.collections.List<V>
public infix fun kotlin.IntArray.zip(/*0*/ other: kotlin.IntArray): kotlin.collections.List<kotlin.Pair<kotlin.Int, kotlin.Int>>
public inline fun </*0*/ V> kotlin.IntArray.zip(/*0*/ other: kotlin.IntArray, /*1*/ transform: (a: kotlin.Int, b: kotlin.Int) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.IntArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.Int, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.IntArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.Int, b: R) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.LongArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.Long, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.LongArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.Long, b: R) -> V): kotlin.collections.List<V>
public infix fun kotlin.LongArray.zip(/*0*/ other: kotlin.LongArray): kotlin.collections.List<kotlin.Pair<kotlin.Long, kotlin.Long>>
public inline fun </*0*/ V> kotlin.LongArray.zip(/*0*/ other: kotlin.LongArray, /*1*/ transform: (a: kotlin.Long, b: kotlin.Long) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.LongArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.Long, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.LongArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.Long, b: R) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.ShortArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.Short, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.ShortArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.Short, b: R) -> V): kotlin.collections.List<V>
public infix fun kotlin.ShortArray.zip(/*0*/ other: kotlin.ShortArray): kotlin.collections.List<kotlin.Pair<kotlin.Short, kotlin.Short>>
public inline fun </*0*/ V> kotlin.ShortArray.zip(/*0*/ other: kotlin.ShortArray, /*1*/ transform: (a: kotlin.Short, b: kotlin.Short) -> V): kotlin.collections.List<V>
public infix fun </*0*/ R> kotlin.ShortArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.Short, R>>
public inline fun </*0*/ R, /*1*/ V> kotlin.ShortArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.Short, b: R) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun </*0*/ R> kotlin.UByteArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.UByte, R>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ V> kotlin.UByteArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.UByte, b: R) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.UByteArray.zip(/*0*/ other: kotlin.UByteArray): kotlin.collections.List<kotlin.Pair<kotlin.UByte, kotlin.UByte>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UByteArray.zip(/*0*/ other: kotlin.UByteArray, /*1*/ transform: (a: kotlin.UByte, b: kotlin.UByte) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun </*0*/ R> kotlin.UByteArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.UByte, R>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ V> kotlin.UByteArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.UByte, b: R) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun </*0*/ R> kotlin.UIntArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.UInt, R>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ V> kotlin.UIntArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.UInt, b: R) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.UIntArray.zip(/*0*/ other: kotlin.UIntArray): kotlin.collections.List<kotlin.Pair<kotlin.UInt, kotlin.UInt>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UIntArray.zip(/*0*/ other: kotlin.UIntArray, /*1*/ transform: (a: kotlin.UInt, b: kotlin.UInt) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun </*0*/ R> kotlin.UIntArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.UInt, R>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ V> kotlin.UIntArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.UInt, b: R) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun </*0*/ R> kotlin.ULongArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.ULong, R>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ V> kotlin.ULongArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.ULong, b: R) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.ULongArray.zip(/*0*/ other: kotlin.ULongArray): kotlin.collections.List<kotlin.Pair<kotlin.ULong, kotlin.ULong>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.ULongArray.zip(/*0*/ other: kotlin.ULongArray, /*1*/ transform: (a: kotlin.ULong, b: kotlin.ULong) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun </*0*/ R> kotlin.ULongArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.ULong, R>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ V> kotlin.ULongArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.ULong, b: R) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun </*0*/ R> kotlin.UShortArray.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<kotlin.UShort, R>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ V> kotlin.UShortArray.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: kotlin.UShort, b: R) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun kotlin.UShortArray.zip(/*0*/ other: kotlin.UShortArray): kotlin.collections.List<kotlin.Pair<kotlin.UShort, kotlin.UShort>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ V> kotlin.UShortArray.zip(/*0*/ other: kotlin.UShortArray, /*1*/ transform: (a: kotlin.UShort, b: kotlin.UShort) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public infix fun </*0*/ R> kotlin.UShortArray.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<kotlin.UShort, R>>
@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes @kotlin.internal.InlineOnly public inline fun </*0*/ R, /*1*/ V> kotlin.UShortArray.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: kotlin.UShort, b: R) -> V): kotlin.collections.List<V>
public infix fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.zip(/*0*/ other: kotlin.Array<out R>): kotlin.collections.List<kotlin.Pair<T, R>>
public inline fun </*0*/ T, /*1*/ R, /*2*/ V> kotlin.collections.Iterable<T>.zip(/*0*/ other: kotlin.Array<out R>, /*1*/ transform: (a: T, b: R) -> V): kotlin.collections.List<V>
public infix fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.zip(/*0*/ other: kotlin.collections.Iterable<R>): kotlin.collections.List<kotlin.Pair<T, R>>
public inline fun </*0*/ T, /*1*/ R, /*2*/ V> kotlin.collections.Iterable<T>.zip(/*0*/ other: kotlin.collections.Iterable<R>, /*1*/ transform: (a: T, b: R) -> V): kotlin.collections.List<V>
@kotlin.SinceKotlin(version = "1.2") public fun </*0*/ T> kotlin.collections.Iterable<T>.zipWithNext(): kotlin.collections.List<kotlin.Pair<T, T>>
@kotlin.SinceKotlin(version = "1.2") public inline fun </*0*/ T, /*1*/ R> kotlin.collections.Iterable<T>.zipWithNext(/*0*/ transform: (a: T, b: T) -> R): kotlin.collections.List<R>

@kotlin.SinceKotlin(version = "1.1") public abstract class AbstractCollection</*0*/ out E> : kotlin.collections.Collection<E> {
    /*primary*/ protected constructor AbstractCollection</*0*/ out E>()
    public abstract override /*1*/ val size: kotlin.Int
        public abstract override /*1*/ fun <get-size>(): kotlin.Int
    public open override /*1*/ fun contains(/*0*/ element: E): kotlin.Boolean
    public open override /*1*/ fun containsAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public open override /*1*/ fun isEmpty(): kotlin.Boolean
    public abstract override /*1*/ fun iterator(): kotlin.collections.Iterator<E>
    @kotlin.js.JsName(name = "toArray") protected open fun toArray(): kotlin.Array<kotlin.Any?>
    protected open fun </*0*/ T> toArray(/*0*/ array: kotlin.Array<T>): kotlin.Array<T>
    public open override /*1*/ fun toString(): kotlin.String
}

public abstract class AbstractIterator</*0*/ T> : kotlin.collections.Iterator<T> {
    /*primary*/ public constructor AbstractIterator</*0*/ T>()
    protected abstract fun computeNext(): kotlin.Unit
    protected final fun done(): kotlin.Unit
    public open override /*1*/ fun hasNext(): kotlin.Boolean
    public open override /*1*/ fun next(): T
    protected final fun setNext(/*0*/ value: T): kotlin.Unit
}

@kotlin.SinceKotlin(version = "1.1") public abstract class AbstractList</*0*/ out E> : kotlin.collections.AbstractCollection<E>, kotlin.collections.List<E> {
    /*primary*/ protected constructor AbstractList</*0*/ out E>()
    public abstract override /*2*/ val size: kotlin.Int
        public abstract override /*2*/ fun <get-size>(): kotlin.Int
    public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public abstract override /*1*/ fun get(/*0*/ index: kotlin.Int): E
    public open override /*2*/ fun hashCode(): kotlin.Int
    public open override /*1*/ fun indexOf(/*0*/ element: E): kotlin.Int
    public open override /*2*/ fun iterator(): kotlin.collections.Iterator<E>
    public open override /*1*/ fun lastIndexOf(/*0*/ element: E): kotlin.Int
    public open override /*1*/ fun listIterator(): kotlin.collections.ListIterator<E>
    public open override /*1*/ fun listIterator(/*0*/ index: kotlin.Int): kotlin.collections.ListIterator<E>
    public open override /*1*/ fun subList(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.collections.List<E>
}

@kotlin.SinceKotlin(version = "1.1") public abstract class AbstractMap</*0*/ K, /*1*/ out V> : kotlin.collections.Map<K, V> {
    /*primary*/ protected constructor AbstractMap</*0*/ K, /*1*/ out V>()
    public open override /*1*/ val keys: kotlin.collections.Set<K>
        public open override /*1*/ fun <get-keys>(): kotlin.collections.Set<K>
    public open override /*1*/ val size: kotlin.Int
        public open override /*1*/ fun <get-size>(): kotlin.Int
    public open override /*1*/ val values: kotlin.collections.Collection<V>
        public open override /*1*/ fun <get-values>(): kotlin.collections.Collection<V>
    public open override /*1*/ fun containsKey(/*0*/ key: K): kotlin.Boolean
    public open override /*1*/ fun containsValue(/*0*/ value: V): kotlin.Boolean
    public open override /*1*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*1*/ fun get(/*0*/ key: K): V?
    public open override /*1*/ fun hashCode(): kotlin.Int
    public open override /*1*/ fun isEmpty(): kotlin.Boolean
    public open override /*1*/ fun toString(): kotlin.String
}

public abstract class AbstractMutableCollection</*0*/ E> : kotlin.collections.AbstractCollection<E>, kotlin.collections.MutableCollection<E> {
    /*primary*/ protected constructor AbstractMutableCollection</*0*/ E>()
    public abstract override /*1*/ fun add(/*0*/ element: E): kotlin.Boolean
    public open override /*1*/ fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public open override /*1*/ fun clear(): kotlin.Unit
    public open override /*1*/ fun remove(/*0*/ element: E): kotlin.Boolean
    public open override /*1*/ fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public open override /*1*/ fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    @kotlin.js.JsName(name = "toJSON") public open fun toJSON(): kotlin.Any
}

public abstract class AbstractMutableList</*0*/ E> : kotlin.collections.AbstractMutableCollection<E>, kotlin.collections.MutableList<E> {
    /*primary*/ protected constructor AbstractMutableList</*0*/ E>()
    protected final var modCount: kotlin.Int
        protected final fun <get-modCount>(): kotlin.Int
        protected final fun <set-modCount>(/*0*/ <set-?>: kotlin.Int): kotlin.Unit
    public open override /*2*/ fun add(/*0*/ element: E): kotlin.Boolean
    public abstract override /*1*/ fun add(/*0*/ index: kotlin.Int, /*1*/ element: E): kotlin.Unit
    public open override /*1*/ fun addAll(/*0*/ index: kotlin.Int, /*1*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public open override /*2*/ fun clear(): kotlin.Unit
    public open override /*2*/ fun contains(/*0*/ element: E): kotlin.Boolean
    public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*2*/ fun hashCode(): kotlin.Int
    public open override /*1*/ fun indexOf(/*0*/ element: E): kotlin.Int
    public open override /*2*/ fun iterator(): kotlin.collections.MutableIterator<E>
    public open override /*1*/ fun lastIndexOf(/*0*/ element: E): kotlin.Int
    public open override /*1*/ fun listIterator(): kotlin.collections.MutableListIterator<E>
    public open override /*1*/ fun listIterator(/*0*/ index: kotlin.Int): kotlin.collections.MutableListIterator<E>
    public open override /*2*/ fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun removeAt(/*0*/ index: kotlin.Int): E
    protected open fun removeRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
    public open override /*2*/ fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun set(/*0*/ index: kotlin.Int, /*1*/ element: E): E
    public open override /*1*/ fun subList(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.collections.MutableList<E>
}

public abstract class AbstractMutableMap</*0*/ K, /*1*/ V> : kotlin.collections.AbstractMap<K, V>, kotlin.collections.MutableMap<K, V> {
    /*primary*/ protected constructor AbstractMutableMap</*0*/ K, /*1*/ V>()
    public open override /*2*/ val keys: kotlin.collections.MutableSet<K>
        public open override /*2*/ fun <get-keys>(): kotlin.collections.MutableSet<K>
    public open override /*2*/ val values: kotlin.collections.MutableCollection<V>
        public open override /*2*/ fun <get-values>(): kotlin.collections.MutableCollection<V>
    public open override /*1*/ fun clear(): kotlin.Unit
    public abstract override /*1*/ fun put(/*0*/ key: K, /*1*/ value: V): V?
    public open override /*1*/ fun putAll(/*0*/ from: kotlin.collections.Map<out K, V>): kotlin.Unit
    public open override /*1*/ fun remove(/*0*/ key: K): V?
}

public abstract class AbstractMutableSet</*0*/ E> : kotlin.collections.AbstractMutableCollection<E>, kotlin.collections.MutableSet<E> {
    /*primary*/ protected constructor AbstractMutableSet</*0*/ E>()
    public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*2*/ fun hashCode(): kotlin.Int
}

@kotlin.SinceKotlin(version = "1.1") public abstract class AbstractSet</*0*/ out E> : kotlin.collections.AbstractCollection<E>, kotlin.collections.Set<E> {
    /*primary*/ protected constructor AbstractSet</*0*/ out E>()
    public open override /*2*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*2*/ fun hashCode(): kotlin.Int
}

@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalStdlibApi public final class ArrayDeque</*0*/ E> : kotlin.collections.AbstractMutableList<E> {
    public constructor ArrayDeque</*0*/ E>()
    public constructor ArrayDeque</*0*/ E>(/*0*/ initialCapacity: kotlin.Int)
    public constructor ArrayDeque</*0*/ E>(/*0*/ elements: kotlin.collections.Collection<E>)
    public open override /*1*/ var size: kotlin.Int
        public open override /*1*/ fun <get-size>(): kotlin.Int
        private open fun <set-size>(/*0*/ <set-?>: kotlin.Int): kotlin.Unit
    public open override /*1*/ fun add(/*0*/ element: E): kotlin.Boolean
    public open override /*1*/ fun add(/*0*/ index: kotlin.Int, /*1*/ element: E): kotlin.Unit
    public open override /*1*/ fun addAll(/*0*/ index: kotlin.Int, /*1*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public open override /*1*/ fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public final fun addFirst(/*0*/ element: E): kotlin.Unit
    public final fun addLast(/*0*/ element: E): kotlin.Unit
    public open override /*1*/ fun clear(): kotlin.Unit
    public open override /*1*/ fun contains(/*0*/ element: E): kotlin.Boolean
    public final fun first(): E
    public final fun firstOrNull(): E?
    public open override /*1*/ fun get(/*0*/ index: kotlin.Int): E
    public open override /*1*/ fun indexOf(/*0*/ element: E): kotlin.Int
    public open override /*1*/ fun isEmpty(): kotlin.Boolean
    public final fun last(): E
    public open override /*1*/ fun lastIndexOf(/*0*/ element: E): kotlin.Int
    public final fun lastOrNull(): E?
    public open override /*1*/ fun remove(/*0*/ element: E): kotlin.Boolean
    public open override /*1*/ fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public open override /*1*/ fun removeAt(/*0*/ index: kotlin.Int): E
    public final fun removeFirst(): E
    public final fun removeFirstOrNull(): E?
    public final fun removeLast(): E
    public final fun removeLastOrNull(): E?
    public open override /*1*/ fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public open override /*1*/ fun set(/*0*/ index: kotlin.Int, /*1*/ element: E): E
}

public open class ArrayList</*0*/ E> : kotlin.collections.AbstractMutableList<E>, kotlin.collections.MutableList<E>, kotlin.collections.RandomAccess {
    public constructor ArrayList</*0*/ E>()
    public constructor ArrayList</*0*/ E>(/*0*/ initialCapacity: kotlin.Int = ...)
    public constructor ArrayList</*0*/ E>(/*0*/ elements: kotlin.collections.Collection<E>)
    public open override /*2*/ val size: kotlin.Int
        public open override /*2*/ fun <get-size>(): kotlin.Int
    public open override /*2*/ fun add(/*0*/ element: E): kotlin.Boolean
    public open override /*2*/ fun add(/*0*/ index: kotlin.Int, /*1*/ element: E): kotlin.Unit
    public open override /*2*/ fun addAll(/*0*/ index: kotlin.Int, /*1*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public open override /*2*/ fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public open override /*2*/ fun clear(): kotlin.Unit
    public final fun ensureCapacity(/*0*/ minCapacity: kotlin.Int): kotlin.Unit
    public open override /*2*/ fun get(/*0*/ index: kotlin.Int): E
    public open override /*2*/ fun indexOf(/*0*/ element: E): kotlin.Int
    public open override /*2*/ fun lastIndexOf(/*0*/ element: E): kotlin.Int
    public open override /*2*/ fun remove(/*0*/ element: E): kotlin.Boolean
    public open override /*2*/ fun removeAt(/*0*/ index: kotlin.Int): E
    protected open override /*1*/ fun removeRange(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.Unit
    public open override /*2*/ fun set(/*0*/ index: kotlin.Int, /*1*/ element: E): E
    protected open override /*1*/ fun toArray(): kotlin.Array<kotlin.Any?>
    public open override /*3*/ fun toString(): kotlin.String
    public final fun trimToSize(): kotlin.Unit
}

public abstract class BooleanIterator : kotlin.collections.Iterator<kotlin.Boolean> {
    /*primary*/ public constructor BooleanIterator()
    public final override /*1*/ fun next(): kotlin.Boolean
    public abstract fun nextBoolean(): kotlin.Boolean
}

public abstract class BooleanIterator : kotlin.collections.Iterator<kotlin.Boolean> {
    /*primary*/ public constructor BooleanIterator()
    public final override /*1*/ fun next(): kotlin.Boolean
    public abstract fun nextBoolean(): kotlin.Boolean
}

public abstract class ByteIterator : kotlin.collections.Iterator<kotlin.Byte> {
    /*primary*/ public constructor ByteIterator()
    public final override /*1*/ fun next(): kotlin.Byte
    public abstract fun nextByte(): kotlin.Byte
}

public abstract class ByteIterator : kotlin.collections.Iterator<kotlin.Byte> {
    /*primary*/ public constructor ByteIterator()
    public final override /*1*/ fun next(): kotlin.Byte
    public abstract fun nextByte(): kotlin.Byte
}

public abstract class CharIterator : kotlin.collections.Iterator<kotlin.Char> {
    /*primary*/ public constructor CharIterator()
    public final override /*1*/ fun next(): kotlin.Char
    public abstract fun nextChar(): kotlin.Char
}

public abstract class CharIterator : kotlin.collections.Iterator<kotlin.Char> {
    /*primary*/ public constructor CharIterator()
    public final override /*1*/ fun next(): kotlin.Char
    public abstract fun nextChar(): kotlin.Char
}

public interface Collection</*0*/ out E> : kotlin.collections.Iterable<E> {
    public abstract val size: kotlin.Int
        public abstract fun <get-size>(): kotlin.Int
    public abstract operator fun contains(/*0*/ element: E): kotlin.Boolean
    public abstract fun containsAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract fun isEmpty(): kotlin.Boolean
    public abstract override /*1*/ fun iterator(): kotlin.collections.Iterator<E>
}

public interface Collection</*0*/ out E> : kotlin.collections.Iterable<E> {
    public abstract val size: kotlin.Int
        public abstract fun <get-size>(): kotlin.Int
    public abstract operator fun contains(/*0*/ element: E): kotlin.Boolean
    public abstract fun containsAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract fun isEmpty(): kotlin.Boolean
    public abstract override /*1*/ fun iterator(): kotlin.collections.Iterator<E>
}

public abstract class DoubleIterator : kotlin.collections.Iterator<kotlin.Double> {
    /*primary*/ public constructor DoubleIterator()
    public final override /*1*/ fun next(): kotlin.Double
    public abstract fun nextDouble(): kotlin.Double
}

public abstract class DoubleIterator : kotlin.collections.Iterator<kotlin.Double> {
    /*primary*/ public constructor DoubleIterator()
    public final override /*1*/ fun next(): kotlin.Double
    public abstract fun nextDouble(): kotlin.Double
}

public abstract class FloatIterator : kotlin.collections.Iterator<kotlin.Float> {
    /*primary*/ public constructor FloatIterator()
    public final override /*1*/ fun next(): kotlin.Float
    public abstract fun nextFloat(): kotlin.Float
}

public abstract class FloatIterator : kotlin.collections.Iterator<kotlin.Float> {
    /*primary*/ public constructor FloatIterator()
    public final override /*1*/ fun next(): kotlin.Float
    public abstract fun nextFloat(): kotlin.Float
}

@kotlin.SinceKotlin(version = "1.1") public interface Grouping</*0*/ T, /*1*/ out K> {
    public abstract fun keyOf(/*0*/ element: T): K
    public abstract fun sourceIterator(): kotlin.collections.Iterator<T>
}

public open class HashMap</*0*/ K, /*1*/ V> : kotlin.collections.AbstractMutableMap<K, V>, kotlin.collections.MutableMap<K, V> {
    public constructor HashMap</*0*/ K, /*1*/ V>()
    public constructor HashMap</*0*/ K, /*1*/ V>(/*0*/ initialCapacity: kotlin.Int)
    public constructor HashMap</*0*/ K, /*1*/ V>(/*0*/ initialCapacity: kotlin.Int, /*1*/ loadFactor: kotlin.Float = ...)
    public constructor HashMap</*0*/ K, /*1*/ V>(/*0*/ original: kotlin.collections.Map<out K, V>)
    public open override /*2*/ val entries: kotlin.collections.MutableSet<kotlin.collections.MutableMap.MutableEntry<K, V>>
        public open override /*2*/ fun <get-entries>(): kotlin.collections.MutableSet<kotlin.collections.MutableMap.MutableEntry<K, V>>
    public open override /*2*/ val size: kotlin.Int
        public open override /*2*/ fun <get-size>(): kotlin.Int
    public open override /*2*/ fun clear(): kotlin.Unit
    public open override /*2*/ fun containsKey(/*0*/ key: K): kotlin.Boolean
    public open override /*2*/ fun containsValue(/*0*/ value: V): kotlin.Boolean
    protected open fun createEntrySet(): kotlin.collections.MutableSet<kotlin.collections.MutableMap.MutableEntry<K, V>>
    public open override /*2*/ fun get(/*0*/ key: K): V?
    public open override /*2*/ fun put(/*0*/ key: K, /*1*/ value: V): V?
    public open override /*2*/ fun remove(/*0*/ key: K): V?
}

public open class HashSet</*0*/ E> : kotlin.collections.AbstractMutableSet<E>, kotlin.collections.MutableSet<E> {
    public constructor HashSet</*0*/ E>()
    public constructor HashSet</*0*/ E>(/*0*/ initialCapacity: kotlin.Int)
    public constructor HashSet</*0*/ E>(/*0*/ initialCapacity: kotlin.Int, /*1*/ loadFactor: kotlin.Float = ...)
    public constructor HashSet</*0*/ E>(/*0*/ elements: kotlin.collections.Collection<E>)
    public open override /*2*/ val size: kotlin.Int
        public open override /*2*/ fun <get-size>(): kotlin.Int
    public open override /*2*/ fun add(/*0*/ element: E): kotlin.Boolean
    public open override /*2*/ fun clear(): kotlin.Unit
    public open override /*2*/ fun contains(/*0*/ element: E): kotlin.Boolean
    public open override /*2*/ fun isEmpty(): kotlin.Boolean
    public open override /*2*/ fun iterator(): kotlin.collections.MutableIterator<E>
    public open override /*2*/ fun remove(/*0*/ element: E): kotlin.Boolean
}

public final data class IndexedValue</*0*/ out T> {
    /*primary*/ public constructor IndexedValue</*0*/ out T>(/*0*/ index: kotlin.Int, /*1*/ value: T)
    public final val index: kotlin.Int
        public final fun <get-index>(): kotlin.Int
    public final val value: T
        public final fun <get-value>(): T
    public final operator /*synthesized*/ fun component1(): kotlin.Int
    public final operator /*synthesized*/ fun component2(): T
    public final /*synthesized*/ fun copy(/*0*/ index: kotlin.Int = ..., /*1*/ value: T = ...): kotlin.collections.IndexedValue<T>
    public open override /*1*/ /*synthesized*/ fun equals(/*0*/ other: kotlin.Any?): kotlin.Boolean
    public open override /*1*/ /*synthesized*/ fun hashCode(): kotlin.Int
    public open override /*1*/ /*synthesized*/ fun toString(): kotlin.String
}

public abstract class IntIterator : kotlin.collections.Iterator<kotlin.Int> {
    /*primary*/ public constructor IntIterator()
    public final override /*1*/ fun next(): kotlin.Int
    public abstract fun nextInt(): kotlin.Int
}

public abstract class IntIterator : kotlin.collections.Iterator<kotlin.Int> {
    /*primary*/ public constructor IntIterator()
    public final override /*1*/ fun next(): kotlin.Int
    public abstract fun nextInt(): kotlin.Int
}

public interface Iterable</*0*/ out T> {
    public abstract operator fun iterator(): kotlin.collections.Iterator<T>
}

public interface Iterable</*0*/ out T> {
    public abstract operator fun iterator(): kotlin.collections.Iterator<T>
}

public interface Iterator</*0*/ out T> {
    public abstract operator fun hasNext(): kotlin.Boolean
    public abstract operator fun next(): T
}

public interface Iterator</*0*/ out T> {
    public abstract operator fun hasNext(): kotlin.Boolean
    public abstract operator fun next(): T
}

public open class LinkedHashMap</*0*/ K, /*1*/ V> : kotlin.collections.HashMap<K, V>, kotlin.collections.MutableMap<K, V> {
    public constructor LinkedHashMap</*0*/ K, /*1*/ V>()
    public constructor LinkedHashMap</*0*/ K, /*1*/ V>(/*0*/ initialCapacity: kotlin.Int)
    public constructor LinkedHashMap</*0*/ K, /*1*/ V>(/*0*/ initialCapacity: kotlin.Int, /*1*/ loadFactor: kotlin.Float = ...)
    public constructor LinkedHashMap</*0*/ K, /*1*/ V>(/*0*/ original: kotlin.collections.Map<out K, V>)
    public open override /*2*/ val size: kotlin.Int
        public open override /*2*/ fun <get-size>(): kotlin.Int
    public open override /*2*/ fun clear(): kotlin.Unit
    public open override /*2*/ fun containsKey(/*0*/ key: K): kotlin.Boolean
    public open override /*2*/ fun containsValue(/*0*/ value: V): kotlin.Boolean
    protected open override /*1*/ fun createEntrySet(): kotlin.collections.MutableSet<kotlin.collections.MutableMap.MutableEntry<K, V>>
    public open override /*2*/ fun get(/*0*/ key: K): V?
    public open override /*2*/ fun put(/*0*/ key: K, /*1*/ value: V): V?
    public open override /*2*/ fun remove(/*0*/ key: K): V?
}

public open class LinkedHashSet</*0*/ E> : kotlin.collections.HashSet<E>, kotlin.collections.MutableSet<E> {
    public constructor LinkedHashSet</*0*/ E>()
    public constructor LinkedHashSet</*0*/ E>(/*0*/ initialCapacity: kotlin.Int)
    public constructor LinkedHashSet</*0*/ E>(/*0*/ initialCapacity: kotlin.Int, /*1*/ loadFactor: kotlin.Float = ...)
    public constructor LinkedHashSet</*0*/ E>(/*0*/ elements: kotlin.collections.Collection<E>)
}

public interface List</*0*/ out E> : kotlin.collections.Collection<E> {
    public abstract override /*1*/ val size: kotlin.Int
        public abstract override /*1*/ fun <get-size>(): kotlin.Int
    public abstract override /*1*/ fun contains(/*0*/ element: E): kotlin.Boolean
    public abstract override /*1*/ fun containsAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract operator fun get(/*0*/ index: kotlin.Int): E
    public abstract fun indexOf(/*0*/ element: E): kotlin.Int
    public abstract override /*1*/ fun isEmpty(): kotlin.Boolean
    public abstract override /*1*/ fun iterator(): kotlin.collections.Iterator<E>
    public abstract fun lastIndexOf(/*0*/ element: E): kotlin.Int
    public abstract fun listIterator(): kotlin.collections.ListIterator<E>
    public abstract fun listIterator(/*0*/ index: kotlin.Int): kotlin.collections.ListIterator<E>
    public abstract fun subList(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.collections.List<E>
}

public interface List</*0*/ out E> : kotlin.collections.Collection<E> {
    public abstract override /*1*/ val size: kotlin.Int
        public abstract override /*1*/ fun <get-size>(): kotlin.Int
    public abstract override /*1*/ fun contains(/*0*/ element: E): kotlin.Boolean
    public abstract override /*1*/ fun containsAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract operator fun get(/*0*/ index: kotlin.Int): E
    public abstract fun indexOf(/*0*/ element: E): kotlin.Int
    public abstract override /*1*/ fun isEmpty(): kotlin.Boolean
    public abstract override /*1*/ fun iterator(): kotlin.collections.Iterator<E>
    public abstract fun lastIndexOf(/*0*/ element: E): kotlin.Int
    public abstract fun listIterator(): kotlin.collections.ListIterator<E>
    public abstract fun listIterator(/*0*/ index: kotlin.Int): kotlin.collections.ListIterator<E>
    public abstract fun subList(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.collections.List<E>
}

public interface ListIterator</*0*/ out T> : kotlin.collections.Iterator<T> {
    public abstract override /*1*/ fun hasNext(): kotlin.Boolean
    public abstract fun hasPrevious(): kotlin.Boolean
    public abstract override /*1*/ fun next(): T
    public abstract fun nextIndex(): kotlin.Int
    public abstract fun previous(): T
    public abstract fun previousIndex(): kotlin.Int
}

public interface ListIterator</*0*/ out T> : kotlin.collections.Iterator<T> {
    public abstract override /*1*/ fun hasNext(): kotlin.Boolean
    public abstract fun hasPrevious(): kotlin.Boolean
    public abstract override /*1*/ fun next(): T
    public abstract fun nextIndex(): kotlin.Int
    public abstract fun previous(): T
    public abstract fun previousIndex(): kotlin.Int
}

public abstract class LongIterator : kotlin.collections.Iterator<kotlin.Long> {
    /*primary*/ public constructor LongIterator()
    public final override /*1*/ fun next(): kotlin.Long
    public abstract fun nextLong(): kotlin.Long
}

public abstract class LongIterator : kotlin.collections.Iterator<kotlin.Long> {
    /*primary*/ public constructor LongIterator()
    public final override /*1*/ fun next(): kotlin.Long
    public abstract fun nextLong(): kotlin.Long
}

public interface Map</*0*/ K, /*1*/ out V> {
    public abstract val entries: kotlin.collections.Set<kotlin.collections.Map.Entry<K, V>>
        public abstract fun <get-entries>(): kotlin.collections.Set<kotlin.collections.Map.Entry<K, V>>
    public abstract val keys: kotlin.collections.Set<K>
        public abstract fun <get-keys>(): kotlin.collections.Set<K>
    public abstract val size: kotlin.Int
        public abstract fun <get-size>(): kotlin.Int
    public abstract val values: kotlin.collections.Collection<V>
        public abstract fun <get-values>(): kotlin.collections.Collection<V>
    public abstract fun containsKey(/*0*/ key: K): kotlin.Boolean
    public abstract fun containsValue(/*0*/ value: V): kotlin.Boolean
    public abstract operator fun get(/*0*/ key: K): V?
    public abstract fun isEmpty(): kotlin.Boolean

    public interface Entry</*0*/ out K, /*1*/ out V> {
        public abstract val key: K
            public abstract fun <get-key>(): K
        public abstract val value: V
            public abstract fun <get-value>(): V
    }
}

public interface Map</*0*/ K, /*1*/ out V> {
    public abstract val entries: kotlin.collections.Set<kotlin.collections.Map.Entry<K, V>>
        public abstract fun <get-entries>(): kotlin.collections.Set<kotlin.collections.Map.Entry<K, V>>
    public abstract val keys: kotlin.collections.Set<K>
        public abstract fun <get-keys>(): kotlin.collections.Set<K>
    public abstract val size: kotlin.Int
        public abstract fun <get-size>(): kotlin.Int
    public abstract val values: kotlin.collections.Collection<V>
        public abstract fun <get-values>(): kotlin.collections.Collection<V>
    public abstract fun containsKey(/*0*/ key: K): kotlin.Boolean
    public abstract fun containsValue(/*0*/ value: V): kotlin.Boolean
    public abstract operator fun get(/*0*/ key: K): V?
    public abstract fun isEmpty(): kotlin.Boolean

    public interface Entry</*0*/ out K, /*1*/ out V> {
        public abstract val key: K
            public abstract fun <get-key>(): K
        public abstract val value: V
            public abstract fun <get-value>(): V
    }
}

public interface MutableCollection</*0*/ E> : kotlin.collections.Collection<E>, kotlin.collections.MutableIterable<E> {
    public abstract fun add(/*0*/ element: E): kotlin.Boolean
    public abstract fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract fun clear(): kotlin.Unit
    public abstract override /*2*/ fun iterator(): kotlin.collections.MutableIterator<E>
    public abstract fun remove(/*0*/ element: E): kotlin.Boolean
    public abstract fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
}

public interface MutableCollection</*0*/ E> : kotlin.collections.Collection<E>, kotlin.collections.MutableIterable<E> {
    public abstract fun add(/*0*/ element: E): kotlin.Boolean
    public abstract fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract fun clear(): kotlin.Unit
    public abstract override /*2*/ fun iterator(): kotlin.collections.MutableIterator<E>
    public abstract fun remove(/*0*/ element: E): kotlin.Boolean
    public abstract fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
}

public interface MutableIterable</*0*/ out T> : kotlin.collections.Iterable<T> {
    public abstract override /*1*/ fun iterator(): kotlin.collections.MutableIterator<T>
}

public interface MutableIterable</*0*/ out T> : kotlin.collections.Iterable<T> {
    public abstract override /*1*/ fun iterator(): kotlin.collections.MutableIterator<T>
}

public interface MutableIterator</*0*/ out T> : kotlin.collections.Iterator<T> {
    public abstract fun remove(): kotlin.Unit
}

public interface MutableIterator</*0*/ out T> : kotlin.collections.Iterator<T> {
    public abstract fun remove(): kotlin.Unit
}

public interface MutableList</*0*/ E> : kotlin.collections.List<E>, kotlin.collections.MutableCollection<E> {
    public abstract override /*1*/ fun add(/*0*/ element: E): kotlin.Boolean
    public abstract fun add(/*0*/ index: kotlin.Int, /*1*/ element: E): kotlin.Unit
    public abstract fun addAll(/*0*/ index: kotlin.Int, /*1*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun clear(): kotlin.Unit
    public abstract override /*1*/ fun listIterator(): kotlin.collections.MutableListIterator<E>
    public abstract override /*1*/ fun listIterator(/*0*/ index: kotlin.Int): kotlin.collections.MutableListIterator<E>
    public abstract override /*1*/ fun remove(/*0*/ element: E): kotlin.Boolean
    public abstract override /*1*/ fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract fun removeAt(/*0*/ index: kotlin.Int): E
    public abstract override /*1*/ fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract operator fun set(/*0*/ index: kotlin.Int, /*1*/ element: E): E
    public abstract override /*1*/ fun subList(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.collections.MutableList<E>
}

public interface MutableList</*0*/ E> : kotlin.collections.List<E>, kotlin.collections.MutableCollection<E> {
    public abstract override /*1*/ fun add(/*0*/ element: E): kotlin.Boolean
    public abstract fun add(/*0*/ index: kotlin.Int, /*1*/ element: E): kotlin.Unit
    public abstract fun addAll(/*0*/ index: kotlin.Int, /*1*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun clear(): kotlin.Unit
    public abstract override /*1*/ fun listIterator(): kotlin.collections.MutableListIterator<E>
    public abstract override /*1*/ fun listIterator(/*0*/ index: kotlin.Int): kotlin.collections.MutableListIterator<E>
    public abstract override /*1*/ fun remove(/*0*/ element: E): kotlin.Boolean
    public abstract override /*1*/ fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract fun removeAt(/*0*/ index: kotlin.Int): E
    public abstract override /*1*/ fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract operator fun set(/*0*/ index: kotlin.Int, /*1*/ element: E): E
    public abstract override /*1*/ fun subList(/*0*/ fromIndex: kotlin.Int, /*1*/ toIndex: kotlin.Int): kotlin.collections.MutableList<E>
}

public interface MutableListIterator</*0*/ T> : kotlin.collections.ListIterator<T>, kotlin.collections.MutableIterator<T> {
    public abstract fun add(/*0*/ element: T): kotlin.Unit
    public abstract override /*2*/ fun hasNext(): kotlin.Boolean
    public abstract override /*2*/ fun next(): T
    public abstract override /*1*/ fun remove(): kotlin.Unit
    public abstract fun set(/*0*/ element: T): kotlin.Unit
}

public interface MutableListIterator</*0*/ T> : kotlin.collections.ListIterator<T>, kotlin.collections.MutableIterator<T> {
    public abstract fun add(/*0*/ element: T): kotlin.Unit
    public abstract override /*2*/ fun hasNext(): kotlin.Boolean
    public abstract override /*2*/ fun next(): T
    public abstract override /*1*/ fun remove(): kotlin.Unit
    public abstract fun set(/*0*/ element: T): kotlin.Unit
}

public interface MutableMap</*0*/ K, /*1*/ V> : kotlin.collections.Map<K, V> {
    public abstract override /*1*/ val entries: kotlin.collections.MutableSet<kotlin.collections.MutableMap.MutableEntry<K, V>>
        public abstract override /*1*/ fun <get-entries>(): kotlin.collections.MutableSet<kotlin.collections.MutableMap.MutableEntry<K, V>>
    public abstract override /*1*/ val keys: kotlin.collections.MutableSet<K>
        public abstract override /*1*/ fun <get-keys>(): kotlin.collections.MutableSet<K>
    public abstract override /*1*/ val values: kotlin.collections.MutableCollection<V>
        public abstract override /*1*/ fun <get-values>(): kotlin.collections.MutableCollection<V>
    public abstract fun clear(): kotlin.Unit
    public abstract fun put(/*0*/ key: K, /*1*/ value: V): V?
    public abstract fun putAll(/*0*/ from: kotlin.collections.Map<out K, V>): kotlin.Unit
    public abstract fun remove(/*0*/ key: K): V?

    public interface MutableEntry</*0*/ K, /*1*/ V> : kotlin.collections.Map.Entry<K, V> {
        public abstract fun setValue(/*0*/ newValue: V): V
    }
}

public interface MutableMap</*0*/ K, /*1*/ V> : kotlin.collections.Map<K, V> {
    public abstract override /*1*/ val entries: kotlin.collections.MutableSet<kotlin.collections.MutableMap.MutableEntry<K, V>>
        public abstract override /*1*/ fun <get-entries>(): kotlin.collections.MutableSet<kotlin.collections.MutableMap.MutableEntry<K, V>>
    public abstract override /*1*/ val keys: kotlin.collections.MutableSet<K>
        public abstract override /*1*/ fun <get-keys>(): kotlin.collections.MutableSet<K>
    public abstract override /*1*/ val values: kotlin.collections.MutableCollection<V>
        public abstract override /*1*/ fun <get-values>(): kotlin.collections.MutableCollection<V>
    public abstract fun clear(): kotlin.Unit
    public abstract fun put(/*0*/ key: K, /*1*/ value: V): V?
    public abstract fun putAll(/*0*/ from: kotlin.collections.Map<out K, V>): kotlin.Unit
    public abstract fun remove(/*0*/ key: K): V?

    public interface MutableEntry</*0*/ K, /*1*/ V> : kotlin.collections.Map.Entry<K, V> {
        public abstract fun setValue(/*0*/ newValue: V): V
    }
}

public interface MutableSet</*0*/ E> : kotlin.collections.Set<E>, kotlin.collections.MutableCollection<E> {
    public abstract override /*1*/ fun add(/*0*/ element: E): kotlin.Boolean
    public abstract override /*1*/ fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun clear(): kotlin.Unit
    public abstract override /*2*/ fun iterator(): kotlin.collections.MutableIterator<E>
    public abstract override /*1*/ fun remove(/*0*/ element: E): kotlin.Boolean
    public abstract override /*1*/ fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
}

public interface MutableSet</*0*/ E> : kotlin.collections.Set<E>, kotlin.collections.MutableCollection<E> {
    public abstract override /*1*/ fun add(/*0*/ element: E): kotlin.Boolean
    public abstract override /*1*/ fun addAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun clear(): kotlin.Unit
    public abstract override /*2*/ fun iterator(): kotlin.collections.MutableIterator<E>
    public abstract override /*1*/ fun remove(/*0*/ element: E): kotlin.Boolean
    public abstract override /*1*/ fun removeAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun retainAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
}

public interface RandomAccess {
}

public interface Set</*0*/ out E> : kotlin.collections.Collection<E> {
    public abstract override /*1*/ val size: kotlin.Int
        public abstract override /*1*/ fun <get-size>(): kotlin.Int
    public abstract override /*1*/ fun contains(/*0*/ element: E): kotlin.Boolean
    public abstract override /*1*/ fun containsAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun isEmpty(): kotlin.Boolean
    public abstract override /*1*/ fun iterator(): kotlin.collections.Iterator<E>
}

public interface Set</*0*/ out E> : kotlin.collections.Collection<E> {
    public abstract override /*1*/ val size: kotlin.Int
        public abstract override /*1*/ fun <get-size>(): kotlin.Int
    public abstract override /*1*/ fun contains(/*0*/ element: E): kotlin.Boolean
    public abstract override /*1*/ fun containsAll(/*0*/ elements: kotlin.collections.Collection<E>): kotlin.Boolean
    public abstract override /*1*/ fun isEmpty(): kotlin.Boolean
    public abstract override /*1*/ fun iterator(): kotlin.collections.Iterator<E>
}

public abstract class ShortIterator : kotlin.collections.Iterator<kotlin.Short> {
    /*primary*/ public constructor ShortIterator()
    public final override /*1*/ fun next(): kotlin.Short
    public abstract fun nextShort(): kotlin.Short
}

public abstract class ShortIterator : kotlin.collections.Iterator<kotlin.Short> {
    /*primary*/ public constructor ShortIterator()
    public final override /*1*/ fun next(): kotlin.Short
    public abstract fun nextShort(): kotlin.Short
}

@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public abstract class UByteIterator : kotlin.collections.Iterator<kotlin.UByte> {
    /*primary*/ public constructor UByteIterator()
    public final override /*1*/ fun next(): kotlin.UByte
    public abstract fun nextUByte(): kotlin.UByte
}

@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public abstract class UIntIterator : kotlin.collections.Iterator<kotlin.UInt> {
    /*primary*/ public constructor UIntIterator()
    public final override /*1*/ fun next(): kotlin.UInt
    public abstract fun nextUInt(): kotlin.UInt
}

@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public abstract class ULongIterator : kotlin.collections.Iterator<kotlin.ULong> {
    /*primary*/ public constructor ULongIterator()
    public final override /*1*/ fun next(): kotlin.ULong
    public abstract fun nextULong(): kotlin.ULong
}

@kotlin.SinceKotlin(version = "1.3") @kotlin.ExperimentalUnsignedTypes public abstract class UShortIterator : kotlin.collections.Iterator<kotlin.UShort> {
    /*primary*/ public constructor UShortIterator()
    public final override /*1*/ fun next(): kotlin.UShort
    public abstract fun nextUShort(): kotlin.UShort
}