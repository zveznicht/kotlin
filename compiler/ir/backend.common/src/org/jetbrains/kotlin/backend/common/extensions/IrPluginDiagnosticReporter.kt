/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.common.extensions

interface IrPluginDiagnosticReporter {
    enum class Severity {
        INFO, WARNING, ERROR
    }

    data class Location(val filePath: String, val line: Int, val column: Int)

    fun report(severity: Severity, message: String, location: Location? = null)
}