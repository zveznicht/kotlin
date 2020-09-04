/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.pill.mapper

import org.gradle.api.Project
import org.jetbrains.kotlin.pill.model.PDependency
import org.jetbrains.kotlin.pill.model.PLibrary
import java.io.File
import java.util.*

class KomboDependencyMapper(rootProject: Project) : LibraryDependencyMapper(rootProject) {
    override val projectLibraries: List<PLibrary>
        get() = emptyList()

    override val mappings: Map<String, Optional<PDependency>>

    init {
        val kotlinLibraryMappings = generateMappings(rootProject) { mappedLibrary ->
            Optional.of(PDependency.Library(mappedLibrary.intellijLibraryName))
        }

        this.mappings = kotlinLibraryMappings
    }

    override fun isAllowedLocalLibrary(library: File) = false
}