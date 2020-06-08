/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.file

import org.jetbrains.kotlin.konan.file.ZippedFile.Companion.PATH_SEPARATOR
import org.jetbrains.kotlin.konan.file.ZippedFileTree.Node.EntryNode
import java.io.BufferedReader
import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributeView
import java.nio.file.attribute.FileTime
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

class ZippedFile private constructor(
        private val fileTree: ZippedFileTree,
        pathInsideZip: String
): AbstractFile {

    constructor(parent: ZippedFile, child: String): this(parent.fileTree, parent.normalizedPathInsideZip + PATH_SEPARATOR + child)
    constructor(zip: ZipFile, pathInsideZip: String): this(ZippedFileTree(zip), pathInsideZip)

    // A normalized path inside zip:
    //  - starts with '/',
    //  - has no trailing '/',
    //  - has all sequences like '////' replaced with a single '/'.
    private val normalizedPathInsideZip: String = pathInsideZip
            .splitToSequence(PATH_SEPARATOR)
            .filter { it.isNotEmpty() }
            .joinToString(prefix = "$PATH_SEPARATOR", separator = "$PATH_SEPARATOR")

    private val fileTreeNode: ZippedFileTree.Node? by lazy {
        fileTree.getNode(normalizedPathInsideZip)
    }

    private val zip: ZipFile
        get() = fileTree.zip

    override val path: String
        get() = normalizedPathInsideZip
    override val absolutePath: String
        get() = normalizedPathInsideZip
    override val absoluteFile: ZippedFile
        get() = this

    override val canonicalPath: String
        get() = absolutePath
    override val canonicalFile: ZippedFile
        get() = absoluteFile

    override val name: String
        get() = normalizedPathInsideZip.substringAfterLast(PATH_SEPARATOR)
    override val extension: String
        get() = normalizedPathInsideZip.substringAfterLast('.')

    override val parent: String
        get() = normalizedPathInsideZip.substringBeforeLast(PATH_SEPARATOR)
    override val parentFile: ZippedFile
        get() = ZippedFile(fileTree, parent)

    override val exists: Boolean
        get() = fileTreeNode != null

    override val isDirectory: Boolean
        get() = fileTreeNode?.isDirectory == true
    override val isFile: Boolean
        get() = fileTreeNode?.isDirectory == false
    override val isAbsolute: Boolean
        get() = true

    override val listFiles: List<ZippedFile> by lazy {
        val node = fileTreeNode
        check (node != null && node.isDirectory) {
            "Zipped file $normalizedPathInsideZip is not a directory or doesn't exist"
        }
        node.children.map { (name, _) -> child(name) }
    }

    override val listFilesOrEmpty: List<ZippedFile>
        get() = if (exists && isDirectory) listFiles else emptyList()

    override fun child(name: String): ZippedFile {
        return ZippedFile(fileTree, normalizedPathInsideZip + PATH_SEPARATOR + name)
    }

    override fun copyTo(destination: File) {
        val node = fileTreeNode
        check(node is EntryNode && !node.isDirectory) {
            "Zipped file $normalizedPathInsideZip is not a regular file or doesn't exist"
        }

        zip.getInputStream(node.entry).use {
            Files.copy(it, Paths.get(URI(destination.absolutePath)))
        }
    }

    override fun recursiveCopyTo(destination: File, resetTimeAttributes: Boolean) {
        val node = fileTreeNode
        check(node != null && node.isDirectory) {
            "Zipped file $normalizedPathInsideZip is not a directory or doesn't exist"
        }

        destination.mkdirs()
        listFiles.forEach {
            val subDestination = destination.child(it.name)
            when {
                it.isDirectory -> it.recursiveCopyTo(subDestination, resetTimeAttributes)
                it.isFile -> it.copyTo(subDestination)
            }
            if (resetTimeAttributes) {
                val zero = FileTime.fromMillis(0)
                Files.getFileAttributeView(
                        subDestination.javaPath,
                        BasicFileAttributeView::class.java
                ).setTimes(zero, zero, zero);
            }
        }
    }

    override fun readBytes(): ByteArray {
        val node = fileTreeNode
        check(node is EntryNode && !node.isDirectory) {
            "Zipped file $normalizedPathInsideZip is not a regular file or doesn't exist"
        }
        return zip.getInputStream(node.entry).readBytes()
    }

    override fun forEachLine(action: (String) -> Unit) {
        val node = fileTreeNode
        check(node is EntryNode && !node.isDirectory) {
            "Zipped file $normalizedPathInsideZip is not a regular file or doesn't exist"
        }
        zip.getInputStream(node.entry).bufferedReader().forEachLine(action)
    }

    override fun bufferedReader(): BufferedReader {
        val node = fileTreeNode
        check(node is EntryNode && !node.isDirectory) {
            "Zipped file $normalizedPathInsideZip is not a regular file or doesn't exist"
        }
        return zip.getInputStream(node.entry).bufferedReader()
    }

    // TODO: Override equals/hashCode.

    companion object {
        const val PATH_SEPARATOR = '/'
    }
}


// If a zip contains a directory with path 'foo/bar', then
// ZipFile.getEntry returns an entry for both strings "foo/bar" and "foo/bar/".
// But prior to Java 9, ZipEntry.isDirectory returns true only for the second case.
// https://bugs.java.com/bugdatabase/view_bug.do?bug_id=6233323
// So we need to iterate over the entries to find an entry with a canonical name.
//
// To avoid such iteration for each ZippedFile and to quickly get list of
// files in a directory, we store this information in a separate object
// shared between ZippedFile instances.
private class ZippedFileTree(val zip: ZipFile) {

    val root: Node.RootNode by lazy {
        val rootNode = Node.RootNode()

        // Sort entries to process parents before children.
        zip.entries().asSequence().sortedBy { it.name }.forEach { entry ->
            val path = entry.name.removeSuffix("$PATH_SEPARATOR").split(PATH_SEPARATOR)

            // Find a required parent.
            var parent: Node = rootNode
            for (pathElement in path.dropLast(1)) {
                parent = parent.children.getValue(pathElement)
            }

            val newNodeName = path.last()
            parent.children[newNodeName] = EntryNode(entry)
        }
        rootNode
    }

    fun getNode(canonicalPath: String): Node? {
        val path = canonicalPath.split(PATH_SEPARATOR).drop(1)

        var currentNode: Node = root
        for (pathElement in path) {
            currentNode = currentNode.children[pathElement] ?: return null
        }
        return currentNode
    }

    sealed class Node {
        val children =  mutableMapOf<String, Node>()

        abstract val isDirectory: Boolean

        class RootNode: Node() {
            override val isDirectory: Boolean
                get() = true
        }

        class EntryNode(val entry: ZipEntry): Node() {
            override val isDirectory: Boolean
                get() = entry.isDirectory
        }
    }
}
