/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package test.collections.guava

import com.google.common.collect.testing.MapTestSuiteBuilder
import com.google.common.collect.testing.TestMapGenerator
import com.google.common.collect.testing.TestStringMapGenerator
import com.google.common.collect.testing.features.CollectionFeature
import com.google.common.collect.testing.features.CollectionSize
import com.google.common.collect.testing.features.Feature
import com.google.common.collect.testing.features.MapFeature
import kotlin.collections.builders.MapBuilder

class MapBuilderTest : GuavaBaseTest() {
    private fun <K, V> runImmutableMapTestsUsing(generator: TestMapGenerator<K, V>) {
        val features = mutableListOf<Feature<*>>(
            CollectionSize.ANY,
            MapFeature.ALLOWS_NULL_KEYS,
            MapFeature.ALLOWS_NULL_VALUES,
            MapFeature.ALLOWS_NULL_ENTRY_QUERIES,
            CollectionFeature.KNOWN_ORDER/*,
                MapFeature.REJECTS_DUPLICATES_AT_CREATION*/
        )

        MapTestSuiteBuilder
            .using(generator)
            .named(generator.javaClass.simpleName)
            .withFeatures(features)
            .createTestSuite()
            .run { runTestSuite(this) }
    }

    private fun <K, V> runMutableMapTestsUsing(generator: TestMapGenerator<K, V>) {
        val features = mutableListOf<Feature<*>>(
            CollectionSize.ANY,
            MapFeature.ALLOWS_NULL_KEYS,
            MapFeature.ALLOWS_NULL_VALUES,
            MapFeature.ALLOWS_NULL_ENTRY_QUERIES,
            MapFeature.GENERAL_PURPOSE,
            CollectionFeature.SUPPORTS_ITERATOR_REMOVE, // iteration over entries/keys/values
            CollectionFeature.SUPPORTS_REMOVE,
            CollectionFeature.KNOWN_ORDER/*,
                MapFeature.REJECTS_DUPLICATES_AT_CREATION*/
        )

        MapTestSuiteBuilder
            .using(generator)
            .named(generator.javaClass.simpleName)
            .withFeatures(features)
            .createTestSuite()
            .run { runTestSuite(this) }
    }

    @kotlin.test.Test
    fun mapBuilder() {
        listOf(
            MapGenerator.Builder.PutAll,
            MapGenerator.Builder.InitialCapacity
        ).forEach {
            runMutableMapTestsUsing(it)
        }
    }

    @kotlin.test.Test
    fun builtMap() {
        listOf(
            MapGenerator.Built.PutAll,
            MapGenerator.Built.InitialCapacity
        ).forEach {
            runImmutableMapTestsUsing(it)
        }
    }
}

@UseExperimental(ExperimentalStdlibApi::class)
private class MapGenerator {
    object Built {
        object PutAll : TestStringMapGenerator() {
            override fun create(entries: Array<out Map.Entry<String, String>>): Map<String, String> {
                return buildMap {
                    putAll(entries.map { it.key to it.value })
                }
            }
        }

        object InitialCapacity : TestStringMapGenerator() {
            override fun create(entries: Array<out Map.Entry<String, String>>): Map<String, String> {
                return buildMap(entries.size) {
                    entries.forEach { put(it.key, it.value) }
                }
            }
        }
    }

    object Builder {
        object PutAll : TestStringMapGenerator() {
            override fun create(entries: Array<out Map.Entry<String, String>>): MutableMap<String, String> {
                return MapBuilder<String, String>().apply {
                    putAll(entries.map { it.key to it.value })
                }
            }
        }

        object InitialCapacity : TestStringMapGenerator() {
            override fun create(entries: Array<out Map.Entry<String, String>>): MutableMap<String, String> {
                return MapBuilder<String, String>(entries.size).apply {
                    entries.forEach { put(it.key, it.value) }
                }
            }
        }
    }
}