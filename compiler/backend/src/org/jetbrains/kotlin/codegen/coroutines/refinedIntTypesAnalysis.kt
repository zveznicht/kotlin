/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen.coroutines

import org.jetbrains.kotlin.codegen.inline.insnOpcodeText
import org.jetbrains.kotlin.codegen.optimization.common.MethodAnalyzer
import org.jetbrains.kotlin.resolve.jvm.AsmTypes
import org.jetbrains.org.objectweb.asm.Handle
import org.jetbrains.org.objectweb.asm.Opcodes.*
import org.jetbrains.org.objectweb.asm.Type
import org.jetbrains.org.objectweb.asm.tree.*
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter
import org.jetbrains.org.objectweb.asm.tree.analysis.Value

// BasicValue interpreter from ASM does not distinct 'int' types from other int-like types like 'byte' or 'boolean',
// neither do HotSpot and JVM spec.
// But it seems like Dalvik does not follow it, and spilling boolean value into an 'int' field fails with VerifyError on Android 4,
// so this function calculates refined frames' markup.
// Note that type of some values is only possible to determine by their usages (e.g. ICONST_1, BALOAD both may push boolean or byte on stack)
// In this case, update the type of the value.

// StrictBasicValue with mutable type
internal open class ReifiedIntTypeValue(open var type: Type?, val insn: AbstractInsnNode?) : Value {
    override fun getSize(): Int = if (type == Type.LONG_TYPE || type == Type.DOUBLE_TYPE) 2 else 1

    override fun equals(other: Any?): Boolean = other is ReifiedIntTypeValue && type == other.type && insn == other.insn

    override fun hashCode(): Int = (type?.hashCode() ?: 0) xor insn.hashCode()

    override fun toString() = if (type == null) "." else "$type"
}

private class MergedIntTypeValue(val values: Set<ReifiedIntTypeValue>) : ReifiedIntTypeValue(null, null) {
    init {
        require(values.none { it is MergedIntTypeValue })
    }

    override var type: Type?
        get() = values.first().type
        set(newType) {
            for (value in values) {
                value.type = newType
            }
        }

    override fun equals(other: Any?): Boolean = other is MergedIntTypeValue && other.values == values

    override fun toString(): String = "M$values"
}

private operator fun ReifiedIntTypeValue?.plus(other: ReifiedIntTypeValue?): ReifiedIntTypeValue? = when {
    this == null -> other
    other == null -> this
    this == other -> this
    this is MergedIntTypeValue -> {
        if (other is MergedIntTypeValue) MergedIntTypeValue(values + other.values)
        else MergedIntTypeValue(values + other)
    }
    other is MergedIntTypeValue -> MergedIntTypeValue(other.values + this)
    else -> MergedIntTypeValue(setOf(this, other))
}

internal val NULL_TYPE = Type.getObjectType("null")

// Same as BasicInterpreter, but updates types based on usages
private class ReifiedIntTypeInterpreter(private val methodNode: MethodNode) : Interpreter<ReifiedIntTypeValue>(API_VERSION) {
    override fun newValue(type: Type?): ReifiedIntTypeValue? =
        if (type == Type.VOID_TYPE) null else ReifiedIntTypeValue(type, null)

    // INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC, INVOKEINTERFACE,
    // MULTIANEWARRAY and INVOKEDYNAMIC
    override fun naryOperation(insn: AbstractInsnNode, values: MutableList<out ReifiedIntTypeValue?>): ReifiedIntTypeValue? {
        fun updateTypes(argTypes: Array<Type>, withReceiver: Boolean) {
            val offset = if (withReceiver) 1 else 0
            for ((index, argType) in argTypes.withIndex()) {
                val value = values[index + offset] ?: continue
                if (argType.isIntType()) {
                    value.type = argType
                } else if (
                    (value.type == AsmTypes.OBJECT_TYPE && argType != AsmTypes.OBJECT_TYPE) ||
                    value.type == NULL_TYPE || value.type == null
                ) {
                    value.type = argType
                }
            }
        }

        return ReifiedIntTypeValue(
            when (insn.opcode) {
                MULTIANEWARRAY -> {
                    Type.getType((insn as MultiANewArrayInsnNode).desc)
                }
                INVOKEDYNAMIC -> {
                    updateTypes(Type.getArgumentTypes((insn as InvokeDynamicInsnNode).desc), false)
                    Type.getReturnType(insn.desc)
                }
                INVOKESTATIC -> {
                    updateTypes(Type.getArgumentTypes((insn as MethodInsnNode).desc), false)
                    Type.getReturnType(insn.desc)
                }
                INVOKEVIRTUAL, INVOKEINTERFACE, INVOKESPECIAL -> {
                    updateTypes(Type.getArgumentTypes((insn as MethodInsnNode).desc), true)
                    Type.getReturnType(insn.desc)
                }
                else -> {
                    unreachable(insn)
                }
            }, insn
        )
    }

