/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.scopes

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.FirSessionComponent
import org.jetbrains.kotlin.fir.declarations.FirClass
import org.jetbrains.kotlin.fir.declarations.addDeclaration
import org.jetbrains.kotlin.fir.declarations.impl.FirGeneratedClass
import org.jetbrains.kotlin.fir.declarations.validate
import org.jetbrains.kotlin.fir.extensions.FirClassGenerationExtension
import org.jetbrains.kotlin.fir.extensions.classGenerationExtensions
import org.jetbrains.kotlin.fir.extensions.extensionService
import org.jetbrains.kotlin.fir.resolve.ScopeSession
import org.jetbrains.kotlin.fir.scopes.impl.FirGeneratedClassLazyDeclaredMemberScope
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
import org.jetbrains.kotlin.name.Name

class FirGeneratedScopeProvider(val session: FirSession) : BaseKotlinScopeProvider(), FirSessionComponent {
    override fun createDeclaredMemberScope(klass: FirClass<*>): FirScope {
        require(klass is FirGeneratedClass)
        return FirGeneratedClassLazyDeclaredMemberScope(klass)
    }

    private class Index {
        val processedNames: MutableSet<Name> = mutableSetOf()
        val callableIndex: MutableMap<Name, List<FirCallableSymbol<*>>> = mutableMapOf()
        val constructors: MutableList<FirConstructorSymbol> = mutableListOf()
        var constructorsProcessed = false
    }

    private val indexesForClasses = mutableMapOf<FirRegularClassSymbol, Index>()

    internal fun getSymbolsByName(extension: FirClassGenerationExtension, klass: FirGeneratedClass, name: Name): List<FirCallableSymbol<*>> {
        val index = indexesForClasses.computeIfAbsent(klass.symbol) { Index() }

        if (name in index.processedNames) {
            return index.callableIndex[name] ?: emptyList()
        }
        val members = extension.generateMembersForClass(klass, name)
        for (member in members) {
            member.validate(name)
            klass.addDeclaration(member)
        }

        return members.map { it.symbol }.also {
            index.callableIndex[name] = it
        }
    }

    internal fun getConstructors(extension: FirClassGenerationExtension, klass: FirGeneratedClass): List<FirConstructorSymbol> {
        val index = indexesForClasses.computeIfAbsent(klass.symbol) { Index() }
        if (index.constructorsProcessed) {
            return index.constructors
        }
        val constructors = extension.generateConstructorsForClass(klass)
        for (constructor in constructors) {
            constructor.validate()
            klass.addDeclaration(constructor)
        }
        return constructors.mapTo(index.constructors) { it.symbol }
    }
}

val FirSession.generatedScopeProvider: FirGeneratedScopeProvider by FirSession.sessionComponentAccessor()
