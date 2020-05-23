/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental.util

import java.io.PrintWriter
import java.io.StringWriter
import java.lang.Exception

internal fun Exception.addSuppressed(suppressedExceptions: Collection<Exception>) {
    for (ex in suppressedExceptions) {
        this.addSuppressed(ex)
    }
}

internal fun Exception.stacktraceString(): String =
    StringWriter().also { printStackTrace(PrintWriter(it)) }.toString()