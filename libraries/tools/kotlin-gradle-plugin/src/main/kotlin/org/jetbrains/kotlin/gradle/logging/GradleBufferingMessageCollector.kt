/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.logging

import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import java.util.ArrayList

internal class GradleBufferingMessageCollector : MessageCollector {
    class MessageData(
        val severity: CompilerMessageSeverity,
        val message: String,
        val location: CompilerMessageLocation?
    )

    private val messages = ArrayList<MessageData>()

    @Synchronized
    override fun report(severity: CompilerMessageSeverity, message: String, location: CompilerMessageLocation?) {
        messages.add(MessageData(severity, message, location))
    }

    @Synchronized
    override fun hasErrors() =
        messages.any { it.severity.isError }

    @Synchronized
    override fun clear() {
        messages.clear()
    }

    @Synchronized
    fun forEachMessage(fn: (MessageData) -> Unit) {
        messages.forEach(fn)
    }

    @Synchronized
    fun flush(delegate: MessageCollector) {
        messages.forEach {
            delegate.report(it.severity, it.message, it.location)
        }
        clear()
    }
}