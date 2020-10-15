/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir

import org.jetbrains.kotlin.daemon.common.threadCpuTime
import org.jetbrains.kotlin.daemon.common.threadUserTime
import sun.management.ManagementFactoryHelper


data class GCInfo(val name: String, val gcTime: Long, val collections: Long) {
    operator fun minus(other: GCInfo): GCInfo {
        return this.copy(
            gcTime = gcTime - other.gcTime,
            collections = collections - other.collections
        )
    }

    operator fun plus(other: GCInfo): GCInfo {
        return this.copy(
            gcTime = gcTime + other.gcTime,
            collections = collections + other.collections
        )
    }
}

data class VMCounters(
    val userTime: Long = 0,
    val cpuTime: Long = 0,
    val gcInfo: Map<String, GCInfo> = emptyMap(),

    val safePointTotalTime: Long = 0,
    val safePointSyncTime: Long = 0,
    val safePointCount: Long = 0,

    val compilationTotalTime: Long = 0,

    val compilationTotalCount: Long = 0,
    val bailoutCompilationCount: Long = 0,
    val invalidatedCompileCount: Long = 0,

    val compiledMethodSize: Long = 0,
    val compiledMethodCodeSize: Long = 0,
) {


    operator fun minus(other: VMCounters): VMCounters {
        return VMCounters(
            userTime - other.userTime,
            cpuTime - other.cpuTime,
            merge(gcInfo, other.gcInfo) { a, b -> a - b },
            safePointTotalTime - other.safePointTotalTime,
            safePointSyncTime - other.safePointSyncTime,
            safePointCount - other.safePointCount,
            compilationTotalTime - other.compilationTotalTime,
            compilationTotalCount - other.compilationTotalCount,
            bailoutCompilationCount - other.bailoutCompilationCount,
            invalidatedCompileCount - other.invalidatedCompileCount,
            compiledMethodSize - other.compiledMethodSize,
            compiledMethodCodeSize - other.compiledMethodCodeSize
        )
    }


    operator fun plus(other: VMCounters): VMCounters {
        return VMCounters(
            userTime + other.userTime,
            cpuTime + other.cpuTime,
            merge(gcInfo, other.gcInfo) { a, b -> a + b },
            safePointTotalTime + other.safePointTotalTime,
            safePointSyncTime + other.safePointSyncTime,
            safePointCount + other.safePointCount,
            compilationTotalTime + other.compilationTotalTime,
            compilationTotalCount + other.compilationTotalCount,
            bailoutCompilationCount + other.bailoutCompilationCount,
            invalidatedCompileCount + other.invalidatedCompileCount,
            compiledMethodSize + other.compiledMethodSize,
            compiledMethodCodeSize + other.compiledMethodCodeSize
        )
    }
}


private fun <K, V> merge(first: Map<K, V>, second: Map<K, V>, valueOp: (V, V) -> V): Map<K, V> {
    val result = first.toMutableMap()
    for ((k, v) in second) {
        result.merge(k, v, valueOp)
    }
    return result
}

object Init {
    init {
        ManagementFactoryHelper.getThreadMXBean().isThreadCpuTimeEnabled = true
    }
}

fun vmStateSnapshot(): VMCounters {
    Init
    val threadMXBean = ManagementFactoryHelper.getThreadMXBean()
    val hotspotRuntimeMBean = ManagementFactoryHelper.getHotspotRuntimeMBean()
//    val hotspotCompilationMBean = ManagementFactoryHelper.getHotspotCompilationMBean()
//    val compilationMXBean = ManagementFactoryHelper.getCompilationMXBean()

    return VMCounters(
        threadMXBean.threadUserTime(), threadMXBean.threadCpuTime(),
        ManagementFactoryHelper.getGarbageCollectorMXBeans().associate { it.name to GCInfo(it.name, it.collectionTime, it.collectionCount) },
        hotspotRuntimeMBean.totalSafepointTime,
        hotspotRuntimeMBean.safepointSyncTime,
        hotspotRuntimeMBean.safepointCount,
        0,
        0,
        0,
        0,
        0,
        0
    )
}