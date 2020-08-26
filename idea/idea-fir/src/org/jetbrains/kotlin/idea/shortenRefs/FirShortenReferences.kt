/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.shortenRefs

import com.intellij.codeInsight.CodeInsightUtilCore
import com.intellij.lang.Language
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.application.runUndoTransparentWriteAction
import com.intellij.openapi.editor.RangeMarker
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.core.util.range
import org.jetbrains.kotlin.idea.frontend.api.analyze
import org.jetbrains.kotlin.idea.references.mainReference
import org.jetbrains.kotlin.psi.*

private enum class FilterResult {
    SKIP,
    GO_INSIDE,
    PROCESS
}

private typealias PsiElementFilter = (element: PsiElement) -> FilterResult

object FirShortenReferences {
    fun collectPossibleShortenings(file: KtFile, startOffset: Int, endOffset: Int): List<ShortenCommand> {
        val rangeMarker = runReadAction {
            val documentManager = PsiDocumentManager.getInstance(file.project)
            val document = file.viewProvider.document!!
            check(documentManager.isCommitted(document)) { "Document should be committed to shorten references in range" }
            document.createRangeMarker(startOffset, endOffset).apply {
                isGreedyToLeft = true
                isGreedyToRight = true
            }
        }

        try {
            return collectShorteningsInFile(file, createRangeFilter(rangeMarker))
        } finally {
            rangeMarker.dispose()
        }
    }

    private fun createRangeFilter(rangeMarker: RangeMarker): PsiElementFilter {
        return fun(element: PsiElement): FilterResult {
            val range = rangeMarker.range ?: return FilterResult.SKIP
            val elementRange = element.textRange!!

            return when {
                range.contains(elementRange) -> FilterResult.PROCESS
                range.intersects(elementRange) -> {
                    // for qualified call expression allow to shorten only the part without parenthesis
                    val calleeExpression = ((element as? KtDotQualifiedExpression)
                        ?.selectorExpression as? KtCallExpression)
                        ?.calleeExpression
                    if (calleeExpression != null) {
                        val rangeWithoutParenthesis = TextRange(elementRange.startOffset, calleeExpression.textRange!!.endOffset)
                        if (range.contains(rangeWithoutParenthesis)) FilterResult.PROCESS else FilterResult.GO_INSIDE
                    } else {
                        FilterResult.GO_INSIDE
                    }
                }
                else -> FilterResult.SKIP
            }
        }
    }

    private fun collectShorteningsInFile(file: KtFile, elementFilter: PsiElementFilter): List<ShortenCommand> {
        val collectedTypes = runReadAction {
            val classCollector = TypesCollectorVisitor(elementFilter)
            file.accept(classCollector)

            classCollector.collectedTypes
        }

        return runReadAction {
            collectedTypes
                .filter { it.qualifier != null }
                .filter { typeCanBeShortened(it) }
                .map { ShortenNow(it) }
        }
    }

    private fun typeCanBeShortened(typeUsage: KtUserType): Boolean {
        require(typeUsage.qualifier != null)

        val originalTypeResolved = typeUsage.resolveToPsi() ?: return false
        val shortenedTypeResolved = createShortenedCopy(typeUsage).resolveToPsi()

        return originalTypeResolved == shortenedTypeResolved
    }

    private fun createShortenedCopy(typeUsage: KtUserType): KtUserType {
        require(typeUsage.qualifier != null)

        val fileCopy = typeUsage.containingKtFile.copy() as KtFile
        val found = findKtElementInRange<KtUserType>(fileCopy, typeUsage.textRange) ?: error("Could not find a matching element!")

        found.deleteQualifier()

        return found
    }

    fun performShortenings(shortenings: List<ShortenCommand>) {
        for (command in shortenings) {
            if (command is ShortenNow) {
                runUndoTransparentWriteAction {
                    command.element.deleteQualifier()
                }
            }
        }
    }
}

private fun KtUserType.resolveToPsi(): PsiElement? {
    val singeReference = referenceExpression?.mainReference ?: return null

    analyze(containingKtFile) {
        return singeReference.resolveToSymbol()?.psi
    }
}

sealed class ShortenCommand
private class ShortenNow(val element: KtUserType) : ShortenCommand()

private class TypesCollectorVisitor(private val elementFilter: PsiElementFilter) : KtVisitorVoid() {
    private val _collectedTypes = ArrayList<KtUserType>()
    val collectedTypes: List<KtUserType> = _collectedTypes

    override fun visitUserType(userType: KtUserType) {
        val filterResult = elementFilter(userType)
        if (filterResult == FilterResult.SKIP) return

        userType.typeArgumentList?.accept(this)

        if (filterResult == FilterResult.PROCESS) {
            addQualifiedElementToAnalyze(userType)
            nextLevel()
        }

        // elements in qualifier must be under
        userType.qualifier?.accept(this)
        if (filterResult == FilterResult.PROCESS) {
            prevLevel()
        }
    }

    override fun visitElement(element: PsiElement) {
        if (elementFilter(element) != FilterResult.SKIP) {
            element.acceptChildren(this)
        }
    }


    private fun addQualifiedElementToAnalyze(element: KtUserType) {
        _collectedTypes += element
    }

    private var level = 0

    private fun nextLevel() {
        level++
    }

    private fun prevLevel() {
        level--
        assert(level >= 0)
    }
}

private val KOTLIN_LANGUAGE = Language.findLanguageByID("kotlin")!!

private inline fun <reified T : KtElement> findKtElementInRange(file: KtFile, range: TextRange): T? {
    return CodeInsightUtilCore.findElementInRange(file, range.startOffset, range.endOffset, T::class.java, KOTLIN_LANGUAGE)
}