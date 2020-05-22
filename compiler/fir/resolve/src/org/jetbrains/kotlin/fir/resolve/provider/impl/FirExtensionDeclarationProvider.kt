/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.resolve.provider.impl

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration
import org.jetbrains.kotlin.fir.declarations.FirFile
import org.jetbrains.kotlin.fir.extensions.classGenerationExtensions
import org.jetbrains.kotlin.fir.extensions.extensionService
import org.jetbrains.kotlin.fir.resolve.provider.FirProvider
import org.jetbrains.kotlin.fir.scopes.FirScope
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

class FirExtensionDeclarationProvider(private val session: FirSession) : FirProvider() {
    private val extensions = session.extensionService.classGenerationExtensions

    override fun getFirClassifierByFqName(classId: ClassId): FirClassLikeDeclaration<*>? {
        if (extensions.isEmpty()) return null
        TODO()
    }

    override fun getClassLikeSymbolByFqName(classId: ClassId): FirClassLikeSymbol<*>? {
        if (extensions.isEmpty()) return null
        TODO()
    }

    override fun getTopLevelCallableSymbols(packageFqName: FqName, name: Name): List<FirCallableSymbol<*>> {
        if (extensions.isEmpty()) return emptyList()
        TODO()
    }

    override fun getFirClassifierContainerFileIfAny(fqName: ClassId): FirFile? {
        if (extensions.isEmpty()) return null
        TODO()
    }

    override fun getFirCallableContainerFile(symbol: FirCallableSymbol<*>): FirFile? {
        if (extensions.isEmpty()) return null
        TODO()
    }

    override fun getFirFilesByPackage(fqName: FqName): List<FirFile> {
        if (extensions.isEmpty()) return emptyList()
        TODO()
    }

    override fun getNestedClassifierScope(classId: ClassId): FirScope? {
        if (extensions.isEmpty()) return null
        TODO()
    }
}