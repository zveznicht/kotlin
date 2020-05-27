/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.scopes.impl

import org.jetbrains.kotlin.fir.declarations.impl.FirGeneratedClass
import org.jetbrains.kotlin.fir.extensions.FirClassGenerationExtension
import org.jetbrains.kotlin.fir.extensions.classGenerationExtensions
import org.jetbrains.kotlin.fir.extensions.extensionService
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
import org.jetbrains.kotlin.fir.scopes.FirScope
import org.jetbrains.kotlin.fir.symbols.impl.*
import org.jetbrains.kotlin.name.Name

class FirGeneratedClassLazyDeclaredMemberScope(
    private val klass: FirGeneratedClass
) : FirScope() {
    private val extension: FirClassGenerationExtension =
        klass.session.extensionService.classGenerationExtensions.first { it.key == klass.origin.key }

    override fun processClassifiersByNameWithSubstitution(name: Name, processor: (FirClassifierSymbol<*>, ConeSubstitutor) -> Unit) {
        // TODO
        super.processClassifiersByNameWithSubstitution(name, processor)
    }

    override fun processFunctionsByName(name: Name, processor: (FirFunctionSymbol<*>) -> Unit) {
        for (symbol in klass.scopeProvider.getSymbolsByName(extension, klass, name)) {
            if (symbol is FirFunctionSymbol<*>) {
                processor(symbol)
            }
        }
    }

    override fun processPropertiesByName(name: Name, processor: (FirVariableSymbol<*>) -> Unit) {
        for (symbol in klass.scopeProvider.getSymbolsByName(extension, klass, name)) {
            if (symbol is FirPropertySymbol) {
                processor(symbol)
            }
        }
    }

    override fun processDeclaredConstructors(processor: (FirConstructorSymbol) -> Unit) {
        for (symbol in klass.scopeProvider.getConstructors(extension, klass)) {
            processor(symbol)
        }
    }

    override fun mayContainName(name: Name): Boolean {
        // TODO
        return super.mayContainName(name)
    }
}