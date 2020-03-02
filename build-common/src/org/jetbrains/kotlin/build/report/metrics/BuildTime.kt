/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.build.report.metrics

import java.io.Serializable

@Suppress("Reformat")
enum class BuildTime(val parent: BuildTime? = null) : Serializable {
    GRADLE_NON_KOTLIN_TASK,
    GRADLE_KOTLIN_TASK,
        GRADLE_CLEAR_OUTPUT(GRADLE_KOTLIN_TASK),
        GRADLE_OUTPUT_BACKUP(GRADLE_KOTLIN_TASK),
        GRADLE_OUTPUT_BACKUP_RESTORE(GRADLE_KOTLIN_TASK),
        GRADLE_DAEMON_CONNECTION(GRADLE_KOTLIN_TASK),
        GRADLE_CLEAR_JAR_CACHE(GRADLE_KOTLIN_TASK),
        NON_INCREMENTAL_COMPILER(GRADLE_KOTLIN_TASK),
        INCREMENTAL_COMPILER(GRADLE_KOTLIN_TASK),
            IC_CALCULATE_INITIAL_DIRTY_SET(INCREMENTAL_COMPILER),
                IC_ANALYZE_CHANGES_IN_DEPENDENCIES(IC_CALCULATE_INITIAL_DIRTY_SET),
                IC_ANALYZE_CHANGES_IN_JAVA_SOURCES(IC_CALCULATE_INITIAL_DIRTY_SET),
                IC_ANALYZE_CHANGES_IN_ANDROID_LAYOUTS(IC_CALCULATE_INITIAL_DIRTY_SET),
                IC_DETECT_REMOVED_CLASSES(IC_CALCULATE_INITIAL_DIRTY_SET),
                IC_FIND_HISTORY_FILES(IC_CALCULATE_INITIAL_DIRTY_SET),
                IC_ANALYZE_HISTORY_FILES(IC_CALCULATE_INITIAL_DIRTY_SET),
            CLEAR_OUTPUT_ON_REBUILD(INCREMENTAL_COMPILER),
            IC_UPDATE_CACHES(INCREMENTAL_COMPILER),
            INCREMENTAL_ITERATION(INCREMENTAL_COMPILER),
            NON_INCREMENTAL_ITERATION(INCREMENTAL_COMPILER),
            IC_WRITE_HISTORY_FILE(INCREMENTAL_COMPILER);

    companion object {
        const val serialVersionUID = 0L

        val children by lazy {
            values().filter { it.parent != null }.groupBy { it.parent }
        }
    }
}