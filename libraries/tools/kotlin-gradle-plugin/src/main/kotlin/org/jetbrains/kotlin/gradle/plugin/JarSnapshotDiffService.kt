/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.plugin

import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.jetbrains.kotlin.incremental.*
import java.io.File


abstract class JarSnapshotDiffService() : BuildService<JarSnapshotDiffService.Parameters> {
    abstract class Parameters : BuildServiceParameters {
        abstract val caches: IncrementalCachesManager<*>
        abstract val reporter: ICReporter
        abstract val sourceFilesExtensions: List<String>
    }

    val caches: IncrementalCachesManager<*> = parameters.caches
    val reporter: ICReporter = parameters.reporter
    val sourceFilesExtensions: List<String> = parameters.sourceFilesExtensions

    companion object {
        //Store list of changed lookups
        val diffCache: MutableMap<Pair<JarSnapshot, JarSnapshot>, DirtyFilesContainer> = mutableMapOf()
    }

//        TODO add proper sync
    @Synchronized
    fun compareJarsInternal(snapshot: JarSnapshot, newJar: JarSnapshot): DirtyFilesContainer {
//         synchronized(object) {
        return diffCache.computeIfAbsent(Pair(snapshot, newJar), {
            //TODO
            DirtyFilesContainer(caches, reporter, sourceFilesExtensions)
        })
//        }
    }



}