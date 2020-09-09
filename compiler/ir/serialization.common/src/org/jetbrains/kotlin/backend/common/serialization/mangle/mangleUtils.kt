/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.common.serialization.mangle

import org.jetbrains.kotlin.name.FqName

inline fun <T> Collection<T>.collectForMangler(
    builder: StringBuilder,
    params: MangleConstant,
    crossinline collect: StringBuilder.(T) -> Unit
) {
    collectForManglerChecked(builder, params) { collect(it); true }
}

fun <T> Collection<T>.collectForManglerChecked(builder: StringBuilder, params: MangleConstant, collect: StringBuilder.(T) -> Boolean) {
    var first = true

    builder.append(params.prefix)

    var addSeparator = true

    for (e in this) {
        if (first) {
            first = false
        } else if (addSeparator) {
            builder.append(params.separator)
        }

        addSeparator = builder.collect(e)
    }

    builder.append(params.suffix)
}

internal val publishedApiAnnotation = FqName("kotlin.PublishedApi")
