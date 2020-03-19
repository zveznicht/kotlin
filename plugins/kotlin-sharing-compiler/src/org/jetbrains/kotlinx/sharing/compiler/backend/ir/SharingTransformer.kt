/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing.compiler.backend.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.irIfThen
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.descriptors.impl.SimpleFunctionDescriptorImpl
import org.jetbrains.kotlin.ir.builders.*
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrField
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody
import org.jetbrains.kotlin.ir.expressions.impl.IrExpressionBodyImpl
import org.jetbrains.kotlin.ir.util.findDeclaration
import org.jetbrains.kotlin.ir.util.parentAsClass
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.descriptorUtil.builtIns
import org.jetbrains.kotlinx.sharing.compiler.pluginapi.IrTransformer
import org.jetbrains.kotlinx.sharing.compiler.pluginapi.PluginDeclarationsCreator
import org.jetbrains.kotlinx.sharing.compiler.pluginapi.SyntheticPropertyDescriptor

class SharingDeclarations : PluginDeclarationsCreator {
    override val propertiesNames: Set<Name> = setOf(isReadOnly)
    override val functionNames: Set<Name> = setOf(setReadOnly, share)

    override fun createPropertyForFrontend(owner: ClassDescriptor, name: Name, context: BindingContext): PropertyDescriptor? = when (name) {
        isReadOnly -> SyntheticPropertyDescriptor(
            owner,
            isReadOnly,
            owner.builtIns.booleanType,
            isVar = true
        )
        else -> null
    }

    override fun createFunctionForFrontend(owner: ClassDescriptor, name: Name, context: BindingContext): SimpleFunctionDescriptor? =
        when (name) {
            setReadOnly -> createReadOnlyFunction(owner, context)
            share -> createShareFunction(owner)
            else -> null
        }

    private fun createReadOnlyFunction(owner: ClassDescriptor, context: BindingContext): SimpleFunctionDescriptor {
        val f = SimpleFunctionDescriptorImpl.create(
            owner,
            Annotations.EMPTY,
            setReadOnly,
            CallableMemberDescriptor.Kind.SYNTHESIZED,
            owner.source
        )
        f.initialize(null, owner.thisAsReceiverParameter, listOf(), listOf(), owner.builtIns.unitType, Modality.FINAL, Visibilities.PUBLIC)
        return f
    }

    private fun createShareFunction(owner: ClassDescriptor): SimpleFunctionDescriptor {
        val f = SimpleFunctionDescriptorImpl.create(
            owner,
            Annotations.EMPTY,
            share,
            CallableMemberDescriptor.Kind.SYNTHESIZED,
            owner.source
        )
        f.initialize(null, owner.thisAsReceiverParameter, listOf(), listOf(), owner.defaultType, Modality.FINAL, Visibilities.PUBLIC)
        return f
    }

    companion object {
        internal val isReadOnly = Name.identifier("isReadOnly")
        internal val setReadOnly = Name.identifier("setReadOnly")
        internal val share = Name.identifier("share")
    }

}

class SharingTransformer(compilerContext: IrPluginContext) : IrTransformer(compilerContext) {
    override fun definePropertyInitializer(
        propertyDescriptor: PropertyDescriptor,
        irProperty: IrProperty,
        createdField: IrField
    ): IrExpressionBody? =
        if (propertyDescriptor.name == SharingDeclarations.isReadOnly)
        // todo: remove 'impl' part
            IrExpressionBodyImpl(createdField.buildExpression { irBoolean(false) })
        else
            null

    override fun lower(irClass: IrClass) {
        val readOnlyProp = irClass.findDeclaration<IrProperty> { it.name == Name.identifier("isReadOnly") }!!
        // transform other props
        irClass.declarations.asSequence().filterIsInstance<IrProperty>().filter { it.isVar }.forEach {
            if (it.setter != null) transformPropSetter(readOnlyProp, it.setter!!)
        }
    }

    override fun defineFunctionBody(functionDescriptor: SimpleFunctionDescriptor, irFunction: IrSimpleFunction): IrBody? =
        when (irFunction.name) {
            SharingDeclarations.setReadOnly -> defineSetReadOnlyBody(functionDescriptor, irFunction)
            SharingDeclarations.share -> defineShareBody(functionDescriptor, irFunction)
            else -> null
        }

    private fun defineShareBody(functionDescriptor: SimpleFunctionDescriptor, irFunction: IrSimpleFunction): IrBody? =
        irFunction.buildBody {
            val copyF = functionDescriptor.containingClass?.referenceFunction(Name.identifier("copy"))!!
            val setReadOnlyF = functionDescriptor.containingClass?.referenceFunction(SharingDeclarations.setReadOnly)!!
            val tempVar = irTemporaryVar(irThis.invoke(copyF), "copy")
            +irGet(tempVar).invoke(setReadOnlyF)
            +irReturn(irGet(tempVar))
        }

    private fun defineSetReadOnlyBody(functionDescriptor: SimpleFunctionDescriptor, irFunction: IrSimpleFunction) = irFunction.buildBody {
        // todo: remove necessity for property here, leave only symbol?
//        val readOnlyProp = functionDescriptor.containingClass?.referenceProperty(SharingDeclarations.isReadOnly)!!
        val readOnlyProp = irFunction.parentAsClass.findDeclaration<IrProperty> { it.name == Name.identifier("isReadOnly") }!!
        +irThis.setProperty(readOnlyProp, irBoolean(true))
    }

    private fun transformPropSetter(
        readOnlyProp: IrProperty,
        subjectSetter: IrSimpleFunction
    ) = subjectSetter.prepend {
        +irIfThen(
            irThis.getProperty(readOnlyProp),
            invoke(
                context.irBuiltIns.illegalArgumentExceptionSymbol,
                irString("Already readonly")
            ) // todo: change to ISE or domain-specific
        )
    }

    // make common?
    private val FunctionDescriptor.containingClass: ClassDescriptor? get() = containingDeclaration as? ClassDescriptor
}
