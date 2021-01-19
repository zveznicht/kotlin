/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.caches

interface FirCache<KEY : Any, VALUE> {
    fun getValue(key: KEY): VALUE
}

internal class FirThreadUnsafeCache<KEY : Any, VALUE>(private val createValue: (KEY) -> VALUE) : FirCache<KEY, VALUE> {
    private val map = HashMap<KEY, Any>()

    @Suppress("UNCHECKED_CAST")
    override fun getValue(key: KEY): VALUE {
        return when (val value = map[key]) {
            null -> {
                val calculated = createValue(key)
                map[key] = calculated ?: NullValue
                calculated
            }
            NullValue -> null
            else -> value
        } as VALUE
    }
}