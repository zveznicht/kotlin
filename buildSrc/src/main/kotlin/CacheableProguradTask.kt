/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.*


@CacheableTask
open class CacheableProguardTask : proguard.gradle.ProGuardTask() {

    @Input
    override fun getOutJarFilters(): MutableList<Any?> = super.getOutJarFilters()

    @Input
    override fun getrenamesourcefileattribute(): Any = super.getrenamesourcefileattribute()

    @Input
    override fun getprintusage(): Any = super.getprintusage()

    @Input
    override fun getforceprocessing(): Any = super.getforceprocessing()

    @Input
    override fun getInJarFilters(): MutableList<Any?> = super.getInJarFilters()

    @Input
    override fun getdontwarn(): Any = super.getdontwarn()

    @Input
    override fun getaddconfigurationdebugging(): Any = super.getaddconfigurationdebugging()

    @Input
    override fun getallowaccessmodification(): Any = super.getallowaccessmodification()

    @Input
    override fun getignorewarnings(): Any = super.getignorewarnings()

    @Input
    override fun getkeepdirectories(): Any = super.getkeepdirectories()

    @Input
    override fun getuseuniqueclassmembernames(): Any = super.getuseuniqueclassmembernames()

    @Input
    override fun getmicroedition(): Any = super.getmicroedition()

    @Input
    override fun getandroid(): Any = super.getandroid()

    @Input
    override fun getoverloadaggressively(): Any = super.getoverloadaggressively()

    @Input
    override fun getdontusemixedcaseclassnames(): Any = super.getdontusemixedcaseclassnames()

    @Input
    override fun getdontnote(): Any = super.getdontnote()

    @Internal
    override fun getInJarFiles(): MutableList<Any?> = super.getInJarFiles()

    @Input
    override fun getInJarCounts(): MutableList<Any?> = super.getInJarCounts()

    @Input
    override fun getdontpreverify(): Any = super.getdontpreverify()

    @Input
    override fun getverbose(): Any = super.getverbose()

    @Input
    override fun getskipnonpubliclibraryclasses(): Any = super.getskipnonpubliclibraryclasses()

    @Input
    override fun getdontoptimize(): Any = super.getdontoptimize()

    @Input
    override fun getdump(): Any = super.getdump()

    @InputFiles
    @PathSensitive(PathSensitivity.RELATIVE)
    override fun getInJarFileCollection(): FileCollection = super.getInJarFileCollection()

    @Input
    override fun getdontobfuscate(): Any = super.getdontobfuscate()

    @Input
    override fun getLibraryJarFilters(): MutableList<Any?> = super.getLibraryJarFilters()

    @Input
    override fun getprintmapping(): Any = super.getprintmapping()

    @Input
    override fun getdontshrink(): Any = super.getdontshrink()

    @Input
    override fun getkeepattributes(): Any = super.getkeepattributes()

    @Internal
    override fun getOutJarFileCollection(): FileCollection = super.getOutJarFileCollection()

    @Input
    override fun getdontskipnonpubliclibraryclassmembers(): Any = super.getdontskipnonpubliclibraryclassmembers()

    @Input
    override fun getprintconfiguration(): Any = super.getprintconfiguration()

    @Input
    override fun getmergeinterfacesaggressively(): Any = super.getmergeinterfacesaggressively()

    @Input
    override fun getConfigurationFiles(): MutableList<Any?> = super.getConfigurationFiles()

    @Input
    override fun getkeeppackagenames(): Any = super.getkeeppackagenames()

    @InputFiles
    @PathSensitive(PathSensitivity.RELATIVE)
    override fun getLibraryJarFileCollection(): FileCollection = super.getLibraryJarFileCollection()

    @InputFiles
    @PathSensitive(PathSensitivity.RELATIVE)
    override fun getConfigurationFileCollection(): FileCollection = super.getConfigurationFileCollection()

    @Input
    override fun getprintseeds(): Any = super.getprintseeds()

    @Input
    override fun getadaptresourcefilenames(): Any = super.getadaptresourcefilenames()

    @Input
    override fun getrepackageclasses(): Any = super.getrepackageclasses()

    @Input
    override fun getadaptresourcefilecontents(): Any = super.getadaptresourcefilecontents()

    @Input
    override fun getflattenpackagehierarchy(): Any = super.getflattenpackagehierarchy()

    @Input
    override fun getadaptclassstrings(): Any = super.getadaptclassstrings()

    @Internal
    override fun getLibraryJarFiles(): MutableList<Any?> = super.getLibraryJarFiles()

    @Input
    override fun getkeepparameternames(): Any = super.getkeepparameternames()

    @Internal
    override fun getOutJarFiles(): MutableList<Any?> = super.getOutJarFiles()
}
