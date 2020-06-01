/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package test.collections.guava

import com.google.common.collect.testing.SetTestSuiteBuilder
import com.google.common.collect.testing.TestSetGenerator
import com.google.common.collect.testing.TestStringSetGenerator
import com.google.common.collect.testing.features.CollectionFeature
import com.google.common.collect.testing.features.CollectionSize
import com.google.common.collect.testing.features.Feature
import kotlin.collections.builders.SetBuilder

class SetBuilderTest : GuavaBaseTest() {
    private fun <E> runImmutableSetTestsUsing(generator: TestSetGenerator<E>) {
        val features = mutableListOf<Feature<*>>(
            CollectionSize.ANY,
            CollectionFeature.ALLOWS_NULL_VALUES,
            CollectionFeature.SUBSET_VIEW,
            CollectionFeature.DESCENDING_VIEW,
            CollectionFeature.KNOWN_ORDER/*,
                CollectionFeature.REJECTS_DUPLICATES_AT_CREATION*/
        )

        SetTestSuiteBuilder
            .using(generator)
            .named(generator.javaClass.simpleName)
            .withFeatures(features)
            .createTestSuite()
            .run { runTestSuite(this) }
    }

    private fun <E> runMutableSetTestsUsing(generator: TestSetGenerator<E>) {
        val features = mutableListOf<Feature<*>>(
            CollectionSize.ANY,
            CollectionFeature.ALLOWS_NULL_VALUES,
            CollectionFeature.GENERAL_PURPOSE,
            CollectionFeature.SUBSET_VIEW,
            CollectionFeature.DESCENDING_VIEW,
            CollectionFeature.KNOWN_ORDER/*,
                CollectionFeature.REJECTS_DUPLICATES_AT_CREATION*/
        )

        SetTestSuiteBuilder
            .using(generator)
            .named(generator.javaClass.simpleName)
            .withFeatures(features)
            .createTestSuite()
            .run { runTestSuite(this) }
    }

    @kotlin.test.Test
    fun setBuilder() {
        listOf(
            SetGenerator.Builder.AddAll,
            SetGenerator.Builder.InitialCapacity
        ).forEach {
            runMutableSetTestsUsing(it)
        }
    }

    @kotlin.test.Test
    fun builtSet() {
        listOf(
            SetGenerator.Built.AddAll,
            SetGenerator.Built.InitialCapacity
        ).forEach {
            runImmutableSetTestsUsing(it)
        }
    }
}

@UseExperimental(ExperimentalStdlibApi::class)
private class SetGenerator {
    object Built {
        object AddAll : TestStringSetGenerator() {
            override fun create(elements: Array<out String>): Set<String> {
                return buildSet {
                    addAll(elements.toList())
                }
            }
        }

        object InitialCapacity : TestStringSetGenerator() {
            override fun create(elements: Array<out String>): Set<String> {
                return buildSet(elements.size) {
                    elements.forEach { add(it) }
                }
            }
        }
    }

    object Builder {
        object AddAll : TestStringSetGenerator() {
            override fun create(elements: Array<out String>): MutableSet<String> {
                return SetBuilder<String>().apply { addAll(elements) }
            }
        }

        object InitialCapacity : TestStringSetGenerator() {
            override fun create(elements: Array<out String>): MutableSet<String> {
                return SetBuilder<String>(elements.size).apply { elements.forEach { add(it) } }
            }
        }
    }
}