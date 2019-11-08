/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.targets.native.internal

import org.gradle.api.Project
import org.gradle.api.artifacts.transform.*
import org.gradle.api.file.FileCollection
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.jetbrains.kotlin.compilerRunner.KonanCompilerRunner
import org.jetbrains.kotlin.gradle.tasks.addArg
import org.jetbrains.kotlin.konan.target.CompilerOutputKind
import org.jetbrains.kotlin.konan.target.HostManager
import org.jetbrains.kotlin.konan.target.KonanTarget
import java.io.File

internal data class CachedKlib(val klib: File, val cached: File)

// TODO: Using this machinery requires Gradle 5.3 (Mar 2019) or later. Is this ok?
abstract class KotlinNativeDependencyPrecompile : TransformAction<KotlinNativeDependencyPrecompile.Parameters> {
    @get:InputArtifact
    abstract val inputArtifact: Provider<FileSystemLocation>

    @get:InputArtifactDependencies
    abstract val dependencies: FileCollection

    // It seems that there is no API allowing us to get *transformed* dependencies of a klib.
    // So we have to store a mapping between processed klibs and caches.
    // TODO: Find a better way.
    // TODO: Should it be concurrent?
    private val klibToCached = mutableMapOf<File, File>()

    private fun buildArgs(
        inputKlib: File,
        outputCache: File,
        target: KonanTarget,
        cachedDependencies: List<CachedKlib>
    ): List<String> = mutableListOf<String>().apply {
        addArg("-p", CompilerOutputKind.DYNAMIC_CACHE.name.toLowerCase())
        addArg("-target", target.name)
        addArg("-o", outputCache.absolutePath)

        add("-Xmake-cache=${inputKlib.absolutePath}")

        cachedDependencies.forEach { (klib, cache) ->
            addArg("-l", klib.absolutePath)
            add("-Xcached-library=${klib.absolutePath},${cache.absolutePath}")
        }

        // TODO: Additional flags.
    }


    private fun runCompiler(konanHome: String, args: List<String>) {
        println("Run compiler with args: ${args.joinToString(separator = " ")}")
        val compilerProcess = ProcessBuilder()
            .command(listOf("$konanHome/bin/konanc") + args)
            .inheritIO()
            .start()
        compilerProcess.waitFor()
    }

    override fun transform(outputs: TransformOutputs) {
        val input = inputArtifact.get().asFile
        val target = HostManager().targetByName(parameters.konanTarget)

        println("TTT Start transforming: $input")
        Thread.sleep(10000)

        // TODO: filter only downloaded libraries.

        val cachedDependencies = dependencies.files.map {

            val cached = klibToCached[it] ?: run {
                // TODO: Better message and replace print with a Gradle logger.
                println("Cannot transform $input: Dependency $it hasn't been transformed first.")
                return
            }
            assert(it.isAbsolute)
            assert(cached.isAbsolute)
            CachedKlib(it, cached)
        }

        // TODO: Naming?
        val outputDirectory = outputs.dir(input.nameWithoutExtension)

        // Ugly hack to provide a link task with the klib -> cache mapping.
        // We store an original klib path in a separate file next to the compile cache.
        // TODO: Find a better solution.
        val outputCache = outputDirectory.resolve(cacheFileName(target))
        val klibReference = outputDirectory.resolve(klibReferenceFileName)

        // TODO: Remove debug output
        println("TTT Precompile dependency: $input")

        // We can't use KonanCompilerRunner because Gradle doesn't provide Project instance here.
        // We can refactor KonanCompilerRunner to eliminate usages of Project but it will require some engineering efforts.
        // So, use an ad-hoc solution.
        //KonanCompilerRunner(parameters.project).run(buildArgs(input, outputCache, target, cachedDependencies))
        runCompiler(parameters.konanHome, buildArgs(input, outputCache, target, cachedDependencies))

        assert(!klibToCached.containsKey(input))
        klibToCached[input] = outputCache

        // TODO: What about interop libraries?
        klibReference.writeText(input.absolutePath)
    }

    interface Parameters : TransformParameters {
        @get:Input
        var konanTarget: String // Cannot store KonanTarget here because Gradle requires all parameters to be serializable

        @get:Input
        var konanHome: String
    }

    companion object {
        internal val klibReferenceFileName = "klib.ref"

        internal fun cacheFileName(target: KonanTarget) = cacheBaseName.toCacheName(target)

        private val cacheBaseName = "cache"
        private fun String.toCacheName(target: KonanTarget): String {
            val outputKind = CompilerOutputKind.DYNAMIC_CACHE
            val suffix = outputKind.suffix(target)
            val prefix = outputKind.prefix(target)
            return "$prefix$this$suffix"
        }
    }
}
