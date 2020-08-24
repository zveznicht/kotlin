/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.frontend.api.fir.scopes

import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
import org.jetbrains.kotlin.fir.scopes.impl.FirClassDeclaredMemberScope
import org.jetbrains.kotlin.idea.frontend.api.ValidityToken
import org.jetbrains.kotlin.idea.frontend.api.ValidityTokenOwner
import org.jetbrains.kotlin.idea.frontend.api.fir.KtSymbolByFirBuilder
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.KtFirClassOrObjectSymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.utils.weakRef
import org.jetbrains.kotlin.idea.frontend.api.scopes.KtDeclaredMemberScope
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtCallableSymbol
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtClassLikeSymbol
import org.jetbrains.kotlin.idea.frontend.api.withValidityAssertion

internal class KtFirDeclaredMemberScope(
    override val owner: KtFirClassOrObjectSymbol,
    firScope: FirClassDeclaredMemberScope,
    builder: KtSymbolByFirBuilder,
    override val token: ValidityToken,
) : KtDeclaredMemberScope() {
    private val firScope by weakRef(firScope)
    private val builder by weakRef(builder)

    override fun getCallableSymbols(): Sequence<KtCallableSymbol> = withValidityAssertion {
        firScope.getCallableSymbols(firScope.getCallableNames(), builder)
    }

    override fun getClassClassLikeSymbols(): Sequence<KtClassLikeSymbol> = withValidityAssertion {
        firScope.getClassLikeSymbols(firScope.getCallableNames(), builder)
    }
}

