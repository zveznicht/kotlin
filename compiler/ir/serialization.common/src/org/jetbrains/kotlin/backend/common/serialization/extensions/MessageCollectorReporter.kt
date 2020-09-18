/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.common.serialization.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrPluginDiagnosticReporter
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector

class MessageCollectorReporter(private val messageCollector: MessageCollector) : IrPluginDiagnosticReporter {

    companion object {
        private fun pluginSeverityToCLISeverity(severity: IrPluginDiagnosticReporter.Severity): CompilerMessageSeverity {
            return when (severity) {
                IrPluginDiagnosticReporter.Severity.INFO -> CompilerMessageSeverity.INFO
                IrPluginDiagnosticReporter.Severity.WARNING -> CompilerMessageSeverity.WARNING
                IrPluginDiagnosticReporter.Severity.ERROR -> CompilerMessageSeverity.ERROR
            }
        }

        private fun pluginLocationToCLILocation(location: IrPluginDiagnosticReporter.Location?): CompilerMessageLocation? {
            return location?.run {
                CompilerMessageLocation.Companion.create(filePath, line, column, null)
            }
        }
    }

    override fun report(severity: IrPluginDiagnosticReporter.Severity, message: String, location: IrPluginDiagnosticReporter.Location?) {
        messageCollector.report(
            pluginSeverityToCLISeverity(severity),
            "[Plugin] $message",
            pluginLocationToCLILocation(location)
        )
    }
}