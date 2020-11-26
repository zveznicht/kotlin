/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental

import org.jetbrains.kotlin.incremental.*
import java.io.File

//TOT Should be in gradle daemon so move it. Only for test here
 class JarSnapshotDiffService() {
    class Parameters (
        val caches: IncrementalCachesManager<*>,
        val reporter: ICReporter,
        val sourceFilesExtensions: List<String>
    ) {}

    companion object {
        //Store list of changed lookups
        val diffCache: MutableMap<Pair<JarSnapshot, JarSnapshot>, DirtyFilesContainer> = mutableMapOf()

        //TODO for test only
        fun compareJarsInternal(
            parameters: Parameters,
            snapshot: JarSnapshot, newJar: JarSnapshot
        ) =
            diffCache.computeIfAbsent(Pair(snapshot, newJar)) { (snapshotJar, actualJar) ->
                DirtyFilesContainer(parameters.caches, parameters.reporter, parameters.sourceFilesExtensions)
                    .also {
                        it.addByDirtyClasses(snapshotJar.fqNames.minus(actualJar.fqNames))
                        it.addByDirtyClasses(actualJar.fqNames.minus(snapshotJar.fqNames))
                        it.addByDirtySymbols(snapshotJar.symbols.minus(actualJar.symbols))
                        it.addByDirtySymbols(actualJar.symbols.minus(snapshotJar.symbols))
                    }
            }
    }


}