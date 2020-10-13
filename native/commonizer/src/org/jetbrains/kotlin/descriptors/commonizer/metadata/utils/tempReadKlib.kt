/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.descriptors.commonizer.metadata.utils

import kotlinx.metadata.klib.fqName
import java.io.File

fun main() {
//    val klib = File("/Users/Dmitriy.Dolovov/temp/commonizer-output2/platform/ios_x64/org.jetbrains.kotlin.native.platform.posix")
//    val klib = File("/Users/Dmitriy.Dolovov/temp/commonizer-output2/platform/ios_x64/org.jetbrains.kotlin.native.platform.posix-NEW")
//    val klib = File("/Users/Dmitriy.Dolovov/.konan/kotlin-native-prebuilt-macos-1.4.10/klib/platform/ios_x64/org.jetbrains.kotlin.native.platform.Network")
    val klib = File("/Users/Dmitriy.Dolovov/IdeaProjects/nativeLib/build/classes/kotlin/native/main/nativeLib.klib")
//    val klib = File("/Users/Dmitriy.Dolovov/temp/2/output")
//    val klib = File("/Users/Dmitriy.Dolovov/.konan/kotlin-native-custom-with-fix/klib/platform/ios_x64/org.jetbrains.kotlin.native.platform.posix")
    val metadata = TrivialLibraryProvider.readLibraryMetadata(klib)

    val fragments = metadata.fragments.filter { it.fqName == "sample" }
//    val fragments = metadata.fragments.filter { it.fqName == "platform.Network" }
//    val fragments = metadata.fragments.filter { it.fqName == "platform.posix" }
    val classes = fragments.flatMap { it.classes }.associateBy { it.name }
    val typeAliases = fragments.flatMap { it.pkg?.typeAliases.orEmpty() }.associateBy { it.name }
    val properties = fragments.flatMap { it.pkg?.properties.orEmpty() }.associateBy { it.name }
    val functions = fragments.flatMap { it.pkg?.functions.orEmpty() }.associateBy { it.name }

    println("Done.")
}
