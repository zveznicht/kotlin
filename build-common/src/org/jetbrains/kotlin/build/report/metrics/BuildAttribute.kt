/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.build.report.metrics

import org.jetbrains.kotlin.build.ExecutionStrategy
import java.io.Serializable
import java.util.*

open class BuildAttributes : Serializable {
    private val myRebuildReasons = EnumMap<RebuildReason, Int>(RebuildReason::class.java)
    private val myExecutionStrategies = EnumMap<ExecutionStrategy, Int>(ExecutionStrategy::class.java)

    val rebuildReasons: Map<RebuildReason, Int>
        get() = myRebuildReasons

    val executionStrategies: Map<ExecutionStrategy, Int>
        get() = executionStrategies

    fun rebuild(reason: RebuildReason) {
        myRebuildReasons[reason] = myRebuildReasons.getOrDefault(reason, 0) + 1
    }

    fun executionStrategy(strategy: ExecutionStrategy) {
        myExecutionStrategies[strategy] = myExecutionStrategies.getOrDefault(strategy, 0) + 1
    }

    fun addAll(other: BuildAttributes) {
        myRebuildReasons.incrementBy(other.myRebuildReasons)
        myExecutionStrategies.incrementBy(other.myExecutionStrategies)
    }

    private fun <K> MutableMap<K, Int>.incrementBy(other: Map<K, Int>) {
        for ((k, n) in other) {
            this[k] = this.getOrDefault(k, 0) + n
        }
    }

    fun asMap(): Map<Class<out Enum<*>>, Map<out Enum<*>, Int>> {
        return mapOf(
            RebuildReason::class.java to myRebuildReasons,
            ExecutionStrategy::class.java to executionStrategies
        )
    }

    companion object {
        const val serialVersionUID = 0L
    }
}

enum class RebuildReason : Serializable {
    NO_BUILD_HISTORY,
    CACHE_CORRUPTION,
    UNKNOWN_CHANGES_IN_GRADLE_INPUTS,
    JAVA_CHANGE_UNTRACKED_FILE_IS_REMOVED,
    JAVA_CHANGE_UNEXPECTED_PSI,
    JAVA_CHANGE_UNKNOWN_QUALIFIER,
    DEP_CHANGE_REMOVED_ENTRY,
    DEP_CHANGE_HISTORY_IS_NOT_FOUND,
    DEP_CHANGE_HISTORY_CANNOT_BE_READ,
    DEP_CHANGE_HISTORY_NO_KNOWN_BUILDS,
    DEP_CHANGE_NON_INCREMENTAL_BUILD_IN_DEP,
    IN_PROCESS_EXECUTION,
    OUT_OF_PROCESS_EXECUTION,
    IC_IS_NOT_ENABLED;

    companion object {
        const val serialVersionUID = 0L
    }
}
