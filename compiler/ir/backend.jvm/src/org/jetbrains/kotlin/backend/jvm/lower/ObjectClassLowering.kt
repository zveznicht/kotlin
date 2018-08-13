/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.jvm.lower

import org.jetbrains.kotlin.backend.common.FileLoweringPass
import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.common.ir.primaryConstructor
import org.jetbrains.kotlin.backend.jvm.JvmBackendContext
import org.jetbrains.kotlin.backend.jvm.JvmLoweredDeclarationOrigin
import org.jetbrains.kotlin.backend.jvm.descriptors.initialize
import org.jetbrains.kotlin.builtins.CompanionObjectMapping
import org.jetbrains.kotlin.codegen.JvmCodegenUtil.isCompanionObjectInInterfaceNotIntrinsic
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.descriptors.impl.PropertyDescriptorImpl
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.declarations.impl.IrFieldImpl
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrExpressionBodyImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrGetFieldImpl
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol
import org.jetbrains.kotlin.ir.symbols.impl.IrFieldSymbolImpl
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.defaultType
import org.jetbrains.kotlin.ir.util.isObject
import org.jetbrains.kotlin.name.Name

class ObjectClassLowering(val context: JvmBackendContext) : IrElementTransformerVoidWithContext(), FileLoweringPass {

    private var pendingTransformations = mutableListOf<Function0<Unit>>()

    override fun lower(irFile: IrFile) {
        irFile.accept(this, null)

        pendingTransformations.forEach { it() }
    }

    override fun visitClassNew(declaration: IrClass): IrStatement {
        process(declaration)
        return super.visitClassNew(declaration)
    }


    private fun process(irClass: IrClass) {
        if (!irClass.isObject) return

        val publicInstance = context.getObjectInstanceField(irClass.symbol)

        val constructor = irClass.primaryConstructor()
            ?: throw AssertionError("Object should have a primary constructor: ${irClass.descriptor}")

        val publicInstanceOwner = if (irClass.descriptor.isCompanionObject) parentScope!!.irElement as IrDeclarationContainer else irClass
        if (isCompanionObjectInInterfaceNotIntrinsic(irClass.descriptor)) {
            // TODO rename to $$INSTANCE
            val privateInstanceDescriptor = publicInstance.descriptor.copy(
                irClass.descriptor,
                Modality.FINAL,
                Visibilities.PROTECTED/*TODO package local*/,
                CallableMemberDescriptor.Kind.SYNTHESIZED,
                false
            ) as PropertyDescriptor
            val privateInstance = createField(IrFieldSymbolImpl(privateInstanceDescriptor), irClass.defaultType)
            initializeInstanceFieldWithInitializer(privateInstance, constructor, irClass, irClass.defaultType)
            initializeFieldWithCustomInitializer(
                publicInstance,
                IrGetFieldImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, privateInstance.symbol, irClass.defaultType),
                publicInstanceOwner
            )
        } else {
            initializeInstanceFieldWithInitializer(publicInstance, constructor, publicInstanceOwner, irClass.defaultType)
        }
    }

    private fun initializeInstanceFieldWithInitializer(
        field: IrField,
        constructor: IrConstructor,
        instanceOwner: IrDeclarationContainer,
        objectType: IrType
    ) {
        initializeFieldWithCustomInitializer(
            field,
            IrCallImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, objectType, constructor.symbol),
            instanceOwner
        )
    }

    private fun initializeFieldWithCustomInitializer(
        field: IrField,
        instanceInitializer: IrExpression,
        instanceOwner: IrDeclarationContainer
    ) {
        field.parent = instanceOwner
        field.initializer = IrExpressionBodyImpl(instanceInitializer)
        pendingTransformations.add { instanceOwner.declarations.add(field) }
    }
}

val JvmBackendContext.getObjectInstanceField: (IrClassSymbol) -> IrField by JvmBackendContext.lazyMapMember { objectSymbol ->
    createField(IrFieldSymbolImpl(createObjectInstanceFieldDescriptor(objectSymbol.descriptor)), objectSymbol.owner.defaultType)
}


private fun createField(symbol: IrFieldSymbol, type: IrType): IrField =
    IrFieldImpl(UNDEFINED_OFFSET, UNDEFINED_OFFSET, JvmLoweredDeclarationOrigin.FIELD_FOR_OBJECT_INSTANCE, symbol, type)

private fun createObjectInstanceFieldDescriptor(objectDescriptor: ClassDescriptor): PropertyDescriptor {
    assert(objectDescriptor.kind == ClassKind.OBJECT) { "Should be an object: $objectDescriptor" }

    val isNotMappedCompanion = objectDescriptor.isCompanionObject && !CompanionObjectMapping.isMappedIntrinsicCompanionObject(
        objectDescriptor
    )
    val name = if (isNotMappedCompanion) objectDescriptor.name else Name.identifier("INSTANCE")
    val containingDeclaration = if (isNotMappedCompanion) objectDescriptor.containingDeclaration else objectDescriptor
    return PropertyDescriptorImpl.create(
        containingDeclaration,
        Annotations.EMPTY, Modality.FINAL, Visibilities.PUBLIC, false,
        name,
        CallableMemberDescriptor.Kind.SYNTHESIZED, SourceElement.NO_SOURCE, /* lateInit = */ false, /* isConst = */ false,
        /* isExpect = */ false, /* isActual = */ false, /* isExternal = */ false, /* isDelegated = */ false
    ).initialize(objectDescriptor.defaultType)
}
