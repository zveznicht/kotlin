/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.visitors

import org.jetbrains.kotlin.fir.FirElement

interface GenericVisitor {
    operator fun <T : FirElement, C> invoke(node: FirElement, context: C): Boolean
}

fun <T : FirElement, C> emptyVisitor(): (FirElement, C) -> Boolean = { _,_ -> true }
fun <T : FirElement, C> failVisitor(): (FirElement, C) -> Boolean = { _,_ -> false }

inline fun <T : FirElement, C> seqVisitor(
    crossinline visitor1: (FirElement, C) -> Boolean,
    crossinline visitor2: (FirElement, C) -> Boolean
): (FirElement, C) -> Boolean = { node: FirElement, context: C ->
    visitor1(node, context)
    visitor2(node, context)
    true
}

inline fun <T : FirElement, C> andVisitor(
    crossinline visitor1: (FirElement, C) -> Boolean,
    crossinline visitor2: (FirElement, C) -> Boolean
): (FirElement, C) -> Boolean = { node: FirElement, context: C ->
    visitor1(node, context) && visitor2(node, context)
}

inline fun <T : FirElement, C> orVisitor(
    crossinline visitor1: (FirElement, C) -> Boolean,
    crossinline visitor2: (FirElement, C) -> Boolean
): (FirElement, C) -> Boolean = { node: FirElement, context: C ->
    visitor1(node, context) || visitor2(node, context)
}

inline fun <T : FirElement, C> allChildrenVisitor(
    crossinline visitor: (FirElement, C) -> Boolean
): (FirElement, C) -> Boolean = { node: FirElement, context: C ->
    for (child in node.children) {
        visitor(child, context)
    }
    true
}

inline fun <T : FirElement, C> findChildrenVisitor(
    crossinline visitor: (FirElement, C) -> Boolean
): (FirElement, C) -> Boolean = { node: FirElement, context: C ->
    node.children.firstOrNull { child ->
        visitor(child, context)
    }?.let {
        true
    } ?: false
}

