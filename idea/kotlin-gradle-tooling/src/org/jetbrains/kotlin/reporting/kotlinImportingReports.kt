/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle

import java.io.Serializable

sealed class KotlinImportingReport : Serializable

class OrphanSourceSetsImportingReport(val sourceSetName: String) : KotlinImportingReport() {
    val message: String
        get() = "[sync warning] Source set \"$sourceSetName\" is not compiled with any compilation. This source set is not imported in the IDE."

    companion object {
        fun generateReportMessage(reports: Collection<OrphanSourceSetsImportingReport>) =
            """
                One or more Kotlin source set orphans were configured but not added to any Kotlin compilation:
                ${reports.joinToString("\n") { it.sourceSetName }}
                You can add a source set to a target's compilation by connecting it with the compilation's default source set using 'dependsOn'.
                See https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#connecting-source-sets
            """
    }
}

class ErroneousImportingReport(val cause: String, @Transient val throwable: Throwable? = null) : KotlinImportingReport()