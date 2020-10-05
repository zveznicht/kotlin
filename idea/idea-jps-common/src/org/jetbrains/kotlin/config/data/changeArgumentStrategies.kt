/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config.data

import com.intellij.util.PathUtil
import org.jetbrains.kotlin.arguments.FlatCompilerArgumentsBucket
import java.io.Serializable
import kotlin.reflect.KProperty

interface ChangeArgumentStrategy<R, T> {
    val bucket: R
    fun getArgumentStrategy(argumentId: String): T
    fun setArgumentStrategy(argumentId: String, newValue: T)
}

interface ChangeFlatArgumentStrategy<T> : ChangeArgumentStrategy<FlatCompilerArgumentsBucket, T> {
    override val bucket: FlatCompilerArgumentsBucket
}

class ChangeBooleanFlatArgumentStrategy(
    override val bucket: FlatCompilerArgumentsBucket
) : ChangeFlatArgumentStrategy<Boolean> {
    override fun getArgumentStrategy(argumentId: String): Boolean = bucket.generalArguments.contains(argumentId)
    override fun setArgumentStrategy(argumentId: String, newValue: Boolean) {
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
    override fun getArgumentStrategy(argumentId: String): String? {
        val arguments = bucket.generalArguments
        val isAdvanced = argumentId.startsWith("-X") && argumentId.length > 2
        val index = if (isAdvanced) arguments.indexOfFirst { it.startsWith(argumentId) } else arguments.indexOf(argumentId)
        val contains = index != -1
        if (!contains) return null
        return if (isAdvanced) arguments[index].removePrefix("$argumentId=") else arguments[index + 1]
    }

    override fun setArgumentStrategy(argumentId: String, newValue: String?) {
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

abstract class ChangeMultipleFlatArgumentStrategy<T> : ChangeFlatArgumentStrategy<Array<String>?> {
    abstract val arguments: T
    private val String.prepared: String
        get() = PathUtil.toSystemIndependentName(this)

    protected val Array<String>?.prepared: Array<String>
        get() = this?.map { it.prepared }?.toTypedArray() ?: emptyArray()
}

class ChangeMultipleFlatArgumentStrategyImpl(
    override val bucket: FlatCompilerArgumentsBucket
) : ChangeMultipleFlatArgumentStrategy<ArrayList<String>>() {
    override val arguments: ArrayList<String> = bucket.generalArguments

    override fun getArgumentStrategy(argumentId: String): Array<String>? {
        val isAdvanced = argumentId.startsWith("-X") && argumentId.length > 2
        val index = if (isAdvanced) arguments.indexOfFirst { it.startsWith(argumentId) } else arguments.indexOf(argumentId)
        val contains = index != -1
        if (!contains) return null
        return (if (isAdvanced) arguments[index].removePrefix("$argumentId=") else arguments[index + 1]).split(",").toTypedArray()
    }

    override fun setArgumentStrategy(argumentId: String, newValue: Array<String>?) {
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
                if (isAdvanced) this[index] = "$argumentId=${newValue.joinToString(",")}"
                else this[index + 1] = newValue.joinToString(",")
            }
            !contains && newValue != null -> bucket.generalArguments.apply {
                if (isAdvanced) add("$argumentId=$newValue")
                else {
                    add(argumentId)
                    add(newValue.joinToString(","))
                }
            }
        }
    }
}


class ChangeFlatPluginOptionsStrategy(override val bucket: FlatCompilerArgumentsBucket) :
    ChangeMultipleFlatArgumentStrategy<ArrayList<String>>() {
    override val arguments: ArrayList<String> = bucket.generalArguments

    override fun setArgumentStrategy(argumentId: String, newValue: Array<String>?) {
        val oldOptions =
            arguments.mapIndexedNotNull { i, s -> i.takeIf { s == argumentId } }
                .flatMap { listOf(arguments[it], arguments[it + 1]) }
        val newOptions = newValue?.flatMap { listOf(argumentId, it) }
        bucket.generalArguments.apply {
            removeAll(oldOptions)
            newOptions?.also { addAll(it) }
        }.toTypedArray()
    }

    override fun getArgumentStrategy(argumentId: String): Array<String> = arguments
        .mapIndexedNotNull { i, s -> i.takeIf { s == argumentId } }
        .flatMap { listOf(arguments[it], arguments[it + 1]) }
        .toTypedArray()
}

class ChangeFlatPluginClasspathsStrategy(
    override val bucket: FlatCompilerArgumentsBucket,
) : ChangeMultipleFlatArgumentStrategy<Array<String>>() {
    override val arguments: Array<String>
        get() = bucket.pluginClasspaths

    override fun setArgumentStrategy(argumentId: String, newValue: Array<String>?) {
        bucket.pluginClasspaths = newValue.prepared
    }

    override fun getArgumentStrategy(argumentId: String): Array<String>? = arguments
}

class ChangeFlatClasspathPartsStrategy(
    override val bucket: FlatCompilerArgumentsBucket,
) : ChangeMultipleFlatArgumentStrategy<Array<String>>() {
    override val arguments: Array<String>
        get() = bucket.classpathParts

    override fun setArgumentStrategy(argumentId: String, newValue: Array<String>?) {
        bucket.classpathParts = newValue.prepared
    }

    override fun getArgumentStrategy(argumentId: String): Array<String> = arguments
}

class ChangeFlatFriendPathsStrategy(
    override val bucket: FlatCompilerArgumentsBucket,
) : ChangeMultipleFlatArgumentStrategy<Array<String>>() {
    override val arguments: Array<String>
        get() = bucket.friendPaths

    override fun setArgumentStrategy(argumentId: String, newValue: Array<String>?) {
        bucket.friendPaths = newValue.prepared
    }

    override fun getArgumentStrategy(argumentId: String): Array<String> = arguments

}

interface ChangingByStrategy<T, R : ChangeFlatArgumentStrategy<T>> : Serializable {
    val argumentsId: String
    var value: T
    val strategy: R

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = strategy.getArgumentStrategy(argumentsId)
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        strategy.setArgumentStrategy(argumentsId, value)
    }
}

class ChangingBySingeFlatArgumentStrategy(override val argumentsId: String, override val strategy: ChangeSingleFlatArgumentStrategy) :
    ChangingByStrategy<String?, ChangeSingleFlatArgumentStrategy> {
    override var value: String? = null
}

class ChangingByFlatArgumentsArrayStrategy(
    override val strategy: ChangeMultipleFlatArgumentStrategy<Array<String>>,
    override val argumentsId: String = ""
) : ChangingByStrategy<Array<String>?, ChangeMultipleFlatArgumentStrategy<Array<String>>> {
    override var value: Array<String>? = null
}

class ChangingByFlatPluginOptionsStrategy(
    override val strategy: ChangeFlatPluginOptionsStrategy,
    override val argumentsId: String = ""
) : ChangingByStrategy<Array<String>?, ChangeFlatPluginOptionsStrategy> {
    override var value: Array<String>? = null
}

