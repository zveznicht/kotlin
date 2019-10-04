
/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.common.serialization.protoMimic

class FileEntry(
    val name: String,
    val lineStartOffsetsList: List<Int>
) {

    fun hasName(): Boolean = true

    fun hasLineStartOffsets(): Boolean = true

    val lineStartOffsetsCount: Int
        get() = lineStartOffsetsList.size
}

class IrFile(
    val declarationIdList: List<Int>,
    val fileEntry: FileEntry,
    val fqNameList: List<Int>,
    val annotationList: List<IrConstructorCall>,
    val explicitlyExportedToCompilerList: List<Long>,
    val actualsList: List<Actual>
) {

    fun hasDeclarationId(): Boolean = true

    val declarationIdCount: Int
        get() = declarationIdList.size

    fun hasFileEntry(): Boolean = true

    fun hasFqName(): Boolean = true

    val fqNameCount: Int
        get() = fqNameList.size

    fun hasAnnotation(): Boolean = true

    val annotationCount: Int
        get() = annotationList.size

    fun hasExplicitlyExportedToCompiler(): Boolean = true

    val explicitlyExportedToCompilerCount: Int
        get() = explicitlyExportedToCompilerList.size

    fun hasActuals(): Boolean = true

    val actualsCount: Int
        get() = actualsList.size
}

class PublicIdSignature(
    val packageFqNameList: List<Int>,
    val declarationFqNameList: List<Int>,
    private val fieldMemberUniqId: Long?,
    val flags: Long
) {

    val memberUniqId: Long
        get() = fieldMemberUniqId!!

    fun hasPackageFqName(): Boolean = true

    val packageFqNameCount: Int
        get() = packageFqNameList.size

    fun hasDeclarationFqName(): Boolean = true

    val declarationFqNameCount: Int
        get() = declarationFqNameList.size

    fun hasMemberUniqId(): Boolean = fieldMemberUniqId != null

    fun hasFlags(): Boolean = true
}

class AccessorIdSignature(
    val propertySignature: Int,
    val name: Int,
    val accessorHashId: Long,
    val flags: Long
) {

    fun hasPropertySignature(): Boolean = true

    fun hasName(): Boolean = true

    fun hasAccessorHashId(): Boolean = true

    fun hasFlags(): Boolean = true
}

class FileLocalIdSignature(
    val container: Int,
    val localId: Long
) {

    fun hasContainer(): Boolean = true

    fun hasLocalId(): Boolean = true
}

class IdSignature(
    val idsigCase: IdsigCase,
    private val fieldPublicSig: PublicIdSignature?,
    private val fieldPrivateSig: FileLocalIdSignature?,
    private val fieldAccessorSig: AccessorIdSignature?,
    private val fieldScopedLocalSig: Int?
) {
    enum class IdsigCase {
        PUBLIC_SIG,
        PRIVATE_SIG,
        ACCESSOR_SIG,
        SCOPED_LOCAL_SIG,
        IDSIG_NOT_SET
    }

    val publicSig: PublicIdSignature
        get() = fieldPublicSig!!

    val privateSig: FileLocalIdSignature
        get() = fieldPrivateSig!!

    val accessorSig: AccessorIdSignature
        get() = fieldAccessorSig!!

    val scopedLocalSig: Int
        get() = fieldScopedLocalSig!!

    fun hasPublicSig(): Boolean = fieldPublicSig != null

    fun hasPrivateSig(): Boolean = fieldPrivateSig != null

    fun hasAccessorSig(): Boolean = fieldAccessorSig != null

    fun hasScopedLocalSig(): Boolean = fieldScopedLocalSig != null
}

class Actual(
    val actualSymbol: Long,
    val expectSymbol: Long
) {

    fun hasActualSymbol(): Boolean = true

    fun hasExpectSymbol(): Boolean = true
}

class IrSimpleType(
    val annotationList: List<IrConstructorCall>,
    val classifier: Long,
    val hasQuestionMark: Boolean,
    val argumentList: List<Long>,
    private val fieldAbbreviation: IrTypeAbbreviation?
) {

    val abbreviation: IrTypeAbbreviation
        get() = fieldAbbreviation!!

    fun hasAnnotation(): Boolean = true

    val annotationCount: Int
        get() = annotationList.size

    fun hasClassifier(): Boolean = true

    fun hasHasQuestionMark(): Boolean = true

    fun hasArgument(): Boolean = true

    val argumentCount: Int
        get() = argumentList.size

    fun hasAbbreviation(): Boolean = fieldAbbreviation != null
}

class IrTypeAbbreviation(
    val annotationList: List<IrConstructorCall>,
    val typeAlias: Long,
    val hasQuestionMark: Boolean,
    val argumentList: List<Long>
) {

    fun hasAnnotation(): Boolean = true

    val annotationCount: Int
        get() = annotationList.size

    fun hasTypeAlias(): Boolean = true

    fun hasHasQuestionMark(): Boolean = true

    fun hasArgument(): Boolean = true

    val argumentCount: Int
        get() = argumentList.size
}

class IrDynamicType(
    val annotationList: List<IrConstructorCall>
) {

    fun hasAnnotation(): Boolean = true

    val annotationCount: Int
        get() = annotationList.size
}

class IrErrorType(
    val annotationList: List<IrConstructorCall>
) {

    fun hasAnnotation(): Boolean = true

    val annotationCount: Int
        get() = annotationList.size
}

class IrType(
    val kindCase: KindCase,
    private val fieldSimple: IrSimpleType?,
    private val fieldDynamic: IrDynamicType?,
    private val fieldError: IrErrorType?
) {
    enum class KindCase {
        SIMPLE,
        DYNAMIC,
        ERROR,
        KIND_NOT_SET
    }

    val simple: IrSimpleType
        get() = fieldSimple!!

    val dynamic: IrDynamicType
        get() = fieldDynamic!!

    val error: IrErrorType
        get() = fieldError!!

    fun hasSimple(): Boolean = fieldSimple != null

    fun hasDynamic(): Boolean = fieldDynamic != null

    fun hasError(): Boolean = fieldError != null
}

class IrBreak(
    val loopId: Int,
    private val fieldLabel: Int?
) {

    val label: Int
        get() = fieldLabel!!

    fun hasLoopId(): Boolean = true

    fun hasLabel(): Boolean = fieldLabel != null
}

class IrBlock(
    val statementList: List<IrStatement>,
    private val fieldOriginName: Int?
) {

    val originName: Int
        get() = fieldOriginName!!

    fun hasStatement(): Boolean = true

    val statementCount: Int
        get() = statementList.size

    fun hasOriginName(): Boolean = fieldOriginName != null
}

class MemberAccessCommon(
    private val fieldDispatchReceiver: IrExpression?,
    private val fieldExtensionReceiver: IrExpression?,
    val valueArgumentList: List<NullableIrExpression>,
    val typeArgumentList: List<Int>
) {

    val dispatchReceiver: IrExpression
        get() = fieldDispatchReceiver!!

    val extensionReceiver: IrExpression
        get() = fieldExtensionReceiver!!

    fun hasDispatchReceiver(): Boolean = fieldDispatchReceiver != null

    fun hasExtensionReceiver(): Boolean = fieldExtensionReceiver != null

    fun hasValueArgument(): Boolean = true

    val valueArgumentCount: Int
        get() = valueArgumentList.size

    fun hasTypeArgument(): Boolean = true

    val typeArgumentCount: Int
        get() = typeArgumentList.size
}

class IrCall(
    val symbol: Long,
    val memberAccess: MemberAccessCommon,
    private val fieldSuper: Long?,
    private val fieldOriginName: Int?
) {

    val `super`: Long
        get() = fieldSuper!!

    val originName: Int
        get() = fieldOriginName!!

    fun hasSymbol(): Boolean = true

    fun hasMemberAccess(): Boolean = true

    fun hasSuper(): Boolean = fieldSuper != null

    fun hasOriginName(): Boolean = fieldOriginName != null
}

class IrConstructorCall(
    val symbol: Long,
    val constructorTypeArgumentsCount: Int,
    val memberAccess: MemberAccessCommon
) {

    fun hasSymbol(): Boolean = true

    fun hasConstructorTypeArgumentsCount(): Boolean = true

    fun hasMemberAccess(): Boolean = true
}

class IrFunctionReference(
    val symbol: Long,
    private val fieldOriginName: Int?,
    val memberAccess: MemberAccessCommon,
    private val fieldReflectionTargetSymbol: Long?
) {

    val originName: Int
        get() = fieldOriginName!!

    val reflectionTargetSymbol: Long
        get() = fieldReflectionTargetSymbol!!

    fun hasSymbol(): Boolean = true

    fun hasOriginName(): Boolean = fieldOriginName != null

    fun hasMemberAccess(): Boolean = true

    fun hasReflectionTargetSymbol(): Boolean = fieldReflectionTargetSymbol != null
}

class IrLocalDelegatedPropertyReference(
    val delegate: Long,
    private val fieldGetter: Long?,
    private val fieldSetter: Long?,
    val symbol: Long,
    private val fieldOriginName: Int?
) {

    val getter: Long
        get() = fieldGetter!!

    val setter: Long
        get() = fieldSetter!!

    val originName: Int
        get() = fieldOriginName!!

    fun hasDelegate(): Boolean = true

    fun hasGetter(): Boolean = fieldGetter != null

    fun hasSetter(): Boolean = fieldSetter != null

    fun hasSymbol(): Boolean = true

    fun hasOriginName(): Boolean = fieldOriginName != null
}

class IrPropertyReference(
    private val fieldField: Long?,
    private val fieldGetter: Long?,
    private val fieldSetter: Long?,
    private val fieldOriginName: Int?,
    val memberAccess: MemberAccessCommon,
    val symbol: Long
) {

    val field: Long
        get() = fieldField!!

    val getter: Long
        get() = fieldGetter!!

    val setter: Long
        get() = fieldSetter!!

    val originName: Int
        get() = fieldOriginName!!

    fun hasField(): Boolean = fieldField != null

    fun hasGetter(): Boolean = fieldGetter != null

    fun hasSetter(): Boolean = fieldSetter != null

    fun hasOriginName(): Boolean = fieldOriginName != null

    fun hasMemberAccess(): Boolean = true

    fun hasSymbol(): Boolean = true
}

class IrComposite(
    val statementList: List<IrStatement>,
    private val fieldOriginName: Int?
) {

    val originName: Int
        get() = fieldOriginName!!

    fun hasStatement(): Boolean = true

    val statementCount: Int
        get() = statementList.size

    fun hasOriginName(): Boolean = fieldOriginName != null
}

