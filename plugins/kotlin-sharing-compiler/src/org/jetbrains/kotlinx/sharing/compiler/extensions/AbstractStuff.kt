/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing.compiler.extensions

import org.jetbrains.kotlin.backend.common.ClassLoweringPass
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.builders.*
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrBlockBody
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.*
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.declareSimpleFunctionWithOverrides
import org.jetbrains.kotlin.ir.util.statements
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.ir.visitors.acceptVoid
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlinx.sharing.compiler.backend.ir.IrBuilderExtension
import org.jetbrains.kotlinx.sharing.compiler.backend.ir.PLUGIN_ORIGIN
import java.util.*

abstract class CompilerPlugin : IrGenerationExtension, SyntheticResolveExtension {

    // todo: unnecessary after KT-37255?
    private val tracked: MutableMap<ClassDescriptor, MutableSet<DeclarationDescriptor>> = hashMapOf()

    final override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        val lowering = createIrTransformer(pluginContext)
        for (file in moduleFragment.files) {
            file.acceptVoid(object : IrElementVisitorVoid {
                override fun visitElement(element: IrElement) {
                    element.acceptChildrenVoid(this)
                }

                override fun visitClass(declaration: IrClass) {
                    if (!isApplied(declaration.descriptor)) return
                    lowering.createMissingParts(declaration, tracked[declaration.descriptor] ?: emptySet())
                    lowering.lower(declaration)
                    declaration.acceptChildrenVoid(this)
                }
            })
        }
    }

    // todo: other methods from synthetic resolve ex
    override fun generateSyntheticProperties(
        thisDescriptor: ClassDescriptor,
        name: Name,
        bindingContext: BindingContext,
        fromSupertypes: ArrayList<PropertyDescriptor>,
        result: MutableSet<PropertyDescriptor>
    ) {
        if (!isApplied(thisDescriptor)) return
        if (name !in creator.propertiesNames) return
        if (result.isNotEmpty()) error("Can't add plugin-defined function with the same name $name as user-defined one")
        val added = listOfNotNull(creator.createPropertyForFrontend(thisDescriptor, name, bindingContext))
        tracked.getOrPut(thisDescriptor, ::hashSetOf).addAll(added)
        result.addAll(added)
    }

    override fun getSyntheticPropertiesNames(thisDescriptor: ClassDescriptor): List<Name> {
        return creator.propertiesNames.toList()
    }

    abstract fun isApplied(toClass: ClassDescriptor): Boolean

    abstract fun createIrTransformer(context: IrPluginContext): IrTransformer

    abstract val creator: PluginDeclarationsCreator
}

abstract class IrTransformer : IrBuilderExtension, ClassLoweringPass {
    fun createMissingParts(irClass: IrClass, pluginDescriptors: Collection<DeclarationDescriptor>) {
        val declarations = pluginDescriptors.map { desc ->
            if (desc !is PropertyDescriptor) TODO()
            createPropertyForBackend(irClass, desc)
        }
        irClass.declarations.addAll(declarations)
    }

    inline fun IrSimpleFunction.prepend(statements: ExtendedBodyBuilder.() -> Unit) {
        val oldBody = body?.statements.orEmpty()
        body = IrBlockBodyImpl(startOffset, endOffset, buildBody(statements).statements + oldBody)
    }

    inline fun IrSymbolOwner.buildBody(blockBody: ExtendedBodyBuilder.() -> Unit): IrBlockBody {
        return ExtendedBodyBuilder(compilerContext, this.symbol).apply { blockBody() }.doBuild()
    }

    inline fun IrSymbolOwner.buildExpression(builder: IrSingleStatementBuilder.() -> IrExpression): IrExpression {
        return IrSingleStatementBuilder(compilerContext, Scope(this.symbol), startOffset, endOffset).build(builder)
    }

