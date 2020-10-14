/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen

import org.jetbrains.kotlin.codegen.context.IndyLambdaContext
import org.jetbrains.kotlin.codegen.inline.FieldRemapper
import org.jetbrains.kotlin.codegen.inline.LocalVarRemapper
import org.jetbrains.kotlin.codegen.inline.ParametersBuilder
import org.jetbrains.kotlin.codegen.inline.RemapVisitor
import org.jetbrains.kotlin.codegen.state.GenerationState
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.jvm.AsmTypes
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin
import org.jetbrains.kotlin.resolve.jvm.diagnostics.OtherOrigin
import org.jetbrains.org.objectweb.asm.Handle
import org.jetbrains.org.objectweb.asm.MethodVisitor
import org.jetbrains.org.objectweb.asm.Opcodes
import org.jetbrains.org.objectweb.asm.Type
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter
import org.jetbrains.org.objectweb.asm.tree.MethodNode

class IndyLambdaCodegen(
    val state: GenerationState,
    val element: KtElement,
    val samType: SamType?,
    val context: IndyLambdaContext,
    val functionReferenceCall: ResolvedCall<FunctionDescriptor>?,
    val strategy: FunctionGenerationStrategy,
    val classBuilder: ClassBuilder,
    val memberCodegen: MemberCodegen<*>,
    val expressionCodegen: ExpressionCodegen
) {
    fun generate(): StackValue {
        lateinit var generatedNode: MethodNode;
        val builder = object : MethodBuilder by classBuilder {
            override fun newMethod(
                origin: JvmDeclarationOrigin,
                access: Int,
                name: String,
                desc: String,
                signature: String?,
                exceptions: Array<String>?
            ): MethodVisitor {
                generatedNode = MethodNode(access, name, desc, signature, exceptions)
                return generatedNode
            }
        }

        val functionCodegen = FunctionCodegen(context, builder, state, memberCodegen)
        val invokeDescriptor = context.contextDescriptor
        functionCodegen.generateMethod(OtherOrigin(element, invokeDescriptor), invokeDescriptor, strategy)

        val capturedFields = ClosureCodegen.calculateConstructorParameters(
            state.typeMapper,
            state.languageVersionSettings,
            context.closure!!,
            AsmTypes.OBJECT_TYPE
        )
        val captured = ClosureCodegen.fieldListToTypeArray(capturedFields)

        val frameMap = FrameMap()
        captured.forEach {
            frameMap.enterTemp(it)
        }
        val capturedSize = frameMap.currentSize
        val params = ParametersBuilder.initializeBuilderFrom(AsmTypes.OBJECT_TYPE, generatedNode.desc).buildParameters()

        val loweredMethodName = "lambda$" + generatedNode.name
        val loweredMethodDesc = Type.getMethodDescriptor(Type.getReturnType(generatedNode.desc), *(captured + Type.getArgumentTypes(generatedNode.desc)))
        val actualMethod = classBuilder.newMethod(
            OtherOrigin(element, invokeDescriptor),
            generatedNode.access /*or Opcodes.ACC_SYNTHETIC*/,
            loweredMethodName,
            loweredMethodDesc,
            generatedNode.signature,
            generatedNode.exceptions?.toTypedArray()
        )
        actualMethod.visitCode()

        generatedNode.accept(
            RemapVisitor(
                actualMethod,
                object : LocalVarRemapper(
                    params,
                    capturedSize
                ) {
                    override fun doRemap(index: Int): RemapInfo {
                        if (index >= 65000) {
                            return RemapInfo(
                                null,
                                StackValue.local(index - 65000, AsmTypes.OBJECT_TYPE),
                                RemapStatus.SHIFT
                            )
                        }
                        return super.doRemap(index)
                    }
                },
                FieldRemapper(null, null, params)
            )
        )
        actualMethod.visitEnd()

        val (_, superInterface) = ClosureCodegen.extractSuperTypes(samType, invokeDescriptor, context.classDescriptor)
        val owner = state.typeMapper.mapType(superInterface.single())
        return StackValue.operation(owner) { v: InstructionAdapter ->
            val bootstrap = Handle(
                Opcodes.H_INVOKESTATIC,
                "java/lang/invoke/LambdaMetafactory",
                "metafactory",
                "(Ljava/lang/invoke/MethodHandles\$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
                false
            )

            val specializedMethod = state.typeMapper.mapAsmMethod(invokeDescriptor)
            val originalMethod = state.typeMapper.mapAsmMethod(ClosureCodegen.erasedInterfaceFunction(invokeDescriptor, samType))

            val implHandle = Handle(
                Opcodes.H_INVOKESTATIC,
                classBuilder.thisName,
                loweredMethodName,
                loweredMethodDesc,
                false
            )
            expressionCodegen.pushClosureOnStack(context.classDescriptor, true, CallGenerator.DefaultCallGenerator(expressionCodegen), null)

            v.invokedynamic(
                originalMethod.name,
                //TODO: normal order
                Type.getMethodDescriptor(owner, *captured),
                bootstrap,
                arrayOf(Type.getMethodType(originalMethod.descriptor), implHandle, Type.getMethodType(specializedMethod.descriptor))
            )
            Unit
        }
    }
}