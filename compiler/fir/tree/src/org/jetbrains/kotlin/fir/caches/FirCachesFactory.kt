/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.caches

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.FirSessionComponent

abstract class FirCachesFactory : FirSessionComponent {
    abstract fun <KEY : Any, VALUE> createCache(createValue: (KEY) -> VALUE): FirCache<KEY, VALUE>

    abstract fun <KEY : Any, VALUE, CONTEXT, DATA> createCacheWithPostCompute(
        createValue: (KEY, CONTEXT) -> Pair<VALUE, DATA>,
        postCompute: (KEY, VALUE, DATA) -> Unit
    ): FirCacheWithPostCompute<KEY, VALUE, CONTEXT, DATA>
}

val FirSession.firCachesFactory: FirCachesFactory by FirSession.sessionComponentAccessor()

object FirThreadUnsafeCachesFactory : FirCachesFactory() {
    override fun <KEY : Any, VALUE> createCache(createValue: (KEY) -> VALUE): FirCache<KEY, VALUE> =
        FirThreadUnsafeCache(createValue)

    override fun <KEY : Any, VALUE, CONTEXT, DATA> createCacheWithPostCompute(
        createValue: (KEY, CONTEXT) -> Pair<VALUE, DATA>,
        postCompute: (KEY, VALUE, DATA) -> Unit
    ): FirCacheWithPostCompute<KEY, VALUE, CONTEXT, DATA> =
        FirThreadUnsafeCacheWithPostCompute(createValue, postCompute)
}