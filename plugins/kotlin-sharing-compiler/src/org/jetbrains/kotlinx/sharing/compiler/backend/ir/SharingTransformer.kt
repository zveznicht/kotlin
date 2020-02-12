/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing.compiler.backend.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.irIfThen
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.findClassAcrossModuleDependencies
import org.jetbrains.kotlin.ir.builders.irBoolean
import org.jetbrains.kotlin.ir.builders.irString
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrField
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.impl.IrExpressionBodyImpl
import org.jetbrains.kotlin.ir.util.findDeclaration
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.descriptorUtil.builtIns
import org.jetbrains.kotlinx.sharing.compiler.extensions.IrTransformer
import org.jetbrains.kotlinx.sharing.compiler.extensions.PluginDeclarationsCreator

class SharingDeclarations : PluginDeclarationsCreator {
    private val readOnlyName = Name.identifier("isReadOnly")

    override val propertiesNames: Set<Name> = setOf(readOnlyName)

    override fun createPropertyForFrontend(owner: ClassDescriptor, name: Name, context: BindingContext): PropertyDescriptor? = when (name) {
        // todo: remove .asString part
        readOnlyName -> SimpleSyntheticPropertyDescriptor(owner, readOnlyName.asString(), owner.builtIns.booleanType, isVar = true)
        else -> null
    }
}

class SharingTransformer(override val compilerContext: IrPluginContext) : IrTransformer() {
    // todo: add owner
    override fun defineBackingField(propertyDescriptor: PropertyDescriptor, irProperty: IrProperty, createdField: IrField) {
        if (propertyDescriptor.name == Name.identifier("isReadOnly"))
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
