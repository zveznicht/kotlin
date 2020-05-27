/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.plugin

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.fir.FirEffectiveVisibilityImpl
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.declarations.builder.buildGeneratedClass
import org.jetbrains.kotlin.fir.declarations.builder.buildSimpleFunction
import org.jetbrains.kotlin.fir.declarations.impl.FirGeneratedClass
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl
import org.jetbrains.kotlin.fir.extensions.FirClassGenerationExtension
import org.jetbrains.kotlin.fir.extensions.predicate.DeclarationPredicate
import org.jetbrains.kotlin.fir.extensions.predicate.has
import org.jetbrains.kotlin.fir.extensions.predicateBasedProvider
import org.jetbrains.kotlin.fir.symbols.CallableId
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

class AllOpenClassGenerator(session: FirSession) : FirClassGenerationExtension(session) {
    private val annotatedClasses: Map<ClassId, FirRegularClass>
        get() {
            return session.predicateBasedProvider.getSymbolsByPredicate(predicate).mapNotNull {
                val klass = it as? FirRegularClass ?: return@mapNotNull null
                klass.symbol.classId to klass
            }.toMap()
        }

    private val nameToGenerate = Name.identifier("foo")

    override fun generateClass(classId: ClassId): FirGeneratedClass? {
        val name = classId.shortClassName
        if (name.isSpecial) return null
        val baseClassId = ClassId(classId.packageFqName, Name.identifier(name.identifier.removeSuffix("Gen")))
        // TODO: replace with getClassByClassId
        if (baseClassId !in annotatedClasses) return null
        return buildGeneratedClass {
            session = this@AllOpenClassGenerator.session
            pluginKey = key
            status = FirResolvedDeclarationStatusImpl(
                Visibilities.PUBLIC,
                FirEffectiveVisibilityImpl.Public,
                Modality.ABSTRACT
            )
            classKind = ClassKind.INTERFACE
            this.name = name
            symbol = FirRegularClassSymbol(classId)
        }
    }

    override fun generateMembersForClass(klass: FirGeneratedClass, name: Name): List<FirCallableDeclaration<*>> {
        if (name != nameToGenerate) return emptyList()
        val function = buildSimpleFunction {
            session = klass.session
            resolvePhase = FirResolvePhase.ANALYZED_DEPENDENCIES
            origin = FirDeclarationOrigin.Plugin(key)
            returnTypeRef = session.builtinTypes.stringType
            status = FirResolvedDeclarationStatusImpl(
                Visibilities.PUBLIC,
                FirEffectiveVisibilityImpl.Public,
                Modality.ABSTRACT
            )
            this.name = nameToGenerate
            symbol = FirNamedFunctionSymbol(CallableId(klass.symbol.classId, nameToGenerate))
        }
        return listOf(function)
    }

    override fun generateConstructorsForClass(klass: FirGeneratedClass): List<FirConstructor> {
        return emptyList()
    }

    override val predicate: DeclarationPredicate = has(FqName("org.jetbrains.kotlin.fir.plugin.WithClass"))

    override val key: FirPluginKey
        get() = AllOpenPluginKey
}