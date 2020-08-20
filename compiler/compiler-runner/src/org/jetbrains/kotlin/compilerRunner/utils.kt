/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.compilerRunner

import com.intellij.openapi.util.io.FileUtil
import com.intellij.util.text.StringFactory
import org.jetbrains.kotlin.cli.config.common.ExitCode
import org.jetbrains.kotlin.cli.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.messages.MessageCollector
import java.io.*
import java.nio.charset.StandardCharsets

fun reportInternalCompilerError(messageCollector: MessageCollector) {
    messageCollector.report(CompilerMessageSeverity.ERROR, "Compiler terminated with internal error")
}

fun processCompilerOutput(
    environment: CompilerEnvironment,
    stream: ByteArrayOutputStream,
    exitCode: ExitCode?
) {
    processCompilerOutput(environment.messageCollector, environment.outputItemsCollector, stream, exitCode)
}

fun processCompilerOutput(
    messageCollector: MessageCollector,
    outputItemsCollector: OutputItemsCollector,
    stream: ByteArrayOutputStream,
    exitCode: ExitCode?
) {
    val reader = BufferedReader(StringReader(stream.toString()))
    CompilerOutputParser.parseCompilerMessagesFromReader(messageCollector, reader, outputItemsCollector)

    if (ExitCode.INTERNAL_ERROR == exitCode) {
        reportInternalCompilerError(messageCollector)
    }
}

@Throws(IOException::class)
fun loadTextAndClose(stream: InputStream): String {
    return loadTextAndClose((InputStreamReader(stream, StandardCharsets.UTF_8) as Reader))
}

@Throws(IOException::class)
fun loadTextAndClose(reader: Reader): String {
    return reader.use { reader ->
        StringFactory.createShared(FileUtil.adaptiveLoadText(reader))
    }
}
