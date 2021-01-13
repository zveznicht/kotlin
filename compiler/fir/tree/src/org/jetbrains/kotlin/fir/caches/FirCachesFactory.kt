/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.caches

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.FirSessionComponent

abstract class FirCachesFactory : FirSessionComponent {
    abstract fun <KEY : Any, VALUE> createMapLikeCacheWithFixedCompute(createValue: (KEY) -> VALUE): FirMapLikeCacheWithFixedValueCreator<KEY, VALUE>
    abstract fun <KEY : Any, VALUE> createMapLikeCache(): FirMapLikeCache<KEY, VALUE>

    abstract fun <VALUE> createValueWithPostCompute(
        createValue: () -> VALUE,
        postCompute: (VALUE) -> Unit
    ): FirValueWithPostCompute<VALUE>
}

val FirSession.firCachesFactory: FirCachesFactory by FirSession.sessionComponentAccessor()

object FirThreadUnsafeCachesFactory : FirCachesFactory() {
    override fun <KEY : Any, VALUE> createMapLikeCacheWithFixedCompute(createValue: (KEY) -> VALUE): FirMapLikeCacheWithFixedValueCreator<KEY, VALUE> =
        FirThreadUnsafeMapLikeCacheWithFixedValueCreator(createValue)

    override fun <KEY : Any, VALUE> createMapLikeCache(): FirMapLikeCache<KEY, VALUE> =
        FirThreadUnsafeMapLikeCache()

    override fun <VALUE> createValueWithPostCompute(
        createValue: () -> VALUE,
        postCompute: (VALUE) -> Unit
    ): FirValueWithPostCompute<VALUE> =
        FirThreadUnsafeLazyValueWithPostCompute(createValue, postCompute)
}