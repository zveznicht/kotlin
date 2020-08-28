/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis

import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import org.jetbrains.kotlin.psi.psiUtil.allChildren
import java.util.*

class PsiElementFinderByType(
    private val root: PsiElement,
    private val types: Collection<IElementType>,
    private var index: Int,
    private val depth: Int
) : ElementFinderByType<PsiElement> {
    // <element, currentDepth>
    private val queue = LinkedList<Pair<PsiElement, Int>>().apply {
        add(root to 0)
    }

    override fun find(): PsiElement? {
        while (queue.isNotEmpty()) {
            val (element, currentDepth) = queue.poll()

            if (element.node.elementType in types) {
                if (index == 0) {
                    return element
                }
                index--
            }

            if (currentDepth == depth) continue

            for (child in element.allChildren) {
                queue.add(child to currentDepth + 1)
            }
        }

        return null
    }
}