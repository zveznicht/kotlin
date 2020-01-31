/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.stm.compiler.backend.ir

import org.jetbrains.kotlin.backend.common.BackendContext
import org.jetbrains.kotlin.backend.common.CodegenUtil.getMemberToGenerate
import org.jetbrains.kotlin.backend.common.deepCopyWithVariables
import org.jetbrains.kotlin.backend.common.lower.at
import org.jetbrains.kotlin.backend.common.lower.createIrBuilder
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.ir.builders.*
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.declarations.impl.IrPropertyImpl
import org.jetbrains.kotlin.ir.declarations.impl.IrTypeParameterImpl
import org.jetbrains.kotlin.ir.declarations.impl.IrValueParameterImpl
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.expressions.impl.*
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.toKotlinType
import org.jetbrains.kotlin.ir.types.typeWith
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.descriptorUtil.module
import org.jetbrains.kotlin.types.*
import org.jetbrains.kotlin.types.typeUtil.representativeUpperBound

// Is creating synthetic origin is a good idea or not?
object STM_PLUGIN_ORIGIN : IrDeclarationOriginImpl("STM")

val BackendContext.externalSymbols: ReferenceSymbolTable get() = ir.symbols.externalSymbolTable

interface IrBuilderExtension {
    val compilerContext: BackendContext
    val translator: TypeTranslator

    val BackendContext.localSymbolTable: SymbolTable

    private fun IrClass.declareSimpleFunctionWithExternalOverrides(descriptor: FunctionDescriptor): IrSimpleFunction {
        return compilerContext.localSymbolTable.declareSimpleFunction(startOffset, endOffset, STM_PLUGIN_ORIGIN, descriptor)
            .also { f ->
                descriptor.overriddenDescriptors.mapTo(f.overriddenSymbols) {
                    compilerContext.externalSymbols.referenceSimpleFunction(it.original)
                }
            }
    }

    fun IrClass.contributeFunction(
        descriptor: FunctionDescriptor,
        fromStubs: Boolean = false,
        bodyGen: IrBlockBodyBuilder.(IrFunction) -> Unit
    ) {
        val f: IrSimpleFunction = if (!fromStubs) declareSimpleFunctionWithExternalOverrides(
            descriptor
        ) else compilerContext.externalSymbols.referenceSimpleFunction(descriptor).owner
        f.parent = this
        f.returnType = descriptor.returnType!!.toIrType()
        if (!fromStubs) f.createParameterDeclarations(this.thisReceiver!!)
        f.body = compilerContext.createIrBuilder(f.symbol).at(this).irBlockBody(this.startOffset, this.endOffset) { bodyGen(f) }
        this.addMember(f)
    }

    fun IrClass.contributeConstructor(
        descriptor: ClassConstructorDescriptor,
        fromStubs: Boolean = false,
        overwriteValueParameters: Boolean = false,
        bodyGen: IrBlockBodyBuilder.(IrConstructor) -> Unit
    ) {
        val c = if (!fromStubs) compilerContext.localSymbolTable.declareConstructor(
            this.startOffset,
            this.endOffset,
            STM_PLUGIN_ORIGIN,
            descriptor
        ) else compilerContext.externalSymbols.referenceConstructor(descriptor).owner
        c.parent = this
        c.returnType = descriptor.returnType.toIrType()
        if (!fromStubs || overwriteValueParameters) c.createParameterDeclarations(
            receiver = null,
            overwriteValueParameters = overwriteValueParameters,
            copyTypeParameters = false
        )
        c.body = compilerContext.createIrBuilder(c.symbol).at(this).irBlockBody(this.startOffset, this.endOffset) { bodyGen(c) }
        this.addMember(c)
    }

