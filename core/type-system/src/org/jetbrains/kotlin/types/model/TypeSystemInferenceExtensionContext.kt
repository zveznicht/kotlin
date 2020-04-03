/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.types.model

abstract class TypeSystemInferenceExtensionContext : TypeSystemCommonSuperTypesContext() {
    abstract fun KotlinTypeMarker.contains(predicate: (KotlinTypeMarker) -> Boolean): Boolean

    abstract fun TypeConstructorMarker.isUnitTypeConstructor(): Boolean

    abstract fun TypeConstructorMarker.getApproximatedIntegerLiteralType(): KotlinTypeMarker

    abstract fun TypeConstructorMarker.isCapturedTypeConstructor(): Boolean

    abstract fun Collection<KotlinTypeMarker>.singleBestRepresentative(): KotlinTypeMarker?

    abstract fun KotlinTypeMarker.isUnit(): Boolean

    abstract fun KotlinTypeMarker.withNullability(nullable: Boolean): KotlinTypeMarker


    abstract fun KotlinTypeMarker.makeDefinitelyNotNullOrNotNull(): KotlinTypeMarker
    abstract fun SimpleTypeMarker.makeSimpleTypeDefinitelyNotNullOrNotNull(): SimpleTypeMarker

    abstract fun createCapturedType(
        constructorProjection: TypeArgumentMarker,
        constructorSupertypes: List<KotlinTypeMarker>,
        lowerType: KotlinTypeMarker?,
        captureStatus: CaptureStatus
    ): CapturedTypeMarker

    abstract fun createStubType(typeVariable: TypeVariableMarker): StubTypeMarker


    abstract fun KotlinTypeMarker.removeAnnotations(): KotlinTypeMarker
    abstract fun KotlinTypeMarker.removeExactAnnotation(): KotlinTypeMarker

    abstract fun SimpleTypeMarker.replaceArguments(newArguments: List<TypeArgumentMarker>): SimpleTypeMarker

    abstract fun KotlinTypeMarker.hasExactAnnotation(): Boolean
    abstract fun KotlinTypeMarker.hasNoInferAnnotation(): Boolean

    abstract fun TypeVariableMarker.freshTypeConstructor(): TypeConstructorMarker

    abstract fun KotlinTypeMarker.mayBeTypeVariable(): Boolean

    abstract fun CapturedTypeMarker.typeConstructorProjection(): TypeArgumentMarker
    abstract fun CapturedTypeMarker.typeParameter(): TypeParameterMarker?
    abstract fun CapturedTypeMarker.withNotNullProjection(): KotlinTypeMarker

    abstract fun DefinitelyNotNullTypeMarker.original(): SimpleTypeMarker

    abstract fun typeSubstitutorByTypeConstructor(map: Map<TypeConstructorMarker, KotlinTypeMarker>): TypeSubstitutorMarker
    abstract fun createEmptySubstitutor(): TypeSubstitutorMarker

    abstract fun TypeSubstitutorMarker.safeSubstitute(type: KotlinTypeMarker): KotlinTypeMarker


    abstract fun TypeVariableMarker.defaultType(): SimpleTypeMarker

    abstract fun createTypeWithAlternativeForIntersectionResult(
        firstCandidate: KotlinTypeMarker,
        secondCandidate: KotlinTypeMarker
    ): KotlinTypeMarker

    abstract fun nullableNothingType(): SimpleTypeMarker
    abstract fun nullableAnyType(): SimpleTypeMarker
    abstract fun nothingType(): SimpleTypeMarker
    abstract fun anyType(): SimpleTypeMarker
}