/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle

import org.jetbrains.kotlin.incremental.JavaClassProtoMapValueExternalizer
import org.jetbrains.kotlin.incremental.ProtoData
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
        Assert.assertEquals(libJarSnapshot.fqNames.size, 2)
//        Assert.assertEquals(libJarSnapshot.lookups.size, 5)

    }

    private fun Project.findJarSnapshot(projectName: String): TestJarSnapshot {
        val jarSnapshot = projectDir.resolve("$projectName/build/kotlin/compileKotlin/jar-snapshot.bin")
        assertTrue(jarSnapshot.exists(), "Jar snapshot wasn't found for prject \"$projectName\"")

        return ObjectInputStream(FileInputStream(jarSnapshot)).use {
            val size = it.readInt()
            val fqNames = ArrayList<String>(size)
            val lookups = HashMap<String, String>()
            repeat(size) { i ->
                fqNames.add(it.readUTF())
                JavaClassProtoMapValueExternalizer.read(it)
//                println(serializableData.toProtoData().proto)
            }

//                val stringArray = readArrayString()
//            mutableMap.put(fqName, serializableData.toProtoData())
            TestJarSnapshot(fqNames)
        }
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
        Assert.assertTrue(libJarSnapshot.fqNames.contains(classToDelete))

        project.projectDir.resolve("lib/src/main/kotlin/test/KotlinClassToDelete.kt").delete()

        project.build("build") {
            assertSuccessful()
            assertTasksExecuted(":lib:compileKotlin", ":lib:compileJava")
        }

        val libJarSnapshotNew = project.findJarSnapshot("lib")
        Assert.assertFalse(libJarSnapshotNew.fqNames.contains(classToDelete))
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

        val classTodelete = "lib/src/main/kotlin/test/KotlinClassToDelete.kt"
        Assert.assertTrue(libJarSnapshot.fqNames.contains(classTodelete))

        project.projectDir.resolve(classTodelete).delete()

        project.build("build") {
            assertSuccessful()
            assertTasksExecuted(":lib:compileKotlin", ":lib:compileJava")
        }

        val libJarSnapshotNew = project.findJarSnapshot("lib")
        Assert.assertFalse(libJarSnapshotNew.fqNames.contains(classTodelete))
    }

//    @Test
//    fun testKotlinMethodRemovedFromLib() {
//        val project = Project("simpleNewIncremental")
//        project.setupWorkingDir()
//
//        project.build("build") {
//            assertSuccessful()
//            assertTasksExecuted(":lib:compileKotlin", ":lib:compileJava")
//        }
//        val libJarSnapshot = project.findJarSnapshot("lib")
//
//        val classTodelete = "lib/src/main/kotlin/test/KotlinClassToUpdate.kt"
//        Assert.assertTrue(libJarSnapshot.fqNames.contains(classTodelete))
//
//        project.projectDir.resolve(classTodelete).replace
//
//        project.build("build") {
//            assertSuccessful()
//            assertTasksExecuted(":lib:compileKotlin", ":lib:compileJava")
//        }
//
//        val libJarSnapshotNew = project.findJarSnapshot("lib")
//        Assert.assertFalse(libJarSnapshotNew.fqNames.contains(classTodelete))
//    }

    @Test
    fun testKotlinClassRemoved() {
        val project = Project("simpleNewIncremental")

        project.build("build") {
            assertSuccessful()
            assertTasksExecuted(":compileKotlin", ":compileJava")
        }

        workingDir.resolve("main/src/main/kotlin/MainKotlinClassToDelete.kt")

        project.build("build") {
            assertSuccessful()
            assertTasksExecuted(":compileKotlin", ":compileJava")
        }
    }

    class TestJarSnapshot(
        val fqNames: List<String>/*,
        val protoData: ProtoData*/
    )

    fun ObjectInputStream.readFqNames(project: Project): List<String> {
        val size = readInt()
        val fqNames = ArrayList<File>(size)
        repeat(size) {
            val fqNameString = readUTF()
            fqNames.add(File(fqNameString))
        }
        return project.relativize(fqNames)
    }

    fun ObjectInputStream.readLookups(): List<Pair<String, String>> {
        val lookupSymbolSize = readInt()
        val lookupSymbols = ArrayList<Pair<String, String>>(lookupSymbolSize)
        repeat(lookupSymbolSize) {
            val name = readUTF()
            val scope = readUTF()
            lookupSymbols.add(Pair(name, scope))
        }
        return lookupSymbols
    }
}