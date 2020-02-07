/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package benchmarks.dsl

import java.io.File

class Suite(
    val scenarios: Array<Scenario>,
    val defaultTasks: Array<Tasks>
)

class Scenario(
    val name: String,
    val steps: Array<Step>
)

open class Step(
    val measure: Boolean,
    val changes: Array<FileChange>,
    val tasks: Array<Tasks>?
)

class FileChange(
    private val targetFile: TargetFile,
    private val typeOfChange: TypeOfChange
) {
    private var applied = false
    private lateinit var contentToRevert: String

    fun apply() {
        contentToRevert = targetFile.readAndModify(typeOfChange)
        applied = true
    }

    fun revert() {
        if (applied) {
            targetFile.modify(contentToRevert)
            applied = false
        }
    }
}

enum class TargetFile(private val path: String) {
    CORE_UTIL_STRINGS("core/util.runtime/src/org/jetbrains/kotlin/utils/strings.kt");

    private val file = File(path)

    fun readAndModify(change: TypeOfChange): String =
        file.readText().also {
            val changedFile = File(path + change.fileExtension)
            val newContent = changedFile.readText()
            file.writeText(newContent)
        }

    fun modify(newContent: String) {
        file.writeText(newContent)
    }
}

enum class TypeOfChange {
    ADD_PRIVATE_FUNCTION,
    ADD_PUBLIC_FUNCTION,
    ADD_PRIVATE_CLASS,
    ADD_PUBLIC_CLASS,
    INTRODUCE_COMPILE_ERROR;

    val fileExtension: String
        get() = ".${name.constantCaseToCamelCase()}.benchmark"
}

enum class Tasks(private val customTask: String? = null) {
    CLEAN,
    CORE_UTIL_CLASSES(":core:util.runtime:classes"),
    DIST,
    IDEA_PLUGIN,
    INSTALL,
    CLASSES,
    TEST_CLASSES;

    val path: String
        get() = customTask ?: name.constantCaseToCamelCase()
}