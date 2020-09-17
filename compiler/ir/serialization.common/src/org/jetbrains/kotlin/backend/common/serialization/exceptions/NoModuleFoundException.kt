/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.common.serialization.exceptions

import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.ir.util.IdSignature
import java.lang.StringBuilder

class NoModuleFoundException(val module: ModuleDescriptor, val signature: IdSignature?) : Exception(renderMessage(module, signature)) {

    companion object {
        private fun renderMessage(module: ModuleDescriptor, signature: IdSignature?): String {
            val sb = StringBuilder("No module deserializer found for ")
            sb.append(module)
            signature?.let {
                sb.append("; It was an attempt to find deserializer for ")
                sb.append(it)
            }
            return sb.toString()
        }
    }
}