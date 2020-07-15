/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:Suppress("PackageDirectoryMismatch") // Old package for compatibility
package org.jetbrains.kotlin.gradle.plugin.cocoapods

import org.gradle.api.Named
import org.gradle.api.NamedDomainObjectSet
import org.gradle.api.Project
import org.gradle.api.tasks.*
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension.CocoapodsDependency.PodspecLocation.*
import java.io.File
import java.net.URI

open class CocoapodsExtension(private val project: Project) {
    @get:Input
    val version: String
        get() = project.version.toString()

    /**
     * Configure authors of the pod built from this project.
     */
    @Optional
    @Input
    var authors: String? = null

    /**
     * Configure existing file `Podfile`.
     */
    @Optional
    @InputFile
    var podfile: File? = null

    @get:Input
    internal var needPodspec: Boolean = true

    /**
     * Setup plugin not to produce podspec file for cocoapods section
     */
    fun noPodspec() {
        needPodspec = false
    }

    /**
     * Configure license of the pod built from this project.
     */
    @Optional
    @Input
    var license: String? = null

    /**
     * Configure description of the pod built from this project.
     */
    @Optional
    @Input
    var summary: String? = null

    /**
     * Configure homepage of the pod built from this project.
     */
    @Optional
    @Input
    var homepage: String? = null

    @Nested
    val ios: PodspecPlatformSettings = PodspecPlatformSettings("ios")

    @Nested
    val osx: PodspecPlatformSettings = PodspecPlatformSettings("osx")

    @Nested
    val tvos: PodspecPlatformSettings = PodspecPlatformSettings("tvos")

    @Nested
    val watchos: PodspecPlatformSettings = PodspecPlatformSettings("watchos")

    /**
     * Configure framework name of the pod built from this project.
     */
    @Input
    var frameworkName: String = project.name.asValidFrameworkName()

    @get:Internal
    internal val sources = Sources()

    private val _pods = project.container(CocoapodsDependency::class.java)

    // For some reason Gradle doesn't consume the @Nested annotation on NamedDomainObjectContainer.
    @get:Nested
    protected val podsAsTaskInput: List<CocoapodsDependency>
        get() = _pods.toList()

    /**
     * Returns a list of pod dependencies.
     */
    // Already taken into account as a task input in the [podsAsTaskInput] property.
    @get:Internal
    val pods: NamedDomainObjectSet<CocoapodsDependency>
        get() = _pods

    /**
     * Add a CocoaPods dependency to the pod built from this project.
     */
    @JvmOverloads
    fun pod(name: String, version: String? = null, podspec: File? = null, moduleName: String = name.moduleName()) {
        // Empty string will lead to an attempt to create two podDownload tasks.
        // One is original podDownload and second is podDownload + pod.name
        var aPodspec = podspec
        if (podspec != null && !podspec.isDirectory) {
            project.logger.warn("Please use directory with podspec file, not podspec file itself")
            aPodspec = podspec.parentFile
        }
        require(name.isNotEmpty()) { "Please provide not empty pod name to avoid ambiguity" }
        addToPods(CocoapodsDependency(name, moduleName, version, aPodspec?.let { Path(it) }))
    }


    /**
     * Add a CocoaPods dependency to the pod built from this project.
     */
    fun pod(name: String, configure: CocoapodsDependency.() -> Unit) {
        // Empty string will lead to an attempt to create two podDownload tasks.
        // One is original podDownload and second is podDownload + pod.name
        require(name.isNotEmpty()) { "Please provide not empty pod name to avoid ambiguity" }
        val dependency = CocoapodsDependency(name, name.moduleName())
        dependency.configure()
        addToPods(dependency)
    }

    private fun String.moduleName() = split("/")[0]

    private fun addToPods(dependency: CocoapodsDependency) {
        val name = dependency.name
        check(_pods.findByName(name) == null) { "Project already has a CocoaPods dependency with name $name" }
        _pods.add(dependency)
    }

    /**
     * Add spec repositories (note that spec repository is different from usual git repository).
     * Please refer to <a href="https://guides.cocoapods.org/making/private-cocoapods.html">cocoapods documentation</a>
     * for additional information.
     * Default sources (cdn.cocoapods.org) included by default.
     */
    fun sources(configure: Sources.() -> Unit) = sources.configure()

    data class CocoapodsDependency(
        private val name: String,
        @get:Input var moduleName: String,
        @get:Optional @get:Input var version: String? = null,
        @get:Optional @get:Nested var podspec: PodspecLocation? = null
    ) : Named {
        @Input
        override fun getName(): String = name

        /**
         * Url to podspec file
         */
        fun url(url: String): PodspecLocation = Url(URI(url))

        /**
         * Path to local pod
         */
        fun path(podspecDirectory: String): PodspecLocation = Path(File(podspecDirectory))

        /**
         * Configure pod from git repository. The podspec file is expected to be in the repository root.
         */
        fun git(url: String, configure: (Git.() -> Unit)? = null): PodspecLocation {
            val git = Git(URI(url))
            if (configure != null) {
                git.configure()
            }
            return git
        }

        sealed class PodspecLocation {
            data class Url(@get:Input val url: URI) : PodspecLocation()
            data class Path(@get:InputDirectory val dir: File) : PodspecLocation()
            data class Git(
                @get:Input val url: URI,
                @get:Input @get:Optional var branch: String? = null,
                @get:Input @get:Optional var tag: String? = null,
                @get:Input @get:Optional var commit: String? = null
            ) : PodspecLocation()
        }
    }

    data class PodspecPlatformSettings(
        private val name: String,
        @get:Optional @get:Input var deploymentTarget: String? = null
    ) : Named {

        @Input
        override fun getName(): String = name
    }

    class Sources {
        @get:Internal
        internal val sourcesCollection = mutableSetOf<Source>()

        fun url(url: String) {
            sourcesCollection.add(Source(URI(url)))
        }

        @Internal
        internal fun getAll(): List<URI> {
            return sourcesCollection.map(Source::url)
        }

        class Source(@get:Input val url: URI)
    }
}