    fun IrBuilderWithScope.irInvoke(
        dispatchReceiver: IrExpression? = null,
        callee: IrFunctionSymbol,
        vararg args: IrExpression,
        typeHint: IrType? = null
    ): IrMemberAccessExpression {
        val call = typeHint?.let { irCall(callee, type = it) } ?: irCall(callee)
        call.dispatchReceiver = dispatchReceiver
        args.forEachIndexed(call::putValueArgument)
        return call
    }

    fun IrBuilderWithScope.irInvoke(
        dispatchReceiver: IrExpression? = null,
        callee: IrFunctionSymbol,
        typeArguments: List<IrType?>,
        valueArguments: List<IrExpression>,
        returnTypeHint: IrType? = null
    ): IrMemberAccessExpression =
        irInvoke(
            dispatchReceiver,
            callee,
            args = *valueArguments.toTypedArray(),
            typeHint = returnTypeHint
        ).also { call -> typeArguments.forEachIndexed(call::putTypeArgument) }

    fun IrBuilderWithScope.createArrayOfExpression(
        arrayElementType: IrType,
        arrayElements: List<IrExpression>
    ): IrExpression {

        val arrayType = compilerContext.ir.symbols.array.typeWith(arrayElementType)
        val arg0 = IrVarargImpl(startOffset, endOffset, arrayType, arrayElementType, arrayElements)
        val typeArguments = listOf(arrayElementType)

        return irCall(compilerContext.ir.symbols.arrayOf, arrayType, typeArguments = typeArguments).apply {
            putValueArgument(0, arg0)
        }
    }

    fun IrBuilderWithScope.irBinOp(name: Name, lhs: IrExpression, rhs: IrExpression): IrExpression {
        val symbol = compilerContext.ir.symbols.getBinaryOperator(
            name,
            lhs.type.toKotlinType(),
            rhs.type.toKotlinType()
        )
        return irInvoke(lhs, symbol, rhs)
    }

    fun IrBuilderWithScope.irGetObject(classDescriptor: ClassDescriptor) =
        IrGetObjectValueImpl(
            startOffset,
            endOffset,
            classDescriptor.defaultType.toIrType(),
            compilerContext.externalSymbols.referenceClass(classDescriptor)
        )

    fun IrBuilderWithScope.irGetObject(irObject: IrClass) =
        IrGetObjectValueImpl(
            startOffset,
            endOffset,
            irObject.defaultType,
            irObject.symbol
        )

    fun <T : IrDeclaration> T.buildWithScope(builder: (T) -> Unit): T =
        also { irDeclaration ->
            compilerContext.localSymbolTable.withScope(irDeclaration.descriptor) {
                builder(irDeclaration)
            }
        }

    fun IrBuilderWithScope.irEmptyVararg(forValueParameter: ValueParameterDescriptor) =
        IrVarargImpl(
            startOffset,
            endOffset,
            forValueParameter.type.toIrType(),
            forValueParameter.varargElementType!!.toIrType()
        )

    class BranchBuilder(
        val irWhen: IrWhen,
        context: IrGeneratorContext,
        scope: Scope,
        startOffset: Int,
        endOffset: Int
    ) : IrBuilderWithScope(context, scope, startOffset, endOffset) {
        operator fun IrBranch.unaryPlus() {
            irWhen.branches.add(this)
        }
    }

    fun IrBuilderWithScope.irWhen(typeHint: IrType? = null, block: BranchBuilder.() -> Unit): IrWhen {
        val whenExpr = IrWhenImpl(startOffset, endOffset, typeHint ?: compilerContext.irBuiltIns.unitType)
        val builder = BranchBuilder(whenExpr, context, scope, startOffset, endOffset)
        builder.block()
        return whenExpr
    }

    fun BranchBuilder.elseBranch(result: IrExpression): IrElseBranch =
        IrElseBranchImpl(
            IrConstImpl.boolean(result.startOffset, result.endOffset, compilerContext.irBuiltIns.booleanType, true),
            result
        )

    fun translateType(ktType: KotlinType): IrType =
        translator.translateType(ktType)

    fun KotlinType.toIrType() = translateType(this)


