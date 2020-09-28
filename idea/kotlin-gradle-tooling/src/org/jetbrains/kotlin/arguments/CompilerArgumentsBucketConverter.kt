/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.arguments

import java.io.File

const val FRIEND_PATH_PREFIX = "-Xfriend-paths="
const val PLUGIN_CLASSPATH_PREFIX = "-Xplugin="

val classpathArgPointers = setOf("-classpath", "-cp")

private fun String.toSystemIndependentName() = replace('\\', '/')

interface CompilerArgumentsBucketConverter<From, To> {
    fun convert(from: From): To
}

class CachedToFlatCompilerArgumentsBucketConverter(val mapper: CompilerArgumentsMapper) :
    CompilerArgumentsBucketConverter<CachedCompilerArgumentsBucket, FlatCompilerArgumentsBucket> {
    override fun convert(from: CachedCompilerArgumentsBucket): FlatCompilerArgumentsBucket {
        //TODO check if it breaks other arguments
        val cachedGeneralArguments = ArrayList(from.generalArguments.map { mapper.getArgument(it).toSystemIndependentName() })
        val cachedClasspath = from.classpathParts.map { mapper.getArgument(it).toSystemIndependentName() }.toTypedArray()
        val cachedPluginClasspath = from.pluginClasspaths.map { mapper.getArgument(it).toSystemIndependentName() }.toTypedArray()
        val cachedFriendPaths = from.friendPaths.map { mapper.getArgument(it).toSystemIndependentName() }.toTypedArray()
        return FlatCompilerArgumentsBucket(cachedGeneralArguments, cachedClasspath, cachedPluginClasspath, cachedFriendPaths)
    }
}

class FlatToRawCompilerArgumentsBucketConverter :
    CompilerArgumentsBucketConverter<FlatCompilerArgumentsBucket, RawCompilerArgumentsBucket> {
    override fun convert(from: FlatCompilerArgumentsBucket): RawCompilerArgumentsBucket = ArrayList<String>().apply {
        addAll(from.generalArguments)
        if (from.classpathParts.isNotEmpty()) {
            add(from.classpathParts.first())
            add(from.classpathParts.toMutableList().apply { removeFirst() }.joinToString(File.pathSeparator))
        }
        from.pluginClasspaths.takeIf { it.isNotEmpty() }?.also { add("$PLUGIN_CLASSPATH_PREFIX${it.joinToString(",")}") }
        from.friendPaths.takeIf { it.isNotEmpty() }?.also { add("$FRIEND_PATH_PREFIX${it.joinToString(",")}") }
    }
}

class CachedToRawCompilerArgumentsBucketConverter(val mapper: CompilerArgumentsMapper) :
    CompilerArgumentsBucketConverter<CachedCompilerArgumentsBucket, RawCompilerArgumentsBucket> {
    override fun convert(from: CachedCompilerArgumentsBucket): RawCompilerArgumentsBucket =
        CachedToFlatCompilerArgumentsBucketConverter(mapper).convert(from).let {
            FlatToRawCompilerArgumentsBucketConverter().convert(it)
        }
}

class RawToFlatCompilerArgumentsBucket :
    CompilerArgumentsBucketConverter<RawCompilerArgumentsBucket, FlatCompilerArgumentsBucket> {
    override fun convert(from: RawCompilerArgumentsBucket): FlatCompilerArgumentsBucket {
        val pluginClasspathArgument = from.firstOrNull { it.startsWith(PLUGIN_CLASSPATH_PREFIX) }
        val friendPathsArgument = from.firstOrNull { it.startsWith(FRIEND_PATH_PREFIX) }
        val classpathArgument = from.flatMapIndexed { index, s ->
            if (s in classpathArgPointers) listOf(s, from[index + 1]) else emptyList()
        }

        // TODO(ychernyshev) Does the order of arguments required here?
        val generalArguments = ArrayList((from - pluginClasspathArgument - friendPathsArgument - classpathArgument).filterNotNull())
        val classpathArguments = mutableListOf<String>().apply {
            if (classpathArgument.isNotEmpty()) {
                add(classpathArgument.first())
                addAll(classpathArgument.last().split(File.pathSeparator))
            }
        }.toTypedArray()

        val pluginClasspaths =
            pluginClasspathArgument?.removePrefix(PLUGIN_CLASSPATH_PREFIX)?.split(",")?.toTypedArray() ?: emptyArray()

        val friendPaths = friendPathsArgument?.removePrefix(FRIEND_PATH_PREFIX)?.split(",")?.toTypedArray() ?: emptyArray()

        return FlatCompilerArgumentsBucket(generalArguments, classpathArguments, pluginClasspaths, friendPaths)
    }
}

class FlatToCachedCompilerArgumentsBucket(val mapper: CompilerArgumentsMapper) :
    CompilerArgumentsBucketConverter<FlatCompilerArgumentsBucket, CachedCompilerArgumentsBucket> {
    override fun convert(from: FlatCompilerArgumentsBucket): CachedCompilerArgumentsBucket {
        val generalArguments = ArrayList(from.generalArguments.map { mapper.cacheArgument(it) })
        val classpathParts = from.classpathParts.map { mapper.cacheArgument(it) }.toTypedArray()
        val pluginClasspaths = from.pluginClasspaths.map { mapper.cacheArgument(it) }.toTypedArray()
        val friendPaths = from.friendPaths.map { mapper.cacheArgument(it) }.toTypedArray()
        return CachedCompilerArgumentsBucket(generalArguments, classpathParts, pluginClasspaths, friendPaths)
    }
}

class RawToCachedCompilerArgumentsBucket(val mapper: CompilerArgumentsMapper) :
    CompilerArgumentsBucketConverter<RawCompilerArgumentsBucket, CachedCompilerArgumentsBucket> {
    override fun convert(from: RawCompilerArgumentsBucket): CachedCompilerArgumentsBucket =
        RawToFlatCompilerArgumentsBucket().convert(from).let {
            FlatToCachedCompilerArgumentsBucket(mapper).convert(it)
        }
}