/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

import org.jetbrains.annotations.NotNull;

public class MessageCollectorUtil {
    public static void reportException(@NotNull MessageCollector messageCollector, @NotNull Throwable exception) {
        messageCollector.report(CompilerMessageSeverity.EXCEPTION, OutputMessageUtil.renderException(exception), null);
    }
}
