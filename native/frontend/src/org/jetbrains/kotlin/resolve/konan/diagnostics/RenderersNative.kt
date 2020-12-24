/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.resolve.konan.diagnostics

import org.jetbrains.kotlin.diagnostics.rendering.Renderer
import org.jetbrains.kotlin.inspections.*

object RenderersNative {
    @JvmField
    val MUTABILITY_RESULT = Renderer<MutabilityResult> { mutabilityResult ->
        referenceChain(mutabilityResult).joinToString(";\n")
    }

    @JvmField
    val MUTABILITY_RESULT_HTML = Renderer<MutabilityResult> { mutabilityResult ->
        referenceChain(mutabilityResult).map {
            "<tr><td>$it</td></tr>"
        }.joinToString(separator = "")
    }

    @JvmStatic
    private fun referenceChain(mutabilityResult: MutabilityResult): Sequence<String> =
        generateSequence(mutabilityResult) {
            if (it is TransitivelyStateful) it.cause else null
        }.map {
            when (it) {
                is Stateful -> {
                    if (it.property == null) {
                        "mutable type ${it.type}"
                    } else {
                        "property '${it.property.name}' with type '${it.property.type}' from type '${it.type}'"
                    }
                }
                is TransitivelyStateful -> {
                    "property '${it.property.name}' with type '${it.property.type}' from type '${it.type}'"
                }
                else -> error("Sealed")
            }
        }

    @JvmField
    val CAPTURED_MUTABLE = Renderer<CapturedCandidate> {
        when (it) {
            is CapturedImplicitReceiver -> "implicit receiver of type '${it.type}'"
            is CapturedValue -> "value of type '${it.type}'"
        }
    }


}