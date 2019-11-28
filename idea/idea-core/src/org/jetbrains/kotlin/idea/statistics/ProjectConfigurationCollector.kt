/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.statistics

import com.intellij.facet.ProjectFacetManager
import com.intellij.internal.statistic.beans.UsageDescriptor
import com.intellij.internal.statistic.eventLog.FeatureUsageData
import com.intellij.internal.statistic.service.fus.collectors.ProjectUsagesCollector
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.idea.KotlinPluginUtil
import org.jetbrains.kotlin.idea.configuration.BuildSystemType
import org.jetbrains.kotlin.idea.configuration.getBuildSystemType
import org.jetbrains.kotlin.idea.facet.KotlinFacetType
import org.jetbrains.kotlin.idea.project.platform
import org.jetbrains.kotlin.platform.js.JsPlatform
import org.jetbrains.kotlin.platform.jvm.JvmPlatform
import org.jetbrains.kotlin.platform.konan.KonanPlatform

class ProjectConfigurationCollector : ProjectUsagesCollector() {

    override fun getUsages(project: Project): Set<UsageDescriptor> {
        val usages = mutableSetOf<UsageDescriptor>()
        val modulesWithFacet = ProjectFacetManager.getInstance(project).getModulesWithFacet(KotlinFacetType.TYPE_ID)

        if (modulesWithFacet.isNotEmpty()) {
            val pluginVersion = KotlinPluginUtil.getPluginVersion()
            modulesWithFacet.forEach {
                val buildSystem = getBuildSystemType(it)
                val platform = getPlatform(it)
                val data = FeatureUsageData()
                    .addData("pluginVersion", pluginVersion)
                    .addData("system", buildSystem)
                    .addData("platform", platform)
                val usageDescriptor = UsageDescriptor("Build", data)
                usages.add(usageDescriptor)
            }
        }
        return usages
    }

    private fun getPlatform(it: Module): String {
        return it.platform?.componentPlatforms?.map {
            when (it) {
                is JvmPlatform -> "jvm"
                is JsPlatform -> "js"
                is KonanPlatform -> "native"
                else -> "unknown"
            }
        }?.joinToString(",") ?: "none"
    }

    private fun getBuildSystemType(it: Module): String {
        val buildSystem = it.getBuildSystemType()
        return when {
            buildSystem == BuildSystemType.JPS -> "JPS"
            buildSystem.toString().toLowerCase().contains("maven") -> "Maven"
            buildSystem.toString().toLowerCase().contains("gradle") -> "Gradle"
            else -> "unknown"
        }
    }

    override fun getGroupId() = "kotlin.project.configuration"
    override fun getVersion(): Int = 1
}
