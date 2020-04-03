/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.types.model

import org.jetbrains.kotlin.types.AbstractTypeCheckerContext

interface KotlinTypeMarker
interface TypeArgumentMarker
interface TypeConstructorMarker
interface TypeParameterMarker

interface SimpleTypeMarker : KotlinTypeMarker
interface CapturedTypeMarker : SimpleTypeMarker
interface DefinitelyNotNullTypeMarker : SimpleTypeMarker

interface FlexibleTypeMarker : KotlinTypeMarker
interface DynamicTypeMarker : FlexibleTypeMarker
interface RawTypeMarker : FlexibleTypeMarker
interface StubTypeMarker : SimpleTypeMarker

interface TypeArgumentListMarker

interface TypeVariableMarker
interface TypeVariableTypeConstructorMarker : TypeConstructorMarker

interface CapturedTypeConstructorMarker : TypeConstructorMarker

interface TypeSubstitutorMarker


enum class TypeVariance(val presentation: String) {
    IN("in"),
    OUT("out"),
    INV("");

    override fun toString(): String = presentation
}


interface TypeCheckerProviderContext {
    fun newBaseTypeCheckerContext(
        errorTypesEqualToAnything: Boolean,
        stubTypesEqualToAnything: Boolean
    ): AbstractTypeCheckerContext
}

abstract class TypeSystemCommonSuperTypesContext : TypeSystemContext(), TypeCheckerProviderContext {
    /*
     * If set in false then if there is an error type in input types list of `commonSuperType` it will be return
     * That flag is needed for FIR where there are a problems with recursive class hierarchies
     */
    abstract val isErrorTypeAllowed: Boolean

    open fun KotlinTypeMarker.anySuperTypeConstructor(predicate: (TypeConstructorMarker) -> Boolean) =
        newBaseTypeCheckerContext(errorTypesEqualToAnything = false, stubTypesEqualToAnything = true)
            .anySupertype(
                lowerBoundIfFlexible(),
                { predicate(it.typeConstructor()) },
                { AbstractTypeCheckerContext.SupertypesPolicy.LowerIfFlexible }
            )

    abstract fun KotlinTypeMarker.canHaveUndefinedNullability(): Boolean

    abstract fun SimpleTypeMarker.isExtensionFunction(): Boolean

    abstract fun SimpleTypeMarker.typeDepth(): Int

    open fun KotlinTypeMarker.typeDepth(): Int = when (this) {
        is SimpleTypeMarker -> typeDepth()
        is FlexibleTypeMarker -> maxOf(lowerBound().typeDepth(), upperBound().typeDepth())
        else -> error("Type should be simple or flexible: $this")
    }

    abstract fun findCommonIntegerLiteralTypesSuperType(explicitSupertypes: List<SimpleTypeMarker>): SimpleTypeMarker?

    /*
     * Converts error type constructor to error type
     * Used only in FIR
     */
    abstract fun TypeConstructorMarker.toErrorType(): SimpleTypeMarker

    abstract fun createFlexibleType(lowerBound: SimpleTypeMarker, upperBound: SimpleTypeMarker): KotlinTypeMarker
    abstract fun createSimpleType(
        constructor: TypeConstructorMarker,
        arguments: List<TypeArgumentMarker>,
        nullable: Boolean,
        isExtensionFunction: Boolean = false
    ): SimpleTypeMarker

    abstract fun createTypeArgument(type: KotlinTypeMarker, variance: TypeVariance): TypeArgumentMarker
    abstract fun createStarProjection(typeParameter: TypeParameterMarker): TypeArgumentMarker

    abstract fun createErrorTypeWithCustomConstructor(debugName: String, constructor: TypeConstructorMarker): KotlinTypeMarker
}

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


class ArgumentList(initialSize: Int) : ArrayList<TypeArgumentMarker>(initialSize), TypeArgumentListMarker


abstract class TypeSystemContext {
    abstract fun KotlinTypeMarker.asSimpleType(): SimpleTypeMarker?
    abstract fun KotlinTypeMarker.asFlexibleType(): FlexibleTypeMarker?

