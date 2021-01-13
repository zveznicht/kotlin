/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.caches

interface FirValueWithPostCompute<VALUE> {
    fun getValue(): VALUE
}

class FirThreadUnsafeValueWithPostCompute<VALUE>(calculate: () -> VALUE, postCompute: (VALUE) -> Unit): FirValueWithPostCompute<VALUE> {
    private val value = calculate()

    init {
        postCompute(value)
    }

    override fun getValue(): VALUE = value
}