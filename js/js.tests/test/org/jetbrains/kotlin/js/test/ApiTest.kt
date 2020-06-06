/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.js.test

import org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.cli.js.messageCollectorLogger
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.config.CommonConfigurationKeys
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.ir.backend.js.MainModule
import org.jetbrains.kotlin.ir.backend.js.jsResolveLibraries
import org.jetbrains.kotlin.ir.backend.js.loadIr
import org.jetbrains.kotlin.js.config.JSConfigurationKeys
import org.jetbrains.kotlin.js.config.JsConfig
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.test.KotlinTestUtils
import org.jetbrains.kotlin.test.KotlinTestWithEnvironment
import org.jetbrains.kotlin.test.util.RecursiveDescriptorComparator
import org.jetbrains.kotlin.test.util.RecursiveDescriptorComparator.RECURSIVE_ALL
import java.io.File

private val OVERWRITE_EXPECTED_OUTPUT = System.getProperty("overwrite.output")?.toBoolean() ?: false // use -Doverwrite.output=true

class ApiTest : KotlinTestWithEnvironment() {

    fun testOutputNotOverwritten() {
        assertFalse(
            "Attention! Expected output is being overwritten! Please set OVERWRITE_EXPECTED_OUTPUT to false.",
            OVERWRITE_EXPECTED_OUTPUT
        )
    }

    fun testStdlib() {
        stdlibModuleDescriptor.packagesSerialized().checkRecursively("js/js.translator/testData/api/stdlib")
    }

    fun testIrStdlib() {
        irStdlibModuleDescriptor.packagesSerialized().checkRecursively("js/js.translator/testData/api/stdlib-ir")
    }

    fun testCompareApi() {
        diffPackages(
            stdlibModuleDescriptor.packagesSerialized().excludePackages(onlyInStdlib),
            irStdlibModuleDescriptor.packagesSerialized().excludePackages(onlyInStdlibIr)
        ).checkRecursively("js/js.translator/testData/api/stdlib-diff")
    }

    private val stdlibModuleDescriptor: ModuleDescriptor
        get() {
            val project = environment.project
            val configuration = environment.configuration

            configuration.put(CommonConfigurationKeys.MODULE_NAME, "test")
            configuration.put(JSConfigurationKeys.LIBRARIES, JsConfig.JS_STDLIB)

            val config = JsConfig(project, configuration)

            return config.moduleDescriptors.single()
        }

    private val irStdlibModuleDescriptor: ModuleDescriptor
        get() {
            val fullRuntimeKlib: String = System.getProperty("kotlin.js.full.stdlib.path")

            val resolvedLibraries =
                jsResolveLibraries(listOf(File(fullRuntimeKlib).absolutePath), messageCollectorLogger(MessageCollector.NONE))

            val project = environment.project
            val configuration = environment.configuration

            return loadIr(
                project,
                MainModule.Klib(resolvedLibraries.getFullList().single()),
                AnalyzerWithCompilerReport(configuration),
                configuration,
                resolvedLibraries,
                listOf()
            ).module.descriptor
        }

    private val onlyInStdlib = setOf(
        "org.khronos.webgl",
        "org.w3c.css.masking",
        "org.w3c.dom",
        "org.w3c.dom.clipboard",
        "org.w3c.dom.css",
        "org.w3c.dom.encryptedmedia",
        "org.w3c.dom.events",
        "org.w3c.dom.mediacapture",
        "org.w3c.dom.mediasource",
        "org.w3c.dom.parsing",
        "org.w3c.dom.pointerevents",
        "org.w3c.dom.svg",
        "org.w3c.dom.url",
        "org.w3c.fetch",
        "org.w3c.files",
        "org.w3c.notifications",
        "org.w3c.performance",
        "org.w3c.workers",
        "org.w3c.xhr"
    )

    private val onlyInStdlibIr = emptySet<String>()

    private fun diffPackages(left: Map<FqName, String>, right: Map<FqName, String>): Map<FqName, String> {
        val allNames = (left.keys + right.keys).toSet()

        return allNames.mapNotNull { name ->
            val a = left[name]
            val b = right[name]

            if (a == null) {
                error("Only in right: $name")
            } else if (b == null) {
                error("Only in left: $name")
            } else {
                val d = diff(a, b)
                if (d.isBlank()) null else name to d
            }
        }.toMap()
    }

    private fun Map<FqName, String>.excludePackages(p: Set<String>): Map<FqName, String> {
        val extraExcludes = p - keys.map { it.asString() }
        assertTrue("Extra excludes found: $extraExcludes", extraExcludes.isEmpty())
        return this.filterKeys { it.asString() !in p }
    }

