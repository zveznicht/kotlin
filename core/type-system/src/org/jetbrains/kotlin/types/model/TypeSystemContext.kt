/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.types.model

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
