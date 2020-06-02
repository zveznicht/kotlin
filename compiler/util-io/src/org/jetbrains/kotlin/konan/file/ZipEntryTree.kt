/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.file

import java.util.zip.ZipEntry
import java.util.zip.ZipFile

// TODO: Finish.
class ZipEntryTree(zip: ZipFile) {

    val roots = mutableMapOf<String, Node>()

    init {
    }


    private fun addEntry(entry: ZipEntry) {
    }

    class Node(val parent: Node?, val entry: ZipEntry) {
        // TODO: encapsulate
        val children = mutableMapOf<String, Node>()
    }
}