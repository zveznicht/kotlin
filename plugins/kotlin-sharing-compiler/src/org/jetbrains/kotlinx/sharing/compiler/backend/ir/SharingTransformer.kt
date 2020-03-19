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
import org.jetbrains.kotlin.ir.builders.irBoolean
import org.jetbrains.kotlin.ir.builders.irSetField
import org.jetbrains.kotlin.ir.builders.irString
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrField
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.expressions.impl.IrExpressionBodyImpl
import org.jetbrains.kotlin.ir.util.findDeclaration
import org.jetbrains.kotlin.ir.util.parentAsClass
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.descriptorUtil.builtIns
import org.jetbrains.kotlinx.sharing.compiler.extensions.IrTransformer
import org.jetbrains.kotlinx.sharing.compiler.extensions.PluginDeclarationsCreator

class SharingDeclarations : PluginDeclarationsCreator {
    override val propertiesNames: Set<Name> = setOf(readOnlyName)
    override val functionNames: Set<Name> = setOf(setReadOnly)

    override fun createPropertyForFrontend(owner: ClassDescriptor, name: Name, context: BindingContext): PropertyDescriptor? = when (name) {
        readOnlyName -> SyntheticPropertyDescriptor(owner, readOnlyName, owner.builtIns.booleanType, isVar = true)
        else -> null
    }

    override fun createFunctionForFrontend(owner: ClassDescriptor, name: Name, context: BindingContext): SimpleFunctionDescriptor? =
        when (name) {
            setReadOnly -> createReadOnlyFunction(owner, context)
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

    companion object {
        internal val readOnlyName = Name.identifier("isReadOnly")
        internal val setReadOnly = Name.identifier("setReadOnly")
    }

}

class SharingTransformer(compilerContext: IrPluginContext) : IrTransformer(compilerContext) {
    // todo: add owner
    override fun defineBackingField(propertyDescriptor: PropertyDescriptor, irProperty: IrProperty, createdField: IrField) {
        if (propertyDescriptor.name == SharingDeclarations.readOnlyName)
        // todo: remove 'impl' part
            createdField.initializer = IrExpressionBodyImpl(createdField.buildExpression { irBoolean(false) })
    }

    override fun lower(irClass: IrClass) {
        val readOnlyProp = irClass.findDeclaration<IrProperty> { it.name == Name.identifier("isReadOnly") }!!
        // transform other props
        irClass.declarations.asSequence().filterIsInstance<IrProperty>().filter { it.isVar }.forEach {
            if (it.setter != null) transformPropSetter(readOnlyProp, it.setter!!)
        }
    }

    override fun defineFunctionBody(functionDescriptor: SimpleFunctionDescriptor, irFunction: IrSimpleFunction): IrBody? =
        if (irFunction.name != SharingDeclarations.setReadOnly) null
        else irFunction.buildBody {
            val readOnlyProp = irFunction.parentAsClass.findDeclaration<IrProperty> { it.name == Name.identifier("isReadOnly") }!!
            +irSetField(irThis, readOnlyProp.backingField!!, irBoolean(true))
        }

    private fun transformPropSetter(
        readOnlyProp: IrProperty,
        subjectSetter: IrSimpleFunction
    ) = subjectSetter.prepend {
        +irIfThen(
            irThis.getProperty(readOnlyProp),
            invoke(context.irBuiltIns.illegalArgumentExceptionSymbol, irString("Already readonly"))
        )
    }
}

// todo: move
fun IrPluginContext.searchClass(packageName: String, className: String): ClassDescriptor {
    return requireNotNull(
        moduleDescriptor.findClassAcrossModuleDependencies(
            ClassId(
                FqName(packageName),
                Name.identifier(className)
            )
        )
    ) { "Can't locate class $className" }
}
