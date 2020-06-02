package org.jetbrains.kotlin.library.impl

import org.jetbrains.kotlin.konan.file.*
import org.jetbrains.kotlin.library.*
import org.jetbrains.kotlin.util.removeSuffixIfPresent
import java.net.URI
import java.nio.file.CopyOption
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import kotlin.io.use

// TODO: Rework
open class KotlinLibraryLayoutImpl(val klib: File, override val component: String?) : KotlinLibraryLayout<File> {
    // TODO: Think about supporting it in the case of using ZipFiles instead of ZipFileSystem.
    val isZipped = klib.isFile

    init {
        if (isZipped) zippedKotlinLibraryChecks(klib)
    }

    override val libDir = if (isZipped) File("/") else klib

    override val libraryName
        get() =
            if (isZipped)
                klib.path.removeSuffixIfPresent(KLIB_FILE_EXTENSION_WITH_DOT)
            else
                libDir.path

    open val extractingToTemp: KotlinLibraryLayout<File> by lazy {
        ExtractingBaseLibraryImpl(this)
    }

    open fun directlyFromZip(zipFile: ZipFile): KotlinLibraryLayout<ZippedFile> =
        FromZipBaseLibraryImpl(this, zipFile)

}

class MetadataLibraryLayoutImpl(klib: File, component: String) : KotlinLibraryLayoutImpl(klib, component),
    MetadataKotlinLibraryLayout<File> {

    override val extractingToTemp: MetadataKotlinLibraryLayout<File> by lazy {
        ExtractingMetadataLibraryImpl(this)
    }

    override fun directlyFromZip(zipFile: ZipFile): MetadataKotlinLibraryLayout<ZippedFile> =
        FromZipMetadataLibraryImpl(this, zipFile)
}

class IrLibraryLayoutImpl(klib: File, component: String) : KotlinLibraryLayoutImpl(klib, component), IrKotlinLibraryLayout<File> {

    override val extractingToTemp: IrKotlinLibraryLayout<File> by lazy {
        ExtractingIrLibraryImpl(this)
    }

    override fun directlyFromZip(zipFile: ZipFile): IrKotlinLibraryLayout<ZippedFile> =
        FromZipIrLibraryImpl(this, zipFile)
}

//region Accesses

// TODO: Probably replace File -> MutableFile
// TODO: Naming for REAL_L and PLACE_L
abstract class AbstractLibraryAccess<REAL_L : KotlinLibraryLayout<File>, PLACE_L : KotlinLibraryLayout<AbstractFile>>(val klib: File) {
    abstract val layout: KotlinLibraryLayoutImpl

    fun <T> realFiles(action: (REAL_L) -> T): T =
        if (layout.isZipped)
            action(layout.extractingToTemp as REAL_L)
        else
            action(layout as REAL_L)

    fun <T> inPlace(action: (PLACE_L) -> T): T =
        if (layout.isZipped)
            layout.klib.withZipFile { zipFile ->
                action(layout.directlyFromZip(zipFile) as PLACE_L)
            }
        else
            action(layout as PLACE_L)
}

open class BaseLibraryAccess(klib: File, component: String?) :
    AbstractLibraryAccess<KotlinLibraryLayout<File>, KotlinLibraryLayout<AbstractFile>>(klib){
    // TODO: This should be a layout factory?
    override val layout = KotlinLibraryLayoutImpl(klib, component)
}

open class MetadataLibraryAccess(klib: File, component: String) :
    AbstractLibraryAccess<MetadataKotlinLibraryLayout<File>, MetadataKotlinLibraryLayout<AbstractFile>>(klib) {
    override val layout = MetadataLibraryLayoutImpl(klib, component)
}

open class IrLibraryAccess(klib: File, component: String) :
    AbstractLibraryAccess<IrKotlinLibraryLayout<File>, IrKotlinLibraryLayout<AbstractFile>>(klib) {
    override val layout = IrLibraryLayoutImpl(klib, component)
}
//endregion.

//region FromZip
open class FromZipBaseLibraryImpl(zipped: KotlinLibraryLayoutImpl, zipFile: ZipFile) :
    KotlinLibraryLayout<ZippedFile> {

    override val libraryName = zipped.libraryName

    // TODO: Is it ok to have this pathInsideZip or replace it with come constant like ZippedFile.ROOT_DIR
    //       Add one more constructor?
    override val libDir = ZippedFile(zipFile, "/")
    override val component = zipped.component
}

class FromZipMetadataLibraryImpl(zipped: MetadataLibraryLayoutImpl, zipFile: ZipFile) :
    FromZipBaseLibraryImpl(zipped, zipFile), MetadataKotlinLibraryLayout<ZippedFile>

