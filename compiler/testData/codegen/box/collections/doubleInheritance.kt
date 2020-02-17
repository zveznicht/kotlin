// IGNORE_BACKEND_FIR: JVM_IR

open class EmptyListImpl : List<String> {
    override val size: Int = 0
    override fun contains(element: String): Boolean = false
    override fun containsAll(elements: Collection<String>): Boolean = false
    override fun get(index: Int): String { error() }
    override fun indexOf(element: String): Int = -1
    override fun lastIndexOf(element: String): Int = -1

    override fun isEmpty(): Boolean = true

    override fun iterator(): Iterator<String> = emptyIterator
    override fun listIterator(): ListIterator<String> = emptyIterator
    override fun listIterator(index: Int): ListIterator<String> = emptyIterator

    override fun subList(fromIndex: Int, toIndex: Int): List<String> = this
    
    companion object {
        val emptyIterator = object : ListIterator<String> {
            override fun hasNext(): Boolean = false
            override fun next(): String { error() }
            override fun nextIndex(): Int { error() }
            override fun hasPrevious(): Boolean = false
            override fun previous(): String { error() }
            override fun previousIndex(): Int { error() }
        }

        fun error(): Nothing { throw Throwable("No elements") }
    }
}

interface ListOfString : List<String>

// Stubs that block MutableList method call should not be generated here.
class DerivedEmptyList : EmptyListImpl(), ListOfString

fun box() : String {
    val sz = DerivedEmptyList().size
    if (sz == 0) return "OK"
    else return "fail"
}
