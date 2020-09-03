/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.pill.mapper

import org.jetbrains.kotlin.pill.model.PDependency
import org.jetbrains.kotlin.pill.model.PProject

interface DependencyMapper {
    fun map(project: PProject, dependency: PDependency): List<PDependency>
}