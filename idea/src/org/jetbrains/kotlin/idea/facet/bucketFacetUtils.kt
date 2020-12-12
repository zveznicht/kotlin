/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.facet

import com.intellij.openapi.externalSystem.service.project.IdeModifiableModelsProvider
import com.intellij.openapi.module.Module
import com.intellij.openapi.projectRoots.JavaSdk
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.util.text.StringUtil
import org.jetbrains.kotlin.caching.DividedPropertiesWithArgumentAnnotationInfoManager
import org.jetbrains.kotlin.caching.FlatArgsInfo
import org.jetbrains.kotlin.caching.FlatCompilerArgumentsBucket
import org.jetbrains.kotlin.cli.common.arguments.*
import org.jetbrains.kotlin.config.*
import org.jetbrains.kotlin.idea.core.isAndroidModule
import org.jetbrains.kotlin.idea.framework.KotlinSdkType
import org.jetbrains.kotlin.idea.util.getProjectJdkTableSafe
import org.jetbrains.kotlin.platform.TargetPlatform
import org.jetbrains.kotlin.platform.js.isJs
import org.jetbrains.kotlin.platform.jvm.isJvm
import org.jetbrains.kotlin.platform.konan.isNative
import org.jetbrains.kotlin.utils.addToStdlib.cast
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

private val TargetPlatform.primaryFields: List<String>
    get() = when {
        isJvm() -> jvmPrimaryFields
        isJs() -> jsPrimaryFields
        isNative() -> commonPrimaryFields
        else -> metadataPrimaryFields
    }

private val TargetPlatform.ignoredFields: List<String>
    get() = when {
        isJvm() -> listOf(K2JVMCompilerArguments::noJdk.name, K2JVMCompilerArguments::jdkHome.name)
        else -> emptyList()
    }

private fun extractAdditionalArgsAndResetIgnored(
    currentBucket: FlatCompilerArgumentsBucket,
    defaultBucket: FlatCompilerArgumentsBucket,
    targetPlatform: TargetPlatform
): String {
    val primaryFields = targetPlatform.primaryFields
    val ignoredFields = targetPlatform.ignoredFields

    val emptyBucketForPlatform = targetPlatform.createArguments().toFlatCompilerArguments()

    val (flagPropsToArgumentAnnotation,
        singlePropsToArgumentAnnotation,
        multiplePropsToArgumentAnnotation,
        _) = DividedPropertiesWithArgumentAnnotationInfoManager(CommonCompilerArguments::class.java.classLoader)
        .dividedPropertiesWithArgumentAnnotationInfo

    val additionalFlags = flagPropsToArgumentAnnotation.filter { it.key.name !in primaryFields && it.key.name !in ignoredFields }
        .map { it to currentBucket.extractFlagArgument(it.key) }
        .filter { it.second.second != defaultBucket.extractFlagArgumentValue(it.first.key) && it.second.second }
        .map {
            currentBucket.setFlagArgument(it.first.key, emptyBucketForPlatform.extractFlagArgumentValue(it.first.key))
            it.second.first
        }
    flagPropsToArgumentAnnotation.keys.filter { it.name in ignoredFields }.forEach {
        currentBucket.setFlagArgument(it, emptyBucketForPlatform.extractFlagArgumentValue(it))
    }


    val additionalSingle = singlePropsToArgumentAnnotation.filter { it.key.name !in primaryFields && it.key.name !in ignoredFields }
        .map { it to currentBucket.extractSingleArgument(it.key) }
        .filter { it.second?.second != defaultBucket.extractSingleArgumentValue(it.first.key) }
        .mapNotNull { kv ->
            currentBucket.setSingleArgument(kv.first.key, emptyBucketForPlatform.extractSingleArgumentValue(kv.first.key))
            kv.second?.let {
                if (kv.first.value.isAdvanced) listOf("${it.first}=${it.second}") else listOf(it.first, it.second)
            }
        }.flatten()

    singlePropsToArgumentAnnotation.keys.filter { it.name in ignoredFields }.forEach {
        currentBucket.setSingleArgument(it, emptyBucketForPlatform.extractSingleArgumentValue(it))
    }

    val additionalMulti = multiplePropsToArgumentAnnotation.filter { it.key.name !in primaryFields && it.key.name !in ignoredFields }
        .map { it to currentBucket.extractMultipleArgument(it.key) }
        .filter { !it.second?.second.contentEquals(defaultBucket.extractMultipleArgumentValue(it.first.key)) }
        .mapNotNull { kv ->
            currentBucket.setMultipleArgument(kv.first.key, emptyBucketForPlatform.extractMultipleArgumentValue(kv.first.key))
            kv.second?.let {
                if (kv.first.value.isAdvanced) listOf("${it.first}=${it.second.joinToString(kv.first.value.delimiter)}")
                else listOf(it.first, it.second.joinToString(kv.first.value.delimiter))
            }
        }.flatten()

    multiplePropsToArgumentAnnotation.keys.filter { it.name in ignoredFields }.forEach {
        currentBucket.setMultipleArgument(it, emptyBucketForPlatform.extractMultipleArgumentValue(it))
    }

    return mutableListOf<String>().apply {
        addAll(additionalSingle)
        addAll(additionalMulti)
        addAll(additionalFlags)
        addAll(currentBucket.freeArgs)
        addAll(currentBucket.internalArguments)
    }.joinToString(" ") {
        if (StringUtil.containsWhitespaces(it) || it.startsWith('"')) {
            StringUtil.wrapWithDoubleQuote(StringUtil.escapeQuotes(it))
        } else it
    }
}

