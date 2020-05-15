/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen.coroutines

import org.jetbrains.annotations.TestOnly
import org.jetbrains.kotlin.codegen.inline.insnOpcodeText
import org.jetbrains.kotlin.codegen.inline.nodeText
import org.jetbrains.kotlin.codegen.optimization.boxing.isUnitInstance
import org.jetbrains.kotlin.codegen.optimization.common.InsnSequence
import org.jetbrains.kotlin.codegen.optimization.common.MethodAnalyzer
import org.jetbrains.kotlin.codegen.optimization.common.asSequence
import org.jetbrains.kotlin.codegen.optimization.common.removeAll
import org.jetbrains.kotlin.codegen.optimization.fixStack.top
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.resolve.jvm.AsmTypes
import org.jetbrains.org.objectweb.asm.Opcodes
import org.jetbrains.org.objectweb.asm.Type
import org.jetbrains.org.objectweb.asm.tree.*
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicInterpreter
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue
import java.io.File
import java.time.Instant
import java.time.Instant.now
import java.time.LocalDateTime
import java.util.*

private class PossibleSpilledValue(val source: AbstractInsnNode, type: Type?) : BasicValue(type) {
    val usages = mutableSetOf<AbstractInsnNode>()

    override fun toString(): String = source.insnOpcodeText
}

private object ConstructedValue : BasicValue(AsmTypes.OBJECT_TYPE)

private class RedundantSpillingInterpreter(
    private val languageVersionSettings: LanguageVersionSettings,
    private val methodNode: MethodNode
) :
    BasicInterpreter(Opcodes.API_VERSION) {
    val possibleSpilledValues = mutableSetOf<PossibleSpilledValue>()

    override fun newOperation(insn: AbstractInsnNode): BasicValue? {
        if (insn.opcode == Opcodes.NEW) return ConstructedValue
        val basicValue = super.newOperation(insn)
        return if (insn.isUnitInstance())
            PossibleSpilledValue(insn, basicValue.type).also { possibleSpilledValues += it }
        else basicValue
    }

    override fun copyOperation(insn: AbstractInsnNode, value: BasicValue?): BasicValue? =
        when {
            value is ConstructedValue -> value
            value is PossibleSpilledValue -> {
                value.usages += insn
                if (insn.opcode == Opcodes.ALOAD || insn.opcode == Opcodes.ASTORE) value
                else super.newValue(value.type)
            }
            insn.opcode == Opcodes.ALOAD -> PossibleSpilledValue(insn, AsmTypes.OBJECT_TYPE)
            else ->
                super.newValue(value?.type)
        }

    override fun naryOperation(insn: AbstractInsnNode, values: MutableList<out BasicValue?>): BasicValue? {
        for (value in values.filterIsInstance<PossibleSpilledValue>()) {
            value.usages += insn
        }
        return super.naryOperation(insn, values)
    }

    override fun merge(v: BasicValue?, w: BasicValue?): BasicValue? =
        if (v is PossibleSpilledValue && w is PossibleSpilledValue && v.source == w.source) v else super.newValue(v?.type)
}

// Inliner emits a lot of locals during inlining.
// Remove all of them since these locals are
//  1) going to be spilled into continuation object
//  2) breaking tail-call elimination
internal class RedundantLocalsEliminationMethodTransformer(
    private val languageVersionSettings: LanguageVersionSettings,
    private val suspensionPoints: List<SuspensionPoint>
) : MethodTransformer() {
    override fun transform(internalClassName: String, methodNode: MethodNode) {
        val interpreter = RedundantSpillingInterpreter(languageVersionSettings, methodNode)
        val frames = MethodAnalyzer<BasicValue>(internalClassName, methodNode, interpreter).analyze()

        val toDelete = mutableSetOf<AbstractInsnNode>()
        for (spilledValue in interpreter.possibleSpilledValues.filter { it.usages.isNotEmpty() }) {
            val aloads = spilledValue.usages.filter { it.opcode == Opcodes.ALOAD } as List<VarInsnNode>

            if (aloads.isEmpty()) continue

            val slot = aloads.first().`var`

            if (aloads.any { it.`var` != slot }) continue
            for (aload in aloads) {
                methodNode.instructions.set(aload, spilledValue.source.clone())
            }

            toDelete.addAll(spilledValue.usages.filter { it.opcode == Opcodes.ASTORE })
            toDelete.add(spilledValue.source)
        }

        for (pop in methodNode.instructions.asSequence().filter { it.opcode == Opcodes.POP }) {
            val value = (frames[methodNode.instructions.indexOf(pop)]?.top() as? PossibleSpilledValue) ?: continue
            if (value.usages.isEmpty() && suspensionPoints.none { value.source in it }) {
                toDelete.add(pop)
                toDelete.add(value.source)
            }
        }

        methodNode.instructions.removeAll(toDelete)
    }

    private fun AbstractInsnNode.clone() = when (this) {
        is FieldInsnNode -> FieldInsnNode(opcode, owner, name, desc)
        is VarInsnNode -> VarInsnNode(opcode, `var`)
        is InsnNode -> InsnNode(opcode)
        is TypeInsnNode -> TypeInsnNode(opcode, desc)
        else -> error("clone of $this is not implemented yet")
    }
}
