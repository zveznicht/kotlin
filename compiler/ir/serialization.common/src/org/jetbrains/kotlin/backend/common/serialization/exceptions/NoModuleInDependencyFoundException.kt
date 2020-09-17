/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.common.serialization.exceptions

import org.jetbrains.kotlin.backend.common.serialization.IrModuleDeserializer
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.ir.util.IdSignature

class NoModuleInDependencyFoundException internal constructor(
    val signature: IdSignature,
    val currentModule: ModuleDescriptor,
    dependencies: Collection<IrModuleDeserializer>
) : Exception(
    renderMessage(signature, currentModule, dependencies)
) {

    companion object {
        private fun renderMessage(
            signature: IdSignature,
            currentModule: ModuleDescriptor,
            dependencies: Collection<IrModuleDeserializer>
        ): String {
            val sb = StringBuilder()
            sb.append("Module $currentModule has reference $signature, unfortunately neither itself nor it dependencies ")
            sb.append(dependencies.joinToString(", ", "[", "]") { it.toString() })
            sb.append(" contains this declaration")
            sb.append("\n")
            sb.append("Please check that project configuration is correct and has required dependencies.")
            return sb.toString()
        }
    }
}