/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.fir.low.level.api.fir.caches

import org.jetbrains.kotlin.fir.caches.FirCachesFactory
import org.jetbrains.kotlin.fir.caches.FirMapLikeCache
import org.jetbrains.kotlin.fir.caches.FirMapLikeCacheWithFixedValueCreator
import org.jetbrains.kotlin.fir.caches.FirValueWithPostCompute

object FirThreadSafeCachesFactory: FirCachesFactory() {
    override fun <KEY : Any, VALUE> createMapLikeCacheWithFixedCompute(createValue: (KEY) -> VALUE): FirMapLikeCacheWithFixedValueCreator<KEY, VALUE>  =
        FirThreadSafeMapLikeCacheWithFixedValueCreator(createValue)

    override fun <KEY : Any, VALUE> createMapLikeCache(): FirMapLikeCache<KEY, VALUE> =
        FirThreadSafeMapLikeCache()

    override fun <VALUE> createValueWithPostCompute(
        createValue: () -> VALUE,
        postCompute: (VALUE) -> Unit
    ): FirValueWithPostCompute<VALUE> =
        FirLazyThreadSafeValueWithPostCompute(createValue, postCompute)
}