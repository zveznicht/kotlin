/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.caches

interface FirValueWithPostCompute<VALUE> {
    fun getValue(): VALUE
}

class FirThreadUnsafeLazyValueWithPostCompute<VALUE>(
    createValue: () -> VALUE,
    postCompute: (VALUE) -> Unit,
): FirValueWithPostCompute<VALUE> {
    private var value: Any? = NonComputedLazyValueMarker

    private var _createValue: (() -> VALUE)? = createValue
    private var _postCompute: ((VALUE) -> Unit)? = postCompute


    override fun getValue(): VALUE {
        if (value == NonComputedLazyValueMarker) {
            val computedValue = _createValue!!()
            value = computedValue
            _postCompute!!(computedValue)
            _createValue = null
            _postCompute = null
        }
        @Suppress("UNCHECKED_CAST")
        return value as VALUE
    }
}