/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.js

import kotlin.coroutines.js.internal.EmptyContinuation
import kotlin.coroutines.startCoroutine

internal object ModuleNotLoaded

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class AllowAsyncRefs

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class TriggerModuleLoading


internal fun <T> runIfLoadedImpl(fn: () -> T?, fallback: () -> T): T {
    val result = fn().asDynamic()

    if (ModuleNotLoaded === result) return fallback()

    return result.unsafeCast<T>()
}

/**
 * PROTOTYPE
 */
fun <T> runIfLoaded(@AllowAsyncRefs fn: () -> T, fallback: () -> T): T = runIfLoadedImpl(fn, fallback)

/**
 * PROTOTYPE
 */
fun <T> runIfLoadedAndTriggerLoading(@AllowAsyncRefs @TriggerModuleLoading fn: () -> T, fallback: () -> T): T = runIfLoadedImpl(fn, fallback)

/**
 * PROTOTYPE
 */
fun <T> runIfLoaded(@AllowAsyncRefs fn: () -> T): T? = runIfLoadedImpl(fn) { null }

/**
 * PROTOTYPE
 */
fun <T> runIfLoadedAndTriggerLoading(@AllowAsyncRefs @TriggerModuleLoading fn: () -> T): T? = runIfLoadedImpl(fn) { null }

/**
 * PROTOTYPE
 *
 * We need a way to run simple suspend code without kotlinx.coroutines
 */
fun launchAndForget(fn: suspend () -> Unit) {
    fn.startCoroutine(EmptyContinuation)
}