    /*
     The rest of the file is mainly copied from FunctionGenerator.
     However, I can't use it's directly because all generateSomething methods require KtProperty (psi element)
     Also, FunctionGenerator itself has DeclarationGenerator as ctor param, which is a part of psi2ir
     (it can be instantiated here, but I don't know how good is that idea)
     */

    fun IrBuilderWithScope.generateAnySuperConstructorCall(toBuilder: IrBlockBodyBuilder) {
        val anyConstructor = compilerContext.builtIns.any.constructors.single()
        with(toBuilder) {
            +IrDelegatingConstructorCallImpl(
                startOffset, endOffset,
                compilerContext.irBuiltIns.unitType,
                compilerContext.externalSymbols.referenceConstructor(anyConstructor),
                anyConstructor
            )
        }
    }

    fun generateSimplePropertyWithBackingField(
        ownerSymbol: IrValueSymbol,
        propertyDescriptor: PropertyDescriptor,
        propertyParent: IrClass
    ): IrProperty {
        val irProperty = IrPropertyImpl(
            propertyParent.startOffset, propertyParent.endOffset,
            STM_PLUGIN_ORIGIN, false,
            propertyDescriptor
        )
        irProperty.parent = propertyParent
        irProperty.backingField = generatePropertyBackingField(propertyDescriptor, irProperty).apply {
            parent = propertyParent
            correspondingPropertySymbol = irProperty.symbol
        }
        val fieldSymbol = irProperty.backingField!!.symbol
        irProperty.getter = propertyDescriptor.getter?.let { generatePropertyAccessor(it, fieldSymbol) }
            ?.apply { parent = propertyParent }
        irProperty.setter = propertyDescriptor.setter?.let { generatePropertyAccessor(it, fieldSymbol) }
            ?.apply { parent = propertyParent }
        return irProperty
    }

    private fun generatePropertyBackingField(
        propertyDescriptor: PropertyDescriptor,
        originProperty: IrProperty
    ): IrField {
        return compilerContext.localSymbolTable.declareField(
            originProperty.startOffset,
            originProperty.endOffset,
            STM_PLUGIN_ORIGIN,
            propertyDescriptor,
            propertyDescriptor.type.toIrType()
        )
    }

    fun generatePropertyAccessor(
        descriptor: PropertyAccessorDescriptor,
        fieldSymbol: IrFieldSymbol
    ): IrSimpleFunction {
        // Declaration can also be called from user code. Since we lookup descriptor getter in externalSymbols
        // (see generateSave/generateLoad), seems it is correct approach to declare getter lazily there.
        val declaration = compilerContext.externalSymbols.referenceSimpleFunction(descriptor).owner
        return declaration.buildWithScope { irAccessor ->
            irAccessor.createParameterDeclarations(receiver = null)
            irAccessor.returnType = irAccessor.descriptor.returnType!!.toIrType()
            irAccessor.body = when (descriptor) {
                is PropertyGetterDescriptor -> generateDefaultGetterBody(descriptor, irAccessor)
                is PropertySetterDescriptor -> generateDefaultSetterBody(descriptor, irAccessor)
                else -> throw AssertionError("Should be getter or setter: $descriptor")
            }
        }
    }

    private fun generateDefaultGetterBody(
        getter: PropertyGetterDescriptor,
        irAccessor: IrSimpleFunction
    ): IrBlockBody {
        val property = getter.correspondingProperty

        val startOffset = irAccessor.startOffset
        val endOffset = irAccessor.endOffset
        val irBody = IrBlockBodyImpl(startOffset, endOffset)

        val receiver = generateReceiverExpressionForFieldAccess(irAccessor.dispatchReceiverParameter!!.symbol, property)

        irBody.statements.add(
            IrReturnImpl(
                startOffset, endOffset, compilerContext.irBuiltIns.nothingType,
                irAccessor.symbol,
                IrGetFieldImpl(
                    startOffset, endOffset,
                    compilerContext.localSymbolTable.referenceField(property),
                    property.type.toIrType(),
                    receiver
                )
            )
        )
        return irBody
    }

