/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.low.level.api

import org.jetbrains.kotlin.fir.declarations.FirDeclaration
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase
import org.jetbrains.kotlin.fir.render
import org.jetbrains.kotlin.fir.resolve.firProvider
import org.jetbrains.kotlin.fir.resolve.transformers.FirPhaseManager
import org.jetbrains.kotlin.fir.symbols.AbstractFirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.idea.fir.low.level.api.file.builder.FirFileBuilder
import org.jetbrains.kotlin.idea.fir.low.level.api.file.builder.ModuleFileCache

internal class IdeFirPhaseManager(
    private val firFileBuilder: FirFileBuilder,
    private val cache: ModuleFileCache
) : FirPhaseManager() {
    override fun ensureResolved(
        symbol: AbstractFirBasedSymbol<*>,
        requiredPhase: FirResolvePhase
    ) {
        val result = symbol.fir as FirDeclaration
        val availablePhase = result.resolvePhase
        if (availablePhase >= requiredPhase) return
        // NB: we should use session from symbol here, not transformer session (important for IDE)
        val provider = result.session.firProvider

        require(provider.isPhasedFirAllowed) {
            "Incorrect resolvePhase: actual: $availablePhase, expected: $requiredPhase\n For: ${symbol.fir.render()}"
        }

        val containingFile = when (symbol) {
            is FirCallableSymbol<*> -> provider.getFirCallableContainerFile(symbol)
            is FirClassLikeSymbol<*> -> provider.getFirClassifierContainerFile(symbol)
            else -> null
        } ?: throw AssertionError("Cannot get container file by symbol: $symbol (${result.render()})")

        firFileBuilder.runResolve(containingFile, cache, availablePhase, requiredPhase)
    }
}
