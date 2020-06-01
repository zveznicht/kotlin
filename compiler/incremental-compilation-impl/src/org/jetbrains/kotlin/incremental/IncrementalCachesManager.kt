/*
 * Copyright 2010-2016 JetBrains s.r.o.
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

package org.jetbrains.kotlin.incremental

import org.jetbrains.kotlin.incremental.storage.BasicMapsOwner
import org.jetbrains.kotlin.incremental.storage.IncrementalCacheContext
import org.jetbrains.kotlin.serialization.SerializerExtensionProtocol
import java.io.Closeable
import java.io.File
import java.io.IOException

abstract class IncrementalCachesManager<PlatformCache : AbstractIncrementalCache<*>>(
    cachesRootDir: File,
    context: IncrementalCacheContext
) : Closeable {
    protected val reporter = context.reporter

    private val caches = arrayListOf<BasicMapsOwner>()
    protected fun <T : BasicMapsOwner> T.registerCache() {
        caches.add(this)
    }

    private val inputSnapshotsCacheDir = File(cachesRootDir, "inputs").apply { mkdirs() }
    private val lookupCacheDir = File(cachesRootDir, "lookups").apply { mkdirs() }

    val inputsCache: InputsCache = InputsCache(inputSnapshotsCacheDir, context).apply { registerCache() }
    val lookupCache: LookupStorage = LookupStorage(lookupCacheDir, context).apply { registerCache() }
    abstract val platformCache: PlatformCache

    override fun close() {
        forEachCacheSafe("close") { it.close() }
    }

    fun flush() {
        forEachCacheSafe("flush") { it.flush(false) }
    }

    private fun forEachCacheSafe(action: String, fn: (BasicMapsOwner) -> Unit) {
        val exceptions = ArrayList<Exception>()
        for (cache in caches) {
            try {
                fn(cache)
            } catch (e: Exception) {
                exceptions.add(e)
                reporter.report { "Exception during '$action' with cache ${cache.javaClass}: $e" }
            }
        }
        if (exceptions.isNotEmpty()) {
            val ioEx = IOException("Could not $action IC caches properly")
            exceptions.forEach { ioEx.addSuppressed(it) }
            throw ioEx
        }
    }
}

class IncrementalJvmCachesManager(
    cacheDirectory: File,
    outputDir: File,
    context: IncrementalCacheContext
) : IncrementalCachesManager<IncrementalJvmCache>(cacheDirectory, context) {

    private val jvmCacheDir = File(cacheDirectory, "jvm").apply { mkdirs() }
    override val platformCache = IncrementalJvmCache(jvmCacheDir, outputDir, context).apply { registerCache() }
}

class IncrementalJsCachesManager(
    cachesRootDir: File,
    context: IncrementalCacheContext,
    serializerProtocol: SerializerExtensionProtocol
) : IncrementalCachesManager<IncrementalJsCache>(cachesRootDir, context) {

    private val jsCacheFile = File(cachesRootDir, "js").apply { mkdirs() }
    override val platformCache = IncrementalJsCache(jsCacheFile, context, serializerProtocol).apply { registerCache() }
}