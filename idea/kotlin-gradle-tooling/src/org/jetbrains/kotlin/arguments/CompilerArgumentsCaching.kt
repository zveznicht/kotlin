/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.arguments

import java.io.Serializable

typealias CachedArgumentIdType = Int
typealias ClasspathArgumentCacheIdType = Array<Int>
typealias ClasspathArgumentsType = Array<String>
typealias RawCompilerArgumentsBucket = ArrayList<String>

interface CompilerArgumentsBucket<T> : Serializable {
    var generalArguments: ArrayList<T>
    var classpathParts: Array<T>
    var pluginClasspaths: Array<T>
    var friendPaths: Array<T>
}

class CachedCompilerArgumentsBucket(
    override var generalArguments: ArrayList<Int>,
    override var classpathParts: Array<Int>,
    override var pluginClasspaths: Array<Int>,
    override var friendPaths: Array<Int>
) : CompilerArgumentsBucket<Int> {
    constructor(otherBucket: CachedCompilerArgumentsBucket) : this(
        ArrayList(otherBucket.generalArguments),
        arrayOf(*otherBucket.classpathParts),
        arrayOf(*otherBucket.pluginClasspaths),
        arrayOf(*otherBucket.friendPaths)
    )
}

class FlatCompilerArgumentsBucket(
    override var generalArguments: ArrayList<String>,
    override var classpathParts: Array<String>,
    override var pluginClasspaths: Array<String>,
    override var friendPaths: Array<String>
) : CompilerArgumentsBucket<String> {
    constructor(otherBucket: FlatCompilerArgumentsBucket) : this(
        ArrayList(otherBucket.generalArguments),
        arrayOf(*otherBucket.classpathParts),
        arrayOf(*otherBucket.pluginClasspaths),
        arrayOf(*otherBucket.friendPaths)
    )
}

interface IArgsInfo<R, T : CompilerArgumentsBucket<R>> : Serializable {
    val currentCompilerArgumentsBucket: T
    val defaultCompilerArgumentsBucket: T
    val dependencyClasspath: Array<R>
}

interface FlatArgsInfo : IArgsInfo<String, FlatCompilerArgumentsBucket> {
    override val currentCompilerArgumentsBucket: FlatCompilerArgumentsBucket
    override val defaultCompilerArgumentsBucket: FlatCompilerArgumentsBucket
    override val dependencyClasspath: ClasspathArgumentsType
}

class FlatArgsInfoImpl(
    override val currentCompilerArgumentsBucket: FlatCompilerArgumentsBucket,
    override val defaultCompilerArgumentsBucket: FlatCompilerArgumentsBucket,
    override val dependencyClasspath: ClasspathArgumentsType
) : FlatArgsInfo {
    constructor(flatArgsInfo: FlatArgsInfo) : this(
        FlatCompilerArgumentsBucket(flatArgsInfo.currentCompilerArgumentsBucket),
        FlatCompilerArgumentsBucket(flatArgsInfo.defaultCompilerArgumentsBucket),
        arrayOf(*flatArgsInfo.dependencyClasspath)
    )
}

interface CachedArgsInfo : IArgsInfo<Int, CachedCompilerArgumentsBucket> {
    override val currentCompilerArgumentsBucket: CachedCompilerArgumentsBucket
    override val defaultCompilerArgumentsBucket: CachedCompilerArgumentsBucket
    override val dependencyClasspath: ClasspathArgumentCacheIdType
}

class CachedArgsInfoImpl(
    override val currentCompilerArgumentsBucket: CachedCompilerArgumentsBucket,
    override val defaultCompilerArgumentsBucket: CachedCompilerArgumentsBucket,
    override val dependencyClasspath: ClasspathArgumentCacheIdType
) : CachedArgsInfo {
    constructor(cachedArgsInfo: CachedArgsInfo) : this(
        CachedCompilerArgumentsBucket(cachedArgsInfo.currentCompilerArgumentsBucket),
        CachedCompilerArgumentsBucket(cachedArgsInfo.defaultCompilerArgumentsBucket),
        arrayOf(*cachedArgsInfo.dependencyClasspath)
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
