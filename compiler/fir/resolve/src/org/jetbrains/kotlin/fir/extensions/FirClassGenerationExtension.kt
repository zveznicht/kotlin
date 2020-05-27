/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.extensions

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
import org.jetbrains.kotlin.fir.declarations.FirConstructor
import org.jetbrains.kotlin.fir.declarations.FirDeclaration
import org.jetbrains.kotlin.fir.declarations.impl.FirGeneratedClass
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.Name
import kotlin.reflect.KClass

abstract class FirClassGenerationExtension(session: FirSession) : FirPredicateBasedExtension(session) {
    companion object {
        val NAME = FirExtensionPointName("StatusTransformer")
    }

    final override val name: FirExtensionPointName
        get() = NAME

    final override val extensionType: KClass<out FirExtension> = FirClassGenerationExtension::class

    abstract fun generateClass(classId: ClassId): FirGeneratedClass?
    abstract fun generateMembersForClass(klass: FirGeneratedClass, name: Name): List<FirCallableDeclaration<*>>
    abstract fun generateConstructorsForClass(klass: FirGeneratedClass): List<FirConstructor>

    fun interface Factory : FirExtension.Factory<FirClassGenerationExtension>
}

val FirExtensionService.classGenerationExtensions: List<FirClassGenerationExtension> by FirExtensionService.registeredExtensions()
