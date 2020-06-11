/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.file

import java.net.URI
import java.nio.file.*
import java.util.concurrent.ConcurrentHashMap
import java.util.zip.ZipOutputStream

// TODO: Edit the comment below.
// Zip filesystem provider creates a singleton zip FileSystem.
// So newFileSystem can return an already existing one.
// And, more painful, closing the filesystem could close it for another consumer thread.
fun File.zipFileSystem(create: Boolean = false): FileSystem {
    if (create && !this.exists) {
        // Open and close a ZipOutputStream to create the zip file.
        ZipOutputStream(Files.newOutputStream(this.toPath(), StandardOpenOption.CREATE)).use {}
    }

    return FileSystems.newFileSystem(this.toPath(), null)
}

fun FileSystem.file(file: File) = File(this.getPath(file.path))

fun FileSystem.file(path: String) = File(this.getPath(path))

private fun File.toPath() = Paths.get(this.path)

fun File.zipDirAs(unixFile: File) {
    unixFile.withMutableZipFileSystem {
        // Time attributes are not preserved to ensure that the output zip file bytes deterministic for a fixed set of
        // input files.
        this.recursiveCopyTo(it.file("/"), resetTimeAttributes = true)
    }
}

fun Path.unzipTo(directory: Path) {
    val zipUri = URI.create("jar:" + this.toUri())
    FileSystems.newFileSystem(zipUri, emptyMap<String, Any?>(), null).use { zipfs ->
        val zipPath = zipfs.getPath("/")
        zipPath.recursiveCopyTo(directory)
    }
}

fun <T> File.withZipFileSystem(mutable: Boolean = false, action: (FileSystem) -> T): T {
    return this.zipFileSystem(mutable).use(action)
}

fun <T> File.withZipFileSystem(action: (FileSystem) -> T): T = this.withZipFileSystem(false, action)

fun <T> File.withMutableZipFileSystem(action: (FileSystem) -> T): T = this.withZipFileSystem(true, action)