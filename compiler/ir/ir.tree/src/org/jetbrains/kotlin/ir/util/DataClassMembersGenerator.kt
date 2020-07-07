/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.util

import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.builders.*
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.expressions.impl.IrGetValueImpl
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.types.checker.KotlinTypeChecker

/**
 * A platform-, frontend-independent logic for generating synthetic members of data class: equals, hashCode, toString, componentN, and copy.
 * Different front-ends may need to define how to declare functions, parameters, etc., or simply provide predefined ones.
 *
 * Generating synthetic members of inline class can use this as well, in particular, members from Any: equals, hashCode, and toString.
 */
@OptIn(ObsoleteDescriptorBasedAPI::class)
abstract class DataClassMembersGenerator<Function, Property, ValueParameter>(
    val context: IrGeneratorContext,
    val symbolTable: SymbolTable,
    val irClass: IrClass,
    val origin: IrDeclarationOrigin
) {

    inline fun <T : IrDeclaration> T.buildWithScope(builder: (T) -> Unit): T =
        also { irDeclaration ->
            symbolTable.withScope(irDeclaration) {
                builder(irDeclaration)
            }
        }

    protected inner class MemberFunctionBuilder(
        startOffset: Int = UNDEFINED_OFFSET,
        endOffset: Int = UNDEFINED_OFFSET,
        val irFunction: IrFunction
    ) : IrBlockBodyBuilder(context, Scope(irFunction.symbol), startOffset, endOffset) {
        fun addToClass(builder: MemberFunctionBuilder.(IrFunction) -> Unit): IrFunction {
            build(builder)
            irClass.declarations.add(irFunction)
            return irFunction
        }

        fun build(builder: MemberFunctionBuilder.(IrFunction) -> Unit) {
            irFunction.buildWithScope {
                builder(irFunction)
                irFunction.body = doBuild()
            }
        }

        fun irThis(): IrExpression {
            val irDispatchReceiverParameter = irFunction.dispatchReceiverParameter!!
            return IrGetValueImpl(
                startOffset, endOffset,
                irDispatchReceiverParameter.type,
                irDispatchReceiverParameter.symbol
            )
        }

        fun irOther(): IrExpression {
            val irFirstParameter = irFunction.valueParameters[0]
            return IrGetValueImpl(
                startOffset, endOffset,
                irFirstParameter.type,
                irFirstParameter.symbol
            )
        }

        fun generateComponentFunction(irField: IrField) {
            +irReturn(irGetField(irThis(), irField))
        }

        fun generateCopyFunction(constructorSymbol: IrConstructorSymbol) {
            +irReturn(
                irCall(
                    constructorSymbol,
                    irClass.defaultType,
                    constructedClass = irClass
                ).apply {
                    mapTypeParameters(::transform)
                    mapValueParameters {
                        val irValueParameter = irFunction.valueParameters[it.index]
                        irGet(irValueParameter.type, irValueParameter.symbol)
                    }
                }
            )
        }

        fun generateEqualsMethodBody(properties: List<Property>) {
            val irType = irClass.defaultType

            if (!irClass.isInline) {
                +irIfThenReturnTrue(irEqeqeq(irThis(), irOther()))
            }
            +irIfThenReturnFalse(irNotIs(irOther(), irType))
            val otherWithCast = irTemporary(irAs(irOther(), irType), "other_with_cast")
            for (property in properties) {
                val field = property.getIrBackingField()
                val arg1 = irGetField(irThis(), field)
                val arg2 = irGetField(irGet(irType, otherWithCast.symbol), field)
                +irIfThenReturnFalse(irNotEquals(arg1, arg2))
            }
            +irReturnTrue()
        }

        private val intClass = context.builtIns.int
        private val intType = context.builtIns.intType

        private val intTimesSymbol: IrSimpleFunctionSymbol =
            intClass.findFirstFunction("times") { KotlinTypeChecker.DEFAULT.equalTypes(it.valueParameters[0].type, intType) }
                .let { symbolTable.referenceSimpleFunction(it) }

        private val intPlusSymbol: IrSimpleFunctionSymbol =
            intClass.findFirstFunction("plus") { KotlinTypeChecker.DEFAULT.equalTypes(it.valueParameters[0].type, intType) }
                .let { symbolTable.referenceSimpleFunction(it) }

        fun generateHashCodeMethodBody(properties: List<Property>) {
            val irIntType = context.irBuiltIns.intType
            var result: IrExpression? = null
            for (property in properties) {
                val hashCodeOfProperty = getHashCodeOfProperty(property)
                result = if (result == null) {
                    hashCodeOfProperty
                } else {
                    val shiftedResult = irCallOp(intTimesSymbol, irIntType, result, irInt(31))
                    irCallOp(intPlusSymbol, irIntType, shiftedResult, hashCodeOfProperty)
                }
            }
            +irReturn(result ?: irInt(0))
        }

        private fun getHashCodeOfProperty(property: Property): IrExpression {
            val field = property.getIrBackingField()
            return when {
                property.isNullable() ->
                    irIfNull(
                        context.irBuiltIns.intType,
                        irGetField(irThis(), field),
                        irInt(0),
                        getHashCodeOf(property, irGetField(irThis(), field))
                    )
                else ->
                    getHashCodeOf(property, irGetField(irThis(), field))
            }
        }

        private fun getHashCodeOf(property: Property, irValue: IrExpression): IrExpression {
            var substituted: FunctionDescriptor? = null
            val hashCodeFunctionSymbol = property.getHashCodeFunction {
                substituted = it
            }

            val hasDispatchReceiver = hashCodeFunctionSymbol.descriptor.dispatchReceiverParameter != null
            return irCall(
                hashCodeFunctionSymbol,
                context.irBuiltIns.intType,
                valueArgumentsCount = if (hasDispatchReceiver) 0 else 1,
                typeArgumentsCount = 0
            ).apply {
                if (hasDispatchReceiver) {
                    dispatchReceiver = irValue
                } else {
                    putValueArgument(0, irValue)
                }
                commitSubstituted(this, substituted ?: hashCodeFunctionSymbol.descriptor)
            }
        }

        fun generateToStringMethodBody(properties: List<Property>) {
            val irConcat = irConcat()
            irConcat.addArgument(irString(irClass.name.asString() + "("))
            var first = true
            for (property in properties) {
                if (!first) irConcat.addArgument(irString(", "))

                irConcat.addArgument(irString(property.getName().asString() + "="))

                val irPropertyValue = irGetField(irThis(), property.getIrBackingField())

                val irPropertyStringValue =
                    if (property.isArrayOrPrimitiveArray())
                        irCall(context.irBuiltIns.dataClassArrayMemberToStringSymbol, context.irBuiltIns.stringType).apply {
                            putValueArgument(0, irPropertyValue)
                        }
                    else
                        irPropertyValue

                irConcat.addArgument(irPropertyStringValue)
                first = false
            }
            irConcat.addArgument(irString(")"))
            +irReturn(irConcat)
        }
    }

    abstract fun declareSimpleFunction(startOffset: Int, endOffset: Int, functionDescriptor: FunctionDescriptor): IrFunction

    abstract fun generateSyntheticFunctionParameterDeclarations(irFunction: IrFunction)

    protected fun IrValueParameter.getBackingField(): IrField =
        irClass.properties.single { irProperty ->
            irProperty.name == this.name && irProperty.backingField?.type == this.type
        }.backingField!!

    fun generateComponentFunction(function: Function, irField: IrField, startOffset: Int, endOffset: Int) {
        buildMember(function, startOffset, endOffset) {
            generateComponentFunction(irField)
        }
    }

    abstract fun transform(typeParameterDescriptor: TypeParameterDescriptor): IrType

    fun generateCopyFunction(function: Function, constructorSymbol: IrConstructorSymbol) {
        buildMember(function, irClass.startOffset, irClass.endOffset) {
            irFunction.valueParameters.forEach { irValueParameter ->
                irValueParameter.defaultValue = irExprBody(irGetField(irThis(), irValueParameter.getBackingField()))
            }
            generateCopyFunction(constructorSymbol)
        }
    }

    fun generateEqualsMethod(function: Function, properties: List<Property>) {
        buildMember(function, irClass.startOffset, irClass.endOffset) {
            generateEqualsMethodBody(properties)
        }
    }

    abstract fun commitSubstituted(irMemberAccessExpression: IrMemberAccessExpression<*>, descriptor: CallableDescriptor)

    fun generateHashCodeMethod(function: Function, properties: List<Property>) {
        buildMember(function, irClass.startOffset, irClass.endOffset) {
            generateHashCodeMethodBody(properties)
        }
    }

    fun generateToStringMethod(function: Function, properties: List<Property>) {
        buildMember(function, irClass.startOffset, irClass.endOffset) {
            generateToStringMethodBody(properties)
        }
    }

    abstract fun Property.isNullable(): Boolean
    abstract fun Property.getName(): Name
    abstract fun Property.getHashCodeFunction(recordSubstituted: (FunctionDescriptor) -> Unit): IrSimpleFunctionSymbol
    abstract fun Property.getIrBackingField(): IrField
    abstract fun Property.isArrayOrPrimitiveArray(): Boolean

    protected abstract fun buildMember(
        function: Function,
        startOffset: Int,
        endOffset: Int,
        body: MemberFunctionBuilder.(IrFunction) -> Unit
    )

    abstract fun ValueParameter.getParameterBackingField(): IrField
}