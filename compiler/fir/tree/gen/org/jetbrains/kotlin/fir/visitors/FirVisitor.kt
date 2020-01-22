/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.visitors

import org.jetbrains.kotlin.fir.*
import org.jetbrains.kotlin.fir.contracts.FirContractDescription
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder
import org.jetbrains.kotlin.fir.expressions.*
import org.jetbrains.kotlin.fir.references.*
import org.jetbrains.kotlin.fir.types.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

abstract class FirVisitor<out R, in D> {
    abstract fun visitElement(element: FirElement, data: D): R

    open fun visitAnnotationContainer(annotationContainer: FirAnnotationContainer, data: D): R  = visitElement(annotationContainer, data)

    open fun visitTypeRef(typeRef: FirTypeRef, data: D): R  = visitElement(typeRef, data)

    open fun visitReference(reference: FirReference, data: D): R  = visitElement(reference, data)

    open fun visitLabel(label: FirLabel, data: D): R  = visitElement(label, data)

    open fun visitImport(import: FirImport, data: D): R  = visitElement(import, data)

    open fun visitResolvedImport(resolvedImport: FirResolvedImport, data: D): R  = visitElement(resolvedImport, data)

    open fun <E> visitSymbolOwner(symbolOwner: FirSymbolOwner<E>, data: D): R where E : FirSymbolOwner<E>, E : FirDeclaration  = visitElement(symbolOwner, data)

    open fun visitResolvable(resolvable: FirResolvable, data: D): R  = visitElement(resolvable, data)

    open fun visitControlFlowGraphOwner(controlFlowGraphOwner: FirControlFlowGraphOwner, data: D): R  = visitElement(controlFlowGraphOwner, data)

    open fun visitTargetElement(targetElement: FirTargetElement, data: D): R  = visitElement(targetElement, data)

    open fun visitDeclarationStatus(declarationStatus: FirDeclarationStatus, data: D): R  = visitElement(declarationStatus, data)

    open fun visitResolvedDeclarationStatus(resolvedDeclarationStatus: FirResolvedDeclarationStatus, data: D): R  = visitElement(resolvedDeclarationStatus, data)

    open fun visitStatement(statement: FirStatement, data: D): R  = visitElement(statement, data)

    open fun visitExpression(expression: FirExpression, data: D): R  = visitElement(expression, data)

    open fun visitDeclaration(declaration: FirDeclaration, data: D): R  = visitElement(declaration, data)

    open fun visitAnonymousInitializer(anonymousInitializer: FirAnonymousInitializer, data: D): R  = visitElement(anonymousInitializer, data)

    open fun visitTypedDeclaration(typedDeclaration: FirTypedDeclaration, data: D): R  = visitElement(typedDeclaration, data)

    open fun <F : FirCallableDeclaration<F>> visitCallableDeclaration(callableDeclaration: FirCallableDeclaration<F>, data: D): R  = visitElement(callableDeclaration, data)

    open fun visitNamedDeclaration(namedDeclaration: FirNamedDeclaration, data: D): R  = visitElement(namedDeclaration, data)

    open fun visitTypeParameter(typeParameter: FirTypeParameter, data: D): R  = visitElement(typeParameter, data)

    open fun visitTypeParametersOwner(typeParametersOwner: FirTypeParametersOwner, data: D): R  = visitElement(typeParametersOwner, data)

    open fun visitMemberDeclaration(memberDeclaration: FirMemberDeclaration, data: D): R  = visitElement(memberDeclaration, data)

    open fun <F : FirCallableMemberDeclaration<F>> visitCallableMemberDeclaration(callableMemberDeclaration: FirCallableMemberDeclaration<F>, data: D): R  = visitElement(callableMemberDeclaration, data)

    open fun <F : FirVariable<F>> visitVariable(variable: FirVariable<F>, data: D): R  = visitElement(variable, data)

    open fun visitValueParameter(valueParameter: FirValueParameter, data: D): R  = visitElement(valueParameter, data)

    open fun visitProperty(property: FirProperty, data: D): R  = visitElement(property, data)

