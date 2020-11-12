/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.parcelize.test

import org.jetbrains.kotlin.checkers.AbstractDiagnosticsTest
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.parcelize.ParcelizeComponentRegistrar

abstract class AbstractParcelizeDiagnosticsTest : AbstractDiagnosticsTest() {
    override fun setupEnvironment(environment: KotlinCoreEnvironment) {
        ParcelizeComponentRegistrar.registerParcelizeComponents(environment.project)
        addParcelizeRuntimeLibrary(environment)
        addAndroidJarLibrary(environment)
    }
}