    private fun Type.isIntType(): Boolean = when (sort) {
        Type.BOOLEAN, Type.BYTE, Type.CHAR, Type.SHORT, Type.INT -> true
        else -> false
    }

    private fun unreachable(insn: AbstractInsnNode): Nothing = error("Unreachable instruction ${insn.insnOpcodeText}")

    // IASTORE, LASTORE, FASTORE, DASTORE, AASTORE, BASTORE, CASTORE, SASTORE
    override fun ternaryOperation(
        insn: AbstractInsnNode,
        arrayref: ReifiedIntTypeValue?,
        index: ReifiedIntTypeValue?,
        value: ReifiedIntTypeValue?
    ): ReifiedIntTypeValue? {
        when (insn.opcode) {
            IASTORE, LASTORE, FASTORE, DASTORE, AASTORE -> {
                // nothing to do
            }
            BASTORE -> {
                value?.type = if (arrayref?.type?.descriptor == "[Z") Type.BOOLEAN_TYPE else Type.BYTE_TYPE
            }
            CASTORE -> {
                value?.type = Type.CHAR_TYPE
            }
            SASTORE -> {
                value?.type = Type.SHORT_TYPE
            }
            else -> unreachable(insn)
        }
        return null
    }

    override fun merge(v: ReifiedIntTypeValue?, w: ReifiedIntTypeValue?): ReifiedIntTypeValue? = when {
        v?.type?.isIntType() == true && w?.type?.isIntType() == true -> v + w
        v != null && v.type == null -> w
        w != null && w.type == null -> v
        v?.type == w?.type -> v
        else -> ReifiedIntTypeValue(null, v?.insn ?: w?.insn)
    }

    // IRETURN, LRETURN, FRETURN, DRETURN, ARETURN
    override fun returnOperation(insn: AbstractInsnNode, value: ReifiedIntTypeValue?, expected: ReifiedIntTypeValue?) {
        if (insn.opcode == IRETURN) {
            value?.type = expected?.type
        }
    }

    // INEG, LNEG, FNEG, DNEG, IINC, I2L, I2F, I2D, L2I, L2F, L2D, F2I, F2L,
    // F2D, D2I, D2L, D2F, I2B, I2C, I2S, IFEQ, IFNE, IFLT, IFGE, IFGT, IFLE,
    // TABLESWITCH, LOOKUPSWITCH, IRETURN, LRETURN, FRETURN, DRETURN, ARETURN,
    // PUTSTATIC, GETFIELD, NEWARRAY, ANEWARRAY, ARRAYLENGTH, ATHROW, CHECKCAST,
    // INSTANCEOF, MONITORENTER, MONITOREXIT, IFNULL, IFNONNULL
    override fun unaryOperation(insn: AbstractInsnNode, value: ReifiedIntTypeValue?): ReifiedIntTypeValue? = when (insn.opcode) {
        INEG, LNEG, FNEG, DNEG, IINC -> ReifiedIntTypeValue(value?.type, insn)
        I2L, F2L, D2L -> ReifiedIntTypeValue(Type.LONG_TYPE, insn)
        I2F, L2F, D2F -> ReifiedIntTypeValue(Type.FLOAT_TYPE, insn)
        L2D, I2D, F2D -> ReifiedIntTypeValue(Type.DOUBLE_TYPE, insn)
        L2I, F2I, D2I, ARRAYLENGTH -> ReifiedIntTypeValue(Type.INT_TYPE, insn)
        I2B -> ReifiedIntTypeValue(Type.BYTE_TYPE, insn)
        I2C -> ReifiedIntTypeValue(Type.CHAR_TYPE, insn)
        I2S -> ReifiedIntTypeValue(Type.SHORT_TYPE, insn)
        IFEQ, IFNE, IFLT, IFGE, IFGT, IFLE, TABLESWITCH, LOOKUPSWITCH, IRETURN, LRETURN, FRETURN, DRETURN, ARETURN,
        ATHROW, MONITORENTER, MONITOREXIT, IFNULL, IFNONNULL -> null
        PUTSTATIC -> {
            val expectedType = Type.getType((insn as FieldInsnNode).desc)
            if (expectedType.isIntType()) {
                value?.type = expectedType
            }
            null
        }
        GETFIELD -> ReifiedIntTypeValue(Type.getType((insn as FieldInsnNode).desc), insn)
        NEWARRAY -> when ((insn as IntInsnNode).operand) {
            T_BOOLEAN -> ReifiedIntTypeValue(Type.getType("[Z"), insn)
            T_CHAR -> ReifiedIntTypeValue(Type.getType("[C"), insn)
            T_BYTE -> ReifiedIntTypeValue(Type.getType("[B"), insn)
            T_SHORT -> ReifiedIntTypeValue(Type.getType("[S"), insn)
            T_INT -> ReifiedIntTypeValue(Type.getType("[I"), insn)
            T_FLOAT -> ReifiedIntTypeValue(Type.getType("[F"), insn)
            T_DOUBLE -> ReifiedIntTypeValue(Type.getType("[D"), insn)
            T_LONG -> ReifiedIntTypeValue(Type.getType("[J"), insn)
            else -> unreachable(insn)
        }
        ANEWARRAY -> ReifiedIntTypeValue(Type.getType("[${Type.getObjectType((insn as TypeInsnNode).desc)}"), insn)
        CHECKCAST -> ReifiedIntTypeValue(Type.getObjectType((insn as TypeInsnNode).desc), insn)
        INSTANCEOF -> ReifiedIntTypeValue(Type.BOOLEAN_TYPE, insn)
        else -> unreachable(insn)
    }

