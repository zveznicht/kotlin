/*
 * Copyright 2000-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.pill.generateCompilerTests;

import org.jetbrains.kotlin.generators.tests.*;
import org.jetbrains.kotlin.generators.tests.generator.InconsistencyChecker;

import java.util.List;

public class GenerateCompilerTests {
    protected void run(String[] args) {
        GenerateCompilerTestsKt.main(args);
        GenerateJsTestsKt.main(args);
        GenerateJava8TestsKt.main(args);
        GenerateRuntimeDescriptorTestsKt.main(args);
    }

    public static void main(String[] args) {
        new GenerateCompilerTests().run(args);
        checkDryRun(args);
    }

    private static void checkDryRun(String[] args) {
        boolean dryRun = InconsistencyChecker.Companion.hasDryRunArg(args);
        List<String> affectedFiles = InconsistencyChecker.Companion.inconsistencyChecker(dryRun).getAffectedFiles();
        int size = affectedFiles.size();
        if (size > 0) {
            throw new IllegalStateException("There " + (size == 1 ? "is a test" : "are " + size + " tests") + " to be regenerated:\n"
                                            + String.join("\n", affectedFiles));
        }
    }
}
