/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.codeInliner

import com.intellij.util.containers.stopAfter

// FIX ME WHEN BUNCH 192 REMOVED (inline me)
inline fun <T> Iterator<T>.stopAfter(crossinline predicate: (T) -> Boolean): Iterator<T> = stopAfter(predicate)