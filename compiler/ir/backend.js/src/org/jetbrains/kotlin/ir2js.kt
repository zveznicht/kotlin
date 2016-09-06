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
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrModule
import org.jetbrains.kotlin.ir.expressions.IrBlockBody
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstKind
import org.jetbrains.kotlin.ir.expressions.IrReturn
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor

private val program = JsProgram("<ir2js>")

fun ir2js(module: IrModule): JsNode =
        module.accept(object : IrElementVisitor<JsNode, Nothing?> {
            override fun visitElement(element: IrElement, data: Nothing?): JsNode {
                TODO("not implemented")
            }

            override fun visitModule(declaration: IrModule, data: Nothing?): JsNode {
                // TODO
                return declaration.files.first().accept(this, data)
            }

            override fun visitFile(declaration: IrFile, data: Nothing?): JsNode {
                val block = JsBlock()
                for (d in declaration.declarations) {
                    // TODO
                    block.statements.add(d.accept(this, data) as JsStatement)
                }
                return block
            }

            override fun visitFunction(declaration: IrFunction, data: Nothing?): JsNode {
                val funName = declaration.descriptor.name.asString()
                val body = declaration.body?.accept(this, data) as JsBlock? ?: JsBlock()
                // TODO
                return JsVars(JsVars.JsVar(program.scope.declareName(funName), JsFunction(JsFunctionScope(program.scope, "scope for $funName"), body, "function $funName")))
            }

            override fun visitBlockBody(body: IrBlockBody, data: Nothing?): JsNode {
                val block = JsBlock()
                for (s in body.statements) {
                    block.statements.add(s.accept(this, data) as JsStatement)
                }
                return block
            }

            override fun visitReturn(expression: IrReturn, data: Nothing?): JsNode {
                return expression.value?.let { JsReturn(it.accept(this, data) as JsExpression) } ?: JsReturn()
            }

            override fun <T> visitConst(expression: IrConst<T>, data: Nothing?): JsNode {
                return when(expression.kind) {
                    IrConstKind.String -> program.getStringLiteral(expression.value as String)
                    IrConstKind.Null -> TODO()
                    IrConstKind.Boolean -> TODO()
                    IrConstKind.Char -> TODO()
                    IrConstKind.Byte -> TODO()
                    IrConstKind.Short -> TODO()
                    IrConstKind.Int -> TODO()
                    IrConstKind.Long -> TODO()
                    IrConstKind.Float -> TODO()
                    IrConstKind.Double -> TODO()
                }
            }

        }, null)
