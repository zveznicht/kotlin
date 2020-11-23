/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental

import org.jetbrains.kotlin.incremental.BuildDiffsStorage.Companion.readFqNames
import org.jetbrains.kotlin.incremental.BuildDiffsStorage.Companion.readLookups
import org.jetbrains.kotlin.incremental.BuildDiffsStorage.Companion.writeFqNames
import org.jetbrains.kotlin.incremental.BuildDiffsStorage.Companion.writeLookups
import org.jetbrains.kotlin.name.FqName
import java.io.*

class JarSnapshot (val symbols: Set<LookupSymbol>,
                   val fqNames: Set<FqName>) {

    companion object {
        fun ObjectInputStream.readJarSnapshot(): JarSnapshot {
            val symbolsResult = readLookups().toSet()
            val fqNamesResult = readFqNames().toSet()
            return JarSnapshot(symbolsResult, fqNamesResult)
        }

        fun ObjectOutputStream.writeJarSnapshot(jarSnapshot: JarSnapshot) {
            writeLookups(jarSnapshot.symbols)
            writeFqNames(jarSnapshot.fqNames)
        }

        fun write(buildInfo: JarSnapshot, file: File) {
            ObjectOutputStream(FileOutputStream(file)).use {
                it.writeJarSnapshot(buildInfo)
            }
        }

        fun read (file: File) : JarSnapshot {
            ObjectInputStream(FileInputStream(file)).use {
                return it.readJarSnapshot()
            }
        }
    }
}