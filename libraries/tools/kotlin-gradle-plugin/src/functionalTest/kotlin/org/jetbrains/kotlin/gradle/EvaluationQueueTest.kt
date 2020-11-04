/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

// TODO KT-34102
@file:Suppress("invisible_reference", "invisible_member", "FunctionName")

package org.jetbrains.kotlin.gradle

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertSame
import org.jetbrains.kotlin.gradle.utils.afterEvaluationQueue

class EvaluationQueueTest {

    @Test
    fun `convention returns same instance`() {
        val project = ProjectBuilder.builder().build()
        assertSame(project.afterEvaluationQueue, project.afterEvaluationQueue)
    }
}
