/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.build.metrics

import java.io.Serializable

enum class BuildMetric : Serializable {
    GRADLE_KOTLIN_COMPILE_TASK,
    GRADLE_OUTPUT_BACKUP,
    GRADLE_DAEMON_CONNECTION,
    INCREMENTAL_COMPILATION,
    NON_INCREMENTAL_COMPILATION,
    IC_CALCULATE_INITIAL_DIRTY_SET,
    IC_ANALYZE_CHANGES_IN_DEPENDENCIES,
    IC_ANALYZE_CHANGES_IN_JAVA_SOURCES,
    IC_ANALYZE_CHANGES_IN_ANDROID_LAYOUTS,
    IC_DETECT_REMOVED_CLASSES,
    IC_FIND_HISTORY_FILES,
    IC_ANALYZE_HISTORY_FILES,
    IC_UPDATE_CACHES,
    IC_WRITE_HISTORY_FILE,
    COMPILE_ITERATION;

    companion object {
        const val serialVersionUID = 0L
    }
}