class IrClassReference(
    val classSymbol: Long,
    val classType: Int
) {

    fun hasClassSymbol(): Boolean = true

    fun hasClassType(): Boolean = true
}

class IrConst(
    val valueCase: ValueCase,
    private val fieldNull: Boolean?,
    private val fieldBoolean: Boolean?,
    private val fieldChar: Int?,
    private val fieldByte: Int?,
    private val fieldShort: Int?,
    private val fieldInt: Int?,
    private val fieldLong: Long?,
    private val fieldFloat: Float?,
    private val fieldDouble: Double?,
    private val fieldString: Int?
) {
    enum class ValueCase {
        NULL,
        BOOLEAN,
        CHAR,
        BYTE,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        STRING,
        VALUE_NOT_SET
    }

    val `null`: Boolean
        get() = fieldNull!!

    val boolean: Boolean
        get() = fieldBoolean!!

    val char: Int
        get() = fieldChar!!

    val byte: Int
        get() = fieldByte!!

    val short: Int
        get() = fieldShort!!

    val int: Int
        get() = fieldInt!!

    val long: Long
        get() = fieldLong!!

    val float: Float
        get() = fieldFloat!!

    val double: Double
        get() = fieldDouble!!

    val string: Int
        get() = fieldString!!

    fun hasNull(): Boolean = fieldNull != null

    fun hasBoolean(): Boolean = fieldBoolean != null

    fun hasChar(): Boolean = fieldChar != null

    fun hasByte(): Boolean = fieldByte != null

    fun hasShort(): Boolean = fieldShort != null

    fun hasInt(): Boolean = fieldInt != null

    fun hasLong(): Boolean = fieldLong != null

    fun hasFloat(): Boolean = fieldFloat != null

    fun hasDouble(): Boolean = fieldDouble != null

    fun hasString(): Boolean = fieldString != null
}

class IrContinue(
    val loopId: Int,
    private val fieldLabel: Int?
) {

    val label: Int
        get() = fieldLabel!!

    fun hasLoopId(): Boolean = true

    fun hasLabel(): Boolean = fieldLabel != null
}

class IrDelegatingConstructorCall(
    val symbol: Long,
    val memberAccess: MemberAccessCommon
) {

    fun hasSymbol(): Boolean = true

    fun hasMemberAccess(): Boolean = true
}

class IrDoWhile(
    val loop: Loop
) {

    fun hasLoop(): Boolean = true
}

class IrEnumConstructorCall(
    val symbol: Long,
    val memberAccess: MemberAccessCommon
) {

    fun hasSymbol(): Boolean = true

    fun hasMemberAccess(): Boolean = true
}

class IrGetClass(
    val argument: IrExpression
) {

    fun hasArgument(): Boolean = true
}

class IrGetEnumValue(
    val symbol: Long
) {

    fun hasSymbol(): Boolean = true
}

class FieldAccessCommon(
    val symbol: Long,
    private val fieldSuper: Long?,
    private val fieldReceiver: IrExpression?
) {

    val `super`: Long
        get() = fieldSuper!!

    val receiver: IrExpression
        get() = fieldReceiver!!

    fun hasSymbol(): Boolean = true

    fun hasSuper(): Boolean = fieldSuper != null

    fun hasReceiver(): Boolean = fieldReceiver != null
}

class IrGetField(
    val fieldAccess: FieldAccessCommon,
    private val fieldOriginName: Int?
) {

    val originName: Int
        get() = fieldOriginName!!

    fun hasFieldAccess(): Boolean = true

    fun hasOriginName(): Boolean = fieldOriginName != null
}

class IrGetValue(
    val symbol: Long,
    private val fieldOriginName: Int?
) {

    val originName: Int
        get() = fieldOriginName!!

    fun hasSymbol(): Boolean = true

    fun hasOriginName(): Boolean = fieldOriginName != null
}

class IrGetObject(
    val symbol: Long
) {

    fun hasSymbol(): Boolean = true
}

class IrInstanceInitializerCall(
    val symbol: Long
) {

    fun hasSymbol(): Boolean = true
}

class Loop(
    val loopId: Int,
    val condition: IrExpression,
    private val fieldLabel: Int?,
    private val fieldBody: IrExpression?,
    private val fieldOriginName: Int?
) {

    val label: Int
        get() = fieldLabel!!

    val body: IrExpression
        get() = fieldBody!!

    val originName: Int
        get() = fieldOriginName!!

    fun hasLoopId(): Boolean = true

    fun hasCondition(): Boolean = true

    fun hasLabel(): Boolean = fieldLabel != null

    fun hasBody(): Boolean = fieldBody != null

    fun hasOriginName(): Boolean = fieldOriginName != null
}

class IrReturn(
    val returnTarget: Long,
    val value: IrExpression
) {

    fun hasReturnTarget(): Boolean = true

    fun hasValue(): Boolean = true
}

class IrSetField(
    val fieldAccess: FieldAccessCommon,
    val value: IrExpression,
    private val fieldOriginName: Int?
) {

    val originName: Int
        get() = fieldOriginName!!

    fun hasFieldAccess(): Boolean = true

    fun hasValue(): Boolean = true

    fun hasOriginName(): Boolean = fieldOriginName != null
}

class IrSetVariable(
    val symbol: Long,
    val value: IrExpression,
    private val fieldOriginName: Int?
) {

    val originName: Int
        get() = fieldOriginName!!

    fun hasSymbol(): Boolean = true

    fun hasValue(): Boolean = true

    fun hasOriginName(): Boolean = fieldOriginName != null
}

class IrSpreadElement(
    val expression: IrExpression,
    val coordinates: Long
) {

    fun hasExpression(): Boolean = true

    fun hasCoordinates(): Boolean = true
}

class IrStringConcat(
    val argumentList: List<IrExpression>
) {

    fun hasArgument(): Boolean = true

    val argumentCount: Int
        get() = argumentList.size
}

class IrThrow(
    val value: IrExpression
) {

    fun hasValue(): Boolean = true
}

class IrTry(
    val result: IrExpression,
    val catchList: List<IrStatement>,
    private val fieldFinally: IrExpression?
) {

    val finally: IrExpression
        get() = fieldFinally!!

    fun hasResult(): Boolean = true

    fun hasCatch(): Boolean = true

    val catchCount: Int
        get() = catchList.size

    fun hasFinally(): Boolean = fieldFinally != null
}

class IrTypeOp(
    val operator: IrTypeOperator,
    val operand: Int,
    val argument: IrExpression
) {

    fun hasOperator(): Boolean = true

    fun hasOperand(): Boolean = true

    fun hasArgument(): Boolean = true
}

class IrVararg(
    val elementType: Int,
    val elementList: List<IrVarargElement>
) {

    fun hasElementType(): Boolean = true

    fun hasElement(): Boolean = true

    val elementCount: Int
        get() = elementList.size
}

class IrVarargElement(
    val varargElementCase: VarargElementCase,
    private val fieldExpression: IrExpression?,
    private val fieldSpreadElement: IrSpreadElement?
) {
    enum class VarargElementCase {
        EXPRESSION,
        SPREAD_ELEMENT,
        VARARG_ELEMENT_NOT_SET
    }

    val expression: IrExpression
        get() = fieldExpression!!

    val spreadElement: IrSpreadElement
        get() = fieldSpreadElement!!

    fun hasExpression(): Boolean = fieldExpression != null

    fun hasSpreadElement(): Boolean = fieldSpreadElement != null
}

class IrWhen(
    val branchList: List<IrStatement>,
    private val fieldOriginName: Int?
) {

    val originName: Int
        get() = fieldOriginName!!

    fun hasBranch(): Boolean = true

    val branchCount: Int
        get() = branchList.size

    fun hasOriginName(): Boolean = fieldOriginName != null
}

class IrWhile(
    val loop: Loop
) {

    fun hasLoop(): Boolean = true
}

class IrFunctionExpression(
    val function: IrFunction,
    val originName: Int
) {

    fun hasFunction(): Boolean = true

    fun hasOriginName(): Boolean = true
}

class IrDynamicMemberExpression(
    val memberName: Int,
    val receiver: IrExpression
) {

    fun hasMemberName(): Boolean = true

    fun hasReceiver(): Boolean = true
}

class IrDynamicOperatorExpression(
    val operator: IrDynamicOperator,
    val receiver: IrExpression,
    val argumentList: List<IrExpression>
) {
    enum class IrDynamicOperator {
        UNARY_PLUS,
        UNARY_MINUS,
        EXCL,
        PREFIX_INCREMENT,
        POSTFIX_INCREMENT,
        PREFIX_DECREMENT,
        POSTFIX_DECREMENT,
        BINARY_PLUS,
        BINARY_MINUS,
        MUL,
        DIV,
        MOD,
        GT,
        LT,
        GE,
        LE,
        EQEQ,
        EXCLEQ,
        EQEQEQ,
        EXCLEQEQ,
        ANDAND,
        OROR,
        EQ,
        PLUSEQ,
        MINUSEQ,
        MULEQ,
        DIVEQ,
        MODEQ,
        ARRAY_ACCESS,
        INVOKE    ;

        companion object {
            fun fromIndex(index: Int): IrDynamicOperator {
                return when (index) {
                    1 -> UNARY_PLUS
                    2 -> UNARY_MINUS
                    3 -> EXCL
                    4 -> PREFIX_INCREMENT
                    5 -> POSTFIX_INCREMENT
                    6 -> PREFIX_DECREMENT
                    7 -> POSTFIX_DECREMENT
                    8 -> BINARY_PLUS
                    9 -> BINARY_MINUS
                    10 -> MUL
                    11 -> DIV
                    12 -> MOD
                    13 -> GT
                    14 -> LT
                    15 -> GE
                    16 -> LE
                    17 -> EQEQ
                    18 -> EXCLEQ
                    19 -> EQEQEQ
                    20 -> EXCLEQEQ
                    21 -> ANDAND
                    22 -> OROR
                    23 -> EQ
                    24 -> PLUSEQ
                    25 -> MINUSEQ
                    26 -> MULEQ
                    27 -> DIVEQ
                    28 -> MODEQ
                    29 -> ARRAY_ACCESS
                    30 -> INVOKE
                    else -> error("Unexpected enum value '$index' for enum 'IrDynamicOperator'")
                }
            }
        }
    }


    fun hasOperator(): Boolean = true

    fun hasReceiver(): Boolean = true

    fun hasArgument(): Boolean = true

    val argumentCount: Int
        get() = argumentList.size
}

