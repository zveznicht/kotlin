/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental

import org.jetbrains.kotlin.incremental.BuildDiffsStorage.Companion.readFqNames
import org.jetbrains.kotlin.incremental.BuildDiffsStorage.Companion.readLookups
import org.jetbrains.kotlin.incremental.BuildDiffsStorage.Companion.writeFqNames
import org.jetbrains.kotlin.incremental.BuildDiffsStorage.Companion.writeLookups
import org.jetbrains.kotlin.incremental.JarSnapshot.Companion.readJarSnapshot
import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.protobuf.CodedInputStream
import org.jetbrains.kotlin.protobuf.CodedOutputStream
import java.io.*

class JarSnapshot(val protos: MutableMap<FqName, ProtoData>) {

    companion object {
        fun ObjectInputStream.readJarSnapshot(): JarSnapshot {

            val size = readInt()
            val codedInputStream = CodedInputStream.newInstance(this)
            repeat(size) {
                val fqNameString = readUTF()
                val fqName = FqName(fqNameString)
                val classProtoBuff = ProtoBuf.Class.PARSER.parseFrom(codedInputStream)

                ClassProtoData()
                when (protoData)  {
                    is ClassProtoData -> protoData.proto.writeTo(codedOutputStream)
                    is PackagePartProtoData -> protoData.proto.writeTo(codedOutputStream)
                }

            }
            //TODO fix it, to compilation only
            return JarSnapshot(mutableMapOf())
        }

        fun ObjectOutputStream.writeJarSnapshot(jarSnapshot: JarSnapshot) {
            val codedOutputStream = CodedOutputStream.newInstance(this)
            writeInt(jarSnapshot.protos.size)
            for (entry in jarSnapshot.protos) {
                writeUTF(entry.key.asString())
                val protoData = entry.value
                when (protoData)  {
                     is ClassProtoData -> protoData.proto.writeTo(codedOutputStream)
                     is PackagePartProtoData -> protoData.proto.writeTo(codedOutputStream)
                }
            }
            codedOutputStream.flush()
//            writeLookups(jarSnapshot.symbols)
//            writeFqNames(jarSnapshot.fqNames)
        }

        fun write(buildInfo: JarSnapshot, file: File) {
            ObjectOutputStream(FileOutputStream(file)).use {
                it.writeJarSnapshot(buildInfo)
            }
        }

        fun read (file: File) : JarSnapshot {
            ObjectInputStream(FileInputStream(file)).use {
//                val codeOutputStream = CodedOutputStream.newInstance(it)
                return it.readJarSnapshot()
            }
        }
    }
}