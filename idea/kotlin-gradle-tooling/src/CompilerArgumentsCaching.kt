/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle

import java.io.Serializable

typealias CachedArgumentIdType = Int
typealias ClasspathArgumentCacheIdType = Array<Int>
typealias ClasspathArgumentsType = Array<String>
typealias RawCompilerArgumentsBucket = List<String>

interface CompilerArgumentsBucket<T> : Serializable {
    val generalArguments: T
    val classpathParts: T
    val pluginClasspaths: T
    val friendPaths: T
}

class CachedCompilerArgumentsBucket(
    override val generalArguments: Array<Int>,
    override val classpathParts: Array<Int>,
    override val pluginClasspaths: Array<Int>,
    override val friendPaths: Array<Int>
) : CompilerArgumentsBucket<Array<Int>> {
    constructor(otherBucket: CachedCompilerArgumentsBucket) : this(
        arrayOf(*otherBucket.generalArguments),
        arrayOf(*otherBucket.classpathParts),
        arrayOf(*otherBucket.pluginClasspaths),
        arrayOf(*otherBucket.friendPaths)
    )
}

class FlatCompilerArgumentsBucket(
    override val generalArguments: Array<String>,
    override val classpathParts: Array<String>,
    override val pluginClasspaths: Array<String>,
    override val friendPaths: Array<String>
) : CompilerArgumentsBucket<Array<String>> {
    constructor(otherBucket: FlatCompilerArgumentsBucket) : this(
        arrayOf(*otherBucket.generalArguments),
        arrayOf(*otherBucket.classpathParts),
        arrayOf(*otherBucket.pluginClasspaths),
        arrayOf(*otherBucket.friendPaths)
    )
}

interface IArgsInfo<R, T : CompilerArgumentsBucket<R>> : Serializable {
    val currentCompilerArgumentsBucket: T
    val defaultCompilerArgumentsBucket: T
    val dependencyClasspathCacheIds: R
}

interface FlatArgsInfo : IArgsInfo<ClasspathArgumentsType, FlatCompilerArgumentsBucket> {
    override val currentCompilerArgumentsBucket: FlatCompilerArgumentsBucket
    override val defaultCompilerArgumentsBucket: FlatCompilerArgumentsBucket
    override val dependencyClasspathCacheIds: ClasspathArgumentsType
}

class FlatArgsInfoImpl(
    override val currentCompilerArgumentsBucket: FlatCompilerArgumentsBucket,
    override val defaultCompilerArgumentsBucket: FlatCompilerArgumentsBucket,
    override val dependencyClasspathCacheIds: ClasspathArgumentsType
) : FlatArgsInfo {
    constructor(flatArgsInfo: FlatArgsInfo) : this(
        FlatCompilerArgumentsBucket(flatArgsInfo.currentCompilerArgumentsBucket),
        FlatCompilerArgumentsBucket(flatArgsInfo.defaultCompilerArgumentsBucket),
        arrayOf(*flatArgsInfo.dependencyClasspathCacheIds)
    )
}

interface CachedArgsInfo : IArgsInfo<ClasspathArgumentCacheIdType, CachedCompilerArgumentsBucket> {
    override val currentCompilerArgumentsBucket: CachedCompilerArgumentsBucket
    override val defaultCompilerArgumentsBucket: CachedCompilerArgumentsBucket
    override val dependencyClasspathCacheIds: ClasspathArgumentCacheIdType
}

class CachedArgsInfoImpl(
    override val currentCompilerArgumentsBucket: CachedCompilerArgumentsBucket,
    override val defaultCompilerArgumentsBucket: CachedCompilerArgumentsBucket,
    override val dependencyClasspathCacheIds: ClasspathArgumentCacheIdType
) : CachedArgsInfo {
    constructor(cachedArgsInfo: CachedArgsInfo) : this(
        CachedCompilerArgumentsBucket(cachedArgsInfo.currentCompilerArgumentsBucket),
        CachedCompilerArgumentsBucket(cachedArgsInfo.defaultCompilerArgumentsBucket),
        arrayOf(*cachedArgsInfo.dependencyClasspathCacheIds)
    )
}

typealias CachedCompilerArgumentBySourceSet = Map<String, CachedArgsInfo>