    abstract fun KotlinTypeMarker.isError(): Boolean
    abstract fun TypeConstructorMarker.isError(): Boolean
    abstract fun KotlinTypeMarker.isUninferredParameter(): Boolean

    abstract fun FlexibleTypeMarker.asDynamicType(): DynamicTypeMarker?

    abstract fun FlexibleTypeMarker.asRawType(): RawTypeMarker?
    abstract fun FlexibleTypeMarker.upperBound(): SimpleTypeMarker

    abstract fun FlexibleTypeMarker.lowerBound(): SimpleTypeMarker
    abstract fun SimpleTypeMarker.asCapturedType(): CapturedTypeMarker?

    open fun KotlinTypeMarker.isCapturedType() = asSimpleType()?.asCapturedType() != null

    abstract fun SimpleTypeMarker.asDefinitelyNotNullType(): DefinitelyNotNullTypeMarker?
    abstract fun SimpleTypeMarker.isMarkedNullable(): Boolean
    abstract fun SimpleTypeMarker.withNullability(nullable: Boolean): SimpleTypeMarker
    abstract fun SimpleTypeMarker.typeConstructor(): TypeConstructorMarker

    abstract fun CapturedTypeMarker.typeConstructor(): CapturedTypeConstructorMarker
    abstract fun CapturedTypeMarker.captureStatus(): CaptureStatus
    abstract fun CapturedTypeMarker.isProjectionNotNull(): Boolean
    abstract fun CapturedTypeConstructorMarker.projection(): TypeArgumentMarker

    abstract fun KotlinTypeMarker.argumentsCount(): Int
    abstract fun KotlinTypeMarker.getArgument(index: Int): TypeArgumentMarker

    open fun SimpleTypeMarker.getArgumentOrNull(index: Int): TypeArgumentMarker? {
        if (index in 0 until argumentsCount()) return getArgument(index)
        return null
    }

    abstract fun SimpleTypeMarker.isStubType(): Boolean

    abstract fun KotlinTypeMarker.asTypeArgument(): TypeArgumentMarker

    abstract fun CapturedTypeMarker.lowerType(): KotlinTypeMarker?

    abstract fun TypeArgumentMarker.isStarProjection(): Boolean
    abstract fun TypeArgumentMarker.getVariance(): TypeVariance
    abstract fun TypeArgumentMarker.getType(): KotlinTypeMarker

    abstract fun TypeConstructorMarker.parametersCount(): Int
    abstract fun TypeConstructorMarker.getParameter(index: Int): TypeParameterMarker
    abstract fun TypeConstructorMarker.supertypes(): Collection<KotlinTypeMarker>
    abstract fun TypeConstructorMarker.isIntersection(): Boolean
    abstract fun TypeConstructorMarker.isClassTypeConstructor(): Boolean
    abstract fun TypeConstructorMarker.isIntegerLiteralTypeConstructor(): Boolean

    abstract fun TypeParameterMarker.getVariance(): TypeVariance
    abstract fun TypeParameterMarker.upperBoundCount(): Int
    abstract fun TypeParameterMarker.getUpperBound(index: Int): KotlinTypeMarker
    abstract fun TypeParameterMarker.getTypeConstructor(): TypeConstructorMarker

    abstract fun isEqualTypeConstructors(c1: TypeConstructorMarker, c2: TypeConstructorMarker): Boolean

    abstract fun TypeConstructorMarker.isDenotable(): Boolean

    open fun KotlinTypeMarker.lowerBoundIfFlexible(): SimpleTypeMarker = this.asFlexibleType()?.lowerBound() ?: this.asSimpleType()!!
    open fun KotlinTypeMarker.upperBoundIfFlexible(): SimpleTypeMarker = this.asFlexibleType()?.upperBound() ?: this.asSimpleType()!!

    open fun KotlinTypeMarker.isFlexible(): Boolean = asFlexibleType() != null

    open fun KotlinTypeMarker.isDynamic(): Boolean = asFlexibleType()?.asDynamicType() != null
    open fun KotlinTypeMarker.isCapturedDynamic(): Boolean =
        asSimpleType()?.asCapturedType()?.typeConstructor()?.projection()?.takeUnless { it.isStarProjection() }
            ?.getType()?.isDynamic() == true

