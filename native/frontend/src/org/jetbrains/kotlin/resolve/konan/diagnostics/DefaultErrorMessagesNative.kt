/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.resolve.konan.diagnostics

import org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticFactoryToRendererMap
import org.jetbrains.kotlin.diagnostics.rendering.Renderer
import org.jetbrains.kotlin.diagnostics.rendering.Renderers
import org.jetbrains.kotlin.inspections.*

private val DIAGNOSTIC_FACTORY_TO_RENDERER by lazy {
    DiagnosticFactoryToRendererMap("Native").apply {
        put(ErrorsNative.THROWS_LIST_EMPTY, "@Throws must have non-empty class list")
        put(
            ErrorsNative.INCOMPATIBLE_THROWS_OVERRIDE, "Member overrides different @Throws filter from {0}",
            Renderers.NAME
        )
        put(
            ErrorsNative.INCOMPATIBLE_THROWS_INHERITED, "Member inherits different @Throws filters from {0}",
            Renderers.commaSeparated(Renderers.NAME)
        )
        put(
            ErrorsNative.MISSING_EXCEPTION_IN_THROWS_ON_SUSPEND,
            "@Throws on suspend declaration must have {0} (or any of its superclasses) listed",
            Renderer { it.shortName().asString() }
        )
        put(
            ErrorsNative.INAPPLICABLE_SHARED_IMMUTABLE_PROPERTY,
            "@SharedImmutable is applicable only to val with backing field or to property with delegation"
        )
        put(ErrorsNative.INAPPLICABLE_SHARED_IMMUTABLE_TOP_LEVEL, "@SharedImmutable is applicable only to top level declarations")
        put(
            ErrorsNative.VARIABLE_IN_SINGLETON_WITHOUT_THREAD_LOCAL,
            "Variable in singleton without @ThreadLocal can't be changed after initialization"
        )
        put(ErrorsNative.VARIABLE_IN_ENUM, "Variable in enum class can't be changed after initialization")
        put(
            ErrorsNative.INAPPLICABLE_THREAD_LOCAL,
            "@ThreadLocal is applicable only to property with backing field, to property with delegation or to objects"
        )
        put(ErrorsNative.INAPPLICABLE_THREAD_LOCAL_TOP_LEVEL, "@ThreadLocal is applicable only to top level declarations")
        put(ErrorsNative.FREEZE_WARNING,
            """Captured {1} with mutable state might cause unexpected IncorrectMutabilityException at runtime.
                |Mutable state is accessible via reference chain:
                |{0}.
                |Consider avoiding mutable objects from being captured by thread-confined API.
            """.trimMargin(),
            Renderer<MutabilityResult> { mutabilityResult ->
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
                }.joinToString(";\n")
            },
            Renderer<CapturedCandidate> {
                when (it) {
                    is CapturedImplicitReceiver -> "implicit receiver of type '${it.type}'"
                    is CapturedValue -> "value of type '${it.type}'"
                }
            }
        )
    }
}

class DefaultErrorMessagesNative : DefaultErrorMessages.Extension {
    override fun getMap(): DiagnosticFactoryToRendererMap = DIAGNOSTIC_FACTORY_TO_RENDERER
}
