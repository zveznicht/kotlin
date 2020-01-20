/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.targets.native.internal

import org.gradle.api.Project
import org.jetbrains.kotlin.compilerRunner.konanHome
import org.jetbrains.kotlin.daemon.common.toHexString
import org.jetbrains.kotlin.descriptors.commonizer.konan.NativeDistributionCommonizer
import org.jetbrains.kotlin.gradle.dsl.multiplatformExtensionOrNull
import org.jetbrains.kotlin.gradle.internal.isInIdeaSync
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.CompilationSourceSetUtil
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeCompilation
import org.jetbrains.kotlin.gradle.targets.metadata.isKotlinGranularMetadataEnabled
import org.jetbrains.kotlin.konan.library.KONAN_DISTRIBUTION_COMMON_LIBS_DIR
import org.jetbrains.kotlin.konan.library.KONAN_DISTRIBUTION_KLIB_DIR
import org.jetbrains.kotlin.konan.library.KONAN_DISTRIBUTION_PLATFORM_LIBS_DIR
import org.jetbrains.kotlin.konan.library.KONAN_STDLIB_NAME
import org.jetbrains.kotlin.konan.target.KonanTarget
import org.jetbrains.kotlin.utils.addToStdlib.flattenTo
import java.io.File
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

internal fun Project.setUpKotlinNativePlatformDependencies() {
    if (multiplatformExtensionOrNull == null) {
        // not a multiplatform project, nothing to set up
        return
    }

    val dependencyResolver = NativePlatformDependencyResolver(this)

    // run commonizer only for HMPP projects and only on IDE sync
    val allowCommonizer = isKotlinGranularMetadataEnabled && isInIdeaSync

    findSourceSetsToAddDependencies(allowCommonizer).forEach { (sourceSet, sourceSetDeps) ->
        sourceSetDeps.forEach { sourceSetDep ->
            val resolvedDep = dependencyResolver.resolve(sourceSetDep)
            resolvedDep.libraryPaths.forEach { dir ->
                project.dependencies.add(sourceSet.implementationMetadataConfigurationName, dependencies.create(files(dir)))
            }
        }
    }
}

private sealed class NativePlatformDependency {
    /* Non-commonized target-neutral libraries taken directly from the Kotlin/Native distribution */
    data class OutOfDistributionCommon(val includeEndorsedLibs: Boolean) : NativePlatformDependency()

    /* Non-commonized libraries for the specific target taken directly from the Kotlin/Native distribution */
    data class OutOfDistributionPlatform(val target: KonanTarget) : NativePlatformDependency()

    /* Commonized (common) libraries */
    data class CommonizedCommon(val targets: Set<KonanTarget>) : NativePlatformDependency()

    /* Commonized libraries for a specific target platform */
    data class CommonizedPlatform(val target: KonanTarget, val common: CommonizedCommon) : NativePlatformDependency()
}

private sealed class NativePlatformResolvedDependency {
    abstract val dependency: NativePlatformDependency
    abstract val libraryPaths: Set<File>

    class OutOfDistributionCommon(
        override val dependency: NativePlatformDependency.OutOfDistributionCommon,
        distributionLibsDir: File
    ) : NativePlatformResolvedDependency() {
        override val libraryPaths = libsInCommonDir(distributionLibsDir).let {
            /* stdlib, endorsed libs */
            if (!dependency.includeEndorsedLibs) it.filter { dir -> dir.endsWith(KONAN_STDLIB_NAME) } else it
        }.toSet()
    }

    class OutOfDistributionPlatform(
        override val dependency: NativePlatformDependency.OutOfDistributionPlatform,
        distributionLibsDir: File
    ) : NativePlatformResolvedDependency() {
        override val libraryPaths =
            /* platform libs for a specific target */ libsInPlatformDir(distributionLibsDir, dependency.target)
    }

    class CommonizedCommon(
        override val dependency: NativePlatformDependency.CommonizedCommon,
        val commonizedLibsDir: File
    ) : NativePlatformResolvedDependency() {
        override val libraryPaths =
            /* commonized platform libs with expect declarations */ libsInCommonDir(commonizedLibsDir)
    }

    class CommonizedPlatform(
        override val dependency: NativePlatformDependency.CommonizedPlatform,
        val common: CommonizedCommon
    ) : NativePlatformResolvedDependency() {
        override val libraryPaths =
            /* commonized platform libs with actual declarations */ libsInPlatformDir(common.commonizedLibsDir, dependency.target)
    }

