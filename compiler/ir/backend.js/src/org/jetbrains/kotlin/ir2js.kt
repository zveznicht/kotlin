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

import com.google.dart.compiler.backend.js.ast.*
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrModule
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.util.dump
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor

private val program = JsProgram("<ir2js>")

val RECEIVER = "\$receiver"

private fun TODO(element: IrElement): Nothing = TODO(element.dump())

fun ir2js(module: IrModule): JsNode {
    return module.accept(object : IrElementVisitor<JsNode, Nothing?> {
        override fun visitElement(element: IrElement, data: Nothing?): JsNode {
            TODO(element)
        }

        override fun visitModule(declaration: IrModule, data: Nothing?): JsNode {
            // TODO
            return declaration.files.first().accept(this, data)
        }

        override fun visitFile(declaration: IrFile, data: Nothing?): JsNode {
            val block = JsBlock()
            for (d in declaration.declarations) {
                // TODO
                block.statements.add(d.accept(DeclarationGenerator(), data) as JsStatement)
            }
            return block
        }

    }, null)
}

typealias Data = Nothing?

interface DummyGenerator<T> : IrElementVisitor<T, Data> {
    override fun visitElement(element: IrElement, data: Data): T {
        TODO(element.dump())
    }
}

class FileGenerator : IrElementVisitor<JsNode, Data>, DummyGenerator<JsNode>
class DeclarationGenerator : IrElementVisitor<JsNode, Data>, DummyGenerator<JsNode> {
    // TODO
    override fun visitDeclaration(declaration: IrDeclaration, data: Data) = JsEmpty

    override fun visitFunction(declaration: IrFunction, data: Nothing?): JsNode {
        val funName = declaration.descriptor.name.asString()
        val body = declaration.body?.accept(this, data) as JsBlock? ?: JsBlock()
        val function = JsFunction(JsFunctionScope(program.scope, "scope for $funName"), body, "function $funName")

        fun JsFunction.addParameter(parameterName: String) {
            val parameter = function.scope.declareName(parameterName)
            parameters.add(JsParameter(parameter))
        }

        val descriptor = declaration.descriptor
        descriptor.valueParameters.forEach {
            function.addParameter(it.name.asString())
        }
        descriptor.extensionReceiverParameter?.let { function.addParameter(RECEIVER) }

        // TODO
        return JsVars(JsVars.JsVar(program.scope.declareName(funName), function))
    }

    override fun visitBlockBody(body: IrBlockBody, data: Nothing?): JsNode {
        val block = JsBlock()
        for (s in body.statements) {
            block.statements.add(s.accept(StatementGenerator(), data))
        }
        return block
    }
}