    private fun createPropertyForBackend(
        owner: IrClass,
        frontendProperty: PropertyDescriptor
    ): IrProperty {
        val irProperty = compilerContext.symbolTable.declareProperty(owner.startOffset, owner.endOffset, PLUGIN_ORIGIN, frontendProperty)
        irProperty.parent = owner
        irProperty.backingField = generatePropertyBackingField(frontendProperty, irProperty).apply {
            parent = owner
            correspondingPropertySymbol = irProperty.symbol
        }
        val fieldSymbol = irProperty.backingField!!.symbol
        irProperty.getter = frontendProperty.getter?.let { generatePropertyAccessor(it, fieldSymbol) }
            ?.apply { parent = owner }
        irProperty.setter = frontendProperty.setter?.let { generatePropertyAccessor(it, fieldSymbol) }
            ?.apply { parent = owner }
        return irProperty
    }

    private fun generatePropertyBackingField(
        propertyDescriptor: PropertyDescriptor,
        originProperty: IrProperty
    ): IrField {
        return compilerContext.symbolTable.declareField(
            originProperty.startOffset,
            originProperty.endOffset,
            PLUGIN_ORIGIN,
            propertyDescriptor,
            propertyDescriptor.type.toIrType()
        ).apply { defineBackingField(propertyDescriptor, originProperty, this) }
    }

    // todo: add owner
    open fun defineBackingField(propertyDescriptor: PropertyDescriptor, irProperty: IrProperty, createdField: IrField) = Unit

    private fun generatePropertyAccessor(
        descriptor: PropertyAccessorDescriptor,
        fieldSymbol: IrFieldSymbol
    ): IrSimpleFunction {
        val declaration = compilerContext.symbolTable.declareSimpleFunctionWithOverrides(
            fieldSymbol.owner.startOffset,
            fieldSymbol.owner.endOffset,
            PLUGIN_ORIGIN, descriptor
        )
        return declaration.buildWithScope { irAccessor ->
            irAccessor.createParameterDeclarations(receiver = null)
            irAccessor.returnType = irAccessor.descriptor.returnType!!.toIrType()
            irAccessor.body = when (descriptor) {
                is PropertyGetterDescriptor -> definePropertyGetterBody(descriptor.correspondingProperty, descriptor, irAccessor)
                is PropertySetterDescriptor -> definePropertySetterBody(descriptor.correspondingProperty, descriptor, irAccessor)
                else -> throw AssertionError("Should be getter or setter: $descriptor")
            }
        }
    }

    open fun definePropertyGetterBody(
        property: PropertyDescriptor,
        getter: PropertyGetterDescriptor,
        irGetter: IrSimpleFunction
    ): IrBlockBody {
        val startOffset = irGetter.startOffset
        val endOffset = irGetter.endOffset
        val irBody = IrBlockBodyImpl(startOffset, endOffset)

        val receiver = generateReceiverExpressionForFieldAccess(irGetter.dispatchReceiverParameter!!.symbol, property)

        irBody.statements.add(
            IrReturnImpl(
                startOffset, endOffset, compilerContext.irBuiltIns.nothingType,
                irGetter.symbol,
                IrGetFieldImpl(
                    startOffset, endOffset,
                    compilerContext.symbolTable.referenceField(property),
                    property.type.toIrType(),
                    receiver
                )
            )
        )
        return irBody
    }

    open fun definePropertySetterBody(
        property: PropertyDescriptor,
        setter: PropertySetterDescriptor,
        irAccessor: IrSimpleFunction
    ): IrBlockBody {
        val startOffset = irAccessor.startOffset
        val endOffset = irAccessor.endOffset
        val irBody = IrBlockBodyImpl(startOffset, endOffset)

        val receiver = generateReceiverExpressionForFieldAccess(irAccessor.dispatchReceiverParameter!!.symbol, property)

        val irValueParameter = irAccessor.valueParameters.single()
        irBody.statements.add(
            IrSetFieldImpl(
                startOffset, endOffset,
                compilerContext.symbolTable.referenceField(property),
                receiver,
                IrGetValueImpl(startOffset, endOffset, irValueParameter.type, irValueParameter.symbol),
                compilerContext.irBuiltIns.unitType
            )
        )
        return irBody
    }
}

interface PluginDeclarationsCreator {
    // todo: add others
    val propertiesNames: Set<Name>

    fun createPropertyForFrontend(owner: ClassDescriptor, name: Name, context: BindingContext): PropertyDescriptor?
}

// todo: Split with generator helpers
// merge with other block body builders
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