/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental

import org.jetbrains.kotlin.incremental.*
import org.jetbrains.kotlin.name.FqName

//TOT Should be in gradle daemon so move it. Only for test here
 class JarSnapshotDiffService() {
    class Parameters (
        val caches: IncrementalCachesManager<*>,
        val reporter: ICReporter,
        val sourceFilesExtensions: List<String>
    ) {}

    companion object {
        //Store list of changed lookups
        val diffCache: MutableMap<Pair<JarSnapshot, JarSnapshot>, DirtyData> = mutableMapOf()

        //TODO for test only
        fun compareJarsInternal(
            parameters: Parameters,
            snapshot: JarSnapshot, newJar: JarSnapshot
        ) =
            diffCache.computeIfAbsent(Pair(snapshot, newJar)) { (snapshotJar, actualJar) ->
                //TODO check relative path
                val symbols = mutableListOf<LookupSymbol>()
                symbols.addAll(snapshotJar.symbols.minus(actualJar.symbols))
                symbols.addAll(actualJar.symbols.minus(snapshotJar.symbols))
                val fqNames = mutableListOf<FqName>()
                fqNames.addAll(snapshotJar.fqNames.minus(actualJar.fqNames))
                fqNames.addAll(actualJar.fqNames.minus(snapshotJar.fqNames))

                DirtyData(symbols, fqNames)
//
//                DirtyFilesContainer(parameters.caches, parameters.reporter, parameters.sourceFilesExtensions)
//                    .also {
//                        //calcalute hash
//                        it.addByDirtyClasses(snapshotJar.fqNames.minus(actualJar.fqNames))
//                        it.addByDirtyClasses(actualJar.fqNames.minus(snapshotJar.fqNames))
//                        it.addByDirtySymbols(snapshotJar.symbols.minus(actualJar.symbols))
//                        it.addByDirtySymbols(actualJar.symbols.minus(snapshotJar.symbols))
//                    }
            }
    }


}