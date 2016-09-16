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

package org.jetbrains.kotlin.transformers

import org.jetbrains.kotlin.ir.descriptors.IrBuiltIns
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin
import org.jetbrains.kotlin.ir.expressions.impl.IrBinaryPrimitiveImpl
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

class StringMemberTransformer(private val irBuiltIns: IrBuiltIns) : IrElementTransformerVoid {
    override fun visitCall(expression: IrCall): IrExpression {
        if (expression.descriptor.containingDeclaration == irBuiltIns.builtIns.string && expression.descriptor.name.asString() == "plus") {
            val argument0 = expression.dispatchReceiver!!
            val argument1 = expression.getArgument(0)!!

            return IrBinaryPrimitiveImpl(expression.startOffset, expression.endOffset, expression.origin ?: IrStatementOrigin.PLUS, expression.descriptor, argument0, argument1)
        }

        if (expression.descriptor == irBuiltIns.booleanNot) {
            return expression.getArgument(0)!!
        }

        return super.visitCall(expression)
    }

}
