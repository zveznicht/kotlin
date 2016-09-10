/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.descriptors.IrBuiltIns
import org.jetbrains.kotlin.ir.detach
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.expressions.impl.IrCompositeImpl
import org.jetbrains.kotlin.ir.replaceWith
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.types.KotlinType

class ExpressionBlockExtractor(private val irBuiltIns: IrBuiltIns) : IrElementVisitorVoid {
    var changed = false

    override fun visitElement(element: IrElement) {
        element.acceptChildrenVoid(this)
//        TODO("not implemented")
    }

    override fun visitFunction(declaration: IrFunction) {
        //TODO default
        super.visitFunction(declaration)
    }

    override fun visitSimpleProperty(declaration: IrSimpleProperty) {
        // TODO initializer
        super.visitSimpleProperty(declaration)
    }

    override fun visitDelegatedProperty(declaration: IrDelegatedProperty) {
        // TODO ?
        super.visitDelegatedProperty(declaration)
    }

    override fun visitLocalDelegatedProperty(declaration: IrLocalDelegatedProperty) {
        // TODO ?
        super.visitLocalDelegatedProperty(declaration)
    }

    override fun visitVariable(declaration: IrVariable) {
        super.visitVariable(declaration)
        extractIrBlockIfNeed(declaration, declaration.initializer, declaration.descriptor.type)
    }

    override fun visitExpressionBody(body: IrExpressionBody) {
        //TODO ?
        super.visitExpressionBody(body)
    }

    override fun visitVararg(expression: IrVararg) {
        // TODO ?
        super.visitVararg(expression)
    }

    override fun visitSpreadElement(spread: IrSpreadElement) {
        // TODO ?
        super.visitSpreadElement(spread)
    }

    override fun visitStringConcatenation(expression: IrStringConcatenation) {
        //
        super.visitStringConcatenation(expression)
    }

    override fun visitSetVariable(expression: IrSetVariable) {
        // expression.value
        super.visitSetVariable(expression)
    }

    override fun visitSetBackingField(expression: IrSetBackingField) {
        // expression.value
        super.visitSetBackingField(expression)
    }

    override fun visitGeneralCall(expression: IrGeneralCall) {
        //expression.arguments
        super.visitGeneralCall(expression)
    }

//    override fun visitCall(expression: IrCall) {
//        super.visitCall(expression)
//    }
//
//    override fun visitDelegatingConstructorCall(expression: IrDelegatingConstructorCall) {
//        super.visitDelegatingConstructorCall(expression)
//    }
//
//    override fun visitEnumConstructorCall(expression: IrEnumConstructorCall) {
//        super.visitEnumConstructorCall(expression)
//    }

    override fun visitTypeOperator(expression: IrTypeOperatorCall) {
        //TODO ?
        super.visitTypeOperator(expression)
    }

    override fun visitWhen(expression: IrWhen) {
        super.visitWhen(expression)
    }

    override fun visitLoop(loop: IrLoop) {
        // condition
        super.visitLoop(loop)
    }

//    override fun visitWhileLoop(loop: IrWhileLoop) {
//        super.visitWhileLoop(loop)
//    }
//
//    override fun visitDoWhileLoop(loop: IrDoWhileLoop) {
//        super.visitDoWhileLoop(loop)
//    }

    override fun visitReturn(expression: IrReturn) {
        super.visitReturn(expression)
        // TODO try to reuse block?
        extractIrBlockIfNeed(expression, expression.value)
    }

    override fun visitThrow(expression: IrThrow) {
        super.visitThrow(expression)
        extractIrBlockIfNeed(expression, expression.value)
    }

    private fun extractIrBlockIfNeed(expression: IrStatement, elementToReplace: IrExpression?, type: KotlinType = irBuiltIns.nothing) {
        if (elementToReplace is IrBlock) {
            changed = true

            extractIrBlock(expression, elementToReplace)
        }
    }

    private fun extractIrBlock(declaration: IrStatement, elementToReplace: IrBlock, type: KotlinType = irBuiltIns.nothing) {
        val oldParent = declaration.parent
        val oldSlot = declaration.slot

        declaration.detach()
        elementToReplace.statements.forEach { it.detach() }

        val last = elementToReplace.statements.last()
        last.detach()
        elementToReplace.replaceWith(last)

        val newEl = IrCompositeImpl(
                declaration.startOffset,
                declaration.endOffset,
                type,
                null,
                elementToReplace.statements.subList(0, elementToReplace.statements.lastIndex) + listOf(declaration))

        oldParent?.replaceChild(oldSlot, newEl)
        declaration.setTreeLocation(newEl, newEl.statements.lastIndex)
    }
}
