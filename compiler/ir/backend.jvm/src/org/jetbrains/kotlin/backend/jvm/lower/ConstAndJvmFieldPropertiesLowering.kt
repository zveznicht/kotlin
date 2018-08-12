/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.jvm.lower

import org.jetbrains.kotlin.backend.common.FileLoweringPass
import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.jvm.JvmBackendContext
import org.jetbrains.kotlin.codegen.JvmCodegenUtil
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.declarations.impl.IrExternalPackageFragmentImpl
import org.jetbrains.kotlin.ir.declarations.impl.IrFieldImpl
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrGetFieldImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrSetFieldImpl
import org.jetbrains.kotlin.ir.symbols.IrExternalPackageFragmentSymbol
import org.jetbrains.kotlin.ir.symbols.impl.IrExternalPackageFragmentSymbolImpl
import org.jetbrains.kotlin.ir.types.toIrType
import org.jetbrains.kotlin.ir.util.referenceFunction
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid
import org.jetbrains.kotlin.synthetic.SyntheticJavaPropertyDescriptor

val JvmBackendContext.getFieldForProperty: (PropertyDescriptor) -> IrField by JvmBackendContext.lazyMapMember { descriptor ->
    IrFieldImpl(
        UNDEFINED_OFFSET,
        UNDEFINED_OFFSET,
        IrDeclarationOrigin.PROPERTY_BACKING_FIELD,
        descriptor,
        descriptor.type.toIrType()!!
    ).apply {
        getParent(descriptor)?.let { parent = it }
    }
}

fun JvmBackendContext.getParent(descriptor: PropertyDescriptor): IrDeclarationParent? {
    val containingDeclaration = descriptor.containingDeclaration
    return when (containingDeclaration) {
        is ClassDescriptor -> ir.symbols.symbolTable.referenceClass(containingDeclaration).owner
        is FunctionDescriptor -> ir.symbols.symbolTable.referenceDeclaredFunction(containingDeclaration).owner
        // For package fragments, the only parameter relevant for accessibility is `fqName`. No danger in creating extra ones.
        is PackageFragmentDescriptor -> IrExternalPackageFragmentImpl(IrExternalPackageFragmentSymbolImpl(containingDeclaration))
        else -> null
    }
}

class ConstAndJvmFieldPropertiesLowering(val context: JvmBackendContext) : IrElementTransformerVoid(), FileLoweringPass {
    override fun lower(irFile: IrFile) {
        irFile.transformChildrenVoid(this)
    }

    override fun visitProperty(declaration: IrProperty): IrStatement {
        if (JvmCodegenUtil.isConstOrHasJvmFieldAnnotation(declaration.descriptor)) {
            /*Safe or need copy?*/
            declaration.getter = null
            declaration.setter = null
        }
        return super.visitProperty(declaration)
    }

    override fun visitCall(expression: IrCall): IrExpression {
        val descriptor = expression.descriptor as? PropertyAccessorDescriptor ?: return super.visitCall(expression)

        val property = descriptor.correspondingProperty
        if (JvmCodegenUtil.isConstOrHasJvmFieldAnnotation(property)) {
            return if (descriptor is PropertyGetterDescriptor) {
                substituteGetter(descriptor, expression)
            } else {
                substituteSetter(descriptor, expression)
            }
        } else if (property is SyntheticJavaPropertyDescriptor) {
            expression.dispatchReceiver = expression.extensionReceiver
            expression.extensionReceiver = null
        }
        return super.visitCall(expression)
    }

    private fun substituteSetter(descriptor: PropertyAccessorDescriptor, expression: IrCall): IrSetFieldImpl {
        val property = descriptor.correspondingProperty
        val fieldSymbol = context.getFieldForProperty(property).symbol

        return IrSetFieldImpl(
            expression.startOffset,
            expression.endOffset,
            fieldSymbol,
            expression.dispatchReceiver,
            expression.getValueArgument(descriptor.valueParameters.lastIndex)!!,
            expression.type,
            expression.origin,
            expression.superQualifierSymbol
        )
    }

    private fun substituteGetter(descriptor: PropertyGetterDescriptor, expression: IrCall): IrGetFieldImpl {
        val property = descriptor.correspondingProperty
        val fieldSymbol = context.getFieldForProperty(property).symbol
        return IrGetFieldImpl(
            expression.startOffset,
            expression.endOffset,
            fieldSymbol,
            expression.type,
            expression.dispatchReceiver,
            expression.origin,
            expression.superQualifierSymbol
        )
    }
}
