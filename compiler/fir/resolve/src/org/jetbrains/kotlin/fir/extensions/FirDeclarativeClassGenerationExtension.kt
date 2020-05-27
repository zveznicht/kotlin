/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.extensions

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.impl.FirGeneratedClass

abstract class FirDeclarativeClassGenerationExtension {
    abstract fun generateAllClasses()
}

class FirDeclarativeClassGenerationExtensionImpl(val session: FirSession) : FirDeclarativeClassGenerationExtension() {
    override fun generateAllClasses() {
        val classes = getAllClasses()
        registerGeneratedClasses(classes)
    }

    private fun getAllClasses(): List<FirGeneratedClass> = TODO()
    private fun registerGeneratedClasses(classes: List<FirGeneratedClass>) {

    }
}