fun configureFacetByFlatArgsInfo(kotlinFacet: KotlinFacet, flatArgsInfo: FlatArgsInfo, modelsProvider: IdeModifiableModelsProvider?) {
    with(kotlinFacet.configuration.settings) {
        val targetPlatform = this.targetPlatform!!
        val compilerArgumentsBucket = this.compilerArgumentsBucket!!

        val currentBucket = flatArgsInfo.currentCompilerArgumentsBucket
        val defaultBucket = flatArgsInfo.defaultCompilerArgumentsBucket

        val emptyBucket = targetPlatform.createArguments().toFlatCompilerArguments()

        //TODO restore checking platform compatibilities
        copyBucketTo(currentBucket, compilerArgumentsBucket) { property, value ->
            value != when {
                property.name in setOf(K2JVMCompilerArguments::classpath.name, K2MetadataCompilerArguments::classpath.name) ->
                    emptyBucket.extractClasspaths()
                property.returnType.classifier == Boolean::class ->
                    emptyBucket.extractFlagArgumentValue(property.cast<KProperty1<out CommonCompilerArguments, Boolean>>())
                property.returnType.classifier == String::class ->
                    emptyBucket.extractSingleArgumentValue(property.cast<KProperty1<out CommonCompilerArguments, String?>>())
                (property.returnType.classifier as? KClass<*>)?.java?.isArray == true ->
                    emptyBucket.extractMultipleArgumentValue(property.cast<KProperty1<out CommonCompilerArguments, Array<String>?>>())
                else -> error("Unexpected property ${property.name} in filtering")
            }
        }

        defaultBucket.convertPathsToSystemIndependent()
        with(compilerArgumentsBucket) {
            setMultipleArgument(
                CommonCompilerArguments::pluginOptions,
                joinPluginOptions(
                    extractMultipleArgumentValue(CommonCompilerArguments::pluginOptions),
                    currentBucket.extractMultipleArgumentValue(CommonCompilerArguments::pluginOptions)
                )
            )
            convertPathsToSystemIndependent()
        }

        if (modelsProvider != null)
            kotlinFacet.module.configureSdkIfPossible(targetPlatform, compilerArgumentsBucket, modelsProvider)

        val additionalArgumentsString = extractAdditionalArgsAndResetIgnored(compilerArgumentsBucket, defaultBucket, targetPlatform)
        compilerSettings?.additionalArguments =
            if (additionalArgumentsString.isNotEmpty()) additionalArgumentsString else CompilerSettings.DEFAULT_ADDITIONAL_ARGUMENTS
        val languageLevel = languageLevel
        val apiLevel = apiLevel
        if (languageLevel != null && apiLevel != null && apiLevel > languageLevel) {
            this.apiLevel = languageLevel
        }
    }
}

private fun Module.configureSdkIfPossible(
    targetPlatform: TargetPlatform,
    bucket: FlatCompilerArgumentsBucket,
    modelsProvider: IdeModifiableModelsProvider
) {
    // SDK for Android module is already configured by Android plugin at this point
    if (isAndroidModule(modelsProvider) || hasNonOverriddenExternalSdkConfiguration(targetPlatform, bucket)) return

    val projectSdk = ProjectRootManager.getInstance(project).projectSdk
    KotlinSdkType.setUpIfNeeded()
    val allSdks = getProjectJdkTableSafe().allJdks
    val sdk = if (targetPlatform.isJvm()) {
        val jdkHome = bucket.extractSingleArgumentValue(K2JVMCompilerArguments::jdkHome)
        when {
            jdkHome != null -> allSdks.firstOrNull { it.sdkType is JavaSdk && FileUtil.comparePaths(it.homePath, jdkHome) == 0 }
            projectSdk != null && projectSdk.sdkType is JavaSdk -> projectSdk
            else -> allSdks.firstOrNull { it.sdkType is JavaSdk }
        }
    } else {
        allSdks.firstOrNull { it.sdkType is KotlinSdkType }
            ?: modelsProvider
                .modifiableModuleModel
                .modules
                .asSequence()
                .mapNotNull { modelsProvider.getModifiableRootModel(it).sdk }
                .firstOrNull { it.sdkType is KotlinSdkType }
    }

    val rootModel = modelsProvider.getModifiableRootModel(this)
    if (sdk == null || sdk == projectSdk) {
        rootModel.inheritSdk()
    } else {
        rootModel.sdk = sdk
    }
}

private fun Module.hasNonOverriddenExternalSdkConfiguration(targetPlatform: TargetPlatform, bucket: FlatCompilerArgumentsBucket): Boolean =
    hasExternalSdkConfiguration && (!targetPlatform.isJvm() || bucket.extractSingleArgumentValue(K2JVMCompilerArguments::jdkHome) == null)