/**
 * Creates deep copy in order to avoid holding links to Proxy objects created by gradle tooling api
 */
fun CachedCompilerArgumentBySourceSet.deepCopy(): CachedCompilerArgumentBySourceSet {
    val result = HashMap<String, CachedArgsInfo>()
    this.forEach { key, value -> result[key] = CachedArgsInfoImpl(value) }
    return result
}

interface ICompilerArgumentsMapper : Serializable {
    fun getArgumentCache(argument: String): Int?
    fun cacheArgument(commonArgument: String): Int
    fun getArgument(id: Int): String
    fun clear()
    fun copyCache(): Map<Int, String>
}

open class CompilerArgumentsMapper(val initialId: Int = 0) : ICompilerArgumentsMapper {

    constructor(otherMapper: CompilerArgumentsMapper) : this(otherMapper.initialId) {
        idToCompilerArguments.putAll(otherMapper.idToCompilerArguments)
        compilerArgumentToId.putAll(otherMapper.compilerArgumentToId)
        nextId = otherMapper.nextId
    }

    protected var nextId = initialId
    protected val idToCompilerArguments: MutableMap<Int, String> = mutableMapOf()
    protected val compilerArgumentToId: MutableMap<String, Int> = mutableMapOf()

    override fun getArgumentCache(argument: String): Int? = compilerArgumentToId[argument]

    override fun cacheArgument(commonArgument: String): Int = getArgumentCache(commonArgument)
        ?: run {
            val index = nextId++
            idToCompilerArguments[index] = commonArgument
            compilerArgumentToId[commonArgument] = index
            index
        }

    override fun getArgument(id: Int): String = idToCompilerArguments[id]!!

    override fun clear() {
        idToCompilerArguments.clear()
        compilerArgumentToId.clear()
        nextId = initialId
    }

    override fun copyCache(): Map<Int, String> = HashMap(idToCompilerArguments)
}

interface IDetachableMapper : ICompilerArgumentsMapper {
    val masterMapper: ICompilerArgumentsMapper
    fun detach(): ICompilerArgumentsMapper
}

class DetachableCompilerArgumentsMapper(override val masterMapper: ICompilerArgumentsMapper) : CompilerArgumentsMapper(),
    IDetachableMapper {
    override fun cacheArgument(commonArgument: String): Int =
        masterMapper.getArgumentCache(commonArgument) ?: masterMapper.cacheArgument(commonArgument).also {
            idToCompilerArguments[it] = commonArgument
            compilerArgumentToId[commonArgument] = it
        }

    override fun detach(): ICompilerArgumentsMapper = CompilerArgumentsMapper(this)
}

interface IWithCheckoutMapper : ICompilerArgumentsMapper {
    fun checkoutMapper(): IDetachableMapper
}

class CompilerArgumentsMapperWithCheckout : CompilerArgumentsMapper(), IWithCheckoutMapper {
    override fun checkoutMapper(): IDetachableMapper = DetachableCompilerArgumentsMapper(this)
}

interface IWithMergeMapper : ICompilerArgumentsMapper {
    fun mergeMapper(mapper: ICompilerArgumentsMapper)
}

class CompilerArgumentsMapperWithMerge : CompilerArgumentsMapper(), IWithMergeMapper {

    override fun mergeMapper(mapper: ICompilerArgumentsMapper) {
        mapper.copyCache().forEach { (id, arg) ->
            idToCompilerArguments[id] = arg
            compilerArgumentToId[arg] = id
        }
    }
}

data class CompilerArgumentMappersContainer @JvmOverloads constructor(
    val projectCompilerArgumentsMapper: CompilerArgumentsMapperWithMerge = CompilerArgumentsMapperWithMerge(),
    val projectMppCompilerArgumentsMapper: CompilerArgumentsMapperWithMerge = CompilerArgumentsMapperWithMerge()
) {
    @JvmOverloads
    fun mergeArgumentsFromMapper(mapper: ICompilerArgumentsMapper, isMpp: Boolean = false) {
        if (isMpp) projectMppCompilerArgumentsMapper.mergeMapper(mapper) else projectCompilerArgumentsMapper.mergeMapper(mapper)
    }
}
