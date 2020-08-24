/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.scopes.impl

import org.jetbrains.kotlin.fir.scopes.FirScope

/**
 * [FirScope] which uses [delegate] as a base scope
 * Which means that if [FirDelegatingScope] can process some name then [delegate] can process it too
 */
interface FirDelegatingScope {
    val delegate: FirScope
}