/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.statistics

object CodeHighlightingFUSCollector {

    fun log(highlightingType: HighlightingType, duration: Long, LOC: Int) {
        val data = mapOf(
            "highlighting type" to highlightingType.text,
            "duration" to duration.toString(),
            "lines count" to LOC.toString()
        )

        KotlinFUSLogger.log(FUSEventGroups.Editor, "Highlighting", data)
    }

    enum class HighlightingType(val text: String) {
        CODE_ANALYSIS("Code analysis"), ERROR("Error diagnostics");
    }
}