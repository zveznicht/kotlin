/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.types

import org.jetbrains.kotlin.fir.FirAnnotationContainer
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.visitors.FirTransformer
import org.jetbrains.kotlin.fir.visitors.FirVisitor

class FirDeserializedTypeRef(
    override val type: ConeKotlinType,
    annotations: List<FirAnnotationCall>,
    additionalAnnotationBuilder: () -> List<FirAnnotationCall>,
) : FirResolvedTypeRef(), FirAnnotationContainer {

    override val annotations: List<FirAnnotationCall> by lazy { annotations + additionalAnnotationBuilder() }

    override val delegatedTypeRef: FirTypeRef?
        get() = null

    override val source: FirSourceElement?
        get() = null

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        annotations.forEach { it.accept(visitor, data) }
    }

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirResolvedTypeRef {
        return this
    }

    override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirResolvedTypeRef {
        return this
    }
}

@FirBuilderDsl
class FirDeserializedTypeRefBuilder {
    val annotations = mutableListOf<FirAnnotationCall>()
    lateinit var additionalAnnotationBuilder: () -> List<FirAnnotationCall>
    lateinit var type: ConeKotlinType

    fun build(): FirDeserializedTypeRef {
        return FirDeserializedTypeRef(type, annotations, additionalAnnotationBuilder)
    }
}

inline fun buildDeserializedTypeRef(init: FirDeserializedTypeRefBuilder.() -> Unit): FirDeserializedTypeRef {
    return FirDeserializedTypeRefBuilder().apply(init).build()
}