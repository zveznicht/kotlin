/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.pill

import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import org.jetbrains.kotlin.pill.artifact.ArtifactDependencyMapper
import org.jetbrains.kotlin.pill.artifact.ArtifactGenerator
import org.jetbrains.kotlin.pill.model.*
import org.jetbrains.kotlin.pill.mapper.*
import shadow.org.jdom2.input.SAXBuilder
import shadow.org.jdom2.*
import shadow.org.jdom2.output.Format
import shadow.org.jdom2.output.XMLOutputter
import java.io.File
import java.util.*

const val EMBEDDED_CONFIGURATION_NAME = "embedded"

class JpsCompatiblePluginTasks(private val rootProject: Project, private val platformDir: File, private val resourcesDir: File) {
    private lateinit var projectDir: File
    private lateinit var platformVersion: String
    private lateinit var platformBaseNumber: String
    private lateinit var intellijCoreDir: File
    private var isAndroidStudioPlatform: Boolean = false

    private fun initEnvironment(project: Project) {
        projectDir = project.projectDir
        platformVersion = project.extensions.extraProperties.get("versions.intellijSdk").toString()
        platformBaseNumber = platformVersion.substringBefore(".", "").takeIf { it.isNotEmpty() }
            ?: platformVersion.substringBefore("-", "").takeIf { it.isNotEmpty() }
                    ?: error("Invalid platform version: $platformVersion")
        intellijCoreDir = File(platformDir.parentFile.parentFile.parentFile, "intellij-core")
        isAndroidStudioPlatform = project.extensions.extraProperties.has("versions.androidStudioRelease")
    }

    fun pill() {
        initEnvironment(rootProject)

        val isKombo = System.getProperty("pill.is.kombo", "false") == "true"

        val variantOptionValue = System.getProperty("pill.variant", "base").toUpperCase()
        val variant = PillExtensionMirror.Variant.values().firstOrNull { it.name == variantOptionValue }
            ?: run {
                rootProject.logger.error("Invalid variant name: $variantOptionValue")
                return
            }

        rootProject.logger.lifecycle("Pill: Setting up project for the '${variant.name.toLowerCase()}' variant...")

        val modulePrefix = System.getProperty("pill.module.prefix", "")
        val modelParser = ModelParser(variant, modulePrefix)

        val libraryDependencyPatcher = if (isKombo) {
            val mappingsPath = System.getProperty("pill.kombo.mappings.dir", null) ?: error("Mappings dir not specified")
            val intellijPath = System.getProperty("pill.kombo.intellij.dir", null) ?: error("IntelliJ dir not specified")
            KomboDependencyMapper(rootProject, File(mappingsPath), File(intellijPath))
        } else {
            DistLibraryDependencyMapper(rootProject)
        }

        val dependencyMappers = listOf(
            libraryDependencyPatcher,
            PlatformSourcesAttachingMapper(platformDir, platformBaseNumber),
            AsmSourcesAttachingMapper(platformDir, platformBaseNumber)
        )

        val jpsProject = modelParser.parse(rootProject)
            .mapDependencies(dependencyMappers)
            .copy(libraries = libraryDependencyPatcher.projectLibraries)

        val files = render(jpsProject)

        removeExistingIdeaLibrariesAndModules()
        removeJpsAndPillRunConfigurations()

        if (!isKombo) {
            removeAllArtifactConfigurations()

            if (variant.includes.contains(PillExtensionMirror.Variant.IDE)) {
                val artifactDependencyMapper = object : ArtifactDependencyMapper {
                    override fun map(module: PModule, dependency: PDependency): List<PDependency> {
                        val result = mutableListOf<PDependency>()

                        for (mappedDependency in mapDependency(jpsProject, module, dependency, dependencyMappers)) {
                            result += mappedDependency

                            if (mappedDependency is PDependency.Module) {
                                val mappedModule = jpsProject.modules.find { it.name == mappedDependency.name }
                                if (mappedModule != null) {
                                    result += mappedModule.embeddedDependencies
                                }
                            }
                        }

                        return result
                    }
                }

                ArtifactGenerator(artifactDependencyMapper, rootProject, jpsProject).generateKotlinPluginArtifact().write()
            }
        }

        copyRunConfigurations(variant)
        setOptionsForDefaultJunitRunConfiguration(rootProject)

        files.forEach { it.write() }
    }

