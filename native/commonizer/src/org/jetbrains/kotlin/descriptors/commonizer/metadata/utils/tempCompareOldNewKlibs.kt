/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.descriptors.commonizer.metadata.utils

import kotlinx.metadata.*
import kotlinx.metadata.klib.KlibModuleMetadata
import kotlinx.metadata.klib.fqName
import org.jetbrains.kotlin.konan.file.File as KFile
import org.jetbrains.kotlin.library.ToolingSingleFileKlibResolveStrategy
import org.jetbrains.kotlin.library.resolveSingleFileKlib
import java.io.File

@Suppress("SpellCheckingInspection")
fun main() {
    val baseDir = File("/Users/Dmitriy.Dolovov/temp/commonizer-output")
    val klibPaths = baseDir.listKlibs

    for (klibPath in klibPaths) {
        doCompare(klibPath, baseDir,false)
    }
}

private val File.listKlibs: Set<File>
    get() = walkTopDown()
        .filter { it.isDirectory && (it.name == "common" || it.parentFile.name == "platform") }
        .map { it.relativeTo(this) }
        .toSet()

private val KmType.signature: String
    get() = buildString { buildSignature("type", this, 0) }

private fun KmType.buildSignature(header: String, builder: StringBuilder, indent: Int) {
    repeat(indent) { builder.append("  ") }
    builder.append("- ").append(header).append(": ")
    when (val classifier = classifier) {
        is KmClassifier.Class -> builder.append("[class] ").append(classifier.name)
        is KmClassifier.TypeAlias -> builder.append("[type-alias] ").append(classifier.name)
        is KmClassifier.TypeParameter -> builder.append("[type-param] ").append(classifier.id)
    }
    if (Flag.Type.IS_NULLABLE(flags)) builder.append('?')
    builder.append('\n')

    if (arguments.isNotEmpty()) {
        repeat(indent) { builder.append("  ") }
        builder.append("  ").append("- arguments: ").append(arguments.size).append('\n')
        arguments.forEach { argument ->
            argument.type!!.buildSignature("type", builder, indent + 2)
        }
    }

    abbreviatedType?.buildSignature("abbreviated", builder, indent + 1)
}

