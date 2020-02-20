/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.tools.projectWizard.moduleConfigurators

import org.jetbrains.kotlin.tools.projectWizard.core.ReadingContext
import org.jetbrains.kotlin.tools.projectWizard.core.entity.PluginSettingReference
import org.jetbrains.kotlin.tools.projectWizard.core.entity.SettingType
import org.jetbrains.kotlin.tools.projectWizard.core.entity.reference
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.BuildSystemIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.GradleOnlyPluginByNameIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.KotlinBuildSystemPluginIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.AndroidConfigIR
import org.jetbrains.kotlin.tools.projectWizard.plugins.AndroidPlugin
import org.jetbrains.kotlin.tools.projectWizard.plugins.kotlin.ModuleConfigurationData
import org.jetbrains.kotlin.tools.projectWizard.plugins.kotlin.ModuleType
import org.jetbrains.kotlin.tools.projectWizard.settings.buildsystem.Module
import org.jetbrains.kotlin.tools.projectWizard.settings.javaPackage
import org.jetbrains.kotlin.tools.projectWizard.core.buildList
import org.jetbrains.kotlin.tools.projectWizard.core.service.kotlinVersionKind
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.RepositoryIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.BuildScriptDependencyIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.BuildScriptRepositoryIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.RawGradleIR
import org.jetbrains.kotlin.tools.projectWizard.plugins.kotlin.ModuleSubType
import org.jetbrains.kotlin.tools.projectWizard.settings.buildsystem.DefaultRepository
import org.jetbrains.kotlin.tools.projectWizard.settings.buildsystem.Repository

interface AndroidModuleConfigurator : ModuleConfigurator, ModuleConfiguratorWithSettings, ModuleConfiguratorWithModuleType {
    override val moduleType: ModuleType
        get() = ModuleType.jvm
    override val greyText: String
        get() = "Requires Android SDK"

    override fun getPluginSettings(): List<PluginSettingReference<Any, SettingType<Any>>> =
        listOf(AndroidPlugin::androidSdkPath.reference)

    override fun ReadingContext.createBuildFileIRs(
        configurationData: ModuleConfigurationData,
        module: Module
    ) = buildList<BuildSystemIR> {
        +GradleOnlyPluginByNameIR("com.android.application")

        // it is explicitly here instead of by `createKotlinPluginIR` as it should be after `com.android.application`
        +KotlinBuildSystemPluginIR(
            KotlinBuildSystemPluginIR.Type.android,
            version = null // Version is already present in the parent buildfile
        )
        +GradleOnlyPluginByNameIR("kotlin-android-extensions")
        +AndroidConfigIR(module.javaPackage(configurationData.pomIr))
        +createRepositories(configurationData).map(::RepositoryIR)
    }

    override fun createRootBuildFileIrs(configurationData: ModuleConfigurationData) = buildList<BuildSystemIR> {
        +createRepositories(configurationData).map { BuildScriptRepositoryIR(RepositoryIR(it)) }
        +listOf(
            RawGradleIR { call("classpath") { +"com.android.tools.build:gradle:3.2.1".quotified } },
            RawGradleIR {
                call("classpath") {
                    +"org.jetbrains.kotlin:kotlin-gradle-plugin:${configurationData.kotlinVersion}".quotified
                }
            }
        ).map(::BuildScriptDependencyIR)
    }

    companion object {
        fun createRepositories(configurationData: ModuleConfigurationData) = buildList<Repository> {
            +DefaultRepository.GRADLE_PLUGIN_PORTAL
            +DefaultRepository.GOOGLE
            +DefaultRepository.JCENTER
            addIfNotNull(configurationData.kotlinVersion.kotlinVersionKind.repository)
        }
    }
}

object AndroidTargetConfigurator : TargetConfigurator,
    SimpleTargetConfigurator,
    AndroidModuleConfigurator,
    SingleCoexistenceTargetConfigurator {
    override val moduleSubType = ModuleSubType.android
    override val moduleType = ModuleType.jvm
}
