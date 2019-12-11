import org.gradle.internal.os.OperatingSystem
import java.net.URI
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import javax.inject.Inject

repositories {
    ivy {
        url = URI("https://dl.google.com/android/repository")
        patternLayout {
            artifact("[artifact]-[revision].[ext]")
            artifact("[artifact]_[revision](-[classifier]).[ext]")
            artifact("[artifact]_[revision](-[classifier]).[ext]")
        }
        metadataSources {
            artifact()
        }
    }
}

val androidSdk by configurations.creating
val androidJar by configurations.creating
val androidPlatform by configurations.creating
val buildTools by configurations.creating

val libsDestDir = File(buildDir, "libs")
val sdkDestDir = File(buildDir, "androidSdk")

val toolsOs = when {
    OperatingSystem.current().isWindows -> "windows"
    OperatingSystem.current().isMacOsX -> "macosx"
    OperatingSystem.current().isLinux -> "linux"
    else -> {
        logger.error("Unknown operating system for android tools: ${OperatingSystem.current().name}")
        ""
    }
}

val prepareSdk by task<DefaultTask> {
    doLast {}
}

val sdkDeps by configurations.creating
// this task is needed to force Gradle to resolve and download all dependencies at once
// this speeds up downloading when parallel build is enabled
val downloadSdkDeps by tasks.creating {
    // write a list of downloaded files, so that task has outputs and Gradle does not rerun it
    val downloadedFilesTxt = project.buildDir.resolve("downloaded-files.txt")
    inputs.files(sdkDeps)
    outputs.files(downloadedFilesTxt)
    doLast {
        val files = sdkDeps.resolve()
        val paths = files.map { it.canonicalPath }.sorted()
        downloadedFilesTxt.writeText(paths.joinToString("\n"))
    }
}

fun unzipSdkTask(
    sdkName: String,
    sdkVer: String,
    destinationSubdir: String,
    coordinatesSuffix: String,
    additionalConfig: Configuration? = null,
    dirLevelsToSkipOnUnzip: Int = 0,
    ext: String = "zip"
): Task {
    val id = "${sdkName}_$sdkVer"
    val cfg = configurations.create(id)
    val dependency = "google:$sdkName:$sdkVer${coordinatesSuffix.takeIf { it.isNotEmpty() }?.let { ":$it" } ?: ""}@$ext"
    dependencies.add(cfg.name, dependency)
    dependencies.add(sdkDeps.name, dependency)

    val unzipTask = task<UnzipWithWorkers>("unzip_$id") {
        dependsOn(downloadSdkDeps)
        archive = cfg
        targetDir = file("$sdkDestDir/$destinationSubdir")
        dirLevelsToSkip = dirLevelsToSkipOnUnzip
    }

    prepareSdk.configure {
        dependsOn(unzipTask)
    }

    additionalConfig?.also {
        dependencies.add(it.name, dependency)
    }

    return unzipTask
}

unzipSdkTask("android_m2repository", "r44", "extras/android", "")
unzipSdkTask("platform", "26_r02", "platforms/android-26", "", androidPlatform, 1)
unzipSdkTask("platform-tools", "r25.0.3", "", toolsOs)
unzipSdkTask("tools", "r24.3.4", "", toolsOs)
unzipSdkTask("build-tools", "r23.0.1", "build-tools/23.0.1", toolsOs, buildTools, 1)
unzipSdkTask("build-tools", "r28.0.2", "build-tools/28.0.2", toolsOs, buildTools, 1)

val clean by task<Delete> {
    delete(buildDir)
}

val extractAndroidJar by tasks.registering {
    dependsOn(androidPlatform)
    inputs.files(androidPlatform)
    val targetFile = File(libsDestDir, "android.jar")
    outputs.files(targetFile)
    doFirst {
        project.copy {
            from(zipTree(androidPlatform.singleFile).matching { include("**/android.jar") }.files.first())
            into(libsDestDir)
        }
    }
}

artifacts.add(androidSdk.name, file("$sdkDestDir")) {
    builtBy(prepareSdk)
}

artifacts.add(androidJar.name, file("$libsDestDir/android.jar")) {
    builtBy(extractAndroidJar)
}

open class UnzipWithWorkers @Inject constructor(
    private val workerExecutor: WorkerExecutor
) : DefaultTask() {
    @get:InputFiles
    lateinit var archive: FileCollection

    @get:OutputDirectory
    lateinit var targetDir: File

    @get:Input
    var dirLevelsToSkip: Int = 0

    @TaskAction
    fun unzip() {
        val archiveFile = archive.singleFile
        workerExecutor.submit(UnzipRunnable::class.java) {
            forkMode = ForkMode.NEVER
            isolationMode = IsolationMode.NONE
            params(archiveFile, targetDir, dirLevelsToSkip)
        }
    }

    open class UnzipRunnable @Inject constructor(
        val archive: File,
        val targetDir: File,
        val dirLevelsToSkip: Int
    ) : Runnable {
        override fun run() {
            check(dirLevelsToSkip >= 0) { "dirLevelsToSkipOnUnzip ($dirLevelsToSkip) cannot be negative!" }
            check(archive.extension == "zip") { "$archive extension is not zip!" }

            archive.inputStream().use { inputStream ->
                val zipStream = ZipInputStream(inputStream)
                for (zipEntry in generateSequence { zipStream.nextEntry }) {
                    handleZipEntry(zipEntry, zipStream)
                    zipStream.closeEntry()
                }
            }
        }

        private fun handleZipEntry(zipEntry: ZipEntry, zipStream: ZipInputStream) {
            val path = zipEntry.targetPath()
            val file = targetDir.resolve(path)

            if (zipEntry.isDirectory || path.isBlank()) return
            file.parentFile.mkdirs()
            file.outputStream().use {  outputStream ->
                zipStream.copyTo(outputStream)
            }
        }

        private fun ZipEntry.targetPath() =
            if (dirLevelsToSkip == 0) name else name.split("/").drop(dirLevelsToSkip).joinToString("/")
    }
}