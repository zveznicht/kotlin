/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config.data

import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import java.io.File

interface CompilerArgumentsProducible {
    val compilerArguments: CommonCompilerArguments
}

interface CompilerArgumentsData {
    var languageVersion: String?
    var apiVersion: String?
    var pluginOptions: Array<String>?
    var pluginClasspaths: Array<String>?
    var classpathParts: Array<String>?
    var jvmTarget: String?
    var coroutinesState: String?
    var autoAdvanceLanguageVersion: Boolean
    var autoAdvanceApiVersion: Boolean
}

interface CompilerArgumentsDataConfigurator<T> {
    fun configureBy(compilerArgumentsData: CompilerArgumentsData, configuration: T)
}

class DataFromCompilerArgumentsConfigurator<T : CommonCompilerArguments> : CompilerArgumentsDataConfigurator<T> {
    override fun configureBy(compilerArgumentsData: CompilerArgumentsData, configuration: T) =
        with(compilerArgumentsData) {
            languageVersion = configuration.languageVersion
            apiVersion = configuration.apiVersion
            pluginOptions = configuration.pluginOptions
            pluginClasspaths = configuration.pluginClasspaths
            classpathParts = (configuration as? K2JVMCompilerArguments)?.classpath?.split(File.pathSeparator)?.toTypedArray()
            jvmTarget = (configuration as? K2JVMCompilerArguments)?.jvmTarget
            coroutinesState = configuration.coroutinesState
            autoAdvanceLanguageVersion = configuration.autoAdvanceLanguageVersion
            autoAdvanceApiVersion = configuration.autoAdvanceApiVersion
        }
}