/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.frontend.api.fir.utils

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.types.ConeTypeCheckerContext
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.types.coneTypeUnsafe
import org.jetbrains.kotlin.idea.frontend.api.ValidityOwner
import org.jetbrains.kotlin.idea.frontend.api.KtType
import org.jetbrains.kotlin.idea.frontend.api.fir.FirKtType

internal fun FirTypeRef.asTypeInfo(session: FirSession, validityOwner: ValidityOwner): KtType {
    val context = ConeTypeCheckerContext(
        isErrorTypeEqualsToAnything = true,
        isStubTypeEqualsToAnything = true,
        session = session
    )
    return FirKtType(coneTypeUnsafe(), context, validityOwner)
}