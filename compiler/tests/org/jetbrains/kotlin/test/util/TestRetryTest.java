/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.test.util;

import junit.framework.TestCase;

public class TestRetryTest extends TestCase {
    private static boolean flag = false;

    public void test() {
        flag = true;
    }

    public void testFlacky() {
        assertFalse(flag);
    }
}
