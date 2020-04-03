/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.types

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.resolve.*
import org.jetbrains.kotlin.fir.resolve.calls.NoSubstitutor
import org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
import org.jetbrains.kotlin.fir.symbols.AbstractFirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.StandardClassIds
import org.jetbrains.kotlin.fir.symbols.impl.*
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.FqNameUnsafe
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.types.AbstractTypeChecker
import org.jetbrains.kotlin.types.AbstractTypeCheckerContext
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext
import org.jetbrains.kotlin.types.checker.convertVariance
import org.jetbrains.kotlin.types.model.*
import org.jetbrains.kotlin.utils.addToStdlib.cast

class ErrorTypeConstructor(val reason: String) : TypeConstructorMarker {
    override fun toString(): String = reason
}

class ConeTypeContext(val session: FirSession) : TypeSystemInferenceExtensionContext(), TypeCheckerProviderContext,
    TypeSystemCommonBackendContext {

    override val baseContext: TypeSystemContext
        get() = this

    val symbolProvider: FirSymbolProvider get() = session.firSymbolProvider

    override val isErrorTypeAllowed: Boolean get() = false

    override fun nullableNothingType(): SimpleTypeMarker {
        return session.builtinTypes.nullableNothingType.type//StandardClassIds.Nothing(symbolProvider).constructType(emptyArray(), true)
    }

    override fun nullableAnyType(): SimpleTypeMarker {
        return session.builtinTypes.nullableAnyType.type//StandardClassIds.Any(symbolProvider).constructType(emptyArray(), true)
    }

    override fun nothingType(): SimpleTypeMarker {
        return session.builtinTypes.nothingType.type//StandardClassIds.Nothing(symbolProvider).constructType(emptyArray(), false)
    }

    override fun anyType(): SimpleTypeMarker {
        return session.builtinTypes.anyType.type//StandardClassIds.Any(symbolProvider).constructType(emptyArray(), false)
    }

    override fun createFlexibleType(lowerBound: SimpleTypeMarker, upperBound: SimpleTypeMarker): KotlinTypeMarker {
        require(lowerBound is ConeKotlinType)
        require(upperBound is ConeKotlinType)

        return coneFlexibleOrSimpleType(this, lowerBound, upperBound)
    }

    // TODO: implement taking into account `isExtensionFunction`
    override fun createSimpleType(
        constructor: TypeConstructorMarker,
        arguments: List<TypeArgumentMarker>,
        nullable: Boolean,
        isExtensionFunction: Boolean
    ): SimpleTypeMarker {
        require(constructor is FirClassifierSymbol<*>)
        return when (constructor) {
            is FirClassLikeSymbol<*> -> ConeClassLikeTypeImpl(
                constructor.toLookupTag(),
                (arguments as List<ConeTypeProjection>).toTypedArray(),
                nullable
            )
            is FirTypeParameterSymbol -> ConeTypeParameterTypeImpl(
                constructor.toLookupTag(),
                nullable
            )
            else -> error("!")
        }

    }

    override fun createTypeArgument(type: KotlinTypeMarker, variance: TypeVariance): TypeArgumentMarker {
        require(type is ConeKotlinType)
        return when (variance) {
            TypeVariance.INV -> type
            TypeVariance.IN -> ConeKotlinTypeProjectionIn(type)
            TypeVariance.OUT -> ConeKotlinTypeProjectionOut(type)
        }
    }

    override fun createStarProjection(typeParameter: TypeParameterMarker): TypeArgumentMarker {
        return ConeStarProjection
    }

    override fun newBaseTypeCheckerContext(
        errorTypesEqualToAnything: Boolean,
        stubTypesEqualToAnything: Boolean
    ): AbstractTypeCheckerContext =
        ConeTypeCheckerContext(this, errorTypesEqualToAnything, stubTypesEqualToAnything)

    override fun KotlinTypeMarker.canHaveUndefinedNullability(): Boolean {
        require(this is ConeKotlinType)
        return this is ConeCapturedType /*|| this is ConeTypeVariable // TODO */
                || this is ConeTypeParameterType
    }

    // TODO: implement checking for extension function
    override fun SimpleTypeMarker.isExtensionFunction(): Boolean {
        require(this is ConeKotlinType)
        return false
    }

    override fun KotlinTypeMarker.typeDepth() = when (this) {
        is ConeSimpleKotlinType -> typeDepth()
        is ConeFlexibleType -> maxOf(lowerBound().typeDepth(), upperBound().typeDepth())
        else -> error("Type should be simple or flexible: $this")
    }

    override fun SimpleTypeMarker.typeDepth(): Int {
        require(this is ConeKotlinType)
        // if (this is TypeUtils.SpecialType) return 0 // TODO: WTF?

        var maxArgumentDepth = 0
        for (arg in typeArguments) {
            val current = if (arg is ConeStarProjection) 1 else (arg as ConeKotlinTypeProjection).type.typeDepth()
            if (current > maxArgumentDepth) {
                maxArgumentDepth = current
            }
        }

        var result = maxArgumentDepth + 1

        if (this is ConeClassLikeType) {
            val fullyExpanded = fullyExpandedType(session)
            if (this !== fullyExpanded) {
                val fullyExpandedTypeDepth = fullyExpanded.typeDepth()
                if (fullyExpandedTypeDepth > result) {
                    result = fullyExpandedTypeDepth
                }
            }
        }

        return result
    }

    override fun KotlinTypeMarker.contains(predicate: (KotlinTypeMarker) -> Boolean): Boolean {
        return this.containsInternal(predicate)
    }

    private fun KotlinTypeMarker?.containsInternal(
        predicate: (KotlinTypeMarker) -> Boolean,
        visited: HashSet<KotlinTypeMarker> = hashSetOf()
    ): Boolean {
        if (this == null) return false
        if (!visited.add(this)) return false

        /*
        TODO:?
        UnwrappedType unwrappedType = type.unwrap();
         */

        if (predicate(this)) return true

        val flexibleType = this as? ConeFlexibleType
        if (flexibleType != null
            && (flexibleType.lowerBound.containsInternal(predicate, visited)
                    || flexibleType.upperBound.containsInternal(predicate, visited))
        ) {
            return true
        }


        if (this is ConeDefinitelyNotNullType
            && this.original.containsInternal(predicate, visited)
        ) {
            return true
        }

        if (this is ConeIntersectionType) {
            return this.intersectedTypes.any { it.containsInternal(predicate, visited) }
        }

        repeat(argumentsCount()) { index ->
            val argument = getArgument(index)
            if (!argument.isStarProjection() && argument.getType().containsInternal(predicate, visited)) return true
        }

        return false
    }

    override fun TypeConstructorMarker.isUnitTypeConstructor(): Boolean {
        return this is FirClassLikeSymbol<*> && this.classId == StandardClassIds.Unit
    }

    override fun Collection<KotlinTypeMarker>.singleBestRepresentative(): KotlinTypeMarker? {
        if (this.size == 1) return this.first()

        val context = newBaseTypeCheckerContext(errorTypesEqualToAnything = true, stubTypesEqualToAnything = true)
        return this.firstOrNull { candidate ->
            this.all { other ->
                // We consider error types equal to anything here, so that intersections like
                // {Array<String>, Array<[ERROR]>} work correctly
                candidate == other || AbstractTypeChecker.equalTypes(context, candidate, other)
            }
        }
    }

    override fun KotlinTypeMarker.isUnit(): Boolean {
        require(this is ConeKotlinType)
        return this.typeConstructor().isUnitTypeConstructor() && !this.isNullable
    }

    override fun KotlinTypeMarker.withNullability(nullable: Boolean): KotlinTypeMarker {
        require(this is ConeKotlinType)
        return this.withNullability(ConeNullability.create(nullable), this@ConeTypeContext)
    }

    override fun KotlinTypeMarker.makeDefinitelyNotNullOrNotNull(): KotlinTypeMarker {
        require(this is ConeKotlinType)
        return makeConeTypeDefinitelyNotNullOrNotNull()
    }

    override fun SimpleTypeMarker.makeSimpleTypeDefinitelyNotNullOrNotNull(): SimpleTypeMarker {
        require(this is ConeKotlinType)
        return makeConeTypeDefinitelyNotNullOrNotNull() as SimpleTypeMarker
    }

    override fun createCapturedType(
        constructorProjection: TypeArgumentMarker,
        constructorSupertypes: List<KotlinTypeMarker>,
        lowerType: KotlinTypeMarker?,
        captureStatus: CaptureStatus
    ): CapturedTypeMarker {
        require(lowerType is ConeKotlinType?)
        require(constructorProjection is ConeTypeProjection)
        return ConeCapturedType(
            captureStatus,
            lowerType,
            constructor = ConeCapturedTypeConstructor(constructorProjection, constructorSupertypes.cast())
        )
    }

    override fun createStubType(typeVariable: TypeVariableMarker): StubTypeMarker {
        require(typeVariable is ConeTypeVariable) { "$typeVariable should subtype of ${ConeTypeVariable::class.qualifiedName}" }
        return ConeStubType(typeVariable, ConeNullability.create(typeVariable.defaultType().isMarkedNullable()))
    }

    override fun KotlinTypeMarker.removeAnnotations(): KotlinTypeMarker {
        return this // TODO
    }

    override fun SimpleTypeMarker.replaceArguments(newArguments: List<TypeArgumentMarker>): SimpleTypeMarker {
        require(this is ConeKotlinType)
        return this.withArguments(newArguments.cast<List<ConeTypeProjection>>().toTypedArray())
    }

    override fun KotlinTypeMarker.hasExactAnnotation(): Boolean {
        return false // TODO
    }

    override fun KotlinTypeMarker.hasNoInferAnnotation(): Boolean {
        return false // TODO
    }

    override fun TypeVariableMarker.freshTypeConstructor(): TypeConstructorMarker {
        require(this is ConeTypeVariable)
        return this.typeConstructor
    }

    override fun KotlinTypeMarker.mayBeTypeVariable(): Boolean {
        require(this is ConeKotlinType)
        return this.typeConstructor() is ConeTypeVariableTypeConstructor
    }

    override fun CapturedTypeMarker.typeConstructorProjection(): TypeArgumentMarker {
        require(this is ConeCapturedType)
        return this.constructor.projection
    }

    override fun CapturedTypeMarker.typeParameter(): TypeParameterMarker? {
        require(this is ConeCapturedType)
        return this.constructor.typeParameterMarker
    }

    override fun CapturedTypeMarker.withNotNullProjection(): KotlinTypeMarker {
        require(this is ConeCapturedType)
        return this // TODO
    }

    override fun CapturedTypeMarker.isProjectionNotNull(): Boolean {
        require(this is ConeCapturedType)
        return false // TODO
    }

    override fun DefinitelyNotNullTypeMarker.original(): SimpleTypeMarker {
        require(this is ConeDefinitelyNotNullType)
        return this.original as SimpleTypeMarker
    }

    override fun typeSubstitutorByTypeConstructor(map: Map<TypeConstructorMarker, KotlinTypeMarker>): TypeSubstitutorMarker {
        if (map.isEmpty()) return createEmptySubstitutor()
        return object : AbstractConeSubstitutor(),
            TypeSubstitutorMarker {
            override fun substituteType(type: ConeKotlinType): ConeKotlinType? {
                if (type !is ConeLookupTagBasedType) return null
                val new = map[type.typeConstructor()] ?: return null
                return (new as ConeKotlinType).approximateIntegerLiteralType().updateNullabilityIfNeeded(type)
            }
        }
    }

    override fun createEmptySubstitutor(): TypeSubstitutorMarker {
        return ConeSubstitutor.Empty
    }

    override fun TypeSubstitutorMarker.safeSubstitute(type: KotlinTypeMarker): KotlinTypeMarker {
        if (this === NoSubstitutor) return type
        require(this is ConeSubstitutor)
        require(type is ConeKotlinType)
        return this.substituteOrSelf(type)
    }

    override fun TypeVariableMarker.defaultType(): SimpleTypeMarker {
        require(this is ConeTypeVariable)
        return this.defaultType
    }

    override fun captureFromExpression(type: KotlinTypeMarker): KotlinTypeMarker? {
        return type
    }

    override fun createErrorTypeWithCustomConstructor(debugName: String, constructor: TypeConstructorMarker): KotlinTypeMarker {
        return ConeKotlinErrorType("$debugName c: $constructor")
    }

    override fun CapturedTypeMarker.captureStatus(): CaptureStatus {
        require(this is ConeCapturedType)
        return this.captureStatus
    }

    override fun TypeConstructorMarker.isCapturedTypeConstructor(): Boolean {
        return this is ConeCapturedTypeConstructor
    }

    override fun KotlinTypeMarker.removeExactAnnotation(): KotlinTypeMarker {
        // TODO
        return this
    }

    override fun TypeConstructorMarker.toErrorType(): SimpleTypeMarker {
        require(this is ErrorTypeConstructor)
        return ConeClassErrorType(reason)
    }

    override fun findCommonIntegerLiteralTypesSuperType(explicitSupertypes: List<SimpleTypeMarker>): SimpleTypeMarker? {
        return ConeIntegerLiteralTypeImpl.findCommonSuperType(explicitSupertypes)
    }

    override fun TypeConstructorMarker.getApproximatedIntegerLiteralType(): KotlinTypeMarker {
        require(this is ConeIntegerLiteralType)
        return this.getApproximatedType()
    }

    override fun createTypeWithAlternativeForIntersectionResult(
        firstCandidate: KotlinTypeMarker,
        secondCandidate: KotlinTypeMarker
    ): KotlinTypeMarker {
        TODO()
    }

    override fun TypeConstructorMarker.isIntegerLiteralTypeConstructor(): Boolean {
        return this is ConeIntegerLiteralType
    }

    override fun SimpleTypeMarker.possibleIntegerTypes(): Collection<KotlinTypeMarker> {
        return (this as? ConeIntegerLiteralType)?.possibleTypes ?: emptyList()
    }

    override fun SimpleTypeMarker.fastCorrespondingSupertypes(constructor: TypeConstructorMarker): List<SimpleTypeMarker>? {
        require(this is ConeKotlinType)
        return if (this is ConeIntegerLiteralType) {
            supertypes
        } else {
            session.correspondingSupertypesCache.getCorrespondingSupertypes(this, constructor)
        }
    }

    override fun SimpleTypeMarker.isIntegerLiteralType(): Boolean {
        return this is ConeIntegerLiteralType
    }

    override fun KotlinTypeMarker.asSimpleType(): SimpleTypeMarker? {
        assert(this is ConeKotlinType)
        return when (this) {
            is ConeClassLikeType -> fullyExpandedType(session)
            is ConeSimpleKotlinType -> this
            is ConeFlexibleType -> null
            else -> error("Unknown simpleType: $this")
        }
    }

    override fun KotlinTypeMarker.asFlexibleType(): FlexibleTypeMarker? {
        assert(this is ConeKotlinType)
        return this as? ConeFlexibleType
    }

    override fun KotlinTypeMarker.isError(): Boolean {
        assert(this is ConeKotlinType)
        return this is ConeClassErrorType || this is ConeKotlinErrorType || this.typeConstructor() is ErrorTypeConstructor
    }

    override fun KotlinTypeMarker.isUninferredParameter(): Boolean {
        assert(this is ConeKotlinType)
        return false // TODO
    }

    override fun FlexibleTypeMarker.asDynamicType(): DynamicTypeMarker? {
        assert(this is ConeKotlinType)
        return null // TODO
    }

    override fun FlexibleTypeMarker.asRawType(): RawTypeMarker? {
        require(this is ConeFlexibleType)
        return this as? ConeRawType
    }

    override fun FlexibleTypeMarker.upperBound(): SimpleTypeMarker {
        require(this is ConeFlexibleType)
        return this.upperBound as SimpleTypeMarker
    }

    override fun FlexibleTypeMarker.lowerBound(): SimpleTypeMarker {
        require(this is ConeFlexibleType)
        return this.lowerBound as SimpleTypeMarker
    }

    override fun SimpleTypeMarker.asCapturedType(): CapturedTypeMarker? {
        //require(this is ConeLookupTagBasedType)
        return this as? ConeCapturedType
    }

    override fun SimpleTypeMarker.asDefinitelyNotNullType(): DefinitelyNotNullTypeMarker? {
        require(this is ConeKotlinType)
        return this as? ConeDefinitelyNotNullType
    }

    override fun SimpleTypeMarker.isMarkedNullable(): Boolean {
        require(this is ConeKotlinType)
        return this.nullability.isNullable
    }

    override fun SimpleTypeMarker.withNullability(nullable: Boolean): SimpleTypeMarker {
        require(this is ConeKotlinType)
        return withNullability(ConeNullability.create(nullable), this@ConeTypeContext)
    }

    override fun SimpleTypeMarker.typeConstructor(): TypeConstructorMarker {
        return when (this) {
            is ConeClassLikeType -> lookupTag.toSymbol(session)
                ?: ErrorTypeConstructor("Unresolved: $lookupTag")
            is ConeTypeParameterType -> lookupTag.typeParameterSymbol
            is ConeCapturedType -> constructor
            is ConeTypeVariableType -> lookupTag as ConeTypeVariableTypeConstructor // TODO: WTF
            is ConeIntersectionType -> this
            is ConeStubType -> variable.typeConstructor
            is ConeDefinitelyNotNullType -> original.typeConstructor()
            is ConeIntegerLiteralType -> this
            else -> error("?: $this")
        }
    }

    override fun CapturedTypeMarker.typeConstructor(): CapturedTypeConstructorMarker {
        require(this is ConeCapturedType)
        return this.constructor
    }

    override fun CapturedTypeConstructorMarker.projection(): TypeArgumentMarker {
        require(this is ConeCapturedTypeConstructor)
        return this.projection
    }

    override fun KotlinTypeMarker.argumentsCount(): Int {
        require(this is ConeKotlinType)

        return this.typeArguments.size
    }

    override fun KotlinTypeMarker.getArgument(index: Int): TypeArgumentMarker {
        require(this is ConeKotlinType)

        return this.typeArguments.getOrNull(index)
            ?: session.builtinTypes.anyType.type//StandardClassIds.Any(session.firSymbolProvider).constructType(emptyArray(), false) // TODO wtf
    }

    override fun KotlinTypeMarker.asTypeArgument(): TypeArgumentMarker {
        require(this is ConeKotlinType)

        return this
    }

    override fun CapturedTypeMarker.lowerType(): KotlinTypeMarker? {
        require(this is ConeCapturedType)
        return this.lowerType
    }

    override fun TypeArgumentMarker.isStarProjection(): Boolean {
        require(this is ConeTypeProjection)
        return this is ConeStarProjection
    }

    override fun TypeArgumentMarker.getVariance(): TypeVariance {
        require(this is ConeTypeProjection)

        return when (this.kind) {
            ProjectionKind.STAR -> error("Nekorrektno (c) Stas")
            ProjectionKind.IN -> TypeVariance.IN
            ProjectionKind.OUT -> TypeVariance.OUT
            ProjectionKind.INVARIANT -> TypeVariance.INV
        }
    }

    override fun TypeArgumentMarker.getType(): KotlinTypeMarker {
        require(this is ConeTypeProjection)
        require(this is ConeKotlinTypeProjection) { "No type for StarProjection" }
        return this.type
    }

    override fun TypeConstructorMarker.parametersCount(): Int {
        //require(this is ConeSymbol)
        return when (this) {
            is FirTypeParameterSymbol,
            is FirAnonymousObjectSymbol,
            is ConeCapturedTypeConstructor,
            is ErrorTypeConstructor,
            is ConeTypeVariableTypeConstructor,
            is ConeIntersectionType -> 0
            is FirRegularClassSymbol -> fir.typeParameters.size
            is FirTypeAliasSymbol -> fir.typeParameters.size
            is ConeIntegerLiteralType -> 0
            else -> error("?!:10")
        }
    }

    override fun TypeConstructorMarker.getParameter(index: Int): TypeParameterMarker {
        //require(this is ConeSymbol)
        return when (this) {
            is FirTypeParameterSymbol -> error("?!:11")
            is FirRegularClassSymbol -> fir.typeParameters[index].symbol
            is FirTypeAliasSymbol -> fir.typeParameters[index].symbol
            else -> error("?!:12")
        }
    }

    override fun TypeConstructorMarker.supertypes(): Collection<KotlinTypeMarker> {
        if (this is ErrorTypeConstructor) return emptyList()
        //require(this is ConeSymbol)
        return when (this) {
            is ConeTypeVariableTypeConstructor -> emptyList()
            is FirTypeParameterSymbol -> fir.bounds.map { it.coneTypeUnsafe() }
            is FirClassSymbol<*> -> fir.superConeTypes
            is FirTypeAliasSymbol -> listOfNotNull(fir.expandedConeType)
            is ConeCapturedTypeConstructor -> supertypes!!
            is ConeIntersectionType -> intersectedTypes
            is ConeIntegerLiteralType -> supertypes
            else -> error("?!:13")
        }
    }

    override fun TypeConstructorMarker.isIntersection(): Boolean {
        return this is ConeIntersectionType
    }

    override fun TypeConstructorMarker.isClassTypeConstructor(): Boolean {
        //assert(this is ConeSymbol)
        return this is FirClassSymbol<*>
    }

    override fun TypeParameterMarker.getVariance(): TypeVariance {
        require(this is FirTypeParameterSymbol)
        return this.fir.variance.convertVariance()
    }

    override fun TypeParameterMarker.upperBoundCount(): Int {
        require(this is FirTypeParameterSymbol)
        return this.fir.bounds.size
    }

    override fun TypeParameterMarker.getUpperBound(index: Int): KotlinTypeMarker {
        require(this is FirTypeParameterSymbol)
        return this.fir.bounds[index].coneTypeUnsafe()
    }

    override fun TypeParameterMarker.getTypeConstructor(): TypeConstructorMarker {
        require(this is FirTypeParameterSymbol)
        return this
    }

    override fun isEqualTypeConstructors(c1: TypeConstructorMarker, c2: TypeConstructorMarker): Boolean {
        if (c1 is ErrorTypeConstructor || c2 is ErrorTypeConstructor) return false

        //assert(c1 is ConeSymbol)
        //assert(c2 is ConeSymbol)
        return c1 == c2
    }

    override fun TypeConstructorMarker.isDenotable(): Boolean {
        //TODO
        return when (this) {
            is ConeCapturedTypeConstructor,
            is ConeTypeVariableTypeConstructor,
            is ConeIntersectionType,
            is ConeIntegerLiteralType -> false
            is AbstractFirBasedSymbol<*> -> true
            else -> true
        }
    }

    override fun TypeConstructorMarker.isCommonFinalClassConstructor(): Boolean {
        if (this is FirAnonymousObjectSymbol) return true
        val classSymbol = this as? FirRegularClassSymbol ?: return false
        val fir = classSymbol.fir
        return fir.modality == Modality.FINAL &&
                fir.classKind != ClassKind.ENUM_ENTRY &&
                fir.classKind != ClassKind.ANNOTATION_CLASS
    }

    override fun captureFromArguments(type: SimpleTypeMarker, status: CaptureStatus): SimpleTypeMarker? {
        require(type is ConeKotlinType)
        val argumentsCount = type.typeArguments.size
        if (argumentsCount == 0) return null

        val typeConstructor = type.typeConstructor()
        if (argumentsCount != typeConstructor.parametersCount()) return null

        if (type.typeArguments.all { it !is ConeStarProjection && it.kind == ProjectionKind.INVARIANT }) return null

        val newArguments = Array(argumentsCount) { index ->
            val argument = type.typeArguments[index]
            if (argument !is ConeStarProjection && argument.kind == ProjectionKind.INVARIANT) return@Array argument

            val lowerType = if (argument !is ConeStarProjection && argument.getVariance() == TypeVariance.IN) {
                (argument as ConeKotlinTypeProjection).type
            } else {
                null
            }

            ConeCapturedType(status, lowerType, argument, typeConstructor.getParameter(index))
        }

        for (index in 0 until argumentsCount) {
            val oldArgument = type.typeArguments[index]
            val newArgument = newArguments[index]

            if (oldArgument !is ConeStarProjection && oldArgument.kind == ProjectionKind.INVARIANT) continue

            val parameter = typeConstructor.getParameter(index)
            val upperBounds = (0 until parameter.upperBoundCount()).mapTo(mutableListOf()) { paramIndex ->
                parameter.getUpperBound(paramIndex) // TODO: substitution
            }

            if (!oldArgument.isStarProjection() && oldArgument.getVariance() == TypeVariance.OUT) {
                upperBounds += oldArgument.getType()
            }

            require(newArgument is ConeCapturedType)
            newArgument.constructor.supertypes = upperBounds as List<ConeKotlinType>
        }

        return type.withArguments(newArguments)
    }

    override fun SimpleTypeMarker.asArgumentList(): TypeArgumentListMarker {
        require(this is ConeKotlinType)
        return this
    }

    override fun identicalArguments(a: SimpleTypeMarker, b: SimpleTypeMarker): Boolean {
        require(a is ConeKotlinType)
        require(b is ConeKotlinType)
        return a.typeArguments === b.typeArguments
    }

    override fun TypeConstructorMarker.isAnyConstructor(): Boolean {
        return this is FirClassLikeSymbol<*> && classId == StandardClassIds.Any
    }

    override fun TypeConstructorMarker.isNothingConstructor(): Boolean {
        return this is FirClassLikeSymbol<*> && classId == StandardClassIds.Nothing
    }

    override fun SimpleTypeMarker.isSingleClassifierType(): Boolean {
        if (isError()) return false
        if (this is ConeCapturedType) return true
        if (this is ConeTypeVariableType) return false
        if (this is ConeIntersectionType) return false
        if (this is ConeIntegerLiteralType) return true
        if (this is ConeStubType) return true
        if (this is ConeDefinitelyNotNullType) return true
        require(this is ConeLookupTagBasedType)
        val typeConstructor = this.typeConstructor()
        return typeConstructor is FirClassSymbol<*> ||
                typeConstructor is FirTypeParameterSymbol
    }

    override fun SimpleTypeMarker.isPrimitiveType(): Boolean {
        return false //TODO
    }

    override fun SimpleTypeMarker.isStubType(): Boolean {
        return this is StubTypeMarker
    }

    override fun intersectTypes(types: List<SimpleTypeMarker>): SimpleTypeMarker {
        @Suppress("UNCHECKED_CAST")
        return ConeTypeIntersector.intersectTypes(this, types as List<ConeKotlinType>) as SimpleTypeMarker
    }

    override fun intersectTypes(types: List<KotlinTypeMarker>): KotlinTypeMarker {
        @Suppress("UNCHECKED_CAST")
        return ConeTypeIntersector.intersectTypes(this, types as List<ConeKotlinType>)
    }

    override fun prepareType(type: KotlinTypeMarker): KotlinTypeMarker {
        return when (type) {
            is ConeClassLikeType -> type.fullyExpandedType(session)
            is ConeFlexibleType -> {
                val lowerBound = prepareType(type.lowerBound)
                if (lowerBound === type.lowerBound) return type

                ConeFlexibleType(
                    lowerBound as ConeKotlinType,
                    prepareType(type.upperBound) as ConeKotlinType
                )
            }
            else -> type
        }
    }

    override fun KotlinTypeMarker.isNullableType(): Boolean {
        require(this is ConeKotlinType)
        if (this.isMarkedNullable)
            return true

        if (this is ConeFlexibleType && this.upperBound.isNullableType())
            return true

        if (this is ConeTypeParameterType /* || is TypeVariable */)
            return hasNullableSuperType(type)

        if (this is ConeIntersectionType && intersectedTypes.any { it.isNullableType() }) {
            return true
        }

        return false
    }

    private fun TypeConstructorMarker.toFirRegularClass(): FirRegularClass? {
        if (this !is FirClassLikeSymbol<*>) return null
        return fir as? FirRegularClass
    }

    override fun arrayType(componentType: KotlinTypeMarker): SimpleTypeMarker = TODO("not implemented")

    override fun KotlinTypeMarker.isArrayOrNullableArray(): Boolean = TODO("not implemented")

    override fun TypeConstructorMarker.isFinalClassOrEnumEntryOrAnnotationClassConstructor(): Boolean {
        val firRegularClass = toFirRegularClass() ?: return false

        return firRegularClass.modality == Modality.FINAL ||
                firRegularClass.classKind == ClassKind.ENUM_ENTRY ||
                firRegularClass.classKind == ClassKind.ANNOTATION_CLASS
    }

    override fun KotlinTypeMarker.hasAnnotation(fqName: FqName): Boolean {
        // TODO support annotations
        return false
    }

    override fun KotlinTypeMarker.getAnnotationFirstArgumentValue(fqName: FqName): Any? {
        // TODO support annotations
        return null
    }

    override fun TypeConstructorMarker.getTypeParameterClassifier(): TypeParameterMarker? {
        return this as? FirTypeParameterSymbol
    }

    override fun TypeConstructorMarker.isInlineClass(): Boolean {
        return toFirRegularClass()?.isInline == true
    }

    override fun TypeConstructorMarker.isInnerClass(): Boolean {
        return toFirRegularClass()?.isInner == true
    }

    override fun TypeParameterMarker.getRepresentativeUpperBound(): KotlinTypeMarker {
        require(this is FirTypeParameterSymbol)
        return this.fir.bounds.getOrNull(0)?.let { (it as? FirResolvedTypeRef)?.type }
            ?: session.builtinTypes.nullableAnyType.type
    }

    override fun KotlinTypeMarker.getSubstitutedUnderlyingType(): KotlinTypeMarker? {
        // TODO: support inline classes
        return null
    }

    override fun TypeConstructorMarker.getPrimitiveType() =
        getClassFqNameUnsafe()?.let(KotlinBuiltIns.FQ_NAMES.fqNameToPrimitiveType::get)

    override fun TypeConstructorMarker.getPrimitiveArrayType() =
        getClassFqNameUnsafe()?.let(KotlinBuiltIns.FQ_NAMES.arrayClassFqNameToPrimitiveType::get)

    override fun TypeConstructorMarker.isUnderKotlinPackage() =
        getClassFqNameUnsafe()?.startsWith(Name.identifier("kotlin")) == true

    override fun TypeConstructorMarker.getClassFqNameUnsafe(): FqNameUnsafe? {
        if (this !is FirClassLikeSymbol<*>) return null
        return toLookupTag().classId.asSingleFqName().toUnsafe()
    }

    override fun TypeParameterMarker.getName() = (this as FirTypeParameterSymbol).name

    override fun TypeParameterMarker.isReified(): Boolean = TODO("not implemented")

    override fun KotlinTypeMarker.isInterfaceOrAnnotationClass(): Boolean {
        val classKind = typeConstructor().toFirRegularClass()?.classKind ?: return false
        return classKind == ClassKind.ANNOTATION_CLASS || classKind == ClassKind.INTERFACE
    }

    override fun TypeConstructorMarker.isError(): Boolean {
        return this is ErrorTypeConstructor
    }
}