    private fun diff(a: String, b: String): String {
        val aLines = a.lines()
        val bLines = b.lines()

        val dx = Array(aLines.size + 1) { IntArray(bLines.size + 1) }
        val dy = Array(aLines.size + 1) { IntArray(bLines.size + 1) }
        val c = Array(aLines.size + 1) { IntArray(bLines.size + 1) }

        for (i in 0..aLines.size) {
            c[i][0] = i
            dx[i][0] = -1
        }
        for (j in 0..bLines.size) {
            c[0][j] = j
            dy[0][j] = -1
        }
        for (i in 1..aLines.size) {
            for (j in 1..bLines.size) {
                if (c[i - 1][j] <= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j] + 1
                    dx[i][j] = -1
                } else {
                    c[i][j] = c[i][j - 1] + 1
                    dy[i][j] = -1
                }
                if (aLines[i - 1] == bLines[j - 1] && c[i - 1][j - 1] < c[i][j]) {
                    c[i][j] = c[i - 1][j - 1]
                    dx[i][j] = -1
                    dy[i][j] = -1
                }
            }
        }

        val result = mutableListOf<String>()

        var x = aLines.size
        var y = bLines.size
        var hasDiff = false

        while (x != 0 && y != 0) {
            val tdx = dx[x][y]
            val tdy = dy[x][y]

            if (tdx != 0) {
                if (tdy != 0) {
                    if (hasDiff) {
                        result += "--- ${x + 1},${y + 1} ---"
                        hasDiff = false
                    }
                } else {
                    result += "- ${aLines[x - 1]}"
                    hasDiff = true
                }
            } else if (tdy != 0) {
                result += "+ ${bLines[y - 1]}"
                hasDiff = true
            }

            x += tdx
            y += tdy
        }

        return result.reversed().joinToString("\n", postfix = "\n")
    }

    private fun String.listFiles(): Array<File> {
        val dirFile = File(this)
        assertTrue("Directory does not exist: ${dirFile.absolutePath}", dirFile.exists())
        assertTrue("Not a directory: ${dirFile.absolutePath}", dirFile.isDirectory)
        return dirFile.listFiles()!!
    }

    private fun String.cleanDir() {
        listFiles().forEach { it.delete() }
    }

    private fun Map<FqName, String>.checkRecursively(dir: String) {


        if (OVERWRITE_EXPECTED_OUTPUT) {
            dir.cleanDir()
        }

        val files = dir.listFiles().map { it.name }.toMutableSet()
        entries.forEach { (fqName, serialized) ->
            val fileName = (if (fqName.isRoot) "ROOT" else fqName.asString()) + ".kt"
            files -= fileName

            if (OVERWRITE_EXPECTED_OUTPUT) {
                File("$dir/$fileName").writeText(serialized)
            }
            KotlinTestUtils.assertEqualsToFile(File("$dir/$fileName"), serialized)
        }

        assertTrue("Extra files found: $files", files.isEmpty())
    }

    private fun ModuleDescriptor.packagesSerialized(): Map<FqName, String> {
        return allPackages().mapNotNull { fqName -> getPackage(fqName).serialize()?.let { fqName to it } }.toMap()
    }

    private fun ModuleDescriptor.allPackages(): Collection<FqName> {
        val result = mutableListOf<FqName>()

        fun impl(pkg: FqName) {
            result += pkg

            getSubPackagesOf(pkg) { true }.forEach { impl(it) }
        }

        impl(FqName.ROOT)

        return result
    }

    private fun PackageViewDescriptor.serialize(): String? {
        val comparator = RecursiveDescriptorComparator(RECURSIVE_ALL.filterRecursion {
            when {
                it is MemberDescriptor && it.isExpect -> false
                it is DeclarationDescriptorWithVisibility && !it.visibility.isPublicAPI -> false
                it is CallableMemberDescriptor && !it.kind.isReal -> false
                it is PackageViewDescriptor -> false
                else -> true
            }
        }.renderDeclarationsFromOtherModules(true))

        val serialized = comparator.serializeRecursively(this).trim()

        if (serialized.count { it == '\n' } <= 1) return null

        return serialized
    }

    override fun createEnvironment(): KotlinCoreEnvironment {
        return KotlinCoreEnvironment.createForTests(TestDisposable(), CompilerConfiguration(), EnvironmentConfigFiles.JS_CONFIG_FILES)
    }
}