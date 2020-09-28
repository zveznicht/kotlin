/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config.data

import com.intellij.openapi.util.io.FileUtil
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.parseCommandLineArguments
import org.jetbrains.kotlin.config.createArguments
import org.jetbrains.kotlin.gradle.FlatArgsInfo
import org.jetbrains.kotlin.gradle.FlatToRawCompilerArgumentsBucketConverter
import org.jetbrains.kotlin.konan.file.File
import org.jetbrains.kotlin.platform.TargetPlatform
import java.io.Serializable
import kotlin.reflect.KProperty

class FlatArgsCompilerArgumentsDataFacade(val flatArgsInfo: FlatArgsInfo) : CompilerArgumentsData {
    private val changeSingleStrategy = ChangeSingleFlatArgumentStrategy(flatArgsInfo.currentCompilerArgumentsBucket)
    private val changePluginOptionStrategy = ChangeFlatPluginOptionsStrategy(flatArgsInfo.currentCompilerArgumentsBucket)
    private val changePluginClasspathsStrategy = ChangeFlatPluginClasspathsStrategy(flatArgsInfo.currentCompilerArgumentsBucket)
    private val changeClasspathPartsStrategy = ChangeFlatClasspathPartsStrategy(flatArgsInfo.currentCompilerArgumentsBucket)

    override var languageVersion: String? by ChangingBySingeFlatArgumentStrategy(LANGUAGE_VERSION, changeSingleStrategy)
    override var apiVersion: String? by ChangingBySingeFlatArgumentStrategy(API_VERSION, changeSingleStrategy)
    override var jvmTarget: String? by ChangingBySingeFlatArgumentStrategy(JVM_TARGET, changeSingleStrategy)
    override var coroutinesState: String? by ChangingBySingeFlatArgumentStrategy(COROUTINES_STATE, changeSingleStrategy)
    override var pluginOptions: Array<String>? by ChangingByFlatArgumentsArrayStrategy(changePluginOptionStrategy)
    override var pluginClasspaths: Array<String>? by ChangingByFlatArgumentsArrayStrategy(changePluginClasspathsStrategy)
    override var classpathParts: Array<String>? by ChangingByFlatArgumentsArrayStrategy(changeClasspathPartsStrategy)


    override var autoAdvanceLanguageVersion: Boolean = false
    override var autoAdvanceApiVersion: Boolean = false

    companion object {
        private const val LANGUAGE_VERSION = "-language-version"
        private const val API_VERSION = "-language-version"
        private const val JVM_TARGET = "-jvm-target"
        private const val COROUTINES_STATE = "-Xcoroutines"
        private const val IGNORED = ""


        private interface ChangingByStrategy<T, R : ChangeFlatArgumentStrategy<T>> : Serializable {
            val argumentsId: String
            var value: T
            val strategy: R

            operator fun getValue(thisRef: Any, property: KProperty<*>) = value
            operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
                this.value = value
                strategy.execute(argumentsId, value)
            }
        }

        private class ChangingBySingeFlatArgumentStrategy(
            override val argumentsId: String,
            override val strategy: ChangeSingleFlatArgumentStrategy
        ) : ChangingByStrategy<String?, ChangeSingleFlatArgumentStrategy> {
            override var value: String? = null
        }

        private class ChangingByFlatArgumentsArrayStrategy(
            override val strategy: ChangeMultipleFlatArgumentStrategy
        ) : ChangingByStrategy<Array<String>?, ChangeMultipleFlatArgumentStrategy> {
            override var value: Array<String>? = null
            override val argumentsId: String = IGNORED
        }
    }

}
