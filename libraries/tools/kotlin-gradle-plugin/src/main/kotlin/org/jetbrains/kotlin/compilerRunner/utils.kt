/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.compilerRunner

import org.jetbrains.kotlin.daemon.common.CompileService

internal fun <T> CompileService.CallResult<T>.getIfGood(): T? =
    if (isGood) get() else null