    // IALOAD, LALOAD, FALOAD, DALOAD, AALOAD, BALOAD, CALOAD, SALOAD, IADD,
    // LADD, FADD, DADD, ISUB, LSUB, FSUB, DSUB, IMUL, LMUL, FMUL, DMUL, IDIV,
    // LDIV, FDIV, DDIV, IREM, LREM, FREM, DREM, ISHL, LSHL, ISHR, LSHR, IUSHR,
    // LUSHR, IAND, LAND, IOR, LOR, IXOR, LXOR, LCMP, FCMPL, FCMPG, DCMPL,
    // DCMPG, IF_ICMPEQ, IF_ICMPNE, IF_ICMPLT, IF_ICMPGE, IF_ICMPGT, IF_ICMPLE,
    // IF_ACMPEQ, IF_ACMPNE, PUTFIELD
    override fun binaryOperation(insn: AbstractInsnNode, v: ReifiedIntTypeValue?, w: ReifiedIntTypeValue?): ReifiedIntTypeValue? =
        when (insn.opcode) {
            IALOAD, IADD, ISUB, IMUL, IDIV, IREM, ISHL, ISHR, IUSHR, IAND, IOR, IXOR, LCMP, FCMPL, FCMPG, DCMPL,
            DCMPG -> ReifiedIntTypeValue(Type.INT_TYPE, insn)
            LALOAD, LADD, LSUB, LMUL, LDIV, LREM, LSHL, LSHR, LUSHR, LAND, LOR, LXOR -> ReifiedIntTypeValue(Type.LONG_TYPE, insn)
            FALOAD, FADD, FSUB, FMUL, FDIV, FREM -> ReifiedIntTypeValue(Type.FLOAT_TYPE, insn)
            DALOAD, DADD, DSUB, DMUL, DDIV, DREM -> ReifiedIntTypeValue(Type.DOUBLE_TYPE, insn)
            AALOAD -> ReifiedIntTypeValue(AsmTypes.OBJECT_TYPE, insn)
            BALOAD -> ReifiedIntTypeValue(if (v?.type?.descriptor == "[Z") Type.BOOLEAN_TYPE else Type.BYTE_TYPE, insn)
            CALOAD -> ReifiedIntTypeValue(Type.CHAR_TYPE, insn)
            SALOAD -> ReifiedIntTypeValue(Type.SHORT_TYPE, insn)
            IF_ICMPEQ, IF_ICMPNE, IF_ICMPLT, IF_ICMPGE, IF_ICMPGT, IF_ICMPLE, IF_ACMPEQ, IF_ACMPNE -> null
            PUTFIELD -> {
                val expectedType = Type.getType((insn as FieldInsnNode).desc)
                if (expectedType.isIntType()) {
                    w?.type = expectedType
                }
                null
            }
            else -> unreachable(insn)
        }

