
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
