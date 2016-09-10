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
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.detach
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.expressions.impl.IrCompositeImpl
import org.jetbrains.kotlin.ir.replaceWith
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid

class ExpressionBlockExtractor : IrElementVisitorVoid {
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
//        var tmp = {}
//        var foo = tmp
        // TODO
        val initializer = declaration.initializer
        if (initializer !is IrBlock) return

        initializer.statements.forEach { it.detach() }

        initializer.replaceWith(initializer.statements.last())

        val oldParent = declaration.parent
        val oldSlot = declaration.slot

        declaration.detach()

        val newEl = IrCompositeImpl(
                declaration.startOffset,
                declaration.endOffset,
                declaration.descriptor.type,
                null,
                initializer.statements.subList(0, initializer.statements.lastIndex) + listOf(declaration))

        oldParent?.replaceChild(oldSlot, newEl)
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

    private fun extractBlockIfNeed(expression: IrExpression, value: IrExpression?) {
        if (value is IrBlock) {
            val lastChild = value.statements.last()
            val lastChildSlot = lastChild.slot
            value.replaceWith(lastChild)
            expression.replaceWith(value)
            value.replaceChild(lastChildSlot, expression)
        }
    }

    override fun visitReturn(expression: IrReturn) {
        super.visitReturn(expression)
        extractBlockIfNeed(expression, expression.value)
    }

    override fun visitThrow(expression: IrThrow) {
        super.visitThrow(expression)
        extractBlockIfNeed(expression, expression.value)
    }
}

//ISSUE: inline typealias intention

// IrBlock
// endVisit

