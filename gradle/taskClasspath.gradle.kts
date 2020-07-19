import org.gradle.api.internal.GeneratedSubclasses
import org.gradle.api.internal.TaskInternal
import org.gradle.internal.classloader.ClassLoaderVisitor
import org.gradle.internal.classloader.ClasspathUtil
import org.gradle.internal.fingerprint.classpath.ClasspathFingerprinter
import org.gradle.internal.hash.FileHasher
import org.gradle.kotlin.dsl.support.serviceOf
import java.io.OutputStreamWriter
import java.net.URL


val printTaskClassPath = findProperty("kotlin.build.task.classpath") == "true"
if (printTaskClassPath) {
    val tasksClasspathFile = rootProject.buildDir.resolve("tasks-classpath.txt")
    tasksClasspathFile.delete()

    gradle.taskGraph.whenReady {
        tasksClasspathFile.writer().use { writer ->
            allTasks.filter { it.isCacheable() }.forEach { task ->
                writer.appendLine("Classpath for ${task.path} >>>")
                val classPath = ClasspathUtil.getClasspath(task::class.java.classLoader)
                classPath.asFiles.forEach { it.printHashes(writer) }
            }
        }
    }
}

fun File.printHashes(writer: OutputStreamWriter) {
    val hasher = gradle.serviceOf<FileHasher>()
    val classpathFingerprinter = gradle.serviceOf<ClasspathFingerprinter>()

    val fileHash = hasher.hash(this)

    val filePath = canonicalPath.replace(gradle.gradleUserHomeDir.canonicalPath, "GRADLE_USER_HOME_DIR")
    val invariantPath = if (File.separatorChar != '/') filePath.replace(File.separatorChar, '/') else filePath
    writer.appendLine(invariantPath)
    writer.appendLine(fileHash.toString())
    writer.appendLine(classpathFingerprinter.fingerprint(files(this)).hash.toString())
    writer.appendLine("")
}

fun Task.isCacheable(): Boolean {
    this as TaskInternal
    return cachingEnabled() && !cachingDisabled()
}

fun TaskInternal.cachingEnabled(): Boolean {
    return if (outputs.cacheIfSpecs.isEmpty())
        GeneratedSubclasses.unpackType(this).isAnnotationPresent(CacheableTask::class.java)
    else
        outputs.cacheIfSpecs.all { it.invoke(this) }
}

fun TaskInternal.cachingDisabled(): Boolean = outputs.doNotCacheIfSpecs.any { it.invoke(this) }

fun OutputStreamWriter.appendLine(csq: CharSequence) = append(csq).append("\n")