class FromZipIrLibraryImpl(zipped: IrLibraryLayoutImpl, zipFile: ZipFile) :
    FromZipBaseLibraryImpl(zipped, zipFile), IrKotlinLibraryLayout<ZippedFile>
//endregion

//region Extracted
private fun ZipFile.extract(entry: ZipEntry, targetFile: File) {
    getInputStream(entry).use {
        Files.copy(it, Paths.get(URI("file://${targetFile.absolutePath}")), StandardCopyOption.REPLACE_EXISTING)
    }
}

// TODO: Support non-absolute path-inside-zip and refactor: call copy from ZippedFile.
fun KotlinLibraryLayoutImpl.extract(file: File): File = this.klib.withZipFile { zipFile ->
    val temporary = org.jetbrains.kotlin.konan.file.createTempFile(file.name)
    // TODO: Better handle absolute paths.
    val zipEntryName = file.path.let {
        if (it.startsWith("/")) it.drop(1) else it
    }
    zipFile.extract(zipFile.getEntry(zipEntryName), temporary)
    temporary.deleteOnExit()
    temporary
}

// TODO: Support non-absolute path-inside-zip and refactor: call copy from ZippedFile.
fun KotlinLibraryLayoutImpl.extractDir(directory: File): File = this.klib.withZipFile { zipFile ->
    val temporary = org.jetbrains.kotlin.konan.file.createTempDir(directory.name)
    // TODO: Better handle absolute paths.
    val directoryZipEntryName = directory.path.let {
        if (it.startsWith("/")) it.drop(1) else it
    }
    require(zipFile.getEntry("$directoryZipEntryName/")?.isDirectory == true) {
        "Requested zip entry is not a directory: $directoryZipEntryName"
    }

    val pathPrefix = "$directoryZipEntryName/"
    zipFile.entries().asSequence().forEach {
        if (it.name.startsWith(directoryZipEntryName) && !it.isDirectory) {
            val relativePath = it.name.removePrefix(pathPrefix)
            val outputFile = temporary.child(relativePath)
            outputFile.parentFile.mkdirs()
            zipFile.extract(it, outputFile)
        }
    }

    temporary.deleteOnExitRecursively()
    temporary
}

/**
 * This class and its children automatically extracts pieces of the library on first access. Use it if you need
 * to pass extracted files to an external tool. Otherwise, stick to [FromZipBaseLibraryImpl].
 */
open class ExtractingKotlinLibraryLayout(zipped: KotlinLibraryLayoutImpl) : KotlinLibraryLayout<File> {
    override val libDir: File get() = error("Extracting layout doesn't extract its own root")
    override val libraryName = zipped.libraryName
    override val component = zipped.component
}

open class ExtractingBaseLibraryImpl(zipped: KotlinLibraryLayoutImpl) :
    ExtractingKotlinLibraryLayout(zipped) {
    override val manifestFile: File by lazy { zipped.extract(zipped.manifestFile) }
    override val resourcesDir: File by lazy { zipped.extractDir(zipped.resourcesDir) }
}

class ExtractingMetadataLibraryImpl(val zipped: MetadataLibraryLayoutImpl) :
    ExtractingKotlinLibraryLayout(zipped),
    MetadataKotlinLibraryLayout<File> {

    override val metadataDir by lazy { zipped.extractDir(zipped.metadataDir) }
}

class ExtractingIrLibraryImpl(val zipped: IrLibraryLayoutImpl) :
    ExtractingKotlinLibraryLayout(zipped),
    IrKotlinLibraryLayout<File> {

    override val irDeclarations: File by lazy { zipped.extract(zipped.irDeclarations) }

    override val irTypes: File by lazy { zipped.extract(zipped.irTypes) }

    override val irSignatures: File by lazy { zipped.extract(zipped.irSignatures) }

    override val irStrings: File by lazy { zipped.extract(zipped.irStrings) }

    override val irBodies: File by lazy { zipped.extract(zipped.irBodies) }

    override val irFiles: File by lazy { zipped.extract(zipped.irFiles) }
}
//endregion

internal fun zippedKotlinLibraryChecks(klibFile: File) {
    check(klibFile.exists) { "Could not find $klibFile." }
    check(klibFile.isFile) { "Expected $klibFile to be a regular file." }

    val extension = klibFile.extension
    check(extension.isEmpty() || extension == KLIB_FILE_EXTENSION || extension == "jar") {
        "KLIB path has unexpected extension: $klibFile"
    }
}