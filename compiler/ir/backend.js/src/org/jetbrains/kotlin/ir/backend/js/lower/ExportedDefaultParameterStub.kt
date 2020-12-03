/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.js.lower

import org.jetbrains.kotlin.backend.common.DeclarationTransformer
import org.jetbrains.kotlin.backend.common.ir.copyParameterDeclarationsFrom
import org.jetbrains.kotlin.backend.common.ir.passTypeArgumentsFrom
import org.jetbrains.kotlin.backend.common.lower.createIrBuilder
import org.jetbrains.kotlin.backend.common.lower.irBlockBody
import org.jetbrains.kotlin.ir.backend.js.JsIrBackendContext
import org.jetbrains.kotlin.ir.backend.js.JsLoweredDeclarationOrigin
import org.jetbrains.kotlin.ir.backend.js.ir.JsIrBuilder
import org.jetbrains.kotlin.ir.backend.js.utils.JsAnnotations
import org.jetbrains.kotlin.ir.backend.js.utils.hasStableJsName
import org.jetbrains.kotlin.ir.builders.*
import org.jetbrains.kotlin.ir.builders.declarations.buildFun
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.name.FqName

private fun IrConstructorCall.isAnnotation(name: FqName): Boolean {
    return symbol.owner.parentAsClass.fqNameWhenAvailable == name
}

class ExportedDefaultParameterStub(val context: JsIrBackendContext) : DeclarationTransformer {

    override fun transformFlat(declaration: IrDeclaration): List<IrDeclaration>? {
        if (declaration !is IrFunction) {
            return null
        }

        if (declaration is IrConstructor) {
            return null
        }

        if (!declaration.hasStableJsName()) {
            return null
        }

        if (!declaration.valueParameters.any { it.defaultValue != null }) {
            return null
        }

        val fn = context.irFactory.buildFun {
            updateFrom(declaration)
            name = declaration.name
            returnType = declaration.returnType
            origin = JsIrBuilder.SYNTHESIZED_DECLARATION
        }

        fn.parent = declaration.parent
        fn.copyParameterDeclarationsFrom(declaration)
        fn.valueParameters.forEach { it.defaultValue = null }

        declaration.origin = JsLoweredDeclarationOrigin.JS_SHADOWED_EXPORT

        val irBuilder = context.createIrBuilder(fn.symbol, fn.startOffset, fn.endOffset)
        fn.body = irBuilder.irBlockBody(fn) {
            +irReturn(irCall(declaration).apply {
                passTypeArgumentsFrom(declaration)
                dispatchReceiver = fn.dispatchReceiverParameter?.let { irGet(it) }
                extensionReceiver = fn.extensionReceiverParameter?.let { irGet(it) }

                declaration.valueParameters.forEachIndexed { index, irValueParameter ->
                    val defaultValue = irValueParameter.defaultValue

                    val value = if (defaultValue != null) {
                        irIfThenElse(
                            irValueParameter.type,
                            irReferentialEquals(
                                irGet(irValueParameter),
                                irCall(this@ExportedDefaultParameterStub.context.intrinsics.jsUndefined)
                            ),
                            defaultValue.expression,
                            irGet(irValueParameter)
                        )
                    } else {
                        irGet(irValueParameter)
                    }

                    putValueArgument(index, value)
                }
            })
        }

        val (exportAnnotations, irrelevantAnnotations) = declaration.annotations.map { it.deepCopyWithSymbols(declaration as? IrDeclarationParent) }
            .partition {
                it.isAnnotation(JsAnnotations.jsExportFqn) || (it.isAnnotation(JsAnnotations.jsNameFqn))
            }

        declaration.annotations = irrelevantAnnotations
        fn.annotations = exportAnnotations

        return listOf(fn, declaration)
    }
}

