/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.pill.mapper

import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginConvention
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.SourceSet
import org.gradle.kotlin.dsl.extra
import org.jetbrains.kotlin.pill.model.PDependency
import org.jetbrains.kotlin.pill.model.PLibrary
import org.jetbrains.kotlin.pill.model.PProject
import java.io.File
import java.util.*
import kotlin.collections.HashMap

class LibraryDependencyMapper(private val rootProject: Project) : DependencyMapper {
    private companion object {
        private val DIST_LIBRARIES = listOf(
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

        private val IGNORED_LIBRARIES = listOf(
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

        private val MAPPED_LIBRARIES = mapOf(
            ":kotlin-reflect-api/java9" to ":kotlin-reflect/main"
        )

        private val LIB_DIRECTORIES = listOf("dependencies", "dist")
    }

    private val mappings: Map<String, Optional<PLibrary>> = run {
        val distLibDir = File(rootProject.extra["distLibDir"].toString())
        val result = HashMap<String, Optional<PLibrary>>()

        fun List<File>.filterExisting() = filter { it.exists() }

        for (path in DIST_LIBRARIES) {
            val project = rootProject.findProject(path) ?: error("Project not found")
            val archiveName = project.convention.findPlugin(BasePluginConvention::class.java)!!.archivesBaseName
            val classesJars = listOf(File(distLibDir, "$archiveName.jar")).filterExisting()
            val sourcesJars = listOf(File(distLibDir, "$archiveName-sources.jar")).filterExisting()
            val sourceSets = project.convention.findPlugin(JavaPluginConvention::class.java)!!.sourceSets

            val applicableSourceSets = listOfNotNull(
                sourceSets.findByName(SourceSet.MAIN_SOURCE_SET_NAME),
                sourceSets.findByName("java9")
            )

            val optLibrary = Optional.of(PLibrary(archiveName, classesJars, sourcesJars, originalName = path))
            applicableSourceSets.forEach { ss -> result["$path/${ss.name}"] = optLibrary }
        }

        for (path in IGNORED_LIBRARIES) {
            result["$path/main"] = Optional.empty<PLibrary>()
        }

        for ((old, new) in MAPPED_LIBRARIES) {
            result[old] = result[new] ?: error("Mapped library $old -> $new not found")
        }

        return@run result
    }

    val libraries: List<PLibrary> = mappings.values.filter { it.isPresent }.map { it.get() }

    override fun map(project: PProject, dependency: PDependency): List<PDependency> {
        if (dependency !is PDependency.ModuleLibrary) {
            return listOf(dependency)
        }

        val root = dependency.library.classes.singleOrNull() ?: return listOf(dependency)
        val paths = project.artifacts[root.absolutePath]

        if (paths == null) {
            val projectDir = rootProject.projectDir
            if (projectDir.isParent(root) && LIB_DIRECTORIES.none { File(projectDir, it).isParent(root) }) {
                rootProject.logger.warn("Paths not found for root: ${root.absolutePath}")
                return emptyList()
            }
            return listOf(dependency)
        }

        val result = mutableListOf<PDependency>()
        for (path in paths) {
            val module = project.modules.find { it.path == path }
            if (module != null) {
                result += PDependency.Module(module.name)
                continue
            }

            val maybeLibrary = mappings[path]
            if (maybeLibrary == null) {
                rootProject.logger.warn("Library not found for root: ${root.absolutePath} ($path)")
                continue
            }

            if (maybeLibrary.isPresent) {
                result += PDependency.Library(maybeLibrary.get().name)
            }
        }

        return result
    }

    private fun File.isParent(child: File): Boolean {
        var parent = child.parentFile ?: return false
        while (true) {
            if (parent == this) {
                return true
            }
            parent = parent.parentFile ?: return false
        }
    }
}