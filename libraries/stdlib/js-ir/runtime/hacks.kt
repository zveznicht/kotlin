/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin

import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@PublishedApi
internal fun throwUninitializedPropertyAccessException(name: String): Nothing =
    throw UninitializedPropertyAccessException("lateinit property $name has not been initialized")

@PublishedApi
internal fun throwKotlinNothingValueException(): Nothing =
    throw KotlinNothingValueException()

internal fun noWhenBranchMatchedException(): Nothing = throw NoWhenBranchMatchedException()

internal fun THROW_ISE(): Nothing {
    throw IllegalStateException()
}

internal fun THROW_CCE(): Nothing {
    throw ClassCastException()
}

internal fun THROW_NPE(): Nothing {
    throw NullPointerException()
}

internal fun THROW_IAE(msg: String): Nothing {
    throw IllegalArgumentException(msg)
}

internal fun <T:Any> ensureNotNull(v: T?): T = if (v == null) THROW_NPE() else v

internal suspend fun loadModule(forModule: String, module: String) {
    val loaderInfo: LoaderInfo = moduleInitializers["$forModule:$module"] ?: return
    if (loaderInfo.loaded) return

    suspendCoroutine<Unit> { cont ->
        loaderInfo.loader().then({ cont.resume(Unit) }, { t -> cont.resumeWithException(Error("Could not load module $module", t))})
    }
}

private val moduleInitializers = js("{}")

internal class LoaderInfo(
    val loader: () -> dynamic,
    var loaded: Boolean = false
)

internal fun setModuleLoader(forModule: String, module: String, loader: () -> dynamic) {
    moduleInitializers["$forModule:$module"] = LoaderInfo(loader)
}

internal fun isLoaded(forModule: String, module: String, triggerLoading: Boolean): Boolean {
    val loaderInfo: LoaderInfo = moduleInitializers["$forModule:$module"] ?: return true
    val loaded = loaderInfo.loaded
    if (triggerLoading && !loaded) {
        loaderInfo.loader() // should run async
    }
    return loaded
}

internal fun reportLoaded(forModule: String, module: String) {
    val loaderInfo: LoaderInfo = moduleInitializers["$forModule:$module"] ?: return
    loaderInfo.loaded = true
}