/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental.util

import java.io.File
import java.util.HashMap
import java.util.HashSet

internal class DirectoryBackup(private val dir: File) {
    private val prev: Map<File, ByteArray>

    init {
        prev = HashMap<File, ByteArray>().apply {
            dir.walk().forEach { f ->
                if (f.isFile) {
                    put(f, f.readBytes())
                }
            }
        }
    }

    fun restore() {
        dir.deleteRecursively()
        prev.keys.mapNotNullTo(HashSet()) { it.parentFile }.forEach { it.mkdirs() }
        for ((file, bytes) in prev) {
            file.writeBytes(bytes)
        }
    }
}