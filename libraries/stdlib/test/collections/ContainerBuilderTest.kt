package test.collections

import test.collections.behaviors.listBehavior
import test.collections.behaviors.mapBehavior
import test.collections.behaviors.setBehavior
import kotlin.test.*

class ContainerBuilderTest {
    private fun <E> mutableCollectionOperations(e: E) = listOf<Pair<String, MutableCollection<E>.() -> Unit>>(
        "add(e)"                                to { add(e) },
        "addAll(listOf(e))"                     to { addAll(listOf(e)) },
        "remove(e)"                             to { remove(e) },
        "removeAll(listOf(e))"                  to { removeAll(listOf(e)) },
        "retainAll(listOf(e))"                  to { retainAll(listOf(e)) },
        "clear()"                               to { clear() },
        "iterator().apply { next() }.remove()"  to { iterator().apply { next() }.remove() }
    )

    private fun <E> mutableListOperations(e: E) = mutableCollectionOperations(e) + listOf<Pair<String, MutableList<E>.() -> Unit>>(
        "add(0, e)"                                 to { add(0, e) },
        "addAll(0, listOf(e))"                      to { addAll(0, listOf(e)) },
        "removeAt(0)"                               to { removeAt(0) },
        "set(0, e)"                                 to { set(0, e) },
        "listIterator().apply { next() }.remove()"  to { listIterator().apply { next() }.remove() },
        "listIterator(0).apply { next() }.remove()" to { listIterator(0).apply { next() }.remove() },
        "listIterator().apply { next() }.set(e)"    to { listIterator().apply { next() }.set(e) },
        "listIterator().add(e)"                     to { listIterator().add(e) }
    )

    private fun <E> mutableSetOperations(e: E): List<Pair<String, MutableSet<E>.() -> Unit>> = mutableCollectionOperations(e)

    private fun <K, V> mutableMapOperations(k: K, v: V) = listOf<Pair<String, MutableMap<K, V>.() -> Unit>>(
        "put(k, v)"                                     to { put(k, v) },
        "remove(k)"                                     to { remove(k) },
        "putAll(mapOf(k to v))"                         to { putAll(mapOf(k to v)) },
        "clear()"                                       to { clear() },
        "keys.clear()"                                  to { keys.clear() },
        "keys.iterator().apply { next() }.remove()"     to { keys.iterator().apply { next() }.remove() },
        "values.clear()"                                to { values.clear() },
        "values.iterator().apply { next() }.remove()"   to { values.iterator().apply { next() }.remove() },
        "entries.clear()"                               to { entries.clear() },
        "entries.iterator().apply { next() }.remove()"  to { entries.iterator().apply { next() }.remove() },
        "entries.first().setValue(v)"                   to { entries.first().setValue(v) },
        "entries.iterator().next().setValue(v)"         to { entries.iterator().next().setValue(v) }
    )

    @Test
    fun buildList() {
        val x = buildList {
            add('b')
            add('c')
        }

        var subList = mutableListOf<Char>()

        val y = buildList<Char>(4) {
            add('a')
            addAll(x)
            add('d')

            subList = subList(0, 4)
        }

        compare(listOf('a', 'b', 'c', 'd'), y) { listBehavior() }
        compare(listOf('a', 'b', 'c', 'd'), y.subList(0, 4)) { listBehavior() }
        compare(listOf('b', 'c'), y.subList(1, 4).subList(0, 2)) { listBehavior() }

        assertEquals(listOf(1), buildList(0) { add(1) })
        assertFailsWith<IllegalArgumentException> {
            buildList(-1) { add(0) }
        }

        assertTrue(y is MutableList<Char>)
        for ((fName, operation) in mutableListOperations('b')) {
            assertFailsWith<UnsupportedOperationException>(fName) { y.operation() }
            assertFailsWith<UnsupportedOperationException>(fName) { y.subList(1, 3).operation() }
            assertFailsWith<UnsupportedOperationException>(fName) { subList.operation() }
        }
    }

    @Test
    fun listBuilderSubList() {
        buildList<Char> {
            addAll(listOf('a', 'b', 'c', 'd', 'e'))

            val subList = subList(1, 4)
            compare(listOf('a', 'b', 'c', 'd', 'e'), this) { listBehavior() }
            compare(listOf('b', 'c', 'd'), subList) { listBehavior() }

            set(2, '1')
            compare(listOf('a', 'b', '1', 'd', 'e'), this) { listBehavior() }
            compare(listOf('b', '1', 'd'), subList) { listBehavior() }

            subList[2] = '2'
            compare(listOf('a', 'b', '1', '2', 'e'), this) { listBehavior() }
            compare(listOf('b', '1', '2'), subList) { listBehavior() }

            subList.add('3')
            compare(listOf('a', 'b', '1', '2', '3', 'e'), this) { listBehavior() }
            compare(listOf('b', '1', '2', '3'), subList) { listBehavior() }

            val subSubList = subList.subList(2, 4)
            // buffer reallocation should happen
            repeat(20) { subSubList.add('x') }
            repeat(20) { subSubList.add(subSubList.size - 2 * it, 'y') }

            val addedChars = "xy".repeat(20)
            compare("ab123${addedChars}e".toList(), this) { listBehavior() }
            compare("b123$addedChars".toList(), subList) { listBehavior() }
            compare("23$addedChars".toList(), subSubList) { listBehavior() }
        }
    }

    @Test
    fun buildSet() {
        val x = buildSet {
            add('b')
            add('c')
        }

        val y = buildSet(4) {
            add('c')
            addAll(x)
            add('d')
        }

        compare(setOf('c', 'b', 'd'), y) { setBehavior() }

        assertEquals(setOf(1), buildSet(0) { add(1) })
        assertFailsWith<IllegalArgumentException> {
            buildSet(-1) { add(0) }
        }

        assertTrue(y is MutableSet<Char>)
        for ((fName, operation) in mutableSetOperations('b')) {
            assertFailsWith<UnsupportedOperationException>(fName) { y.operation() }
        }
    }

    @Test
    fun buildMap() {
        val x = buildMap<Char, Int> {
            put('b', 2)
            put('c', 3)
        }

        val y = buildMap<Char, Int>(4) {
            put('a', 1)
            put('c', 0)
            putAll(x)
            put('d', 4)
        }

        compare(mapOf('a' to 1, 'c' to 3, 'b' to 2, 'd' to 4), y) { mapBehavior() }

        assertEquals(mapOf("a" to 1), buildMap<String, Int>(0) { put("a", 1) })
        assertFailsWith<IllegalArgumentException> {
            buildMap<String, Int>(-1) { put("x", 1) }
        }

        assertTrue(y is MutableMap<Char, Int>)
        for ((fName, operation) in mutableMapOperations('a', 0)) {
            assertFailsWith<UnsupportedOperationException>(fName) { y.operation() }
        }
        for ((fName, operation) in mutableSetOperations('a')) {
            assertFailsWith<UnsupportedOperationException>(fName) { y.keys.operation() }
        }
        for ((fName, operation) in mutableCollectionOperations(1)) {
            assertFailsWith<UnsupportedOperationException>(fName) { y.values.operation() }
        }
        for ((fName, operation) in mutableSetOperations(y.entries.first())) {
            assertFailsWith<UnsupportedOperationException>(fName) { y.entries.operation() }
        }
    }
}
