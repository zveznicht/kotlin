/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.extensions

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.impl.FirGeneratedClass
import org.jetbrains.kotlin.name.ClassId
import kotlin.reflect.KClass

abstract class FirClassGenerationExtension(session: FirSession) : FirPredicateBasedExtension(session) {
    companion object {
        val NAME = FirExtensionPointName("StatusTransformer")
    }

    final override val name: FirExtensionPointName
        get() = NAME

    final override val extensionType: KClass<out FirExtension> = FirClassGenerationExtension::class

    abstract fun generateClass(classId: ClassId): FirGeneratedClass?

    fun interface Factory : FirExtension.Factory<FirClassGenerationExtension>
}

val FirExtensionService.classGenerationExtensions: List<FirClassGenerationExtension> by FirExtensionService.registeredExtensions()
