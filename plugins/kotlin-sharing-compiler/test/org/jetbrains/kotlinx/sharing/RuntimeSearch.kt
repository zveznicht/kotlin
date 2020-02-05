/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing

import junit.framework.TestCase
import org.jetbrains.kotlin.utils.PathUtil
import org.junit.*
import java.io.File

class RuntimeLibraryInClasspathTest {
    private val runtimeLibraryPath = getLibraryRuntimeJar()

    @Test
    @Ignore
    fun testRuntimeLibraryExists() {
        TestCase.assertNotNull(
            "kotlinx-serialization runtime library is not found. Make sure it is present in test classpath",
            runtimeLibraryPath
        )
    }
}

internal fun getLibraryRuntimeJar(): File? = try {
    PathUtil.getResourcePathForClass(Class.forName("kotlinx.serialization.KSerializer"))
} catch (e: ClassNotFoundException) {
    null
}
