/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.jvm.descriptors

import org.jetbrains.kotlin.backend.common.descriptors.KnownClassDescriptor
import org.jetbrains.kotlin.backend.common.descriptors.KnownPackageFragmentDescriptor
import org.jetbrains.kotlin.backend.common.descriptors.SharedVariablesManager
import org.jetbrains.kotlin.backend.jvm.JvmLoweredDeclarationOrigin
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.builtins.PrimitiveType
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.descriptors.impl.ClassConstructorDescriptorImpl
import org.jetbrains.kotlin.descriptors.impl.LocalVariableDescriptor
import org.jetbrains.kotlin.descriptors.impl.PropertyDescriptorImpl
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrConstructor
import org.jetbrains.kotlin.ir.declarations.IrField
import org.jetbrains.kotlin.ir.declarations.IrVariable
import org.jetbrains.kotlin.ir.declarations.impl.IrConstructorImpl
import org.jetbrains.kotlin.ir.declarations.impl.IrFieldImpl
import org.jetbrains.kotlin.ir.declarations.impl.IrVariableImpl
import org.jetbrains.kotlin.ir.descriptors.IrBuiltIns
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrGetValue
import org.jetbrains.kotlin.ir.expressions.IrSetVariable
import org.jetbrains.kotlin.ir.expressions.impl.*
import org.jetbrains.kotlin.ir.symbols.IrVariableSymbol
import org.jetbrains.kotlin.ir.types.toIrType
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.scopes.MemberScope
import org.jetbrains.kotlin.types.*

