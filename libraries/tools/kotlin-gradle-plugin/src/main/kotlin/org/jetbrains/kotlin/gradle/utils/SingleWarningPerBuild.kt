/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.utils

import org.gradle.api.Project

internal object SingleWarningPerBuild {
    private const val ACTION_ID_SHOW_WARNING = "show-warning:"

    fun show(project: Project, warningText: String) = SingleActionPerBuild.run(project, ACTION_ID_SHOW_WARNING + warningText) {
        project.logger.warn(warningText)
    }
}
