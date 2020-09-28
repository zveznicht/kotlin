/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config.data

import com.intellij.util.PathUtil
import org.jetbrains.kotlin.gradle.FlatCompilerArgumentsBucket

interface ChangeArgumentStrategy<R, T> {
    val bucket: R
    fun execute(argumentId: String, newValue: T)
}

interface ChangeFlatArgumentStrategy<T> : ChangeArgumentStrategy<FlatCompilerArgumentsBucket, T> {
    override val bucket: FlatCompilerArgumentsBucket
}

class ChangeBooleanFlatArgumentStrategy(
    override val bucket: FlatCompilerArgumentsBucket
) : ChangeFlatArgumentStrategy<Boolean> {
    override fun execute(argumentId: String, newValue: Boolean) {
        with(bucket.generalArguments) {
            val contains = contains(argumentId)
            when {
                newValue && contains || (!newValue && !contains) -> return
                newValue -> add(argumentId)
                else -> remove(argumentId)
            }
        }
    }
}

class ChangeSingleFlatArgumentStrategy(
    override val bucket: FlatCompilerArgumentsBucket
) : ChangeFlatArgumentStrategy<String?> {
    override fun execute(argumentId: String, newValue: String?) {
        val arguments = bucket.generalArguments
        val isAdvanced = argumentId.startsWith("-X") && argumentId.length > 2
        val index = if (isAdvanced) arguments.indexOfFirst { it.startsWith(argumentId) } else arguments.indexOf(argumentId)
        val contains = index != -1
        when {
            !contains && newValue == null -> return
            contains && newValue == null -> bucket.generalArguments.apply {
                removeAt(index)
                if (!isAdvanced) {
                    removeAt(index + 1)
                }
            }
            contains && newValue != null -> bucket.generalArguments.apply {
                if (isAdvanced) this[index] = "$argumentId=$newValue"
                else this[index + 1] = newValue
            }
            !contains && newValue != null -> bucket.generalArguments.apply {
                if (isAdvanced) add("$argumentId=$newValue")
                else {
                    add(argumentId)
                    add(newValue)
                }
            }
        }
    }
}

abstract class ChangeMultipleFlatArgumentStrategy : ChangeFlatArgumentStrategy<Array<String>?> {
    private val String.prepared: String
        get() = PathUtil.toSystemIndependentName(this)

    protected val Array<String>?.prepared: Array<String>
        get() = this?.map { it.prepared }?.toTypedArray() ?: emptyArray()
}

class ChangeFlatPluginOptionsStrategy(override val bucket: FlatCompilerArgumentsBucket) : ChangeMultipleFlatArgumentStrategy() {
    override fun execute(argumentId: String, newValue: Array<String>?) {
        val argumentsList = bucket.generalArguments
        val oldOptions =
            argumentsList.mapIndexedNotNull { i, s -> i.takeIf { s == argumentId } }
                .flatMap { listOf(argumentsList[it], argumentsList[it + 1]) }
        val newOptions = newValue?.flatMap { listOf(argumentId, it) }
        bucket.generalArguments.apply {
            removeAll(oldOptions)
            newOptions?.also { addAll(it) }
        }.toTypedArray()
    }
}

class ChangeFlatPluginClasspathsStrategy(
    override val bucket: FlatCompilerArgumentsBucket,
) : ChangeMultipleFlatArgumentStrategy() {
    override fun execute(argumentId: String, newValue: Array<String>?) {
        bucket.pluginClasspaths = newValue.prepared
    }
}

class ChangeFlatClasspathPartsStrategy(
    override val bucket: FlatCompilerArgumentsBucket,
) : ChangeMultipleFlatArgumentStrategy() {
    override fun execute(argumentId: String, newValue: Array<String>?) {
        bucket.classpathParts = newValue.prepared
    }
}

class ChangeFlatFriendPathsStrategy(
    override val bucket: FlatCompilerArgumentsBucket,
) : ChangeMultipleFlatArgumentStrategy() {
    override fun execute(argumentId: String, newValue: Array<String>?) {
        bucket.friendPaths = newValue.prepared
    }
}
