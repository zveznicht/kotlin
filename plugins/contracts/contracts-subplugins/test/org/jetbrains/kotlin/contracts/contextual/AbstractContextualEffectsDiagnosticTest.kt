/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.contracts.contextual

import org.jetbrains.kotlin.checkers.AbstractDiagnosticsTest
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.contracts.contextual.dslmarker.DslMarkerContract
import org.jetbrains.kotlin.contracts.contextual.exceptions.ExceptionContract
import org.jetbrains.kotlin.contracts.contextual.extensions.SpecificContractExtension
import org.jetbrains.kotlin.contracts.contextual.safebuilders.CallContract
import org.jetbrains.kotlin.contracts.contextual.transactions.TransactionContract
import org.jetbrains.kotlin.extensions.ContractsExtension
import java.io.File

abstract class AbstractContextualEffectsDiagnosticTest : AbstractDiagnosticsTest() {
    override fun createEnvironment(file: File): KotlinCoreEnvironment {
        val environment = super.createEnvironment(file)
        val project = environment.project

        ContractsExtension.registerExtensionPoint(project)
        ContractsExtension.registerExtension(project, ContractsImplementationExtension())

        SpecificContractExtension.registerExtensionPoint(project)
        SpecificContractExtension.registerExtension(project, ExceptionContract())
        SpecificContractExtension.registerExtension(project, CallContract())
        SpecificContractExtension.registerExtension(project, TransactionContract())
        SpecificContractExtension.registerExtension(project, DslMarkerContract())

        return environment
    }

    override fun getExtraClasspath(): MutableList<File> {
        return mutableListOf(
            File("plugins/contracts/contracts-plugin/dsl/build/libs/kotlin-contracts-dsl-1.3-SNAPSHOT.jar"),
            File("plugins/contracts/contracts-subplugins/dsl/build/libs/kotlin-contracts-subplugins-dsl-1.3-SNAPSHOT.jar")
        )
    }
}