class IrOperation(
    val operationCase: OperationCase,
    private val fieldBlock: IrBlock?,
    private val fieldBreak: IrBreak?,
    private val fieldCall: IrCall?,
    private val fieldClassReference: IrClassReference?,
    private val fieldComposite: IrComposite?,
    private val fieldConst: IrConst?,
    private val fieldContinue: IrContinue?,
    private val fieldDelegatingConstructorCall: IrDelegatingConstructorCall?,
    private val fieldDoWhile: IrDoWhile?,
    private val fieldEnumConstructorCall: IrEnumConstructorCall?,
    private val fieldFunctionReference: IrFunctionReference?,
    private val fieldGetClass: IrGetClass?,
    private val fieldGetEnumValue: IrGetEnumValue?,
    private val fieldGetField: IrGetField?,
    private val fieldGetObject: IrGetObject?,
    private val fieldGetValue: IrGetValue?,
    private val fieldInstanceInitializerCall: IrInstanceInitializerCall?,
    private val fieldPropertyReference: IrPropertyReference?,
    private val fieldReturn: IrReturn?,
    private val fieldSetField: IrSetField?,
    private val fieldSetVariable: IrSetVariable?,
    private val fieldStringConcat: IrStringConcat?,
    private val fieldThrow: IrThrow?,
    private val fieldTry: IrTry?,
    private val fieldTypeOp: IrTypeOp?,
    private val fieldVararg: IrVararg?,
    private val fieldWhen: IrWhen?,
    private val fieldWhile: IrWhile?,
    private val fieldDynamicMember: IrDynamicMemberExpression?,
    private val fieldDynamicOperator: IrDynamicOperatorExpression?,
    private val fieldLocalDelegatedPropertyReference: IrLocalDelegatedPropertyReference?,
    private val fieldConstructorCall: IrConstructorCall?,
    private val fieldFunctionExpression: IrFunctionExpression?
) {
    enum class OperationCase {
        BLOCK,
        BREAK,
        CALL,
        CLASS_REFERENCE,
        COMPOSITE,
        CONST,
        CONTINUE,
        DELEGATING_CONSTRUCTOR_CALL,
        DO_WHILE,
        ENUM_CONSTRUCTOR_CALL,
        FUNCTION_REFERENCE,
        GET_CLASS,
        GET_ENUM_VALUE,
        GET_FIELD,
        GET_OBJECT,
        GET_VALUE,
        INSTANCE_INITIALIZER_CALL,
        PROPERTY_REFERENCE,
        RETURN,
        SET_FIELD,
        SET_VARIABLE,
        STRING_CONCAT,
        THROW,
        TRY,
        TYPE_OP,
        VARARG,
        WHEN,
        WHILE,
        DYNAMIC_MEMBER,
        DYNAMIC_OPERATOR,
        LOCAL_DELEGATED_PROPERTY_REFERENCE,
        CONSTRUCTOR_CALL,
        FUNCTION_EXPRESSION,
        OPERATION_NOT_SET
    }

    val block: IrBlock
        get() = fieldBlock!!

    val `break`: IrBreak
        get() = fieldBreak!!

    val call: IrCall
        get() = fieldCall!!

    val classReference: IrClassReference
        get() = fieldClassReference!!

    val composite: IrComposite
        get() = fieldComposite!!

    val const: IrConst
        get() = fieldConst!!

    val `continue`: IrContinue
        get() = fieldContinue!!

    val delegatingConstructorCall: IrDelegatingConstructorCall
        get() = fieldDelegatingConstructorCall!!

    val doWhile: IrDoWhile
        get() = fieldDoWhile!!

    val enumConstructorCall: IrEnumConstructorCall
        get() = fieldEnumConstructorCall!!

    val functionReference: IrFunctionReference
        get() = fieldFunctionReference!!

    val getClass: IrGetClass
        get() = fieldGetClass!!

    val getEnumValue: IrGetEnumValue
        get() = fieldGetEnumValue!!

    val getField: IrGetField
        get() = fieldGetField!!

    val getObject: IrGetObject
        get() = fieldGetObject!!

    val getValue: IrGetValue
        get() = fieldGetValue!!

    val instanceInitializerCall: IrInstanceInitializerCall
        get() = fieldInstanceInitializerCall!!

    val propertyReference: IrPropertyReference
        get() = fieldPropertyReference!!

    val `return`: IrReturn
        get() = fieldReturn!!

    val setField: IrSetField
        get() = fieldSetField!!

    val setVariable: IrSetVariable
        get() = fieldSetVariable!!

    val stringConcat: IrStringConcat
        get() = fieldStringConcat!!

    val `throw`: IrThrow
        get() = fieldThrow!!

    val `try`: IrTry
        get() = fieldTry!!

    val typeOp: IrTypeOp
        get() = fieldTypeOp!!

    val vararg: IrVararg
        get() = fieldVararg!!

    val `when`: IrWhen
        get() = fieldWhen!!

    val `while`: IrWhile
        get() = fieldWhile!!

    val dynamicMember: IrDynamicMemberExpression
        get() = fieldDynamicMember!!

    val dynamicOperator: IrDynamicOperatorExpression
        get() = fieldDynamicOperator!!

    val localDelegatedPropertyReference: IrLocalDelegatedPropertyReference
        get() = fieldLocalDelegatedPropertyReference!!

    val constructorCall: IrConstructorCall
        get() = fieldConstructorCall!!

    val functionExpression: IrFunctionExpression
        get() = fieldFunctionExpression!!

    fun hasBlock(): Boolean = fieldBlock != null

    fun hasBreak(): Boolean = fieldBreak != null

    fun hasCall(): Boolean = fieldCall != null

    fun hasClassReference(): Boolean = fieldClassReference != null

    fun hasComposite(): Boolean = fieldComposite != null

    fun hasConst(): Boolean = fieldConst != null

    fun hasContinue(): Boolean = fieldContinue != null

    fun hasDelegatingConstructorCall(): Boolean = fieldDelegatingConstructorCall != null

    fun hasDoWhile(): Boolean = fieldDoWhile != null

    fun hasEnumConstructorCall(): Boolean = fieldEnumConstructorCall != null

    fun hasFunctionReference(): Boolean = fieldFunctionReference != null

    fun hasGetClass(): Boolean = fieldGetClass != null

    fun hasGetEnumValue(): Boolean = fieldGetEnumValue != null

    fun hasGetField(): Boolean = fieldGetField != null

    fun hasGetObject(): Boolean = fieldGetObject != null

    fun hasGetValue(): Boolean = fieldGetValue != null

    fun hasInstanceInitializerCall(): Boolean = fieldInstanceInitializerCall != null

    fun hasPropertyReference(): Boolean = fieldPropertyReference != null

    fun hasReturn(): Boolean = fieldReturn != null

    fun hasSetField(): Boolean = fieldSetField != null

    fun hasSetVariable(): Boolean = fieldSetVariable != null

    fun hasStringConcat(): Boolean = fieldStringConcat != null

    fun hasThrow(): Boolean = fieldThrow != null

    fun hasTry(): Boolean = fieldTry != null

    fun hasTypeOp(): Boolean = fieldTypeOp != null

    fun hasVararg(): Boolean = fieldVararg != null

    fun hasWhen(): Boolean = fieldWhen != null

    fun hasWhile(): Boolean = fieldWhile != null

    fun hasDynamicMember(): Boolean = fieldDynamicMember != null

    fun hasDynamicOperator(): Boolean = fieldDynamicOperator != null

    fun hasLocalDelegatedPropertyReference(): Boolean = fieldLocalDelegatedPropertyReference != null

    fun hasConstructorCall(): Boolean = fieldConstructorCall != null

    fun hasFunctionExpression(): Boolean = fieldFunctionExpression != null
}

enum class IrTypeOperator {
    CAST,
    IMPLICIT_CAST,
    IMPLICIT_NOTNULL,
    IMPLICIT_COERCION_TO_UNIT,
    IMPLICIT_INTEGER_COERCION,
    SAFE_CAST,
    INSTANCEOF,
    NOT_INSTANCEOF,
    SAM_CONVERSION,
    IMPLICIT_DYNAMIC_CAST;

    companion object {
        fun fromIndex(index: Int): IrTypeOperator {
            return when (index) {
                1 -> CAST
                2 -> IMPLICIT_CAST
                3 -> IMPLICIT_NOTNULL
                4 -> IMPLICIT_COERCION_TO_UNIT
                5 -> IMPLICIT_INTEGER_COERCION
                6 -> SAFE_CAST
                7 -> INSTANCEOF
                8 -> NOT_INSTANCEOF
                9 -> SAM_CONVERSION
                10 -> IMPLICIT_DYNAMIC_CAST
                else -> error("Unexpected enum value '$index' for enum 'IrTypeOperator'")
            }
        }
    }
}

class IrExpression(
    val operation: IrOperation,
    val type: Int,
    val coordinates: Long
) {

    fun hasOperation(): Boolean = true

    fun hasType(): Boolean = true

    fun hasCoordinates(): Boolean = true
}

class NullableIrExpression(
    private val fieldExpression: IrExpression?
) {

    val expression: IrExpression
        get() = fieldExpression!!

    fun hasExpression(): Boolean = fieldExpression != null
}

class IrDeclarationBase(
    val symbol: Long,
    val originName: Int,
    val coordinates: Long,
    val flags: Long,
    val annotationList: List<IrConstructorCall>
) {

    fun hasSymbol(): Boolean = true

    fun hasOriginName(): Boolean = true

    fun hasCoordinates(): Boolean = true

    fun hasFlags(): Boolean = true

    fun hasAnnotation(): Boolean = true

    val annotationCount: Int
        get() = annotationList.size
}

class IrFunctionBase(
    val base: IrDeclarationBase,
    val nameType: Long,
    val typeParameterList: List<IrTypeParameter>,
    private val fieldDispatchReceiver: IrValueParameter?,
    private val fieldExtensionReceiver: IrValueParameter?,
    val valueParameterList: List<IrValueParameter>,
    private val fieldBody: Int?
) {

    val dispatchReceiver: IrValueParameter
        get() = fieldDispatchReceiver!!

    val extensionReceiver: IrValueParameter
        get() = fieldExtensionReceiver!!

    val body: Int
        get() = fieldBody!!

    fun hasBase(): Boolean = true

    fun hasNameType(): Boolean = true

    fun hasTypeParameter(): Boolean = true

    val typeParameterCount: Int
        get() = typeParameterList.size

    fun hasDispatchReceiver(): Boolean = fieldDispatchReceiver != null

    fun hasExtensionReceiver(): Boolean = fieldExtensionReceiver != null

    fun hasValueParameter(): Boolean = true

    val valueParameterCount: Int
        get() = valueParameterList.size

    fun hasBody(): Boolean = fieldBody != null
}