    private fun generateDefaultSetterBody(
        setter: PropertySetterDescriptor,
        irAccessor: IrSimpleFunction
    ): IrBlockBody {
        val property = setter.correspondingProperty

        val startOffset = irAccessor.startOffset
        val endOffset = irAccessor.endOffset
        val irBody = IrBlockBodyImpl(startOffset, endOffset)

        val receiver = generateReceiverExpressionForFieldAccess(irAccessor.dispatchReceiverParameter!!.symbol, property)

        val irValueParameter = irAccessor.valueParameters.single()
        irBody.statements.add(
            IrSetFieldImpl(
                startOffset, endOffset,
                compilerContext.localSymbolTable.referenceField(property),
                receiver,
                IrGetValueImpl(startOffset, endOffset, irValueParameter.type, irValueParameter.symbol),
                compilerContext.irBuiltIns.unitType
            )
        )
        return irBody
    }

    fun generateReceiverExpressionForFieldAccess(
        ownerSymbol: IrValueSymbol,
        property: PropertyDescriptor
    ): IrExpression {
        val containingDeclaration = property.containingDeclaration
        return when (containingDeclaration) {
            is ClassDescriptor ->
                IrGetValueImpl(
                    ownerSymbol.owner.startOffset, ownerSymbol.owner.endOffset,
//                symbolTable.referenceValue(containingDeclaration.thisAsReceiverParameter)
                    ownerSymbol
                )
            else -> throw AssertionError("Property must be in class")
        }
    }

    fun IrFunction.createParameterDeclarations(
        receiver: IrValueParameter?,
        overwriteValueParameters: Boolean = false,
        copyTypeParameters: Boolean = true
    ) {
        fun ParameterDescriptor.irValueParameter() = IrValueParameterImpl(
            this@createParameterDeclarations.startOffset, this@createParameterDeclarations.endOffset,
            STM_PLUGIN_ORIGIN,
            this,
            type.toIrType(),
            null
        ).also {
            it.parent = this@createParameterDeclarations
        }

        dispatchReceiverParameter = descriptor.dispatchReceiverParameter?.irValueParameter()
        extensionReceiverParameter = descriptor.extensionReceiverParameter?.irValueParameter()

        if (!overwriteValueParameters)
            assert(valueParameters.isEmpty())
        else
            valueParameters.clear()
        valueParameters.addAll(descriptor.valueParameters.map { it.irValueParameter() })

        assert(typeParameters.isEmpty())
        if (copyTypeParameters) copyTypeParamsFromDescriptor()
    }

    fun IrFunction.copyTypeParamsFromDescriptor() {
        descriptor.typeParameters.mapTo(typeParameters) {
            IrTypeParameterImpl(
                startOffset, endOffset,
                STM_PLUGIN_ORIGIN,
                it
            ).also { typeParameter ->
                typeParameter.parent = this
            }
        }
    }

    fun kClassTypeFor(projection: TypeProjection): SimpleType {
        val kClass = compilerContext.builtIns.kClass
        return KotlinTypeFactory.simpleNotNullType(Annotations.EMPTY, kClass, listOf(projection))
    }

    val KotlinType?.toClassDescriptor: ClassDescriptor?
        get() = this?.constructor?.declarationDescriptor?.let { descriptor ->
            when (descriptor) {
                is ClassDescriptor -> descriptor
                is TypeParameterDescriptor -> descriptor.representativeUpperBound.toClassDescriptor
                else -> null
            }
        }

    fun createClassReference(classType: KotlinType, startOffset: Int, endOffset: Int): IrClassReference {
        val clazz = classType.toClassDescriptor!!
        val returnType =
            kClassTypeFor(TypeProjectionImpl(Variance.INVARIANT, classType))
        return IrClassReferenceImpl(
            startOffset,
            endOffset,
            returnType.toIrType(),
            compilerContext.externalSymbols.referenceClassifier(clazz),
            classType.toIrType()
        )
    }