class StatementGenerator : IrElementVisitor<JsStatement, Data>, DummyGenerator<JsStatement> {
//    fun visitElement(element: IrElement, data: D): R
//    fun visitModule(declaration: IrModule, data: D) = visitElement(declaration, data)
//    fun visitFile(declaration: IrFile, data: D) = visitElement(declaration, data)
//
//    fun visitDeclaration(declaration: IrDeclaration, data: D) = visitElement(declaration, data)
//    fun visitClass(declaration: IrClass, data: D) = visitDeclaration(declaration, data)
//    fun visitTypeAlias(declaration: IrTypeAlias, data: D) = visitDeclaration(declaration, data)
//    fun visitGeneralFunction(declaration: IrGeneralFunction, data: D) = visitDeclaration(declaration, data)
//    fun visitFunction(declaration: IrFunction, data: D) = visitGeneralFunction(declaration, data)
//    fun visitPropertyGetter(declaration: IrPropertyGetter, data: D) = visitGeneralFunction(declaration, data)
//    fun visitPropertySetter(declaration: IrPropertySetter, data: D) = visitGeneralFunction(declaration, data)
//    fun visitConstructor(declaration: IrConstructor, data: D) = visitGeneralFunction(declaration, data)
//    fun visitProperty(declaration: IrProperty, data: D) = visitDeclaration(declaration, data)
//    fun visitSimpleProperty(declaration: IrSimpleProperty, data: D) = visitProperty(declaration, data)
//    fun visitDelegatedProperty(declaration: IrDelegatedProperty, data: D) = visitProperty(declaration, data)
//    fun visitLocalDelegatedProperty(declaration: IrLocalDelegatedProperty, data: D) = visitDeclaration(declaration, data)
//    fun visitLocalPropertyAccessor(declaration: IrLocalPropertyAccessor, data: D) = visitGeneralFunction(declaration, data)
//    fun visitVariable(declaration: IrVariable, data: D) = visitDeclaration(declaration, data)
//    fun visitEnumEntry(declaration: IrEnumEntry, data: D) = visitDeclaration(declaration, data)
//    fun visitAnonymousInitializer(declaration: IrAnonymousInitializer, data: D) = visitDeclaration(declaration, data)
//
//    fun visitBody(body: IrBody, data: D) = visitElement(body, data)
//    fun visitExpressionBody(body: IrExpressionBody, data: D) = visitBody(body, data)
//    fun visitBlockBody(body: IrBlockBody, data: D) = visitBody(body, data)
//    fun visitSyntheticBody(body: IrSyntheticBody, data: D) = visitBody(body, data)
//
    override fun visitExpression(expression: IrExpression, data: Data): JsStatement {
        return JsExpressionStatement(expression.accept(ExpressionGenerator(), data))
    }
//    override fun <T> visitConst(expression: IrConst<T>, data: Nothing?): JsExpression {
//    fun visitVararg(expression: IrVararg, data: D) = visitExpression(expression, data)
//    fun visitSpreadElement(spread: IrSpreadElement, data: D) = visitElement(spread, data)
//
    override fun visitBlock(expression: IrBlock, data: Data): JsBlock {
        return JsBlock(expression.statements.map { it.accept(this, data) })
    }

//    fun visitStringConcatenation(expression: IrStringConcatenation, data: D) = visitExpression(expression, data)
//    fun visitThisReference(expression: IrThisReference, data: D) = visitExpression(expression, data)
//
//    fun visitDeclarationReference(expression: IrDeclarationReference, data: D) = visitExpression(expression, data)
//    fun visitSingletonReference(expression: IrGetSingletonValue, data: D) = visitDeclarationReference(expression, data)
//    fun visitGetObjectValue(expression: IrGetObjectValue, data: D) = visitSingletonReference(expression, data)
//    fun visitGetEnumValue(expression: IrGetEnumValue, data: D) = visitSingletonReference(expression, data)
//    fun visitVariableAccess(expression: IrVariableAccessExpression, data: D) = visitDeclarationReference(expression, data)
//    override fun visitGetVariable(expression: IrGetVariable, data: Data): JsExpression {
//    override fun visitSetVariable(expression: IrSetVariable, data: Data): JsExpression {
//    fun visitBackingFieldReference(expression: IrBackingFieldExpression, data: D) = visitDeclarationReference(expression, data)
//    fun visitGetBackingField(expression: IrGetBackingField, data: D) = visitBackingFieldReference(expression, data)
//    fun visitSetBackingField(expression: IrSetBackingField, data: D) = visitBackingFieldReference(expression, data)
//    fun visitGetExtensionReceiver(expression: IrGetExtensionReceiver, data: D) = visitDeclarationReference(expression, data)
//    fun visitGeneralCall(expression: IrGeneralCall, data: D) = visitDeclarationReference(expression, data)
//    override fun visitCall(expression: IrCall, data: Data): JsExpression {
//    fun visitDelegatingConstructorCall(expression: IrDelegatingConstructorCall, data: D) = visitGeneralCall(expression, data)
//    fun visitEnumConstructorCall(expression: IrEnumConstructorCall, data: D) = visitGeneralCall(expression, data)
//    fun visitGetClass(expression: IrGetClass, data: D) = visitExpression(expression, data)
//
//    fun visitCallableReference(expression: IrCallableReference, data: D) = visitGeneralCall(expression, data)
//    fun visitClassReference(expression: IrClassReference, data: D) = visitDeclarationReference(expression, data)
//
//    fun visitInstanceInitializerCall(expression: IrInstanceInitializerCall, data: D) = visitExpression(expression, data)
//
//    fun visitTypeOperator(expression: IrTypeOperatorCall, data: D) = visitExpression(expression, data)


    override fun visitWhen(expression: IrWhen, data: Data): JsStatement {
        return (0 until expression.branchesCount).reversed().fold(expression.elseBranch?.accept(this, data)) { st, i ->
            JsIf(
                    expression.getNthCondition(i)!!.accept(ExpressionGenerator(), data),
                    expression.getNthResult(i)!!.accept(this, data),
                    st)
        }!!
    }

//    fun visitLoop(loop: IrLoop, data: D) = visitExpression(loop, data)
//    fun visitWhileLoop(loop: IrWhileLoop, data: D) = visitLoop(loop, data)
//    fun visitDoWhileLoop(loop: IrDoWhileLoop, data: D) = visitLoop(loop, data)

    override fun visitTryCatch(tryCatch: IrTryCatch, data: Data): JsStatement {
        val tryBlock = tryCatch.tryResult.accept(this, data)

        val catchBlocks = (0 until tryCatch.catchClausesCount).map {
            // why nullable?
            val catchParameter = tryCatch.getNthCatchParameter(it)!!
            val catchBody = tryCatch.getNthCatchResult(it)?.accept(this, data)!!

            // TODO set condition
            JsCatch(program.scope, catchParameter.name.asString(), catchBody)
        }

        val finallyBlock = tryCatch.finallyExpression?.accept(this, data)

        return JsTry(/*TODO*/ tryBlock as JsBlock, catchBlocks, /*TODO*/finallyBlock as JsBlock)
    }


