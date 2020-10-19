/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.descriptors.commonizer

import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.library.SerializedMetadata
import java.io.File

sealed class Result {
    object NothingToCommonize : Result()

    class Commonized(
        val modulesByTargets: Map<Target, Collection<ModuleResult>>
    ) : Result() {
        val sharedTarget: SharedTarget by lazy { modulesByTargets.keys.filterIsInstance<SharedTarget>().single() }
        val leafTargets: Set<LeafTarget> by lazy { modulesByTargets.keys.filterIsInstance<LeafTarget>().toSet() }
    }
}

sealed class ModuleResult {
    class Absent(val originalLocation: File) : ModuleResult()
    class Commonized(val module: ModuleDescriptor, val metadata: LibraryMetadata) : ModuleResult()
}

class LibraryMetadata(
    val libraryName: String,
    val metadata: SerializedMetadata
)
