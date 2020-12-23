/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle

import org.jetbrains.kotlin.incremental.ChangesCollector.Companion.getNonPrivateMemberNames
import org.jetbrains.kotlin.incremental.JavaClassProtoMapValueExternalizer
import org.jetbrains.kotlin.incremental.toProtoData
import org.junit.Assert
import org.junit.Test
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream
import kotlin.test.assertTrue

class JarSnapshotIT : BaseGradleIT() {
    @Test
    fun testJarSnapshot() {
        val project = Project("simpleNewIncremental")

        project.build("build") {
            assertSuccessful()
            assertTasksExecuted(":lib:compileKotlin", ":lib:compileJava", ":main:compileKotlin", ":main:compileJava")
        }
        val libJarSnapshot = project.findJarSnapshot("lib")
        Assert.assertEquals(libJarSnapshot.fqNamesToSymbols.size, 2)
    }

    @Test
    fun testKotlinClassRemovedFromLib() {
        val project = Project("simpleNewIncremental")
        project.setupWorkingDir()

        project.build("build") {
            assertSuccessful()
            assertTasksExecuted(":lib:compileKotlin", ":lib:compileJava")
        }
        val libJarSnapshot = project.findJarSnapshot("lib")

        val classToDelete = "test.KotlinClassToDelete"
        Assert.assertTrue(libJarSnapshot.fqNamesToSymbols.containsKey(classToDelete))

        project.projectDir.resolve("lib/src/main/kotlin/test/KotlinClassToDelete.kt").delete()

        project.build("build") {
            assertSuccessful()
            assertTasksExecuted(":lib:compileKotlin", ":lib:compileJava")
        }

        val libJarSnapshotNew = project.findJarSnapshot("lib")
        Assert.assertFalse(libJarSnapshotNew.fqNamesToSymbols.containsKey(classToDelete))
    }

    @Test
    fun testKotlinMethodRemovedFromLib() {
        val project = Project("simpleNewIncremental")
        project.setupWorkingDir()

        project.build("build") {
            assertSuccessful()
            assertTasksExecuted(":lib:compileKotlin", ":lib:compileJava")
        }
        val libJarSnapshot = project.findJarSnapshot("lib")

        val classToUpdate = "test.KotlinClassToDelete"
        val methodToDelete = "methodToDelete"

        val symbols = libJarSnapshot.fqNamesToSymbols[classToUpdate]
        Assert.assertTrue(symbols?.contains(methodToDelete) ?: false)

        project.projectDir.resolve(classToUpdate).writeText("package test\n" +
                                                                    "\n" +
                                                                    "class KotlinClassToDelete {\n" +
                                                                    "    fun andMoreMethod() = 1\n" +
                                                                    "\n" +
                                                                    "}")

        project.build("build") {
            assertSuccessful()
            assertTasksExecuted(":lib:compileKotlin", ":lib:compileJava")
        }

        val libJarSnapshotNew = project.findJarSnapshot("lib")
        val symbolsNew = libJarSnapshotNew.fqNamesToSymbols[classToUpdate]
        Assert.assertNotNull(symbolsNew)
        Assert.assertFalse(symbolsNew!!.contains(methodToDelete))

    }

    class TestJarSnapshot(
        val fqNamesToSymbols: Map<String, Set<String>>
    )

    private fun Project.findJarSnapshot(projectName: String): TestJarSnapshot {
        val jarSnapshot = projectDir.resolve("$projectName/build/kotlin/compileKotlin/jar-snapshot.bin")
        assertTrue(jarSnapshot.exists(), "Jar snapshot wasn't found for prject \"$projectName\"")

        return ObjectInputStream(FileInputStream(jarSnapshot)).use {
            val size = it.readInt()
            val fqNames = HashMap<String, Set<String>>(size)

            repeat(size) { i ->
                val fqName = it.readUTF()
                if (it.readBoolean()) { //only class proto data for the test
                    val serializableData = JavaClassProtoMapValueExternalizer.read(it)
                    val symbols = serializableData.toProtoData().getNonPrivateMemberNames()

                    fqNames.put(fqName, symbols)
                }
            }

            TestJarSnapshot(fqNames)
        }
    }


}