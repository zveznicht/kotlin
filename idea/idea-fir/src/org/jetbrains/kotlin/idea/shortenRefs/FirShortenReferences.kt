/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.shortenRefs

import com.intellij.lang.Language
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.application.runUndoTransparentWriteAction
import com.intellij.openapi.editor.RangeMarker
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import gnu.trove.TIntArrayList
import org.jetbrains.kotlin.fir.ROOT_PREFIX_FOR_IDE_RESOLUTION_MODE
import org.jetbrains.kotlin.idea.core.util.range
import org.jetbrains.kotlin.idea.frontend.api.analyze
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtClassKind
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtClassOrObjectSymbol
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtPackageSymbol
import org.jetbrains.kotlin.idea.intentions.getLeftMostReceiverExpression
import org.jetbrains.kotlin.idea.references.KtSimpleNameReference
import org.jetbrains.kotlin.idea.references.mainReference
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.referenceExpression

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

    private fun collectShorteningsInFile(file: KtFile, elementFilter: PsiElementFilter): List<ShortenCommand> = runReadAction {
        val classCollector = QualifiedTypesCollector(elementFilter)
        file.accept(classCollector)
        val collectedTypes = classCollector.collectedElements

        val expressionsCollector = QualifiedExpressionCollector(elementFilter)
        file.accept(expressionsCollector)
        val collectedExpressions = expressionsCollector.collectedElements

        val typesToShorten = collectedTypes
            .filter { it.qualifier != null }
            .filter { typeCanBeShortened(it) }
            .map { ShortenTypeNow(it) }

        val expressionsToShorten = collectedExpressions
            .filter { expressionCanBeShortened(it) }
            .map { ShortenExpressionNow(it) }

        typesToShorten + expressionsToShorten
    }

    private fun expressionCanBeShortened(expression: KtDotQualifiedExpression): Boolean {
        if (!canBePossibleToDropReceiver(expression)) return false

        val shortenedExpression = createShortenedCopy(expression)

        val originalTarget = expression.resolveToPsi() ?: return false
        val shortenedTarget = shortenedExpression.resolveToPsi() ?: return false

        return originalTarget == shortenedTarget
    }

    private fun canBePossibleToDropReceiver(element: KtDotQualifiedExpression): Boolean {
        val nameRef = when (val receiver = element.receiverExpression) {
            is KtThisExpression -> return true
            is KtNameReferenceExpression -> receiver
            // FIXME this is a hack; currently fir does not work well with multi-word packages like `foo.bar` and can resolve only from `foo`
            is KtDotQualifiedExpression -> {
                receiver.getLeftMostReceiverExpression() as? KtNameReferenceExpression ?: return false
            }
            else -> return false
        }

        if (nameRef.getReferencedName() == ROOT_PREFIX_FOR_IDE_RESOLUTION_MODE) return true

        analyze(element) {
            return when (val symbol = nameRef.mainReference.resolveToSymbol()) {
                is KtPackageSymbol -> true
                is KtClassOrObjectSymbol -> symbol.classKind != KtClassKind.OBJECT
                else -> false
            }
        }
    }

    private fun typeCanBeShortened(typeUsage: KtUserType): Boolean {
        require(typeUsage.qualifier != null)

        val originalTypeResolved = typeUsage.resolveToPsi() ?: return false
        val shortenedTypeResolved = createShortenedCopy(typeUsage).resolveToPsi()

        return originalTypeResolved == shortenedTypeResolved
    }

    private fun createShortenedCopy(expression: KtDotQualifiedExpression): KtExpression {
        val fileCopy = expression.containingKtFile.copy() as KtFile
        val expressionCopy = findSameElementInCopy(expression, fileCopy)
        
        return expressionCopy.replace(expressionCopy.selectorExpression!!) as KtExpression
    }

    private fun createShortenedCopy(typeUsage: KtUserType): KtUserType {
        require(typeUsage.qualifier != null)

        val fileCopy = typeUsage.containingKtFile.copy() as KtFile
        val found = findSameElementInCopy(typeUsage, fileCopy)

        found.deleteQualifier()

        return found
    }

    fun performShortenings(shortenings: List<ShortenCommand>) {
        runUndoTransparentWriteAction {
            for (command in shortenings) {
                when (command) {
                    is ShortenTypeNow -> {
                        command.element.deleteQualifier()
                    }
                    is ShortenExpressionNow -> {
                        command.element.selectorExpression?.let(command.element::replace)
                    }
                }
            }
        }
    }
}

