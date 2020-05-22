/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.logging

import org.jetbrains.kotlin.compilerRunner.KotlinLogger

internal class GradleRecordingKotlinLogger(
    private val messages: MutableList<String>,
    private val isVerbose: Boolean,
    private val delegate: KotlinLogger
) : KotlinLogger {
    override fun error(msg: String) {
        messages.add(msg)
        delegate.error(msg)
    }

    override fun warn(msg: String) {
        messages.add(msg)
        delegate.warn(msg)
    }

    override fun info(msg: String) {
        if (isVerbose) {
            messages.add(msg)
            delegate.info(msg)
        }
    }

    override fun debug(msg: String) {
        if (isDebugEnabled) {
            messages.add(msg)
            delegate.debug(msg)
        }
    }

    override val isDebugEnabled: Boolean
        get() = isVerbose || delegate.isDebugEnabled
}