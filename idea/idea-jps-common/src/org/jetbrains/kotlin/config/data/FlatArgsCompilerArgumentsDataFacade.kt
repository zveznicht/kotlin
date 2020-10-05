/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config.data

import org.jetbrains.kotlin.arguments.FlatArgsInfo
import org.jetbrains.kotlin.arguments.FlatArgsInfoImpl
import org.jetbrains.kotlin.arguments.FlatCompilerArgumentsBucket
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments

class FlatArgsCompilerArgumentsDataFacade(val flatArgsInfo: FlatArgsInfo) : CompilerArgumentsData {
    private val changeSingleStrategy = ChangeSingleFlatArgumentStrategy(flatArgsInfo.currentCompilerArgumentsBucket)
    private val changeFlagStrategy = ChangeBooleanFlatArgumentStrategy(flatArgsInfo.currentCompilerArgumentsBucket)
    private val changeMultipleStrategy = ChangeMultipleFlatArgumentStrategyImpl(flatArgsInfo.currentCompilerArgumentsBucket)
    private val changePluginOptionStrategy = ChangeFlatPluginOptionsStrategy(flatArgsInfo.currentCompilerArgumentsBucket)
    private val changePluginClasspathsStrategy = ChangeFlatPluginClasspathsStrategy(flatArgsInfo.currentCompilerArgumentsBucket)
    private val changeClasspathPartsStrategy = ChangeFlatClasspathPartsStrategy(flatArgsInfo.currentCompilerArgumentsBucket)

    private val singleArgumentsMap = mutableMapOf<String, String?>().apply {
        val generalArguments = flatArgsInfo.currentCompilerArgumentsBucket.generalArguments
    }
    private val flagArgumentsMap = mutableMapOf<String, Boolean>().apply {
        val generalArguments = flatArgsInfo.currentCompilerArgumentsBucket.generalArguments
    }
    private val multipleArgumentsMap = mutableMapOf<String, Array<String>?>().apply {
        val generalArguments = flatArgsInfo.currentCompilerArgumentsBucket.generalArguments
    }

    override var languageVersion: String? by ChangingBySingeFlatArgumentStrategy(LANGUAGE_VERSION, changeSingleStrategy)
    override var apiVersion: String? by ChangingBySingeFlatArgumentStrategy(API_VERSION, changeSingleStrategy)
    override var jvmTarget: String? by ChangingBySingeFlatArgumentStrategy(JVM_TARGET, changeSingleStrategy)
    override var coroutinesState: String? by ChangingBySingeFlatArgumentStrategy(COROUTINES_STATE, changeSingleStrategy)
    override var pluginOptions: Array<String>? by ChangingByFlatPluginOptionsStrategy(changePluginOptionStrategy)
    override var pluginClasspaths: Array<String>? by ChangingByFlatArgumentsArrayStrategy(changePluginClasspathsStrategy)
    override var classpathParts: Array<String>? by ChangingByFlatArgumentsArrayStrategy(changeClasspathPartsStrategy)
    override var jdkHome: String? by ChangingBySingeFlatArgumentStrategy(JDK_HOME, changeSingleStrategy)


    override var autoAdvanceLanguageVersion: Boolean = false
    override var autoAdvanceApiVersion: Boolean = false
    override fun getArbitrarySingleArgument(argumentId: String): String? = changeSingleStrategy.getArgumentStrategy(argumentId)

    override fun setArbitrarySingleArgument(argumentId: String, newValue: String?) =
        changeSingleStrategy.setArgumentStrategy(argumentId, newValue)

    override fun getArbitraryFlag(argumentId: String): Boolean = changeFlagStrategy.getArgumentStrategy(argumentId)

    override fun setArbitraryFlag(argumentId: String, newValue: Boolean) = changeFlagStrategy.setArgumentStrategy(argumentId, newValue)

    override fun getArbitraryMultipleArguments(argumentId: String): Array<String>? =
        changeMultipleStrategy.getArgumentStrategy(argumentId)

    override fun setArbitraryMultipleArguments(argumentId: String, newValue: Array<String>?) =
        changeMultipleStrategy.setArgumentStrategy(argumentId, newValue)

    companion object {
        private const val LANGUAGE_VERSION = "-language-version"
        private const val API_VERSION = "-language-version"
        private const val JVM_TARGET = "-jvm-target"
        private const val COROUTINES_STATE = "-Xcoroutines"
        private const val JDK_HOME = "-jdk-home"
    }
}