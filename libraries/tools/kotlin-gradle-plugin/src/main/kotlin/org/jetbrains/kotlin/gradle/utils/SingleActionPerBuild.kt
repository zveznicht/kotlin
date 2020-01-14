/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.utils

import org.gradle.api.Project
import java.util.*

internal object SingleActionPerBuild {
    private val rootModulePerformedActions = WeakHashMap<Project, MutableSet<String>>()

    fun run(project: Project, actionId: String, action: () -> Unit) {
        val performedActions = rootModulePerformedActions.computeIfAbsent(project) { mutableSetOf() }
        if (performedActions.add(actionId)) {
            action()
        }
    }
}
