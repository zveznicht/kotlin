/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.low.level.api.fir.caches

import org.jetbrains.kotlin.fir.caches.FirMapLikeCache
import java.util.concurrent.ConcurrentHashMap

internal class FirThreadSafeMapLikeCache<KEY : Any, VALUE> : FirMapLikeCache<KEY, VALUE> {
    private val map = ConcurrentHashMap<KEY, Any>()

    override fun getOrCreateValue(key: KEY, createValue: (KEY) -> VALUE): VALUE =
        map.computeIfAbsentWithNullableValue(key, createValue)
}