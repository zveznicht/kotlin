/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.frontend.api.scopes

import org.jetbrains.kotlin.idea.frontend.api.ValidityToken
import org.jetbrains.kotlin.idea.frontend.api.ValidityTokenOwner
import org.jetbrains.kotlin.idea.frontend.api.symbols.*
import org.jetbrains.kotlin.idea.frontend.api.withValidityAssertion
import org.jetbrains.kotlin.name.FqName

abstract class KtScope : ValidityTokenOwner {
    open fun getAllSymbols(): Sequence<KtSymbol> = withValidityAssertion {
        sequence {
            yieldAll(getCallableSymbols())
            yieldAll(getClassClassLikeSymbols())
        }
    }

    abstract fun getCallableSymbols(): Sequence<KtCallableSymbol>
    abstract fun getClassClassLikeSymbols(): Sequence<KtClassLikeSymbol>
}

abstract class KtCompositeScope : KtScope() {
    abstract val subScopes: List<KtScope>
}

abstract class KtMemberScope : KtScope() {
    abstract val owner: KtClassOrObjectSymbol
}

abstract class KtDeclaredMemberScope : KtScope() {
    abstract val owner: KtClassOrObjectSymbol
}

abstract class KtPackageScope : KtScope() {
    abstract val fqName: FqName
}