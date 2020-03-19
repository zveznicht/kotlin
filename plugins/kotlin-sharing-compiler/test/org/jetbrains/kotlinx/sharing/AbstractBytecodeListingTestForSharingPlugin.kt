/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing

import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.codegen.AbstractAsmLikeInstructionListingTest
import org.jetbrains.kotlin.test.TargetBackend
import java.io.File

abstract class AbstractBytecodeListingTestForSharingPlugin : AbstractAsmLikeInstructionListingTest() {
    override fun getExpectedTextFileName(wholeFile: File): String {
        return wholeFile.nameWithoutExtension + ".ir.txt"
    }

    override fun getBackend(): TargetBackend = TargetBackend.JVM_IR

    override fun setupEnvironment(environment: KotlinCoreEnvironment) = environment.setupForSharingPlugin()
}
