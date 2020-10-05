/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config.data.configurator

import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.config.data.CompilerArgumentsData
import org.jetbrains.kotlin.platform.TargetPlatform
import org.jetbrains.kotlin.utils.addToStdlib.runIf

class CompilerArgumentsDataLanguageFeaturesConfigurator(
    override val compilerArgumentsData: CompilerArgumentsData,
    val targetPlatform: TargetPlatform,
    val collector: MessageCollector
) :
    CompilerArgumentsDataConfigurator<MutableMap<LanguageFeature, LanguageFeature.State>> {
    override fun configure(configuration: MutableMap<LanguageFeature, LanguageFeature.State>) {
        configuration.apply {
            runIf(MULTI_PLATFORM.getFlag()) {
                put(LanguageFeature.MultiPlatformProjects, LanguageFeature.State.ENABLED)
            }

            when (compilerArgumentsData.coroutinesState) {
                CommonCompilerArguments.ERROR -> put(LanguageFeature.Coroutines, LanguageFeature.State.ENABLED_WITH_ERROR)
                CommonCompilerArguments.ENABLE -> put(LanguageFeature.Coroutines, LanguageFeature.State.ENABLED)
                CommonCompilerArguments.WARN, CommonCompilerArguments.DEFAULT -> {
                }
                else -> collector.report(
                    CompilerMessageSeverity.ERROR,
                    "Invalid value of -Xcoroutines (should be: enable, warn or error): ${compilerArgumentsData.coroutinesState}",
                    null
                )

            }

            runIf(NEW_INFERENCE.getFlag()) {
                put(LanguageFeature.NewInference, LanguageFeature.State.ENABLED)
                put(LanguageFeature.SamConversionPerArgument, LanguageFeature.State.ENABLED)
                put(LanguageFeature.FunctionReferenceWithDefaultValueAsOtherType, LanguageFeature.State.ENABLED)
                put(LanguageFeature.DisableCompatibilityModeForNewInference, LanguageFeature.State.ENABLED)
            }

            if (INLINE_CLASSES.getFlag()) {
                put(LanguageFeature.InlineClasses, LanguageFeature.State.ENABLED)
            }

            if (POLYMORPHIC_SIGNATURE.getFlag()) {
                put(LanguageFeature.PolymorphicSignature, LanguageFeature.State.ENABLED)
            }

            if (LEGACY_SMART_CAST_AFTER_TRY.getFlag()) {
                put(LanguageFeature.SoundSmartCastsAfterTry, LanguageFeature.State.DISABLED)
            }

            if (EFFECT_SYSTEM.getFlag()) {
                put(LanguageFeature.UseCallsInPlaceEffect, LanguageFeature.State.ENABLED)
                put(LanguageFeature.UseReturnsEffect, LanguageFeature.State.ENABLED)
            }

            if (READ_DESERIALIZED_CONTRACTS.getFlag()) {
                put(LanguageFeature.ReadDeserializedContracts, LanguageFeature.State.ENABLED)
            }

            if (PROPER_IEEE_754_COMPARISONS.getFlag()) {
                put(LanguageFeature.ProperIeee754Comparisons, LanguageFeature.State.ENABLED)
            }

            if (USE_MIXED_NAMED_ARGUMENTS.getFlag()) {
                put(LanguageFeature.MixedNamedArgumentsInTheirOwnPosition, LanguageFeature.State.ENABLED)
            }

            if (INFERENCE_COMPATIBILITY.getFlag()) {
                put(LanguageFeature.InferenceCompatibility, LanguageFeature.State.ENABLED)
            }

            if (PROGRESSIVE_MODE.getFlag()) {
                LanguageFeature.values().filter { it.kind.enabledInProgressiveMode }.forEach {
                    // Don't overwrite other settings: users may want to turn off some particular
                    // breaking change manually instead of turning off whole progressive mode
                    if (!contains(it)) put(it, LanguageFeature.State.ENABLED)
                }
            }

            // Internal arguments should go last, because it may be useful to override
            // some feature state via -XX (even if some -X flags were passed)
//            if (internalArguments.isNotEmpty()) {
//                configureLanguageFeaturesFromInternalArgs(collector)
//            }
        }
    }

    companion object {
        private const val MULTI_PLATFORM = "-Xmulti-platform"
        private const val NEW_INFERENCE = "-Xnew-inference"
        private const val INLINE_CLASSES = "-Xinline-classes"
        private const val POLYMORPHIC_SIGNATURE = "-Xpolymorphic-signature"
        private const val LEGACY_SMART_CAST_AFTER_TRY = "-Xlegacy-smart-cast-after-try"
        private const val EFFECT_SYSTEM = "-Xeffect-system"
        private const val READ_DESERIALIZED_CONTRACTS = "-Xread-deserialized-contracts"
        private const val PROPER_IEEE_754_COMPARISONS = "-Xproper-ieee754-comparisons"
        private const val USE_MIXED_NAMED_ARGUMENTS = "-Xuse-mixed-named-arguments"
        private const val INFERENCE_COMPATIBILITY = "-Xinference-compatibility"
        private const val PROGRESSIVE_MODE = "-progressive"
    }

}