    open fun visitField(field: FirField, data: D): R  = visitElement(field, data)

    open fun <F : FirClassLikeDeclaration<F>> visitClassLikeDeclaration(classLikeDeclaration: FirClassLikeDeclaration<F>, data: D): R  = visitElement(classLikeDeclaration, data)

    open fun <F : FirClass<F>> visitClass(klass: FirClass<F>, data: D): R  = visitElement(klass, data)

    open fun visitRegularClass(regularClass: FirRegularClass, data: D): R  = visitElement(regularClass, data)

    open fun visitSealedClass(sealedClass: FirSealedClass, data: D): R  = visitElement(sealedClass, data)

    open fun visitTypeAlias(typeAlias: FirTypeAlias, data: D): R  = visitElement(typeAlias, data)

    open fun visitEnumEntry(enumEntry: FirEnumEntry, data: D): R  = visitElement(enumEntry, data)

    open fun <F : FirFunction<F>> visitFunction(function: FirFunction<F>, data: D): R  = visitElement(function, data)

    open fun visitContractDescriptionOwner(contractDescriptionOwner: FirContractDescriptionOwner, data: D): R  = visitElement(contractDescriptionOwner, data)

    open fun <F : FirMemberFunction<F>> visitMemberFunction(memberFunction: FirMemberFunction<F>, data: D): R  = visitElement(memberFunction, data)

    open fun visitSimpleFunction(simpleFunction: FirSimpleFunction, data: D): R  = visitElement(simpleFunction, data)

    open fun visitPropertyAccessor(propertyAccessor: FirPropertyAccessor, data: D): R  = visitElement(propertyAccessor, data)

    open fun visitConstructor(constructor: FirConstructor, data: D): R  = visitElement(constructor, data)

    open fun visitFile(file: FirFile, data: D): R  = visitElement(file, data)

    open fun visitAnonymousFunction(anonymousFunction: FirAnonymousFunction, data: D): R  = visitElement(anonymousFunction, data)

    open fun visitAnonymousObject(anonymousObject: FirAnonymousObject, data: D): R  = visitElement(anonymousObject, data)

    open fun visitDiagnosticHolder(diagnosticHolder: FirDiagnosticHolder, data: D): R  = visitElement(diagnosticHolder, data)

    open fun visitLoop(loop: FirLoop, data: D): R  = visitElement(loop, data)

    open fun visitErrorLoop(errorLoop: FirErrorLoop, data: D): R  = visitElement(errorLoop, data)

    open fun visitDoWhileLoop(doWhileLoop: FirDoWhileLoop, data: D): R  = visitElement(doWhileLoop, data)

    open fun visitWhileLoop(whileLoop: FirWhileLoop, data: D): R  = visitElement(whileLoop, data)

    open fun visitBlock(block: FirBlock, data: D): R  = visitElement(block, data)

    open fun visitBinaryLogicExpression(binaryLogicExpression: FirBinaryLogicExpression, data: D): R  = visitElement(binaryLogicExpression, data)

    open fun <E : FirTargetElement> visitJump(jump: FirJump<E>, data: D): R  = visitElement(jump, data)

    open fun visitLoopJump(loopJump: FirLoopJump, data: D): R  = visitElement(loopJump, data)

    open fun visitBreakExpression(breakExpression: FirBreakExpression, data: D): R  = visitElement(breakExpression, data)

    open fun visitContinueExpression(continueExpression: FirContinueExpression, data: D): R  = visitElement(continueExpression, data)

    open fun visitCatch(catch: FirCatch, data: D): R  = visitElement(catch, data)

    open fun visitTryExpression(tryExpression: FirTryExpression, data: D): R  = visitElement(tryExpression, data)

    open fun <T> visitConstExpression(constExpression: FirConstExpression<T>, data: D): R  = visitElement(constExpression, data)

    open fun visitTypeProjection(typeProjection: FirTypeProjection, data: D): R  = visitElement(typeProjection, data)

