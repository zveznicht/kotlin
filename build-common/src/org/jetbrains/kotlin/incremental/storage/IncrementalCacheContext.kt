/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental.storage

import org.jetbrains.kotlin.build.report.ICReporter

data class IncrementalCacheContext(
    val pathConverter: FileToPathConverter,
    val storageProvider: StorageProvider,
    val reporter: ICReporter,
    val outputBackup: OutputBackup
)

fun IncrementalCacheContext.readOnlyStorage(): IncrementalCacheContext =
    copy(storageProvider = ReadOnlyStorageProviderWrapper(storageProvider))