    override fun visitBreak(jump: IrBreak, data: Data): JsStatement {
        return JsBreak(jump.label?.let(::JsNameRef))
    }

    override fun visitContinue(jump: IrContinue, data: Data): JsStatement {
        return JsContinue(jump.label?.let(::JsNameRef))
    }


    override fun visitReturn(expression: IrReturn, data: Data): JsStatement {
        return expression.value?.let { JsReturn(it.accept(ExpressionGenerator(), data)) } ?: JsReturn()
    }
    override fun visitThrow(expression: IrThrow, data: Data): JsStatement {
        return JsThrow(expression.value.accept(ExpressionGenerator(), data))
    }
//
//    fun visitErrorDeclaration(declaration: IrErrorDeclaration, data: D) = visitDeclaration(declaration, data)
//    fun visitErrorExpression(expression: IrErrorExpression, data: D) = visitExpression(expression, data)
//    fun visitErrorCallExpression(expression: IrErrorCallExpression, data: D) = visitErrorExpression(expression, data)

}

class ExpressionGenerator : IrElementVisitor<JsExpression, Data>, DummyGenerator<JsExpression> {
//    fun visitElement(element: IrElement, data: D): R
//    fun visitModule(declaration: IrModule, data: D) = visitElement(declaration, data)
//    fun visitFile(declaration: IrFile, data: D) = visitElement(declaration, data)
//
//    fun visitDeclaration(declaration: IrDeclaration, data: D) = visitElement(declaration, data)
//    fun visitClass(declaration: IrClass, data: D) = visitDeclaration(declaration, data)
//    fun visitTypeAlias(declaration: IrTypeAlias, data: D) = visitDeclaration(declaration, data)
//    fun visitGeneralFunction(declaration: IrGeneralFunction, data: D) = visitDeclaration(declaration, data)
//    fun visitFunction(declaration: IrFunction, data: D) = visitGeneralFunction(declaration, data)
//    fun visitPropertyGetter(declaration: IrPropertyGetter, data: D) = visitGeneralFunction(declaration, data)
//    fun visitPropertySetter(declaration: IrPropertySetter, data: D) = visitGeneralFunction(declaration, data)
//    fun visitConstructor(declaration: IrConstructor, data: D) = visitGeneralFunction(declaration, data)
//    fun visitProperty(declaration: IrProperty, data: D) = visitDeclaration(declaration, data)
//    fun visitSimpleProperty(declaration: IrSimpleProperty, data: D) = visitProperty(declaration, data)
//    fun visitDelegatedProperty(declaration: IrDelegatedProperty, data: D) = visitProperty(declaration, data)
//    fun visitLocalDelegatedProperty(declaration: IrLocalDelegatedProperty, data: D) = visitDeclaration(declaration, data)
//    fun visitLocalPropertyAccessor(declaration: IrLocalPropertyAccessor, data: D) = visitGeneralFunction(declaration, data)
//    fun visitVariable(declaration: IrVariable, data: D) = visitDeclaration(declaration, data)
//    fun visitEnumEntry(declaration: IrEnumEntry, data: D) = visitDeclaration(declaration, data)
//    fun visitAnonymousInitializer(declaration: IrAnonymousInitializer, data: D) = visitDeclaration(declaration, data)
//
//    fun visitBody(body: IrBody, data: D) = visitElement(body, data)
//    fun visitExpressionBody(body: IrExpressionBody, data: D) = visitBody(body, data)
//    fun visitBlockBody(body: IrBlockBody, data: D) = visitBody(body, data)
//    fun visitSyntheticBody(body: IrSyntheticBody, data: D) = visitBody(body, data)
//
//    fun visitExpression(expression: IrExpression, data: D) = visitElement(expression, data)
    override fun <T> visitConst(expression: IrConst<T>, data: Data): JsExpression {
        return when (expression.kind) {
            IrConstKind.String -> program.getStringLiteral(expression.value as String)
            IrConstKind.Null -> JsLiteral.NULL
            IrConstKind.Boolean -> if (expression.value as Boolean) JsLiteral.TRUE else JsLiteral.FALSE
            IrConstKind.Char -> program.getNumberLiteral((expression.value as Char).toInt()) // TODO
            IrConstKind.Byte -> program.getNumberLiteral((expression.value as Byte).toInt())
            IrConstKind.Short -> program.getNumberLiteral((expression.value as Short).toInt())
            IrConstKind.Int -> program.getNumberLiteral(expression.value as Int)
            IrConstKind.Long -> program.getNumberLiteral((expression.value as Long).toDouble()) // TODO
            IrConstKind.Float -> program.getNumberLiteral((expression.value as Float).toDouble())
            IrConstKind.Double -> program.getNumberLiteral((expression.value as Float).toDouble())
        }
    }
//    fun visitVararg(expression: IrVararg, data: D) = visitExpression(expression, data)
//    fun visitSpreadElement(spread: IrSpreadElement, data: D) = visitElement(spread, data)
//
//    fun visitBlock(expression: IrBlock, data: D) = visitExpression(expression, data)
//    fun visitStringConcatenation(expression: IrStringConcatenation, data: D) = visitExpression(expression, data)
//    fun visitThisReference(expression: IrThisReference, data: D) = visitExpression(expression, data)
//
//    fun visitDeclarationReference(expression: IrDeclarationReference, data: D) = visitExpression(expression, data)
//    fun visitSingletonReference(expression: IrGetSingletonValue, data: D) = visitDeclarationReference(expression, data)
//    fun visitGetObjectValue(expression: IrGetObjectValue, data: D) = visitSingletonReference(expression, data)
//    fun visitGetEnumValue(expression: IrGetEnumValue, data: D) = visitSingletonReference(expression, data)
//    fun visitVariableAccess(expression: IrVariableAccessExpression, data: D) = visitDeclarationReference(expression, data)
    override fun visitGetVariable(expression: IrGetVariable, data: Data): JsExpression {
        return JsNameRef(expression.descriptor.name.asString())
    }

