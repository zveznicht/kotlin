/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.build.metrics

import java.io.Serializable

enum class BuildAttribute : Serializable {
    REBUILD_REASON,
    EXECUTION_STRATEGY;

    companion object {
        const val serialVersionUID = 0L
    }
}

