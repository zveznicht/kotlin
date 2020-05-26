/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.resolve.provider.impl

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.FirSessionComponent
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.declarations.builder.buildFile
import org.jetbrains.kotlin.fir.extensions.FirClassGenerationExtension
import org.jetbrains.kotlin.fir.extensions.classGenerationExtensions
import org.jetbrains.kotlin.fir.extensions.extensionService
import org.jetbrains.kotlin.fir.resolve.provider.FirProvider
import org.jetbrains.kotlin.fir.resolve.provider.FirSymbolProvider
import org.jetbrains.kotlin.fir.scopes.FirScope
import org.jetbrains.kotlin.fir.symbols.CallableId
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

class FirGeneratedSymbolProvider(val session: FirSession) : FirProvider(), FirSessionComponent {
    private val extensions: List<FirClassGenerationExtension> by lazy {
        session.extensionService.classGenerationExtensions
    }

    private val state = State()

    override fun getClassLikeSymbolByFqName(classId: ClassId): FirClassLikeSymbol<*>? {
        return getFirClassifierByFqName(classId)?.symbol
    }

    override fun getTopLevelCallableSymbols(packageFqName: FqName, name: Name): List<FirCallableSymbol<*>> {
        // TODO("Not yet implemented")
        return emptyList()
    }

    override fun getNestedClassifierScope(classId: ClassId): FirScope? {
        // TODO("Not yet implemented")
        return null
    }

    override fun getAllCallableNamesInPackage(fqName: FqName): Set<Name> {
        return super.getAllCallableNamesInPackage(fqName)
    }

    override fun getClassNamesInPackage(fqName: FqName): Set<Name> {
        return super.getClassNamesInPackage(fqName)
    }

    override fun getAllCallableNamesInClass(classId: ClassId): Set<Name> {
        return super.getAllCallableNamesInClass(classId)
    }

    override fun getNestedClassesNamesInClass(classId: ClassId): Set<Name> {
        return super.getNestedClassesNamesInClass(classId)
    }

    // fir provider

    /*
     * TODO: what if two extensions want to generate class with same classId?
     */
    override fun getFirClassifierByFqName(classId: ClassId): FirClassLikeDeclaration<*>? {
        val wasAdded = state.processedClassIdMap.add(classId)
        if (!wasAdded) return state.classifierMap[classId]
        for (extension in extensions) {
            val generatedClass = extension.generateClass(classId) ?: continue
            val packageFqName = classId.packageFqName
            val file = getFile(extension.key, packageFqName)
            file.addDeclaration(generatedClass)
            state.classesInPackage.computeIfAbsent(packageFqName) { mutableSetOf() } += generatedClass.name
            state.classifierContainerFileMap[classId] = file
            return generatedClass
        }
        return null
    }

    override fun getFirClassifierContainerFileIfAny(fqName: ClassId): FirFile? {
        return state.classifierContainerFileMap[fqName]
    }

    override fun getFirCallableContainerFile(symbol: FirCallableSymbol<*>): FirFile? {
        return state.callableContainerMap[symbol]
    }

    override fun getFirFilesByPackage(fqName: FqName): List<FirFile> {
        return state.filesMap[fqName] ?: emptyList()
    }

    // unique

    fun getFile(pluginKey: FirPluginKey, fqName: FqName): FirFile {
        return state.filesByPluginMap.computeIfAbsent(fqName) { mutableMapOf() }
            .computeIfAbsent(pluginKey) {
                buildFile {
                    session = this@FirGeneratedSymbolProvider.session
                    resolvePhase = FirResolvePhase.ANALYZED_DEPENDENCIES
                    origin = FirDeclarationOrigin.Plugin(pluginKey)
                    // TODO: add proper name generation
                    name = "${pluginKey}.Generated.kt"
                    packageFqName = fqName
                }.also {
                    state.filesMap.computeIfAbsent(fqName) { mutableListOf() } += it
                }
            }
    }

    fun getAllGeneratedFiles(): List<FirFile> {
        return state.filesMap.values.flatten()
    }

    private class State {
        val filesByPluginMap: MutableMap<FqName, MutableMap<FirPluginKey, FirFile>> = mutableMapOf()
        val filesMap: MutableMap<FqName, MutableList<FirFile>> = mutableMapOf()
        val classifierMap: MutableMap<ClassId, FirClassLikeDeclaration<*>> = mutableMapOf()
        val classifierContainerFileMap: MutableMap<ClassId, FirFile> = mutableMapOf()
        val classesInPackage: MutableMap<FqName, MutableSet<Name>> = mutableMapOf()
        val callableMap: MutableMap<CallableId, List<FirCallableSymbol<*>>> = mutableMapOf()
        val callableContainerMap: MutableMap<FirCallableSymbol<*>, FirFile> = mutableMapOf()

        // which CallableId and ClassId was already processed by plugins
        val processedCallableIdMap: MutableSet<CallableId> = mutableSetOf()
        val processedClassIdMap: MutableSet<ClassId> = mutableSetOf()
    }
}

val FirSession.generatedSymbolProvider: FirGeneratedSymbolProvider by FirSession.sessionComponentAccessor()