class IrFunction(
    val base: IrFunctionBase,
    val overriddenList: List<Long>
) {

    fun hasBase(): Boolean = true

    fun hasOverridden(): Boolean = true

    val overriddenCount: Int
        get() = overriddenList.size
}

class IrConstructor(
    val base: IrFunctionBase
) {

    fun hasBase(): Boolean = true
}

class IrField(
    val base: IrDeclarationBase,
    val nameType: Long,
    private val fieldInitializer: Int?
) {

    val initializer: Int
        get() = fieldInitializer!!

    fun hasBase(): Boolean = true

    fun hasNameType(): Boolean = true

    fun hasInitializer(): Boolean = fieldInitializer != null
}

class IrLocalDelegatedProperty(
    val base: IrDeclarationBase,
    val nameType: Long,
    val delegate: IrVariable,
    private val fieldGetter: IrFunction?,
    private val fieldSetter: IrFunction?
) {

    val getter: IrFunction
        get() = fieldGetter!!

    val setter: IrFunction
        get() = fieldSetter!!

    fun hasBase(): Boolean = true

    fun hasNameType(): Boolean = true

    fun hasDelegate(): Boolean = true

    fun hasGetter(): Boolean = fieldGetter != null

    fun hasSetter(): Boolean = fieldSetter != null
}

class IrProperty(
    val base: IrDeclarationBase,
    val name: Int,
    private val fieldBackingField: IrField?,
    private val fieldGetter: IrFunction?,
    private val fieldSetter: IrFunction?
) {

    val backingField: IrField
        get() = fieldBackingField!!

    val getter: IrFunction
        get() = fieldGetter!!

    val setter: IrFunction
        get() = fieldSetter!!

    fun hasBase(): Boolean = true

    fun hasName(): Boolean = true

    fun hasBackingField(): Boolean = fieldBackingField != null

    fun hasGetter(): Boolean = fieldGetter != null

    fun hasSetter(): Boolean = fieldSetter != null
}

class IrVariable(
    val base: IrDeclarationBase,
    val nameType: Long,
    private val fieldInitializer: IrExpression?
) {

    val initializer: IrExpression
        get() = fieldInitializer!!

    fun hasBase(): Boolean = true

    fun hasNameType(): Boolean = true

    fun hasInitializer(): Boolean = fieldInitializer != null
}

class IrValueParameter(
    val base: IrDeclarationBase,
    val nameType: Long,
    private val fieldVarargElementType: Int?,
    private val fieldDefaultValue: Int?
) {

    val varargElementType: Int
        get() = fieldVarargElementType!!

    val defaultValue: Int
        get() = fieldDefaultValue!!

    fun hasBase(): Boolean = true

    fun hasNameType(): Boolean = true

    fun hasVarargElementType(): Boolean = fieldVarargElementType != null

    fun hasDefaultValue(): Boolean = fieldDefaultValue != null
}

class IrTypeParameter(
    val base: IrDeclarationBase,
    val name: Int,
    val superTypeList: List<Int>
) {

    fun hasBase(): Boolean = true

    fun hasName(): Boolean = true

    fun hasSuperType(): Boolean = true

    val superTypeCount: Int
        get() = superTypeList.size
}

class IrClass(
    val base: IrDeclarationBase,
    val name: Int,
    private val fieldThisReceiver: IrValueParameter?,
    val typeParameterList: List<IrTypeParameter>,
    val declarationList: List<IrDeclaration>,
    val superTypeList: List<Int>
) {

    val thisReceiver: IrValueParameter
        get() = fieldThisReceiver!!

    fun hasBase(): Boolean = true

    fun hasName(): Boolean = true

    fun hasThisReceiver(): Boolean = fieldThisReceiver != null

    fun hasTypeParameter(): Boolean = true

    val typeParameterCount: Int
        get() = typeParameterList.size

    fun hasDeclaration(): Boolean = true

    val declarationCount: Int
        get() = declarationList.size

    fun hasSuperType(): Boolean = true

    val superTypeCount: Int
        get() = superTypeList.size
}

class IrTypeAlias(
    val base: IrDeclarationBase,
    val nameType: Long,
    val typeParameterList: List<IrTypeParameter>
) {

    fun hasBase(): Boolean = true

    fun hasNameType(): Boolean = true

    fun hasTypeParameter(): Boolean = true

    val typeParameterCount: Int
        get() = typeParameterList.size
}

class IrEnumEntry(
    val base: IrDeclarationBase,
    val name: Int,
    private val fieldInitializer: Int?,
    private val fieldCorrespondingClass: IrClass?
) {

    val initializer: Int
        get() = fieldInitializer!!

    val correspondingClass: IrClass
        get() = fieldCorrespondingClass!!

    fun hasBase(): Boolean = true

    fun hasName(): Boolean = true

    fun hasInitializer(): Boolean = fieldInitializer != null

    fun hasCorrespondingClass(): Boolean = fieldCorrespondingClass != null
}

class IrAnonymousInit(
    val base: IrDeclarationBase,
    val body: Int
) {

    fun hasBase(): Boolean = true

    fun hasBody(): Boolean = true
}

class IrDeclaration(
    val declaratorCase: DeclaratorCase,
    private val fieldIrAnonymousInit: IrAnonymousInit?,
    private val fieldIrClass: IrClass?,
    private val fieldIrConstructor: IrConstructor?,
    private val fieldIrEnumEntry: IrEnumEntry?,
    private val fieldIrField: IrField?,
    private val fieldIrFunction: IrFunction?,
    private val fieldIrProperty: IrProperty?,
    private val fieldIrTypeParameter: IrTypeParameter?,
    private val fieldIrVariable: IrVariable?,
    private val fieldIrValueParameter: IrValueParameter?,
    private val fieldIrLocalDelegatedProperty: IrLocalDelegatedProperty?,
    private val fieldIrTypeAlias: IrTypeAlias?
) {
    enum class DeclaratorCase {
        IR_ANONYMOUS_INIT,
        IR_CLASS,
        IR_CONSTRUCTOR,
        IR_ENUM_ENTRY,
        IR_FIELD,
        IR_FUNCTION,
        IR_PROPERTY,
        IR_TYPE_PARAMETER,
        IR_VARIABLE,
        IR_VALUE_PARAMETER,
        IR_LOCAL_DELEGATED_PROPERTY,
        IR_TYPE_ALIAS,
        DECLARATOR_NOT_SET
    }

    val irAnonymousInit: IrAnonymousInit
        get() = fieldIrAnonymousInit!!

    val irClass: IrClass
        get() = fieldIrClass!!

    val irConstructor: IrConstructor
        get() = fieldIrConstructor!!

    val irEnumEntry: IrEnumEntry
        get() = fieldIrEnumEntry!!

    val irField: IrField
        get() = fieldIrField!!

    val irFunction: IrFunction
        get() = fieldIrFunction!!

    val irProperty: IrProperty
        get() = fieldIrProperty!!

    val irTypeParameter: IrTypeParameter
        get() = fieldIrTypeParameter!!

    val irVariable: IrVariable
        get() = fieldIrVariable!!

    val irValueParameter: IrValueParameter
        get() = fieldIrValueParameter!!

    val irLocalDelegatedProperty: IrLocalDelegatedProperty
        get() = fieldIrLocalDelegatedProperty!!

    val irTypeAlias: IrTypeAlias
        get() = fieldIrTypeAlias!!

    fun hasIrAnonymousInit(): Boolean = fieldIrAnonymousInit != null

    fun hasIrClass(): Boolean = fieldIrClass != null

    fun hasIrConstructor(): Boolean = fieldIrConstructor != null

    fun hasIrEnumEntry(): Boolean = fieldIrEnumEntry != null

    fun hasIrField(): Boolean = fieldIrField != null

    fun hasIrFunction(): Boolean = fieldIrFunction != null

    fun hasIrProperty(): Boolean = fieldIrProperty != null

    fun hasIrTypeParameter(): Boolean = fieldIrTypeParameter != null

    fun hasIrVariable(): Boolean = fieldIrVariable != null

    fun hasIrValueParameter(): Boolean = fieldIrValueParameter != null

    fun hasIrLocalDelegatedProperty(): Boolean = fieldIrLocalDelegatedProperty != null

    fun hasIrTypeAlias(): Boolean = fieldIrTypeAlias != null
}

class IrBranch(
    val condition: IrExpression,
    val result: IrExpression
) {

    fun hasCondition(): Boolean = true

    fun hasResult(): Boolean = true
}

class IrBlockBody(
    val statementList: List<IrStatement>
) {

    fun hasStatement(): Boolean = true

    val statementCount: Int
        get() = statementList.size
}

class IrCatch(
    val catchParameter: IrVariable,
    val result: IrExpression
) {

    fun hasCatchParameter(): Boolean = true

    fun hasResult(): Boolean = true
}

enum class IrSyntheticBodyKind {
    ENUM_VALUES,
    ENUM_VALUEOF;

    companion object {
        fun fromIndex(index: Int): IrSyntheticBodyKind {
            return when (index) {
                1 -> ENUM_VALUES
                2 -> ENUM_VALUEOF
                else -> error("Unexpected enum value '$index' for enum 'IrSyntheticBodyKind'")
            }
        }
    }
}

class IrSyntheticBody(
    val kind: IrSyntheticBodyKind
) {

    fun hasKind(): Boolean = true
}

class IrStatement(
    val coordinates: Long,
    val statementCase: StatementCase,
    private val fieldDeclaration: IrDeclaration?,
    private val fieldExpression: IrExpression?,
    private val fieldBlockBody: IrBlockBody?,
    private val fieldBranch: IrBranch?,
    private val fieldCatch: IrCatch?,
    private val fieldSyntheticBody: IrSyntheticBody?
) {
    enum class StatementCase {
        DECLARATION,
        EXPRESSION,
        BLOCK_BODY,
        BRANCH,
        CATCH,
        SYNTHETIC_BODY,
        STATEMENT_NOT_SET
    }

    val declaration: IrDeclaration
        get() = fieldDeclaration!!

    val expression: IrExpression
        get() = fieldExpression!!

    val blockBody: IrBlockBody
        get() = fieldBlockBody!!

    val branch: IrBranch
        get() = fieldBranch!!

    val catch: IrCatch
        get() = fieldCatch!!

    val syntheticBody: IrSyntheticBody
        get() = fieldSyntheticBody!!

    fun hasCoordinates(): Boolean = true

    fun hasDeclaration(): Boolean = fieldDeclaration != null

    fun hasExpression(): Boolean = fieldExpression != null

    fun hasBlockBody(): Boolean = fieldBlockBody != null

    fun hasBranch(): Boolean = fieldBranch != null

    fun hasCatch(): Boolean = fieldCatch != null

    fun hasSyntheticBody(): Boolean = fieldSyntheticBody != null
}

