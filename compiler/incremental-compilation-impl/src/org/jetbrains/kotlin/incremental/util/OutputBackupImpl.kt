/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental.util

import org.jetbrains.kotlin.incremental.storage.OutputBackup
import java.io.File
import java.nio.file.Files
import java.util.HashMap
import java.util.HashSet

internal class OutputBackupImpl : OutputBackup {
    private val deleted = HashMap<File, ByteArray>()
    private val newFiles = HashSet<File>()

    override fun backupBeforeRemoval(file: File) {
        deleted.getOrPut(file) { file.readBytes() }
    }

    override fun newOutput(file: File) {
        newFiles.add(file)
    }

    override fun restore() {
        newFiles.forEach { Files.delete(it.toPath()) }
        deleted.forEach { (file, bytes) -> file.writeBytes(bytes) }
    }
}