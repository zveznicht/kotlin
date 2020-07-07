/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.psi2ir.generators

import org.jetbrains.kotlin.backend.common.DataClassMethodGenerator
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.incremental.components.NoLookupLocation
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.DataClassMembersGenerator
import org.jetbrains.kotlin.ir.util.declareSimpleFunctionWithOverrides
import org.jetbrains.kotlin.ir.util.properties
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.psiUtil.endOffset
import org.jetbrains.kotlin.psi.psiUtil.startOffset
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.DescriptorToSourceUtils
import org.jetbrains.kotlin.resolve.scopes.MemberScope
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.isNullable
import org.jetbrains.kotlin.types.typeUtil.representativeUpperBound


/**
 * A generator that generates synthetic members of data class as well as part of inline class.
 *
 * This one uses [DataClassMethodGenerator] to determine which members are needed to generate; uses [DataClassMembersGenerator] to generate
 * function bodies; and provides ways to declare functions or parameters based on descriptors and binding context.
 */
class DataClassMembersGenerator(
    declarationGenerator: DeclarationGenerator
) : DeclarationGeneratorExtension(declarationGenerator) {

    fun generateInlineClassMembers(ktClassOrObject: KtClassOrObject, irClass: IrClass) {
        MyDataClassMethodGenerator(ktClassOrObject, irClass, IrDeclarationOrigin.GENERATED_INLINE_CLASS_MEMBER).generate()
    }

    fun generateDataClassMembers(ktClassOrObject: KtClassOrObject, irClass: IrClass) {
        MyDataClassMethodGenerator(ktClassOrObject, irClass, IrDeclarationOrigin.GENERATED_DATA_CLASS_MEMBER).generate()
    }

    fun IrMemberAccessExpression.commitSubstituted(descriptor: CallableDescriptor) = context.run { commitSubstituted(descriptor) }

    private fun declareSimpleFunction(startOffset: Int, endOffset: Int, origin: IrDeclarationOrigin, function: FunctionDescriptor) =
        context.symbolTable.declareSimpleFunctionWithOverrides(
            startOffset, endOffset, origin,
            function
        ).apply {
            returnType = function.returnType!!.toIrType()
        }

    private inner class MyDataClassMethodGenerator(
        ktClassOrObject: KtClassOrObject,
        val irClass: IrClass,
        val origin: IrDeclarationOrigin
    ) : DataClassMethodGenerator(ktClassOrObject, declarationGenerator.context.bindingContext) {

        private val irDataClassMembersGenerator =
            object : DataClassMembersGenerator<FunctionDescriptor, PropertyDescriptor, ValueParameterDescriptor>(context, context.symbolTable, irClass, origin) {
                override fun declareSimpleFunction(startOffset: Int, endOffset: Int, functionDescriptor: FunctionDescriptor): IrFunction =
                    declareSimpleFunction(startOffset, endOffset, origin, functionDescriptor)

                override fun generateSyntheticFunctionParameterDeclarations(irFunction: IrFunction) {
                    FunctionGenerator(declarationGenerator).generateSyntheticFunctionParameterDeclarations(irFunction)
                }

                override fun transform(typeParameterDescriptor: TypeParameterDescriptor): IrType =
                    typeParameterDescriptor.defaultType.toIrType()

                override fun commitSubstituted(irMemberAccessExpression: IrMemberAccessExpression, descriptor: CallableDescriptor) {
                    irMemberAccessExpression.commitSubstituted(descriptor)
                }

                override fun PropertyDescriptor.isNullable(): Boolean = type.isNullable()

                override fun PropertyDescriptor.getName(): Name = name

                override fun PropertyDescriptor.getHashCodeFunction(recordSubstituted: (FunctionDescriptor) -> Unit): IrSimpleFunctionSymbol =
                    getHashCodeFunction(type, recordSubstituted)

                private fun MemberScope.findHashCodeFunctionOrNull() =
                    getContributedFunctions(Name.identifier("hashCode"), NoLookupLocation.FROM_BACKEND)
                        .find { it.valueParameters.isEmpty() }

                private fun getHashCodeFunction(type: KotlinType): FunctionDescriptor =
                    type.memberScope.findHashCodeFunctionOrNull()
                        ?: context.builtIns.any.unsubstitutedMemberScope.findHashCodeFunctionOrNull()!!

                private fun getHashCodeFunction(
                    type: KotlinType,
                    recordSubstituted: (FunctionDescriptor) -> Unit
                ): IrSimpleFunctionSymbol =
                    when (val typeConstructorDescriptor = type.constructor.declarationDescriptor) {
                        is ClassDescriptor ->
                            if (KotlinBuiltIns.isArrayOrPrimitiveArray(typeConstructorDescriptor))
                                context.irBuiltIns.dataClassArrayMemberHashCodeSymbol
                            else {
                                val substituted = getHashCodeFunction(type)
                                recordSubstituted(substituted)
                                symbolTable.referenceSimpleFunction(substituted.original)
                            }

                        is TypeParameterDescriptor ->
                            getHashCodeFunction(typeConstructorDescriptor.representativeUpperBound, recordSubstituted)

                        else ->
                            throw AssertionError("Unexpected type: $type")
                    }

                override fun PropertyDescriptor.getIrBackingField(): IrField =
                    irClass.properties.single { it.initialDescriptor == this }.backingField!!

                override fun PropertyDescriptor.isArrayOrPrimitiveArray(): Boolean {
                    val typeConstructorDescriptor = type.constructor.declarationDescriptor
                    return typeConstructorDescriptor is ClassDescriptor &&
                            KotlinBuiltIns.isArrayOrPrimitiveArray(typeConstructorDescriptor)
                }

                // Build a member from a descriptor (psi2ir) as well as its body.
                override fun buildMember(
                    function: FunctionDescriptor,
                    startOffset: Int,
                    endOffset: Int,
                    body: MemberFunctionBuilder.(IrFunction) -> Unit
                ) {
                    MemberFunctionBuilder(startOffset, endOffset, declareSimpleFunction(startOffset, endOffset, function)).addToClass { irFunction ->
                        irFunction.buildWithScope {
                            generateSyntheticFunctionParameterDeclarations(irFunction)
                            body(irFunction)
                        }
                    }
                }

                override fun ValueParameterDescriptor.getParameterBackingField(): IrField {
                    val property = getOrFail(BindingContext.VALUE_PARAMETER_AS_PROPERTY, this)
                    return property.getIrBackingField()
                }
            }

        override fun generateComponentFunction(function: FunctionDescriptor, parameter: ValueParameterDescriptor) {
            if (!irClass.isData) return

            val ktParameter = DescriptorToSourceUtils.descriptorToDeclaration(parameter)
                ?: throw AssertionError("No definition for data class constructor parameter $parameter")

            with(irDataClassMembersGenerator) {
                val backingField = parameter.getParameterBackingField()
                generateComponentFunction(function, backingField, ktParameter.startOffset, ktParameter.endOffset)
            }
        }

        override fun generateCopyFunction(function: FunctionDescriptor, constructorParameters: List<KtParameter>) {
            if (!irClass.isData) return

            val dataClassConstructor = classDescriptor.unsubstitutedPrimaryConstructor
                ?: throw AssertionError("Data class should have a primary constructor: $classDescriptor")
            val constructorSymbol = context.symbolTable.referenceConstructor(dataClassConstructor)

            irDataClassMembersGenerator.generateCopyFunction(function, constructorSymbol)
        }

        override fun generateEqualsMethod(function: FunctionDescriptor, properties: List<PropertyDescriptor>) =
            irDataClassMembersGenerator.generateEqualsMethod(function, properties)

        override fun generateHashCodeMethod(function: FunctionDescriptor, properties: List<PropertyDescriptor>) =
            irDataClassMembersGenerator.generateHashCodeMethod(function, properties)

        override fun generateToStringMethod(function: FunctionDescriptor, properties: List<PropertyDescriptor>) =
            irDataClassMembersGenerator.generateToStringMethod(function, properties)
    }
}
