/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.file

import org.jetbrains.kotlin.util.removeSuffixIfPresent
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.RandomAccessFile
import java.lang.Exception
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributeView
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime

data class File constructor(internal val javaPath: Path) : MutableFile {
    constructor(parent: Path, child: String): this(parent.resolve(child))
    constructor(parent: File, child: String): this(parent.javaPath.resolve(child))
    constructor(parent: File, child: File): this(parent.javaPath.resolve(child.javaPath))
    constructor(path: String): this(Paths.get(path))
    constructor(parent: String, child: String): this(Paths.get(parent, child))

    override val path: String
        get() = javaPath.toString()
    override val absolutePath: String
        get() = javaPath.toAbsolutePath().toString()
    override val absoluteFile: File
        get() = File(absolutePath)
    override val canonicalPath: String
        get() = javaPath.toFile().canonicalPath
    override val canonicalFile: File
        get() = File(canonicalPath)

    override val name: String
        get() = javaPath.fileName.toString().removeSuffixIfPresent("/") // https://bugs.java.com/bugdatabase/view_bug.do?bug_id=8153248
    override val extension: String
        get() = name.substringAfterLast('.', "")
    override val parent: String
        get() = javaPath.parent.toString()
    override val parentFile: File
        get() = File(javaPath.parent)

    override val exists
        get() = Files.exists(javaPath)
    override val isDirectory
        get() = Files.isDirectory(javaPath)
    override val isFile
        get() = Files.isRegularFile(javaPath)
    override val isAbsolute
        get() = javaPath.isAbsolute
    override val listFiles: List<File>
        get() = Files.newDirectoryStream(javaPath).use { stream -> stream.map(::File) }
    override val listFilesOrEmpty: List<File>
        get() = if (exists) listFiles else emptyList()

    override fun child(name: String) = File(this, name)

    override fun copyTo(destination: File) {
        Files.copy(javaPath, destination.javaPath, StandardCopyOption.REPLACE_EXISTING)
    }

    override fun recursiveCopyTo(destination: File, resetTimeAttributes: Boolean) {
        val sourcePath = javaPath
        val destPath = destination.javaPath
        sourcePath.recursiveCopyTo(destPath, resetTimeAttributes = resetTimeAttributes)
    }

    override fun mkdirs(): File = this.also { Files.createDirectories(javaPath) }
    override fun delete(): Boolean = Files.deleteIfExists(javaPath)
    override fun deleteRecursively(): Unit = postorder { Files.delete(it) }
    override fun deleteOnExitRecursively(): Unit = preorder { File(it).deleteOnExit() }

    fun preorder(task: (Path) -> Unit) {
        if (!this.exists) return

        Files.walkFileTree(javaPath, object: SimpleFileVisitor<Path>() {
            override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult {
                task(file!!)
                return FileVisitResult.CONTINUE
            }

            override fun preVisitDirectory(dir: Path?, attrs: BasicFileAttributes?): FileVisitResult {
                task(dir!!)
                return FileVisitResult.CONTINUE
            }
        })

    }

    fun postorder(task: (Path) -> Unit) {
        if (!this.exists) return

        Files.walkFileTree(javaPath, object: SimpleFileVisitor<Path>() {
            override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult {
                task(file!!)
                return FileVisitResult.CONTINUE
            }

            override fun postVisitDirectory(dir: Path?, exc: java.io.IOException?): FileVisitResult {
                task(dir!!)
                return FileVisitResult.CONTINUE
            }
        })
    }

    fun map(mode: FileChannel.MapMode = FileChannel.MapMode.READ_ONLY,
            start: Long = 0, size: Long = -1): MappedByteBuffer {
        val file = RandomAccessFile(path,
                                    if (mode == FileChannel.MapMode.READ_ONLY) "r" else "rw")
        val fileSize = if (mode == FileChannel.MapMode.READ_ONLY)
            file.length() else size.also { assert(size != -1L) }
        val channel = file.channel
        return channel.map(mode, start, fileSize).also { channel.close() }
    }