class JvmSharedVariablesManager(
    val builtIns: KotlinBuiltIns,
    val irBuiltIns: IrBuiltIns
) : SharedVariablesManager {
    private val kotlinJvmInternalPackage = KnownPackageFragmentDescriptor(builtIns.builtInsModule, FqName("kotlin.jvm.internal"))
    private val refNamespaceClass =
        KnownClassDescriptor.createClass(Name.identifier("Ref"), kotlinJvmInternalPackage, listOf(builtIns.anyType))

    private class PrimitiveRefDescriptorsProvider(type: KotlinType, refClass: ClassDescriptor) {
        val refType: KotlinType = refClass.defaultType

        val refConstructorDescriptor: ClassConstructorDescriptor =
            ClassConstructorDescriptorImpl.create(refClass, Annotations.EMPTY, true, SourceElement.NO_SOURCE).apply {
                initialize(emptyList(), Visibilities.PUBLIC, emptyList())
                returnType = refType
            }

        val refConstructor: IrConstructor = IrConstructorImpl(
            UNDEFINED_OFFSET,
            UNDEFINED_OFFSET,
            JvmLoweredDeclarationOrigin.REF_FOR_SHARED_VARIABLE,
            refConstructorDescriptor
        )

        val elementFieldDescriptor: PropertyDescriptor =
            PropertyDescriptorImpl.create(
                refClass, Annotations.EMPTY, Modality.FINAL, Visibilities.PUBLIC, true,
                Name.identifier("element"), CallableMemberDescriptor.Kind.DECLARATION, SourceElement.NO_SOURCE,
                /* lateInit = */ false, /* isConst = */ false, /* isExpect = */ false, /* isActual = */ false,
                /* isExternal = */ false, /* isDelegated = */ false
            ).initialize(type, dispatchReceiverParameter = refClass.thisAsReceiverParameter)

        val elementField: IrField = IrFieldImpl(
            UNDEFINED_OFFSET,
            UNDEFINED_OFFSET,
            JvmLoweredDeclarationOrigin.REF_FOR_SHARED_VARIABLE,
            elementFieldDescriptor,
            type.toIrType()!!
        )
    }

    private val primitiveRefDescriptorProviders: Map<PrimitiveType, PrimitiveRefDescriptorsProvider> =
        PrimitiveType.values().associate {
            val type = builtIns.getPrimitiveKotlinType(it)

            val refClassName = Name.identifier(it.typeName.asString() + "Ref")
            val refClass = KnownClassDescriptor.createClass(refClassName, refNamespaceClass, listOf(builtIns.anyType))

            it to PrimitiveRefDescriptorsProvider(type, refClass)
        }

    private inner class ObjectRefDescriptorsProvider {
        val genericRefClass: ClassDescriptor =
            KnownClassDescriptor.createClassWithTypeParameters(
                Name.identifier("ObjectRef"), refNamespaceClass, listOf(builtIns.anyType), listOf(Name.identifier("T"))
            )

        val genericRefConstructorDescriptor: ClassConstructorDescriptor =
            ClassConstructorDescriptorImpl.create(genericRefClass, Annotations.EMPTY, true, SourceElement.NO_SOURCE).apply {
                initialize(emptyList(), Visibilities.PUBLIC)
                val typeParameter = typeParameters[0]
                val typeParameterType = KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(
                    Annotations.EMPTY,
                    typeParameter.typeConstructor,
                    listOf(),
                    false,
                    MemberScope.Empty
                )
                returnType = KotlinTypeFactory.simpleNotNullType(
                    Annotations.EMPTY,
                    genericRefClass,
                    listOf(TypeProjectionImpl(Variance.INVARIANT, typeParameterType))
                )
            }

        val genericRefConstructor: IrConstructor = IrConstructorImpl(
            UNDEFINED_OFFSET,
            UNDEFINED_OFFSET,
            JvmLoweredDeclarationOrigin.REF_FOR_SHARED_VARIABLE,
            genericRefConstructorDescriptor
        )

        val constructorTypeParameter: TypeParameterDescriptor =
            genericRefConstructorDescriptor.typeParameters[0]

        fun getSubstitutedRefConstructorDescriptor(valueType: KotlinType): ClassConstructorDescriptor =
            genericRefConstructorDescriptor.substitute(
                TypeSubstitutor.create(
                    mapOf(constructorTypeParameter.typeConstructor to TypeProjectionImpl(Variance.INVARIANT, valueType))
                )
            )!!

        val genericElementFieldDescriptor: PropertyDescriptor =
            PropertyDescriptorImpl.create(
                genericRefClass, Annotations.EMPTY, Modality.FINAL, Visibilities.PUBLIC, true,
                Name.identifier("element"), CallableMemberDescriptor.Kind.DECLARATION, SourceElement.NO_SOURCE,
                /* lateInit = */ false, /* isConst = */ false, /* isExpect = */ false, /* isActual = */ false,
                /* isExternal = */ false, /* isDelegated = */ false
            ).initialize(
                type = builtIns.anyType,
                dispatchReceiverParameter = genericRefClass.thisAsReceiverParameter
            )

        val genericElementField: IrField = IrFieldImpl(
            UNDEFINED_OFFSET,
            UNDEFINED_OFFSET,
            JvmLoweredDeclarationOrigin.REF_FOR_SHARED_VARIABLE,
            genericElementFieldDescriptor,
            irBuiltIns.anyType
        )

        fun getRefType(valueType: KotlinType) =
            KotlinTypeFactory.simpleNotNullType(
                Annotations.EMPTY,
                genericRefClass,
                listOf(TypeProjectionImpl(Variance.INVARIANT, valueType))
            )
    }

    private val objectRefDescriptorsProvider = ObjectRefDescriptorsProvider()

    override fun declareSharedVariable(originalDeclaration: IrVariable): IrVariable {
        val variableDescriptor = originalDeclaration.descriptor
        val sharedVariableDescriptor = LocalVariableDescriptor(
            variableDescriptor.containingDeclaration, variableDescriptor.annotations, variableDescriptor.name,
            getSharedVariableType(variableDescriptor.type),
            false, false, variableDescriptor.isLateInit, variableDescriptor.source
        )

        val valueType = originalDeclaration.descriptor.type
        val primitiveRefDescriptorsProvider = primitiveRefDescriptorProviders[getPrimitiveType(valueType)]

        val refConstructorDescriptor =
            primitiveRefDescriptorsProvider?.refConstructorDescriptor
                ?: objectRefDescriptorsProvider.getSubstitutedRefConstructorDescriptor(valueType)

        val refConstructor = primitiveRefDescriptorsProvider?.refConstructor ?: objectRefDescriptorsProvider.genericRefConstructor

        val refConstructorTypeArguments =
            if (primitiveRefDescriptorsProvider != null) null
            else mapOf(objectRefDescriptorsProvider.constructorTypeParameter to valueType)


        val refConstructorCall = IrCallImpl(
            originalDeclaration.startOffset, originalDeclaration.endOffset,
            refConstructorDescriptor.constructedClass.defaultType.toIrType()!!,
            refConstructor.symbol, refConstructorDescriptor,
            refConstructorTypeArguments?.size ?: 0
        )
        if (refConstructorTypeArguments?.isNotEmpty() == true) {
            refConstructorCall.putTypeArgument(0, originalDeclaration.type)
        }
        return IrVariableImpl(
            originalDeclaration.startOffset, originalDeclaration.endOffset, originalDeclaration.origin,
            sharedVariableDescriptor, sharedVariableDescriptor.type.toIrType()!!, refConstructorCall
        )
    }

    override fun defineSharedValue(
        originalDeclaration: IrVariable,
        sharedVariableDeclaration: IrVariable
    ): IrStatement {
        val initializer = originalDeclaration.initializer ?: return sharedVariableDeclaration

        val valueType = originalDeclaration.descriptor.type
        val primitiveRefDescriptorsProvider = primitiveRefDescriptorProviders[getPrimitiveType(valueType)]

        val elementField =
            primitiveRefDescriptorsProvider?.elementField ?: objectRefDescriptorsProvider.genericElementField

        val sharedVariableInitialization = IrSetFieldImpl(
            initializer.startOffset, initializer.endOffset,
            elementField.symbol,
            IrGetValueImpl(initializer.startOffset, initializer.endOffset, sharedVariableDeclaration.symbol),
            initializer,
            originalDeclaration.type
        )

        return IrCompositeImpl(
            originalDeclaration.startOffset, originalDeclaration.endOffset, irBuiltIns.unitType, null,
            listOf(sharedVariableDeclaration, sharedVariableInitialization)
        )
    }

    private fun getElementField(valueType: KotlinType): IrField {
        val primitiveRefDescriptorsProvider = primitiveRefDescriptorProviders[getPrimitiveType(valueType)]

        return primitiveRefDescriptorsProvider?.elementField ?: objectRefDescriptorsProvider.genericElementField
    }

    override fun getSharedValue(sharedVariableSymbol: IrVariableSymbol, originalGet: IrGetValue): IrExpression =
        IrGetFieldImpl(
            originalGet.startOffset, originalGet.endOffset,
            getElementField(originalGet.descriptor.type).symbol,
            originalGet.type,
            IrGetValueImpl(
                originalGet.startOffset,
                originalGet.endOffset,
                sharedVariableSymbol
            ),
            originalGet.origin
        )

    override fun setSharedValue(sharedVariableSymbol: IrVariableSymbol, originalSet: IrSetVariable): IrExpression =
        IrSetFieldImpl(
            originalSet.startOffset, originalSet.endOffset,
            getElementField(originalSet.descriptor.type).symbol,
            IrGetValueImpl(
                originalSet.startOffset,
                originalSet.endOffset,
                sharedVariableSymbol
            ),
            originalSet.value,
            originalSet.type,
            originalSet.origin
        )

    private fun getSharedVariableType(valueType: KotlinType): KotlinType =
        primitiveRefDescriptorProviders[getPrimitiveType(valueType)]?.refType ?: objectRefDescriptorsProvider.getRefType(valueType)

    private fun getPrimitiveType(type: KotlinType): PrimitiveType? =
        when {
            KotlinBuiltIns.isBoolean(type) -> PrimitiveType.BOOLEAN
            KotlinBuiltIns.isChar(type) -> PrimitiveType.CHAR
            KotlinBuiltIns.isByte(type) -> PrimitiveType.BYTE
            KotlinBuiltIns.isShort(type) -> PrimitiveType.SHORT
            KotlinBuiltIns.isInt(type) -> PrimitiveType.INT
            KotlinBuiltIns.isLong(type) -> PrimitiveType.LONG
            KotlinBuiltIns.isFloat(type) -> PrimitiveType.FLOAT
            KotlinBuiltIns.isDouble(type) -> PrimitiveType.DOUBLE
            else -> null
        }

}
