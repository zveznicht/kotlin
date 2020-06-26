/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.frontend.api.fir

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.declarations.impl.FirValueParameterImpl
import org.jetbrains.kotlin.fir.resolve.providers.FirProvider
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
import org.jetbrains.kotlin.fir.symbols.ConeClassLikeLookupTag
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol
import org.jetbrains.kotlin.fir.types.*
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl
import org.jetbrains.kotlin.idea.frontend.api.*
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
import org.jetbrains.kotlin.fir.symbols.ConeClassLikeLookupTag
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol
import org.jetbrains.kotlin.fir.types.*
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl
import org.jetbrains.kotlin.idea.frontend.api.*
import org.jetbrains.kotlin.idea.frontend.api.Invalidatable
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.*
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.KtFirClassOrObjectSymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.KtFirLocalVariableSymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.KtFirPropertySymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.KtFirFunctionSymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.KtFirFunctionValueParameterSymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.utils.weakRef
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtClassLikeSymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.FirKtClassOrObjectSymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.FirKtLocalVariableSymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.FirKtPropertySymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.FirKtFunctionSymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.symbols.FirKtFunctionValueParameterSymbol
import org.jetbrains.kotlin.idea.frontend.api.fir.utils.weakRef
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtClassLikeSymbol
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtSymbol
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtTypeParameterSymbol
import org.jetbrains.kotlin.idea.frontend.api.symbols.KtVariableSymbol

internal class KtSymbolByFirBuilder(
    firProvider: FirSymbolProvider,
    typeCheckerContext: ConeTypeCheckerContext,
    override val token: Invalidatable
) : InvalidatableByValidityToken {
    private val firProvider by weakRef(firProvider)
    private val typeCheckerContext by weakRef(typeCheckerContext)

    fun buildSymbol(fir: FirDeclaration): KtSymbol = when (fir) {
        is FirRegularClass -> buildClassSymbol(fir)
        is FirSimpleFunction -> buildFunctionSymbol(fir)
        is FirProperty -> buildVariableSymbol(fir)
        is FirValueParameterImpl -> buildParameterSymbol(fir)
        is FirConstructor -> buildConstructorSymbol(fir)
        is FirTypeParameter -> buildTypeParameterSymbol(fir)
        is FirTypeAlias -> buildTypeAliasSymbol(fir)
        is FirEnumEntry -> buildEnumEntrySymbol(fir)
        is FirField -> buildFieldSymbol(fir)
        is FirAnonymousFunction -> buildAnonymousFunctionSymbol(fir)
        else ->
            TODO(fir::class.toString())
    }

    fun buildClassLikeSymbol(fir: FirClassLikeDeclaration<*>): KtClassLikeSymbol = when (fir) {
        is FirRegularClass -> buildClassSymbol(fir)
        is FirTypeAlias -> buildTypeAliasSymbol(fir)
        else ->
            TODO(fir::class.toString())
    }

    fun buildClassSymbol(fir: FirRegularClass) = KtFirClassOrObjectSymbol(fir, token, this)

    // TODO it can be a constructor parameter, which may be split into parameter & property
    // we should handle them both
    fun buildParameterSymbol(fir: FirValueParameterImpl) = KtFirFunctionValueParameterSymbol(fir, token)
    fun buildFirConstructorParameter(fir: FirValueParameterImpl) = KtFirConstructorValueParameterSymbol(fir, token)

    fun buildFunctionSymbol(fir: FirSimpleFunction) = KtFirFunctionSymbol(fir, token, this)
    fun buildConstructorSymbol(fir: FirConstructor) = KtFirConstructorSymbol(fir, token, this)
    fun buildTypeParameterSymbol(fir: FirTypeParameter) = KtFirTypeParameterSymbol(fir, token)
    fun buildTypeAliasSymbol(fir: FirTypeAlias) = KtFirTypeAliasSymbol(fir, token)
    fun buildEnumEntrySymbol(fir: FirEnumEntry) = KtFirEnumEntrySymbol(fir, token)
    fun buildFieldSymbol(fir: FirField) = KtFirFieldSymbol(fir, token)
    fun buildAnonymousFunctionSymbol(fir: FirAnonymousFunction) = KtFirAnonymousFunctionSymbol(fir, token, this)

    fun buildVariableSymbol(fir: FirProperty): KtVariableSymbol {
        return when {
            fir.isLocal -> KtFirLocalVariableSymbol(fir, token)
            else -> KtFirPropertySymbol(fir, token)
        }
    }

    fun buildClassLikeSymbolByLookupTag(lookupTag: ConeClassLikeLookupTag): KtClassLikeSymbol? = withValidityAssertion {
        firProvider.getSymbolByLookupTag(lookupTag)?.fir?.let(::buildClassLikeSymbol)
    }

    fun buildTypeParameterSymbolByLookupTag(lookupTag: ConeTypeParameterLookupTag): KtTypeParameterSymbol? = withValidityAssertion {
        (firProvider.getSymbolByLookupTag(lookupTag) as? FirTypeParameterSymbol)?.fir?.let(::buildTypeParameterSymbol)
    }

    fun buildTypeArgument(coneType: ConeTypeProjection): KtTypeArgument = when (coneType) {
        is ConeStarProjection -> KtStarProjectionTypeArgument
        is ConeKotlinTypeProjection -> KtFirTypeArgumentWithVariance(
            buildKtType(coneType.type),
            coneType.kind.toVariance()
        )
    }

    private fun ProjectionKind.toVariance() = when (this) {
        ProjectionKind.OUT -> KtTypeArgumentVariance.COVARIANT
        ProjectionKind.IN -> KtTypeArgumentVariance.CONTRAVARIANT
        ProjectionKind.INVARIANT -> KtTypeArgumentVariance.INVARIANT
        ProjectionKind.STAR -> error("KtStarProjectionTypeArgument be directly created")
    }

    fun buildKtType(coneType: ConeKotlinType): KtType = when (coneType) {
        is ConeClassLikeTypeImpl -> KtFirClassType(coneType, typeCheckerContext, token, this)
        is ConeTypeParameterType -> KtFirTypeParameterType(coneType, typeCheckerContext, token, this)
        is ConeClassErrorType -> KtFirErrorType(coneType, typeCheckerContext, token)
        is ConeFlexibleType -> KtFirFlexibleType(coneType, typeCheckerContext, token, this)
        is ConeIntersectionType -> KtFirIntersectionType(coneType, typeCheckerContext, token, this)
        else -> TODO()
    }
}

internal fun FirElement.buildSymbol(builder: KtSymbolByFirBuilder) =
    (this as? FirDeclaration)?.let(builder::buildSymbol)

internal fun FirDeclaration.buildSymbol(builder: KtSymbolByFirBuilder) =
    builder.buildSymbol(this)