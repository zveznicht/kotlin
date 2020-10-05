/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config.data.configurator

import org.jetbrains.kotlin.config.data.CompilerArgumentsData

interface CompilerArgumentsDataConfigurator<T> {
    val compilerArgumentsData: CompilerArgumentsData
    fun configure(configuration: T)

    fun String.getFlag(): Boolean = compilerArgumentsData.getArbitraryFlag(this)
    fun String.setFlag(value: Boolean) = compilerArgumentsData.setArbitraryFlag(this, value)
    fun String.getSingle(): String? = compilerArgumentsData.getArbitrarySingleArgument(this)
    fun String.setSingle(value: String?) = compilerArgumentsData.setArbitrarySingleArgument(this, value)
    fun String.getMultiple(): Array<String>? = compilerArgumentsData.getArbitraryMultipleArguments(this)
    fun String.setMultiple(value: Array<String>?) = compilerArgumentsData.setArbitraryMultipleArguments(this, value)
}