/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.caches

interface FirMapLikeCache<KEY : Any, VALUE> {
    fun getOrCreateValue(key: KEY, createValue: (KEY) -> VALUE): VALUE
}

internal class FirThreadUnsafeMapLikeCache<KEY : Any, VALUE> : FirMapLikeCache<KEY, VALUE> {
    private val map = mutableMapOf<KEY, VALUE>()

    override fun getOrCreateValue(key: KEY, createValue: (KEY) -> VALUE): VALUE =
        map.computeIfAbsent(key, createValue)
}