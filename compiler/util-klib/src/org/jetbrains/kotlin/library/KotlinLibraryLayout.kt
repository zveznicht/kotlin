/**
 * Copyright 2010-2019 JetBrains s.r.o.
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

package org.jetbrains.kotlin.library

import org.jetbrains.kotlin.konan.file.AbstractFile
import org.jetbrains.kotlin.konan.file.File

const val KLIB_MANIFEST_FILE_NAME = "manifest"
const val KLIB_MODULE_METADATA_FILE_NAME = "module"
const val KLIB_IR_FOLDER_NAME = "ir"

/**
 * This scheme describes the Kotlin/Native Library (KLIB) layout.
 */

// TODO: Do something with these ugly casts.
// TODO: Questions:
//      Can we make mutable and immutable library layouts?
//      Does it worth making AbstractFile at all?
interface KotlinLibraryLayout<out T : AbstractFile> {
    val libDir: T
    val libraryName: String
        get() = libDir.path
    val component: String?
    val componentDir: T
        get() = libDir.child(component!!) as T
    val manifestFile: T
        get() = componentDir.child(KLIB_MANIFEST_FILE_NAME) as T
    val resourcesDir: T
        get() = componentDir.child("resources") as T
    val pre_1_4_manifest: T
        get() = libDir.child(KLIB_MANIFEST_FILE_NAME) as T
}

interface MetadataKotlinLibraryLayout<out T : AbstractFile> : KotlinLibraryLayout<T> {
    val metadataDir: T
        get() = componentDir.child("linkdata") as T
    val moduleHeaderFile: T
        get() = metadataDir.child(KLIB_MODULE_METADATA_FILE_NAME) as T

    fun packageFragmentsDir(packageName: String): T =
        metadataDir.child(if (packageName == "") "root_package" else "package_$packageName") as T

    fun packageFragmentFile(packageFqName: String, partName: String): T =
        packageFragmentsDir(packageFqName).child("$partName$KLIB_METADATA_FILE_EXTENSION_WITH_DOT") as T
}

interface IrKotlinLibraryLayout<T : AbstractFile> : KotlinLibraryLayout<T> {
    val irDir: T
        get() = componentDir.child(KLIB_IR_FOLDER_NAME) as T
    val irDeclarations: T
        get() = irDir.child("irDeclarations.knd") as T
    val irTypes: T
        get() = irDir.child("types.knt") as T
    val irSignatures: T
        get() = irDir.child("signatures.knt") as T
    val irStrings: T
        get() = irDir.child("strings.knt") as T
    val irBodies: T
        get() = irDir.child("bodies.knb") as T
    val irFiles: T
        get() = irDir.child("files.knf") as T
    val dataFlowGraphFile: T
        get() = irDir.child("module_data_flow_graph") as T

    fun irDeclarations(file: T): T = file.child("irCombined.knd") as T
    fun irTypes(file: T): T = file.child("types.knt") as T
    fun irSignatures(file: T): T = file.child("signatures.knt") as T
    fun irStrings(file: T): T = file.child("strings.knt") as T
    fun irBodies(file: T): T = file.child("body.knb") as T
    fun irFile(file: T): T = file.child("file.knf") as T
}