    private companion object {
        private fun libsInCommonDir(basePath: File) =
            basePath.resolve(KONAN_DISTRIBUTION_COMMON_LIBS_DIR).listFiles()?.toSet() ?: emptySet()

        private fun libsInPlatformDir(basePath: File, target: KonanTarget) =
            basePath.resolve(KONAN_DISTRIBUTION_PLATFORM_LIBS_DIR).resolve(target.name).listFiles()?.toSet() ?: emptySet()
    }
}

private class NativePlatformDependencyResolver(val project: Project) {
    private val distributionDir = File(project.konanHome)
    private val distributionLibsDir = distributionDir.resolve(KONAN_DISTRIBUTION_KLIB_DIR)
    private val resolvedDependencies = mutableMapOf<NativePlatformDependency, NativePlatformResolvedDependency>()

    fun resolve(dependency: NativePlatformDependency): NativePlatformResolvedDependency = resolvedDependencies.computeIfAbsent(dependency) {
        when (dependency) {
            is NativePlatformDependency.OutOfDistributionCommon -> {
                NativePlatformResolvedDependency.OutOfDistributionCommon(dependency, distributionLibsDir)
            }

            is NativePlatformDependency.OutOfDistributionPlatform -> {
                NativePlatformResolvedDependency.OutOfDistributionPlatform(dependency, distributionLibsDir)
            }

            is NativePlatformDependency.CommonizedCommon -> {
                val commonizedLibsDir = runCommonizer(project, distributionDir, dependency.targets)
                NativePlatformResolvedDependency.CommonizedCommon(dependency, commonizedLibsDir)
            }

            is NativePlatformDependency.CommonizedPlatform -> {
                val resolvedCommon = resolve(dependency.common) as NativePlatformResolvedDependency.CommonizedCommon
                NativePlatformResolvedDependency.CommonizedPlatform(dependency, resolvedCommon)
            }
        }
    }
}

private fun Project.findSourceSetsToAddDependencies(allowCommonizer: Boolean): Map<KotlinSourceSet, Set<NativePlatformDependency>> {
    val sourceSetsToAddDeps = mutableMapOf<KotlinSourceSet, Set<NativePlatformDependency>>()
    if (allowCommonizer) {
        sourceSetsToAddDeps += findSourceSetsToAddCommonizedPlatformDependencies()
    }

    val compilationsBySourceSets = CompilationSourceSetUtil.compilationsBySourceSets(this)
    val nativeCompilations = compilationsBySourceSets.values.flattenTo(mutableSetOf()).filterIsInstance<KotlinNativeCompilation>()

    nativeCompilations.associate { it.defaultSourceSet to (it.konanTarget to it.enableEndorsedLibs) }
        .forEach { (defaultSourceSet, details) ->
            if (defaultSourceSet !in sourceSetsToAddDeps) {
                val (target, includeEndorsedLibs) = details
                sourceSetsToAddDeps[defaultSourceSet] = setOf(
                    NativePlatformDependency.OutOfDistributionCommon(includeEndorsedLibs),
                    NativePlatformDependency.OutOfDistributionPlatform(target)
                )
            }
        }

    return sourceSetsToAddDeps
}

