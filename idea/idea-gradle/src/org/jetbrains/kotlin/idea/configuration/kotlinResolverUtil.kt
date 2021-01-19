/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.configuration

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.externalSystem.model.DataNode
import com.intellij.openapi.externalSystem.model.ProjectKeys
import com.intellij.openapi.externalSystem.model.project.ModuleData
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.updateSettings.impl.pluginsAdvertisement.PluginsAdvertiser
import com.intellij.util.PlatformUtils
import org.jetbrains.annotations.NonNls
import org.jetbrains.kotlin.gradle.OrphanSourceSetsImportingReport
import org.jetbrains.kotlin.gradle.org.jetbrains.kotlin.reporting.KotlinImportingReportsContainer
import org.jetbrains.kotlin.gradle.org.jetbrains.kotlin.reporting.KotlinImportingReportsVisitor
import org.jetbrains.kotlin.idea.KotlinBundle
import org.jetbrains.kotlin.idea.KotlinIdeaGradleBundle

@NonNls
const val NATIVE_DEBUG_ID = "com.intellij.nativeDebug"

fun suggestNativeDebug(projectPath: String) {
    if (!PlatformUtils.isIdeaUltimate() ||
        PluginManagerCore.isPluginInstalled(PluginId.getId(NATIVE_DEBUG_ID))
    ) {
        return
    }

    val project = ProjectManager.getInstance().openProjects.firstOrNull { it.basePath == projectPath } ?: return

    PluginsAdvertiser.NOTIFICATION_GROUP.createNotification(
        KotlinIdeaGradleBundle.message("title.plugin.suggestion"),
        KotlinIdeaGradleBundle.message("notification.text.native.debug.provides.debugger.for.kotlin.native"),
        NotificationType.INFORMATION, null
    ).addAction(object : NotificationAction(KotlinIdeaGradleBundle.message("action.text.install")) {
        override fun actionPerformed(e: AnActionEvent, notification: Notification) {
            PluginsAdvertiser.installAndEnablePlugins(setOf(NATIVE_DEBUG_ID)) { notification.expire() }
        }
    }).notify(project)
}

fun processKotlinImportingReports(moduleDataNode: DataNode<ModuleData>, projectPath: String) {
    ProjectManager.getInstance().openProjects.firstOrNull { it.basePath == projectPath }?.apply {
        KotlinImportingReportsNotifierVisitor.visit(moduleDataNode.kotlinImportingReportsContainer)
    }
}

private object KotlinImportingReportsNotifierVisitor : KotlinImportingReportsVisitor<KotlinImportingReportsContainer> {
    override fun visit(visited: KotlinImportingReportsContainer) {
        visited[OrphanSourceSetsImportingReport::class.java].takeIf { it.isNotEmpty() }
            ?.joinToString("\n") { it.sourceSetName }
            ?.let {
                val notificationGroup = NotificationGroup.balloonGroup(KotlinBundle.message("importing.reports.notification.group"))
                notificationGroup.createNotification(
                    KotlinBundle.message("importing.reports.notification.orphan.title"),
                    KotlinBundle.message("importing.reports.notification.orphan.message", it),
                    NotificationType.WARNING
                )
            }?.notify(null)
    }
}
