/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.caches


interface FirCacheWithPostCompute<KEY : Any, VALUE, CONTEXT> {
    fun getValue(key: KEY, context: CONTEXT): VALUE
}

internal class FirThreadUnsafeCacheWithPostCompute<KEY : Any, VALUE, CONTEXT>(
    private val createValue: (KEY, CONTEXT) -> VALUE,
    private val postCompute: (KEY, VALUE) -> Unit
) : FirCacheWithPostCompute<KEY, VALUE, CONTEXT> {
    private val map = mutableMapOf<KEY, Any?>()

    @Suppress("UNCHECKED_CAST")
    override fun getValue(key: KEY, context: CONTEXT): VALUE = when (val value = map[key]) {
        null -> {
            val createdValue = createValue(key, context)
            map[key] = createdValue ?: NullValue
            postCompute(key, createdValue)
            createdValue
        }
        NullValue -> null
        else -> value
    } as VALUE
}