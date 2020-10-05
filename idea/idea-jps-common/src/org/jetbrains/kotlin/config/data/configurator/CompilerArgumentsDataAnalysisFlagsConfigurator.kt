/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config.data.configurator

import org.jetbrains.kotlin.cli.common.arguments.Jsr305Parser
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.config.*
import org.jetbrains.kotlin.config.data.CompilerArgumentsData
import org.jetbrains.kotlin.platform.TargetPlatform
import org.jetbrains.kotlin.platform.jvm.isJvm

class CompilerArgumentsDataAnalysisFlagsConfigurator(
    override val compilerArgumentsData: CompilerArgumentsData,
    val targetPlatform: TargetPlatform,
    val collector: MessageCollector
) :
    CompilerArgumentsDataConfigurator<MutableMap<AnalysisFlag<*>, Any>> {
    override fun configure(configuration: MutableMap<AnalysisFlag<*>, Any>) {
        configuration.apply {
            put(AnalysisFlags.skipMetadataVersionCheck, SKIP_METADATA_VERSION_CHECK.getFlag())
            put(AnalysisFlags.skipPrereleaseCheck, SKIP_PRERELEASE_CHECK.getFlag() || SKIP_METADATA_VERSION_CHECK.getFlag())
            put(AnalysisFlags.multiPlatformDoNotCheckActual, NO_CHECK_ACTUAL.getFlag())

            val experimentalFqNames = EXPERIMENTAL.getMultiple()?.toList().orEmpty()
            if (experimentalFqNames.isNotEmpty()) {
                put(AnalysisFlags.experimental, experimentalFqNames)
                collector.report(CompilerMessageSeverity.WARNING, "'-Xexperimental' is deprecated and will be removed in a future release")
            }
            put(
                AnalysisFlags.useExperimental,
                USE_EXPERIMENTAL.getMultiple()?.toList().orEmpty() + OPT_IN.getMultiple()?.toList().orEmpty()
            )
            put(AnalysisFlags.expectActualLinker, EXPECT_ACTUAL_LINKER.getFlag())
            put(AnalysisFlags.explicitApiVersion, compilerArgumentsData.apiVersion != null)
            put(AnalysisFlags.allowResultReturnType, ALLOW_RESULT_RETURN_TYPE.getFlag())
            EXPLICIT_API.getSingle()?.let { ExplicitApiMode.fromString(it) }?.also { put(AnalysisFlags.explicitApiMode, it) }
                ?: collector.report(
                    CompilerMessageSeverity.ERROR,
                    "Unknown value for parameter -Xexplicit-api: '${EXPLICIT_API.getSingle()}'. Value should be one of ${ExplicitApiMode.availableValues()}"
                )
            if (!targetPlatform.isJvm()) return

            put(JvmAnalysisFlags.strictMetadataVersionSemantics, STRICT_METADATA_VERSION_SEMANTICS.getFlag())
            put(
                JvmAnalysisFlags.jsr305,
                Jsr305Parser(collector).parse(JSR_305.getMultiple(), SUPPORT_COMPATQUAL_CHECKER_FRAMEWORK_ANNOTATIONS.getSingle())
            )
            put(AnalysisFlags.ignoreDataFlowInAssert, JVMAssertionsMode.fromString(ASSERTIONS.getSingle()) != JVMAssertionsMode.LEGACY)
            JvmDefaultMode.fromStringOrNull(JVM_DEFAULT.getSingle())?.let {
                put(JvmAnalysisFlags.jvmDefaultMode, it)
            } ?: collector.report(
                CompilerMessageSeverity.ERROR,
                "Unknown @JvmDefault mode: ${JVM_DEFAULT.getSingle()}, " +
                        "supported modes: ${JvmDefaultMode.values().map { it.description }}"
            )
            put(JvmAnalysisFlags.inheritMultifileParts, INHERIT_MULTIFILE_PARTS.getFlag())
            put(JvmAnalysisFlags.sanitizeParentheses, SANITIZE_PARENTHESES.getFlag())
            put(JvmAnalysisFlags.suppressMissingBuiltinsError, SUPPRESS_MISSING_BUILTINS_ERROR.getFlag())
            put(JvmAnalysisFlags.irCheckLocalNames, IR_CHECK_LOCAL_NAMES.getFlag())
            put(AnalysisFlags.reportErrorsOnIrDependencies, !USE_IR.getFlag() && !USE_FIR.getFlag() && !ALLOW_JVM_IR_DEPENDENCIES.getFlag())
            put(JvmAnalysisFlags.disableUltraLightClasses, DISABLE_ULTRA_LIGHT_CLASSES.getFlag())
        }
    }

    companion object {
        private const val SKIP_METADATA_VERSION_CHECK = "-Xskip-metadata-version-check"
        private const val SKIP_PRERELEASE_CHECK = "-Xskip-prerelease-check"
        private const val NO_CHECK_ACTUAL = "-Xno-check-actual"
        private const val EXPERIMENTAL = "-Xexperimental"
        private const val USE_EXPERIMENTAL = "-Xuse-experimental"
        private const val OPT_IN = "-Xopt-in"
        private const val EXPECT_ACTUAL_LINKER = "-Xexpect-actual-linker"
        private const val ALLOW_RESULT_RETURN_TYPE = "-Xallow-result-return-type"
        private const val EXPLICIT_API = "-Xexplicit-api"
        private const val STRICT_METADATA_VERSION_SEMANTICS = "-Xgenerate-strict-metadata-version"
        private const val JSR_305 = "-Xjsr305"
        private const val ASSERTIONS = "-Xassertions"
        private const val SUPPORT_COMPATQUAL_CHECKER_FRAMEWORK_ANNOTATIONS = "-Xsupport-compatqual-checker-framework-annotations"
        private const val JVM_DEFAULT = "-Xjvm-default"
        private const val INHERIT_MULTIFILE_PARTS = "-Xmultifile-parts-inherit"
        private const val SANITIZE_PARENTHESES = "-Xsanitize-parentheses"
        private const val SUPPRESS_MISSING_BUILTINS_ERROR = "-Xsuppress-missing-builtins-error"
        private const val IR_CHECK_LOCAL_NAMES = "-Xir-check-local-names"
        private const val USE_IR = "-Xuse-ir"
        private const val USE_FIR = "-Xuse-fir"
        private const val ALLOW_JVM_IR_DEPENDENCIES = "-Xallow-jvm-ir-dependencies"
        private const val DISABLE_ULTRA_LIGHT_CLASSES = "-Xdisable-ultra-light-classes"
    }
}

