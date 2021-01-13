/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.utils

import kotlin.reflect.KProperty

class ThreadLocalWithInitialValue<T>(private val initial: T): ThreadLocal<T>() {
    override fun initialValue(): T = initial
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> ThreadLocal<T>.getValue(thisRef: Any?, property: KProperty<*>): T = get()

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> ThreadLocal<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    set(value)
}

