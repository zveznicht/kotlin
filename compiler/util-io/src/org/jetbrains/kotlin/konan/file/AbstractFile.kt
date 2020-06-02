/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.file

import java.io.BufferedReader
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

// TODO: Handle naming
// TODO: It looks like we really need to support real/zipped files separately.
//       Look at BaseLibraryAccess. It has methods for real and zipped files, so it makes sense to separate them.
interface AbstractFile {

    val path: String
    val absolutePath: String
    val absoluteFile: AbstractFile
    val canonicalPath: String
    val canonicalFile: AbstractFile

    val name: String
    val extension: String
    val parent: String
    val parentFile: AbstractFile

    val exists: Boolean
    val isDirectory: Boolean
    val isFile: Boolean
    val isAbsolute: Boolean

    val listFiles: List<AbstractFile>
    val listFilesOrEmpty: List<AbstractFile>
    fun child(name: String): AbstractFile

    fun copyTo(destination: File)
    fun recursiveCopyTo(destination: File, resetTimeAttributes: Boolean = false)

    fun readBytes(): ByteArray

    fun forEachLine(action: (String) -> Unit)

    fun bufferedReader(): BufferedReader

    fun readStrings() = mutableListOf<String>().also { list ->
        forEachLine { list.add(it) }
    }
}