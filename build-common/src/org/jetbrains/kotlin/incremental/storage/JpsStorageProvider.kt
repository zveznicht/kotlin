/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental.storage

import com.intellij.util.io.DataExternalizer
import com.intellij.util.io.KeyDescriptor
import java.io.File

class JpsStorageProvider : StorageProvider() {
    private val nonCachingStorage = System.getProperty("kotlin.jps.non.caching.storage")?.toBoolean() ?: false

    override fun <K, V> create(
        storageFile: File,
        keyDescriptor: KeyDescriptor<K>,
        valueExternalizer: DataExternalizer<V>
    ): LazyStorage<K, V> =
        if (nonCachingStorage) {
            NonCachingLazyStorage(storageFile, keyDescriptor, valueExternalizer)
        } else {
            CachingLazyStorage(storageFile, keyDescriptor, valueExternalizer)
        }

}

