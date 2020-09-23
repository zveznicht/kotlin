/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.pill.mapper

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.SourceSet
import org.jetbrains.kotlin.pill.model.PDependency
import org.jetbrains.kotlin.pill.model.PLibrary
import org.jetbrains.kotlin.pill.model.PModule
import org.jetbrains.kotlin.pill.model.PProject
import java.io.File
import java.util.*

abstract class LibraryDependencyMapper(protected val rootProject: Project) : DependencyMapper {
    protected companion object {
        private val MAPPED_KOTLIN_LIBRARIES = listOf(
            ":kotlin-annotations-jvm",
            ":kotlin-stdlib",
            ":kotlin-stdlib-jdk7",
            ":kotlin-stdlib-jdk8",
            ":kotlin-reflect",
            ":kotlin-test:kotlin-test-jvm",
            ":kotlin-test:kotlin-test-junit",
            ":kotlin-script-runtime",
            ":kotlin-coroutines-experimental-compat"
        )

        private val IGNORED_KOTLIN_LIBRARIES: List<String> = listOf(
            // Libraries
            ":kotlin-stdlib-common",
            ":kotlin-reflect-api",
            ":kotlin-serialization",
            ":kotlin-test:kotlin-test-common",
            ":kotlin-test:kotlin-test-annotations-common",
            // Other
            ":kotlin-compiler",
            ":kotlin-daemon-embeddable",
            ":kotlin-compiler-embeddable",
            ":kotlin-android-extensions",
            ":kotlin-scripting-compiler-embeddable",
            ":kotlin-scripting-compiler-impl-embeddable",
            ":kotlin-scripting-jvm-host"
        )

        fun generateMappings(rootProject: Project, mapper: (String) -> PDependency): Map<String, Optional<PDependency>> {
            val result = HashMap<String, Optional<PDependency>>()

            fun getApplicableSourceSets(path: String): List<String> {
                val project = rootProject.findProject(path) ?: error("Project not found")
                val sourceSets = project.convention.findPlugin(JavaPluginConvention::class.java)?.sourceSets ?: return emptyList()
                return listOf(SourceSet.MAIN_SOURCE_SET_NAME, "java9").filter { sourceSets.findByName(it) != null }
            }

            fun storeMapping(path: String, optDependency: Optional<PDependency>) {
                for (sourceSet in getApplicableSourceSets(path)) {
                    result["$path/${sourceSet}"] = optDependency
                }
            }

            for (path in MAPPED_KOTLIN_LIBRARIES) {
                storeMapping(path, Optional.of(mapper(path)))
            }

            for (path in IGNORED_KOTLIN_LIBRARIES) {
                storeMapping(path, Optional.empty<PDependency>())
            }

            return result
        }
    }

    abstract val projectLibraries: List<PLibrary>

    protected abstract val mappings: Map<String, Optional<PDependency>>

    protected abstract fun isAllowedLocalLibrary(library: File): Boolean

    override fun map(project: PProject, module: PModule, dependency: PDependency): List<PDependency> {
        if (dependency !is PDependency.ModuleLibrary) {
            return listOf(dependency)
        }

        val root = dependency.library.classes.singleOrNull() ?: return listOf(dependency)
        val paths = project.artifacts[root.absolutePath]

        if (paths == null) {
            val projectDir = rootProject.projectDir
            if (projectDir.isParent(root) && !isAllowedLocalLibrary(root)) {
                rootProject.logger.warn("[${module.name}] Paths not found for root: ${root.absolutePath}")
                return emptyList()
            }
            return listOf(dependency)
        }

        val result = mutableListOf<PDependency>()
        for (path in paths) {
            val dependencyModule = project.modules.find { it.path == path }
            if (dependencyModule != null) {
                result += PDependency.Module(dependencyModule.name)
                continue
            }

            val optDependency = mappings[path]
            if (optDependency == null) {
                rootProject.logger.warn("[${module.name}] Library not found for root: ${root.absolutePath} ($path)")
                continue
            }

            if (optDependency.isPresent) {
                result += optDependency.get()
            }
        }

        return result
    }

    protected fun File.isParent(child: File): Boolean {
        var parent = child.parentFile ?: return false
        while (true) {
            if (parent == this) {
                return true
            }
            parent = parent.parentFile ?: return false
        }
    }
}