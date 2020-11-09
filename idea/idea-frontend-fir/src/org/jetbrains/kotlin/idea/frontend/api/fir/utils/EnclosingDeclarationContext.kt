/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.frontend.api.fir.utils

import com.intellij.psi.util.parentsOfType
import org.jetbrains.kotlin.fir.declarations.FirFile
import org.jetbrains.kotlin.idea.fir.low.level.api.api.FirModuleResolveState
import org.jetbrains.kotlin.idea.fir.low.level.api.api.LowLevelFirApiFacadeForCompletion
import org.jetbrains.kotlin.idea.util.getElementTextInContext
import org.jetbrains.kotlin.psi.*

internal sealed class EnclosingDeclarationContext {
    abstract val enclosingDeclaration: KtDeclaration

    companion object {
        fun detect(positionInFile: KtElement): EnclosingDeclarationContext {
            val ktNamedFunction = positionInFile.parentsOfType<KtNamedFunction>().firstOrNull { it.name != null }
            if (ktNamedFunction != null) {
                return FunctionContext(ktNamedFunction)
            }

            val ktProperty = positionInFile.parentsOfType<KtProperty>().firstOrNull { !it.isLocal }
            if (ktProperty != null) {
                return PropertyContext(ktProperty)
            }

            error("Cannot find enclosing declaration for ${positionInFile.getElementTextInContext()}")
        }
    }
}

internal class FunctionContext(
    override val enclosingDeclaration: KtNamedFunction
) : EnclosingDeclarationContext()

internal class PropertyContext(
    override val enclosingDeclaration: KtProperty
) : EnclosingDeclarationContext()

internal fun EnclosingDeclarationContext.buildCompletionContext(originalFirFile: FirFile, firResolveState: FirModuleResolveState) =
    when (this) {
        is FunctionContext -> LowLevelFirApiFacadeForCompletion.buildCompletionContextForFunction(
            originalFirFile,
            enclosingDeclaration,
            state = firResolveState
        )

        is PropertyContext -> LowLevelFirApiFacadeForCompletion.buildCompletionContextForProperty(
            originalFirFile,
            enclosingDeclaration,
            state = firResolveState
        )
    }