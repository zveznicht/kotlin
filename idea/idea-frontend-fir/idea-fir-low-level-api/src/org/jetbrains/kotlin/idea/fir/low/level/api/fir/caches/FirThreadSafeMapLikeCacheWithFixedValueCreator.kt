/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.low.level.api.fir.caches

import org.jetbrains.kotlin.fir.caches.FirMapLikeCacheWithFixedValueCreator
import java.util.concurrent.ConcurrentHashMap

internal class FirThreadSafeMapLikeCacheWithFixedValueCreator<KEY : Any, VALUE>(
    createValue: (KEY) -> VALUE
) : FirMapLikeCacheWithFixedValueCreator<KEY, VALUE> {

    private val map = ConcurrentHashMap<KEY, Any>()

    private val createPossiblyNullValue: (KEY) -> Any = { key -> createValue(key) ?: NullValue }

    @Suppress("UNCHECKED_CAST")
    override fun getOrCreateValue(key: KEY): VALUE =
        map.computeIfAbsent(key, createPossiblyNullValue).nullValueToNull() as VALUE
}