    // ILOAD, LLOAD, FLOAD, DLOAD, ALOAD, ISTORE, LSTORE, FSTORE, DSTORE,
    // ASTORE, DUP, DUP_X1, DUP_X2, DUP2, DUP2_X1, DUP2_X2, SWAP
    override fun copyOperation(insn: AbstractInsnNode, value: ReifiedIntTypeValue?): ReifiedIntTypeValue? =
        when (insn.opcode) {
            // If same ICONST is stored into several slots, thay can have different types
            // For example,
            //  val b: Byte = 1
            //  val i: Int = b.toInt()
            // In this case, `b` and `i` have the same source, but different types.
            // The example also shows, that the types should be `I`.
            ISTORE -> ReifiedIntTypeValue(Type.INT_TYPE, insn)
            // Sometimes we cannot get the type from the usage only
            // For example,
            //  val c = '1'
            //  if (c == '2) ...
            // In this case, update the type using information from LVT
            ILOAD -> {
                methodNode.localVariables.find { local ->
                    local.index == (insn as VarInsnNode).`var` &&
                            methodNode.instructions.indexOf(local.start) < methodNode.instructions.indexOf(insn) &&
                            methodNode.instructions.indexOf(insn) < methodNode.instructions.indexOf(local.end)
                }?.let { local ->
                    value?.type = Type.getType(local.desc)
                }
                value
            }
            else -> value
        }

    // ACONST_NULL, ICONST_M1, ICONST_0, ICONST_1, ICONST_2, ICONST_3, ICONST_4,
    // ICONST_5, LCONST_0, LCONST_1, FCONST_0, FCONST_1, FCONST_2, DCONST_0,
    // DCONST_1, BIPUSH, SIPUSH, LDC, JSR, GETSTATIC, NEW
    override fun newOperation(insn: AbstractInsnNode): ReifiedIntTypeValue? = when (insn.opcode) {
        ACONST_NULL -> ReifiedIntTypeValue(NULL_TYPE, insn)
        ICONST_M1, ICONST_0, ICONST_1, ICONST_2, ICONST_3, ICONST_4, ICONST_5 -> ReifiedIntTypeValue(Type.INT_TYPE, insn)
        LCONST_0, LCONST_1 -> ReifiedIntTypeValue(Type.LONG_TYPE, insn)
        FCONST_0, FCONST_1, FCONST_2 -> ReifiedIntTypeValue(Type.FLOAT_TYPE, insn)
        DCONST_0, DCONST_1 -> ReifiedIntTypeValue(Type.DOUBLE_TYPE, insn)
        BIPUSH -> ReifiedIntTypeValue(Type.BYTE_TYPE, insn)
        SIPUSH -> ReifiedIntTypeValue(Type.SHORT_TYPE, insn)
        LDC -> when (val cst = (insn as LdcInsnNode).cst) {
            is Int -> ReifiedIntTypeValue(Type.INT_TYPE, insn)
            is Long -> ReifiedIntTypeValue(Type.LONG_TYPE, insn)
            is Float -> ReifiedIntTypeValue(Type.FLOAT_TYPE, insn)
            is Double -> ReifiedIntTypeValue(Type.DOUBLE_TYPE, insn)
            is String -> ReifiedIntTypeValue(AsmTypes.JAVA_STRING_TYPE, insn)
            is Type -> when (cst.sort) {
                Type.OBJECT, Type.ARRAY -> ReifiedIntTypeValue(AsmTypes.JAVA_CLASS_TYPE, insn)
                Type.METHOD -> ReifiedIntTypeValue(Type.getObjectType("java/lang/invoke/MethodType"), insn)
                else -> unreachable(insn)
            }
            is Handle -> ReifiedIntTypeValue(Type.getObjectType("java/lang/invoke/MethodHandle"), insn)
            else -> ReifiedIntTypeValue(AsmTypes.OBJECT_TYPE, insn)
        }
        JSR -> ReifiedIntTypeValue(Type.VOID_TYPE, insn)
        GETSTATIC -> ReifiedIntTypeValue(Type.getType((insn as FieldInsnNode).desc), insn)
        NEW -> ReifiedIntTypeValue(Type.getObjectType((insn as TypeInsnNode).desc), insn)
        else -> unreachable(insn)
    }
}

internal fun performRefinedTypeAnalysis(methodNode: MethodNode, thisName: String): Array<out Frame<ReifiedIntTypeValue>?> =
    MethodAnalyzer(thisName, methodNode, ReifiedIntTypeInterpreter(methodNode)).analyze()