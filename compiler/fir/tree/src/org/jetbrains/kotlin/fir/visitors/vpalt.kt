/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.visitors

interface GenericTreeNode {
    val children: Iterable<GenericTreeNode>
}

interface GenericVisitor {
    operator fun <T : GenericTreeNode, C> invoke(node: GenericTreeNode, context: C): Boolean
}

fun <T : GenericTreeNode, C> emptyVisitor(): (GenericTreeNode, C) -> Boolean = { _,_ -> true }
fun <T : GenericTreeNode, C> failVisitor(): (GenericTreeNode, C) -> Boolean = { _,_ -> false }

inline fun <T : GenericTreeNode, C> seqVisitor(
    crossinline visitor1: (GenericTreeNode, C) -> Boolean,
    crossinline visitor2: (GenericTreeNode, C) -> Boolean
): (GenericTreeNode, C) -> Boolean = { node: GenericTreeNode, context: C ->
    visitor1(node, context)
    visitor2(node, context)
    true
}

inline fun <T : GenericTreeNode, C> andVisitor(
    crossinline visitor1: (GenericTreeNode, C) -> Boolean,
    crossinline visitor2: (GenericTreeNode, C) -> Boolean
): (GenericTreeNode, C) -> Boolean = { node: GenericTreeNode, context: C ->
    visitor1(node, context) && visitor2(node, context)
}

inline fun <T : GenericTreeNode, C> orVisitor(
    crossinline visitor1: (GenericTreeNode, C) -> Boolean,
    crossinline visitor2: (GenericTreeNode, C) -> Boolean
): (GenericTreeNode, C) -> Boolean = { node: GenericTreeNode, context: C ->
    visitor1(node, context) || visitor2(node, context)
}

inline fun <T : GenericTreeNode, C> allChildrenVisitor(
    crossinline visitor: (GenericTreeNode, C) -> Boolean
): (GenericTreeNode, C) -> Boolean = { node: GenericTreeNode, context: C ->
    for (child in node.children) {
        visitor(child, context)
    }
    true
}

inline fun <T : GenericTreeNode, C> findChildrenVisitor(
    crossinline visitor: (GenericTreeNode, C) -> Boolean
): (GenericTreeNode, C) -> Boolean = { node: GenericTreeNode, context: C ->
    node.children.firstOrNull { child ->
        visitor(child, context)
    }?.let {
        true
    } ?: false
}