private fun doCompare(klibPath: File, baseDir: File, printMatches: Boolean) {
    println("BEGIN $klibPath")

    val newLibs: Map<String, KFile> = baseDir.resolve(klibPath).listFiles().orEmpty()
        .filter { it.name.endsWith("-NEW") }
        .groupBy { it.name.substringBefore("-NEW") }
        .mapValues { KFile(it.value.single().absolutePath) }

    val oldLibs: Map<String, KFile> = baseDir.resolve(klibPath).listFiles().orEmpty().toSet()
        .filter { it.name in newLibs }
        .groupBy { it.name }
        .mapValues { KFile(it.value.single().absolutePath) }

    val allLibs = newLibs.keys intersect oldLibs.keys
    check(newLibs.keys == allLibs)
    check(oldLibs.keys == allLibs)

    for (lib in allLibs.sorted().filter { "posix" in it }) {
        val newLib = newLibs.getValue(lib)
        val oldLib = oldLibs.getValue(lib)

        val newKlib = resolveSingleFileKlib(newLib, strategy = ToolingSingleFileKlibResolveStrategy)
        val oldKlib = resolveSingleFileKlib(oldLib, strategy = ToolingSingleFileKlibResolveStrategy)

        val newMetadata = KlibModuleMetadata.read(TrivialLibraryProvider(newKlib))
        val oldMetadata = KlibModuleMetadata.read(TrivialLibraryProvider(oldKlib))

        when (val result = MetadataDeclarationsComparator().compare(newMetadata, oldMetadata)) {
            Result.Success -> if (printMatches) println("- [full match] $lib")
            is Result.Failure -> {
                println("- [MISMATCHES: ${result.mismatches.size}] $lib")

                val newPosix: List<KmModuleFragment> = newMetadata.fragments.filter { it.fqName == "platform.posix" }
                val oldPosix: List<KmModuleFragment> = oldMetadata.fragments.filter { it.fqName == "platform.posix" }

                val newProperties: List<KmProperty> = newPosix.flatMap { it.pkg?.properties.orEmpty() }
                val newPropertiesMap: Map<String, KmProperty> = newProperties.associateBy { it.name }
                val oldProperties: List<KmProperty> = oldPosix.flatMap { it.pkg?.properties.orEmpty() }
                val oldPropertiesMap: Map<String, KmProperty> = oldProperties.associateBy { it.name }

//                val newClasses: List<KmClass> = newPosix.flatMap { it.classes }
//                val newClassesMap: Map<String, KmClass> = newClasses.associateBy { it.name }
//                val oldClasses: List<KmClass> = oldPosix.flatMap { it.classes }
//                val oldClassesMap: Map<String, KmClass> = oldClasses.associateBy { it.name }
//
//                val newClass = newClassesMap["platform/posix/regex_t"]
//                val oldClass = oldClassesMap["platform/posix/regex_t"]

                listOf("SEM_FAILED", "tzname").forEach { propertyName ->
                    val oldProp = oldPropertiesMap[propertyName]?.returnType?.signature
                    val newProp = newPropertiesMap[propertyName]?.returnType?.signature

                    if (oldProp != null && newProp != null) {
                        println("=== Property $propertyName ===")
                        if (oldProp == newProp) {
                            println("[equal]")
                            println(oldProp)
                        } else {
                            println("[old]")
                            println(oldProp)
                            println("[new]")
                            println(newProp)
                        }
                    }
                }

                println("- Grouped issues:")

                result.mismatches
                    .groupingBy { it::class.java.simpleName to it.kind }
                    .eachCount()
                    .entries
                    .sortedBy { it.key.first + "-" + it.key.second }
                    .forEach { (key, value) ->
                        println("\t${key.first} ${key.second} -> $value")
                    }

//                println("- Grouped MissingEntity-AbbreviatedType issues:")
//                result.mismatches
//                    .asSequence()
//                    .filterIsInstance<Mismatch.MissingEntity>()
//                    .filter { it.kind == "AbbreviatedType" }
//                    .groupingBy { mismatch ->
//                        var path = mismatch.path.filter { it != "<root>" && !it.startsWith("Module") && !it.startsWith("Package") }
//                            .map { it.substringBefore(' ') }
//                            .map { if (it == "TypeProjection") "Arg" else it }
//
//                        path = run {
//                            val temp = mutableListOf<String>()
//                            var index = 0
//                            while (index < path.size) {
//                                val curr = path[index]
//                                if (curr == "Arg" && index < path.size - 1) {
//                                    val next = path[index + 1]
//                                    if (next == "Type") {
//                                        temp += "Arg/Type"
//                                        index += 2
//                                        continue
//                                    }
//                                }
//                                temp += curr
//                                index++
//                            }
//                            temp
//                        }
//
//                        path = run {
//                            val temp = mutableListOf<String>()
//                            var index = 0
//                            while (index < path.size) {
//                                val curr = path[index]
//                                val usages = when (val indexOfFirstDifferent = path.subList(index, path.size).indexOfFirst { it != curr }) {
//                                    -1 -> path.size - index
//                                    0 -> 1
//                                    else -> indexOfFirstDifferent
//                                }
//                                index += usages
//                                temp += when {
//                                    usages > 1 -> "$curr[$usages]"
//                                    curr == "Arg/Type" -> "$curr[1]"
//                                    else -> curr
//                                }
//                            }
//                            temp
//                        }
//
//                        path.joinToString(" -> ")
//
////                        val type = it.path.last().substringBefore(' ')
////                        val path = it.path
////                            .filter { " " in it && !it.startsWith("Module") && !it.startsWith("Package") }
////                            .map { if (it.startsWith("Class")) "Class " + it.substringAfterLast('/') else it }
////                            .joinToString(" -> ")
////
////                        "$type ($path): ${it.name}[${it.valueB} -> ${it.valueA}]"
//                    }
//                    .eachCount()
//                    .entries.sortedBy { it.key }
//                    .forEach { (key, value) ->
//                        println("\t$key: $value")
//                    }

                val temp = result.mismatches
                    .asSequence()
                    .filterIsInstance<Mismatch.DifferentValues>()
                    .filter { it.kind == "Classifier" }
                    .toList()

                if (temp.isNotEmpty()) {
                    print("")
                }

//                println("- Grouped unique top-level SetterFlag issues:")
//                println("\t" + result.mismatches
//                    .filterIsInstance<Mismatch.DifferentValues>()
//                    .filter { it.kind == "SetterFlag" }
//                    .filter { it.path[it.path.lastIndex - 1].substringBefore(' ') == "Package" }
//                    .groupingBy { it.path[it.path.lastIndex - 1] + ":" + it.path[it.path.lastIndex] }
//                    .eachCount()
//                    .size + " <> " + newProperties.size
//                )

//                val props = result.mismatches
//                    .asSequence()
//                    .filterIsInstance<Mismatch.DifferentValues>()
//                    .filter { it.kind == "SetterFlag" }
//                    .filter { it.path[it.path.lastIndex - 1].substringBefore(' ') == "Package" }
//                    .map { it.path.last().substringAfter(' ') }
//                    .filter { !it.startsWith('_') }
//                    .filter { it[0].isLowerCase() }
//                    .sorted()
//                    .take(10)
//                    .toList()
//                println("- First 10 top-level properties: $props")

//
//                groupedByPath.values.flatten().forEach { mismatch ->
//                    if (mismatch !is Mismatch.DifferentValues) return@forEach
//                    if (mismatch.kind != "Classifier" || mismatch.path.last() != "UnderlyingType") return@forEach
//                    if ((mismatch.valueB as? KmClassifier.Class)?.name !in arrayOf(
//                            "kotlinx/cinterop/CPointer",
//                            "kotlin/Function0",
//                            "kotlin/Function1",
//                            "kotlin/Function2",
//                            "kotlin/Function3"
//                        )
//                    ) return@forEach
//
//                    var fullTypeAliasName = (mismatch.valueA as? KmClassifier.TypeAlias)?.name ?: return@forEach
//
//                    while (true) {
//                        val typeAlias = resolveTypeAlias(fullTypeAliasName)
//
//                        when (val underlyingTypeClassifier = typeAlias.underlyingType.classifier) {
//                            is KmClassifier.Class -> {
//                                if (underlyingTypeClassifier.name !in arrayOf(
//                                        "kotlinx/cinterop/CPointer",
//                                        "kotlin/Function0",
//                                        "kotlin/Function1",
//                                        "kotlin/Function2",
//                                        "kotlin/Function3"
//                                    )
//                                ) {
//                                    error("Unexpected class found: ${underlyingTypeClassifier.name}")
//                                }
//                            }
//                            is KmClassifier.TypeAlias -> {
//                                if (underlyingTypeClassifier.name !in arrayOf(
//                                        "kotlinx/cinterop/CArrayPointer",
//                                        "kotlinx/cinterop/COpaquePointer"
//                                    )
//                                ) {
//                                    fullTypeAliasName = underlyingTypeClassifier.name
//                                    continue
//                                }
//                            }
//                        }
//
//                        val relevantMismatches = groupedByPath.getValue(mismatch.path)
//                        relevantMismatches -= mismatch
//                        relevantMismatches.removeIf { it is Mismatch.MissingEntity && it.kind == "TypeProjection" }
//                        break
//                    }
//                }
//
//                groupedByPath.values.flatten().forEach { mismatch ->
//                    if (mismatch !is Mismatch.DifferentValues) return@forEach
//                    if (mismatch.kind != "Flag" || mismatch.name != "IS_NULLABLE") return@forEach
//                    if (mismatch.path.last() !in arrayOf("UnderlyingType", "ExpandedType")) return@forEach
//                    if (mismatch.valueA.toString() != "false" || mismatch.valueB.toString() != "true") return
//
//                    groupedByPath.getValue(mismatch.path) -= mismatch
//                }
//
//                if ("linux_x64" in klibPath.path) {
//                    groupedByPath.values.flatten().forEach { mismatch ->
//                        if (mismatch !is Mismatch.MissingEntity) return@forEach
//                        if (mismatch.kind != "TypeAlias") return@forEach
//                        val options = listOf("caddr_t", "sig_t", "va_list")
//                        if (mismatch.name !in options && mismatch.name.removeSuffix("Var") !in options) return@forEach
//
//                        groupedByPath.getValue(mismatch.path) -= mismatch
//                    }
//
//                    groupedByPath.values.flatten().forEach { mismatch ->
//                        if (mismatch !is Mismatch.MissingEntity) return@forEach
//                        if (mismatch.kind != "Function") return@forEach
//                        val function = mismatch.presentValue as? KmFunction ?: return@forEach
//                        if (function.valueParameters.any {
//                                (it.type?.classifier as? KmClassifier.Class)?.name == "kotlinx/cinterop/CPointer"
//                                        && (it.type?.arguments?.singleOrNull()?.type?.classifier as? KmClassifier.Class)?.name?.endsWith("va_list_tag") == true
//                            }) {
//                            groupedByPath.getValue(mismatch.path) -= mismatch
//                        }
//                    }
//                }
//
//                groupedByPath.entries.removeIf { (_, values) -> values.isEmpty() }
//
//                if (groupedByPath.isNotEmpty()) {
//                    println("- [MISMATCHES] $lib")
//                    groupedByPath.values.flatten().forEachIndexed { index, mismatch ->
//                        println("  ${index + 1}. $mismatch")
//                    }
//                } else if (printMatches) println("- [match] $lib")
            }
        }
    }
    println("END $klibPath\n")

    return
}
