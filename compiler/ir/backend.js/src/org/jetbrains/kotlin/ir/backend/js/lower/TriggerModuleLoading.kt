/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.js.lower

import org.jetbrains.kotlin.backend.common.BodyLoweringPass
import org.jetbrains.kotlin.backend.common.ir.isTopLevel
import org.jetbrains.kotlin.backend.common.lower.createIrBuilder
import org.jetbrains.kotlin.backend.common.lower.irIfThen
import org.jetbrains.kotlin.backend.common.lower.irNot
import org.jetbrains.kotlin.backend.common.runOnFilePostfix
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.backend.js.JsIrBackendContext
import org.jetbrains.kotlin.ir.builders.*
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.name.FqName

@OptIn(ObsoleteDescriptorBasedAPI::class)
class TriggerModuleLoading(val context: JsIrBackendContext): BodyLoweringPass {

    override fun lower(irFile: IrFile) {
        runOnFilePostfix(irFile)
    }

    override fun lower(irBody: IrBody, container: IrDeclaration) {
        if (container is IrSimpleFunction && container.isSuspend) {
            insertModuleLoadingInSuspendFun(irBody, container)
        } else {
            irBody.accept(SpecialFunctionsFinder(), null)
        }
    }

    private fun insertModuleLoadingInSuspendFun(irBody: IrBody, container: IrDeclaration) {
        if (container !is IrSimpleFunction || !container.isSuspend) return

        val currentModule = container.module
        val lazyModules = irBody.collectReferencedModules(currentModule).filter { context.shouldLazyLoad(currentModule, it) }
            .map { it.name.asString() }

        val backendContext = context

        val additionalStatements =
            lazyModules.map { moduleName ->
                context.createIrBuilder((container as IrSymbolDeclaration<*>).symbol).buildStatement(UNDEFINED_OFFSET, UNDEFINED_OFFSET) {
                    irCall(backendContext.loadModule).apply {
                        putValueArgument(0, irString(currentModule.name.asString()))
                        putValueArgument(1, irString(moduleName))
                    }
                }
            }

        addStatements(irBody, container, additionalStatements)
    }

    private fun addStatements(irBody: IrBody, container: IrDeclaration, additionalStatements: List<IrStatement>) {
        when (irBody) {
            is IrBlockBody -> irBody.statements.addAll(0, additionalStatements)
            is IrExpressionBody -> irBody.expression = context.createIrBuilder((container as IrSymbolDeclaration<*>).symbol).irComposite {
                additionalStatements.forEach { +it }
                +irBody.expression
            }
        }
    }

    private fun insertModuleLoadingInLambda(irBody: IrBody, container: IrFunction, triggerModuleLoading: Boolean) {

        // TODO find a better way to avoid unit materialization
        container.returnType = context.dynamicType

        val currentModule = container.moduleOrNull ?: return
        val lazyModules = irBody.collectReferencedModules(currentModule).filter { context.shouldLazyLoad(currentModule, it) }
            .map { it.name.asString() }

        val backendContext = context

        val additionalStatements =
            lazyModules.map { moduleName ->
                context.createIrBuilder((container as IrSymbolDeclaration<*>).symbol).buildStatement(UNDEFINED_OFFSET, UNDEFINED_OFFSET) {
                    irIfThen(irNot(irCall(backendContext.isLoaded).apply {
                        putValueArgument(0, irString(currentModule.name.asString()))
                        putValueArgument(1, irString(moduleName))
                        putValueArgument(2, irBoolean(triggerModuleLoading))
                    }), irReturn(irGetObject(backendContext.moduleNotLoaded)))
                }
            }

        addStatements(irBody, container, additionalStatements)
    }

    private val AllowAsyncRefsFqn = FqName("kotlin.js.AllowAsyncRefs")
    private val TriggerModuleLoadingFqn = FqName("kotlin.js.TriggerModuleLoading")

    private fun IrElement.collectReferencedModules(currentModule: ModuleDescriptor): Set<ModuleDescriptor> {
        val result = mutableSetOf<ModuleDescriptor>()

        accept(object : SpecialFunctionsFinder() {

            override fun visitElement(element: IrElement) {
                element.acceptChildrenVoid(this)
            }

            override fun visitGetObjectValue(expression: IrGetObjectValue) {
                super.visitGetObjectValue(expression)

                expression.process()
            }

            override fun visitGetField(expression: IrGetField) {
                super.visitGetField(expression)

                expression.process()
            }

            override fun visitSetField(expression: IrSetField) {
                super.visitSetField(expression)

                expression.process()
            }

            override fun visitCall(expression: IrCall) {
                super.visitCall(expression)

                expression.process()
            }

            override fun visitCallableReference(expression: IrCallableReference<*>) {
                super.visitCallableReference(expression)

                expression.process()
            }

            override fun visitDeclarationReference(expression: IrDeclarationReference) {
                super.visitDeclarationReference(expression)

                expression.process()
            }

            private fun IrDeclarationReference.process() {
                val declaration = symbol.owner as IrDeclaration

                val declarationToCheck = when (declaration) {
                    is IrSimpleFunction -> declaration.correspondingPropertySymbol?.owner ?: declaration
                    is IrField -> declaration.correspondingPropertySymbol?.owner ?: declaration
                    else -> declaration
                }

                if (!declarationToCheck.isTopLevelDeclaration) return

                val module = declaration.moduleOrNull ?: return
                if (module == currentModule) return

                result += module
            }
        }, null)

        return result
    }

    private open inner class SpecialFunctionsFinder: IrElementVisitorVoid {

        private val specialLambdas = mutableMapOf<IrFunctionExpression, Boolean>()

        override fun visitElement(element: IrElement) {
            element.acceptChildrenVoid(this)
        }

        override fun visitCall(expression: IrCall) {
            expression.symbol.owner.valueParameters.forEachIndexed { index, irValueParameter ->
                if (irValueParameter.annotations.any {
                        it.symbol.owner.constructedClass.fqNameWhenAvailable == AllowAsyncRefsFqn
                    }) {
                    specialLambdas[expression.getValueArgument(index) as IrFunctionExpression] = irValueParameter.annotations.any {
                        it.symbol.owner.constructedClass.fqNameWhenAvailable == TriggerModuleLoadingFqn
                    }
                }
            }

            super.visitCall(expression)
        }

        override fun visitFunctionExpression(expression: IrFunctionExpression) {
            specialLambdas[expression]?.let { triggerLoading ->
                expression.function.let { fn ->
                    fn.body?.let { body ->
                        insertModuleLoadingInLambda(body, fn, triggerLoading)
                        return
                    }
                }
            }

            super.visitFunctionExpression(expression)
        }

        override fun visitFunction(declaration: IrFunction) {
            if (declaration.isSuspend) {
                declaration.body?.let { body ->
                    insertModuleLoadingInSuspendFun(body, declaration)
                    return
                }
            }

            super.visitFunction(declaration)
        }
    }
}

@ObsoleteDescriptorBasedAPI
private val IrDeclaration.moduleOrNull: ModuleDescriptor?
    get() = try { this.module } catch (_: Throwable) { null }