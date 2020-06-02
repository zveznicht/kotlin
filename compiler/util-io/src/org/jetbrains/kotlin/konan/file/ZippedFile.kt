/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.file

import java.io.BufferedReader
import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.io.File as JFile

// TODO: ZipFiles must be closed. What's with Windows?
// TODO: Better reporting for check(exists && ...)
class ZippedFile(
    val zip: ZipFile,
    pathInsideZip: String
) : AbstractFile {

    // TODO: Now we assume that this path doesn't have a trailing /. Is it ok?
    private val pathInsideZip: String
    private val isZipRoot: Boolean
    private val entry: ZipEntry?

    init {
        require(pathInsideZip.startsWith(PATH_SEPARATOR)) { "A path to a file inside zip must start with $PATH_SEPARATOR" }

        // TODO: Make it faster!
        if (pathInsideZip == "$PATH_SEPARATOR") {
            this.pathInsideZip = pathInsideZip
            this.isZipRoot = true
            this.entry = null
        } else {
            // If a zip contains a directory with path 'foo/bar', then
            // ZipFile.getEntry returns an entry for both strings "foo/bar" and "foo/bar/".
            // But prior to Java 9, ZipEntry.isDirectory returns true only for the second case.
            // https://bugs.java.com/bugdatabase/view_bug.do?bug_id=6233323
            // So we need to iterate over the entries to find an entry with a canonical name.

            val fileEntryName = pathInsideZip
                    .removePrefix("$PATH_SEPARATOR")
                    .removeSuffix("$PATH_SEPARATOR")

            val directoryEntryName = fileEntryName + PATH_SEPARATOR

            this.isZipRoot = false
            this.entry = zip.entries().asSequence().find {
                it.name == fileEntryName || it.name == directoryEntryName
            }
            this.pathInsideZip = PATH_SEPARATOR + fileEntryName
        }
    }

    override val path: String
        get() = pathInsideZip
    override val absolutePath: String
        get() = pathInsideZip
    override val absoluteFile: ZippedFile
        get() = this

    // TODO: Implement properly.
    override val canonicalPath: String
        get() = absolutePath
    override val canonicalFile: ZippedFile
        get() = absoluteFile

    // TODO: May be get rid of JFile?
    override val name: String
        get() = JFile(path).name
    override val extension: String
        get() = JFile(path).extension

    // TODO: What with Windows separator? Get rid of JFile?
    override val parent: String
        get() = JFile(path).name
    override val parentFile: ZippedFile
        get() = ZippedFile(zip, parent)

    override val exists: Boolean
        get() = isZipRoot || entry != null

    override val isDirectory: Boolean
        get() = isZipRoot || entry?.isDirectory == true
    override val isFile: Boolean
        get() = entry?.isDirectory == false
    override val isAbsolute: Boolean
        get() = true

    // TODO: Make it faster
    override val listFiles: List<ZippedFile> by lazy {
        val prefix = if (isZipRoot) "" else pathInsideZip.drop(1) + PATH_SEPARATOR

        zip.entries().asSequence().filter {
            it.name.startsWith(prefix)
        }.mapTo(mutableSetOf()) {
            it.name.removePrefix(prefix).substringBefore(PATH_SEPARATOR)
        }.map(this::child)
    }
    override val listFilesOrEmpty: List<ZippedFile>
        get() = if (exists) listFiles else emptyList()

    override fun child(name: String): ZippedFile {
        val newPath = if (pathInsideZip.endsWith(PATH_SEPARATOR)) {
            pathInsideZip + name
        } else {
            pathInsideZip + PATH_SEPARATOR + name
        }
        return ZippedFile(zip, newPath)
    }

    override fun copyTo(destination: File) {
        // TODO: What if file doesn't exist/file is a directory? What exception should be thrown?
        check(exists && isFile)

        zip.getInputStream(entry).use {
            Files.copy(it, Paths.get(URI(destination.absolutePath)))
        }
    }

    // TODO: Support resetTimeAttrs!
    override fun recursiveCopyTo(destination: File, resetTimeAttributes: Boolean) {
        // TODO: What if file doesn't exist/file is not a directory? What exception should be thrown?
        check(exists && isDirectory)

        destination.mkdirs()
        listFiles.forEach {
            val subDestination = destination.child(it.name)
            when {
                it.isDirectory -> it.recursiveCopyTo(subDestination, resetTimeAttributes)
                it.isFile -> it.copyTo(subDestination)
            }
        }
    }

    override fun readBytes(): ByteArray {
        check(exists && isFile)
        return zip.getInputStream(entry).readBytes()
    }

    override fun forEachLine(action: (String) -> Unit) {
        check(exists && isFile)
        zip.getInputStream(entry).bufferedReader().forEachLine(action)
    }

    override fun bufferedReader(): BufferedReader {
        check(exists && isFile)
        return zip.getInputStream(entry).bufferedReader()
    }

    companion object {
        const val PATH_SEPARATOR = '/'
    }

}
