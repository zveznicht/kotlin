/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.file

import java.io.OutputStream
import java.io.PrintWriter
import java.nio.file.Path

interface MutableFile: AbstractFile {
    fun mkdirs(): MutableFile
    fun delete(): Boolean
    fun deleteRecursively()
    fun deleteOnExitRecursively()
    fun deleteOnExit(): MutableFile
    fun writeBytes(bytes: ByteArray)
    fun appendBytes(bytes: ByteArray)

    fun writeLines(lines: Iterable<String>)
    fun writeText(text: String)
    fun createAsSymlink(target: String)
    fun outputStream(): OutputStream?
    fun printWriter(): PrintWriter
}