private fun Project.findSourceSetsToAddCommonizedPlatformDependencies(): Map<KotlinSourceSet, Set<NativePlatformDependency>> {
    val sourceSetsToAddDeps = mutableMapOf<KotlinSourceSet, Set<NativePlatformDependency>>()

    val compilationsBySourceSets = CompilationSourceSetUtil.compilationsBySourceSets(this)
    val nativeCompilations = compilationsBySourceSets.values.flattenTo(mutableSetOf()).filterIsInstance<KotlinNativeCompilation>()

    nativeCompilations.forEach { nativeCompilation ->
        // consider source sets in compilation only one step above the default source set
        // TODO: reconsider this restriction
        val commonSourceSetCandidates = nativeCompilation.allKotlinSourceSets intersect nativeCompilation.defaultSourceSet.dependsOn

        commonSourceSetCandidates.forEach sourceSet@{ sourceSet ->
            if (sourceSet.name == KotlinSourceSet.COMMON_MAIN_SOURCE_SET_NAME
                || sourceSet.name == KotlinSourceSet.COMMON_TEST_SOURCE_SET_NAME
            ) {
                // exclude the most common source sets
                return@sourceSet
            }

            if (sourceSet in sourceSetsToAddDeps) {
                // already processed
                return@sourceSet
            }

            val leafSourceSets = mutableMapOf<KotlinSourceSet, Pair<KonanTarget, /* include endorsed? */ Boolean>>()

            for (compilation in compilationsBySourceSets.getValue(sourceSet)) {
                if (compilation !is KotlinNativeCompilation) {
                    // the source set participates in non-native compilation
                    return@sourceSet
                }

                val defaultSourceSet = compilation.defaultSourceSet
                if (defaultSourceSet == sourceSet) {
                    // there is a compilation where the source set is the default source set
                    return@sourceSet
                }

                leafSourceSets[defaultSourceSet] = compilation.konanTarget to compilation.enableEndorsedLibs
            }

            val allTargets = leafSourceSets.values.mapTo(mutableSetOf()) { /* target */ it.first }
            if (allTargets.isEmpty())
                return@sourceSet

            val commonizedCommonDep = NativePlatformDependency.CommonizedCommon(allTargets)
            val includeEndorsedLibsToCommonSourceSet = leafSourceSets.values.all { /* include endorsed? */ it.second }

            sourceSetsToAddDeps[sourceSet] = setOf(
                NativePlatformDependency.OutOfDistributionCommon(includeEndorsedLibsToCommonSourceSet),
                commonizedCommonDep
            )

            leafSourceSets.forEach { (leafSourceSet, details) ->
                val existingDep = sourceSetsToAddDeps[leafSourceSet]
                if (existingDep == null) {
                    val (target, includeEndorsedLibs) = details
                    val commonizedPlatformDep = NativePlatformDependency.CommonizedPlatform(target, commonizedCommonDep)

                    sourceSetsToAddDeps[leafSourceSet] = setOf(
                        NativePlatformDependency.OutOfDistributionCommon(includeEndorsedLibs),
                        commonizedPlatformDep
                    )
                } /*else if (existingDep != leafDep) {
                    // do nothing, the warning will be logged later
                }*/
            }
        }
    }

    return sourceSetsToAddDeps
}

private fun runCommonizer(project: Project, distributionDir: File, targets: Set<KonanTarget>): File {
    if (targets.size == 1) {
        // no need to commonize, just use the libraries from the distribution
        return distributionDir.resolve(KONAN_DISTRIBUTION_KLIB_DIR)
    }

    val baseDestinationDir = project.rootProject.buildDir.resolve("commonizedLibraries")
    check(baseDestinationDir.isDirectory || !baseDestinationDir.exists()) { "$baseDestinationDir is not a directory" }

    val kotlinVersion = project.getKotlinPluginVersion()?.onlySafeCharacters

    // naive up-to-date check
    val definitelyNotUpToDate = when {
        kotlinVersion == null || kotlinVersion.endsWith("SNAPSHOT", ignoreCase = true) -> {
            // "X.Y.Z-SNAPSHOT" is not enough to uniquely identify the concrete version of Kotlin plugin,
            // therefore lets assume that it's always not up to date
            true
        }

        !distributionDir.startsWith(File(System.getProperty("user.home")).resolve(".konan")) -> {
            // a distribution installed in non-standard location, probably built by user
            true
        }

        else -> false
    }

    // need stable order of targets for consistency
    val orderedTargets = targets.sortedBy { it.name }

    val discriminator = buildString {
        append(kotlinVersion ?: "unknown")
        append("-")
        append(distributionDir.path.md5String)
        append("-")
        orderedTargets.joinTo(this, separator = "-")
    }

    val destinationDir = baseDestinationDir.resolve(discriminator)
    if (!definitelyNotUpToDate && destinationDir.isDirectory) {
        // it's up to date
        return destinationDir
    }

    destinationDir.deleteRecursively()

    val destinationTmpDir = destinationDir.parentFile.resolve(destinationDir.name + ".tmp")

    NativeDistributionCommonizer(
        repository = distributionDir,
        targets = orderedTargets,
        destination = destinationTmpDir,
        handleError = ::error,
        log = { project.logger.info("[COMMONIZER] $it") }
    ).run()

    destinationTmpDir.renameTo(destinationDir)

    return destinationDir
}

private val String.md5String
    get() = MessageDigest.getInstance("MD5").digest(toByteArray(StandardCharsets.UTF_8)).toHexString()

private val String.onlySafeCharacters
    get() = StringBuilder().also { builder ->
        for (ch in this) {
            builder.append(
                when (ch) {
                    in '0'..'9', in 'a'..'Z', in 'A'..'Z' -> ch
                    else -> '_'
                }
            )
        }
    }.toString()
