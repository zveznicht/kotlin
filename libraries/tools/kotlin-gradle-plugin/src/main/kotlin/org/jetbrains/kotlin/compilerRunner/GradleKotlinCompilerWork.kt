/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.compilerRunner

import org.gradle.api.Project
import org.jetbrains.kotlin.cli.common.ExitCode
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.config.Services
import org.jetbrains.kotlin.daemon.common.*
import org.jetbrains.kotlin.gradle.logging.*
import org.jetbrains.kotlin.gradle.plugin.internal.state.TaskExecutionResults
import org.jetbrains.kotlin.gradle.plugin.internal.state.TaskLoggers
import org.jetbrains.kotlin.gradle.report.BuildReportMode
import org.jetbrains.kotlin.gradle.report.TaskExecutionResult
import org.jetbrains.kotlin.gradle.tasks.clearLocalState
import org.jetbrains.kotlin.gradle.tasks.throwGradleExceptionIfError
import org.jetbrains.kotlin.gradle.utils.stackTraceAsString
import org.jetbrains.kotlin.incremental.ChangedFiles
import org.jetbrains.kotlin.incremental.IncrementalModuleInfo
import org.slf4j.LoggerFactory
import java.io.*
import java.lang.Exception
import java.net.URLClassLoader
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import javax.inject.Inject

internal class ProjectFilesForCompilation(
    val projectRootFile: File,
    val clientIsAliveFlagFile: File,
    val sessionFlagFile: File
) : Serializable {
    constructor(project: Project) : this(
        projectRootFile = project.rootProject.projectDir,
        clientIsAliveFlagFile = GradleCompilerRunner.getOrCreateClientFlagFile(project),
        sessionFlagFile = GradleCompilerRunner.getOrCreateSessionFlagFile(project)
    )

    companion object {
        const val serialVersionUID: Long = 0
    }
}

internal class GradleKotlinCompilerWorkArguments(
    val projectFiles: ProjectFilesForCompilation,
    val compilerFullClasspath: List<File>,
    val compilerClassName: String,
    val compilerArgs: Array<String>,
    val isVerbose: Boolean,
    val incrementalCompilationEnvironment: IncrementalCompilationEnvironment?,
    val incrementalModuleInfo: IncrementalModuleInfo?,
    val outputFiles: List<File>,
    val taskPath: String,
    val buildReportMode: BuildReportMode?,
    val kotlinScriptExtensions: Array<String>,
    val checkICCachesAreClosed: Boolean
) : Serializable {
    companion object {
        const val serialVersionUID: Long = 0
    }
}

