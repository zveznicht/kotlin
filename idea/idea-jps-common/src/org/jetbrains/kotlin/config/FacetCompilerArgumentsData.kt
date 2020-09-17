/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config

import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.mergeBeans
import org.jetbrains.kotlin.cli.common.arguments.parseCommandLineArguments
import org.jetbrains.kotlin.gradle.CachedArgsInfo
import org.jetbrains.kotlin.gradle.CompilerArgumentsMapper
import org.jetbrains.kotlin.platform.IdePlatformKind
import org.jetbrains.kotlin.platform.TargetPlatform

interface FacetCompilerArgumentsData {
    var languageVersion: String?
    var apiVersion: String?
    var pluginOptions: Array<String>?
    var autoAdvanceLanguageVersion: Boolean
    var autoAdvanceApiVersion: Boolean
    var coroutinesState: LanguageFeature.State?
    var pluginClasspaths: Array<String>?
    var jvmTarget: String?
    var componentPlatforms: String?
    var targetPlatform: TargetPlatform?
    var moduleName: String?
    var outputFile: String?
    var jsr305: Array<String>?
    var supportCompatqualCheckerFrameworkAnnotations: String?

    val compilerArgumentsInstance: CommonCompilerArguments

    fun updatedCompilerArgumentsInstance(): CommonCompilerArguments = compilerArgumentsInstance.apply {
        languageVersion = this@FacetCompilerArgumentsData.languageVersion
        apiVersion = this@FacetCompilerArgumentsData.apiVersion
        pluginOptions = this@FacetCompilerArgumentsData.pluginOptions
        autoAdvanceLanguageVersion = this@FacetCompilerArgumentsData.autoAdvanceLanguageVersion
        autoAdvanceApiVersion = this@FacetCompilerArgumentsData.autoAdvanceApiVersion
        coroutinesState = when (this@FacetCompilerArgumentsData.coroutinesState) {
            null -> CommonCompilerArguments.DEFAULT
            LanguageFeature.State.ENABLED -> CommonCompilerArguments.ENABLE
            LanguageFeature.State.ENABLED_WITH_WARNING -> CommonCompilerArguments.WARN
            LanguageFeature.State.ENABLED_WITH_ERROR, LanguageFeature.State.DISABLED -> CommonCompilerArguments.ERROR
        }
        pluginClasspaths = this@FacetCompilerArgumentsData.pluginClasspaths
        (this as? K2JVMCompilerArguments)?.jvmTarget = this@FacetCompilerArgumentsData.jvmTarget
    }

    fun mergeDataFrom(from: FacetCompilerArgumentsData) {
        languageVersion = from.languageVersion
        apiVersion = from.apiVersion
        pluginOptions = from.pluginOptions
        autoAdvanceLanguageVersion = from.autoAdvanceLanguageVersion
        autoAdvanceApiVersion = from.autoAdvanceApiVersion
        coroutinesState = from.coroutinesState
        pluginClasspaths = from.pluginClasspaths
        jvmTarget = from.jvmTarget
        componentPlatforms = from.componentPlatforms
        targetPlatform = from.targetPlatform
        moduleName = from.moduleName
        outputFile = from.outputFile
        jsr305 = from.jsr305
        supportCompatqualCheckerFrameworkAnnotations = from.supportCompatqualCheckerFrameworkAnnotations
    }
}

/*
    This class is used when facet is configuring from loaded facet which contains serialized compilerArguments
 */
class FacetCompilerArgumentsDataInstanceBased(var baseCompilerArguments: CommonCompilerArguments) : FacetCompilerArgumentsData {
    override var languageVersion: String? = baseCompilerArguments.languageVersion
    override var apiVersion: String? = baseCompilerArguments.apiVersion
    override var pluginOptions: Array<String>? = baseCompilerArguments.pluginOptions
    override var autoAdvanceLanguageVersion: Boolean = true
    override var autoAdvanceApiVersion: Boolean = true
    override var coroutinesState: LanguageFeature.State? = null
    override var pluginClasspaths: Array<String>? = baseCompilerArguments.pluginClasspaths
    override var jvmTarget: String? = (baseCompilerArguments as? K2JVMCompilerArguments)?.jvmTarget
    override var componentPlatforms: String? =
        baseCompilerArguments.let { IdePlatformKind.platformByCompilerArguments(it) }?.serializeComponentPlatforms()
    override var targetPlatform: TargetPlatform?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var moduleName: String?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var outputFile: String?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var jsr305: Array<String>?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var supportCompatqualCheckerFrameworkAnnotations: String?
        get() = TODO("Not yet implemented")
        set(value) {}

    override val compilerArgumentsInstance: CommonCompilerArguments
        get() = baseCompilerArguments

