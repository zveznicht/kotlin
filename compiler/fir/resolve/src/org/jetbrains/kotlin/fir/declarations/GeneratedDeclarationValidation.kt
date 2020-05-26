/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.declarations

import org.jetbrains.kotlin.fir.declarations.impl.FirGeneratedClass
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.expressions.FirArgumentList
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.expressions.FirResolvable
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef
import org.jetbrains.kotlin.fir.types.FirTypeRef

// TODO: add proper error messages to all `require`

fun FirAnnotationCall.validate() {
    typeRef.validate()
    annotations.forEach { it.validate() }
    argumentList.validate()
    annotationTypeRef.validate()
    require(resolved)
}

fun FirGeneratedClass.validate() {
    annotations.forEach { it.validate() }
    typeParameters.forEach { it.validate() }
    status.validate()
    val companion = companionObject
    require(companion is FirGeneratedClass?)
    companion?.validate()
    superTypeRefs.forEach { it.validate() }
}

private fun FirArgumentList.validate() {
    require(this is FirResolvedArgumentList)
    mapping.keys.forEach { it.validate() }
}

private fun FirExpression.validate() {
    typeRef.validate()
    if (this is FirResolvable) {
        require(calleeReference is FirResolvedNamedReference)
    }
}

private fun FirTypeRef.validate() {
    require(this is FirResolvedTypeRef)
}

private fun FirDeclarationStatus.validate() {
    require(this is FirResolvedDeclarationStatus)
}

private fun FirTypeParameterRef.validate() {
    symbol.fir.validate()
}

private fun FirTypeParameter.validate() {
    resolvePhase.validate()
    origin.validate()
    bounds.forEach { it.validate() }
    annotations.forEach { it.validate() }
}

private fun FirResolvePhase.validate() {
    require(this == FirResolvePhase.ANALYZED_DEPENDENCIES)
}

private fun FirDeclarationOrigin.validate() {
    require(this is FirDeclarationOrigin.Plugin)
}