internal class GradleKotlinCompilerWork @Inject constructor(
    /**
     * Arguments are passed through [GradleKotlinCompilerWorkArguments],
     * because Gradle Workers API does not support nullable arguments (https://github.com/gradle/gradle/issues/2405),
     * and because Workers API does not support named arguments,
     * which are useful when there are many arguments with the same type
     * (to protect against parameters reordering bugs)
     */
    config: GradleKotlinCompilerWorkArguments
) : Runnable {

    companion object {
        init {
            if (System.getProperty("org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.trace.loading") == "true") {
                println("Loaded GradleKotlinCompilerWork")
            }
        }
    }

    private val projectRootFile = config.projectFiles.projectRootFile
    private val clientIsAliveFlagFile = config.projectFiles.clientIsAliveFlagFile
    private val sessionFlagFile = config.projectFiles.sessionFlagFile
    private val compilerFullClasspath = config.compilerFullClasspath
    private val compilerClassName = config.compilerClassName
    private val compilerArgs = config.compilerArgs
    private val isVerbose = config.isVerbose
    private val incrementalCompilationEnvironment = config.incrementalCompilationEnvironment
    private val incrementalModuleInfo = config.incrementalModuleInfo
    private val outputFiles = config.outputFiles
    private val taskPath = config.taskPath
    private val buildReportMode = config.buildReportMode
    private val kotlinScriptExtensions = config.kotlinScriptExtensions
    private val checkICCachesAreClosed = config.checkICCachesAreClosed
    private val buildReportLines = ArrayList<String>()

    private val log =
        getTaskLoggerOrFallBack().let { log ->
            if (buildReportMode == null) log
            else GradleRecordingKotlinLogger(buildReportLines, isVerbose = buildReportMode == BuildReportMode.VERBOSE, delegate = log)
        }

    private fun getTaskLoggerOrFallBack(): KotlinLogger =
        TaskLoggers.get(taskPath)?.let { GradleKotlinLogger(it) }
            ?: run {
                val logger = LoggerFactory.getLogger("GradleKotlinCompilerWork")
                val kotlinLogger = if (logger is org.gradle.api.logging.Logger) {
                    GradleKotlinLogger(logger)
                } else SL4JKotlinLogger(logger)

                kotlinLogger.apply {
                    debug("Could not get logger for '$taskPath'. Falling back to sl4j logger")
                }
            }

    private val isIncremental: Boolean
        get() = incrementalCompilationEnvironment != null

    override fun run() {
        val messageCollector = GradlePrintingMessageCollector(log)
        val exitCode = try {
            compileWithDaemonOrFallbackImpl(messageCollector)
        } finally {
            if (incrementalCompilationEnvironment?.disableMultiModuleIC == true) {
                incrementalCompilationEnvironment.multiModuleICSettings.buildHistoryFile.delete()
            }
            TaskExecutionResults[taskPath] = TaskExecutionResult(buildReportLines)
        }

        throwGradleExceptionIfError(exitCode)
    }

    private fun compileWithDaemonOrFallbackImpl(messageCollector: MessageCollector): ExitCode {
        log.kotlinDebug {
            "Calling compiler ($compilerClassName, classpath = [${compilerFullClasspath.joinToString { it.path }}]) with args: " +
                    "[${compilerArgs.joinToString(" ")}]"
        }

        val executionStrategy = kotlinCompilerExecutionStrategy()
        if (executionStrategy == DAEMON_EXECUTION_STRATEGY) {
            val daemonExitCode = compileWithDaemon(messageCollector)

            if (daemonExitCode != null) {
                return daemonExitCode
            } else {
                log.warn("Could not connect to kotlin daemon. Using fallback strategy.")
            }
        }

        val isGradleDaemonUsed = System.getProperty("org.gradle.daemon")?.let(String::toBoolean)
        return if (executionStrategy == IN_PROCESS_EXECUTION_STRATEGY || isGradleDaemonUsed == false) {
            compileInProcess(messageCollector)
        } else {
            compileOutOfProcess()
        }
    }

    private fun compileWithDaemon(messageCollector: MessageCollector): ExitCode? {
        val isDebugEnabled = log.isDebugEnabled
                || buildReportMode != null
                || (System.getProperty("kotlin.daemon.debug.log")?.toBoolean() ?: true)
        val daemonMessageCollector = GradleBufferingMessageCollector()

        val connection =
            try {
                GradleCompilerRunner.getDaemonConnectionImpl(
                    clientIsAliveFlagFile,
                    sessionFlagFile,
                    compilerFullClasspath,
                    daemonMessageCollector,
                    isDebugEnabled = isDebugEnabled,
                    checkICCachesAreClosed = checkICCachesAreClosed
                )
            } catch (e: Exception) {
                log.error("Caught an exception trying to connect to Kotlin Daemon:")
                log.error(e.stackTraceAsString())
                null
            }
        if (connection == null) {
            daemonMessageCollector.flush(messageCollector)
            if (isIncremental) {
                log.warn("Could not perform incremental compilation: $COULD_NOT_CONNECT_TO_DAEMON_MESSAGE")
            } else {
                log.warn(COULD_NOT_CONNECT_TO_DAEMON_MESSAGE)
            }
            return null
        }

        val (daemon, sessionId) = connection

        if (log.isDebugEnabled) {
            daemon.getDaemonJVMOptions().takeIf { it.isGood }?.let { jvmOpts ->
                log.debug("Kotlin compile daemon JVM options: ${jvmOpts.get().mappers.flatMap { it.toArgs("-") }}")
            }
        }
        val targetPlatform = when (compilerClassName) {
            KotlinCompilerClass.JVM -> CompileService.TargetPlatform.JVM
            KotlinCompilerClass.JS -> CompileService.TargetPlatform.JS
            KotlinCompilerClass.METADATA -> CompileService.TargetPlatform.METADATA
            else -> throw IllegalArgumentException("Unknown compiler type $compilerClassName")
        }
        val bufferingMessageCollector = GradleBufferingMessageCollector()
        val exitCode = try {
            val res = if (isIncremental) {
                incrementalCompilationWithDaemon(daemon, sessionId, targetPlatform, bufferingMessageCollector)
            } else {
                nonIncrementalCompilationWithDaemon(daemon, sessionId, targetPlatform, bufferingMessageCollector)
            }
            bufferingMessageCollector.flush(messageCollector)
            exitCodeFromProcessExitCode(log, res.get())
        } catch (e: Exception) {
            bufferingMessageCollector.flush(messageCollector)
            log.error("Compilation with Kotlin compile daemon was not successful")
            log.error(e.stackTraceAsString())
            null
        }
        log.logFinish(DAEMON_EXECUTION_STRATEGY)
        return exitCode
    }

    private fun nonIncrementalCompilationWithDaemon(
        daemon: CompileService,
        sessionId: Int,
        targetPlatform: CompileService.TargetPlatform,
        bufferingMessageCollector: GradleBufferingMessageCollector
    ): CompileService.CallResult<Int> {
        logNonIcBuild("incremental compilation is not enabled for '$taskPath'")
        val compilationOptions = CompilationOptions(
            compilerMode = CompilerMode.NON_INCREMENTAL_COMPILER,
            targetPlatform = targetPlatform,
            reportCategories = reportCategories(isVerbose),
            reportSeverity = reportSeverity(isVerbose),
            requestedCompilationResults = emptyArray(),
            kotlinScriptExtensions = kotlinScriptExtensions
        )
        val servicesFacade = GradleCompilerServicesFacadeImpl(log, bufferingMessageCollector)
        return daemon.compile(sessionId, compilerArgs, compilationOptions, servicesFacade, compilationResults = null)
    }

    private fun incrementalCompilationWithDaemon(
        daemon: CompileService,
        sessionId: Int,
        targetPlatform: CompileService.TargetPlatform,
        bufferingMessageCollector: GradleBufferingMessageCollector
    ): CompileService.CallResult<Int> {
        val icEnv = incrementalCompilationEnvironment ?: error("incrementalCompilationEnvironment is null!")
        val knownChangedFiles = icEnv.changedFiles as? ChangedFiles.Known

        val requestedCompilationResults = EnumSet.of(CompilationResultCategory.IC_COMPILE_ITERATION)
        when (buildReportMode) {
            BuildReportMode.SIMPLE -> CompilationResultCategory.BUILD_REPORT_LINES
            BuildReportMode.VERBOSE -> CompilationResultCategory.VERBOSE_BUILD_REPORT_LINES
            null -> null
        }?.let { requestedCompilationResults.add(it) }

        val compilationOptions = IncrementalCompilationOptions(
            areFileChangesKnown = knownChangedFiles != null,
            modifiedFiles = knownChangedFiles?.modified,
            deletedFiles = knownChangedFiles?.removed,
            workingDir = icEnv.workingDir,
            reportCategories = reportCategories(isVerbose),
            reportSeverity = reportSeverity(isVerbose),
            requestedCompilationResults = requestedCompilationResults.map { it.code }.toTypedArray(),
            compilerMode = CompilerMode.INCREMENTAL_COMPILER,
            targetPlatform = targetPlatform,
            usePreciseJavaTracking = icEnv.usePreciseJavaTracking,
            outputFiles = outputFiles,
            multiModuleICSettings = icEnv.multiModuleICSettings,
            modulesInfo = incrementalModuleInfo!!,
            kotlinScriptExtensions = kotlinScriptExtensions
        )

        log.kotlinDebug { "Options for Kotlin daemon: $compilationOptions" }
        val servicesFacade = GradleIncrementalCompilerServicesFacadeImpl(log, bufferingMessageCollector)
        val compilationResults = GradleCompilationResults(log, projectRootFile)
        return try {
            daemon.compile(sessionId, compilerArgs, compilationOptions, servicesFacade, compilationResults)
        } finally {
            buildReportLines.addAll(compilationResults.icLogLines ?: emptyList())
        }
    }

    private fun compileOutOfProcess(): ExitCode {
        clearLocalState(outputFiles, log, reason = "out-of-process execution strategy is non-incremental")
        logNonIcBuild("$OUT_OF_PROCESS_EXECUTION_STRATEGY execution strategy does not support incremental compilation")
        return runToolInSeparateProcess(compilerArgs, compilerClassName, compilerFullClasspath, log)
    }

    private fun compileInProcess(messageCollector: MessageCollector): ExitCode {
        clearLocalState(outputFiles, log, reason = "in-process execution strategy is non-incremental")
        logNonIcBuild("$IN_PROCESS_EXECUTION_STRATEGY execution strategy does not support incremental compilation")


        // in-process compiler should always be run in a different thread
        // to avoid leaking thread locals from compiler (see KT-28037)
        val threadPool = Executors.newSingleThreadExecutor()
        val bufferingMessageCollector = GradleBufferingMessageCollector()
        return try {
            val future = threadPool.submit(Callable {
                compileInProcessImpl(bufferingMessageCollector)
            })
            future.get()
        } finally {
            bufferingMessageCollector.flush(messageCollector)
            threadPool.shutdown()

            log.logFinish(IN_PROCESS_EXECUTION_STRATEGY)
        }
    }

    private fun compileInProcessImpl(messageCollector: MessageCollector): ExitCode {
        // todo: cache classloader?
        val classpathURLs = compilerFullClasspath.map { it.toURI().toURL() }.toTypedArray()
        return URLClassLoader(classpathURLs).use { classLoader ->
            try {
                compileInProcessWithClassLoader(messageCollector, classLoader)
            } finally {
                val coreEnvClass = classLoader.loadClass("org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment")
                val disposeAppEnv = coreEnvClass.getDeclaredMethod("disposeApplicationEnvironment", coreEnvClass)
                disposeAppEnv.invoke(coreEnvClass)
            }
        }
    }

    private fun compileInProcessWithClassLoader(messageCollector: MessageCollector, classLoader: ClassLoader): ExitCode {
        val stream = ByteArrayOutputStream()
        val out = PrintStream(stream)
        val servicesClass = Class.forName(Services::class.java.canonicalName, true, classLoader)
        val emptyServices = servicesClass.getField("EMPTY").get(servicesClass)
        val compiler = Class.forName(compilerClassName, true, classLoader)

        val exec = compiler.getMethod(
            "execAndOutputXml",
            PrintStream::class.java,
            servicesClass,
            Array<String>::class.java
        )

        val res = exec.invoke(compiler.newInstance(), out, emptyServices, compilerArgs)
        val exitCode = ExitCode.valueOf(res.toString())
        processCompilerOutput(
            messageCollector,
            OutputItemsCollectorImpl(),
            stream,
            exitCode
        )
        return exitCode
    }

    private fun reportCategories(verbose: Boolean): Array<Int> =
        if (!verbose) {
            arrayOf(ReportCategory.COMPILER_MESSAGE.code)
        } else {
            ReportCategory.values().map { it.code }.toTypedArray()
        }

    private fun reportSeverity(verbose: Boolean): Int =
        if (!verbose) {
            ReportSeverity.INFO.code
        } else {
            ReportSeverity.DEBUG.code
        }

    private fun logNonIcBuild(reason: String) {
        log.debug("Performing non-incremental build: $reason")
    }
}