/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.scopes

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.FirSessionComponent
import org.jetbrains.kotlin.fir.declarations.FirClass
import org.jetbrains.kotlin.fir.extensions.FirClassGenerationExtension
import org.jetbrains.kotlin.fir.extensions.classGenerationExtensions
import org.jetbrains.kotlin.fir.extensions.extensionService
import org.jetbrains.kotlin.fir.resolve.ScopeSession
import org.jetbrains.kotlin.fir.resolve.provider.impl.FirGeneratedSymbolProvider

class FirGeneratedScopeProvider(val session: FirSession) : FirScopeProvider(), FirSessionComponent {
    private val extensions: List<FirClassGenerationExtension> by lazy {
        session.extensionService.classGenerationExtensions
    }

    override fun getUseSiteMemberScope(klass: FirClass<*>, useSiteSession: FirSession, scopeSession: ScopeSession): FirScope {
        TODO("Not yet implemented")
    }

    override fun getStaticMemberScopeForCallables(klass: FirClass<*>, useSiteSession: FirSession, scopeSession: ScopeSession): FirScope? {
        TODO("Not yet implemented")
    }

    override fun getNestedClassifierScope(klass: FirClass<*>, useSiteSession: FirSession, scopeSession: ScopeSession): FirScope? {
        TODO("Not yet implemented")
    }
}

val FirSession.generatedScopeProvider: FirGeneratedScopeProvider by FirSession.sessionComponentAccessor()