/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.resolve.provider.impl

import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration
import org.jetbrains.kotlin.fir.declarations.FirFile
import org.jetbrains.kotlin.fir.resolve.provider.FirProvider
import org.jetbrains.kotlin.fir.scopes.FirScope
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.utils.addToStdlib.firstNotNullResult

class FirCompositeProvider(val providers: List<FirProvider>) : FirProvider() {
    private val symbolProvider = FirCompositeSymbolProvider(providers)

    override fun getFirClassifierByFqName(classId: ClassId): FirClassLikeDeclaration<*>? {
        return providers.firstNotNullResult { it.getFirClassifierByFqName(classId) }
    }

    override fun getClassLikeSymbolByFqName(classId: ClassId): FirClassLikeSymbol<*>? {
        return symbolProvider.getClassLikeSymbolByFqName(classId)
    }

    override fun getTopLevelCallableSymbols(packageFqName: FqName, name: Name): List<FirCallableSymbol<*>> {
        return symbolProvider.getTopLevelCallableSymbols(packageFqName, name)
    }

    override fun getFirClassifierContainerFileIfAny(fqName: ClassId): FirFile? {
        return providers.firstNotNullResult { it.getFirClassifierContainerFile(fqName) }
    }

    override fun getFirCallableContainerFile(symbol: FirCallableSymbol<*>): FirFile? {
        return providers.firstNotNullResult { it.getFirCallableContainerFile(symbol) }
    }

    override fun getFirFilesByPackage(fqName: FqName): List<FirFile> {
        return providers.flatMap { it.getFirFilesByPackage(fqName) }
    }

    override fun getNestedClassifierScope(classId: ClassId): FirScope? {
        return symbolProvider.getNestedClassifierScope(classId)
    }

    override fun getAllCallableNamesInPackage(fqName: FqName): Set<Name> {
        return symbolProvider.getAllCallableNamesInPackage(fqName)
    }

    override fun getClassNamesInPackage(fqName: FqName): Set<Name> {
        return symbolProvider.getClassNamesInPackage(fqName)
    }

    override fun getAllCallableNamesInClass(classId: ClassId): Set<Name> {
        return symbolProvider.getAllCallableNamesInClass(classId)
    }

    override fun getNestedClassesNamesInClass(classId: ClassId): Set<Name> {
        return symbolProvider.getNestedClassesNamesInClass(classId)
    }

    override fun getPackage(fqName: FqName): FqName? {
        return symbolProvider.getPackage(fqName)
    }
}