private fun KtElement.resolveToPsi(): PsiElement? {
    val referenceExpression = when (this) {
        is KtDotQualifiedExpression -> selectorExpression?.referenceExpression()
        is KtUserType -> referenceExpression
        is KtExpression -> referenceExpression()
        else -> return null
    }

    val singleReference = referenceExpression?.mainReference as? KtSimpleNameReference ?: return null

    analyze(containingKtFile) {
        return singleReference.resolveToSymbol()?.psi
    }
}

sealed class ShortenCommand
private class ShortenTypeNow(val element: KtUserType) : ShortenCommand()
private class ShortenExpressionNow(val element: KtDotQualifiedExpression): ShortenCommand()

private abstract class QualifiedElementsCollector<T : KtElement>(protected val elementFilter: PsiElementFilter) : KtVisitorVoid() {
    private val _collectedElements = ArrayList<T>()
    val collectedElements: List<T> = _collectedElements
    private var level = 0

    override fun visitElement(element: PsiElement) {
        if (elementFilter(element) != FilterResult.SKIP) {
            element.acceptChildren(this)
        }
    }

    protected fun addQualifiedElementToAnalyze(element: T) {
        _collectedElements += element
    }

    protected fun nextLevel() {
        level++
    }

    protected fun prevLevel() {
        level--
        assert(level >= 0)
    }
}

private class QualifiedTypesCollector(elementFilter: PsiElementFilter) : QualifiedElementsCollector<KtUserType>(elementFilter) {
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
}

private class QualifiedExpressionCollector(elementFilter: PsiElementFilter) :
    QualifiedElementsCollector<KtDotQualifiedExpression>(elementFilter) {
    override fun visitDotQualifiedExpression(expression: KtDotQualifiedExpression) {
        val filterResult = elementFilter(expression)
        if (filterResult == FilterResult.SKIP) return

        expression.selectorExpression?.acceptChildren(this)

        if (filterResult == FilterResult.PROCESS) {
            addQualifiedElementToAnalyze(expression)
            nextLevel()
        }

        // elements in receiver must be under
        expression.receiverExpression.accept(this)
        if (filterResult == FilterResult.PROCESS) {
            prevLevel()
        }
    }
}

private val KOTLIN_LANGUAGE = Language.findLanguageByID("kotlin")!!

/**
 * This is a copy from `com.intellij.psi.util.PsiTreeUtil#findSameElementInCopy` which is not available at the moment.
 */
private inline fun <reified T : PsiElement> findSameElementInCopy(element: T, copy: PsiFile): T {
    val offsets = TIntArrayList()
    var cur: PsiElement = element
    while (cur.javaClass != copy.javaClass) {
        var pos = 0
        var sibling = cur.prevSibling
        while (sibling != null) {
            pos++
            sibling = sibling.prevSibling
        }
        offsets.add(pos)
        cur = checkNotNull(cur.parent) { "Cannot find parent file" }
    }

    cur = copy
    for (level in offsets.size() - 1 downTo 0) {
        val pos = offsets[level]
        cur = checkNotNull(cur.firstChild) { "File structure differs: no child" }
        for (i in 0 until pos) {
            cur = checkNotNull(cur.nextSibling) { "File structure differs: number of siblings is less than $pos" }
        }
    }

    check(cur.javaClass == element.javaClass) { "File structure differs: ${cur.javaClass} != ${element.javaClass}" }
    return cur as T
}