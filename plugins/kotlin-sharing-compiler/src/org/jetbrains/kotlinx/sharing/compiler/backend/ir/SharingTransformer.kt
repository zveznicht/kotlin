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
import org.jetbrains.kotlin.ir.builders.*
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.findDeclaration
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.descriptorUtil.builtIns
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlinx.sharing.compiler.extensions.IrTransformer
import org.jetbrains.kotlinx.sharing.compiler.extensions.PluginDeclarationsCreator

class SharingDeclarations : PluginDeclarationsCreator {
    override fun createPropertyForFrontend(owner: ClassDescriptor, context: BindingContext): List<PropertyDescriptor> {
        return listOf(SimpleSyntheticPropertyDescriptor(owner, "isReadOnly", owner.builtIns.booleanType, isVar = true))
    }
}

class SharingTransformer(override val compilerContext: IrPluginContext) : IrTransformer() {
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

// todo: Merge with generator helpers
class ExtendedBodyBuilder(val compilerContext: IrPluginContext, val scopeSymbol: IrSymbol) : IrBlockBodyBuilder(
    compilerContext, Scope(scopeSymbol),
    scopeSymbol.owner.startOffset,
    scopeSymbol.owner.endOffset
) {
    val irThis: IrExpression
        get() = when (val ctx = scopeSymbol.owner) {
            is IrSimpleFunction -> irGet(ctx.dispatchReceiverParameter!!)
            is IrClass -> irGet(ctx.thisReceiver!!)
            else -> error("No this in ${ctx::class}")
        }

    fun IrExpression.getProperty(property: IrProperty): IrExpression {
        return if (property.getter != null) irGet(property.getter!!.returnType, this, property.getter!!.symbol)
        else irGetField(this, property.backingField!!)
    }

    fun KotlinType.toIrType() = compilerContext.typeTranslator.translateType(this)

    fun IrExpression.invoke(
        callee: IrFunctionSymbol,
        vararg args: IrExpression,
        typeHint: IrType = callee.descriptor.returnType!!.toIrType()
    ): IrExpression {
        val call = irCall(callee, type = typeHint)
        call.dispatchReceiver = this
        args.forEachIndexed(call::putValueArgument)
        return call
    }

    fun invoke(
        callee: IrFunctionSymbol,
        vararg args: IrExpression,
        typeHint: IrType = callee.descriptor.returnType!!.toIrType()
    ): IrExpression {
        val call = irCall(callee, type = typeHint)
        call.dispatchReceiver = null
        args.forEachIndexed(call::putValueArgument)
        return call
    }
}