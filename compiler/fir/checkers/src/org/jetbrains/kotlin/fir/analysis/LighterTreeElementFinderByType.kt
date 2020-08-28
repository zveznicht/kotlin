/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis

import com.intellij.lang.LighterASTNode
import com.intellij.openapi.util.Ref
import com.intellij.psi.tree.IElementType
import com.intellij.util.diff.FlyweightCapableTreeStructure
import java.util.*

class LighterTreeElementFinderByType(
    private val tree: FlyweightCapableTreeStructure<LighterASTNode>,
    private val root: LighterASTNode?,
    private var types: Collection<IElementType>,
    private var index: Int,
    private val depth: Int
) : ElementFinderByType<LighterASTNode> {
    // <node, currentDepth>
    private val queue = LinkedList<Pair<LighterASTNode, Int>>().apply {
        root?.let { add(it to 0) }
    }

    override fun find(): LighterASTNode? {
        while (queue.isNotEmpty()) {
            val (node, currentDepth) = queue.poll()

            if (node.tokenType in types) {
                if (index == 0) {
                    return node
                }
                index--
            }

            if (currentDepth == depth) continue

            for (child in node.getChildren()) {
                queue.add(child to currentDepth + 1)
            }
        }

        return null
    }

    private fun LighterASTNode.getChildren(): List<LighterASTNode> {
        val ref = Ref<Array<LighterASTNode?>>()
        tree.getChildren(this, ref)
        return ref.get()?.filterNotNull() ?: emptyList()
    }
}