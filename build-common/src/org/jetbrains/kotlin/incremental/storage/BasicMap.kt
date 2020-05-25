/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.incremental.storage

import com.intellij.util.io.DataExternalizer
import com.intellij.util.io.EnumeratorStringDescriptor
import com.intellij.util.io.KeyDescriptor
import org.jetbrains.annotations.TestOnly
import org.jetbrains.kotlin.utils.Printer
import java.io.File
import java.util.*

abstract class BasicMap<K : Comparable<K>, V>(
        internal val storageFile: File,
        keyDescriptor: KeyDescriptor<K>,
        valueExternalizer: DataExternalizer<V>
) {
    protected val storage: LazyStorage<K, V>
    private val nonCachingStorage = System.getProperty("kotlin.jps.non.caching.storage")?.toBoolean() ?: false

    init {
        storage = if (nonCachingStorage) {
            NonCachingLazyStorage(storageFile, keyDescriptor, valueExternalizer)
        } else {
            CachingLazyStorage(storageFile, keyDescriptor, valueExternalizer)
        }

        markOpen(this)
    }

    fun clean() {
        storage.clean()
    }

    fun flush(memoryCachesOnly: Boolean) {
        storage.flush(memoryCachesOnly)
    }

    fun close() {
        storage.close()
        markClosed(this)
    }

    private fun closeSilent() {
        storage.close()
    }

    companion object {
        private val shouldTrace = System.getProperty("kotlin.checkICCachesAreClosed")?.toBoolean() ?: false
        private val openMaps = if (shouldTrace) Collections.newSetFromMap(IdentityHashMap<BasicMap<*, *>, Boolean>()) else emptySet()

        @Synchronized
        private fun markOpen(m: BasicMap<*, *>) {
            if (shouldTrace) {
                openMaps.add(m)
            }
        }

        @Synchronized
        private fun markClosed(m: BasicMap<*, *>) {
            if (shouldTrace) {
                openMaps.remove(m)
            }
        }

        @Synchronized
        fun listAndCloseOpenedCaches(): List<String> {
            if (!shouldTrace) return emptyList()

            val result = openMaps.map {
                try {
                    it.closeSilent()
                } catch (e: Exception) {
                    // ignore
                }

                "${it.javaClass.name} at ${it.storageFile}"
            }
            openMaps.clear()
            return result
        }
    }

    @TestOnly
    fun dump(): String {
        return with(StringBuilder()) {
            with(Printer(this)) {
                println(this@BasicMap::class.java.simpleName)
                pushIndent()

                for (key in storage.keys.sorted()) {
                    println("${dumpKey(key)} -> ${dumpValue(storage[key]!!)}")
                }

                popIndent()
            }

            this
        }.toString()
    }

    @TestOnly
    protected abstract fun dumpKey(key: K): String

    @TestOnly
    protected abstract fun dumpValue(value: V): String
}

abstract class BasicStringMap<V>(
        storageFile: File,
        keyDescriptor: KeyDescriptor<String>,
        valueExternalizer: DataExternalizer<V>
) : BasicMap<String, V>(storageFile, keyDescriptor, valueExternalizer) {
    constructor(
            storageFile: File,
            valueExternalizer: DataExternalizer<V>
    ) : this(storageFile, EnumeratorStringDescriptor.INSTANCE, valueExternalizer)

    override fun dumpKey(key: String): String = key
}
