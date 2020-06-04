/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.resolve.dfa.cfg

import org.jetbrains.kotlin.fir.declarations.FirDeclaration

class ControlFlowGraph(val declaration: FirDeclaration?, val name: String, val kind: Kind) {
    private var _nodes: MutableList<CFGNode<*>> = mutableListOf()

    val nodes: List<CFGNode<*>>
        get() = _nodes

    internal fun addNode(node: CFGNode<*>) {
        assertState(State.Building)
        _nodes.add(node)
    }

    lateinit var enterNode: CFGNode<*>
        internal set

    lateinit var exitNode: CFGNode<*>
        internal set
    var owner: ControlFlowGraph? = null
        private set

    var state: State = State.Building
        private set

    private val _subGraphs: MutableList<ControlFlowGraph> = mutableListOf()
    val subGraphs: List<ControlFlowGraph> get() = _subGraphs

    internal fun complete() {
        assertState(State.Building)
        state = State.Completed
        val sortedNodes = orderNodes(enterNode)
        _nodes.clear()
        _nodes.addAll(sortedNodes)
    }

    internal fun addSubGraph(graph: ControlFlowGraph) {
        assertState(State.Building)
        assert(graph.owner == null) {
            "SubGraph already has owner"
        }
        graph.owner = this
        _subGraphs += graph
    }

    internal fun removeSubGraph(graph: ControlFlowGraph) {
        assertState(State.Building)
        assert(graph.owner == this)
        _subGraphs.remove(graph)
        graph.owner = null

        CFGNode.removeAllIncomingEdges(graph.enterNode)
        CFGNode.removeAllOutgoingEdges(graph.exitNode)
    }

    private fun assertState(state: State) {
        assert(this.state == state) {
            "This action can not be performed at $this state"
        }
    }

    enum class State {
        Building,
        Completed;
    }

    enum class Kind {
        Function, ClassInitializer, TopLevel
    }
}

enum class EdgeKind(val usedInDfa: Boolean) {
    Simple(usedInDfa = true),
    Dead(usedInDfa = false),
    Cfg(usedInDfa = false),
    Dfg(usedInDfa = true)
}

@OptIn(ExperimentalStdlibApi::class)
private fun orderNodes(startNode: CFGNode<*>): LinkedHashSet<CFGNode<*>> {
    val visitedNodes = LinkedHashSet<CFGNode<*>>()
    /*
     * [delayedNodes] is needed to accomplish next order contract:
     *   for each node all previous node lays before it
     */
    val delayedNodes = LinkedHashSet<CFGNode<*>>()
    val stack = ArrayDeque<CFGNode<*>>()
    stack.addFirst(startNode)
    while (stack.isNotEmpty()) {
        val node = stack.removeFirst()
        visitedNodes.add(node)
        val previousNodes = node.previousNodes
        if (!previousNodes.all { it in visitedNodes }) {
            if (!delayedNodes.add(node)) {
                throw IllegalArgumentException("Infinite loop")
            }
            stack.addLast(node)
        }
        val followingNodes = node.followingNodes

        followingNodes.filterNot { visitedNodes.contains(it) }.forEach { stack.addFirst(it) }
    }
    return visitedNodes
}