    fun IrBuilderWithScope.classReference(classType: KotlinType): IrClassReference = createClassReference(classType, startOffset, endOffset)

    fun buildInitializersRemapping(irClass: IrClass): (IrField) -> IrExpression? {
        val original = irClass.constructors.singleOrNull { it.isPrimary }
            ?: throw IllegalStateException("Serializable class must have single primary constructor")
        // default arguments of original constructor
        val defaultsMap: Map<ParameterDescriptor, IrExpression?> =
            original.valueParameters.associate { it.descriptor to it.defaultValue?.expression }
        return fun(f: IrField): IrExpression? {
            val i = f.initializer?.expression ?: return null
            val irExpression =
                if (i is IrGetValueImpl && i.origin == IrStatementOrigin.INITIALIZE_PROPERTY_FROM_PARAMETER) {
                    // this is a primary constructor property, use corresponding default of value parameter
                    defaultsMap.getValue(i.descriptor as ParameterDescriptor)
                } else {
                    i
                }
            return irExpression?.deepCopyWithVariables()
        }
    }

    fun findEnumValuesMethod(enumClass: ClassDescriptor): IrFunction {
        assert(enumClass.kind == ClassKind.ENUM_CLASS)
        return compilerContext.externalSymbols.referenceClass(enumClass).owner.functions
            .find { it.origin == IrDeclarationOrigin.ENUM_CLASS_SPECIAL_MEMBER && it.name == Name.identifier("values") }
            ?: throw AssertionError("Enum class does not have .values() function")
    }

    private fun getEnumMembersNames(enumClass: ClassDescriptor): Sequence<String> {
        assert(enumClass.kind == ClassKind.ENUM_CLASS)
        return enumClass.unsubstitutedMemberScope.getContributedDescriptors().asSequence()
            .filterIsInstance<ClassDescriptor>()
            .filter { it.kind == ClassKind.ENUM_ENTRY }
            .map { it.name.toString() }
    }
}

internal fun BackendContext.createTypeTranslator(moduleDescriptor: ModuleDescriptor): TypeTranslator =
    TypeTranslator(externalSymbols, irBuiltIns.languageVersionSettings, moduleDescriptor.builtIns).apply {
        constantValueGenerator = ConstantValueGenerator(moduleDescriptor, symbolTable = externalSymbols)
        constantValueGenerator.typeTranslator = this
    }

class Generator(val descr: ClassDescriptor, override val compilerContext: BackendContext) : IrBuilderExtension {
    override val translator: TypeTranslator = compilerContext.createTypeTranslator(descr.module)
    private val _table = SymbolTable()
    override val BackendContext.localSymbolTable: SymbolTable
        get() = _table

    fun generatePublish(irClass: IrClass, function: FunctionDescriptor) =
        irClass.contributeFunction(function, fromStubs = true) {}

}

private fun ClassDescriptor.checkPublishMethodResult(type: KotlinType): Boolean =
    KotlinBuiltIns.isInt(type)

private fun ClassDescriptor.checkPublishMethodParameters(parameters: List<ValueParameterDescriptor>): Boolean =
    parameters.size == 0

open class StmIrGenerator {

    companion object {

        private fun getSyntheticPublishMember(descr: ClassDescriptor): FunctionDescriptor? = getMemberToGenerate(
            descr, "publish", descr::checkPublishMethodResult, descr::checkPublishMethodParameters
        )

        fun generate(
            irClass: IrClass,
            context: BackendContext,
            bindingContext: BindingContext
        ) {
            val function = getSyntheticPublishMember(irClass.descriptor) ?: return
            Generator(irClass.descriptor, context).generatePublish(irClass, function)
        }
    }
}