class IrProtoReaderMimic(private val source: ByteArray) {


    private var offset = 0

    private var currentEnd = source.size

    private val hasData: Boolean
        get() = offset < currentEnd

    private inline fun <T> readWithLength(block: () -> T): T {
        val length = readInt32()
        val oldEnd = currentEnd
        currentEnd = offset + length
        try {
            return block()
        } finally {
            currentEnd = oldEnd
        }
    }

    private fun nextByte(): Byte {
        if (!hasData) error("Oops")
        return source[offset++]
    }

    private fun readVarint64(): Long {
        var result = 0L

        var shift = 0
        while (true) {
            val b = nextByte().toInt()

            result = result or ((b and 0x7F).toLong() shl shift)
            shift += 7

            if ((b and 0x80) == 0) break
        }

        if (shift > 70) {
            error("int64 overflow $shift")
        }

        return result
    }

    fun readInt32(): Int = readVarint64().toInt()

    fun readInt64(): Long = readVarint64()

    fun readBool(): Boolean = readVarint64() != 0L

    fun readFloat(): Float {
        var bits = nextByte().toInt() and 0xFF
        bits = bits or ((nextByte().toInt() and 0xFF) shl 8)
        bits = bits or ((nextByte().toInt() and 0xFF) shl 16)
        bits = bits or ((nextByte().toInt() and 0xFF) shl 24)

        return Float.fromBits(bits)
    }

    fun readDouble(): Double {
        var bits = nextByte().toLong() and 0xFF
        bits = bits or ((nextByte().toLong() and 0xFF) shl 8)
        bits = bits or ((nextByte().toLong() and 0xFF) shl 16)
        bits = bits or ((nextByte().toLong() and 0xFF) shl 24)
        bits = bits or ((nextByte().toLong() and 0xFF) shl 32)
        bits = bits or ((nextByte().toLong() and 0xFF) shl 40)
        bits = bits or ((nextByte().toLong() and 0xFF) shl 48)
        bits = bits or ((nextByte().toLong() and 0xFF) shl 56)

        return Double.fromBits(bits)
    }

    private fun readString(): String {
        val length = readInt32()
        val result = String(source, offset, length)
        offset += length
        return result
    }

    private fun skip(type: Int) {
        when (type) {
            0 -> readInt64()
            1 -> offset += 8
            2 -> {
                val len = readInt32()
                offset += len
            }
            3, 4 -> error("groups")
            5 -> offset += 4
        }
    }