    override fun deleteOnExit(): File {
        // Works only on the default file system, 
        // but that's okay for now.
        javaPath.toFile().deleteOnExit()
        return this // Allow streaming.
    }

    override fun readBytes() = Files.readAllBytes(javaPath)
    override fun writeBytes(bytes: ByteArray) {
        Files.write(javaPath, bytes)
    }

    override fun appendBytes(bytes: ByteArray) {
        Files.write(javaPath, bytes, StandardOpenOption.APPEND)
    }

    override fun writeLines(lines: Iterable<String>) {
        Files.write(javaPath, lines)
    }

    override fun writeText(text: String): Unit = writeLines(listOf(text))

    override fun forEachLine(action: (String) -> Unit) {
        Files.lines(javaPath).use { lines ->
            lines.forEach { action(it) }
        }
    }

    override fun createAsSymlink(target: String) {
        val targetPath = Paths.get(target)
        if (Files.isSymbolicLink(this.javaPath) && Files.readSymbolicLink(javaPath) == targetPath) {
            return
        }
        Files.createSymbolicLink(this.javaPath, targetPath)
    }

    override fun toString() = path

    // TODO: Consider removeing these after konanazing java.util.Properties.
    override fun bufferedReader() = Files.newBufferedReader(javaPath)
    override fun outputStream() = Files.newOutputStream(javaPath)
    override fun printWriter() = javaPath.toFile().printWriter()

    companion object {
        val userDir
            get() = File(System.getProperty("user.dir"))

        val userHome
            get() = File(System.getProperty("user.home"))

        val javaHome
            get() = File(System.getProperty("java.home"))
        val pathSeparator = java.io.File.pathSeparator
        val separator = java.io.File.separator
    }

    override fun readStrings() = mutableListOf<String>().also { list -> forEachLine{list.add(it)}}

    override fun equals(other: Any?): Boolean {
        val otherFile = other as? File ?: return false
        return otherFile.javaPath.toAbsolutePath() == javaPath.toAbsolutePath()
    }

    override fun hashCode() = javaPath.toAbsolutePath().hashCode()
}

fun String.File(): File = File(this)
fun Path.File(): File = File(this)

fun createTempFile(name: String, suffix: String? = null)
        = Files.createTempFile(name, suffix).File()
fun createTempDir(name: String): File
        = Files.createTempDirectory(name).File()

fun Path.recursiveCopyTo(destPath: Path, resetTimeAttributes: Boolean = false) {
    val sourcePath = this
    Files.walk(sourcePath).forEach next@ { oldPath ->

        val relative = sourcePath.relativize(oldPath)
        val destFs = destPath.getFileSystem()
        // We are copying files between file systems, 
        // so pass the relative path through the String.
        val newPath = destFs.getPath(destPath.toString(), relative.toString())

        // File systems don't allow replacing an existing root.
        if (newPath == newPath.getRoot()) return@next
        if (Files.isDirectory(newPath)) {
            Files.createDirectories(newPath)
        } else {
            Files.copy(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING)
        }
        if (resetTimeAttributes) {
            val zero = FileTime.fromMillis(0)
            Files.getFileAttributeView(newPath, BasicFileAttributeView::class.java).setTimes(zero, zero, zero);
        }
    }
}

fun bufferedReader(errorStream: InputStream?) = BufferedReader(InputStreamReader(errorStream))

// stdlib `use` function adapted for AutoCloseable.
inline fun <T : AutoCloseable?, R> T.use(block: (T) -> R): R {
    var closed = false
    try {
        return block(this)
    } catch (e: Exception) {
        closed = true
        try {
            this?.close()
        } catch (closeException: Exception) {
        }
        throw e
    } finally {
        if (!closed) {
            this?.close()
        }
    }
}
