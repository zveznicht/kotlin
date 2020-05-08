/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.collections.builders

@PublishedApi
internal class ListBuilder<E> private constructor(
    private var array: Array<E>,
    private var length: Int,
    private var isReadOnly: Boolean
) : MutableList<E>, RandomAccess, AbstractMutableList<E>() {

    constructor() : this(10)

    constructor(initialCapacity: Int) : this(
        arrayOfUninitializedElements(initialCapacity), 0, false)

    fun build(): List<E> {
        checkIsMutable()
        isReadOnly = true
        return this
    }

    override val size: Int
        get() = length

    override fun isEmpty(): Boolean = length == 0

    override fun get(index: Int): E {
        AbstractList.checkElementIndex(index, length)
        return array[index]
    }

    override operator fun set(index: Int, element: E): E {
        checkIsMutable()
        AbstractList.checkElementIndex(index, length)
        val old = array[index]
        array[index] = element
        return old
    }

    override fun indexOf(element: E): Int = array.indexOf(element, 0, length)
    override fun lastIndexOf(element: E): Int = array.lastIndexOf(element, 0, length)

    override fun iterator(): MutableIterator<E> = Itr(this, 0)
    override fun listIterator(): MutableListIterator<E> = Itr(this, 0)

    override fun listIterator(index: Int): MutableListIterator<E> {
        AbstractList.checkPositionIndex(index, length)
        return Itr(this, index)
    }

    override fun add(element: E): Boolean {
        checkIsMutable()
        addAtInternal(length, element)
        return true
    }

    override fun add(index: Int, element: E) {
        checkIsMutable()
        AbstractList.checkPositionIndex(index, length)
        addAtInternal(index, element)
    }

    override fun addAll(elements: Collection<E>): Boolean {
        checkIsMutable()
        val n = elements.size
        addAllInternal(length, elements, n)
        return n > 0
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        checkIsMutable()
        AbstractList.checkPositionIndex(index, length)
        val n = elements.size
        addAllInternal(index, elements, n)
        return n > 0
    }

    override fun clear() {
        checkIsMutable()
        removeRangeInternal(0, length)
    }

    override fun removeAt(index: Int): E {
        checkIsMutable()
        AbstractList.checkElementIndex(index, length)
        return removeAtInternal(index)
    }

    override fun remove(element: E): Boolean {
        checkIsMutable()
        val i = indexOf(element)
        if (i >= 0) removeAt(i)
        return i >= 0
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        checkIsMutable()
        return retainOrRemoveAllInternal(0, length, elements, false) > 0
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        checkIsMutable()
        return retainOrRemoveAllInternal(0, length, elements, true) > 0
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
        AbstractList.checkRangeIndexes(fromIndex, toIndex, length)
        return SubList(this, null, fromIndex, toIndex - fromIndex)
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun ensureCapacity(minCapacity: Int) {
        if (minCapacity > array.size) {
            val newSize = ArrayDeque.newCapacity(array.size, minCapacity)
            array = array.copyOfUninitializedElements(newSize)
        }
    }

    override fun equals(other: Any?): Boolean {
        return other === this ||
                (other is List<*>) && array.subarrayContentEquals(0, length, other)
    }

    override fun hashCode(): Int {
        return array.subarrayContentHashCode(0, length)
    }

    override fun toString(): String {
        return array.subarrayContentToString(0, length)
    }

    // ---------------------------- private ----------------------------

    private fun checkIsMutable() {
        if (isReadOnly) throw UnsupportedOperationException()
    }

    private fun ensureExtraCapacity(n: Int) {
        ensureCapacity(length + n)
    }

    private fun insertAtInternal(i: Int, n: Int) {
        ensureExtraCapacity(n)
        array.copyInto(array, startIndex = i, endIndex = length, destinationOffset = i + n)
        length += n
    }

    private fun addAtInternal(i: Int, element: E) {
        insertAtInternal(i, 1)
        array[i] = element
    }

    private fun addAllInternal(i: Int, elements: Collection<E>, n: Int) {
        insertAtInternal(i, n)
        var j = 0
        val it = elements.iterator()
        while (j < n) {
            array[i + j] = it.next()
            j++
        }
    }

    private fun removeAtInternal(i: Int): E {
        val old = array[i]
        array.copyInto(array, startIndex = i + 1, endIndex = length, destinationOffset = i)
        array.resetAt(length - 1)
        length--
        return old
    }

    private fun removeRangeInternal(rangeOffset: Int, rangeLength: Int) {
        array.copyInto(array, startIndex = rangeOffset + rangeLength, endIndex = length, destinationOffset = rangeOffset)
        array.resetRange(fromIndex = length - rangeLength, toIndex = length)
        length -= rangeLength
    }

    /** Retains elements if [retain] == true and removes them it [retain] == false. */
    private fun retainOrRemoveAllInternal(rangeOffset: Int, rangeLength: Int, elements: Collection<E>, retain: Boolean): Int {
        var i = 0
        var j = 0
        while (i < rangeLength) {
            if (elements.contains(array[rangeOffset + i]) == retain) {
                array[rangeOffset + j++] = array[rangeOffset + i++]
            } else {
                i++
            }
        }
        val removed = rangeLength - j
        array.copyInto(array, startIndex = rangeOffset + rangeLength, endIndex = length, destinationOffset = rangeOffset + j)
        array.resetRange(fromIndex = length - removed, toIndex = length)
        length -= removed
        return removed
    }

    private class Itr<E>(private val list: ListBuilder<E>, private var index: Int) : MutableListIterator<E> {
        private var lastIndex: Int = -1

        override fun hasPrevious(): Boolean = index > 0
        override fun hasNext(): Boolean = index < list.length

        override fun previousIndex(): Int = index - 1
        override fun nextIndex(): Int = index

        override fun previous(): E {
            if (index <= 0) throw NoSuchElementException()
            lastIndex = --index
            return list.array[lastIndex]
        }

        override fun next(): E {
            if (index >= list.length) throw NoSuchElementException()
            lastIndex = index++
            return list.array[lastIndex]
        }

        override fun set(element: E) {
            check(lastIndex != -1) { "Call next() or previous() before replacing element from the iterator." }
            list.checkIsMutable()
            list.array[lastIndex] = element
        }

        override fun add(element: E) {
            list.add(index++, element)
            lastIndex = -1
        }

        override fun remove() {
            check(lastIndex != -1) { "Call next() or previous() before removing element from the iterator." }
            list.removeAt(lastIndex)
            index = lastIndex
            lastIndex = -1
        }
    }

    private class SubList<E>(
        val root: ListBuilder<E>,
        val backing: SubList<E>?,
        val offset: Int,
        var length: Int
    ) : RandomAccess, AbstractMutableList<E>() {

        override val size: Int
            get() = length

        override fun get(index: Int): E {
            AbstractList.checkElementIndex(index, length)
            return root.array[offset + index]
        }

        override fun set(index: Int, element: E): E {
            root.checkIsMutable()
            AbstractList.checkElementIndex(index, length)
            val old = root.array[offset + index]
            root.array[offset + index] = element
            return old
        }

        override fun indexOf(element: E): Int = root.array.indexOf(element, offset, length)
        override fun lastIndexOf(element: E): Int = root.array.lastIndexOf(element, offset, length)

        override fun iterator(): MutableIterator<E> = SubListItr(this, 0)
        override fun listIterator(): MutableListIterator<E> = SubListItr(this, 0)

        override fun listIterator(index: Int): MutableListIterator<E> {
            AbstractList.checkPositionIndex(index, length)
            return SubListItr(this, index)
        }

        override fun add(element: E): Boolean {
            root.checkIsMutable()
            addAtInternal(offset + length, element)
            return true
        }

        override fun add(index: Int, element: E) {
            root.checkIsMutable()
            AbstractList.checkPositionIndex(index, length)
            addAtInternal(offset + index, element)
        }

        override fun addAll(elements: Collection<E>): Boolean {
            root.checkIsMutable()
            val n = elements.size
            addAllInternal(offset + length, elements, n)
            return n > 0
        }

        override fun addAll(index: Int, elements: Collection<E>): Boolean {
            root.checkIsMutable()
            AbstractList.checkPositionIndex(index, length)
            val n = elements.size
            addAllInternal(offset + index, elements, n)
            return n > 0
        }

        override fun clear() {
            root.checkIsMutable()
            removeRangeInternal(offset, length)
        }

        override fun removeAt(index: Int): E {
            root.checkIsMutable()
            AbstractList.checkElementIndex(index, length)
            return removeAtInternal(offset + index)
        }

        override fun remove(element: E): Boolean {
            root.checkIsMutable()
            val i = indexOf(element)
            if (i >= 0) removeAt(i)
            return i >= 0
        }

        override fun removeAll(elements: Collection<E>): Boolean {
            root.checkIsMutable()
            return retainOrRemoveAllInternal(offset, length, elements, false) > 0
        }

        override fun retainAll(elements: Collection<E>): Boolean {
            root.checkIsMutable()
            return retainOrRemoveAllInternal(offset, length, elements, true) > 0
        }

        override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
            AbstractList.checkRangeIndexes(fromIndex, toIndex, length)
            return SubList(root, this, offset + fromIndex, toIndex - fromIndex)
        }

        override fun equals(other: Any?): Boolean {
            return other === this ||
                    (other is List<*>) && root.array.subarrayContentEquals(offset, length, other)
        }

        override fun hashCode(): Int {
            return root.array.subarrayContentHashCode(offset, length)
        }

        override fun toString(): String {
            return root.array.subarrayContentToString(offset, length)
        }

        // ---------------------------- private ----------------------------

        private fun addAtInternal(i: Int, element: E) {
            if (backing != null) backing.addAtInternal(i, element) else root.addAtInternal(i, element)
            length++
        }

        private fun addAllInternal(i: Int, elements: Collection<E>, n: Int) {
            if (backing != null) backing.addAllInternal(i, elements, n) else root.addAllInternal(i, elements, n)
            length += n
        }

        private fun removeAtInternal(i: Int): E {
            val old = if (backing != null) backing.removeAtInternal(i) else root.removeAtInternal(i)
            length--
            return old
        }

        private fun removeRangeInternal(rangeOffset: Int, rangeLength: Int) {
            if (backing != null) backing.removeRangeInternal(rangeOffset, rangeLength) else root.removeRangeInternal(rangeOffset, rangeLength)
            length -= rangeLength
        }

        /** Retains elements if [retain] == true and removes them it [retain] == false. */
        private fun retainOrRemoveAllInternal(rangeOffset: Int, rangeLength: Int, elements: Collection<E>, retain: Boolean): Int {
            val removed = if (backing != null) backing.retainOrRemoveAllInternal(rangeOffset, rangeLength, elements, retain) else
                root.retainOrRemoveAllInternal(rangeOffset, rangeLength, elements, retain)
            length -= removed
            return removed
        }

        private class SubListItr<E>(private val list: SubList<E>, private var index: Int) : MutableListIterator<E> {
            private var lastIndex: Int = -1

            override fun hasPrevious(): Boolean = index > 0
            override fun hasNext(): Boolean = index < list.length

            override fun previousIndex(): Int = index - 1
            override fun nextIndex(): Int = index

            override fun previous(): E {
                if (index <= 0) throw NoSuchElementException()
                lastIndex = --index
                return list.root.array[list.offset + lastIndex]
            }

            override fun next(): E {
                if (index >= list.length) throw NoSuchElementException()
                lastIndex = index++
                return list.root.array[list.offset + lastIndex]
            }

            override fun set(element: E) {
                check(lastIndex != -1) { "Call next() or previous() before replacing element from the iterator." }
                list.root.checkIsMutable()
                list.root.array[list.offset + lastIndex] = element
            }

            override fun add(element: E) {
                list.add(index++, element)
                lastIndex = -1
            }

            override fun remove() {
                check(lastIndex != -1) { "Call next() or previous() before removing element from the iterator." }
                list.removeAt(lastIndex)
                index = lastIndex
                lastIndex = -1
            }
        }
    }
}