    fun readFileEntry(): FileEntry {
        var name: String = ""
        var lineStartOffsets: MutableList<Int> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> name = readString()
                18 -> readWithLength { while (hasData) lineStartOffsets.add(readInt32()) } 
                else -> skip(fieldHeader and 7)
            }
        }
        return FileEntry(name, lineStartOffsets)
    }

    fun readIrFile(): IrFile {
        var declarationId: MutableList<Int> = mutableListOf()
        var fileEntry: FileEntry? = null
        var fqName: MutableList<Int> = mutableListOf()
        var annotation: MutableList<IrConstructorCall> = mutableListOf()
        var explicitlyExportedToCompiler: MutableList<Long> = mutableListOf()
        var actuals: MutableList<Actual> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> readWithLength { while (hasData) declarationId.add(readInt32()) } 
                18 -> fileEntry = readWithLength { readFileEntry() }
                26 -> readWithLength { while (hasData) fqName.add(readInt32()) } 
                34 -> annotation.add(readWithLength { readIrConstructorCall() })
                42 -> readWithLength { while (hasData) explicitlyExportedToCompiler.add(readInt64()) } 
                50 -> actuals.add(readWithLength { readActual() })
                else -> skip(fieldHeader and 7)
            }
        }
        return IrFile(declarationId, fileEntry!!, fqName, annotation, explicitlyExportedToCompiler, actuals)
    }

    fun readPublicIdSignature(): PublicIdSignature {
        var packageFqName: MutableList<Int> = mutableListOf()
        var declarationFqName: MutableList<Int> = mutableListOf()
        var memberUniqId: Long? = null
        var flags: Long = 0
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> readWithLength { while (hasData) packageFqName.add(readInt32()) } 
                18 -> readWithLength { while (hasData) declarationFqName.add(readInt32()) } 
                24 -> memberUniqId = readInt64()
                32 -> flags = readInt64()
                else -> skip(fieldHeader and 7)
            }
        }
        return PublicIdSignature(packageFqName, declarationFqName, memberUniqId, flags)
    }

    fun readAccessorIdSignature(): AccessorIdSignature {
        var propertySignature: Int = 0
        var name: Int = 0
        var accessorHashId: Long = 0L
        var flags: Long = 0
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> propertySignature = readInt32()
                16 -> name = readInt32()
                24 -> accessorHashId = readInt64()
                32 -> flags = readInt64()
                else -> skip(fieldHeader and 7)
            }
        }
        return AccessorIdSignature(propertySignature, name, accessorHashId, flags)
    }

    fun readFileLocalIdSignature(): FileLocalIdSignature {
        var container: Int = 0
        var localId: Long = 0L
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> container = readInt32()
                16 -> localId = readInt64()
                else -> skip(fieldHeader and 7)
            }
        }
        return FileLocalIdSignature(container, localId)
    }

    fun readIdSignature(): IdSignature {
        var publicSig: PublicIdSignature? = null
        var privateSig: FileLocalIdSignature? = null
        var accessorSig: AccessorIdSignature? = null
        var scopedLocalSig: Int? = null
        var oneOfCase: IdSignature.IdsigCase = IdSignature.IdsigCase.IDSIG_NOT_SET
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> {
                    publicSig = readWithLength { readPublicIdSignature() }
                    oneOfCase = IdSignature.IdsigCase.PUBLIC_SIG
                }
                18 -> {
                    privateSig = readWithLength { readFileLocalIdSignature() }
                    oneOfCase = IdSignature.IdsigCase.PRIVATE_SIG
                }
                26 -> {
                    accessorSig = readWithLength { readAccessorIdSignature() }
                    oneOfCase = IdSignature.IdsigCase.ACCESSOR_SIG
                }
                32 -> {
                    scopedLocalSig = readInt32()
                    oneOfCase = IdSignature.IdsigCase.SCOPED_LOCAL_SIG
                }
                else -> skip(fieldHeader and 7)
            }
        }
        return IdSignature(oneOfCase!!, publicSig, privateSig, accessorSig, scopedLocalSig)
    }

    fun readActual(): Actual {
        var actualSymbol: Long = 0L
        var expectSymbol: Long = 0L
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> actualSymbol = readInt64()
                16 -> expectSymbol = readInt64()
                else -> skip(fieldHeader and 7)
            }
        }
        return Actual(actualSymbol, expectSymbol)
    }

    fun readIrSimpleType(): IrSimpleType {
        var annotation: MutableList<IrConstructorCall> = mutableListOf()
        var classifier: Long = 0L
        var hasQuestionMark: Boolean = false
        var argument: MutableList<Long> = mutableListOf()
        var abbreviation: IrTypeAbbreviation? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> annotation.add(readWithLength { readIrConstructorCall() })
                16 -> classifier = readInt64()
                24 -> hasQuestionMark = readBool()
                34 -> readWithLength { while (hasData) argument.add(readInt64()) } 
                42 -> abbreviation = readWithLength { readIrTypeAbbreviation() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrSimpleType(annotation, classifier, hasQuestionMark, argument, abbreviation)
    }

    fun readIrTypeAbbreviation(): IrTypeAbbreviation {
        var annotation: MutableList<IrConstructorCall> = mutableListOf()
        var typeAlias: Long = 0L
        var hasQuestionMark: Boolean = false
        var argument: MutableList<Long> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> annotation.add(readWithLength { readIrConstructorCall() })
                16 -> typeAlias = readInt64()
                24 -> hasQuestionMark = readBool()
                34 -> readWithLength { while (hasData) argument.add(readInt64()) } 
                else -> skip(fieldHeader and 7)
            }
        }
        return IrTypeAbbreviation(annotation, typeAlias, hasQuestionMark, argument)
    }

    fun readIrDynamicType(): IrDynamicType {
        var annotation: MutableList<IrConstructorCall> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> annotation.add(readWithLength { readIrConstructorCall() })
                else -> skip(fieldHeader and 7)
            }
        }
        return IrDynamicType(annotation)
    }

    fun readIrErrorType(): IrErrorType {
        var annotation: MutableList<IrConstructorCall> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> annotation.add(readWithLength { readIrConstructorCall() })
                else -> skip(fieldHeader and 7)
            }
        }
        return IrErrorType(annotation)
    }

    fun readIrType(): IrType {
        var simple: IrSimpleType? = null
        var dynamic: IrDynamicType? = null
        var error: IrErrorType? = null
        var oneOfCase: IrType.KindCase = IrType.KindCase.KIND_NOT_SET
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> {
                    simple = readWithLength { readIrSimpleType() }
                    oneOfCase = IrType.KindCase.SIMPLE
                }
                18 -> {
                    dynamic = readWithLength { readIrDynamicType() }
                    oneOfCase = IrType.KindCase.DYNAMIC
                }
                26 -> {
                    error = readWithLength { readIrErrorType() }
                    oneOfCase = IrType.KindCase.ERROR
                }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrType(oneOfCase!!, simple, dynamic, error)
    }

    fun readIrBreak(): IrBreak {
        var loopId: Int = 0
        var label: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> loopId = readInt32()
                16 -> label = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrBreak(loopId, label)
    }

    fun readIrBlock(): IrBlock {
        var statement: MutableList<IrStatement> = mutableListOf()
        var originName: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> statement.add(readWithLength { readIrStatement() })
                16 -> originName = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrBlock(statement, originName)
    }

    fun readMemberAccessCommon(): MemberAccessCommon {
        var dispatchReceiver: IrExpression? = null
        var extensionReceiver: IrExpression? = null
        var valueArgument: MutableList<NullableIrExpression> = mutableListOf()
        var typeArgument: MutableList<Int> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> dispatchReceiver = readWithLength { readIrExpression() }
                18 -> extensionReceiver = readWithLength { readIrExpression() }
                26 -> valueArgument.add(readWithLength { readNullableIrExpression() })
                34 -> readWithLength { while (hasData) typeArgument.add(readInt32()) } 
                else -> skip(fieldHeader and 7)
            }
        }
        return MemberAccessCommon(dispatchReceiver, extensionReceiver, valueArgument, typeArgument)
    }

    fun readIrCall(): IrCall {
        var symbol: Long = 0L
        var memberAccess: MemberAccessCommon? = null
        var super_: Long? = null
        var originName: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                18 -> memberAccess = readWithLength { readMemberAccessCommon() }
                24 -> super_ = readInt64()
                32 -> originName = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrCall(symbol, memberAccess!!, super_, originName)
    }

    fun readIrConstructorCall(): IrConstructorCall {
        var symbol: Long = 0L
        var constructorTypeArgumentsCount: Int = 0
        var memberAccess: MemberAccessCommon? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                16 -> constructorTypeArgumentsCount = readInt32()
                26 -> memberAccess = readWithLength { readMemberAccessCommon() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrConstructorCall(symbol, constructorTypeArgumentsCount, memberAccess!!)
    }

    fun readIrFunctionReference(): IrFunctionReference {
        var symbol: Long = 0L
        var originName: Int? = null
        var memberAccess: MemberAccessCommon? = null
        var reflectionTargetSymbol: Long? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                16 -> originName = readInt32()
                26 -> memberAccess = readWithLength { readMemberAccessCommon() }
                32 -> reflectionTargetSymbol = readInt64()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrFunctionReference(symbol, originName, memberAccess!!, reflectionTargetSymbol)
    }

    fun readIrLocalDelegatedPropertyReference(): IrLocalDelegatedPropertyReference {
        var delegate: Long = 0L
        var getter: Long? = null
        var setter: Long? = null
        var symbol: Long = 0L
        var originName: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> delegate = readInt64()
                16 -> getter = readInt64()
                24 -> setter = readInt64()
                32 -> symbol = readInt64()
                40 -> originName = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrLocalDelegatedPropertyReference(delegate, getter, setter, symbol, originName)
    }

    fun readIrPropertyReference(): IrPropertyReference {
        var field: Long? = null
        var getter: Long? = null
        var setter: Long? = null
        var originName: Int? = null
        var memberAccess: MemberAccessCommon? = null
        var symbol: Long = 0L
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> field = readInt64()
                16 -> getter = readInt64()
                24 -> setter = readInt64()
                32 -> originName = readInt32()
                42 -> memberAccess = readWithLength { readMemberAccessCommon() }
                48 -> symbol = readInt64()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrPropertyReference(field, getter, setter, originName, memberAccess!!, symbol)
    }

    fun readIrComposite(): IrComposite {
        var statement: MutableList<IrStatement> = mutableListOf()
        var originName: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> statement.add(readWithLength { readIrStatement() })
                16 -> originName = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrComposite(statement, originName)
    }

    fun readIrClassReference(): IrClassReference {
        var classSymbol: Long = 0L
        var classType: Int = 0
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> classSymbol = readInt64()
                16 -> classType = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrClassReference(classSymbol, classType)
    }

    fun readIrConst(): IrConst {
        var null_: Boolean? = null
        var boolean: Boolean? = null
        var char: Int? = null
        var byte: Int? = null
        var short: Int? = null
        var int: Int? = null
        var long: Long? = null
        var float: Float? = null
        var double: Double? = null
        var string: Int? = null
        var oneOfCase: IrConst.ValueCase = IrConst.ValueCase.VALUE_NOT_SET
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> {
                    null_ = readBool()
                    oneOfCase = IrConst.ValueCase.NULL
                }
                16 -> {
                    boolean = readBool()
                    oneOfCase = IrConst.ValueCase.BOOLEAN
                }
                24 -> {
                    char = readInt32()
                    oneOfCase = IrConst.ValueCase.CHAR
                }
                32 -> {
                    byte = readInt32()
                    oneOfCase = IrConst.ValueCase.BYTE
                }
                40 -> {
                    short = readInt32()
                    oneOfCase = IrConst.ValueCase.SHORT
                }
                48 -> {
                    int = readInt32()
                    oneOfCase = IrConst.ValueCase.INT
                }
                56 -> {
                    long = readInt64()
                    oneOfCase = IrConst.ValueCase.LONG
                }
                69 -> {
                    float = readFloat()
                    oneOfCase = IrConst.ValueCase.FLOAT
                }
                73 -> {
                    double = readDouble()
                    oneOfCase = IrConst.ValueCase.DOUBLE
                }
                80 -> {
                    string = readInt32()
                    oneOfCase = IrConst.ValueCase.STRING
                }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrConst(oneOfCase!!, null_, boolean, char, byte, short, int, long, float, double, string)
    }

    fun readIrContinue(): IrContinue {
        var loopId: Int = 0
        var label: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> loopId = readInt32()
                16 -> label = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrContinue(loopId, label)
    }

    fun readIrDelegatingConstructorCall(): IrDelegatingConstructorCall {
        var symbol: Long = 0L
        var memberAccess: MemberAccessCommon? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                18 -> memberAccess = readWithLength { readMemberAccessCommon() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrDelegatingConstructorCall(symbol, memberAccess!!)
    }

    fun readIrDoWhile(): IrDoWhile {
        var loop: Loop? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> loop = readWithLength { readLoop() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrDoWhile(loop!!)
    }

    fun readIrEnumConstructorCall(): IrEnumConstructorCall {
        var symbol: Long = 0L
        var memberAccess: MemberAccessCommon? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                18 -> memberAccess = readWithLength { readMemberAccessCommon() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrEnumConstructorCall(symbol, memberAccess!!)
    }

    fun readIrGetClass(): IrGetClass {
        var argument: IrExpression? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> argument = readWithLength { readIrExpression() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrGetClass(argument!!)
    }

    fun readIrGetEnumValue(): IrGetEnumValue {
        var symbol: Long = 0L
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrGetEnumValue(symbol)
    }

    fun readFieldAccessCommon(): FieldAccessCommon {
        var symbol: Long = 0L
        var super_: Long? = null
        var receiver: IrExpression? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                16 -> super_ = readInt64()
                26 -> receiver = readWithLength { readIrExpression() }
                else -> skip(fieldHeader and 7)
            }
        }
        return FieldAccessCommon(symbol, super_, receiver)
    }

    fun readIrGetField(): IrGetField {
        var fieldAccess: FieldAccessCommon? = null
        var originName: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> fieldAccess = readWithLength { readFieldAccessCommon() }
                16 -> originName = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrGetField(fieldAccess!!, originName)
    }

    fun readIrGetValue(): IrGetValue {
        var symbol: Long = 0L
        var originName: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                16 -> originName = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrGetValue(symbol, originName)
    }

    fun readIrGetObject(): IrGetObject {
        var symbol: Long = 0L
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrGetObject(symbol)
    }

    fun readIrInstanceInitializerCall(): IrInstanceInitializerCall {
        var symbol: Long = 0L
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrInstanceInitializerCall(symbol)
    }

    fun readLoop(): Loop {
        var loopId: Int = 0
        var condition: IrExpression? = null
        var label: Int? = null
        var body: IrExpression? = null
        var originName: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> loopId = readInt32()
                18 -> condition = readWithLength { readIrExpression() }
                24 -> label = readInt32()
                34 -> body = readWithLength { readIrExpression() }
                40 -> originName = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return Loop(loopId, condition!!, label, body, originName)
    }

    fun readIrReturn(): IrReturn {
        var returnTarget: Long = 0L
        var value: IrExpression? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> returnTarget = readInt64()
                18 -> value = readWithLength { readIrExpression() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrReturn(returnTarget, value!!)
    }

    fun readIrSetField(): IrSetField {
        var fieldAccess: FieldAccessCommon? = null
        var value: IrExpression? = null
        var originName: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> fieldAccess = readWithLength { readFieldAccessCommon() }
                18 -> value = readWithLength { readIrExpression() }
                24 -> originName = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrSetField(fieldAccess!!, value!!, originName)
    }

    fun readIrSetVariable(): IrSetVariable {
        var symbol: Long = 0L
        var value: IrExpression? = null
        var originName: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                18 -> value = readWithLength { readIrExpression() }
                24 -> originName = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrSetVariable(symbol, value!!, originName)
    }

    fun readIrSpreadElement(): IrSpreadElement {
        var expression: IrExpression? = null
        var coordinates: Long = 0L
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> expression = readWithLength { readIrExpression() }
                16 -> coordinates = readInt64()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrSpreadElement(expression!!, coordinates)
    }

    fun readIrStringConcat(): IrStringConcat {
        var argument: MutableList<IrExpression> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> argument.add(readWithLength { readIrExpression() })
                else -> skip(fieldHeader and 7)
            }
        }
        return IrStringConcat(argument)
    }

    fun readIrThrow(): IrThrow {
        var value: IrExpression? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> value = readWithLength { readIrExpression() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrThrow(value!!)
    }

    fun readIrTry(): IrTry {
        var result: IrExpression? = null
        var catch: MutableList<IrStatement> = mutableListOf()
        var finally: IrExpression? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> result = readWithLength { readIrExpression() }
                18 -> catch.add(readWithLength { readIrStatement() })
                26 -> finally = readWithLength { readIrExpression() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrTry(result!!, catch, finally)
    }

    fun readIrTypeOp(): IrTypeOp {
        var operator: IrTypeOperator? = null
        var operand: Int = 0
        var argument: IrExpression? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> operator = IrTypeOperator.fromIndex(readInt32())
                16 -> operand = readInt32()
                26 -> argument = readWithLength { readIrExpression() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrTypeOp(operator!!, operand, argument!!)
    }

    fun readIrVararg(): IrVararg {
        var elementType: Int = 0
        var element: MutableList<IrVarargElement> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> elementType = readInt32()
                18 -> element.add(readWithLength { readIrVarargElement() })
                else -> skip(fieldHeader and 7)
            }
        }
        return IrVararg(elementType, element)
    }

    fun readIrVarargElement(): IrVarargElement {
        var expression: IrExpression? = null
        var spreadElement: IrSpreadElement? = null
        var oneOfCase: IrVarargElement.VarargElementCase = IrVarargElement.VarargElementCase.VARARG_ELEMENT_NOT_SET
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> {
                    expression = readWithLength { readIrExpression() }
                    oneOfCase = IrVarargElement.VarargElementCase.EXPRESSION
                }
                18 -> {
                    spreadElement = readWithLength { readIrSpreadElement() }
                    oneOfCase = IrVarargElement.VarargElementCase.SPREAD_ELEMENT
                }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrVarargElement(oneOfCase!!, expression, spreadElement)
    }

    fun readIrWhen(): IrWhen {
        var branch: MutableList<IrStatement> = mutableListOf()
        var originName: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> branch.add(readWithLength { readIrStatement() })
                16 -> originName = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrWhen(branch, originName)
    }

    fun readIrWhile(): IrWhile {
        var loop: Loop? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> loop = readWithLength { readLoop() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrWhile(loop!!)
    }

    fun readIrFunctionExpression(): IrFunctionExpression {
        var function: IrFunction? = null
        var originName: Int = 0
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> function = readWithLength { readIrFunction() }
                16 -> originName = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrFunctionExpression(function!!, originName)
    }

    fun readIrDynamicMemberExpression(): IrDynamicMemberExpression {
        var memberName: Int = 0
        var receiver: IrExpression? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> memberName = readInt32()
                18 -> receiver = readWithLength { readIrExpression() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrDynamicMemberExpression(memberName, receiver!!)
    }

    fun readIrDynamicOperatorExpression(): IrDynamicOperatorExpression {
        var operator: IrDynamicOperatorExpression.IrDynamicOperator? = null
        var receiver: IrExpression? = null
        var argument: MutableList<IrExpression> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> operator = IrDynamicOperatorExpression.IrDynamicOperator.fromIndex(readInt32())
                18 -> receiver = readWithLength { readIrExpression() }
                26 -> argument.add(readWithLength { readIrExpression() })
                else -> skip(fieldHeader and 7)
            }
        }
        return IrDynamicOperatorExpression(operator!!, receiver!!, argument)
    }

    fun readIrOperation(): IrOperation {
        var block: IrBlock? = null
        var break_: IrBreak? = null
        var call: IrCall? = null
        var classReference: IrClassReference? = null
        var composite: IrComposite? = null
        var const: IrConst? = null
        var continue_: IrContinue? = null
        var delegatingConstructorCall: IrDelegatingConstructorCall? = null
        var doWhile: IrDoWhile? = null
        var enumConstructorCall: IrEnumConstructorCall? = null
        var functionReference: IrFunctionReference? = null
        var getClass: IrGetClass? = null
        var getEnumValue: IrGetEnumValue? = null
        var getField: IrGetField? = null
        var getObject: IrGetObject? = null
        var getValue: IrGetValue? = null
        var instanceInitializerCall: IrInstanceInitializerCall? = null
        var propertyReference: IrPropertyReference? = null
        var return_: IrReturn? = null
        var setField: IrSetField? = null
        var setVariable: IrSetVariable? = null
        var stringConcat: IrStringConcat? = null
        var throw_: IrThrow? = null
        var try_: IrTry? = null
        var typeOp: IrTypeOp? = null
        var vararg: IrVararg? = null
        var when_: IrWhen? = null
        var while_: IrWhile? = null
        var dynamicMember: IrDynamicMemberExpression? = null
        var dynamicOperator: IrDynamicOperatorExpression? = null
        var localDelegatedPropertyReference: IrLocalDelegatedPropertyReference? = null
        var constructorCall: IrConstructorCall? = null
        var functionExpression: IrFunctionExpression? = null
        var oneOfCase: IrOperation.OperationCase = IrOperation.OperationCase.OPERATION_NOT_SET
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> {
                    block = readWithLength { readIrBlock() }
                    oneOfCase = IrOperation.OperationCase.BLOCK
                }
                18 -> {
                    break_ = readWithLength { readIrBreak() }
                    oneOfCase = IrOperation.OperationCase.BREAK
                }
                26 -> {
                    call = readWithLength { readIrCall() }
                    oneOfCase = IrOperation.OperationCase.CALL
                }
                34 -> {
                    classReference = readWithLength { readIrClassReference() }
                    oneOfCase = IrOperation.OperationCase.CLASS_REFERENCE
                }
                42 -> {
                    composite = readWithLength { readIrComposite() }
                    oneOfCase = IrOperation.OperationCase.COMPOSITE
                }
                50 -> {
                    const = readWithLength { readIrConst() }
                    oneOfCase = IrOperation.OperationCase.CONST
                }
                58 -> {
                    continue_ = readWithLength { readIrContinue() }
                    oneOfCase = IrOperation.OperationCase.CONTINUE
                }
                66 -> {
                    delegatingConstructorCall = readWithLength { readIrDelegatingConstructorCall() }
                    oneOfCase = IrOperation.OperationCase.DELEGATING_CONSTRUCTOR_CALL
                }
                74 -> {
                    doWhile = readWithLength { readIrDoWhile() }
                    oneOfCase = IrOperation.OperationCase.DO_WHILE
                }
                82 -> {
                    enumConstructorCall = readWithLength { readIrEnumConstructorCall() }
                    oneOfCase = IrOperation.OperationCase.ENUM_CONSTRUCTOR_CALL
                }
                90 -> {
                    functionReference = readWithLength { readIrFunctionReference() }
                    oneOfCase = IrOperation.OperationCase.FUNCTION_REFERENCE
                }
                98 -> {
                    getClass = readWithLength { readIrGetClass() }
                    oneOfCase = IrOperation.OperationCase.GET_CLASS
                }
                106 -> {
                    getEnumValue = readWithLength { readIrGetEnumValue() }
                    oneOfCase = IrOperation.OperationCase.GET_ENUM_VALUE
                }
                114 -> {
                    getField = readWithLength { readIrGetField() }
                    oneOfCase = IrOperation.OperationCase.GET_FIELD
                }
                122 -> {
                    getObject = readWithLength { readIrGetObject() }
                    oneOfCase = IrOperation.OperationCase.GET_OBJECT
                }
                130 -> {
                    getValue = readWithLength { readIrGetValue() }
                    oneOfCase = IrOperation.OperationCase.GET_VALUE
                }
                138 -> {
                    instanceInitializerCall = readWithLength { readIrInstanceInitializerCall() }
                    oneOfCase = IrOperation.OperationCase.INSTANCE_INITIALIZER_CALL
                }
                146 -> {
                    propertyReference = readWithLength { readIrPropertyReference() }
                    oneOfCase = IrOperation.OperationCase.PROPERTY_REFERENCE
                }
                154 -> {
                    return_ = readWithLength { readIrReturn() }
                    oneOfCase = IrOperation.OperationCase.RETURN
                }
                162 -> {
                    setField = readWithLength { readIrSetField() }
                    oneOfCase = IrOperation.OperationCase.SET_FIELD
                }
                170 -> {
                    setVariable = readWithLength { readIrSetVariable() }
                    oneOfCase = IrOperation.OperationCase.SET_VARIABLE
                }
                178 -> {
                    stringConcat = readWithLength { readIrStringConcat() }
                    oneOfCase = IrOperation.OperationCase.STRING_CONCAT
                }
                186 -> {
                    throw_ = readWithLength { readIrThrow() }
                    oneOfCase = IrOperation.OperationCase.THROW
                }
                194 -> {
                    try_ = readWithLength { readIrTry() }
                    oneOfCase = IrOperation.OperationCase.TRY
                }
                202 -> {
                    typeOp = readWithLength { readIrTypeOp() }
                    oneOfCase = IrOperation.OperationCase.TYPE_OP
                }
                210 -> {
                    vararg = readWithLength { readIrVararg() }
                    oneOfCase = IrOperation.OperationCase.VARARG
                }
                218 -> {
                    when_ = readWithLength { readIrWhen() }
                    oneOfCase = IrOperation.OperationCase.WHEN
                }
                226 -> {
                    while_ = readWithLength { readIrWhile() }
                    oneOfCase = IrOperation.OperationCase.WHILE
                }
                234 -> {
                    dynamicMember = readWithLength { readIrDynamicMemberExpression() }
                    oneOfCase = IrOperation.OperationCase.DYNAMIC_MEMBER
                }
                242 -> {
                    dynamicOperator = readWithLength { readIrDynamicOperatorExpression() }
                    oneOfCase = IrOperation.OperationCase.DYNAMIC_OPERATOR
                }
                250 -> {
                    localDelegatedPropertyReference = readWithLength { readIrLocalDelegatedPropertyReference() }
                    oneOfCase = IrOperation.OperationCase.LOCAL_DELEGATED_PROPERTY_REFERENCE
                }
                258 -> {
                    constructorCall = readWithLength { readIrConstructorCall() }
                    oneOfCase = IrOperation.OperationCase.CONSTRUCTOR_CALL
                }
                266 -> {
                    functionExpression = readWithLength { readIrFunctionExpression() }
                    oneOfCase = IrOperation.OperationCase.FUNCTION_EXPRESSION
                }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrOperation(oneOfCase!!, block, break_, call, classReference, composite, const, continue_, delegatingConstructorCall, doWhile, enumConstructorCall, functionReference, getClass, getEnumValue, getField, getObject, getValue, instanceInitializerCall, propertyReference, return_, setField, setVariable, stringConcat, throw_, try_, typeOp, vararg, when_, while_, dynamicMember, dynamicOperator, localDelegatedPropertyReference, constructorCall, functionExpression)
    }

    fun readIrExpression(): IrExpression {
        var operation: IrOperation? = null
        var type: Int = 0
        var coordinates: Long = 0L
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> operation = readWithLength { readIrOperation() }
                16 -> type = readInt32()
                24 -> coordinates = readInt64()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrExpression(operation!!, type, coordinates)
    }

    fun readNullableIrExpression(): NullableIrExpression {
        var expression: IrExpression? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> expression = readWithLength { readIrExpression() }
                else -> skip(fieldHeader and 7)
            }
        }
        return NullableIrExpression(expression)
    }

    fun readIrDeclarationBase(): IrDeclarationBase {
        var symbol: Long = 0L
        var originName: Int = 0
        var coordinates: Long = 0L
        var flags: Long = 0
        var annotation: MutableList<IrConstructorCall> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> symbol = readInt64()
                16 -> originName = readInt32()
                24 -> coordinates = readInt64()
                32 -> flags = readInt64()
                42 -> annotation.add(readWithLength { readIrConstructorCall() })
                else -> skip(fieldHeader and 7)
            }
        }
        return IrDeclarationBase(symbol, originName, coordinates, flags, annotation)
    }

    fun readIrFunctionBase(): IrFunctionBase {
        var base: IrDeclarationBase? = null
        var nameType: Long = 0L
        var typeParameter: MutableList<IrTypeParameter> = mutableListOf()
        var dispatchReceiver: IrValueParameter? = null
        var extensionReceiver: IrValueParameter? = null
        var valueParameter: MutableList<IrValueParameter> = mutableListOf()
        var body: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrDeclarationBase() }
                16 -> nameType = readInt64()
                26 -> typeParameter.add(readWithLength { readIrTypeParameter() })
                34 -> dispatchReceiver = readWithLength { readIrValueParameter() }
                42 -> extensionReceiver = readWithLength { readIrValueParameter() }
                50 -> valueParameter.add(readWithLength { readIrValueParameter() })
                56 -> body = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrFunctionBase(base!!, nameType, typeParameter, dispatchReceiver, extensionReceiver, valueParameter, body)
    }

    fun readIrFunction(): IrFunction {
        var base: IrFunctionBase? = null
        var overridden: MutableList<Long> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrFunctionBase() }
                18 -> readWithLength { while (hasData) overridden.add(readInt64()) } 
                else -> skip(fieldHeader and 7)
            }
        }
        return IrFunction(base!!, overridden)
    }

    fun readIrConstructor(): IrConstructor {
        var base: IrFunctionBase? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrFunctionBase() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrConstructor(base!!)
    }

    fun readIrField(): IrField {
        var base: IrDeclarationBase? = null
        var nameType: Long = 0L
        var initializer: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrDeclarationBase() }
                16 -> nameType = readInt64()
                24 -> initializer = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrField(base!!, nameType, initializer)
    }

    fun readIrLocalDelegatedProperty(): IrLocalDelegatedProperty {
        var base: IrDeclarationBase? = null
        var nameType: Long = 0L
        var delegate: IrVariable? = null
        var getter: IrFunction? = null
        var setter: IrFunction? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrDeclarationBase() }
                16 -> nameType = readInt64()
                26 -> delegate = readWithLength { readIrVariable() }
                34 -> getter = readWithLength { readIrFunction() }
                42 -> setter = readWithLength { readIrFunction() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrLocalDelegatedProperty(base!!, nameType, delegate!!, getter, setter)
    }

    fun readIrProperty(): IrProperty {
        var base: IrDeclarationBase? = null
        var name: Int = 0
        var backingField: IrField? = null
        var getter: IrFunction? = null
        var setter: IrFunction? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrDeclarationBase() }
                16 -> name = readInt32()
                26 -> backingField = readWithLength { readIrField() }
                34 -> getter = readWithLength { readIrFunction() }
                42 -> setter = readWithLength { readIrFunction() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrProperty(base!!, name, backingField, getter, setter)
    }

    fun readIrVariable(): IrVariable {
        var base: IrDeclarationBase? = null
        var nameType: Long = 0L
        var initializer: IrExpression? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrDeclarationBase() }
                16 -> nameType = readInt64()
                26 -> initializer = readWithLength { readIrExpression() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrVariable(base!!, nameType, initializer)
    }

    fun readIrValueParameter(): IrValueParameter {
        var base: IrDeclarationBase? = null
        var nameType: Long = 0L
        var varargElementType: Int? = null
        var defaultValue: Int? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrDeclarationBase() }
                16 -> nameType = readInt64()
                24 -> varargElementType = readInt32()
                32 -> defaultValue = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrValueParameter(base!!, nameType, varargElementType, defaultValue)
    }

    fun readIrTypeParameter(): IrTypeParameter {
        var base: IrDeclarationBase? = null
        var name: Int = 0
        var superType: MutableList<Int> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrDeclarationBase() }
                16 -> name = readInt32()
                26 -> readWithLength { while (hasData) superType.add(readInt32()) } 
                else -> skip(fieldHeader and 7)
            }
        }
        return IrTypeParameter(base!!, name, superType)
    }

    fun readIrClass(): IrClass {
        var base: IrDeclarationBase? = null
        var name: Int = 0
        var thisReceiver: IrValueParameter? = null
        var typeParameter: MutableList<IrTypeParameter> = mutableListOf()
        var declaration: MutableList<IrDeclaration> = mutableListOf()
        var superType: MutableList<Int> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrDeclarationBase() }
                16 -> name = readInt32()
                26 -> thisReceiver = readWithLength { readIrValueParameter() }
                34 -> typeParameter.add(readWithLength { readIrTypeParameter() })
                42 -> declaration.add(readWithLength { readIrDeclaration() })
                50 -> readWithLength { while (hasData) superType.add(readInt32()) } 
                else -> skip(fieldHeader and 7)
            }
        }
        return IrClass(base!!, name, thisReceiver, typeParameter, declaration, superType)
    }

    fun readIrTypeAlias(): IrTypeAlias {
        var base: IrDeclarationBase? = null
        var nameType: Long = 0L
        var typeParameter: MutableList<IrTypeParameter> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrDeclarationBase() }
                16 -> nameType = readInt64()
                26 -> typeParameter.add(readWithLength { readIrTypeParameter() })
                else -> skip(fieldHeader and 7)
            }
        }
        return IrTypeAlias(base!!, nameType, typeParameter)
    }

    fun readIrEnumEntry(): IrEnumEntry {
        var base: IrDeclarationBase? = null
        var name: Int = 0
        var initializer: Int? = null
        var correspondingClass: IrClass? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrDeclarationBase() }
                16 -> name = readInt32()
                24 -> initializer = readInt32()
                34 -> correspondingClass = readWithLength { readIrClass() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrEnumEntry(base!!, name, initializer, correspondingClass)
    }

    fun readIrAnonymousInit(): IrAnonymousInit {
        var base: IrDeclarationBase? = null
        var body: Int = 0
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> base = readWithLength { readIrDeclarationBase() }
                16 -> body = readInt32()
                else -> skip(fieldHeader and 7)
            }
        }
        return IrAnonymousInit(base!!, body)
    }

    fun readIrDeclaration(): IrDeclaration {
        var irAnonymousInit: IrAnonymousInit? = null
        var irClass: IrClass? = null
        var irConstructor: IrConstructor? = null
        var irEnumEntry: IrEnumEntry? = null
        var irField: IrField? = null
        var irFunction: IrFunction? = null
        var irProperty: IrProperty? = null
        var irTypeParameter: IrTypeParameter? = null
        var irVariable: IrVariable? = null
        var irValueParameter: IrValueParameter? = null
        var irLocalDelegatedProperty: IrLocalDelegatedProperty? = null
        var irTypeAlias: IrTypeAlias? = null
        var oneOfCase: IrDeclaration.DeclaratorCase = IrDeclaration.DeclaratorCase.DECLARATOR_NOT_SET
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> {
                    irAnonymousInit = readWithLength { readIrAnonymousInit() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_ANONYMOUS_INIT
                }
                18 -> {
                    irClass = readWithLength { readIrClass() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_CLASS
                }
                26 -> {
                    irConstructor = readWithLength { readIrConstructor() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_CONSTRUCTOR
                }
                34 -> {
                    irEnumEntry = readWithLength { readIrEnumEntry() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_ENUM_ENTRY
                }
                42 -> {
                    irField = readWithLength { readIrField() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_FIELD
                }
                50 -> {
                    irFunction = readWithLength { readIrFunction() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_FUNCTION
                }
                58 -> {
                    irProperty = readWithLength { readIrProperty() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_PROPERTY
                }
                66 -> {
                    irTypeParameter = readWithLength { readIrTypeParameter() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_TYPE_PARAMETER
                }
                74 -> {
                    irVariable = readWithLength { readIrVariable() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_VARIABLE
                }
                82 -> {
                    irValueParameter = readWithLength { readIrValueParameter() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_VALUE_PARAMETER
                }
                90 -> {
                    irLocalDelegatedProperty = readWithLength { readIrLocalDelegatedProperty() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_LOCAL_DELEGATED_PROPERTY
                }
                98 -> {
                    irTypeAlias = readWithLength { readIrTypeAlias() }
                    oneOfCase = IrDeclaration.DeclaratorCase.IR_TYPE_ALIAS
                }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrDeclaration(oneOfCase!!, irAnonymousInit, irClass, irConstructor, irEnumEntry, irField, irFunction, irProperty, irTypeParameter, irVariable, irValueParameter, irLocalDelegatedProperty, irTypeAlias)
    }

    fun readIrBranch(): IrBranch {
        var condition: IrExpression? = null
        var result: IrExpression? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> condition = readWithLength { readIrExpression() }
                18 -> result = readWithLength { readIrExpression() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrBranch(condition!!, result!!)
    }

    fun readIrBlockBody(): IrBlockBody {
        var statement: MutableList<IrStatement> = mutableListOf()
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> statement.add(readWithLength { readIrStatement() })
                else -> skip(fieldHeader and 7)
            }
        }
        return IrBlockBody(statement)
    }

    fun readIrCatch(): IrCatch {
        var catchParameter: IrVariable? = null
        var result: IrExpression? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                10 -> catchParameter = readWithLength { readIrVariable() }
                18 -> result = readWithLength { readIrExpression() }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrCatch(catchParameter!!, result!!)
    }

    fun readIrSyntheticBody(): IrSyntheticBody {
        var kind: IrSyntheticBodyKind? = null
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> kind = IrSyntheticBodyKind.fromIndex(readInt32())
                else -> skip(fieldHeader and 7)
            }
        }
        return IrSyntheticBody(kind!!)
    }

    fun readIrStatement(): IrStatement {
        var coordinates: Long = 0L
        var declaration: IrDeclaration? = null
        var expression: IrExpression? = null
        var blockBody: IrBlockBody? = null
        var branch: IrBranch? = null
        var catch: IrCatch? = null
        var syntheticBody: IrSyntheticBody? = null
        var oneOfCase: IrStatement.StatementCase = IrStatement.StatementCase.STATEMENT_NOT_SET
        while (hasData) {
            when (val fieldHeader = readInt32()) {
                8 -> coordinates = readInt64()
                18 -> {
                    declaration = readWithLength { readIrDeclaration() }
                    oneOfCase = IrStatement.StatementCase.DECLARATION
                }
                26 -> {
                    expression = readWithLength { readIrExpression() }
                    oneOfCase = IrStatement.StatementCase.EXPRESSION
                }
                34 -> {
                    blockBody = readWithLength { readIrBlockBody() }
                    oneOfCase = IrStatement.StatementCase.BLOCK_BODY
                }
                42 -> {
                    branch = readWithLength { readIrBranch() }
                    oneOfCase = IrStatement.StatementCase.BRANCH
                }
                50 -> {
                    catch = readWithLength { readIrCatch() }
                    oneOfCase = IrStatement.StatementCase.CATCH
                }
                58 -> {
                    syntheticBody = readWithLength { readIrSyntheticBody() }
                    oneOfCase = IrStatement.StatementCase.SYNTHETIC_BODY
                }
                else -> skip(fieldHeader and 7)
            }
        }
        return IrStatement(coordinates, oneOfCase!!, declaration, expression, blockBody, branch, catch, syntheticBody)
    }

}
