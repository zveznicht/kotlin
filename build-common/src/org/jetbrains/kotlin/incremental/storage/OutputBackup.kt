/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental.storage

import java.io.File

interface OutputBackup {
    fun backupBeforeRemoval(file: File)
    fun newOutput(file: File)
    fun restore()

    object DoNothing : OutputBackup {
        override fun backupBeforeRemoval(file: File) {
        }

        override fun newOutput(file: File) {
        }

        override fun restore() {
        }
    }
}