    open fun KotlinTypeMarker.isDefinitelyNotNullType(): Boolean = asSimpleType()?.asDefinitelyNotNullType() != null

    open fun KotlinTypeMarker.hasFlexibleNullability() =
        lowerBoundIfFlexible().isMarkedNullable() != upperBoundIfFlexible().isMarkedNullable()

    open fun KotlinTypeMarker.typeConstructor(): TypeConstructorMarker =
        (asSimpleType() ?: lowerBoundIfFlexible()).typeConstructor()

    abstract fun KotlinTypeMarker.isNullableType(): Boolean

    open fun KotlinTypeMarker.isNullableAny() = this.typeConstructor().isAnyConstructor() && this.isNullableType()
    open fun KotlinTypeMarker.isNothing() = this.typeConstructor().isNothingConstructor() && !this.isNullableType()
    open fun KotlinTypeMarker.isFlexibleNothing() =
        this is FlexibleTypeMarker && lowerBound().isNothing() && upperBound().isNullableNothing()

    open fun KotlinTypeMarker.isNullableNothing() = this.typeConstructor().isNothingConstructor() && this.isNullableType()

    open fun SimpleTypeMarker.isClassType(): Boolean = typeConstructor().isClassTypeConstructor()

    open fun SimpleTypeMarker.fastCorrespondingSupertypes(constructor: TypeConstructorMarker): List<SimpleTypeMarker>? = null

    open fun SimpleTypeMarker.isIntegerLiteralType(): Boolean = typeConstructor().isIntegerLiteralTypeConstructor()

    abstract fun SimpleTypeMarker.possibleIntegerTypes(): Collection<KotlinTypeMarker>

    abstract fun TypeConstructorMarker.isCommonFinalClassConstructor(): Boolean

    abstract fun captureFromArguments(
        type: SimpleTypeMarker,
        status: CaptureStatus
    ): SimpleTypeMarker?

    abstract fun captureFromExpression(type: KotlinTypeMarker): KotlinTypeMarker?

    abstract fun SimpleTypeMarker.asArgumentList(): TypeArgumentListMarker

    open operator fun TypeArgumentListMarker.get(index: Int): TypeArgumentMarker {
        return when (this) {
            is SimpleTypeMarker -> getArgument(index)
            is ArgumentList -> get(index)
            else -> error("unknown type argument list type: $this, ${this::class}")
        }
    }

    open fun TypeArgumentListMarker.size(): Int {
        return when (this) {
            is SimpleTypeMarker -> argumentsCount()
            is ArgumentList -> size
            else -> error("unknown type argument list type: $this, ${this::class}")
        }
    }

    abstract fun TypeConstructorMarker.isAnyConstructor(): Boolean
    abstract fun TypeConstructorMarker.isNothingConstructor(): Boolean

    /**
     *
     * SingleClassifierType is one of the following types:
     *  - classType
     *  - type for type parameter
     *  - captured type
     *
     * Such types can contains error types in our arguments, but type constructor isn't errorTypeConstructor
     */
    abstract fun SimpleTypeMarker.isSingleClassifierType(): Boolean

    abstract fun intersectTypes(types: List<KotlinTypeMarker>): KotlinTypeMarker
    abstract fun intersectTypes(types: List<SimpleTypeMarker>): SimpleTypeMarker

    open fun KotlinTypeMarker.isSimpleType() = asSimpleType() != null

    abstract fun prepareType(type: KotlinTypeMarker): KotlinTypeMarker

    abstract fun SimpleTypeMarker.isPrimitiveType(): Boolean

    /**
     *  @return true is a.arguments == b.arguments, or false if not supported
     */
    open fun identicalArguments(a: SimpleTypeMarker, b: SimpleTypeMarker) = false
}

enum class CaptureStatus {
    FOR_SUBTYPING,
    FOR_INCORPORATION,
    FROM_EXPRESSION
}

inline fun TypeArgumentListMarker.all(
    context: TypeSystemContext,
    crossinline predicate: (TypeArgumentMarker) -> Boolean
): Boolean = with(context) {
    repeat(size()) { index ->
        if (!predicate(get(index))) return false
    }
    return true
}
