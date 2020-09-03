/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.pill.mapper

import org.jetbrains.kotlin.pill.model.PDependency
import org.jetbrains.kotlin.pill.model.PProject
import java.io.File

class PlatformSourcesAttachingMapper(private val platformDir: File, private val platformBaseNumber: String) : DependencyMapper {
    override fun map(project: PProject, dependency: PDependency): List<PDependency> {
        if (dependency is PDependency.ModuleLibrary) {
            val library = dependency.library
            val asmSourcesJar = File(platformDir, "../asm-shaded-sources/asm-src-$platformBaseNumber.jar")
            val asmAllJar = File(platformDir, "lib/asm-all.jar")

            if (library.classes.any { it == asmAllJar }) {
                return listOf(dependency.copy(library = library.attachSource(asmSourcesJar)))
            }
        }

        return listOf(dependency)
    }
}