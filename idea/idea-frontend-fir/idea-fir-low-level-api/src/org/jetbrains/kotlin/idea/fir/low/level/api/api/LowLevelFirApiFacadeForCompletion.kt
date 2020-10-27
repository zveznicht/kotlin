/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.low.level.api.api

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirFile
import org.jetbrains.kotlin.fir.declarations.FirProperty
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase
import org.jetbrains.kotlin.fir.declarations.FirSimpleFunction
import org.jetbrains.kotlin.fir.declarations.builder.buildPropertyAccessorCopy
import org.jetbrains.kotlin.fir.declarations.builder.buildPropertyCopy
import org.jetbrains.kotlin.fir.declarations.builder.buildSimpleFunctionCopy
import org.jetbrains.kotlin.fir.resolve.FirTowerDataContext
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.idea.fir.low.level.api.FirModuleResolveStateImpl
import org.jetbrains.kotlin.idea.fir.low.level.api.element.builder.FirTowerDataContextCollector
import org.jetbrains.kotlin.idea.fir.low.level.api.providers.FirIdeProvider
import org.jetbrains.kotlin.idea.fir.low.level.api.providers.firIdeProvider
import org.jetbrains.kotlin.idea.fir.low.level.api.util.ktDeclaration
import org.jetbrains.kotlin.idea.util.getElementTextInContext
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty

object LowLevelFirApiFacadeForCompletion {
    class FirCompletionContext internal constructor(
        val session: FirSession,
        private val towerDataContextCollector: FirTowerDataContextCollector,
    ) {
        fun getTowerDataContext(element: KtElement): FirTowerDataContext {
            var current: PsiElement? = element
            while (current is KtElement) {
                towerDataContextCollector.getContext(current)?.let { return it }
                current = current.parent
            }

            error("No context for ${element.getElementTextInContext()}")
        }
    }

    fun buildCompletionContextForFunction(
        firFile: FirFile,
        ktFunction: KtNamedFunction,
        state: FirModuleResolveState,
    ): FirCompletionContext {
        val firIdeProvider = firFile.session.firIdeProvider

        val firFunction = state.getOrBuildFirFor(ktFunction) as FirSimpleFunction
        val copyFunctionWithUnresolvedBody = buildFunctionCopyForCompletion(firIdeProvider, firFunction, state)

        val contextCollector = FirTowerDataContextCollector()
        state.lazyResolveDeclarationForCompletion(copyFunctionWithUnresolvedBody, firFile, firIdeProvider, contextCollector)

        return FirCompletionContext(
            copyFunctionWithUnresolvedBody.session,
            contextCollector
        )
    }

    fun buildCompletionContextForProperty(
        firFile: FirFile,
        ktProperty: KtProperty,
        state: FirModuleResolveState,
    ): FirCompletionContext {
        val firIdeProvider = firFile.session.firIdeProvider

        val firProperty = state.getOrBuildFirFor(ktProperty) as FirProperty
        val copiedPropertyWithUnresolvedBody = buildPropertyCopyForCompletion(firIdeProvider, firProperty, state)

        val contextCollector = FirTowerDataContextCollector()
        state.lazyResolveDeclarationForCompletion(copiedPropertyWithUnresolvedBody, firFile, firIdeProvider, contextCollector)

        return FirCompletionContext(
            copiedPropertyWithUnresolvedBody.session,
            contextCollector
        )
    }

    private fun buildFunctionCopyForCompletion(
        firIdeProvider: FirIdeProvider,
        firFunction: FirSimpleFunction,
        state: FirModuleResolveState
    ): FirSimpleFunction {
        val builtFunction = firIdeProvider.buildFunctionWithBody(firFunction.ktDeclaration as KtNamedFunction)

        // right now we can't resolve builtFunction header properly, as it built right in air,
        // without file, which is now required for running stages other then body resolve, so we
        // take original function header (which is resolved) and copy replacing body with body from builtFunction
        return buildSimpleFunctionCopy(firFunction) {
            body = builtFunction.body
            symbol = builtFunction.symbol as FirNamedFunctionSymbol
            resolvePhase = minOf(firFunction.resolvePhase, FirResolvePhase.DECLARATIONS)
            source = builtFunction.source
            session = state.rootModuleSession
        }
    }


    private fun buildPropertyCopyForCompletion(
        firIdeProvider: FirIdeProvider,
        furProperty: FirProperty,
        state: FirModuleResolveState
    ): FirProperty {
        val builtProperty = firIdeProvider.buildPropertyWithBody(furProperty.ktDeclaration as KtProperty)

        val originalSetter = furProperty.setter
        val builtSetter = builtProperty.setter

        // setter has a header with `value` parameter, and we want it type to be resolved
        val copySetter = if (originalSetter != null && builtSetter != null) {
            buildPropertyAccessorCopy(originalSetter) {
                body = builtSetter.body
                symbol = builtSetter.symbol
                resolvePhase = minOf(builtSetter.resolvePhase, FirResolvePhase.DECLARATIONS)
                source = builtSetter.source
                session = state.rootModuleSession
            }
        } else {
            builtSetter
        }

        return buildPropertyCopy(furProperty) {
            symbol = builtProperty.symbol
            initializer = builtProperty.initializer

            getter = builtProperty.getter
            setter = copySetter

            resolvePhase = minOf(furProperty.resolvePhase, FirResolvePhase.DECLARATIONS)
            source = builtProperty.source
            session = state.rootModuleSession
        }
    }
}
