/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.targets.js.ir

import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.component.ProjectComponentIdentifier
import org.gradle.api.artifacts.result.ResolvedComponentResult
import org.gradle.api.artifacts.result.ResolvedDependencyResult
import org.gradle.api.attributes.Attribute
import org.gradle.api.file.FileCollection
import org.jetbrains.kotlin.gradle.dsl.multiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.mpp.ModuleDependencyIdentifier
import org.jetbrains.kotlin.gradle.plugin.mpp.ModuleIds
import org.jetbrains.kotlin.gradle.plugin.sources.KotlinDependencyScope
import org.jetbrains.kotlin.gradle.plugin.sources.sourceSetDependencyConfigurationByScope
import java.io.File
import java.io.InputStream
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import javax.xml.parsers.DocumentBuilderFactory

internal class DependencyIncrementalCompilationTransformation(
    val project: Project,
    val kotlinSourceSet: KotlinSourceSet,
    /** A list of scopes that the dependencies from [kotlinSourceSet] are treated as requested dependencies. */
    private val sourceSetRequestedScopes: List<KotlinDependencyScope>,
    /** A configuration that holds the dependencies of the appropriate scope for all Kotlin source sets in the project */
    private val allSourceSetsConfiguration: Configuration
) {
    // Keep parents of each dependency, too. We need a dependency's parent when it's an MPP's metadata module dependency:
    // in this case, the parent is the MPP's root module.
    private data class ResolvedDependencyWithParent(
        val dependency: ResolvedComponentResult,
        val parent: ResolvedComponentResult?
    )

    val metadataDependencyResolutions: Unit by lazy { doTransform() }

    private val requestedDependencies: Iterable<Dependency> by lazy {
        fun collectScopedDependenciesFromSourceSet(sourceSet: KotlinSourceSet): Set<Dependency> =
            sourceSetRequestedScopes.flatMapTo(mutableSetOf()) { scope ->
                project.sourceSetDependencyConfigurationByScope(sourceSet, scope).incoming.dependencies
            }

        val ownDependencies = collectScopedDependenciesFromSourceSet(kotlinSourceSet)

        ownDependencies
    }

    private val configurationToResolve: Configuration by lazy {
        /** If [kotlinSourceSet] is not a published source set, its dependencies are not included in [allSourceSetsConfiguration].
         * In that case, to resolve the dependencies of the source set in a way that is consistent with the published source sets,
         * we need to create a new configuration with the dependencies from both [allSourceSetsConfiguration] and the
         * input configuration(s) of the source set. */
        var modifiedConfiguration: Configuration? = null

        val originalDependencies = allSourceSetsConfiguration.allDependencies

        requestedDependencies.forEach { dependency ->
            if (dependency !in originalDependencies) {
                modifiedConfiguration = modifiedConfiguration ?: project.configurations.detachedConfiguration().apply {
                    fun <T> copyAttribute(key: Attribute<T>) {
                        attributes.attribute(key, allSourceSetsConfiguration.attributes.getAttribute(key)!!)
                    }
                    allSourceSetsConfiguration.attributes.keySet().forEach { copyAttribute(it) }
                    allSourceSetsConfiguration.extendsFrom.forEach { extendsFrom(it) }
                }
                modifiedConfiguration!!.dependencies.add(dependency)
            }
        }

        modifiedConfiguration ?: allSourceSetsConfiguration
    }

    private fun doTransform() {
        val resolutionResult = configurationToResolve.incoming.resolutionResult
        val allModuleDependencies =
            configurationToResolve.incoming.resolutionResult.allDependencies.filterIsInstance<ResolvedDependencyResult>()

        val allRequestedDependencies = requestedDependencies

        val resolvedDependencyQueue: Queue<ResolvedDependencyWithParent> = ArrayDeque<ResolvedDependencyWithParent>().apply {
            val requestedModules: Set<ModuleDependencyIdentifier> = allRequestedDependencies.mapTo(mutableSetOf()) {
                ModuleIds.fromDependency(it)
            }

            addAll(
                resolutionResult.root.dependencies
                    .filter { ModuleIds.fromComponentSelector(project, it.requested) in requestedModules }
                    .filterIsInstance<ResolvedDependencyResult>()
                    .map { ResolvedDependencyWithParent(it.selected, null) }
            )
        }

        val visitedDependencies = mutableSetOf<ResolvedComponentResult>()

        while (resolvedDependencyQueue.isNotEmpty()) {
            val (resolvedDependency: ResolvedComponentResult, parent: ResolvedComponentResult?) = resolvedDependencyQueue.poll()

            visitedDependencies.add(resolvedDependency)

            println(
                resolvedDependency.moduleVersion
            )

            val transitiveDependenciesToVisit =
                    resolvedDependency.dependencies.filterIsInstance<ResolvedDependencyResult>()

            resolvedDependencyQueue.addAll(
                transitiveDependenciesToVisit.filter { it.selected !in visitedDependencies }
                    .map { ResolvedDependencyWithParent(it.selected, resolvedDependency) }
            )
        }
    }
}