/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.checkers

import com.intellij.lang.LighterASTNode
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import com.intellij.util.diff.FlyweightCapableTreeStructure
import org.jetbrains.kotlin.fir.*

/**
 * Allows to map some psi/light nodes
 * to some other nodes which can help
 * to clarify the exact node we're working with.
 */
class SourceClarification {
    companion object {
        val samePsi = { it: PsiElement -> it }
        val sameLightNode = { it: LighterASTNode -> it }
    }

    var clarifyPsi = samePsi
    var clarifyLightNode = sameLightNode

    fun forPsi(clarify: (PsiElement) -> PsiElement): SourceClarification {
        clarifyPsi = clarify
        return this
    }

    fun forLightNode(clarify: (LighterASTNode) -> LighterASTNode): SourceClarification {
        clarifyLightNode = clarify
        return this
    }
}

/**
 * Describes the corresponding FirSource elements
 * for anything that may have type arguments.
 */
class TypeArgumentSources(
    /**
     * The source for some element itself.
     */
    val self: FirSourceElement,
    /**
     * The source representing the type
     * arguments list (with `<>`).
     */
    val arguments: FirSourceElement?
) {
    /**
     * Returns true if the type argument list
     * is not empty.
     */
    fun hasAnyArguments(): Boolean {
        val psi = arguments.psi
        val lightNode = arguments.lightNode

        if (psi != null && psi !is PsiErrorElement) {
            return psi.children.isNotEmpty()
        } else if (lightNode != null && arguments is FirLightSourceElement) {
            return lightNode.getChildren(arguments.tree).any { it != null }
        }

        return false
    }
}

/**
 * Starting from `this` searches for all corresponding
 * TypeArgumentSources.
 *
 * e.g. for
 *      `test<List<Int>>`
 * returns
 *      [(`test`, `<List<Int>>`), (`List`, `<Int>`), (`Int`, null)]
 */
fun FirSourceElement.retrieveTypeArgumentsSources(sourceClarification: SourceClarification? = null): List<TypeArgumentSources> {
    val localPsi = this.psi
    val localLight = this.lightNode

    if (localPsi != null && localPsi !is PsiErrorElement) {
        return localPsi.retrieveTypeArgumentsSources(
            sourceClarification?.clarifyPsi ?: SourceClarification.samePsi
        )
    } else if (localLight != null && this is FirLightSourceElement) {
        return localLight.retrieveTypeArgumentsSources(
            this.tree,
            sourceClarification?.clarifyLightNode ?: SourceClarification.sameLightNode
        )
    }

    return emptyList()
}

private fun PsiElement.retrieveTypeArgumentsSources(
    clarifyPsi: (PsiElement) -> PsiElement
): List<TypeArgumentSources> {
    val result = mutableListOf<TypeArgumentSources>()

    fun PsiElement.collect() {
        when {
            this.children.isEmpty() -> {
                result.add(TypeArgumentSources(this.toFirPsiSourceElement(), null))
            }
            this.children.size == 1 -> {
                val self = this.children[0]
                result.add(TypeArgumentSources(self.toFirPsiSourceElement(), null))
            }
            this.children.size >= 2 -> {
                val self = this.children[0]
                val arguments = this.children[1]
                result.add(TypeArgumentSources(self.toFirPsiSourceElement(), arguments.toFirPsiSourceElement()))

                if (arguments !is PsiErrorElement) {
                    arguments.children.forEach {
                        if (it !is PsiErrorElement && it.firstChild.children.isNotEmpty()) {
                            it.firstChild.children[0].collect()
                        }
                    }
                }
            }
        }
    }

    clarifyPsi(this).collect()
    return result
}

private fun LighterASTNode.retrieveTypeArgumentsSources(
    tree: FlyweightCapableTreeStructure<LighterASTNode>,
    clarifyLightNode: (LighterASTNode) -> LighterASTNode
): List<TypeArgumentSources> {
    val result = mutableListOf<TypeArgumentSources>()

    fun LighterASTNode.collect() {
        val children = this.getChildren(tree)

        when {
            children.isEmpty() -> {
                result.add(TypeArgumentSources(this.toFirLightSourceElement(tree), null))
            }
            children.size == 1 -> {
                children[0]?.let {
                    result.add(TypeArgumentSources(it.toFirLightSourceElement(tree), null))
                }
            }
            children.size >= 2 -> {
                val self = children[0]
                val arguments = children[1]

                if (self != null) {
                    if (arguments != null) {
                        result.add(TypeArgumentSources(self.toFirLightSourceElement(tree), arguments.toFirLightSourceElement(tree)))

                        val argumentsChildren = arguments.getChildren(tree)
                        argumentsChildren.getOrNull(1)
                            ?.getChildren(tree)?.getOrNull(0)
                            ?.getChildren(tree)?.getOrNull(0)
                            ?.collect()
                    } else {
                        result.add(TypeArgumentSources(self.toFirLightSourceElement(tree), null))
                    }
                }
            }
        }
    }

    clarifyLightNode(this).collect()
    return result
}

private fun LighterASTNode.toFirLightSourceElement(tree: FlyweightCapableTreeStructure<LighterASTNode>) =
    toFirLightSourceElement(startOffset, endOffset, tree)

private fun LighterASTNode.getChildren(tree: FlyweightCapableTreeStructure<LighterASTNode>): Array<out LighterASTNode?> {
    val childrenRef = Ref<Array<LighterASTNode>>()
    val childCount = tree.getChildren(this, childrenRef)
    return if (childCount > 0) childrenRef.get() else emptyArray()
}