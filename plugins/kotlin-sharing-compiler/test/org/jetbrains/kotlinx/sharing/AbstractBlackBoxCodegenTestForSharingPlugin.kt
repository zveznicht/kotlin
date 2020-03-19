/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing

import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.codegen.AbstractBlackBoxCodegenTest
import org.jetbrains.kotlin.test.TargetBackend

abstract class AbstractBlackBoxCodegenTestForSharingPlugin : AbstractBlackBoxCodegenTest() {
    override fun setupEnvironment(environment: KotlinCoreEnvironment) = environment.setupForSharingPlugin()

    override fun getBackend(): TargetBackend = TargetBackend.JVM_IR
}