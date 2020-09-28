/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.arguments

import java.io.Serializable


interface CompilerArgumentsMapper : Serializable {
    fun getArgumentCache(argument: String): Int?
    fun cacheArgument(commonArgument: String): Int
    fun getArgument(id: Int): String
    fun clear()
    fun copyCache(): Map<Int, String>
}

open class CompilerArgumentsMapperImpl(val initialId: Int = 0) : CompilerArgumentsMapper {

    constructor(otherMapper: CompilerArgumentsMapperImpl) : this(otherMapper.initialId) {
        idToCompilerArguments.putAll(otherMapper.idToCompilerArguments)
        nextId = otherMapper.nextId
    }

    var nextId = initialId
    protected val idToCompilerArguments: MutableMap<Int, String> = mutableMapOf()

    override fun getArgumentCache(argument: String): Int? =
        idToCompilerArguments.filter { it.value == argument }.keys.firstOrNull()

    override fun cacheArgument(commonArgument: String): Int = getArgumentCache(commonArgument)
        ?: run {
            val index = nextId++
            idToCompilerArguments[index] = commonArgument
            index
        }

    override fun getArgument(id: Int): String = idToCompilerArguments[id]!!

    override fun clear() {
        idToCompilerArguments.clear()
        nextId = initialId
    }

    override fun copyCache(): Map<Int, String> = HashMap(idToCompilerArguments)
}

interface IDetachableMapper : CompilerArgumentsMapper {
    val masterMapper: CompilerArgumentsMapper
    fun detach(): CompilerArgumentsMapper
}

class DetachableCompilerArgumentsMapper(override val masterMapper: CompilerArgumentsMapperImpl) : CompilerArgumentsMapperImpl(),
    IDetachableMapper {
    override fun cacheArgument(commonArgument: String): Int =
        masterMapper.getArgumentCache(commonArgument) ?: masterMapper.cacheArgument(commonArgument).also {
            idToCompilerArguments[it] = commonArgument
            nextId = masterMapper.nextId
        }

    override fun detach(): CompilerArgumentsMapper = CompilerArgumentsMapperImpl(this)
}

interface IWithCheckoutMapper : CompilerArgumentsMapper {
    fun checkoutMapper(): IDetachableMapper
}

class CompilerArgumentsMapperWithCheckout(initialId: Int = 0) : CompilerArgumentsMapperImpl(initialId), IWithCheckoutMapper {
    override fun checkoutMapper(): IDetachableMapper = DetachableCompilerArgumentsMapper(this)
}

interface IWithMergeMapper : CompilerArgumentsMapper {
    fun mergeMapper(mapper: CompilerArgumentsMapper)
}

class CompilerArgumentsMapperWithMerge : CompilerArgumentsMapperImpl(), IWithMergeMapper {

    override fun mergeMapper(mapper: CompilerArgumentsMapper) {
        mapper.copyCache().forEach { (id, arg) ->
            idToCompilerArguments[id] = arg
        }
    }
}

data class CompilerArgumentMappersContainer(
    val projectCompilerArgumentsMapper: CompilerArgumentsMapperWithMerge = CompilerArgumentsMapperWithMerge(),
    val projectMppCompilerArgumentsMapper: CompilerArgumentsMapperWithMerge = CompilerArgumentsMapperWithMerge()
) {
    fun mergeArgumentsFromMapper(mapper: CompilerArgumentsMapper, isMpp: Boolean = false) {
        if (isMpp) projectMppCompilerArgumentsMapper.mergeMapper(mapper) else projectCompilerArgumentsMapper.mergeMapper(mapper)
    }
}
