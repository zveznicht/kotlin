/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.contracts.contextual.resolution

import org.jetbrains.kotlin.contracts.contextual.model.CleanerDeclaration
import org.jetbrains.kotlin.contracts.contextual.model.ProviderDeclaration
import org.jetbrains.kotlin.contracts.contextual.model.VerifierDeclaration
import org.jetbrains.kotlin.contracts.description.ExtensionEffectDeclaration
import org.jetbrains.kotlin.contracts.description.expressions.ContractDescriptionValue
import org.jetbrains.kotlin.contracts.description.expressions.FunctionReference
import org.jetbrains.kotlin.contracts.description.expressions.VariableReference

data class ContextProviderEffectDeclaration(
    val factory: ProviderDeclaration,
    val references: List<ContractDescriptionValue>,
    val owner: FunctionReference
) : ExtensionEffectDeclaration

data class LambdaContextProviderEffectDeclaration(
    val factory: ProviderDeclaration,
    val references: List<ContractDescriptionValue>,
    val owner: VariableReference
) : ExtensionEffectDeclaration

data class ContextVerifierEffectDeclaration(
    val factory: VerifierDeclaration,
    val references: List<ContractDescriptionValue>,
    val owner: FunctionReference
) : ExtensionEffectDeclaration

data class LambdaContextVerifierEffectDeclaration(
    val factory: VerifierDeclaration,
    val references: List<ContractDescriptionValue>,
    val owner: VariableReference
) : ExtensionEffectDeclaration

data class ContextCleanerEffectDeclaration(
    val factory: CleanerDeclaration,
    val references: List<ContractDescriptionValue>,
    val owner: FunctionReference
) : ExtensionEffectDeclaration

data class LambdaContextCleanerEffectDeclaration(
    val factory: CleanerDeclaration,
    val references: List<ContractDescriptionValue>,
    val owner: VariableReference
) : ExtensionEffectDeclaration