    fun unpill() {
        initEnvironment(rootProject)

        removeExistingIdeaLibrariesAndModules()
        removeJpsAndPillRunConfigurations()
        removeAllArtifactConfigurations()
    }

    private fun removeExistingIdeaLibrariesAndModules() {
        File(projectDir, ".idea/libraries").deleteRecursively()
        File(projectDir, ".idea/modules").deleteRecursively()
    }

    private fun removeJpsAndPillRunConfigurations() {
        File(projectDir, ".idea/runConfigurations")
            .walk()
            .filter { (it.name.startsWith("JPS_") || it.name.startsWith("Pill_")) && it.extension.toLowerCase() == "xml" }
            .forEach { it.delete() }
    }

    private fun removeAllArtifactConfigurations() {
        File(projectDir, ".idea/artifacts")
            .walk()
            .filter { it.extension.toLowerCase() == "xml" }
            .forEach { it.delete() }
    }

    private fun copyRunConfigurations(variant: PillExtensionMirror.Variant) {
        val targetDir = File(projectDir, ".idea/runConfigurations")
        val platformDirProjectRelative = "\$PROJECT_DIR\$/" + platformDir.toRelativeString(projectDir)
        val additionalIdeaArgs = if (isAndroidStudioPlatform) "-Didea.platform.prefix=AndroidStudio" else ""

        targetDir.mkdirs()

        fun substitute(text: String): String {
            return text
                .replace("\$IDEA_HOME_PATH\$", platformDirProjectRelative)
                .replace("\$ADDITIONAL_IDEA_ARGS\$", additionalIdeaArgs)
        }

        for (variantToCopy in variant.includes) {
            val runConfigurationsDir = resourcesDir.resolve("runConfigurations").resolve(variantToCopy.name)
            (runConfigurationsDir.listFiles() ?: emptyArray())
                .filter { it.extension == "xml" }
                .map { it.name to substitute(it.readText()) }
                .forEach { File(targetDir, it.first).writeText(it.second) }
        }
    }

