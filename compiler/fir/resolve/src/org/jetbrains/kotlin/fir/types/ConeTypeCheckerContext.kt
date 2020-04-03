/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.types

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
import org.jetbrains.kotlin.fir.resolve.substitution.substitutorByMap
import org.jetbrains.kotlin.fir.resolve.toSymbol
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.firUnsafe
import org.jetbrains.kotlin.types.AbstractTypeCheckerContext
import org.jetbrains.kotlin.types.model.*

class ConeTypeCheckerContext(
    override val baseContext: ConeTypeContext,
    override val isErrorTypeEqualsToAnything: Boolean,
    override val isStubTypeEqualsToAnything: Boolean,
) : AbstractTypeCheckerContext(baseContext) {
    override fun substitutionSupertypePolicy(type: SimpleTypeMarker): SupertypesPolicy {
        with(baseContext) {
            if (type.argumentsCount() == 0) return SupertypesPolicy.LowerIfFlexible
            require(type is ConeKotlinType)
            val declaration = when (type) {
                is ConeClassLikeType -> type.lookupTag.toSymbol(session)?.firUnsafe<FirClassLikeDeclaration<*>>()
                else -> null
            }

            val substitutor = if (declaration is FirTypeParametersOwner) {
                val substitution =
                    declaration.typeParameters.zip(type.typeArguments).associate { (parameter, argument) ->
                        parameter.symbol to ((argument as? ConeKotlinTypeProjection)?.type
                            ?: session.builtinTypes.nullableAnyType.type)//StandardClassIds.Any(session.firSymbolProvider).constructType(emptyArray(), isNullable = true))
                    }
                substitutorByMap(substitution)
            } else {
                ConeSubstitutor.Empty
            }
            return object : SupertypesPolicy.DoCustomTransform() {
                override fun transformType(context: AbstractTypeCheckerContext, type: KotlinTypeMarker): SimpleTypeMarker {
                    val lowerBound = type.lowerBoundIfFlexible()
                    require(lowerBound is ConeKotlinType)
                    return substitutor.substituteOrSelf(lowerBound) as SimpleTypeMarker
                }

            }
        }
    }

    override fun areEqualTypeConstructors(a: TypeConstructorMarker, b: TypeConstructorMarker): Boolean {
        return a == b
    }

    override fun prepareType(type: KotlinTypeMarker): KotlinTypeMarker {
        return baseContext.prepareType(type)
    }

    override fun refineType(type: KotlinTypeMarker): KotlinTypeMarker {
        return prepareType(type)
    }

    override val KotlinTypeMarker.isAllowedTypeVariable: Boolean
        get() = this is ConeKotlinType && this is ConeTypeVariableType

}