    open fun visitStarProjection(starProjection: FirStarProjection, data: D): R  = visitElement(starProjection, data)

    open fun visitTypeProjectionWithVariance(typeProjectionWithVariance: FirTypeProjectionWithVariance, data: D): R  = visitElement(typeProjectionWithVariance, data)

    open fun visitCall(call: FirCall, data: D): R  = visitElement(call, data)

    open fun visitAnnotationCall(annotationCall: FirAnnotationCall, data: D): R  = visitElement(annotationCall, data)

    open fun visitOperatorCall(operatorCall: FirOperatorCall, data: D): R  = visitElement(operatorCall, data)

    open fun visitTypeOperatorCall(typeOperatorCall: FirTypeOperatorCall, data: D): R  = visitElement(typeOperatorCall, data)

    open fun visitWhenExpression(whenExpression: FirWhenExpression, data: D): R  = visitElement(whenExpression, data)

    open fun visitWhenBranch(whenBranch: FirWhenBranch, data: D): R  = visitElement(whenBranch, data)

    open fun visitQualifiedAccessWithoutCallee(qualifiedAccessWithoutCallee: FirQualifiedAccessWithoutCallee, data: D): R  = visitElement(qualifiedAccessWithoutCallee, data)

    open fun visitQualifiedAccess(qualifiedAccess: FirQualifiedAccess, data: D): R  = visitElement(qualifiedAccess, data)

    open fun visitCheckNotNullCall(checkNotNullCall: FirCheckNotNullCall, data: D): R  = visitElement(checkNotNullCall, data)

    open fun visitArrayOfCall(arrayOfCall: FirArrayOfCall, data: D): R  = visitElement(arrayOfCall, data)

    open fun visitArraySetCall(arraySetCall: FirArraySetCall, data: D): R  = visitElement(arraySetCall, data)

    open fun visitClassReferenceExpression(classReferenceExpression: FirClassReferenceExpression, data: D): R  = visitElement(classReferenceExpression, data)

    open fun visitErrorExpression(errorExpression: FirErrorExpression, data: D): R  = visitElement(errorExpression, data)

    open fun visitErrorFunction(errorFunction: FirErrorFunction, data: D): R  = visitElement(errorFunction, data)

    open fun visitQualifiedAccessExpression(qualifiedAccessExpression: FirQualifiedAccessExpression, data: D): R  = visitElement(qualifiedAccessExpression, data)

    open fun visitFunctionCall(functionCall: FirFunctionCall, data: D): R  = visitElement(functionCall, data)

    open fun visitDelegatedConstructorCall(delegatedConstructorCall: FirDelegatedConstructorCall, data: D): R  = visitElement(delegatedConstructorCall, data)

    open fun visitComponentCall(componentCall: FirComponentCall, data: D): R  = visitElement(componentCall, data)

    open fun visitCallableReferenceAccess(callableReferenceAccess: FirCallableReferenceAccess, data: D): R  = visitElement(callableReferenceAccess, data)

    open fun visitThisReceiverExpression(thisReceiverExpression: FirThisReceiverExpression, data: D): R  = visitElement(thisReceiverExpression, data)

    open fun visitExpressionWithSmartcast(expressionWithSmartcast: FirExpressionWithSmartcast, data: D): R  = visitElement(expressionWithSmartcast, data)

    open fun visitGetClassCall(getClassCall: FirGetClassCall, data: D): R  = visitElement(getClassCall, data)

    open fun visitWrappedExpression(wrappedExpression: FirWrappedExpression, data: D): R  = visitElement(wrappedExpression, data)

    open fun visitWrappedArgumentExpression(wrappedArgumentExpression: FirWrappedArgumentExpression, data: D): R  = visitElement(wrappedArgumentExpression, data)

    open fun visitLambdaArgumentExpression(lambdaArgumentExpression: FirLambdaArgumentExpression, data: D): R  = visitElement(lambdaArgumentExpression, data)

    open fun visitSpreadArgumentExpression(spreadArgumentExpression: FirSpreadArgumentExpression, data: D): R  = visitElement(spreadArgumentExpression, data)

