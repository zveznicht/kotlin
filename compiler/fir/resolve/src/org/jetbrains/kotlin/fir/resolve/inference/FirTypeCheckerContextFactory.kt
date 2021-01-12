/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.resolve.inference

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.FirSessionComponent
import org.jetbrains.kotlin.fir.types.ConeTypeCheckerContext

class FirTypeCheckerContextFactory(session: FirSession) : FirSessionComponent {
    private val errorStub = ConeTypeCheckerContext(isErrorTypeEqualsToAnything = true, isStubTypeEqualsToAnything = true, session)
    private val errorNoStub = ConeTypeCheckerContext(isErrorTypeEqualsToAnything = true, isStubTypeEqualsToAnything = false, session)
    private val noErrorStub = ConeTypeCheckerContext(isErrorTypeEqualsToAnything = false, isStubTypeEqualsToAnything = true, session)
    private val noErrorNoStub = ConeTypeCheckerContext(isErrorTypeEqualsToAnything = false, isStubTypeEqualsToAnything = false, session)

    fun createConeTypeCheckerContext(
        isErrorTypeEqualsToAnything: Boolean,
        isStubTypeEqualsToAnything: Boolean
    ): ConeTypeCheckerContext = when {
        isErrorTypeEqualsToAnything && isStubTypeEqualsToAnything -> errorStub
        isErrorTypeEqualsToAnything && !isStubTypeEqualsToAnything -> errorNoStub
        !isErrorTypeEqualsToAnything && isStubTypeEqualsToAnything -> noErrorStub
        !isErrorTypeEqualsToAnything && !isStubTypeEqualsToAnything -> noErrorNoStub
        else -> error("Impossible state: isErrorTypeEqualsToAnything=$isErrorTypeEqualsToAnything, isStubTypeEqualsToAnything=$isStubTypeEqualsToAnything")
    }
}

val FirSession.typeCheckerContextFactory: FirTypeCheckerContextFactory by FirSession.sessionComponentAccessor()
