/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.caches


interface FirCacheWithPostCompute<KEY : Any, VALUE, CONTEXT, DATA> {
    fun getValue(key: KEY, context: CONTEXT): VALUE
    fun getValueIfComputed(key: KEY): VALUE?
}

internal class FirThreadUnsafeCacheWithPostCompute<KEY : Any, VALUE, CONTEXT, DATA>(
    private val createValue: (KEY, CONTEXT) -> Pair<VALUE, DATA>,
    private val postCompute: (KEY, VALUE, DATA) -> Unit
) : FirCacheWithPostCompute<KEY, VALUE, CONTEXT, DATA> {
    private val map = mutableMapOf<KEY, Any?>()

    @Suppress("UNCHECKED_CAST")
    override fun getValue(key: KEY, context: CONTEXT): VALUE = when (val value = map[key]) {
        null -> {
            val (createdValue, data) = createValue(key, context)
            map[key] = createdValue ?: NullValue
            postCompute(key, createdValue, data)
            createdValue
        }
        NullValue -> null
        else -> value
    } as VALUE

    @Suppress("UNCHECKED_CAST")
    override fun getValueIfComputed(key: KEY): VALUE? {
        return when (val value = map[key]) {
            NullValue -> null
            else -> value as VALUE
        }
    }
}