    /*
        This sets a proper (project root) working directory and a "idea.home.path" property to the default JUnit configuration,
        so one does not need to make these changes manually.
     */
    private fun setOptionsForDefaultJunitRunConfiguration(project: Project) {
        val workspaceFile = File(projectDir, ".idea/workspace.xml")
        if (!workspaceFile.exists()) {
            project.logger.warn("${workspaceFile.name} does not exist, JUnit default run configuration was not modified")
            return
        }

        val document = SAXBuilder().build(workspaceFile)
        val rootElement = document.rootElement

        fun Element.getOrCreateChild(name: String, vararg attributes: Pair<String, String>): Element {
            for (child in getChildren(name)) {
                if (attributes.all { (attribute, value) -> child.getAttributeValue(attribute) == value }) {
                    return child
                }
            }

            return Element(name).apply {
                for ((attributeName, value) in attributes) {
                    setAttribute(attributeName, value)
                }

                this@getOrCreateChild.addContent(this@apply)
            }
        }

        val platformDirProjectRelative = "\$PROJECT_DIR\$/" + platformDir.toRelativeString(projectDir)

        val runManagerComponent = rootElement.getOrCreateChild("component", "name" to "RunManager")

        val junitConfiguration = runManagerComponent.getOrCreateChild(
            "configuration",
            "default" to "true",
            "type" to "JUnit",
            "factoryName" to "JUnit"
        )

        val kotlinJunitConfiguration = runManagerComponent.getOrCreateChild(
            "configuration",
            "default" to "true",
            "type" to "KotlinJUnit",
            "factoryName" to "Kotlin JUnit"
        )

        fun Element.applyJUnitTemplate() {
            getOrCreateChild("option", "name" to "WORKING_DIRECTORY").setAttribute("value", "file://\$PROJECT_DIR\$")
            getOrCreateChild("option", "name" to "VM_PARAMETERS").also { vmParams ->
                var options = vmParams.getAttributeValue("value", "")
                    .split(' ')
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }

                fun addOrReplaceOptionValue(name: String, value: Any?) {
                    val optionsWithoutNewValue = options.filter { !it.startsWith("-D$name=") }
                    options = if (value == null) optionsWithoutNewValue else (optionsWithoutNewValue + listOf("-D$name=$value"))
                }

                if (options.none { it == "-ea" }) {
                    options = options + "-ea"
                }

                addOrReplaceOptionValue("idea.home.path", platformDirProjectRelative)
                addOrReplaceOptionValue("ideaSdk.androidPlugin.path", "$platformDirProjectRelative/plugins/android/lib")
                addOrReplaceOptionValue("use.jps", "true")
                addOrReplaceOptionValue("kotlinVersion", project.rootProject.extra["kotlinVersion"].toString())

                val isAndroidStudioBunch = project.findProperty("versions.androidStudioRelease") != null
                addOrReplaceOptionValue("idea.platform.prefix", if (isAndroidStudioBunch) "AndroidStudio" else null)

                val androidJarPath = project.configurations.findByName("androidJar")?.singleFile
                val androidSdkPath = project.configurations.findByName("androidSdk")?.singleFile

                if (androidJarPath != null && androidSdkPath != null) {
                    addOrReplaceOptionValue("android.sdk", "\$PROJECT_DIR\$/" + androidSdkPath.toRelativeString(projectDir))
                    addOrReplaceOptionValue("android.jar", "\$PROJECT_DIR\$/" + androidJarPath.toRelativeString(projectDir))
                }

                vmParams.setAttribute("value", options.joinToString(" "))
            }
        }

        junitConfiguration.applyJUnitTemplate()
        kotlinJunitConfiguration.applyJUnitTemplate()

        val output = XMLOutputter().also {
            @Suppress("UsePropertyAccessSyntax")
            it.format = Format.getPrettyFormat().apply {
                setEscapeStrategy { c -> Verifier.isHighSurrogate(c) || c == '"' }
                setIndent("  ")
                setTextMode(Format.TextMode.TRIM)
                setOmitEncoding(false)
                setOmitDeclaration(false)
            }
        }

        val postProcessedXml = output.outputString(document)
            .replace("&#x22;", "&quot;")
            .replace("&#xA;", "&#10;")
            .replace("&#xC;", "&#13;")

        workspaceFile.writeText(postProcessedXml)
    }

    private fun PProject.mapDependencies(mappers: List<DependencyMapper>): PProject {
        fun mapRoot(module: PModule, root: POrderRoot): List<POrderRoot> {
            val dependencies = mapDependency(this, module, root.dependency, mappers)
            return dependencies.map { root.copy(dependency = it) }
        }

        val modules = this.modules.map { module ->
            val newOrderRoots = module.orderRoots.flatMap { root -> mapRoot(module, root) }.distinct()
            module.copy(orderRoots = newOrderRoots)
        }

        return this.copy(modules = modules)
    }

    private fun mapDependency(
        project: PProject,
        module: PModule,
        dependency: PDependency,
        mappers: List<DependencyMapper>
    ): List<PDependency> {
        var dependencies = listOf(dependency)
        for (mapper in mappers) {
            val newDependencies = mutableListOf<PDependency>()
            for (dep in dependencies) {
                newDependencies += mapper.map(project, module, dep)
            }
            dependencies = newDependencies
        }

        return dependencies
    }
}