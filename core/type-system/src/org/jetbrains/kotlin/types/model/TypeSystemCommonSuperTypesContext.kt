/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.types.model

import org.jetbrains.kotlin.types.AbstractTypeCheckerContext

abstract class TypeSystemCommonSuperTypesContext : TypeSystemContext(),
    TypeCheckerProviderContext {
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