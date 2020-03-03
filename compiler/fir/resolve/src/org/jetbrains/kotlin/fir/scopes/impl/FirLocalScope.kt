/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.scopes.impl

import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentMapOf
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.NAME_FOR_BACKING_FIELD
import org.jetbrains.kotlin.fir.resolve.PersistentMultimap
import org.jetbrains.kotlin.fir.scopes.FirScope
import org.jetbrains.kotlin.fir.symbols.impl.*
import org.jetbrains.kotlin.name.Name

class FirLocalScope(
    properties: PersistentMap<Name, FirVariableSymbol<*>>,
    functions: PersistentMultimap<Name, FirFunctionSymbol<*>>,
    classes: PersistentMap<Name, FirRegularClassSymbol>
) : FirScope() {
    var properties: PersistentMap<Name, FirVariableSymbol<*>> = properties
        private set
    var functions: PersistentMultimap<Name, FirFunctionSymbol<*>> = functions
        private set
    var classes: PersistentMap<Name, FirRegularClassSymbol> = classes
        private set

    constructor() : this(persistentMapOf(), PersistentMultimap(), persistentMapOf())

    fun storeClass(klass: FirRegularClass) {
        classes = classes.put(klass.name, klass.symbol)
    }

    fun storeFunction(function: FirSimpleFunction) {
        functions = functions.put(function.name, function.symbol as FirNamedFunctionSymbol)
    }

    fun storeVariable(variable: FirVariable<*>) {
        properties = properties.put(variable.name, variable.symbol)
    }

    fun storeBackingField(property: FirProperty) {
        properties = properties.put(NAME_FOR_BACKING_FIELD, property.backingFieldSymbol)
    }

    override fun processFunctionsByName(name: Name, processor: (FirFunctionSymbol<*>) -> Unit) {
        for (function in functions[name]) {
            processor(function)
        }
    }

    override fun processPropertiesByName(name: Name, processor: (FirVariableSymbol<*>) -> Unit) {
        val property = properties[name]
        if (property != null) {
            processor(property)
        }
    }

    override fun processClassifiersByName(name: Name, processor: (FirClassifierSymbol<*>) -> Unit) {
        val klass = classes[name]
        if (klass != null) {
            processor(klass)
        }
    }

    fun snapshot(): FirLocalScope {
        return FirLocalScope(properties, functions, classes)
    }
}