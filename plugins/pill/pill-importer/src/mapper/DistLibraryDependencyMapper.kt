/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.pill.mapper

import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginConvention
import org.gradle.kotlin.dsl.extra
import org.jetbrains.kotlin.pill.model.PDependency
import org.jetbrains.kotlin.pill.model.PLibrary
import java.io.File
import java.util.*

class DistLibraryDependencyMapper(rootProject: Project) : LibraryDependencyMapper(rootProject) {
    private companion object {
        private val LIB_DIRECTORIES = listOf("dependencies", "dist")
    }

    private val rootProjectDir = rootProject.projectDir

    override val mappings: Map<String, Optional<PDependency>>
    override val projectLibraries: List<PLibrary>

    init {
        val distLibDir = File(rootProject.extra["distLibDir"].toString())

        fun List<File>.filterExisting() = filter { it.exists() }

        val projectLibraries = mutableListOf<PLibrary>()

        val mappings = generateMappings(rootProject) { mappedLibrary ->
            val path = mappedLibrary.path
            val project = rootProject.findProject(path) ?: error("Project not found")
            val archiveName = project.convention.findPlugin(BasePluginConvention::class.java)!!.archivesBaseName
            val classesJars = listOf(File(distLibDir, "$archiveName.jar")).filterExisting()
            val sourcesJars = listOf(File(distLibDir, "$archiveName-sources.jar")).filterExisting()
            val library = PLibrary(archiveName, classesJars, sourcesJars)
            projectLibraries += library
            Optional.of(PDependency.Library(library.name))
        }

        this.mappings = mappings
        this.projectLibraries = projectLibraries
    }

    override fun isAllowedLocalLibrary(library: File): Boolean {
        return LIB_DIRECTORIES.any { File(rootProjectDir, it).isParent(library) }
    }
}