    override fun visitSetVariable(expression: IrSetVariable, data: Data): JsExpression {
        val v = JsNameRef(expression.descriptor.name.asString())
        val value = expression.value.accept(this, data)
        return JsBinaryOperation(JsBinaryOperator.ASG, v, value)
    }
//    fun visitBackingFieldReference(expression: IrBackingFieldExpression, data: D) = visitDeclarationReference(expression, data)
//    fun visitGetBackingField(expression: IrGetBackingField, data: D) = visitBackingFieldReference(expression, data)
//    fun visitSetBackingField(expression: IrSetBackingField, data: D) = visitBackingFieldReference(expression, data)
    override fun visitGetExtensionReceiver(expression: IrGetExtensionReceiver, data: Data): JsExpression {
        // TODO receiver
        return JsNameRef(RECEIVER)
    }
//    fun visitGeneralCall(expression: IrGeneralCall, data: D) = visitDeclarationReference(expression, data)
    override fun visitCall(expression: IrCall, data: Data): JsExpression {
        val dispatchReceiver = expression.dispatchReceiver?.accept(this, data)
        val extensionReceiver = expression.extensionReceiver?.accept(this, data)
        val ref = JsNameRef(expression.descriptor.name.asString(), dispatchReceiver)
        val arguments =
                // TODO mapTo
                expression.descriptor.valueParameters.map {
                    expression.getArgument(it.index)?.accept(this, data) ?: JsPrefixOperation(JsUnaryOperator.VOID, program.getNumberLiteral(1))
                }

        return JsInvocation(ref, extensionReceiver?.let { listOf(extensionReceiver) + arguments } ?: arguments)
    }
//    fun visitDelegatingConstructorCall(expression: IrDelegatingConstructorCall, data: D) = visitGeneralCall(expression, data)
//    fun visitEnumConstructorCall(expression: IrEnumConstructorCall, data: D) = visitGeneralCall(expression, data)
//    fun visitGetClass(expression: IrGetClass, data: D) = visitExpression(expression, data)
//
//    fun visitCallableReference(expression: IrCallableReference, data: D) = visitGeneralCall(expression, data)
//    fun visitClassReference(expression: IrClassReference, data: D) = visitDeclarationReference(expression, data)
//
//    fun visitInstanceInitializerCall(expression: IrInstanceInitializerCall, data: D) = visitExpression(expression, data)
//
//    fun visitTypeOperator(expression: IrTypeOperatorCall, data: D) = visitExpression(expression, data)
//
//    fun visitWhen(expression: IrWhen, data: D) = visitExpression(expression, data)
//    fun visitLoop(loop: IrLoop, data: D) = visitExpression(loop, data)
//    fun visitWhileLoop(loop: IrWhileLoop, data: D) = visitLoop(loop, data)
//    fun visitDoWhileLoop(loop: IrDoWhileLoop, data: D) = visitLoop(loop, data)
//    fun visitTryCatch(tryCatch: IrTryCatch, data: D) = visitExpression(tryCatch, data)
//
//    fun visitErrorDeclaration(declaration: IrErrorDeclaration, data: D) = visitDeclaration(declaration, data)
//    fun visitErrorExpression(expression: IrErrorExpression, data: D) = visitExpression(expression, data)
//    fun visitErrorCallExpression(expression: IrErrorCallExpression, data: D) = visitErrorExpression(expression, data)
}

/*
rewrite on ir:
* when expressions
* try-catch
* return, break, continue
* block expressions
*
* class

*/
