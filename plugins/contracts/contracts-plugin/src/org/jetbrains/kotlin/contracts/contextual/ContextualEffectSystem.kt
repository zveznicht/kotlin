/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.contracts.contextual

import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.contracts.contextual.extensions.SpecificContractExtension
import org.jetbrains.kotlin.contracts.contextual.model.ContextFamily
import org.jetbrains.kotlin.contracts.contextual.parsing.PsiEffectDeclarationExtractor
import org.jetbrains.kotlin.contracts.parsing.PsiContractVariableParserDispatcher
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.BindingContext

object ContextualEffectSystem {
    fun getFamilies(project: Project): Collection<ContextFamily> = SpecificContractExtension.getInstances(project).map { it.getFamily() }

    fun getParsers(
        project: Project,
        bindingContext: BindingContext,
        dispatcher: PsiContractVariableParserDispatcher
    ): Collection<PsiEffectDeclarationExtractor> =
        SpecificContractExtension.getInstances(project).map { it.getParser(bindingContext, dispatcher) }
}

fun KtExpression.declaredFactsInfo(bindingContext: BindingContext): FactsBindingInfo =
    bindingContext[BindingContext.EXTENSION_SLICE, this]
        ?.get(ContractsCommandLineProcessor.PLUGIN_ID) as? FactsBindingInfo
        ?: FactsBindingInfo.EMPTY