/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental

import org.jetbrains.kotlin.build.report.ICReporter
import org.jetbrains.kotlin.name.FqName
import java.io.File

class DirtyFilesContainer(
    private val caches: IncrementalCachesManager<*>,
    private val reporter: ICReporter,
    private val sourceFilesExtensions: List<String>
) {
    private val myDirtyKotlinFiles = HashSet<File>()
    private val myDirtyJavaFiles = HashSet<File>()

    val kotlinFiles: Set<File>
        get() = myDirtyKotlinFiles

    val javaFiles: Set<File>
        get() = myDirtyJavaFiles

    fun add(files: Iterable<File>, reason: String?) {
        for (file in files) {
            if (file.isJavaFile()) {
                myDirtyJavaFiles.add(file)
            } else if (file.isKotlinFile(sourceFilesExtensions)) {
                myDirtyKotlinFiles.add(file)
            }
        }
        if (reason != null) {
            reporter.reportMarkDirty(files, reason)
        }
    }

    fun addByDirtySymbols(lookupSymbols: Collection<LookupSymbol>) {
        if (lookupSymbols.isEmpty()) return

        val dirtyFilesFromLookups = mapLookupSymbolsToFiles(caches.lookupCache, lookupSymbols, reporter)
        // reason is null, because files are reported in mapLookupSymbolsToFiles
        add(dirtyFilesFromLookups, reason = null)
    }

    fun addByDirtyClasses(dirtyClassesFqNames: Collection<FqName>) {
        if (dirtyClassesFqNames.isEmpty()) return

        val fqNamesWithSubtypes = dirtyClassesFqNames.flatMap {
            withSubtypes(
                it,
                listOf(caches.platformCache)
            )
        }
        val dirtyFilesFromFqNames =
            mapClassesFqNamesToFiles(listOf(caches.platformCache), fqNamesWithSubtypes, reporter)
        // reason is null, because files are reported in mapClassesFqNamesToFiles
        add(dirtyFilesFromFqNames, reason = null)
    }
}