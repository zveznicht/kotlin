/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.java.types

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.visitors.FirTransformer
import org.jetbrains.kotlin.fir.visitors.FirVisitor

class FirLazyJavaSuperTypeRef(
    private val computeDelegate: () -> FirResolvedTypeRef
) : FirResolvedTypeRef() {
    private val delegate by lazy { computeDelegate() }

    override val source: FirSourceElement?
        get() = delegate.source
    override val annotations: List<FirAnnotationCall>
        get() = delegate.annotations
    override val type: ConeKotlinType
        get() = delegate.type
    override val delegatedTypeRef: FirTypeRef?
        get() = delegate.delegatedTypeRef

    override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirResolvedTypeRef {
        return delegate.transformAnnotations(transformer, data)
    }

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        delegate.acceptChildren(visitor, data)
    }

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirElement {
        return delegate.transformChildren(transformer, data)
    }
}