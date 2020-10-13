/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.expressions.impl

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.expressions.FirClassReferenceExpression
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImpl
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirClassReferenceExpressionImpl(
    override val source: FirSourceElement?,
    override val annotations: MutableList<FirAnnotationCall>,
    override var classTypeRef: FirTypeRef,
) : FirClassReferenceExpression() {
    override var typeRef: FirTypeRef = FirImplicitTypeRefImpl(null)

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        typeRef.accept(visitor, data)
        annotations.forEach { it.accept(visitor, data) }
        classTypeRef.accept(visitor, data)
    }

    override val children: Iterable<FirElement> get() = mutableListOf<FirElement>().also {
        it.add(typeRef)
        it.addAll(annotations)
        it.add(classTypeRef)
    }

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirClassReferenceExpressionImpl {
        typeRef = typeRef.transform<FirTypeRef, D>(transformer, data).single
        transformAnnotations(transformer, data)
        classTypeRef = classTypeRef.transform<FirTypeRef, D>(transformer, data).single
        return this
    }

    override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirClassReferenceExpressionImpl {
        annotations.transformInplace(transformer, data)
        return this
    }

    override fun replaceTypeRef(newTypeRef: FirTypeRef) {
        typeRef = newTypeRef
    }
}
