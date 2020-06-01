/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental.storage

import java.io.File

class ReadOnlyStorageWrapper<K, V>(private val delegate: LazyStorage<K, V>) :
    LazyStorage<K, V> {
    override val keys: Collection<K>
        get() = delegate.keys

    override fun contains(key: K): Boolean =
        delegate.contains(key)

    override fun get(key: K): V? =
        delegate.get(key)

    override fun set(key: K, value: V) {
        writeOperationError("set")
    }

    override fun remove(key: K) {
        writeOperationError("remove")
    }

    override fun append(key: K, value: V) {
        writeOperationError("append")
    }

    override fun clean() {
        writeOperationError("clean")
    }

    override fun flush(memoryCachesOnly: Boolean) {
        writeOperationError("flush")
    }

    override fun close() {
        delegate.close()
    }

    override val storageFile: File
        get() = delegate.storageFile

    private fun writeOperationError(action: String): Nothing {
        throw NotImplementedError("Read only storage does not support '$action'")
    }
}