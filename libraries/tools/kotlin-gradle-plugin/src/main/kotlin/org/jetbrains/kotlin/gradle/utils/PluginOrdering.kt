/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.utils

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.AbstractAndroidProjectHandler

internal class AfterEvaluateAndroidOrdering {
    companion object {
        private const val extraPropertyName = "org.jetbrains.kotlin.internal.AfterEvaluateAndroidOrdering"

        internal fun init(project: Project) {
            val instance = get(project)
            instance.waitForAndroidPlugin(project)
        }

        private fun get(project: Project): AfterEvaluateAndroidOrdering =
            with(project.extensions.extraProperties) {
                if (!has(extraPropertyName)) {
                    val instance = AfterEvaluateAndroidOrdering()
                    set(extraPropertyName, instance)
                }
                get(extraPropertyName) as AfterEvaluateAndroidOrdering
            }

        internal fun whenEvaluatedAfterAndroid(project: Project, action: () -> Unit) {
            with(get(project)) {
                if (done) {
                    action()
                } else {
                    pendingActions += action
                }
            }
        }
    }

    private val pendingActions = mutableListOf<() -> Unit>()

    private class ActionHandlingToken
    private var currentActionHandlingToken: ActionHandlingToken = ActionHandlingToken()
    private val doneToken = ActionHandlingToken()

    private val done: Boolean
        get() = currentActionHandlingToken === doneToken

    private fun applyInAfterEvaluateIfTokenNotChanged(project: Project) {
        val handler = currentActionHandlingToken
        project.afterEvaluate {
            if (currentActionHandlingToken == handler) {
                currentActionHandlingToken = doneToken
                pendingActions.forEach { it.invoke() }
            }
        }
    }

    private fun waitForAndroidPlugin(project: Project) {
        applyInAfterEvaluateIfTokenNotChanged(project)

        AbstractAndroidProjectHandler.androidPluginIds.forEach { androidPluginId ->
            currentActionHandlingToken = ActionHandlingToken()
            project.pluginManager.withPlugin(androidPluginId) { applyInAfterEvaluateIfTokenNotChanged(project) }
        }
    }
}
