/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.scopes.impl

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport
import org.jetbrains.kotlin.fir.declarations.expandedConeType
import org.jetbrains.kotlin.fir.resolve.ScopeSession
import org.jetbrains.kotlin.fir.resolve.toSymbol
import org.jetbrains.kotlin.fir.resolve.transformers.ensureResolvedForCalls
import org.jetbrains.kotlin.fir.scopes.FirScope
import org.jetbrains.kotlin.fir.scopes.unsubstitutedScope
import org.jetbrains.kotlin.fir.symbols.CallableId
import org.jetbrains.kotlin.fir.symbols.impl.*
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.Name

abstract class FirAbstractImportingScope(
    session: FirSession,
    protected val scopeSession: ScopeSession,
    lookupInFir: Boolean
) : FirAbstractProviderBasedScope(session, lookupInFir) {

    private fun getStaticsScope(symbol: FirClassLikeSymbol<*>): FirScope? {
        if (symbol is FirTypeAliasSymbol) {
            val expansionSymbol = symbol.fir.expandedConeType?.lookupTag?.toSymbol(session)
            if (expansionSymbol != null) {
                return getStaticsScope(expansionSymbol)
            }
        } else {
            val firClass = (symbol as FirClassSymbol<*>).fir

            return if (firClass.classKind == ClassKind.OBJECT) {
                FirObjectImportedCallableScope(
                    symbol.classId,
                    firClass.unsubstitutedScope(session, scopeSession, withForcedTypeCalculator = false)
                )
            } else {
                firClass.scopeProvider.getStaticScope(firClass, session, scopeSession)
            }
        }

        return null

    }

    fun getStaticsScope(classId: ClassId): FirScope? {
        val symbol = provider.getClassLikeSymbolByFqName(classId) ?: return null
        return getStaticsScope(symbol)
    }

    protected fun processFunctionsByNameWithImport(
        import: FirResolvedImport,
        name: Name,
        processor: (FirNamedFunctionSymbol) -> Unit
    ) {
        val callableId = CallableId(import.packageFqName, import.relativeClassName, name)
        val classId = import.resolvedClassId
        if (classId != null) {
            getStaticsScope(classId)?.processFunctionsByName(callableId.callableName, processor)
        } else if (name.isSpecial || name.identifier.isNotEmpty()) {
            val symbols = provider.getTopLevelFunctionSymbols(callableId.packageName, callableId.callableName)
            for (symbol in symbols) {
                symbol.ensureResolvedForCalls(session)
                processor(symbol)
            }
        }
    }

    protected fun processPropertiesByNameWithImport(
        import: FirResolvedImport,
        name: Name,
        processor: (FirVariableSymbol<*>) -> Unit
    ) {
        val callableId = CallableId(import.packageFqName, import.relativeClassName, name)
        val classId = import.resolvedClassId
        if (classId != null) {
            getStaticsScope(classId)?.processPropertiesByName(callableId.callableName, processor)
        } else if (name.isSpecial || name.identifier.isNotEmpty()) {
            val symbols = provider.getTopLevelPropertySymbols(callableId.packageName, callableId.callableName)
            for (symbol in symbols) {
                symbol.ensureResolvedForCalls(session)
                processor(symbol)
            }
        }
    }
}
