/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlinx.serialization.idea

import org.jetbrains.kotlin.config.data.CompilerArgumentsData
import org.jetbrains.kotlin.config.data.configurator.DataFromCompilerArgumentsConfigurator
import org.jetbrains.kotlin.idea.compiler.configuration.KotlinCommonCompilerArgumentsHolder
import org.jetbrains.kotlin.idea.facet.KotlinFacet
import org.jetbrains.kotlin.utils.PathUtil
import java.io.File

internal object KotlinSerializationImportHandler {
    private const val pluginJpsJarName = "kotlinx-serialization-compiler-plugin.jar"

    val PLUGIN_JPS_JAR: String
        get() = File(PathUtil.kotlinPathsForIdeaPlugin.libPath, pluginJpsJarName).absolutePath

    fun isPluginJarPath(path: String): Boolean {
        return path.endsWith(pluginJpsJarName)
    }

    fun modifyCompilerArguments(facet: KotlinFacet, buildSystemPluginJar: String) {
        val facetSettings = facet.configuration.settings
        //TODO replace `return` with DataFromDummyImpl
        val compilerArgumentsData = facetSettings.compilerArgumentsData ?: CompilerArgumentsData.dummyImpl.apply {
            val commonArguments = KotlinCommonCompilerArgumentsHolder.getInstance(facet.module.project).settings
            DataFromCompilerArgumentsConfigurator(this).configure(commonArguments)
            facetSettings.compilerArgumentsData = this
        }

        var pluginWasEnabled = false
        val oldPluginClasspaths = (compilerArgumentsData.pluginClasspaths ?: emptyArray()).filterTo(mutableListOf()) {
            val lastIndexOfFile = it.lastIndexOfAny(charArrayOf('/', File.separatorChar))
            if (lastIndexOfFile < 0) {
                return@filterTo true
            }
            val match = it.drop(lastIndexOfFile + 1).matches("$buildSystemPluginJar-.*\\.jar".toRegex())
            if (match) pluginWasEnabled = true
            !match
        }

        val newPluginClasspaths = if (pluginWasEnabled) oldPluginClasspaths + PLUGIN_JPS_JAR else oldPluginClasspaths
        compilerArgumentsData.pluginClasspaths = newPluginClasspaths.toTypedArray()
    }
}