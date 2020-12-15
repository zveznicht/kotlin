/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental

import org.jetbrains.kotlin.incremental.JarSnapshot.Companion.readJarSnapshot
import org.jetbrains.kotlin.incremental.JavaClassProtoMapValueExternalizer.readBytesWithSize
import org.jetbrains.kotlin.incremental.JavaClassProtoMapValueExternalizer.writeBytesWithSize
import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.metadata.deserialization.NameResolverImpl
import org.jetbrains.kotlin.metadata.jvm.JvmProtoBuf
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmNameResolver
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.protobuf.CodedOutputStream
import org.jetbrains.kotlin.renderer.DescriptorRenderer
import org.jetbrains.kotlin.serialization.DescriptorSerializer
import java.io.*

class JarSnapshot(val protos: MutableMap<FqName, ProtoData>) {

    companion object {
        fun ObjectInputStream.readJarSnapshot(): JarSnapshot {

            val size = readInt()
//            val codedInputStream = CodedInputStream.newInstance(this)
            val mutableMap = hashMapOf<FqName, ProtoData>()
            repeat(size) {
                val fqNameString = readUTF()
                val fqName = FqName(fqNameString)
//                val classProtoBuff = ProtoBuf.Class.PARSER.parseFrom(codedInputStream)

                val serializableData = JavaClassProtoMapValueExternalizer.read(this)
//                val stringArray = readArrayString()
                mutableMap.put(fqName, serializableData.toProtoData())
            }
            return JarSnapshot(mutableMap)


//                if (isPackageFacade) {
//                    val (nameResolver, packageProto) = JvmProtoBufUtil.readPackageDataFrom(bytes, strings)
//                    PackagePartProtoData(packageProto, nameResolver, packageFqName)
//                } else {
//                    val (nameResolver, classProto) = JvmProtoBufUtil.readClassDataFrom(bytes, strings)
//                    ClassProtoData(classProto, nameResolver)
//                }
//                when (protoData)  {
//                    is ClassProtoData -> protoData.proto.writeTo(codedOutputStream)
//                    is PackagePartProtoData -> protoData.proto.writeTo(codedOutputStream)
//                }

//            }
            //TODO fix it, to compilation only
//            return JarSnapshot(mutableMap)
        }

        fun ObjectOutputStream.writeJarSnapshot(jarSnapshot: JarSnapshot) {
//            val codedOutputStream = CodedOutputStream.newInstance(this)

            writeInt(jarSnapshot.protos.size)
            for (entry in jarSnapshot.protos) {
                writeUTF(entry.key.asString())
                val protoData = entry.value
                when (protoData) {
                     is ClassProtoData -> {
                         val extension = JavaClassesSerializerExtension()
                         val (stringTable, qualifiedNameTable) = extension.stringTable.buildProto()
                         JavaClassProtoMapValueExternalizer.save(this,
                             SerializedJavaClass(protoData.proto, stringTable, qualifiedNameTable)
                         )
                     }
//                     is PackagePartProtoData -> protoData.proto.writeTo(codedOutputStream)
                }
            }
//            codedOutputStream.flush()
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
        fun ObjectInputStream.readArrayString(): Array<String>{
            val size = readInt()
            val array = arrayOf<String>()
            repeat(size) {
                array[it] = readUTF()
            }
            return array
        }

        fun ObjectOutputStream.writeArrayString(array: Array<String>) {
            writeInt(array.size)
            for (string in array) {
                writeUTF(string)
            }
        }

//        fun ObjectInputStream.readStringTableTypes(): JvmProtoBuf.StringTableTypes {
//            return ProtoBuf.StringTable.parseFrom(readBytesWithSize(), JAVA_CLASS_PROTOBUF_REGISTRY)
//        }

        fun ObjectOutputStream.writeStringTableTypes(stringTableTypes: JvmProtoBuf.StringTableTypes) {
            writeBytesWithSize(stringTableTypes.toByteArray())
        }

    }


}