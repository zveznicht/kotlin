/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package test.collections.guava

import com.google.common.collect.testing.ListTestSuiteBuilder
import com.google.common.collect.testing.TestListGenerator
import com.google.common.collect.testing.TestStringListGenerator
import com.google.common.collect.testing.features.CollectionFeature
import com.google.common.collect.testing.features.CollectionSize
import com.google.common.collect.testing.features.ListFeature
import kotlin.collections.builders.ListBuilder

class BuilderGuavaTest : GuavaBaseTest() {

    private fun <E> runImmutableListTestsUsing(generator: TestListGenerator<E>) {
        ListTestSuiteBuilder
            .using(generator)
            .named(generator.javaClass.simpleName)
            .withFeatures(
                CollectionSize.ANY,
                CollectionFeature.ALLOWS_NULL_VALUES
            )
            .createTestSuite()
            .run { runTestSuite(this) }
    }

    private fun <E> runMutableListTestsUsing(generator: TestListGenerator<E>) {
        ListTestSuiteBuilder
            .using(generator)
            .named(generator.javaClass.simpleName)
            .withFeatures(
                CollectionSize.ANY,
                CollectionFeature.ALLOWS_NULL_VALUES,
                ListFeature.GENERAL_PURPOSE
            )
            .createTestSuite()
            .run { runTestSuite(this) }
    }

    @kotlin.test.Test
    fun listBuilder() {
        listOf(
            ListGenerator.Builder.AddAll,
            ListGenerator.Builder.InitialCapacity,
            ListGenerator.Builder.HeadSubList,
            ListGenerator.Builder.TailSubList,
            ListGenerator.Builder.MiddleSubList
        ).forEach {
            runMutableListTestsUsing(it)
        }
    }

    @kotlin.test.Test
    fun builtList() {
        listOf(
            ListGenerator.Built.AddAll,
            ListGenerator.Built.InitialCapacity,
            ListGenerator.Built.HeadSubList,
            ListGenerator.Built.TailSubList,
            ListGenerator.Built.MiddleSubList
        ).forEach {
            runImmutableListTestsUsing(it)
        }
    }
}

@UseExperimental(ExperimentalStdlibApi::class)
private class ListGenerator {
    object Built {
        object AddAll : TestStringListGenerator() {
            override fun create(elements: Array<out String>): List<String> {
                return buildList {
                    addAll(elements.toList())
                }
            }
        }

        object InitialCapacity : TestStringListGenerator() {
            override fun create(elements: Array<out String>): List<String> {
                return buildList(elements.size) {
                    elements.forEach {
                        add(it)
                    }
                }
            }
        }

        object HeadSubList : TestStringListGenerator() {
            override fun create(elements: Array<out String>): List<String> {
                return buildList {
                    addAll(listOf(*elements, "f", "g"))
                }.subList(0, elements.size)
            }
        }

        object TailSubList : TestStringListGenerator() {
            override fun create(elements: Array<String>): List<String> {
                return buildList {
                    addAll(listOf("f", "g", *elements))
                }.subList(2, elements.size + 2)
            }
        }

        object MiddleSubList : TestStringListGenerator() {
            override fun create(elements: Array<String>): List<String> {
                return buildList {
                    addAll(listOf("f", "g", *elements, "h", "i"))
                }.subList(2, elements.size + 2)
            }
        }
    }

    object Builder {
        object AddAll : TestStringListGenerator() {
            override fun create(elements: Array<out String>): MutableList<String> {
                return ListBuilder<String>().apply { addAll(elements.toList()) }
            }
        }

        object InitialCapacity : TestStringListGenerator() {
            override fun create(elements: Array<out String>): MutableList<String> {
                return ListBuilder<String>(elements.size).apply { elements.forEach { add(it) } }
            }
        }

        object HeadSubList : TestStringListGenerator() {
            override fun create(elements: Array<out String>): MutableList<String> {
                return ListBuilder<String>()
                    .apply { addAll(listOf(*elements, "f", "g")) }
                    .subList(0, elements.size)
            }
        }

        object TailSubList : TestStringListGenerator() {
            override fun create(elements: Array<String>): MutableList<String> {
                return ListBuilder<String>()
                    .apply { addAll(listOf("f", "g", *elements)) }
                    .subList(2, elements.size + 2)
            }
        }

        object MiddleSubList : TestStringListGenerator() {
            override fun create(elements: Array<String>): MutableList<String> {
                return ListBuilder<String>()
                    .apply { addAll(listOf("f", "g", *elements, "h", "i")) }
                    .subList(2, elements.size + 2)
            }
        }
    }
}