    open fun visitNamedArgumentExpression(namedArgumentExpression: FirNamedArgumentExpression, data: D): R  = visitElement(namedArgumentExpression, data)

    open fun visitResolvedQualifier(resolvedQualifier: FirResolvedQualifier, data: D): R  = visitElement(resolvedQualifier, data)

    open fun visitReturnExpression(returnExpression: FirReturnExpression, data: D): R  = visitElement(returnExpression, data)

    open fun visitStringConcatenationCall(stringConcatenationCall: FirStringConcatenationCall, data: D): R  = visitElement(stringConcatenationCall, data)

    open fun visitThrowExpression(throwExpression: FirThrowExpression, data: D): R  = visitElement(throwExpression, data)

    open fun visitVariableAssignment(variableAssignment: FirVariableAssignment, data: D): R  = visitElement(variableAssignment, data)

    open fun visitWhenSubjectExpression(whenSubjectExpression: FirWhenSubjectExpression, data: D): R  = visitElement(whenSubjectExpression, data)

    open fun visitWrappedDelegateExpression(wrappedDelegateExpression: FirWrappedDelegateExpression, data: D): R  = visitElement(wrappedDelegateExpression, data)

    open fun visitNamedReference(namedReference: FirNamedReference, data: D): R  = visitElement(namedReference, data)

    open fun visitErrorNamedReference(errorNamedReference: FirErrorNamedReference, data: D): R  = visitElement(errorNamedReference, data)

    open fun visitSuperReference(superReference: FirSuperReference, data: D): R  = visitElement(superReference, data)

    open fun visitThisReference(thisReference: FirThisReference, data: D): R  = visitElement(thisReference, data)

    open fun visitControlFlowGraphReference(controlFlowGraphReference: FirControlFlowGraphReference, data: D): R  = visitElement(controlFlowGraphReference, data)

    open fun visitResolvedNamedReference(resolvedNamedReference: FirResolvedNamedReference, data: D): R  = visitElement(resolvedNamedReference, data)

    open fun visitDelegateFieldReference(delegateFieldReference: FirDelegateFieldReference, data: D): R  = visitElement(delegateFieldReference, data)

    open fun visitBackingFieldReference(backingFieldReference: FirBackingFieldReference, data: D): R  = visitElement(backingFieldReference, data)

    open fun visitResolvedCallableReference(resolvedCallableReference: FirResolvedCallableReference, data: D): R  = visitElement(resolvedCallableReference, data)

    open fun visitResolvedTypeRef(resolvedTypeRef: FirResolvedTypeRef, data: D): R  = visitElement(resolvedTypeRef, data)

    open fun visitErrorTypeRef(errorTypeRef: FirErrorTypeRef, data: D): R  = visitElement(errorTypeRef, data)

    open fun visitDelegatedTypeRef(delegatedTypeRef: FirDelegatedTypeRef, data: D): R  = visitElement(delegatedTypeRef, data)

    open fun visitTypeRefWithNullability(typeRefWithNullability: FirTypeRefWithNullability, data: D): R  = visitElement(typeRefWithNullability, data)

    open fun visitUserTypeRef(userTypeRef: FirUserTypeRef, data: D): R  = visitElement(userTypeRef, data)

    open fun visitDynamicTypeRef(dynamicTypeRef: FirDynamicTypeRef, data: D): R  = visitElement(dynamicTypeRef, data)

    open fun visitFunctionTypeRef(functionTypeRef: FirFunctionTypeRef, data: D): R  = visitElement(functionTypeRef, data)

    open fun visitResolvedFunctionTypeRef(resolvedFunctionTypeRef: FirResolvedFunctionTypeRef, data: D): R  = visitElement(resolvedFunctionTypeRef, data)

    open fun visitImplicitTypeRef(implicitTypeRef: FirImplicitTypeRef, data: D): R  = visitElement(implicitTypeRef, data)

    open fun visitContractDescription(contractDescription: FirContractDescription, data: D): R  = visitElement(contractDescription, data)

}
