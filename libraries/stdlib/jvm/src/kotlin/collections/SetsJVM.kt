/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:kotlin.jvm.JvmMultifileClass
@file:kotlin.jvm.JvmName("SetsKt")
@file:OptIn(kotlin.experimental.ExperimentalTypeInference::class)

package kotlin.collections

import kotlin.collections.builders.SetBuilder
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


/**
 * Returns an immutable set containing only the specified object [element].
 * The returned set is serializable.
 */
public fun <T> setOf(element: T): Set<T> = java.util.Collections.singleton(element)

/**
 * Builds a new read-only [Set] by populating a [MutableSet] using the given [builderAction]
 * and returning a read-only set with the same elements.
 *
 * The set passed as a receiver to the [builderAction] is valid only inside that function.
 * Using it outside of the function produces an unspecified behavior.
 *
 * Elements of the set are iterated in the order they were added by the [builderAction].
 *
 * @sample samples.collections.Builders.Sets.buildSetSample
 */
@SinceKotlin("1.3")
@ExperimentalStdlibApi
@kotlin.internal.InlineOnly
public actual inline fun <E> buildSet(@BuilderInference builderAction: MutableSet<E>.() -> Unit): Set<E> {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return SetBuilder<E>().apply(builderAction).build()
}

/**
 * Builds a new read-only [Set] by populating a [MutableSet] using the given [builderAction]
 * and returning a read-only set with the same elements.
 *
 * The set passed as a receiver to the [builderAction] is valid only inside that function.
 * Using it outside of the function produces an unspecified behavior.
 *
 * [capacity] is used to hint the expected number of elements added in the [builderAction].
 *
 * Elements of the set are iterated in the order they were added by the [builderAction].
 *
 * @throws IllegalArgumentException if the given [capacity] is negative.
 *
 * @sample samples.collections.Builders.Sets.buildSetSample
 */
@SinceKotlin("1.3")
@ExperimentalStdlibApi
@kotlin.internal.InlineOnly
public actual inline fun <E> buildSet(capacity: Int, @BuilderInference builderAction: MutableSet<E>.() -> Unit): Set<E> {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return SetBuilder<E>(capacity).apply(builderAction).build()
}


/**
 * Returns a new [java.util.SortedSet] with the given elements.
 */
public fun <T> sortedSetOf(vararg elements: T): java.util.TreeSet<T> = elements.toCollection(java.util.TreeSet<T>())

/**
 * Returns a new [java.util.SortedSet] with the given [comparator] and elements.
 */
public fun <T> sortedSetOf(comparator: Comparator<in T>, vararg elements: T): java.util.TreeSet<T> = elements.toCollection(java.util.TreeSet<T>(comparator))

