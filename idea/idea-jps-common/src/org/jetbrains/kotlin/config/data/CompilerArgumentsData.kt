/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config.data

import org.jetbrains.kotlin.arguments.FlatArgsInfoImpl
import org.jetbrains.kotlin.arguments.FlatCompilerArgumentsBucket
import org.jetbrains.kotlin.konan.file.File

interface CompilerArgumentsData {
    var languageVersion: String?
    var apiVersion: String?
    var pluginOptions: Array<String>?
    var pluginClasspaths: Array<String>?
    var classpathParts: Array<String>?
    var classpath: String?
        get() = classpathParts?.joinToString(File.pathSeparator)
        set(value) {
            classpathParts = value?.split(File.pathSeparator)?.toTypedArray()
        }
    var jvmTarget: String?
    var coroutinesState: String?
    var jdkHome: String?
    var autoAdvanceLanguageVersion: Boolean
    var autoAdvanceApiVersion: Boolean

    fun getArbitrarySingleArgument(argumentId: String): String?
    fun setArbitrarySingleArgument(argumentId: String, newValue: String?)
    fun getArbitraryFlag(argumentId: String): Boolean
    fun setArbitraryFlag(argumentId: String, newValue: Boolean)
    fun getArbitraryMultipleArguments(argumentId: String): Array<String>?
    fun setArbitraryMultipleArguments(argumentId: String, newValue: Array<String>?)

    companion object {
        val dummyImpl: CompilerArgumentsData
            get() = FlatArgsCompilerArgumentsDataFacade(
                FlatArgsInfoImpl(
                    FlatCompilerArgumentsBucket(ArrayList(), arrayOf(), arrayOf(), arrayOf()),
                    FlatCompilerArgumentsBucket(ArrayList(), arrayOf(), arrayOf(), arrayOf()),
                    arrayOf()
                )
            )
    }
}