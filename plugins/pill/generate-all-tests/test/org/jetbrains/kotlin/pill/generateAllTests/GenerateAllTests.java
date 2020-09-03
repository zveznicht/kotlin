/*
 * Copyright 2000-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.pill.generateAllTests;

import org.jetbrains.kotlin.generators.tests.*;
import org.jetbrains.kotlin.pill.generateCompilerTests.GenerateCompilerTests;

public class GenerateAllTests extends GenerateCompilerTests {
    @Override
    protected void run(String[] args) {
        super.run(args);
        GenerateTestsKt.main(args);
    }
}