internal fun <E> arrayOfUninitializedElements(size: Int): Array<E> {
    require(size >= 0) { "capacity must be non-negative." }
    @Suppress("UNCHECKED_CAST")
    return arrayOfNulls<Any?>(size) as Array<E>
}

private fun <T> Array<out T>.subarrayContentToString(offset: Int, length: Int): String {
    val sb = StringBuilder(2 + length * 3)
    sb.append("[")
    var i = 0
    while (i < length) {
        if (i > 0) sb.append(", ")
        sb.append(this[offset + i])
        i++
    }
    sb.append("]")
    return sb.toString()
}

private fun <T> Array<T>.subarrayContentHashCode(offset: Int, length: Int): Int {
    var result = 1
    var i = 0
    while (i < length) {
        val nextElement = this[offset + i]
        result = result * 31 + nextElement.hashCode()
        i++
    }
    return result
}

private fun <T> Array<T>.subarrayContentEquals(offset: Int, length: Int, other: List<*>): Boolean {
    if (length != other.size) return false
    var i = 0
    while (i < length) {
        if (this[offset + i] != other[i]) return false
        i++
    }
    return true
}

internal fun <T> Array<T>.copyOfUninitializedElements(newSize: Int): Array<T> {
    @Suppress("UNCHECKED_CAST")
    return copyOf(newSize) as Array<T>
}

internal fun <E> Array<E>.resetAt(index: Int) {
    @Suppress("UNCHECKED_CAST")
    (this as Array<Any?>)[index] = null
}

internal fun <E> Array<E>.resetRange(fromIndex: Int, toIndex: Int) {
    for (index in fromIndex until toIndex) resetAt(index)
}

private fun <T> Array<T>.indexOf(element: T, offset: Int, length: Int): Int {
    var i = 0
    while (i < length) {
        if (this[offset + i] == element) return i
        i++
    }
    return -1
}

private fun <T> Array<T>.lastIndexOf(element: T, offset: Int, length: Int): Int {
    var i = length - 1
    while (i >= 0) {
        if (this[offset + i] == element) return i
        i--
    }
    return -1
}