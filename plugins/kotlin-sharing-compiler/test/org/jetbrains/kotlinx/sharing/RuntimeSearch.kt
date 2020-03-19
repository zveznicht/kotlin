/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing

import junit.framework.TestCase
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRoot
import org.jetbrains.kotlin.utils.PathUtil
import org.jetbrains.kotlinx.sharing.compiler.extensions.SharingPluginComponentRegistrar
import org.junit.Test
import java.io.File

class RuntimeLibraryInClasspathTest {
    private val runtimeLibraryPath = getLibraryRuntimeJar()

    @Test
    fun testRuntimeLibraryExists() {
        TestCase.assertNotNull(
            "kotlinx-sharing runtime library is not found. Make sure it is present in test classpath",
            runtimeLibraryPath
        )
    }
}

internal fun getLibraryRuntimeJar(): File? = try {
    PathUtil.getResourcePathForClass(Class.forName("kotlinx.sharing.Shared"))
} catch (e: ClassNotFoundException) {
    null
}

fun KotlinCoreEnvironment.setupForSharingPlugin() {
    SharingPluginComponentRegistrar.registerExtensions(project)
    updateClasspath(listOf(JvmClasspathRoot(getLibraryRuntimeJar()!!)))
}