interface KindOwner {
    val allParents: List<KindOwner>
}

private val KindOwner.origin: KindOwner get() = this

private abstract class ElementMapping(elements: Collection<KindOwner>) {
    abstract val varToElements: Map<Int, KindOwner>
    abstract val elementsToVar: Map<KindOwner, Int>

    operator fun get(element: KindOwner): Int = elementsToVar.getValue(element)
    operator fun get(index: Int): KindOwner = varToElements.getValue(index)

    val size: Int = elements.size
}

private fun buildGraphs(elements: Collection<KindOwner>, elementMapping: ElementMapping): Pair<List<List<Int>>, List<List<Int>>> {
    val g = (1..elementMapping.size * 2).map { mutableListOf<Int>() }
    val gt = (1..elementMapping.size * 2).map { mutableListOf<Int>() }

    fun Int.direct(): Int = this
    fun Int.invert(): Int = this + 1

    fun extractIndex(element: KindOwner) = elementMapping[element] * 2

    for (element in elements) {
        val elementVar = extractIndex(element)
        for (parent in element.allParents) {
            val parentVar = extractIndex(parent.origin)
            // parent -> element
            g[parentVar.direct()] += elementVar.direct()
            g[elementVar.invert()] += parentVar.invert()
        }
        for (i in 0 until element.allParents.size) {
            for (j in i + 1 until element.allParents.size) {
                val firstParentVar = extractIndex(element.allParents[i].origin)
                val secondParentVar = extractIndex(element.allParents[j].origin)
                // firstParent -> !secondParent
                g[firstParentVar.direct()] += secondParentVar.invert()
                g[secondParentVar.direct()] += firstParentVar.invert()
            }
        }
    }

    for (from in g.indices) {
        for (to in g[from]) {
            gt[to] += from
        }
    }
    return g to gt
}
