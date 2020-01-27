/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.build.metrics

import java.io.Serializable
import java.util.*

@Suppress("Reformat")
enum class BuildTime(val parent: BuildTime? = null) : Serializable {
    GRADLE_JAVA_COMPILE_TASK,
    GRADLE_KAPT_STUBS_TASK,
    GRADLE_KAPT_TASK,
    GRADLE_OUTPUT_BACKUP,
    GRADLE_KOTLIN_COMPILE_TASK,
        GRADLE_DAEMON_CONNECTION(GRADLE_KOTLIN_COMPILE_TASK),
        GRADLE_CLEAR_JAR_CACHE(GRADLE_KOTLIN_COMPILE_TASK),
        GRADLE_KOTLIN_COMPILER(GRADLE_KOTLIN_COMPILE_TASK),
        IC_ALL(GRADLE_KOTLIN_COMPILE_TASK),
            IC_CALCULATE_INITIAL_DIRTY_SET(IC_ALL),
                IC_ANALYZE_CHANGES_IN_DEPENDENCIES(IC_CALCULATE_INITIAL_DIRTY_SET),
                IC_ANALYZE_CHANGES_IN_JAVA_SOURCES(IC_CALCULATE_INITIAL_DIRTY_SET),
                IC_ANALYZE_CHANGES_IN_ANDROID_LAYOUTS(IC_CALCULATE_INITIAL_DIRTY_SET),
                IC_DETECT_REMOVED_CLASSES(IC_CALCULATE_INITIAL_DIRTY_SET),
                IC_FIND_HISTORY_FILES(IC_CALCULATE_INITIAL_DIRTY_SET),
                IC_ANALYZE_HISTORY_FILES(IC_CALCULATE_INITIAL_DIRTY_SET),
            IC_UPDATE_CACHES(IC_ALL),
            COMPILE_ITERATION(IC_ALL),
            IC_WRITE_HISTORY_FILE(IC_ALL);

    companion object {
        const val serialVersionUID = 0L

        val children by lazy {
            values().filter { it.parent != null }.groupBy { it.parent }
        }
    }
}