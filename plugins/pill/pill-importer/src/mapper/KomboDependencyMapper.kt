/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.pill.mapper

import org.gradle.api.Project
import org.gradle.api.internal.artifacts.ivyservice.CacheLayout
import org.gradle.api.invocation.Gradle
import org.gradle.api.plugins.BasePluginConvention
import org.jetbrains.kotlin.pill.model.PDependency
import org.jetbrains.kotlin.pill.model.PLibrary
import org.jetbrains.kotlin.pill.model.PModule
import org.jetbrains.kotlin.pill.model.PProject
import org.jetbrains.kotlin.pill.util.ProjectContext
import java.io.File
import java.util.*

class KomboDependencyMapper(rootProject: Project, mappingsDir: File, intellijDir: File) : LibraryDependencyMapper(rootProject) {
    private companion object {
        private val CACHED_GRADLE_LIBRARIES = listOf(
            "org.jetbrains.kotlin:protobuf-lite:",
            "org.jetbrains.kotlin:protobuf-relocated:"
        )
    }

    private val gradleDirs = GradleDirectoryProvider(rootProject.gradle)
    private val substitutions = SubstitutionFileReader(mappingsDir).read(ProjectContext(intellijDir))

    override val projectLibraries: List<PLibrary>
        get() = emptyList()

    override val mappings: Map<String, Optional<PDependency>>

    init {
        check(substitutions.isNotEmpty()) { "Substitutions shouldn't be empty" }

        val kotlinLibraryMappings = generateMappings(rootProject) { path ->
            val project = rootProject.findProject(path) ?: error("Project not found")
            val archiveName = project.convention.findPlugin(BasePluginConvention::class.java)!!.archivesBaseName
            PDependency.Library("kotlinc.$archiveName")
        }

        this.mappings = kotlinLibraryMappings
    }

    override fun map(project: PProject, module: PModule, dependency: PDependency): List<PDependency> {
        if (dependency is PDependency.ModuleLibrary) {
            val root = dependency.library.classes.singleOrNull()
            if (root != null) {
                mapRoot(module, root)?.let { return it }
            }
        }

        return super.map(project, module, dependency)
    }

    private fun mapRoot(module: PModule, root: File): List<PDependency>? {
        // Ordinary Gradle dependency
        if (root.startsWith(gradleDirs.filesDir)) {
            val path = root.toRelativeString(gradleDirs.filesDir).split(File.separatorChar)
            val groupId = path.getOrNull(0)
            val artifactId = path.getOrNull(1)
            val version = path.getOrNull(2)

            if (groupId != null && artifactId != null && version != null) {
                val mavenId = "$groupId:$artifactId:$version"
                val libraryName = "${root.name} ($groupId:$artifactId:$version)"
                val shouldCache = CACHED_GRADLE_LIBRARIES.any { mavenId.startsWith(it) }

                return if (shouldCache) {
                    // TODO cache library in repository
                    val library = PLibrary(name = libraryName, classes = listOf(root))
                    listOf(PDependency.ModuleLibrary(library))
                } else {
                    val mavenInfo = PLibrary.Maven(mavenId, includeTransitiveDeps = false)
                    val library = PLibrary(libraryName, classes = listOf(root), maven = mavenInfo)
                    listOf(PDependency.ModuleLibrary(library))
                }
            }
        }

        // Kotlin dependency
        if (root.startsWith(gradleDirs.kotlinBuildDependenciesDir)) {
            val relativePath = root.toRelativeString(gradleDirs.kotlinBuildDependenciesDir)

            // <artifactName>/<version>/artifacts/<path>
            val chunks = relativePath.split(File.separatorChar, limit = 4)
            if (chunks.size != 4 || chunks[2] != "artifacts") {
                rootProject.logger.error("[${module.name}] Invalid Kotlin dependency path: $relativePath")
                return null
            }

            val artifactName = chunks[0]
            val path = chunks[3]

            val substitutionsForArtifact = substitutions[artifactName]
            if (substitutionsForArtifact == null) {
                rootProject.logger.error("[${module.name}] Can't find IntelliJ artifact $artifactName ($relativePath)")
                return null
            }

            val substitutionsForPath = substitutionsForArtifact?.get(path)
            if (substitutionsForPath == null) {
//                rootProject.logger.warn("Can't find a substitution for Kotlin dependency: $relativePath")
                return null
            }

            return substitutionsForPath.map { mapSubstitution(it) }
        }

        return null
    }

    private fun mapSubstitution(substitution: Substitution): PDependency {
        return when (substitution) {
            is Substitution.ProjectLibrary -> PDependency.Library(substitution.libraryName)
            is Substitution.ModuleOutput -> PDependency.Module(substitution.moduleName)
            is Substitution.File -> {
                val library = PLibrary(substitution.file.name, classes = listOf(substitution.file))
                PDependency.ModuleLibrary(library)
            }
        }
    }

    override fun isAllowedLocalLibrary(library: File) = false
}

private class GradleDirectoryProvider(gradle: Gradle) {
    private companion object {
        fun File.ensureExists(): File {
            check(exists()) { "File $this doesn't exist" }
            return this
        }
    }

    val rootDir: File = gradle.gradleUserHomeDir
    val filesDir: File = File(rootDir, "caches/modules-${CacheLayout.ROOT.version}/files-${CacheLayout.FILE_STORE.version}").ensureExists()
    val kotlinBuildDependenciesDir = File(rootDir, "kotlin-build-dependencies/repo/kotlin.build").ensureExists()
}