    override fun updatedCompilerArgumentsInstance(): CommonCompilerArguments = super.updatedCompilerArgumentsInstance().also {
        baseCompilerArguments = it
    }

}

/*
    This class is used when facet is configuring from caches obtained during project import
 */
class FacetCompilerArgumentsDataCacheBased(val cachedArgsInfo: CachedArgsInfo, val mapper: CompilerArgumentsMapper) :
    FacetCompilerArgumentsData {

    override var languageVersion: String? = obtainDividedOptionValueFromCache(LANGUAGE_VERSION)
    override var apiVersion: String? = obtainDividedOptionValueFromCache(API_VERSION)
    override var jvmTarget: String? = obtainDividedOptionValueFromCache(JVM_TARGET)
    override var componentPlatforms: String? = null
    override var targetPlatform: TargetPlatform?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var moduleName: String?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var outputFile: String?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var jsr305: Array<String>?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var supportCompatqualCheckerFrameworkAnnotations: String?
        get() = TODO("Not yet implemented")
        set(value) {}
    override val compilerArgumentsInstance: CommonCompilerArguments = run {
        val currentArguments = cachedArgsInfo.currentCachedCompilerArgumentsBucket.collectArgumentsList(mapper)
        val defaultArguments = cachedArgsInfo.defaultCachedCompilerArgumentsBucket.collectArgumentsList(mapper)
        val dependencyClasspath = cachedArgsInfo.dependencyClasspathCacheIds.map { mapper.getCommonArgument(it) }
        val compilerArguments =
            componentPlatforms?.deserializeTargetPlatformByComponentPlatforms()?.createArguments() ?: CommonCompilerArguments.DummyImpl()
        val compilerArgumentsClass = compilerArguments::class.java
        val currentArgumentsBean = compilerArgumentsClass.newInstance()
        val defaultArgumentsBean = compilerArgumentsClass.newInstance()
        parseCommandLineArguments(currentArguments, currentArgumentsBean)
        parseCommandLineArguments(defaultArguments, defaultArgumentsBean)
        mergeBeans(defaultArgumentsBean, currentArgumentsBean)
    }

    override var autoAdvanceLanguageVersion: Boolean = true
    override var autoAdvanceApiVersion: Boolean = true
    override var coroutinesState: LanguageFeature.State? =
        obtainJointOptionValueFromCache(COROUTINES_STATE)?.let {
            when (it) {
                CommonCompilerArguments.ENABLE -> LanguageFeature.State.ENABLED
                CommonCompilerArguments.WARN, CommonCompilerArguments.DEFAULT -> LanguageFeature.State.ENABLED_WITH_WARNING
                CommonCompilerArguments.ERROR -> LanguageFeature.State.ENABLED_WITH_ERROR
                else -> null
            }
        }

    override var pluginOptions: Array<String>? = null

    override var pluginClasspaths: Array<String>?
        get() = TODO("Not yet implemented")
        set(value) {}

    private fun obtainDividedOptionValueFromCache(option: String) =
        mapper.getArgumentCache(option)?.let { retrieveNextFromCacheOrNull(it) }

    private fun obtainJointOptionValueFromCache(optionPrefix: String): String? {
        val argumentWithPrefix = cachedArgsInfo.currentCachedCompilerArgumentsBucket.cachedGeneralArguments
            .firstOrNull { id -> mapper.getCommonArgument(id).startsWith(optionPrefix) }
            ?: cachedArgsInfo.defaultCachedCompilerArgumentsBucket.cachedGeneralArguments
                .firstOrNull { id -> mapper.getCommonArgument(id).startsWith(optionPrefix) }
        return argumentWithPrefix?.let { mapper.getCommonArgument(it).removePrefix(optionPrefix) }
    }

    private fun retrieveNextFromCacheOrNull(idCurrent: Int): String? {
        val currentArgCaches = cachedArgsInfo.currentCachedCompilerArgumentsBucket.cachedGeneralArguments
        val defaultArgCaches = cachedArgsInfo.defaultCachedCompilerArgumentsBucket.cachedGeneralArguments
        return currentArgCaches.indexOfFirst { it == idCurrent }.takeIf { it != -1 }
            ?.let { mapper.getCommonArgument(currentArgCaches[it + 1]) }
            ?: defaultArgCaches.indexOfFirst { it == idCurrent }.takeIf { it != -1 }
                ?.let { mapper.getCommonArgument(defaultArgCaches[it + 1]) }

    }

    companion object {
        const val LANGUAGE_VERSION = "-language-version"
        const val API_VERSION = "-api-version"
        const val JVM_TARGET = "-jvm-target"
        const val COROUTINES_STATE = "-Xcoroutines="
    }


}