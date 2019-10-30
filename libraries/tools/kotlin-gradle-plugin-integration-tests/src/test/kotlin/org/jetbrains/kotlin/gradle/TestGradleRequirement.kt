/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.gradle

import org.junit.Assume

enum class TestGradle(private val customVersionString: String? = null) {
    // Values must be sorted!
    v4_9,
    v4_10_2,
    v5_0,
    v5_1,
    v5_2,
    v5_3_RC_1("5.3-rc-1"),
    v5_3_RC_2("5.3-rc-2");

    override fun toString() =
        customVersionString
            ?: name.removePrefix("v").replace("_", ".")

    companion object {
        fun fromString(version: String): TestGradle =
            values().firstOrNull { version == it.toString() } ?: error("Version $version not found")

        val OLDEST = values().min()!!
        val NEWEST = values().max()!!
    }
}