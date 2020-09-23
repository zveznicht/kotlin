/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.pill.mapper

import com.google.gson.Gson
import org.jetbrains.kotlin.pill.util.PathContext
import java.io.File

sealed class Substitution {
    class ProjectLibrary(val libraryName: String) : Substitution()
    class ModuleOutput(val moduleName: String) : Substitution()
    class File(val file: java.io.File) : Substitution()
}

typealias Substitutions = Map<String, SubstitutionsForArtifact>
typealias SubstitutionsForArtifact = Map<String, List<Substitution>>

class SubstitutionFileReader(private val mappingsDir: File) {
    private companion object {
        private const val NAME_POSTFIX = "-structure-mapping.json"
        private val GSON = Gson()
    }

    fun read(pathContext: PathContext): Substitutions {
        val substitutions = HashMap<String, SubstitutionsForArtifact>()

        for (file in mappingsDir.listFiles().orEmpty()) {
            val artifactNames = getArtifactNames(file) ?: continue
            val substitutionsForArtifact = HashMap<String, MutableList<Substitution>>()

            val rawSubstitutions = GSON.fromJson(file.readText(), Array<RawSubstitution>::class.java)
            for (rawSubstitution in rawSubstitutions) {
                val substitution = when (rawSubstitution.type) {
                    "project-library" -> Substitution.ProjectLibrary(rawSubstitution.libraryName ?: error("Library name is not specified"))
                    "module-output" -> Substitution.ModuleOutput(rawSubstitution.moduleName ?: error("Module name is not specified"))
                    "module-library-file" -> {
                        val rawPath = rawSubstitution.filePath ?: error("Library file path is not specified")
                        val path = pathContext.substituteWithValues(rawPath)
                        Substitution.File(File(path))
                    }
                    else -> error("Unexpected substitution kind")
                }

                substitutionsForArtifact.getOrPut(rawSubstitution.path, ::ArrayList) += substitution
            }

            for (artifactName in artifactNames) {
                substitutions[artifactName] = substitutionsForArtifact
            }
        }

        return substitutions
    }

    private fun getArtifactNames(file: File): List<String>? {
        val name = file.name
        if (!name.endsWith(NAME_POSTFIX)) {
            return null
        }

        return when (val artifactName = name.dropLast(NAME_POSTFIX.length)) {
            "ideaIU-project" -> listOf("ideaIU", "ideaIC")
            "intellij-core-project" -> listOf("intellij-core")
            "standalone-jps" -> listOf("jps-standalone")
            else -> error("Unknown artifact name $artifactName")
        }
    }
}

private class RawSubstitution(
    val path: String,
    val type: String,
    val moduleName: String?,
    val libraryName: String?,
    val filePath: String?
)