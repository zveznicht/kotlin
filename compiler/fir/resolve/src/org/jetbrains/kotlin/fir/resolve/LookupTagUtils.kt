/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.resolve

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
import org.jetbrains.kotlin.fir.symbols.ConeClassLikeLookupTag
import org.jetbrains.kotlin.fir.symbols.ConeClassifierLookupTag
import org.jetbrains.kotlin.fir.symbols.ConeClassifierLookupTagWithFixedSymbol
import org.jetbrains.kotlin.fir.symbols.impl.*

inline fun ConeClassifierLookupTag.toSymbol(useSiteSession: FirSession): FirClassifierSymbol<*>? =
    when (this) {
        is ConeClassLikeLookupTag -> toSymbol(useSiteSession)
        is ConeClassifierLookupTagWithFixedSymbol -> this.symbol
        else -> error("Unknown lookupTag type: ${this::class}")
    }

@OptIn(LookupTagInternals::class)
inline fun ConeClassLikeLookupTag.toSymbol(useSiteSession: FirSession): FirClassLikeSymbol<*>? {
    return when (this) {
        is ConeClassLookupTagWithFixedSymbol -> {
            this.symbol
        }
        is ConeClassLikeLookupTagImpl -> {
            if (boundSymbol?.key === useSiteSession) {
                boundSymbol?.value
            } else {
                useSiteSession.firSymbolProvider.getClassLikeSymbolByFqName(classId).also {
                    bindSymbolToLookupTag(useSiteSession, it)
                }
            }
        }
        else -> {
            useSiteSession.firSymbolProvider.getClassLikeSymbolByFqName(classId)
        }
    }
}

@OptIn(LookupTagInternals::class)
inline fun ConeClassLikeLookupTagImpl.bindSymbolToLookupTag(session: FirSession, symbol: FirClassLikeSymbol<*>?) {
    boundSymbol = OneElementWeakMap(session, symbol)
}
