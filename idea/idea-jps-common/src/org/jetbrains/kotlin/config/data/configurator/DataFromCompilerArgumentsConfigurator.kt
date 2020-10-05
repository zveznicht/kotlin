/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config.data.configurator

import org.jetbrains.kotlin.cli.common.arguments.Argument
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2MetadataCompilerArguments
import org.jetbrains.kotlin.config.data.CompilerArgumentsData
import org.jetbrains.kotlin.konan.file.File
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class DataFromCompilerArgumentsConfigurator(override val compilerArgumentsData: CompilerArgumentsData) :
    CompilerArgumentsDataConfigurator<CommonCompilerArguments> {

    private fun copyWithReflection(compilerArguments: CommonCompilerArguments) {
        @Suppress("UNCHECKED_CAST")
        for (prop in compilerArguments::class.memberProperties) {
            val argument = prop.annotations.firstOrNull { it is Argument } as Argument?
            val argumentId = argument?.value ?: continue
            when {
                prop.returnType.classifier == Boolean::class ->
                    (prop as? KProperty1<in CommonCompilerArguments, Boolean>)?.get(compilerArguments)?.let {
                        compilerArgumentsData.setArbitraryFlag(argumentId, it)
                    }
                prop.returnType.classifier == String::class ->
                    (prop as? KProperty1<in CommonCompilerArguments, String?>)?.get(compilerArguments).let {
                        compilerArgumentsData.setArbitrarySingleArgument(argumentId, it)
                    }
                (prop.returnType.classifier as? KClass<*>)?.java?.isArray == true
                -> (prop as? KProperty1<in CommonCompilerArguments, Array<String>?>)?.get(compilerArguments).let {
                    compilerArgumentsData.setArbitraryMultipleArguments(argumentId, it)
                }
            }
        }
    }

    override fun configure(configuration: CommonCompilerArguments) {

        //TODO replace with `copyWithReflection`
        with(compilerArgumentsData) {
            languageVersion = configuration.languageVersion
            apiVersion = configuration.apiVersion
            pluginOptions = configuration.pluginOptions
            pluginClasspaths = configuration.pluginClasspaths
            classpathParts = (configuration as? K2JVMCompilerArguments)?.classpath?.split(File.pathSeparator)?.toTypedArray()
                ?: (configuration as? K2MetadataCompilerArguments)?.classpath?.split(File.pathSeparator)?.toTypedArray()
            jvmTarget = (configuration as? K2JVMCompilerArguments)?.jvmTarget
            coroutinesState = configuration.coroutinesState
            jdkHome = (configuration as? K2JVMCompilerArguments)?.jdkHome
        }
    }
}