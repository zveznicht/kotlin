/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.types

import org.jetbrains.kotlin.ir.descriptors.IrBuiltIns
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.types.AbstractTypeCheckerContext
import org.jetbrains.kotlin.types.model.KotlinTypeMarker
import org.jetbrains.kotlin.types.model.SimpleTypeMarker
import org.jetbrains.kotlin.types.model.TypeConstructorMarker

class IrTypeCheckerContext(override val baseContext: IrTypeSystemContext) : AbstractTypeCheckerContext(baseContext) {
    constructor(irBuiltIns: IrBuiltIns) : this(IrTypeSystemContext(irBuiltIns))

    private val irBuiltIns: IrBuiltIns = baseContext.irBuiltIns

    override fun substitutionSupertypePolicy(type: SimpleTypeMarker): SupertypesPolicy.DoCustomTransform {
        require(type is IrSimpleType)
        val parameters = extractTypeParameters((type.classifier as IrClassSymbol).owner).map { it.symbol }
        val typeSubstitutor = IrTypeSubstitutor(parameters, type.arguments, irBuiltIns)

        return object : SupertypesPolicy.DoCustomTransform() {
            override fun transformType(context: AbstractTypeCheckerContext, type: KotlinTypeMarker): SimpleTypeMarker {
                require(type is IrType)
                return typeSubstitutor.substitute(type) as IrSimpleType
            }
        }
    }

    override fun areEqualTypeConstructors(a: TypeConstructorMarker, b: TypeConstructorMarker) = baseContext.isEqualTypeConstructors(a, b)

    override val isErrorTypeEqualsToAnything get() = false
    override val isStubTypeEqualsToAnything get() = false

    override val KotlinTypeMarker.isAllowedTypeVariable: Boolean
        get() = false
}
