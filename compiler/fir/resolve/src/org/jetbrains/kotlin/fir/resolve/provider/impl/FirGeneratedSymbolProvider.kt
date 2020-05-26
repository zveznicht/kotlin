/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.resolve.provider.impl

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.FirSessionComponent
import org.jetbrains.kotlin.fir.extensions.FirClassGenerationExtension
import org.jetbrains.kotlin.fir.extensions.classGenerationExtensions
import org.jetbrains.kotlin.fir.extensions.extensionService
import org.jetbrains.kotlin.fir.resolve.provider.FirSymbolProvider
import org.jetbrains.kotlin.fir.scopes.FirScope
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

class FirGeneratedSymbolProvider(val session: FirSession) : FirSymbolProvider(), FirSessionComponent {
    private val extensions: List<FirClassGenerationExtension> by lazy {
        session.extensionService.classGenerationExtensions
    }

    override fun getClassLikeSymbolByFqName(classId: ClassId): FirClassLikeSymbol<*>? {
        TODO("Not yet implemented")
    }

    override fun getTopLevelCallableSymbols(packageFqName: FqName, name: Name): List<FirCallableSymbol<*>> {
        TODO("Not yet implemented")
    }

    override fun getNestedClassifierScope(classId: ClassId): FirScope? {
        TODO("Not yet implemented")
    }

    override fun getPackage(fqName: FqName): FqName? {
        TODO("Not yet implemented")
    }
}

val FirSession.generatedSymbolProvider: FirGeneratedSymbolProvider by FirSession.sessionComponentAccessor()