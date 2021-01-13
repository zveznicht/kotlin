/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.caches

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.FirSessionComponent

abstract class FirCachesFactory : FirSessionComponent {
    abstract fun <KEY : Any, VALUE, CONTEXT> createCacheWithPostCompute(
        createValue: (KEY, CONTEXT) -> VALUE,
        postCompute: (KEY, VALUE) -> Unit
    ): FirCacheWithPostCompute<KEY, VALUE, CONTEXT>

    abstract fun <KEY : Any, VALUE> createMapLikeCache(): FirMapLikeCache<KEY, VALUE>
}

val FirSession.firCachesFactory: FirCachesFactory by FirSession.sessionComponentAccessor()

object FirThreadUnsafeCachesFactory : FirCachesFactory() {
    override fun <KEY : Any, VALUE, CONTEXT> createCacheWithPostCompute(
        createValue: (KEY, CONTEXT) -> VALUE,
        postCompute: (KEY, VALUE) -> Unit
    ): FirCacheWithPostCompute<KEY, VALUE, CONTEXT> =
        FirThreadUnsafeCacheWithPostCompute(createValue, postCompute)


    override fun <KEY : Any, VALUE> createMapLikeCache(): FirMapLikeCache<KEY, VALUE> =
        FirThreadUnsafeMapLikeCache()
}